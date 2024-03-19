package kiosk_prj.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JTable;

import kiosk_prj.view.OrderStatusDesign;

public class OrderStatusEvent extends WindowAdapter implements ActionListener, MouseListener {
	
	private OrderStatusDesign osd;
	private JButton jbMenuComplete, jbExit;
	private JTable jtOrderStatus;
	
	public OrderStatusEvent(OrderStatusDesign osd) {
		this.osd = osd;
		
		jbExit = osd.getJbExit();
		jtOrderStatus = osd.getJtOrderStatus();
		
		
	}//OrderStatusEvent
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == jbExit) {
			System.out.println("asdfasdfasdf");
		}//end if
	}//actionPerformed
	
	@Override
	public void windowClosing(WindowEvent e) {
		osd.dispose();
	}//windowClosing

	@Override
	public void mouseClicked(MouseEvent e) {
		
		
		
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


}
