package kiosk_prj.coupon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PublishCouponEvent extends WindowAdapter implements ActionListener {

	PublishCouponDesign pcd;
	
	public PublishCouponEvent(PublishCouponDesign pcd) {
		this.pcd = pcd;
	} // PublishCouponEvent
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == pcd.getJbtnGoMain()) {
			closeDialog();
			pcd.getMcd().dispose();
		} // end if
		if(ae.getSource() == pcd.getJbtnPublish()) {
			
		} // end if
		if(ae.getSource() == pcd.getJbtnCancel()) {
			closeDialog();
		} // end if
	} // actionPerformed
	
	public void closeDialog() {
		pcd.dispose();
	} // closeDialog

	@Override
	public void windowClosing(WindowEvent e) {
		closeDialog();
	}
}
