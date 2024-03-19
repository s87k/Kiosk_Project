package kiosk_prj.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderDesign extends JFrame implements ActionListener {
	private JPanel menueBarPanel, cardPanel, cartPanel;
	private JButton best, coffee, nonCoffee, tea, smoothie, pay, cancel;
	private JPanel panel1, panel2, panel3, panel4, panel5;
	private JTable cartList;
	private JLabel jlOrderPrice;
	private JTextField jtfOrderPrice;
	private CardLayout cardLayout;
	private DefaultTableModel dtmCartList;

	public OrderDesign() {
		setTitle("Order Page");
		setSize(600, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2, 2));
		panel1.add(new JButton("베스트 1"));
		panel1.add(new JButton("베스트 2"));
		panel1.add(new JButton("베스트 3"));

		panel2 = new JPanel();
		panel2.setLayout(new GridLayout(2, 2));
		panel2.add(new JButton("coffee-1"));
		panel2.add(new JButton("coffee-2"));
		panel2.add(new JButton("coffee-3"));
		panel2.add(new JButton("coffee-4"));

		panel3 = new JPanel();
		panel3.setLayout(new GridLayout(2, 2));
		panel3.add(new JButton("nonCoffee-1"));
		panel3.add(new JButton("nonCoffee-2"));
		panel3.add(new JButton("nonCoffee-3"));
		panel3.add(new JButton("nonCoffee-4"));

		panel4 = new JPanel();
		panel4.setLayout(new GridLayout(2, 2));
		panel4.add(new JButton("tea-1"));
		panel4.add(new JButton("tea-2"));
		panel4.add(new JButton("tea-3"));
		panel4.add(new JButton("tea-4"));

		panel5 = new JPanel();
		panel5.setLayout(new GridLayout(2, 2));
		panel5.add(new JButton("smoothie-1"));
		panel5.add(new JButton("smoothie-2"));
		panel5.add(new JButton("smoothie-3"));
		panel5.add(new JButton("smoothie-4"));

		cardPanel.add(panel1, "panel1");
		cardPanel.add(panel2, "panel2");
		cardPanel.add(panel3, "panel3");
		cardPanel.add(panel4, "panel4");
		cardPanel.add(panel5, "panel5");

		add(cardPanel, BorderLayout.CENTER);

		// 세 번째 JPanel (하단)
		cartPanel = new JPanel();
		cartPanel.setLayout(new BorderLayout());

//		cartList = new JTable(5, 1);
		
		//DefaultTableModel 추가
		dtmCartList = new DefaultTableModel(new Object[]{"Item"}, 0);
        cartList = new JTable(dtmCartList);
		
		//JTable 장바구니 테두리
//      JScrollPane scrollPane = new JScrollPane(cartList);
//      scrollPane.setBorder(BorderFactory.createTitledBorder("장바구니")); // "장바구니" 테두리 추가
//      cartPanel.add(scrollPane, BorderLayout.CENTER);
        
		cartPanel.add(cartList, BorderLayout.CENTER);

		// jlOrderPrice, jtfOrderPrice, cancel, pay 일렬배치
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

	}//OrderDesign

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
	}//actionPerformed

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new OrderDesign().setVisible(true);
			}
		});
	}//main

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
	
	
}//class
