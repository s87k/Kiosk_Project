package kiosk_prj.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import kiosk_prj.controller.OrderEvent;

@SuppressWarnings("serial")
public class OrderDesign extends JFrame implements ActionListener {
	
	private JPanel menu;
	private JButton pay, cancel, best, coffee, nonCoffee, tea, smoothie;
	private JButton espresso, americano, cafeLatte, vanillaLatte;
	private JList<String> cartList;
	private JLabel orderPrice;
	private CardLayout cardLayout;

	private DefaultListModel<String> dlmCartList;

	public OrderDesign() {
		setTitle("Order Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// CardLayout 생성
		cardLayout = new CardLayout();
		menu = new JPanel(cardLayout);

		// 1 번째 패널 생성
		JPanel panel1 = new JPanel();
		panel1.setLayout(null);

		JLabel jl1 = new JLabel("베스트 메뉴입니다.");
		jl1.setBounds(240, 3, 150, 30);
		panel1.add(jl1);

		// 베스트 메뉴 버튼 생성 및 추가
		espresso = new JButton("Espresso");
		espresso.setBounds(130, 40, 150, 150);
		panel1.add(espresso);

		americano = new JButton("Americano");
		americano.setBounds(310, 40, 150, 150);
		panel1.add(americano);

		cafeLatte = new JButton("Cafe Latte");
		cafeLatte.setBounds(130, 220, 150, 150);
		panel1.add(cafeLatte);

		vanillaLatte = new JButton("Vanilla Latte");
		vanillaLatte.setBounds(310, 220, 150, 150);
		panel1.add(vanillaLatte);
/////////////////////////////////////////////////////////////////////
		// 2 번째 패널 생성
		JPanel panel2 = new JPanel();
		panel2.setLayout(null);

		JLabel jl2 = new JLabel("커피 메뉴입니다.");
		jl2.setBounds(240, 3, 150, 30);
		panel2.add(jl2);

		// 커피 메뉴 버튼 생성 및 추가
		espresso = new JButton("Espresso");
		espresso.setBounds(130, 40, 150, 150);
		panel2.add(espresso);

		americano = new JButton("Americano");
		americano.setBounds(310, 40, 150, 150);
		panel2.add(americano);

		cafeLatte = new JButton("Cafe Latte");
		cafeLatte.setBounds(130, 220, 150, 150);
		panel2.add(cafeLatte);

		vanillaLatte = new JButton("Vanilla Latte");
		vanillaLatte.setBounds(310, 220, 150, 150);
		panel2.add(vanillaLatte);

		// 장바구니 JList 생성 및 추가
		dlmCartList = new DefaultListModel<String>();
		cartList = new JList<String>(dlmCartList);
		cartList.setBounds(75, 410, 450, 200);
		panel2.add(cartList);

		// 주문금액 JLabel 생성 및 추가
		orderPrice = new JLabel("Order Price: 1200원");
		orderPrice.setBounds(85, 615, 200, 50);
		panel2.add(orderPrice);

		// 결제 JButton 생성 및 추가
		pay = new JButton("결제");
		pay.setBounds(425, 630, 100, 50); // 285
		panel2.add(pay);

		// 취소 JButton 생성 및 추가
		cancel = new JButton("취소");
		cancel.setBounds(315, 630, 100, 50);
		panel2.add(cancel);
/////////////////////////////////////////////////////////////////////
		// 3 번째 패널 생성
		JPanel panel3 = new JPanel();
		panel3.add(new JLabel("논커피 메뉴입니다."));

		// 4 번째 패널 생성
		JPanel panel4 = new JPanel();
		panel4.add(new JLabel("티 메뉴입니다."));

		// 5 번째 패널 생성
		JPanel panel5 = new JPanel();
		panel5.add(new JLabel("스무디 메뉴입니다."));

		// 장바구니 JList 생성 및 추가
		dlmCartList = new DefaultListModel<String>();
		cartList = new JList<String>(dlmCartList);
		cartList.setBounds(75, 410, 450, 200);
		panel1.add(cartList);

		// 주문금액 JLabel 생성 및 추가
		orderPrice = new JLabel("Order Price: 1200원");
		orderPrice.setBounds(85, 615, 200, 50);
		panel1.add(orderPrice);

		// 결제 JButton 생성 및 추가
		pay = new JButton("결제");
		pay.setBounds(425, 630, 100, 50); // 285
		panel1.add(pay);

		// 취소 JButton 생성 및 추가
		cancel = new JButton("취소");
		cancel.setBounds(315, 630, 100, 50);
		panel1.add(cancel);

		// 메뉴바 버튼 패널 생성
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());

		best = new JButton("Best");
		coffee = new JButton("Coffee");
		nonCoffee = new JButton("Non Coffee");
		tea = new JButton("Tea");
		smoothie = new JButton("Smoothie");

		buttonPanel.add(best);
		buttonPanel.add(coffee);
		buttonPanel.add(nonCoffee);
		buttonPanel.add(tea);
		buttonPanel.add(smoothie);

		// 패널들을 cards 패널에 추가
		menu.add(panel1, "1 Best");
		menu.add(panel2, "2 Coffee");
		menu.add(panel3, "3 Non Coffee");
		menu.add(panel4, "4 Tea");
		menu.add(panel5, "5 Smoothie");

		// cards 패널을 프레임에 추가
		getContentPane().add(buttonPanel, BorderLayout.NORTH);
		getContentPane().add(menu, BorderLayout.CENTER);

		// 패널 addActionListener 추가
		best.addActionListener(this);
		coffee.addActionListener(this);
		nonCoffee.addActionListener(this);
		tea.addActionListener(this);
		smoothie.addActionListener(this);

		espresso.addActionListener(this);
		// OrderEvent에 addActionListener 추가
		OrderEvent oe = new OrderEvent(this);

		americano.addActionListener(oe);
		cafeLatte.addActionListener(oe);
		vanillaLatte.addActionListener(oe);

		pay.addActionListener(oe);
		cancel.addActionListener(oe);

		setVisible(true);
		setBounds(100, 0, 600, 800);
	}// OrderDesign

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == best) {
			cardLayout.show(menu, "1 Best");
		} else if (e.getSource() == coffee) {
			cardLayout.show(menu, "2 Coffee");
		} else if (e.getSource() == nonCoffee) {
			cardLayout.show(menu, "3 Non Coffee");
		} else if (e.getSource() == tea) {
			cardLayout.show(menu, "4 Tea");
		} else if (e.getSource() == smoothie) {
			cardLayout.show(menu, "5 Smoothie");
		} 
	}// actionPerformed

	public static void main(String[] args) {
		new OrderDesign();
	}// main

	public JPanel getMenu() {
		return menu;
	}

	public JButton getPay() {
		return pay;
	}

	public JButton getCancel() {
		return cancel;
	}

	public JButton getBest() {
		return best;
	}

	public JButton getCoffee() {
		return coffee;
	}

	public JButton getNonCoffee() {
		return nonCoffee;
	}

	public JButton getTea() {
		return tea;
	}

	public JButton getSmoothie() {
		return smoothie;
	}

	public JButton getEspresso() {
		return espresso;
	}

	public JButton getAmericano() {
		return americano;
	}

	public JButton getCafeLatte() {
		return cafeLatte;
	}

	public JButton getVanillaLatte() {
		return vanillaLatte;
	}

	public JList<String> getCartList() {
		return cartList;
	}

	public DefaultListModel<String> getDlmCartList() {
		return dlmCartList;
	}

	public JLabel getOrderPrice() {
		return orderPrice;
	}

	public CardLayout getCardLayout() {
		return cardLayout;
	}

}// class
