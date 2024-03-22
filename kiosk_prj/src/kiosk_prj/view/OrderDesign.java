package kiosk_prj.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import kiosk_prj.controller.OrderEvent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderDesign extends JFrame implements ActionListener {
	private JPanel menueBarPanel, cardPanel, cartPanel;
	private JButton best, coffee, nonCoffee, tea, smoothie, pay, cancel;
	private JButton bestAmericano, bestCafeLatte, bestVanillaLatte;
	private JButton americano, cafeLatte, vanillaLatte, espresso;
	private JButton chocolateLatte, toffeeNutLatte, greenTeaLatte, dalgonaLatte;
	private JButton peachTea, lemonTea, grapefruitTea, camomileTea;
	private JButton yogurtSmoothie, milkShake, StrowberrySmoothie, javachipFrappe;
	private JPanel panel1, panel2, panel3, panel4, panel5;
	private JTable cartList;
	private DefaultTableModel dtmCartList;
	private JLabel jlOrderPrice;
	private JTextField jtfOrderPrice;
	private CardLayout cardLayout;

	public OrderDesign() {
		setTitle("Order Page");
		setLayout(new BorderLayout());

		// 첫 번째 JPanel (상단)
		menueBarPanel = new JPanel();
		best = new JButton("Best");
		best.addActionListener(this);
		coffee = new JButton("Coffee");
		coffee.addActionListener(this);
		nonCoffee = new JButton("Non Coffee");
		nonCoffee.addActionListener(this);
		tea = new JButton("Tea");
		tea.addActionListener(this);
		smoothie = new JButton("Smoothie");
		smoothie.addActionListener(this);
		menueBarPanel.add(best);
		menueBarPanel.add(coffee);
		menueBarPanel.add(nonCoffee);
		menueBarPanel.add(tea);
		menueBarPanel.add(smoothie);
		add(menueBarPanel, BorderLayout.NORTH);

		// 두 번째 JPanel (중앙, CardLayout)
		cardPanel = new JPanel();
		cardLayout = new CardLayout();
		cardPanel.setLayout(cardLayout);

		// 베스트 패널
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2, 2));
		bestAmericano = new JButton("아메리카노");
		panel1.add(bestAmericano);

		bestCafeLatte = new JButton("카페라떼");
		panel1.add(bestCafeLatte);

		bestVanillaLatte = new JButton("바닐라 라떼");
		panel1.add(bestVanillaLatte);

		// 커피 패널
		panel2 = new JPanel();
		panel2.setLayout(new GridLayout(2, 2));
		espresso = new JButton("에스프레소");
		panel2.add(espresso);

		americano = new JButton("아메리카노");
		panel2.add(americano);

		cafeLatte = new JButton("카페라떼");
		panel2.add(cafeLatte);

		vanillaLatte = new JButton("바닐라 라떼");
		panel2.add(vanillaLatte);

		// 논커피 패널
		panel3 = new JPanel();
		panel3.setLayout(new GridLayout(2, 2));
		chocolateLatte = new JButton("초코 라떼");
		panel3.add(chocolateLatte);

		toffeeNutLatte = new JButton("토피넛 라떼");
		panel3.add(toffeeNutLatte);

		greenTeaLatte = new JButton("녹차 라떼");
		panel3.add(greenTeaLatte);

		dalgonaLatte = new JButton("달고나 라떼");
		panel3.add(dalgonaLatte);

		// 티 패널
		panel4 = new JPanel();
		panel4.setLayout(new GridLayout(2, 2));
		peachTea = new JButton("복숭아 티");
		panel4.add(peachTea);

		lemonTea = new JButton("레몬 티");
		panel4.add(lemonTea);

		grapefruitTea = new JButton("자몽 티");
		panel4.add(grapefruitTea);

		camomileTea = new JButton("캐모마일 티");
		panel4.add(camomileTea);

		// 스무디 패널
		panel5 = new JPanel();
		panel5.setLayout(new GridLayout(2, 2));
		yogurtSmoothie = new JButton("요거트 스무디");
		panel5.add(yogurtSmoothie);

		StrowberrySmoothie = new JButton("딸기 스무디");
		panel5.add(StrowberrySmoothie);

		milkShake = new JButton("밀크쉐이크");
		panel5.add(milkShake);

		javachipFrappe = new JButton("자바칩 프라페");
		panel5.add(javachipFrappe);

		// card패널에 카드 추가
		cardPanel.add(panel1, "panel1");
		cardPanel.add(panel2, "panel2");
		cardPanel.add(panel3, "panel3");
		cardPanel.add(panel4, "panel4");
		cardPanel.add(panel5, "panel5");

		add(cardPanel, BorderLayout.CENTER);

		// 세 번째 JPanel 장바구니 & 취소 & 결제 (하단)
		cartPanel = new JPanel();
		cartPanel.setLayout(new BorderLayout());

		// JTable 1열 5행 일단 넣어봄..
//		cartList = new JTable(5, 1);

		// 장바구니 DefaultTableModel 추가
		dtmCartList = new DefaultTableModel();
		cartList = new JTable(dtmCartList);

		// JTable 장바구니 테두리
//      JScrollPane scrollPane = new JScrollPane(cartList);
//      scrollPane.setBorder(BorderFactory.createTitledBorder("장바구니")); // "장바구니" 테두리 추가
//      cartPanel.add(scrollPane, BorderLayout.CENTER);

		cartPanel.add(cartList, BorderLayout.CENTER);

		// 패널추가해서 jlOrderPrice, jtfOrderPrice, cancel, pay 일렬배치
		JPanel buttonsPanel = new JPanel();
		jlOrderPrice = new JLabel("주문 금액 : ");
		buttonsPanel.add(jlOrderPrice);

		jtfOrderPrice = new JTextField(10);
		jtfOrderPrice.setEditable(false);
		buttonsPanel.add(jtfOrderPrice);

		cancel = new JButton("취소");
		buttonsPanel.add(cancel);

		pay = new JButton("결제");
		buttonsPanel.add(pay);

		cartPanel.add(buttonsPanel, BorderLayout.SOUTH);

		add(cartPanel, BorderLayout.SOUTH);

		// actionListener 추가
		OrderEvent oe = new OrderEvent(this);

		// 메뉴 actionListener 추가
		bestAmericano.addActionListener(oe);
		bestCafeLatte.addActionListener(oe);
		bestVanillaLatte.addActionListener(oe);

		espresso.addActionListener(oe);
		americano.addActionListener(oe);
		cafeLatte.addActionListener(oe);
		vanillaLatte.addActionListener(oe);

		chocolateLatte.addActionListener(oe);
		toffeeNutLatte.addActionListener(oe);
		greenTeaLatte.addActionListener(oe);
		dalgonaLatte.addActionListener(oe);

		peachTea.addActionListener(oe);
		lemonTea.addActionListener(oe);
		grapefruitTea.addActionListener(oe);
		camomileTea.addActionListener(oe);

		yogurtSmoothie.addActionListener(oe);
		milkShake.addActionListener(oe);
		StrowberrySmoothie.addActionListener(oe);
		javachipFrappe.addActionListener(oe);

		pay.addActionListener(oe);
		cancel.addActionListener(oe);

		cartList.addMouseListener(oe);

		addWindowListener(oe);

		setVisible(true);
		setResizable(false);
		setSize(600, 800);
		setLocationRelativeTo(null);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}// order

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == best) {
			cardLayout.show(cardPanel, "panel1");
		} else if (e.getSource() == coffee) {
			cardLayout.show(cardPanel, "panel2");
		} else if (e.getSource() == nonCoffee) {
			cardLayout.show(cardPanel, "panel3");
		} else if (e.getSource() == tea) {
			cardLayout.show(cardPanel, "panel4");
		} else if (e.getSource() == smoothie) {
			cardLayout.show(cardPanel, "panel5");
		}
	}// actionPerformed

	public static void main(String[] args) {
		new OrderDesign();
	}// main

	public JPanel getMenueBarPanel() {
		return menueBarPanel;
	}

	public JPanel getCardPanel() {
		return cardPanel;
	}

	public JPanel getCartPanel() {
		return cartPanel;
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

	public JButton getPay() {
		return pay;
	}

	public JButton getCancel() {
		return cancel;
	}

	public JPanel getPanel1() {
		return panel1;
	}

	public JPanel getPanel2() {
		return panel2;
	}

	public JPanel getPanel3() {
		return panel3;
	}

	public JPanel getPanel4() {
		return panel4;
	}

	public JPanel getPanel5() {
		return panel5;
	}

	public JTable getCartList() {
		return cartList;
	}

	public JLabel getJlOrderPrice() {
		return jlOrderPrice;
	}

	public JTextField getJtfOrderPrice() {
		return jtfOrderPrice;
	}

	public CardLayout getCardLayout() {
		return cardLayout;
	}

	public DefaultTableModel getDtmCartList() {
		return dtmCartList;
	}

	public JButton getBestAmericano() {
		return bestAmericano;
	}

	public JButton getBestCafeLatte() {
		return bestCafeLatte;
	}

	public JButton getBestVanillaLatte() {
		return bestVanillaLatte;
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

	public JButton getChocolateLatte() {
		return chocolateLatte;
	}

	public JButton getToffeeNutLatte() {
		return toffeeNutLatte;
	}

	public JButton getGreenTeaLatte() {
		return greenTeaLatte;
	}

	public JButton getDalgonaLatte() {
		return dalgonaLatte;
	}

	public JButton getPeachTea() {
		return peachTea;
	}

	public JButton getLemonTea() {
		return lemonTea;
	}

	public JButton getGrapefruitTea() {
		return grapefruitTea;
	}

	public JButton getCamomileTea() {
		return camomileTea;
	}

	public JButton getYogurtSmoothie() {
		return yogurtSmoothie;
	}

	public JButton getMilkShake() {
		return milkShake;
	}

	public JButton getStrowberrySmoothie() {
		return StrowberrySmoothie;
	}

	public JButton getJavachipFrappe() {
		return javachipFrappe;
	}

}// class
