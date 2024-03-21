package kiosk_prj.coupon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ModifyCouponEvent extends WindowAdapter implements ActionListener {

	private ModifyCouponDesign mcd;
	
	public ModifyCouponEvent(ModifyCouponDesign mcd) {
		this.mcd = mcd;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == mcd.getJbtnCancel()) {
			closeDialog();
		} // end if 
		if(ae.getSource() == mcd.getJbtnOk()) {
			
		} // end if 
		if(ae.getSource() == mcd.getJbtnDeleteCoup()) {
			
		} // end if 
	}

	public void closeDialog() {
		mcd.dispose();
	} // closeDialog
	
	@Override
	public void windowClosing(WindowEvent e) {
		closeDialog();
	}

} // class
