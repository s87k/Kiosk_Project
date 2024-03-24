package kiosk_prj.coupon.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import kiosk_prj.coupon.dao.CoupConditionTypeDAO;
import kiosk_prj.coupon.dao.CouponKindDAO;
import kiosk_prj.coupon.dao.CouponPublishDAO;
import kiosk_prj.coupon.view.PublishCouponDesign;
import kiosk_prj.coupon.vo.CoupConditionPriceVO;
import kiosk_prj.coupon.vo.CoupConditionTypeVO;
import kiosk_prj.coupon.vo.CouponKindVO;
import kiosk_prj.coupon.vo.CouponPublishVO;
import kiosk_prj.coupon.vo.ResultVO;

import static java.lang.String.valueOf;
import static java.lang.Integer.parseInt;

public class PublishCouponEvent extends WindowAdapter implements ActionListener {

	private PublishCouponDesign pcd;
	
	private static final int IND_COUP_TYPE = 1;
	private static final int IND_FLAG_PUBLISHABLE = 5;
	
	
	public PublishCouponEvent(PublishCouponDesign pcd) {
		this.pcd = pcd;
	} // PublishCouponEvent
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == pcd.getJbtnGoMain()) {		// 메인으로 가기 버튼
			closeDialog();
			pcd.getMcd().dispose();
		} // end if
		if(ae.getSource() == pcd.getJbtnPublish()) {	// 발급 버튼
			publishCoupon();
		} // end if
		if(ae.getSource() == pcd.getJbtnCancel()) {		// 취소 버튼
			closeDialog();
		} // end if
	} // actionPerformed
	
	public void closeDialog() {
		pcd.dispose();
	} // closeDialog

	@Override
	public void windowClosing(WindowEvent we) {
		closeDialog();
	} // windowClosing
	
	public void publishCoupon() {
		int selectedIndex = pcd.getJtabCoupType().getSelectedRow();
		if(selectedIndex == -1) {
			JOptionPane.showMessageDialog(pcd, "발급할 쿠폰을 선택해주세요");
			return;
		} // end if
		if(!pcd.getDtmCoupType().getValueAt(selectedIndex, IND_FLAG_PUBLISHABLE).toString().equals("O")) {
			JOptionPane.showMessageDialog(pcd, "해당 쿠폰은 발급할 수 없습니다");
			return;
		} // end if
		int couponKindNo = parseInt(pcd.getDtmCoupType().getValueAt(selectedIndex, IND_COUP_TYPE).toString());
		
		String strConditionPrice = pcd.getJtfPubConditonVal().getText().trim();
		if(strConditionPrice == null || strConditionPrice.equals("")) {
			JOptionPane.showMessageDialog(pcd, "발급 조건에 올바른 값을 입력해주세요");
			return;
		} // end if
		int conditionPrice = parseInt(strConditionPrice);
		int conditionTypeNo = pcd.getJcbPubCondition().getSelectedIndex() + 1;
		
		CouponPublishVO cpVO = new CouponPublishVO(conditionPrice, conditionTypeNo, couponKindNo);
		CouponPublishDAO cpDAO = CouponPublishDAO.getInstance();
		try {
			cpDAO.insertCoupPub(cpVO);
		} catch (SQLException se) {
			se.printStackTrace();
			if(se.getErrorCode() == 1) {
				JOptionPane.showMessageDialog(pcd, "이미 동일한 조건으로 발급된 쿠폰이 있어 발급을 취소합니다", "notice", JOptionPane.WARNING_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(pcd, "쿠폰 발급에 실패했습니다", "notice", JOptionPane.WARNING_MESSAGE);
			} // end else
			return;
		} // end catch
		
		JOptionPane.showMessageDialog(pcd, "쿠폰 발급에 성공했습니다");
		
	} // publishCoupon

	public void searchAllCoupPubConditionType() throws SQLException {
		CoupConditionTypeDAO cctDAO = CoupConditionTypeDAO.getInstance();
		List<CoupConditionTypeVO> listCctVO = cctDAO.selectAllCoupConditionType();
		for (int i = 0; i < listCctVO.size(); i++) {
			pcd.getDcmPubCondition().addElement(listCctVO.get(i).getConditionTypeName());	
		} // end for
	} // searchAllCoupPubConditionType
	
	public void searchPublishableCouponType() throws SQLException {
		CouponKindDAO ckDAO = CouponKindDAO.getInstance();
		List<CouponKindVO> ckList;
		ckList = ckDAO.selectAllCoupKind();
		
		CouponKindVO ckVO = null;
		for (int i = 0; i < ckList.size(); i++) {
			ckVO = ckList.get(i);
			pcd.getDtmCoupType().addRow(new String[] {valueOf(i), valueOf(ckVO.getCoupKindNo()), ckVO.getCoupKindName(), valueOf(ckVO.getExpiresPeriod()).concat("개월"), valueOf(ckVO.getDiscount()).concat("원"), ckVO.isFlagPublishable() == true ? "O" : "X"});
		} // end for
	} // searchPublishableCouponType
	
} // class
