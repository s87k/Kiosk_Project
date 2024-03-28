package kiosk_prj.coupon.controller;

import static java.lang.String.valueOf;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import kiosk_prj.coupon.dao.CouponInfoViewDAO;
import kiosk_prj.coupon.dao.CouponKindDAO;
import kiosk_prj.coupon.dao.CouponPublishDAO;
import kiosk_prj.coupon.view.ModifyCouponDesign;
import kiosk_prj.coupon.view.SearchCouponDesign;
import kiosk_prj.coupon.vo.CouponAddedInfoVO;
import kiosk_prj.coupon.vo.CouponKindVO;
import kiosk_prj.coupon.vo.CouponPubInfoVO;
import kiosk_prj.coupon.vo.CouponPublishVO;
import kiosk_prj.coupon.vo.ManageButton;
import kiosk_prj.coupon.vo.StatusUse;

public class SearchCouponEvent implements MouseListener, ChangeListener{
	
	private SearchCouponDesign scd;
	
	private int indexTab;
	
	public static final int COUPON_CODE = 1;
	public static final int COUPON_ISSUE = 1;
	
	public SearchCouponEvent(SearchCouponDesign scd) {
		this.scd = scd;
	} // SearchCouponEvent

	
	@Override
	public void mousePressed(MouseEvent me) {
		if (scd.getMcd().getLastClickedButton() == ManageButton.MODIFY.ordinal()) {
			runModifyCouponDesign();
			renewAllTable();
		} // end if
	} // mousePressed
	
	public void runModifyCouponDesign() {
		CouponPublishVO cpVO = null;
		if(indexTab == COUPON_ISSUE) {
			ConvertCouponRadix ccr = ConvertCouponRadix.getInstance();
			cpVO = ccr.Radix62ToCouponPublishVO(scd.getDtmCoupIssue().getValueAt(scd.getJtabCoupIssue().getSelectedRow(), COUPON_CODE).toString());
			try {
				cpVO = CouponPublishDAO.getInstance().selectOneCoupPub(cpVO.getConditionPrice(), cpVO.getConditionTypeNo(), cpVO.getCoupKindNo());
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(scd, "선택된 쿠폰 정보를 가져오는 데 실패했습니다");
				return;
			} // end catch
		} else {
			cpVO = new CouponPublishVO(0, 0, Integer.parseInt(scd.getDtmCoupKind().getValueAt(scd.getJtabCoupKind().getSelectedRow(), COUPON_CODE).toString()));
		} // end else
		new ModifyCouponDesign(scd.getMcd(), cpVO);
	} // runModifyCouponDesign
	
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
	
	public void renewPublishableCouponKind() throws SQLException {
		DefaultTableModel dtm = scd.getDtmCoupKind();
		if(dtm == null) {
			return;
		} // end if
		
		CouponKindDAO ckDAO = CouponKindDAO.getInstance();
		List<CouponKindVO> ckList;
		ckList = ckDAO.selectAllCoupKind();
		
		CouponKindVO ckVO = null;
		dtm.setRowCount(0);
		for (int i = 0; i < ckList.size(); i++) {
			ckVO = ckList.get(i);
			dtm.addRow(new String[] {valueOf(i + 1), valueOf(ckVO.getCoupKindNo()), ckVO.getCoupKindName(), valueOf(ckVO.getExpiresPeriod()).concat("개월"), valueOf(ckVO.getDiscount()).concat("원"), ckVO.isFlagPublishable() == true ? "O" : "X"});
		} // end for
	} // searchPublishableCouponType
	
	public void renewCoupIssueTable() throws SQLException {
		DefaultTableModel dtm = scd.getDtmCoupIssue();
		if(dtm == null) {
			return;
		} // end if
		CouponInfoViewDAO civDAO = CouponInfoViewDAO.getInstance();
		List<CouponAddedInfoVO> listCaivVO = civDAO.searchAllAddedCouponView();
		Iterator<CouponAddedInfoVO> itaCaiv = listCaivVO.iterator();
		CouponAddedInfoVO caivVO = null;
		String couponCode = "";
		ConvertCouponRadix ccr = ConvertCouponRadix.getInstance();
		int num = 1;
		dtm.setRowCount(0);
		while(itaCaiv.hasNext()) {
			caivVO = itaCaiv.next();
			couponCode = ccr.CouponPublishVOToRadix62(new CouponPublishVO(caivVO.getConditionPrice(), caivVO.getConditionTypeNo(), caivVO.getCoupKindNo()));
			dtm.addRow(new Object[] {num++, couponCode, 
					caivVO.getCoupKindName(), caivVO.getDiscount(), 
					caivVO.getFlagDisable() == false ? "X": "O", 
					caivVO.getExpiresPeriod(), caivVO.getCondition()});
		} // end while
	} // renewCoupIssueTable
	
	public void renewAllTable() {
		CouponInfoViewDAO civDAO = CouponInfoViewDAO.getInstance();
		try {
			renewPublishableCouponKind();
			renewCoupIssueTable();
			renewRow2DtmCoupPub(scd.getDtmCoupPub(), civDAO.selectPubCouponView());
			renewRow2DtmCoupPub(scd.getDtmCoupPubUsable(), civDAO.selectPubCouponView(StatusUse.USABLE.getIntVal()));
			renewRow2DtmCoupPub(scd.getDtmCoupPubUnusable(), civDAO.selectPubCouponView(StatusUse.UN_USABLE.getIntVal()));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(scd, "쿠폰 조회에 실패했습니다");
			e.printStackTrace();
		} // end catch
	} // renewAllTable

	public void renewRow2DtmCoupPub(DefaultTableModel dtm, List<CouponPubInfoVO> listCpiVO) {
		Iterator<CouponPubInfoVO> itaCpi = listCpiVO.iterator();
		
		if(dtm == null) {
			return;
		} // end if
		
		dtm.setRowCount(0);
		
		CouponPubInfoVO cpiVO = null;
		Object[] arrElement = null;
		Date publishDate = null;
		Date useCoupDate = null;
		Date expireDate = null;
		
		SimpleDateFormat sdfFull = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
		SimpleDateFormat sdfYMD = new SimpleDateFormat("yy-MM-dd");
		
		int cnt = 1;
		while(itaCpi.hasNext()) {
			cpiVO = itaCpi.next();
			publishDate = cpiVO.getPublishDate();
			useCoupDate = cpiVO.getUseCoupDate();
			expireDate = cpiVO.getExpireDate();
			arrElement = new Object[] {cnt++, cpiVO.getCoupPubCode(), cpiVO.getCoupKindName(),
					cpiVO.getDiscount(), cpiVO.getMemberName(), cpiVO.getPhoneNumber(), cpiVO.getStatusUse(),
					publishDate != null ? sdfFull.format(publishDate) : "-",
					useCoupDate != null ? sdfFull.format(useCoupDate) : "-",
					expireDate != null ? sdfYMD.format(expireDate) : "-"};
			dtm.addRow(arrElement);
		} // end while
	} // renewRow2DtmCoupPub
	
	@Override
	public void stateChanged(ChangeEvent ce) {
		indexTab = scd.getJtbpCoupSearch().getSelectedIndex();
		CouponInfoViewDAO civDAO = CouponInfoViewDAO.getInstance();
		try {
			switch (indexTab) {
			case 0: 
				renewPublishableCouponKind();
				break;
			case 1:
				renewCoupIssueTable();
				break;
			case 2:
				renewRow2DtmCoupPub(scd.getDtmCoupPub(), civDAO.selectPubCouponView());
				break;
			case 3:
				renewRow2DtmCoupPub(scd.getDtmCoupPubUsable(), civDAO.selectPubCouponView(StatusUse.USABLE.getIntVal()));
				break;
			case 4:
				renewRow2DtmCoupPub(scd.getDtmCoupPubUnusable(), civDAO.selectPubCouponView(StatusUse.UN_USABLE.getIntVal()));
				break;
			} // end switch
		} catch (SQLException se) {
			JOptionPane.showMessageDialog(scd, "쿠폰 조회에 실패했습니다");
		} // end catch
	} // stateChanged
} // class
