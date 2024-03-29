package kiosks;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import kiosks.OrderDetailEvent;

@SuppressWarnings("serial")
public class OrderDetailDesgin extends JDialog{
	private OrderDesign od;
	
	private JLabel menuName, menuPrice, menuImg, jlCount;
	private JButton storeCup, personalCup, hot, ice, menuPlus, menuMinus, addMenu;
	private JRadioButton shotOption;
	private Font font;
	
	public OrderDetailDesgin(OrderDesign od, String title) {
		super(od, "옵션 선택");
		setLayout(null);
		
		// 메뉴 정보
		// 메뉴 이미지 넣기
		ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("americano.jpg"));
		
		Image image = icon.getImage();
		Image scaledImage = image.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		menuImg = new JLabel(scaledIcon);
		menuImg.setPreferredSize(new Dimension(120,120));
		
		
		menuName = new JLabel("");
		menuPrice = new JLabel("");
		JLabel won = new JLabel("원");
		
		font = menuName.getFont();
		menuName.setFont(font.deriveFont(Font.BOLD, 20));
		font = menuPrice.getFont();
		menuPrice.setFont(font.deriveFont(Font.PLAIN, 20));
		font = won.getFont();
		won.setFont(font.deriveFont(Font.BOLD, 20));
		
		add(menuImg);
		add(menuName);
		add(menuPrice);
		add(won);
		
		menuImg.setBounds(30, 30, 120, 120);
		menuName.setBounds(180, 55, 150, 50);
		menuPrice.setBounds(340, 55, 50, 50);
		won.setBounds(390, 55, 30, 50);
		
		
		// 옵션 선택
		// 컵 옵션
		JLabel jlCup = new JLabel("컵*");
		font = jlCup.getFont();
		jlCup.setFont(font.deriveFont(Font.PLAIN, 20));
		jlCup.setForeground(Color.RED);
		
		storeCup = new JButton("매장컵");
		personalCup = new JButton("텀블러");
		
		add(jlCup);
		add(storeCup);
		add(personalCup);
		
		jlCup.setBounds(50, 180, 30, 30);
		storeCup.setBounds(50, 220, 100, 50);
		personalCup.setBounds(180, 220, 100, 50);
		
		// 온도 옵션
		JLabel jlTemp = new JLabel("온도*");
		font = jlTemp.getFont();
		jlTemp.setFont(font.deriveFont(Font.PLAIN, 20));
		jlTemp.setForeground(Color.RED);
		
		hot = new JButton("HOT");
		ice = new JButton("ICE");
		
		add(jlTemp);
		add(hot);
		add(ice);
		
		jlTemp.setBounds(50, 300, 50, 30);
		hot.setBounds(50, 340, 100, 50);
		ice.setBounds(180, 340, 100, 50);
		
		// 샷추가 옵션
		JLabel jlShot = new JLabel("샷추가");
		font = jlShot.getFont();
		jlShot.setFont(font.deriveFont(Font.PLAIN, 20));
		
		shotOption = new JRadioButton("1샷 추가(+500원)");
		
		add(jlShot);
		add(shotOption);
		
		jlShot.setBounds(50, 420, 70, 30);
		shotOption.setBounds(50, 455, 150, 20);
		
		// 수량 옵션
		jlCount = new JLabel("1");
		jlCount.setHorizontalAlignment(SwingConstants.CENTER);
		jlCount.setBorder(BorderFactory.createTitledBorder(""));
		menuMinus  = new JButton("-");
		menuPlus = new JButton("+");
		
		// 주문 담기 버튼
		addMenu = new JButton("");
		
		add(menuMinus);
		add(jlCount);
		add(menuPlus);
		add(addMenu);
		
		menuMinus.setBounds(0, 511, 100, 50);
		jlCount.setBounds(100, 511, 100, 50);
		menuPlus.setBounds(200, 511, 100, 50);
		addMenu.setBounds(300, 511, 200, 50);
		
		//actionListener 추가
		OrderDetailEvent ode = new OrderDetailEvent(this, od);
		storeCup.addActionListener(ode);
		personalCup.addActionListener(ode);
		hot.addActionListener(ode);
		ice.addActionListener(ode);
		shotOption.addActionListener(ode);
		menuMinus.addActionListener(ode);
		menuPlus.addActionListener(ode);
		addMenu.addActionListener(ode);
		
		addWindowListener(ode);
		
		getContentPane().setBackground(new Color(0xECEDFA));
		setSize(500,600);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
		
	}//orderDetail

	public JLabel getMenuName() {
		return menuName;
	}
	
	public JLabel getMenuPrice() {
		return menuPrice;
	}

	public JLabel getMenuImg() {
		return menuImg;
	}

	public JLabel getJlCount() {
		return jlCount;
	}

	public JButton getStoreCup() {
		return storeCup;
	}

	public JButton getPersonalCup() {
		return personalCup;
	}

	public JButton getHot() {
		return hot;
	}

	public JButton getIce() {
		return ice;
	}

	public JButton getMenuPlus() {
		return menuPlus;
	}

	public JButton getMenuMinus() {
		return menuMinus;
	}

	public JButton getAddMenu() {
		return addMenu;
	}

	public JRadioButton getShotOption() {
		return shotOption;
	}
	
//	public static void main(String[] args) {
//		new OrderDetailDesgin();
//	}//main

}//class
