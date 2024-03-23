package kiosk_prj.coupon.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import kiosk_prj.coupon.view.ManageCouponDesign;
import kiosk_prj.coupon.view.SearchCouponDesign;

public class SearchCouponEvent implements ChangeListener, MouseListener {
	
	private SearchCouponDesign scd;
	
	public SearchCouponEvent(SearchCouponDesign scd) {
		this.scd = scd;
	} // SearchCouponEvent

	
	@Override
	public void mousePressed(MouseEvent me) {
		if (scd.getMcd().getLastClickedButton() == ManageCouponDesign.BUTTON_MODIFY) {
			System.out.println(scd.getDtmCoupType().getValueAt(scd.getJtabCoupType().getSelectedRow(), scd.getJtabCoupType().getSelectedColumn()));
			
			
		} // end if
	} // mousePressed
	
	@Override
	public void mouseClicked(MouseEvent e) {
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
	
	@Override
	public void stateChanged(ChangeEvent ce) {
//		JTabbedPane jtbpCoupSearch = (JTabbedPane)ce.getSource();
//		System.out.println(jtbpCoupSearch.getSelectedIndex());
	} // stateChanged

} // class
