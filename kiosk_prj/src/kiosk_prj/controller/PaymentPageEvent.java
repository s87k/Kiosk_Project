package kiosk_prj.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kiosk_prj.view.PaymentPageDesign;

public class PaymentPageEvent extends WindowAdapter implements ActionListener, MouseListener{

	private PaymentPageDesign ppd;
	
	private JTable orderMenuList;
	private DefaultTableModel dtmOrderMemuList;
	
	public PaymentPageEvent(PaymentPageDesign ppd) {
		this.ppd = ppd;
		
		orderMenuList = ppd.getOrderMenuList();
		dtmOrderMemuList = ppd.getDtmOrderMemuList();
	}//PaymentPageEvent

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ppd.getBtnCheckCoupon()) {
			System.out.println("쿠폰조회");
		}
		if(e.getSource() == ppd.getCreditCard()) {
			System.out.println("신용카드 결제");
		}
		if(e.getSource() == ppd.getCash()) {
			System.out.println("현금 결제");
		}
	}//actionPerformed
	
	@Override
	public void windowClosed(WindowEvent e) {
		ppd.dispose();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

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
