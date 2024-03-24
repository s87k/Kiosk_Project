package kiosk_prj.coupon.controller;

import static java.lang.String.valueOf;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import kiosk_prj.coupon.dao.CouponKindDAO;
import kiosk_prj.coupon.view.ManageCouponDesign;
import kiosk_prj.coupon.view.ModifyCouponDesign;
import kiosk_prj.coupon.view.SearchCouponDesign;
import kiosk_prj.coupon.vo.CouponKindVO;
import kiosk_prj.coupon.vo.CouponPublishVO;

public class SearchCouponEvent implements MouseListener, ChangeListener{
	
	private SearchCouponDesign scd;
	
	private int indexTab;
	
	public static final int COUPON_CODE = 1;
	
	public SearchCouponEvent(SearchCouponDesign scd) {
		this.scd = scd;
	} // SearchCouponEvent

	
	@Override
	public void mousePressed(MouseEvent me) {
		if (scd.getMcd().getLastClickedButton() == ManageCouponDesign.BUTTON_MODIFY) {
			if(indexTab == 1) {
				ConvertCouponRadix ccr = ConvertCouponRadix.getInstance();
				CouponPublishVO cpVO = ccr.Radix62ToCouponPublishVO(scd.getDtmCoupIssue().getValueAt(scd.getJtabCoupIssue().getSelectedRow(), COUPON_CODE).toString());
				new ModifyCouponDesign(scd.getMcd(), cpVO);
			}
			
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
	
	public void searchPublishableCouponType() throws SQLException {
		CouponKindDAO ckDAO = CouponKindDAO.getInstance();
		List<CouponKindVO> ckList;
		ckList = ckDAO.selectAllCoupKind();
		
		CouponKindVO ckVO = null;
		for (int i = 0; i < ckList.size(); i++) {
			ckVO = ckList.get(i);
			scd.getDtmCoupKind().addRow(new String[] {valueOf(i + 1), valueOf(ckVO.getCoupKindNo()), ckVO.getCoupKindName(), valueOf(ckVO.getExpiresPeriod()).concat("개월"), valueOf(ckVO.getDiscount()).concat("원"), ckVO.isFlagPublishable() == true ? "O" : "X"});
		} // end for
	} // searchPublishableCouponType


	@Override
	public void stateChanged(ChangeEvent ce) {
		indexTab = scd.getJtbpCoupSearch().getSelectedIndex();
	} // stateChanged
} // class
