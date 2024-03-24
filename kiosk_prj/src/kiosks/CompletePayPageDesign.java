package kiosks;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kiosks.CompletePayPageEvent;
import kiosks.OrderDetailEvent;

public class CompletePayPageDesign extends JFrame {

	private JLabel orderNum, memberNum;
	private JButton toStart;
	private Font font;

	public CompletePayPageDesign() {
		setLayout(null);

		// 주문완료
		JLabel orderFinGuide = new JLabel("주문완료되었습니다.");
		font = orderFinGuide.getFont();
		orderFinGuide.setFont(font.deriveFont(Font.PLAIN, 20));
		orderFinGuide.setBounds(100, 50, 300, 50);

		orderNum = new JLabel("n번째 주문");
		font = orderNum.getFont();
		orderNum.setFont(font.deriveFont(Font.PLAIN, 20));
		orderNum.setBounds(100, 150, 200, 50);

		add(orderFinGuide);
		add(orderNum);

		// 쿠폰 사용
		JLabel issuedGuide = new JLabel("쿠폰이 발급되었습니다!");
		font = issuedGuide.getFont();
		issuedGuide.setFont(font.deriveFont(Font.PLAIN, 15));
		issuedGuide.setBounds(100, 250, 200, 50);

		memberNum = new JLabel("1234 님의 현재 보유 쿠폰 N장");
		font = memberNum.getFont();
		memberNum.setFont(font.deriveFont(Font.PLAIN, 13));
		memberNum.setBounds(100, 320, 300, 30);

		add(issuedGuide);
		add(memberNum);

		// 처음으로 버튼 추가
		toStart = new JButton("처음으로");
		toStart.setBounds(230, 380, 100, 30);
		add(toStart);

		// actionListener 추가
		CompletePayPageEvent cppe = new CompletePayPageEvent(this);
		toStart.addActionListener(cppe);

		addWindowListener(cppe);

		setSize(500, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);

	}// CompletePayPageDesign

//	public static void main(String[] args) {
//		new CompletePayPageDesign();
//	}// main

	public JLabel getOrderNum() {
		return orderNum;
	}

	public JLabel getMemberNum() {
		return memberNum;
	}

	public JButton getToStart() {
		return toStart;
	}

}// class
