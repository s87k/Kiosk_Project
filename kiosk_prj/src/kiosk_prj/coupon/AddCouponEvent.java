package kiosk_prj.coupon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddCouponEvent extends WindowAdapter implements ActionListener {
	
	private AddCouponDesign acd;
	
	public AddCouponEvent(AddCouponDesign acd) {
		this.acd = acd;
	} // AddCouponEvent
	
	public void enablePeriodSetting(boolean flag) {
		acd.getJbtnMonth1().setEnabled(flag);
		acd.getJbtnMonth3().setEnabled(flag);
		acd.getJbtnYear1().setEnabled(flag);
		acd.getJcbPeriodDetail().setEnabled(!flag);
	} // enablePeriodDefault
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == acd.getJrbPeriodDefault()) {
			enablePeriodSetting(true);
		} // end if
		if(ae.getSource() == acd.getJrbPeriodDetail()) {
			enablePeriodSetting(false);
		} // end if
		if(ae.getSource() == acd.getJbtnGoMain()) {
			closeDialog();
			acd.getMcd().dispose();
		} // end if
		if(ae.getSource() == acd.getJbtnAddCoupon()) {
			
		} // end if
		if(ae.getSource() == acd.getJbtnCancel()) {
			acd.dispose();
		} // end if
		if(ae.getSource() == acd.getJbtnMonth1()) {
			acd.getJcbPeriodDetail().setSelectedIndex(acd.getDcbmPeriodDetail().getIndexOf("1개월"));
		} // end if
		if(ae.getSource() == acd.getJbtnMonth3()) {
			acd.getJcbPeriodDetail().setSelectedIndex(acd.getDcbmPeriodDetail().getIndexOf("3개월"));
		} // end if
		if(ae.getSource() == acd.getJbtnYear1()) {
			acd.getJcbPeriodDetail().setSelectedIndex(acd.getDcbmPeriodDetail().getIndexOf("12개월"));
		} // end if
		
	} // actionPerformed
	
	public void closeDialog() {
		acd.dispose();
	} // closeDialog

	@Override
	public void windowClosing(WindowEvent e) {
		closeDialog();
	}

} // class
