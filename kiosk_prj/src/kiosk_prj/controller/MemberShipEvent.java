package kiosk_prj.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import kiosk_prj.view.MemberShipDesign;

@SuppressWarnings("serial")
public class MemberShipEvent extends WindowAdapter implements ActionListener {
	private MemberShipDesign msd;

	public MemberShipEvent(MemberShipDesign msd) {
		this.msd = msd;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton numberArr[] = msd.getInputNumber();
		String currentText = msd.getjPhoneNum().getText();

		// 숫자 입력 버튼 for문 설정하여 코드 가독성 향상
		if (currentText.length() <= 10) {
			for (int i = 0; i <= 9; i++) {
				if (e.getSource() == numberArr[i]) {
					msd.getjPhoneNum().setText(currentText + i);
					break;
				}
			}
		}

		if (e.getSource() == numberArr[10]) {
			if (!currentText.isEmpty()) {
				String newText = currentText.substring(0, currentText.length() - 1);
				msd.getjPhoneNum().setText(newText);
			}
		}
		if (e.getSource() == numberArr[11]) {
			msd.getjPhoneNum().setText("");
		}

		if (e.getSource() == msd.getExit()) {
			msd.dispose();
		}
	}

}
