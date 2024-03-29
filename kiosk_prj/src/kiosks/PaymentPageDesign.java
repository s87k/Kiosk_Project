package kiosks;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import kiosks.PaymentPageEvent;

@SuppressWarnings("serial")
public class PaymentPageDesign extends JFrame {
	private JLabel orderPrice, lblCheckCouponGuide, totalPrice, usedCoup;
	private JButton btnCheckCoupon, creditCard, cash;
	private JTable orderMenuList;
	private DefaultTableModel dtmOrderMemuList;
	private Font font;
	private String phoneNum;
	public static String coupPubCode;
	public static int amount;
	public static String strPhoneNum;

	public PaymentPageDesign() {
		// 수동 배치
		setLayout(null);

		// 총 상품 금액 표시 레이블
		JLabel jlorderPrice = new JLabel("총 주문금액");
		font = jlorderPrice.getFont();
		jlorderPrice.setFont(font.deriveFont(Font.PLAIN, 25));
		jlorderPrice.setBounds(150, 50, 150, 30);

		orderPrice = new JLabel("");
		font = orderPrice.getFont();
		orderPrice.setFont(font.deriveFont(Font.PLAIN, 25));
		orderPrice.setBounds(340, 50, 100, 30);

		JLabel won = new JLabel("원");
		font = won.getFont();
		won.setFont(font.deriveFont(Font.PLAIN, 25));
		won.setBounds(440, 50, 50, 30);

		// 쿠폰 조회 & 안내 메시지
		btnCheckCoupon = new JButton("쿠폰 조회");
		btnCheckCoupon.setBounds(180, 110, 100, 30);
		
		usedCoup = new JLabel("");
		usedCoup.setBounds(300, 110, 100, 30);

		lblCheckCouponGuide = new JLabel("<html>*전화번호로 보유쿠폰을 조회합니다.<br>*회원가입도 쿠폰조회에서!</html>");
		font = lblCheckCouponGuide.getFont();
		lblCheckCouponGuide.setFont(font.deriveFont(Font.PLAIN, 11));
		lblCheckCouponGuide.setBounds(180, 150, 200, 25);

		// 장바구니
		String[] cartTable = { "메뉴명", "옵션", "가격" };
		dtmOrderMemuList = new DefaultTableModel(cartTable, 0) {
			// 셀 수정 불가
			public boolean isCellEditable(int row, int colum) {
				return false;
			}
		};
		orderMenuList = new JTable(dtmOrderMemuList);
		// 열 이동 불가
		orderMenuList.getTableHeader().setReorderingAllowed(false);
		
		// JTable 장바구니 테두리
		JScrollPane jspOrderMenuList = new JScrollPane(orderMenuList);
		jspOrderMenuList.setPreferredSize(new Dimension(450, 250));
		jspOrderMenuList.setBorder(BorderFactory.createTitledBorder("장바구니"));
		jspOrderMenuList.setBounds(70, 200, 450, 250);

		// 총 결제 금액 표시 레이블
		JLabel jlTotalPrice = new JLabel("총 결제 금액");
		font = jlTotalPrice.getFont();
		jlTotalPrice.setFont(font.deriveFont(Font.PLAIN, 25));
		jlTotalPrice.setBounds(150, 470, 150, 30);

		totalPrice = new JLabel("");
		font = totalPrice.getFont();
		totalPrice.setFont(font.deriveFont(Font.PLAIN, 25));
		totalPrice.setBounds(340, 470, 100, 30);

		JLabel totalWon = new JLabel("원");
		font = totalWon.getFont();
		totalWon.setFont(font.deriveFont(Font.PLAIN, 25));
		totalWon.setBounds(440, 470, 50, 30);

		// 결제 방법 선택 레이블 및 버튼
		JLabel jlPaymentMethod = new JLabel("결제 방법을 선택해주세요.");
		font = jlPaymentMethod.getFont();
		jlPaymentMethod.setFont(font.deriveFont(Font.PLAIN, 14));
		jlPaymentMethod.setBounds(213, 510, 200, 30);

		creditCard = new JButton("신용카드");
		creditCard.setBounds(120, 550, 150, 70);

		cash = new JButton("현금결제");
		cash.setBounds(300, 550, 150, 70);

		// 패널에 컴포넌트 추가
		add(jlorderPrice);
		add(orderPrice);
		add(won);
		add(btnCheckCoupon);
		add(usedCoup);
		add(lblCheckCouponGuide);
		add(jspOrderMenuList);
		add(jlTotalPrice);
		add(totalPrice);
		add(totalWon);
		add(jlPaymentMethod);
		add(creditCard);
		add(cash);

		// actionListener 추가
		PaymentPageEvent ppe = new PaymentPageEvent(this);
		btnCheckCoupon.addActionListener(ppe);
		creditCard.addActionListener(ppe);
		cash.addActionListener(ppe);

		jspOrderMenuList.addMouseListener(ppe);

		addWindowListener(ppe);

		getContentPane().setBackground(new Color(0xECEDFA));
		setSize(600, 800);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}// PaymentPageDesign

	public void setTotalPrice(JLabel totalPrice) {
		this.totalPrice = totalPrice;
	}

	public JLabel getOrderPrice() {
		return orderPrice;
	}

	public JLabel getLblCheckCouponGuide() {
		return lblCheckCouponGuide;
	}

	public JLabel getTotalPrice() {
		return totalPrice;
	}

	public JButton getBtnCheckCoupon() {
		return btnCheckCoupon;
	}

	public JButton getCreditCard() {
		return creditCard;
	}

	public JButton getCash() {
		return cash;
	}

	public JTable getOrderMenuList() {
		return orderMenuList;
	}

	public DefaultTableModel getDtmOrderMemuList() {
		return dtmOrderMemuList;
	}

	public JLabel getUsedCoup() {
		return usedCoup;
	}

	public void setUsedCoup(JLabel usedCoup) {
		this.usedCoup = usedCoup;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	

//	public static void main(String[] args) {
//		new PaymentPageDesign();
//	}// main

}// class
