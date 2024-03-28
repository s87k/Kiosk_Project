package kiosks;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import kiosk_prj.coupon.dao.CouponHeldDAO;
import kiosk_prj.coupon.vo.CouponHeldVO;
import kiosk_prj.coupon.vo.StatusUse;
import kiosks.PaymentPageDesign;
import kiosks.PhoneNumDesign;
import kiosks.UsingCouponDesign;
import kiosks.dao.SelectCouponDAO;
import kiosks.vo.SelectCouponVO;

public class UsingCouponEvent extends WindowAdapter implements ActionListener, MouseListener {

	private UsingCouponDesign ucd;
	private PaymentPageDesign ppd;

	private String phoneNum;

	public UsingCouponEvent(UsingCouponDesign ucd, PaymentPageDesign ppd) {
		this.ucd = ucd;
		this.ppd = ppd;

		phoneNum = ucd.getPhoneNumber();
		setCouponList(phoneNum);
	}// UsingCouponEvent

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ucd.getCancel()) {
			ucd.dispose();
		}

		if (e.getSource() == ucd.getUse()) {
			useCoup();
		}
	}// actionPerformed

	/**
	 * 사용할 쿠폰 선택하기
	 */
	private void useCoup() {
		int select = ucd.getCouponList().getSelectedRow();
		if (select != -1) {
//			String couponName = ucd.getCouponList().getValueAt(ucd.getCouponList().getSelectedRow(), 0).toString();
			setDiscount(Integer.parseInt(ucd.getDtmCouponList().getValueAt(ucd.getCouponList().getSelectedRow(), 2).toString()));
			// 쿠폰 사용후 조회버튼과 안내메시지 안보이게
			ppd.getBtnCheckCoupon().setVisible(false);
			ppd.getLblCheckCouponGuide().setVisible(false);
			// 쿠폰 적용 후 안내메시지 재설정
			ppd.getUsedCoup().setText("쿠폰이 적용되었습니다.");
			Font font = ppd.getUsedCoup().getFont();
			ppd.getUsedCoup().setFont(font.deriveFont(Font.BOLD, 15));
			ppd.getUsedCoup().setBounds(210, 125, 200, 30);
			PaymentPageDesign.coupPubCode = ucd.getDtmCouponList().getValueAt(ucd.getCouponList().getSelectedRow(), 3).toString();
			//쿠폰 선택창 종료
			ucd.dispose();
		} else {
			JOptionPane.showMessageDialog(null, "사용할 쿠폰을 선택해주세요.");
			return;
		}
	}// useCoup

	/**
	 * 선택한 쿠폰의 할인 가격 적용하기
	 * 
	 * @param discount
	 */
	private void setDiscount(int discount) {
		// 결제창의 총 결제 금액 가져오기
		String totalPriceText = ppd.getTotalPrice().getText();
		if (!totalPriceText.isEmpty()) {
			int totalPrice = Integer.parseInt(totalPriceText);
			// 할인금액이 결제 금액보다 큰지 확인
			if (totalPrice < discount) {
				JOptionPane.showMessageDialog(ucd, "결제금액보다 할인금액이 큰 쿠폰을 사용하실 수 없습니다.");
				return;
			}
			// 할인 금액 빼기
			int result = totalPrice - discount;
			// 결제 금액 문자열로 전환
			String resultStr = String.valueOf(result);
			// 할인된 금액을 총 결제 금액으로 설정
			ppd.getTotalPrice().setText(resultStr);
			PaymentPageDesign.amount = result;	
			// 쿠폰을 사용한 회원의 전화번호로 재설정
			ppd.setPhoneNum(phoneNum);
			
		}
	}// setDiscount

	/**
	 * 전화번호로 보유쿠폰 조회
	 * 
	 * @param phoneNum
	 */
	public void setCouponList(String phoneNum) {
		SelectCouponDAO scDAO = SelectCouponDAO.getInstance();
		try {
			List<SelectCouponVO> list = scDAO.searchCoupByPhoneNum(phoneNum);
			if (list.isEmpty()) {
				JOptionPane.showMessageDialog(ucd, "사용 가능한 쿠폰이 없습니다.");
				ucd.dispose();
			}

			DefaultTableModel dtm = ucd.getDtmCouponList();
			dtm.setRowCount(0);

			String[] rowData = null;
			SelectCouponVO scVO = null;
			for (int i = 0; i < list.size(); i++) {
				scVO = list.get(i);
				rowData = new String[4];
				// 1. 쿠폰 이름
				rowData[0] = scVO.getCouponName();
				// 2. 만료일 설정
				// 2-1 쿠폰 만료일 설정을 위해 쿠폰 발급일을 가져옴
				String publishDate = scVO.getPublishDate();
				// 2-2 DB에 설정되어 있는 쿠폰 만료일의 값을 int로 파싱
				int expireDateValue = Integer.parseInt(scVO.getExpireDate());
				// 2-3 쿠폰 만료일의 값을 발급일에 추가하기 위해 "-"를 기준으로 발급일을 자름
				String[] publishDateParts = publishDate.split("-");
				// 2-4 쿠폰 만료일을 더했을 때, 12월이 넘어가면 연도의 값을 더해줘야 하므로, 발급일의 연도의 값을 가져옴
				int publishYear = Integer.parseInt(publishDateParts[0]);
				// 2-5 쿠폰 만료일을 더하기 위해 발급일 월의 값을 가져옴
				int publishMonth = Integer.parseInt(publishDateParts[1]);
				// 2-6 쿠폰 만료일 설정을 위해 발급월의 값에 만료일의 값을 더해줌
				int totalMonth = publishMonth + expireDateValue;
				// 2-7 만약 만료일의 값과 발급일의 값을 더했을 때, 12가 넘어가면 다음년도로 넘어감
				int adjustedYear = publishYear + (totalMonth / 12);
				// 2-8 만료일이 다음 년도로 남어가게 되면, 더한 값에 12를 나눈 나머지로 만료일의 월을 설정함
				int adjustedMonth = totalMonth % 12;
				// 2-8 만료일의 출력 형식을 발급일의 출력 형식에 맞게 바꿔줌
				String adjustDate = adjustedYear + "-" + adjustedMonth + "-" + publishDateParts[2];
				// 2-9 쿠폰 만료일
				rowData[1] = adjustDate + " 까지";
				
				rowData[2] = String.valueOf(scVO.getDisCount());
				rowData[3] = scVO.getCoupPubCode();
				dtm.addRow(rowData);
			}
//			PaymentPageDesign.coupPubCode = scVO.getCoupPubCode();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// setCouponList

	@Override
	public void windowClosing(WindowEvent e) {
		ucd.dispose();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		ucd.dispose();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}// class
