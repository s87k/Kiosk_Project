package kiosk_prj.coupon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

public class ModifyCouponEvent extends WindowAdapter implements ActionListener {

	ModifyCouponDesign mcd;
	
	public ModifyCouponEvent(ModifyCouponDesign mcd) {
		this.mcd = mcd;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

	}

} // class
