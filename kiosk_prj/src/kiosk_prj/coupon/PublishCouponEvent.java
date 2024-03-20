package kiosk_prj.coupon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

public class PublishCouponEvent extends WindowAdapter implements ActionListener {

	PublishCouponDesign pcd;
	
	public PublishCouponEvent(PublishCouponDesign pcd) {
		this.pcd = pcd;
	} // PublishCouponEvent
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub

	}

}
