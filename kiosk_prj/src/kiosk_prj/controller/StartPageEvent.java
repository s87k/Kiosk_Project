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
			spd.dispose();
			System.out.println("매장주문");
			OrderDesign.main(new String[]{});
		}//end if
		
		if(e.getSource() == spd.getTakeOut()) {
			spd.dispose();
			System.out.println("포장주문");
			OrderDesign.main(new String[]{});
		}//end if
		
	}//actionPerformed

	@Override
	public void windowClosed(WindowEvent e) {
		spd.dispose();
	}
	
	

}//class
