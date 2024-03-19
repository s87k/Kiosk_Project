package kiosk_prj.view;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class OrderDetailDesgin extends JFrame{

	private JLabel menuInfo, menuImg, jlCup, jlTemp, jlShot, jlCount;
	private JButton storeCup, personalCup, hot, ice, menuPlus, menuMinus, addMenu;
	private int count;
	private JRadioButton shotOption;
	
	public OrderDetailDesgin() {
		setTitle("Order Detail Page");
		
		// 메뉴 정보 옵션
		JPanel eachMenuInfo = new JPanel();
		menuImg = new JLabel("커피이미지");
		menuInfo = new JLabel("커피정보 | 가격");
		
		eachMenuInfo.add(menuImg);
		eachMenuInfo.add(menuInfo);
		
		// 컵 옵션
		JPanel option = new JPanel();
		jlCup = new JLabel("컵");
		storeCup = new JButton("매장컵");
		personalCup = new JButton("개인컵");
		
		// 온도 옵션
		jlTemp = new JLabel("온도");
		hot = new JButton("HOT");
		ice = new JButton("ICE");
		
		// 샷추가 옵션
		jlShot = new JLabel("샷추가");
		shotOption = new JRadioButton("1샷 추가(+500원)");
		
		option.add(jlCup);
		option.add(storeCup);
		option.add(personalCup);
		option.add(jlTemp);
		option.add(hot);
		option.add(ice);
		option.add(jlShot);
		option.add(shotOption);
		
		// 수량 옵션
		JPanel countOption = new JPanel();
		int count = 1;
		jlCount = new JLabel();
		menuMinus  = new JButton("-");
		menuPlus = new JButton("+");
		
		// 주문 담기 버튼
		addMenu = new JButton("??원 담기");
		
		countOption.add(menuMinus);
		countOption.add(jlCount);
		countOption.add(menuPlus);
		countOption.add(addMenu);
		
		
		
		setVisible(true);
		setBounds(100, 0, 600, 800);
	}//OrderDetailDesgin
	
	public static void main(String[] args) {
		new OrderDetailDesgin();
	}//main

}//class
