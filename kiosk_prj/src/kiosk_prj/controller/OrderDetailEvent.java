package kiosk_prj.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.table.DefaultTableModel;

import kiosk_prj.view.OrderDesign;
import kiosk_prj.view.OrderDetailDesgin;

public class OrderDetailEvent extends WindowAdapter implements ActionListener{

	private OrderDetailDesgin odd;
	private OrderDesign od;
	private Color originalColor;
	
	public OrderDetailEvent(OrderDetailDesgin odd, OrderDesign od) {
		this.odd = odd;
		this.od = od;
		originalColor = odd.getStoreCup().getBackground();
	}//OrderDetailEvent
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == odd.getStoreCup()) {
			System.out.println("매장컵");
			odd.getStoreCup().setBackground(Color.GRAY);
			odd.getPersonalCup().setBackground(originalColor);
		}
		if(e.getSource() == odd.getPersonalCup()) {
			System.out.println("개인컵");
			odd.getPersonalCup().setBackground(Color.GRAY);
			odd.getStoreCup().setBackground(originalColor);
		}
		if(e.getSource() == odd.getHot()) {
			System.out.println("핫");
			odd.getHot().setBackground(Color.GRAY);
			odd.getIce().setBackground(originalColor);
		}
		if(e.getSource() == odd.getIce()) {
			System.out.println("아이스");
			odd.getIce().setBackground(Color.GRAY);
			odd.getHot().setBackground(originalColor);
		}
		if(e.getSource() == odd.getMenuMinus()) {
			System.out.println("수량빼기");
			int count = Integer.parseInt(odd.getJlCount().getText());
			if(count > 0) {
				count--;
				odd.getJlCount().setText(Integer.toString(count));
			}//end if
			int price = Integer.parseInt(odd.getMenuPrice().getText());
			int totalPrice = count*price;
			odd.getAddMenu().setText(totalPrice+"원 담기");
		}
		if(e.getSource() == odd.getMenuPlus()) {
			System.out.println("수량더하기");
			int count = Integer.parseInt(odd.getJlCount().getText());
			count++;
			odd.getJlCount().setText(Integer.toString(count));
			int price = Integer.parseInt(odd.getMenuPrice().getText());
			int totalPrice = count*price;
			odd.getAddMenu().setText(totalPrice+"원 담기");
		}
		if(e.getSource() == odd.getAddMenu()) {
			System.out.println("주문 담기");
			String menuName = odd.getMenuName().getText();
	        int menuPrice = Integer.parseInt(odd.getMenuPrice().getText());
	        int count = Integer.parseInt(odd.getJlCount().getText());
	        int totalPrice = count * menuPrice;
	        
	        Object[] rowData = {menuName, totalPrice};
	        od.getDtmCartList().addRow(rowData);
			odd.dispose();
		}
	}

	@Override
	public void windowClosed(WindowEvent e) {
		odd.dispose();
	}

}
