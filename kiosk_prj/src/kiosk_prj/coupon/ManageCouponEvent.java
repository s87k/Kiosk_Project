package kiosk_prj.coupon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

public class ManageCouponEvent extends WindowAdapter implements ActionListener {
	
	private ManageCouponDesign mcd;
	
	public ManageCouponEvent(ManageCouponDesign mcd) {
		this.mcd = mcd;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == mcd.getJbtnAddCoupType()) {
			new AddCouponDesign(mcd);
		} // end if
	} // actionPerformed

} // class
