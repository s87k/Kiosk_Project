package kiosk_prj.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kiosk_prj.view.OrderDesign;
import kiosk_prj.view.StartPageDesign;

public class StartPageEvent extends WindowAdapter implements ActionListener {

	private StartPageDesign spd;
	
	public StartPageEvent(StartPageDesign spd) {
		this.spd = spd;
	}//StartPageEvent

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == spd.getStore()) {
			System.out.println("매장주문");
			OrderDesign od = new OrderDesign();
		}//end if
		
		if(e.getSource() == spd.getTakeOut()) {
			System.out.println("포장주문");
			OrderDesign od = new OrderDesign();
		}//end if
		
	}//actionPerformed

	@Override
	public void windowClosed(WindowEvent e) {
		spd.dispose();
	}
	
	

}//class
