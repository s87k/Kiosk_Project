package kiosk_prj.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;

import kiosk_prj.view.AdminMainPageDesign;
import kiosk_prj.view.MemberShipDesign;

public class AdminMainPageEvent extends WindowAdapter implements ActionListener, MouseListener {
	
	private AdminMainPageDesign amod;
	
	public AdminMainPageEvent(AdminMainPageDesign amod) {
		this.amod = amod;
		
		
	}//AdminMainPageEvent

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == amod.getJbUserManagement()) {
			new MemberShipDesign(amod, null);
		}
	}//actionPerformed

	@Override
	public void mouseClicked(MouseEvent e) {
		//jtable용?
	}//mouseClicked

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}//class
