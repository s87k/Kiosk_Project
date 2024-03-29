package kiosks;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kiosks.CompletePayPageEvent;
import kiosks.OrderDetailEvent;

public class CompletePayPageDesign extends JFrame {

	private JLabel memberCoup, issuedGuide;
	private JButton toStart;
	private Font font;
	private String phoneNum;

	public CompletePayPageDesign(String phoneNum) {
		this.phoneNum = phoneNum;
		setLayout(null);

		// 주문완료
		JLabel orderFinGuide = new JLabel("주문완료되었습니다.");
		font = orderFinGuide.getFont();
		orderFinGuide.setFont(font.deriveFont(Font.BOLD, 20));
		orderFinGuide.setBounds(150, 50, 300, 50);

		add(orderFinGuide);

		// 쿠폰 사용
		issuedGuide = new JLabel("<html>주문해주셔서 감사합니다.<br>곧 음료가 준비됩니다.</html>");
		font = issuedGuide.getFont();
		issuedGuide.setFont(font.deriveFont(Font.BOLD, 15));
		issuedGuide.setBounds(160, 250, 200, 50);

		memberCoup = new JLabel("");
		font = memberCoup.getFont();
		memberCoup.setFont(font.deriveFont(Font.BOLD, 13));
		memberCoup.setBounds(140, 320, 300, 30);

		add(issuedGuide);
		add(memberCoup);

		// 처음으로 버튼 추가
		toStart = new JButton("처음으로");
		toStart.setBounds(330, 450, 100, 30);
		add(toStart);

		// actionListener 추가
		CompletePayPageEvent cppe = new CompletePayPageEvent(this);
		toStart.addActionListener(cppe);

		addWindowListener(cppe);

		getContentPane().setBackground(new Color(0xECEDFA));
		setSize(500, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);

	}// CompletePayPageDesign

	public JLabel getMemberCoup() {
		return memberCoup;
	}

	public JButton getToStart() {
		return toStart;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public JLabel getIssuedGuide() {
		return issuedGuide;
	}
	
	

}// class
