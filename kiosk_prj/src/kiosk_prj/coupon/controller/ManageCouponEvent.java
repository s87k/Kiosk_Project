package kiosk_prj.coupon.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kiosk_prj.coupon.view.AddCouponDesign;
import kiosk_prj.coupon.view.ManageCouponDesign;
import kiosk_prj.coupon.view.ModifyCouponDesign;
import kiosk_prj.coupon.view.PublishCouponDesign;

public class ManageCouponEvent extends WindowAdapter implements ActionListener {
	
	private ManageCouponDesign mcd;
	
	public ManageCouponEvent(ManageCouponDesign mcd) {
		this.mcd = mcd;
	}
	
	public void setSCDsVisible(boolean flagSearch, boolean flagModify ) {
		mcd.getScdSearch().getJtbpCoupSearch().setVisible(flagSearch);
		mcd.getScdModify().getJtbpCoupSearch().setVisible(flagModify);
	} // setSCDsVisible

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == mcd.getJbtnAddCoupType()) {
			mcd.setLastClickedButton(ManageCouponDesign.BUTTON_ADD);   
			setSCDsVisible(false, false);
			new AddCouponDesign(mcd);
		} // end if
		if(ae.getSource() == mcd.getJbtnPublishCoup()) {
			mcd.setLastClickedButton(ManageCouponDesign.BUTTON_PUBLISH);
			setSCDsVisible(false, false);
			new PublishCouponDesign(mcd);
		} // end if
		if(ae.getSource() == mcd.getJbtnSearchCoup()) {
			mcd.setLastClickedButton(ManageCouponDesign.BUTTON_SEARCH);
			setSCDsVisible(true, false);
		} // end if
		if(ae.getSource() == mcd.getJbtnModifyCoup()) {
			mcd.setLastClickedButton(ManageCouponDesign.BUTTON_MODIFY);
			setSCDsVisible(false, true);
			// 쿠폰 조회 다이얼로그를 호출하는 건 SearchCouponEvent.java
		} // end if
		if(ae.getSource() == mcd.getJbtnGoToMain()) {
			closeDialog();
		} // end if
	} // actionPerformed

	@Override
	public void windowClosing(WindowEvent e) {
		closeDialog();
	}
	public void closeDialog() {
		mcd.dispose();
	} // closeDialog

} // class
