package kiosks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import kiosks.PaymentPageDesign;
import kiosks.PhoneNumDesign;
import kiosks.UsingCouponDesign;
import kiosks.dao.SelectCouponDAO;
import kiosks.vo.SelectCouponVO;

public class UsingCouponEvent extends WindowAdapter implements ActionListener, MouseListener {

	private UsingCouponDesign ucd;
	

	public UsingCouponEvent(UsingCouponDesign ucd) {
		this.ucd = ucd;
//		setCouponList("01012345678");
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
			String couponName = ucd.getCouponList().getValueAt(ucd.getCouponList().getSelectedRow(), 0).toString();
			setDiscount(couponName);
			ucd.dispose();
		} else {
			JOptionPane.showMessageDialog(null, "사용할 쿠폰을 선택해주세요.");
			return;
		}
	}// useCoup

	/**
	 * 선택한 쿠폰의 할인 가격 적용하기
	 * 
	 * @param couponName
	 */
	private void setDiscount(String couponName) {
		
		SelectCouponDAO scDAO = SelectCouponDAO.getInstance();
		try {
			List<SelectCouponVO> list = scDAO.searchDiscount(couponName);
			if (!list.isEmpty()) {
				SelectCouponVO scVO;
				scVO = list.get(0);
				
				// 결제창의 총 결제 금액 가져오기
				String totalPriceText = ucd.getTotalPrice();
				if (!totalPriceText.isEmpty()) {
//					int totalPrice = Integer.parseInt(totalPriceText);
					System.out.println("현재 결제 금액 " + ucd.getTotalPrice());
					// 할인 금액 빼기
					int result = Integer.parseInt(ucd.getTotalPrice()) - scVO.getDisCount();
					System.out.println("할인 적용금액 숫자 " + result);
					String resultStr = String.valueOf(result);
					System.out.println("현재 결제 금액 문자열 " + resultStr);
					// 할인된 금액을 총 결제 금액으로 설정
//					ppd.getTotalPrice().setText(resultStr);
					
					
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setCouponList(String phoneNum) {
		SelectCouponDAO scDAO = SelectCouponDAO.getInstance();
		
		try {
			List<SelectCouponVO> list = scDAO.searchCoupByPhoneNum(phoneNum);
			
			DefaultTableModel dtm = ucd.getDtmCouponList();
			
			dtm.setRowCount(0);
			
			String[] rowData = null;
			SelectCouponVO scVO = null;
			
			for(int i = 0; i < list.size(); i++) {
				scVO = list.get(i);
				rowData = new String[2];
				rowData[0] = scVO.getCouponName();
				rowData[1] = scVO.getPublishDate();
				
				dtm.addRow(rowData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

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
