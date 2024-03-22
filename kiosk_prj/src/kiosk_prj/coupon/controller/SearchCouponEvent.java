package kiosk_prj.coupon.controller;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import kiosk_prj.coupon.view.SearchCouponDesign;

public class SearchCouponEvent implements ChangeListener {
	
	private SearchCouponDesign scd;
	
	public SearchCouponEvent(SearchCouponDesign scd) {
		this.scd = scd;
	} // SearchCouponEvent

	@Override
	public void stateChanged(ChangeEvent ce) {
		JTabbedPane jtbpCoupSearch = (JTabbedPane)ce.getSource();
		System.out.println(jtbpCoupSearch.getSelectedIndex());
	}

}
