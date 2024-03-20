package kiosk_prj.coupon;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SearchCouponEvent implements ChangeListener {
	
	SearchCouponDesign scd;
	
	public SearchCouponEvent(SearchCouponDesign scd) {
		this.scd = scd;
	} // SearchCouponEvent

	@Override
	public void stateChanged(ChangeEvent ce) {
		JTabbedPane jtbpCoupSearch = (JTabbedPane)ce.getSource();
		System.out.println(jtbpCoupSearch.getSelectedIndex());
	}

}
