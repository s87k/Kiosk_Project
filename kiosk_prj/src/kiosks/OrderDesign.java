package kiosks;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import kiosks.OrderEvent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderDesign extends JFrame {
	private JPanel menueBarPanel, cardPanel, cartPanel;
	private JButton best, coffee, nonCoffee, tea, smoothie, pay, cancel;
	private JPanel panel1, panel2, panel3, panel4, panel0;
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
		coffee = new JButton("Coffee");
		nonCoffee = new JButton("Non Coffee");
		tea = new JButton("Tea");
		smoothie = new JButton("Smoothie");
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
		panel0 = new JPanel();
		panel0.setLayout(new GridLayout(0, 2));
		JScrollPane scrollPane0 = new JScrollPane(panel0); 

		// 커피 패널
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(0, 2));
		JScrollPane scrollPane1 = new JScrollPane(panel1); 
		
		// 논커피 패널
		panel2 = new JPanel();
		panel2.setLayout(new GridLayout(0, 2));
		JScrollPane scrollPane2 = new JScrollPane(panel2); 

		// 티 패널
		panel3 = new JPanel();
		panel3.setLayout(new GridLayout(0, 2));
		JScrollPane scrollPane3 = new JScrollPane(panel3); 

		// 스무디 패널
		panel4 = new JPanel();
		panel4.setLayout(new GridLayout(0, 2));
		JScrollPane scrollPane4 = new JScrollPane(panel4); 

		// card패널에 카드 추가
		cardPanel.add(scrollPane0, "0");
		cardPanel.add(scrollPane1, "1");
		cardPanel.add(scrollPane2, "2");
		cardPanel.add(scrollPane3, "3");
		cardPanel.add(scrollPane4, "4");

		add(cardPanel, BorderLayout.CENTER);

		// 세 번째 JPanel 장바구니 & 취소 & 결제 (하단)
		cartPanel = new JPanel();
		cartPanel.setLayout(new BorderLayout());

		// 장바구니 DefaultTableModel 추가
		String[] cartTable = {"메뉴명","옵션","가격"};
		dtmCartList = new DefaultTableModel(cartTable,0) {
			// 셀 수정 불가
			public boolean isCellEditable(int row, int colum) {
				return false;
			}
		};
		cartList = new JTable(dtmCartList);
		// 열 이동 불가
		cartList.getTableHeader().setReorderingAllowed(false);

		// JTable 장바구니 테두리
		JScrollPane scrollPane = new JScrollPane(cartList);
		scrollPane.setPreferredSize(new Dimension(600, 200));
		scrollPane.setBorder(BorderFactory.createTitledBorder("장바구니")); 
		cartPanel.add(scrollPane, BorderLayout.CENTER);

		// 패널추가해서 jlOrderPrice, jtfOrderPrice, cancel, pay 일렬배치
		JPanel buttonsPanel = new JPanel();
		jlOrderPrice = new JLabel("주문 금액 : ");
		buttonsPanel.add(jlOrderPrice);

		jtfOrderPrice = new JTextField(10);
		jtfOrderPrice.setEditable(false);
		buttonsPanel.add(jtfOrderPrice);

		cancel = new JButton("주문 삭제");
		buttonsPanel.add(cancel);

		pay = new JButton("결제");
		buttonsPanel.add(pay);

		cartPanel.add(buttonsPanel, BorderLayout.SOUTH);

		add(cartPanel, BorderLayout.SOUTH);

		// actionListener 추가
		OrderEvent oe = new OrderEvent(this);

		// 카드 actionListener 추가
		best.addActionListener(oe);
		coffee.addActionListener(oe);
		nonCoffee.addActionListener(oe);
		tea.addActionListener(oe);
		smoothie.addActionListener(oe);

		
		// 장바구니 actionListener 추가
		cartList.addMouseListener(oe);
		dtmCartList.addTableModelListener(oe);

		// 결제 & 취소 버튼 actionListener 추가
		pay.addActionListener(oe);
		cancel.addActionListener(oe);

		addWindowListener(oe);

		getContentPane().setBackground(new Color(0xECEDFA));
		setVisible(true);
		setResizable(false);
		setSize(600, 800);
		setLocationRelativeTo(null);
	}// OrderDesign

//	public static void main(String[] args) {
//		new OrderDesign();
//	}// main

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

	public JPanel getPanel0() {
		return panel0;
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

}// class
