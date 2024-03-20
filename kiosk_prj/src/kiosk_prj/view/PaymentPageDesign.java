package kiosk_prj.view;

import javax.swing.*;

public class PaymentPageDesign extends JFrame {

	public PaymentPageDesign() {

		// 수동 배치
		setLayout(null);

		// 총 상품 금액 표시 레이블
		JLabel jlorderPrice = new JLabel("총 상품금액");
		jlorderPrice.setBounds(10, 10, 100, 30);

		JLabel orderPrice = new JLabel("일단 1200원");
		orderPrice.setBounds(130, 10, 100, 30);

		// 쿠폰 조회 & 안내 메시지
		JButton btnCheckCoupon = new JButton("쿠폰 조회");
		btnCheckCoupon.setBounds(10, 60, 100, 30);

		JLabel lblCheckCouponGuide = new JLabel("<html>전화번호로 보유쿠폰을 조회합니다.<br>회원가입도 쿠폰조회에서!</html>");
		lblCheckCouponGuide.setBounds(10, 100, 300, 100);

		// 주문 메뉴 리스트
		String[] column = { "상품", "수량" };
		Object[][] data = { { "아메리카노", 1 }, { "가데이터 2", 2 }, { "가데이터 3", 3 }, };

		JTable orderMenuList = new JTable(data, column);
		JScrollPane scrollPane = new JScrollPane(orderMenuList);
		scrollPane.setBounds(10, 230, 300, 250);

		// 총 결제 금액 표시 레이블
		JLabel jlTotalPrice = new JLabel("총 결제 금액");
		jlTotalPrice.setBounds(10, 500, 100, 30);

		JLabel totalPrice = new JLabel("일단 1200원");
		totalPrice.setBounds(120, 500, 100, 30);

		// 결제 방법 선택 레이블 및 버튼
		JLabel jlPaymentMethod = new JLabel("결제 방법을 선택해주세요.");
		jlPaymentMethod.setBounds(10, 530, 200, 30);

		JButton creditCard = new JButton("신용카드");
		creditCard.setBounds(10, 580, 100, 30);

		JButton cash = new JButton("현금결제");
		cash.setBounds(10, 630, 100, 30);

		// 패널에 컴포넌트 추가
		add(jlorderPrice);
		add(orderPrice);
		add(btnCheckCoupon);
		add(lblCheckCouponGuide);
		add(scrollPane);
		add(jlTotalPrice);
		add(totalPrice);
		add(jlPaymentMethod);
		add(creditCard);
		add(cash);

		// Set size for the payment panel
		setVisible(true);
		setResizable(false);
		setSize(600, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}// PaymentPageDesign

	public static void main(String[] args) {
		new PaymentPageDesign();
	}// main
}// class
