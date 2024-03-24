package kiosks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import kiosks.JoinMembershipDesign;
import kiosks.PhoneNumDesign;
import kiosks.UsingCouponDesign;

public class PhoneNumEvent extends WindowAdapter implements ActionListener {

	private PhoneNumDesign pnd;

	public PhoneNumEvent(PhoneNumDesign pnd) {
		this.pnd = pnd;
	}// PhoneNumEvent

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			JButton button = (JButton) e.getSource();
			String buttonText = button.getText();

			// "입력" 과 "<" 버튼이 아닌 경우
			if (!(buttonText.equals("입력") || buttonText.equals("<"))) {
				String currentText = pnd.getJtfPhoneNum().getText();
				if (currentText.length() <= 10) {
					pnd.getJtfPhoneNum().setText(currentText + buttonText); // 텍스트 필드에 버튼의 텍스트 추가
				}

				// "<" 버튼인 경우
			} else if (buttonText.equals("<")) {
				String currentText = pnd.getJtfPhoneNum().getText();
				if (!currentText.isEmpty()) {
					currentText = currentText.substring(0, currentText.length() - 1);
					pnd.getJtfPhoneNum().setText(currentText);
				}

				// "입력" 버튼인 경우
			} else if (buttonText.equals("입력")) {
				if (pnd.getJtfPhoneNum().getText().length() < 10) {
					JOptionPane.showMessageDialog(pnd, "전화번호의 최소 글자 수는 10자리 입니다.", "안내",
							JOptionPane.INFORMATION_MESSAGE);
					return;//메시지 다이얼로그가 2번씩 뜨는데 이유를 모르겠음.
					
					//전화번호 최소 글자 수를 충족한 경우
				} else {
					int result = JOptionPane.showConfirmDialog(null, "일단 Y : 쿠폰조회 N : 회원가입", "확인",
							JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION) {
						new UsingCouponDesign(pnd, "옵션 선택");
					} else if (result == JOptionPane.NO_OPTION) {
						new JoinMembershipDesign();
						pnd.dispose();
					}
				} // else if
			} // end if
		} // end if
	}// actionPerformed

}// class
