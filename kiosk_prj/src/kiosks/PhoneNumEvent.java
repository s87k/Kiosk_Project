package kiosks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import kiosks.JoinMembershipDesign;
import kiosks.PhoneNumDesign;
import kiosks.UsingCouponDesign;
import kiosks.dao.SearchMemberShipDAO;
import kiosks.dao.SelectCouponDAO;
import kiosks.vo.SearchMemberShipVO;
import kiosks.vo.SelectCouponVO;

public class PhoneNumEvent extends WindowAdapter implements ActionListener {

	private PhoneNumDesign pnd;
	private PaymentPageDesign ppd;
	
	private String buttonText;

	public PhoneNumEvent(PhoneNumDesign pnd, PaymentPageDesign ppd) {
		this.pnd = pnd;
		this.ppd = ppd;
	}// PhoneNumEvent

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			JButton button = (JButton) e.getSource();
			buttonText = button.getText();

			// 번호 입력
			if (!(buttonText.equals("입력") || buttonText.equals("<"))) {
				inputPhoneNum();

			// backSpace 맨 뒷자리 번호 삭제
			} else if (buttonText.equals("<")) {
				backSpace();

			// "입력"을 눌러 번호 조회
			} else if (buttonText.equals("입력")) {
				searchMem();
			} // end if
		} // end if
	}// actionPerformed
	
	/**
	 * 전화번호 입력하기
	 */
	private void inputPhoneNum() {
		String currentText = pnd.getJtfPhoneNum().getText();
		if (currentText.length() < 10) {
			pnd.getJtfPhoneNum().setText(currentText + buttonText); // 텍스트 필드에 버튼의 텍스트 추가
		}// end if
	}//inputPhoneNum
	
	/**
	 * 입력한 번호 삭제하기 
	 */
	private void backSpace() {
		String currentText = pnd.getJtfPhoneNum().getText();
		if (!currentText.isEmpty()) {
			currentText = currentText.substring(0, currentText.length() - 1);
			pnd.getJtfPhoneNum().setText(currentText);
		}// end if
	}//backSpace
	
	/**
	 * 입력된 번호로 회원조회하기
	 */
	private void searchMem() {
		if (pnd.getJtfPhoneNum().getText().length() < 10) {
			JOptionPane.showMessageDialog(pnd, "전화번호의 최소 글자 수는 10자리 입니다.");
			return;
			// 전화번호 최소 글자 수를 충족한 경우 
		} else {
			String currentText = pnd.getJtfPhoneNum().getText();
			checkMem(currentText);
		} // else if
	}//searchMem
	
	private void checkMem(String phoneNumber) {
		SearchMemberShipDAO smsDAO = SearchMemberShipDAO.getInstance();
		try {
			List<SearchMemberShipVO> list = smsDAO.selectPhoneNum(phoneNumber);
			//회원일 경우
			if(!list.isEmpty()) {
//				SearchMemberShipVO smsVO = list.get(0);
				//보유쿠폰 리스트 보내기
//				new UsingCouponDesign(ppd, "쿠폰 조회", ppd.getTotalPrice().getText());
				heldCoup(phoneNumber);
				
				//보유쿠폰 조회하고 UsingCouponDesign의 리스트에 뿌리기
				
			} else {
			// 회원이 아닐 경우
				String currentText = pnd.getJtfPhoneNum().getText();
				String inform = "<html>입력하신 전화번호는 회원이 아닙니다.<br>"+ currentText +"<br>이 번호로 회원가입을 하시겠습니까?</html>";
				
				int result = JOptionPane.showConfirmDialog(null, inform, "확인",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					JoinMembershipDesign jmd = new JoinMembershipDesign(pnd, "회원가입");
					jmd.getJtfPhoneNum().setText(currentText);
				} else if (result == JOptionPane.NO_OPTION) {
					pnd.dispose();
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void heldCoup(String phoneNumber) {
		SelectCouponDAO scDAO = SelectCouponDAO.getInstance();
		try {
			List<SelectCouponVO> list = scDAO.searchCoupByPhoneNum(phoneNumber);
			UsingCouponDesign ucd = new UsingCouponDesign(ppd, phoneNumber, ppd.getTotalPrice().getText());
			DefaultTableModel dtm = ucd.getDtmCouponList();
			dtm.setRowCount(0);
			
			String[] rowData = null;
			SelectCouponVO scVO = null;
			for(int i=0; i < list.size(); i++) {
				scVO = list.get(i);
				rowData = new String[2];
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
				rowData[1] = adjustDate+" 까지";
				dtm.addRow(rowData);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}// class
