package kiosk_prj.coupon.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import kiosk_prj.coupon.view.ManageCouponDesign;
import kiosk_prj.coupon.view.ModifyCouponDesign;
import kiosk_prj.coupon.view.SearchCouponDesign;
import kiosk_prj.coupon.vo.CouponPublishVO;

public class SearchCouponEvent implements MouseListener {
	
	private SearchCouponDesign scd;
	
	public static final int COUPON_CODE = 1;
	
	public SearchCouponEvent(SearchCouponDesign scd) {
		this.scd = scd;
	} // SearchCouponEvent

	
	@Override
	public void mousePressed(MouseEvent me) {
		if (scd.getMcd().getLastClickedButton() == ManageCouponDesign.BUTTON_MODIFY) {
			ConvertCouponRadix ccr = ConvertCouponRadix.getInstance();
			CouponPublishVO cpVO = ccr.Radix62ToCouponPublishVO(scd.getDtmCoupType().getValueAt(scd.getJtabCoupType().getSelectedRow(), COUPON_CODE).toString());
			new ModifyCouponDesign(scd.getMcd(), cpVO);
		} // end if
	} // mousePressed
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
} // class
