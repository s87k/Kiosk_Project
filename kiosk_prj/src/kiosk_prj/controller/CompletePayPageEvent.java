package kiosk_prj.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kiosk_prj.view.CompletePayPageDesign;
import kiosk_prj.view.StartPageDesign;

public class CompletePayPageEvent extends WindowAdapter implements ActionListener{

	private CompletePayPageDesign cppd;
	
	public CompletePayPageEvent(CompletePayPageDesign cppd) {
		this.cppd = cppd;
	}//CompletePayPageEvent
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cppd.getToStart()) {
			System.out.println("처음으로");
			new StartPageDesign();
			cppd.dispose();
		}
	}//actionPerformed
	
	@Override
	public void windowClosed(WindowEvent e) {
		cppd.dispose();
	}

}//class
