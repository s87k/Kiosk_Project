package kiosk_prj.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kiosk_prj.view.OrderDesign;

public class OrderEvent extends WindowAdapter implements ActionListener{

	private OrderDesign od;
	
	public OrderEvent(OrderDesign od) {
		this.od = od;
	}//OrderEvent
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == od.getEspresso()) {
			System.out.println("에스프레소");
		} 
		if (ae.getSource() == od.getAmericano()) {
			System.out.println("아메리카노");
		}
		if (ae.getSource() == od.getCafeLatte()) {
			System.out.println("카페라떼");
		} 
		if (ae.getSource() == od.getVanillaLatte()) {
			System.out.println("바닐라라떼");
		}
		if (ae.getSource() == od.getCancel()) {
			System.out.println("취소");
		} 
		if (ae.getSource() == od.getPay()) {
			System.out.println("결제하기");
		}
	}//actionPerformed
	
	@Override
	public void windowClosed(WindowEvent e) {
		od.dispose();
	}

}//class
