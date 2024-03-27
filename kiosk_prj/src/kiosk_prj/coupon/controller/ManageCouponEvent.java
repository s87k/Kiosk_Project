package kiosk_prj.coupon.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import kiosk_prj.coupon.UpdateIcon;
import kiosk_prj.coupon.view.AddCouponDesign;
import kiosk_prj.coupon.view.ManageCouponDesign;
import kiosk_prj.coupon.view.ModifyCouponDesign;
import kiosk_prj.coupon.view.PublishCouponDesign;
import kiosk_prj.coupon.vo.ManageButton;

public class ManageCouponEvent extends WindowAdapter implements ActionListener, UpdateIcon {
	
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
		if(ae.getSource() == mcd.getArrJbtnFunc()[ManageButton.ADD.ordinal()]) {
			mcd.setLastClickedButton(ManageButton.ADD.ordinal());   
			setSCDsVisible(false, false);
			changeBtnIcon(ManageButton.ADD.ordinal());
			new AddCouponDesign(mcd);
		} // end if
		if(ae.getSource() == mcd.getArrJbtnFunc()[ManageButton.PUBLISH.ordinal()]) {
			mcd.setLastClickedButton(ManageButton.PUBLISH.ordinal());
			setSCDsVisible(false, false);
			changeBtnIcon(ManageButton.PUBLISH.ordinal());
			new PublishCouponDesign(mcd);
		} // end if
		if(ae.getSource() == mcd.getArrJbtnFunc()[ManageButton.SEARCH.ordinal()]) {
			mcd.setLastClickedButton(ManageButton.SEARCH.ordinal());
			setSCDsVisible(true, false);
			changeBtnIcon(ManageButton.SEARCH.ordinal());
		} // end if
		if(ae.getSource() == mcd.getArrJbtnFunc()[ManageButton.MODIFY.ordinal()]) {
			mcd.setLastClickedButton(ManageButton.MODIFY.ordinal());
			setSCDsVisible(false, true);
			changeBtnIcon(ManageButton.MODIFY.ordinal());
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
	
	@Override
	public void changeBtnIcon(int indexBtn) {
		ImageIcon[] arrIiBtn = mcd.getArrIiBtn();
		ImageIcon[] arrIiBtnClick = mcd.getArrIiBtnClick();
		JButton[] arrJbtn = mcd.getArrJbtnFunc();
		
		for(int i = 0; i < arrIiBtn.length; i++) {
			if(i == indexBtn) {
				arrJbtn[i].setIcon(arrIiBtnClick[i]);
			} else {
				arrJbtn[i].setIcon(arrIiBtn[i]);
			}
		} // end for
	} // changeBtnIcon

} // class
