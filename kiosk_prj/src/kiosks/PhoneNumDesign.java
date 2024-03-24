package kiosks;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kiosks.PhoneNumEvent;

public class PhoneNumDesign extends JFrame {

	private JLabel orderAmount;
	private JTextField jtfPhoneNum;
	private Font font;
	private JButton keypad;

	public PhoneNumDesign() {
		setLayout(null);

		// 결제 금액 라벨
		JLabel jlAmount = new JLabel("결제금액");
		font = jlAmount.getFont();
		jlAmount.setFont(font.deriveFont(Font.PLAIN, 15));
		jlAmount.setBounds(150, 50, 100, 30);

		orderAmount = new JLabel("");
		font = orderAmount.getFont();
		orderAmount.setFont(font.deriveFont(Font.PLAIN, 20));
		orderAmount.setBounds(150, 100, 100, 30);

		// 번호 조회 안내 라벨
		JLabel guide = new JLabel("<html>휴대폰 번호를 입력해주세요.<br>쿠폰을 조회합니다.</html>");
		font = guide.getFont();
		guide.setFont(font.deriveFont(Font.PLAIN, 23));
		guide.setBounds(150, 160, 300, 50);

		jtfPhoneNum = new JTextField(11);
		font = jtfPhoneNum.getFont();
		jtfPhoneNum.setFont(font.deriveFont(Font.PLAIN, 20));
		jtfPhoneNum.setBounds(150, 240, 200, 50);

		// 번호 입력하는 키패드 만들기
		JPanel jpPhoneNum = new JPanel(new GridLayout(4, 3));

		// 버튼에 넣을 숫자&기호 배열
		String[][] btnLabel = { { "1", "2", "3" }, { "4", "5", "6" }, { "7", "8", "9" }, { "<", "0", "입력" }, };

		// 버튼 배열
		JButton[][] btnArr = new JButton[4][3];
		

		// 버튼 생성 및 프레임에 추가
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				keypad = new JButton(btnLabel[i][j]);
				btnArr[i][j] = keypad;
				jpPhoneNum.add(keypad);
			}//end for
		}//end for

		// 버튼에 ActionListener 추가
		PhoneNumEvent pne = new PhoneNumEvent(this);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				keypad = btnArr[i][j];
				keypad.addActionListener(pne);
			} // end for
		} // end for
		keypad.addActionListener(pne);
		
		jpPhoneNum.setBounds(150, 320, 300, 300);
		
		//프레임에 요소 추가
		add(jlAmount);
		add(orderAmount);
		add(guide);
		add(jtfPhoneNum);
		add(jpPhoneNum);
		
		setVisible(true);
		setResizable(false);
		setSize(600, 800);
		setLocationRelativeTo(null);

	}// PhoneNumDesign

	public static void main(String[] args) {
		new PhoneNumDesign();
	}// main

	public JLabel getOrderAmount() {
		return orderAmount;
	}

	public JTextField getJtfPhoneNum() {
		return jtfPhoneNum;
	}

	public void setOrderAmount(JLabel orderAmount) {
		this.orderAmount = orderAmount;
	}

	public void setJtfPhoneNum(JTextField jtfPhoneNum) {
		this.jtfPhoneNum = jtfPhoneNum;
	}

	public void setKeypad(JButton keypad) {
		this.keypad = keypad;
	}

	public JButton getKeypad() {
		return keypad;
	}


}// class
