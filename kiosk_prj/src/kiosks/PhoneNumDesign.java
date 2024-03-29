package kiosks;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.MaskFormatter;

import kiosks.PhoneNumEvent;

public class PhoneNumDesign extends JDialog {

	private JLabel orderAmount;
	private JFormattedTextField jtfPhoneNum;
	private Font font;
	private JButton keypad;
	private String totalPrice;

	public PhoneNumDesign(PaymentPageDesign ppd) {
		super(ppd, "번호 조회", true);
		setLayout(null);

		// 결제 금액 라벨
		JLabel jlAmount = new JLabel("결제금액");
		font = jlAmount.getFont();
		jlAmount.setFont(font.deriveFont(Font.PLAIN, 15));
		jlAmount.setBounds(100, 30, 70, 30);

		orderAmount = new JLabel(ppd.getTotalPrice().getText()+" 원");
		font = orderAmount.getFont();
		orderAmount.setFont(font.deriveFont(Font.PLAIN, 20));
		orderAmount.setBounds(200, 30, 100, 30);

		// 번호 조회 안내 라벨
		JLabel guide = new JLabel("<html>휴대폰 번호를 입력해주세요.<br>쿠폰을 조회합니다.</html>");
		font = guide.getFont();
		guide.setFont(font.deriveFont(Font.PLAIN, 23));
		guide.setBounds(100, 90, 300, 50);

		try {
			MaskFormatter form = new MaskFormatter("###########");
			form.setPlaceholderCharacter(' ');
			jtfPhoneNum = new JFormattedTextField(form);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		font = jtfPhoneNum.getFont();
		jtfPhoneNum.setFont(font.deriveFont(Font.PLAIN, 20));
		jtfPhoneNum.setBounds(100, 160, 200, 50);

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
			} // end for
		} // end for

		// 버튼에 ActionListener 추가
		PhoneNumEvent pne = new PhoneNumEvent(this, ppd);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				keypad = btnArr[i][j];
				keypad.addActionListener(pne);
			} // end for
		} // end for

		jpPhoneNum.setBounds(100, 235, 300, 300);

		// 프레임에 요소 추가
		add(jlAmount);
		add(orderAmount);
		add(guide);
		add(jtfPhoneNum);
		add(jpPhoneNum);

		getContentPane().setBackground(new Color(0xECEDFA));
		setSize(500, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}// PhoneNumDesign

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public JLabel getOrderAmount() {
		return orderAmount;
	}

	public JTextField getJtfPhoneNum() {
		return jtfPhoneNum;
	}

	public void setOrderAmount(JLabel orderAmount) {
		this.orderAmount = orderAmount;
	}

	public void setJtfPhoneNum(JFormattedTextField jtfPhoneNum) {
		this.jtfPhoneNum = jtfPhoneNum;
	}

	public void setKeypad(JButton keypad) {
		this.keypad = keypad;
	}

	public JButton getKeypad() {
		return keypad;
	}

}// class
