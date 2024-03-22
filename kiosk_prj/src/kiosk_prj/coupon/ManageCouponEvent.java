package kiosk_prj.coupon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
			setSCDsVisible(false, false);
			new AddCouponDesign(mcd);
		} // end if
		if(ae.getSource() == mcd.getJbtnPublishCoup()) {
			setSCDsVisible(false, false);
			new PublishCouponDesign(mcd);
		} // end if
		if(ae.getSource() == mcd.getJbtnSearchCoup()) {
			setSCDsVisible(true, false);
		} // end if
		if(ae.getSource() == mcd.getJbtnModifyCoup()) {
			setSCDsVisible(false, true);
			// 지금은 테스트용으로 바로 ModifyCouponDesign이 뜨지만, 
			// 나중에는 CouponSearchDesign의 jtable에서 선택되면 CouponSearchEvent에서 호출하도록
			new ModifyCouponDesign(mcd);
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
