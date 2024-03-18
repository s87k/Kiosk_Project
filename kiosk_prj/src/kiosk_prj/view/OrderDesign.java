package kiosk_prj.view;

//public class OrderDesign {
//
//	public static void main(String[] args) {
//
//	}
//
//}
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class OrderDesign extends JFrame implements ActionListener {
	JPanel menuBar; // 여러 패널을 담는 패널
	JButton pay, best, coffee, nonCoffee;
	JButton espresso, americano, cafeLatte, vanillaLatte;
	JList<String> cartList;
	JLabel orderPrice;
	CardLayout cardLayout;

	public OrderDesign() {
		setTitle("Order Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// CardLayout 생성
		cardLayout = new CardLayout();
		menuBar = new JPanel(cardLayout);

		// 첫 번째 패널 생성
		JPanel panel1 = new JPanel();
		panel1.setLayout(null);

		JLabel jl1 = new JLabel("베스트 메뉴입니다.");
		jl1.setBounds(130, 50, 100, 100);
		panel1.add(jl1);

		// 버튼 생성 및 추가
		espresso = new JButton("Espresso");
		espresso.addActionListener(this);
		espresso.setBounds(10, 10, 100, 30);
		panel1.add(espresso);

		americano = new JButton("Americano");
		americano.addActionListener(this);
		americano.setBounds(120, 10, 100, 30);
		panel1.add(americano);

		cafeLatte = new JButton("Cafe Latte");
		cafeLatte.addActionListener(this);
		cafeLatte.setBounds(10, 50, 100, 30);
		panel1.add(cafeLatte);

		vanillaLatte = new JButton("Vanilla Latte");
		vanillaLatte.addActionListener(this);
		vanillaLatte.setBounds(120, 50, 100, 30);
		panel1.add(vanillaLatte);

		// JList 생성 및 추가
		cartList = new JList<String>(new String[] { "Item 1", "Item 2", "Item 3" }); // 임의의 데이터로 리스트 초기화
		cartList.setBounds(100, 400, 400, 200); // setBounds 설정
		panel1.add(cartList);

		// JLabel 생성 및 추가
		orderPrice = new JLabel("Order Price: $100"); // 임의의 가격 표시
		orderPrice.setBounds(100, 625, 200, 100); // setBounds 설정
		panel1.add(orderPrice);

		// JButton 생성 및 추가
		pay = new JButton("Pay");
		pay.addActionListener(this);
		pay.setBounds(350, 650, 150, 50); // setBounds 설정
		panel1.add(pay);

		// 두 번째 패널 생성
		JPanel panel2 = new JPanel();
		panel2.add(new JLabel("커피 메뉴입니다."));

		// 세 번째 패널 생성
		JPanel panel3 = new JPanel();
		panel3.add(new JLabel("논커피 메뉴입니다."));

		// 버튼 패널 생성
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());

		best = new JButton("Best");
		coffee = new JButton("Coffee");
		nonCoffee = new JButton("Non Coffee");

		best.addActionListener(this);
		coffee.addActionListener(this);
		nonCoffee.addActionListener(this);

		buttonPanel.add(best);
		buttonPanel.add(coffee);
		buttonPanel.add(nonCoffee);

		// 패널들을 cards 패널에 추가
		menuBar.add(panel1, "1 Best");
		menuBar.add(panel2, "2 Coffee");
		menuBar.add(panel3, "3 Non Coffee");

		// cards 패널을 프레임에 추가
		getContentPane().add(menuBar, BorderLayout.CENTER);
		getContentPane().add(buttonPanel, BorderLayout.NORTH);

		pack();
		setVisible(true);
		setBounds(100, 0, 600, 800);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == best) {
			cardLayout.show(menuBar, "1 Best"); // 다음 패널로 이동
		} else if (e.getSource() == coffee) {
			cardLayout.show(menuBar, "2 Coffee"); // 다음 패널로 이동
		} else if (e.getSource() == nonCoffee) {
			cardLayout.show(menuBar, "3 Non Coffee"); // 첫 번째 패널로 이동
		} else if(e.getSource() == pay) {
			System.out.println("결제하기");
		}
	}

	public static void main(String[] args) {
		new OrderDesign();
	}
}
