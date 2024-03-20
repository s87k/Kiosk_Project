package kiosk_prj.coupon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

public class AddCouponEvent extends WindowAdapter implements ActionListener {
	
	AddCouponDesign acd;
	
	public AddCouponEvent(AddCouponDesign acd) {
		this.acd = acd;
	} // AddCouponEvent

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
