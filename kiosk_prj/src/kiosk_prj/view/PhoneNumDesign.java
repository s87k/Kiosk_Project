package kiosk_prj.view;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PhoneNumDesign extends JFrame {

	private JLabel orderAmount;
	private JTextField jtfPhoneNum;
	private Font font;

	public PhoneNumDesign() {
		setLayout(null);

		// 결제 금액 라벨
		JLabel jlAmount = new JLabel("결제금액");
		font = jlAmount.getFont();
		jlAmount.setFont(font.deriveFont(Font.PLAIN, 15));
		jlAmount.setBounds(150, 50, 100, 30);

		orderAmount = new JLabel("2700");
		font = orderAmount.getFont();
		orderAmount.setFont(font.deriveFont(Font.PLAIN, 20));
		orderAmount.setBounds(150, 100, 100, 30);

		// 번호 조회 안내 라벨
		JLabel guide = new JLabel("<html>휴대폰 번호를 입력해주세요.<br>쿠폰을 조회합니다.</html>");
		font = guide.getFont();
		guide.setFont(font.deriveFont(Font.PLAIN, 23));
		guide.setBounds(150, 160, 300, 50);

		jtfPhoneNum = new JTextField(13);
		jtfPhoneNum.setBounds(150, 220, 200, 30);

		// 번호 입력하는 키패드 만들기
		JPanel jpPhoneNum = new JPanel(new GridLayout(4, 3));

		// 버튼에 넣을 숫자&기호 배열
		String[][] btnLabel = { { "1", "2", "3" }, { "4", "5", "6" }, { "7", "8", "9" }, { "<", "0", "입력" }, };

		// 버튼 배열
		JButton[][] btnArr = new JButton[4][3];

		// 버튼 생성 및 프레임에 추가
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				JButton button = new JButton(btnLabel[i][j]);
				btnArr[i][j] = button;
				jpPhoneNum.add(button);
			}//end for
		}//end for

		// 버튼에 ActionListener 추가
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				JButton button = btnArr[i][j];
				button.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// 각 버튼을 클릭했을 때의 동작 추가
						String buttonText = button.getText();
						jtfPhoneNum.setText(buttonText);
						System.out.println("Clicked: " + buttonText);
						// 버튼에 대한 다른 동작 수행 가능
					}// actionPerformed

				});// addActionListener
			} // end for
		} // end for
		
		jpPhoneNum.setBounds(150, 280, 300, 300);
		
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

}// class
