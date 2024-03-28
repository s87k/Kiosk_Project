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
		String currentText = pnd.getJtfPhoneNum().getText().trim();
		if (currentText.length() < 11) {
			pnd.getJtfPhoneNum().setText(currentText + buttonText);
		}// end if
	}//inputPhoneNum
	
	/**
	 * 입력한 번호 삭제하기 
	 */
	private void backSpace() {
		String currentText = pnd.getJtfPhoneNum().getText().trim();
		if (!currentText.isEmpty()) {
			currentText = currentText.substring(0, currentText.length() - 1);
			pnd.getJtfPhoneNum().setText(currentText);
		}// end if
	}//backSpace
	
	/**
	 * 입력 번호 조건 확인
	 */
	private void searchMem() {
		if (pnd.getJtfPhoneNum().getText().trim().length() < 10) {
			JOptionPane.showMessageDialog(pnd, "전화번호의 최소 글자 수는 10자리 입니다.");
			return;
			// 전화번호 최소 글자 수를 충족한 경우 
		} else {
			String currentText = pnd.getJtfPhoneNum().getText().trim();
			checkMem(currentText);
		} // else if
	}//searchMem
	
	/**
	 * 입력된 번호로 회원 조회
	 * @param phoneNumber
	 */
	private void checkMem(String phoneNumber) {
		SearchMemberShipDAO smsDAO = SearchMemberShipDAO.getInstance();
		try {
			List<SearchMemberShipVO> list = smsDAO.selectPhoneNum(phoneNumber);
			//회원일 경우
			if(!list.isEmpty()) {
				PaymentPageDesign.strPhoneNum = phoneNumber;
				pnd.dispose();
				new UsingCouponDesign(ppd, phoneNumber);
			} else {
			// 회원이 아닐 경우
				String currentText = pnd.getJtfPhoneNum().getText().trim();
				String inform = "<html>입력하신 전화번호는 회원이 아닙니다.<br>      "+ currentText +"<br>이 번호로 회원가입을 하시겠습니까?</html>";
				
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
	
	
	
}// class
