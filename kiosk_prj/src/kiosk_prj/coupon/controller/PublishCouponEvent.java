package kiosk_prj.coupon.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import kiosk_prj.coupon.UpdateDefaultModelImpl;
import kiosk_prj.coupon.dao.CoupConditionTypeDAO;
import kiosk_prj.coupon.dao.CouponKindDAO;
import kiosk_prj.coupon.dao.CouponPublishDAO;
import kiosk_prj.coupon.view.PublishCouponDesign;
import kiosk_prj.coupon.vo.CoupConditionTypeVO;
import kiosk_prj.coupon.vo.CouponKindVO;
import kiosk_prj.coupon.vo.CouponPublishVO;

import static java.lang.String.valueOf;
import static java.lang.Integer.parseInt;

public class PublishCouponEvent extends WindowAdapter implements ActionListener, UpdateDefaultModelImpl {

	private PublishCouponDesign pcd;
	
	private static final int IND_COUP_TYPE = 1;
	private static final int IND_FLAG_PUBLISHABLE = 5;
	
	
	public PublishCouponEvent(PublishCouponDesign pcd) {
		this.pcd = pcd;
	} // PublishCouponEvent
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == pcd.getJbtnGoBack()) {		// 닫기 버튼
			closeDialog();
		} // end if
		if(ae.getSource() == pcd.getJbtnPublish()) {	// 발급 버튼
			publishCoupon();
		} // end if
		if(ae.getSource() == pcd.getJbtnCancel()) {		// 취소 버튼
			closeDialog();
		} // end if
	} // actionPerformed
	
	public void closeDialog() {
		pcd.getMcd().getMce().changeBtnIcon(-1);
		pcd.dispose();
	} // closeDialog

	@Override
	public void windowClosing(WindowEvent we) {
		closeDialog();
	} // windowClosing
	
	public void publishCoupon() {
		int selectedIndex = pcd.getJtabCoupKind().getSelectedRow();
		if(selectedIndex == -1) {
			JOptionPane.showMessageDialog(pcd, "발급할 쿠폰을 선택해주세요");
			return;
		} // end if
		if(!pcd.getDtmCoupKind().getValueAt(selectedIndex, IND_FLAG_PUBLISHABLE).toString().equals("O")) {
			JOptionPane.showMessageDialog(pcd, "해당 쿠폰은 발급할 수 없습니다");
			return;
		} // end if
		int couponKindNo = parseInt(pcd.getDtmCoupKind().getValueAt(selectedIndex, IND_COUP_TYPE).toString());
		
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

	@Override
	public void searchAllCoupPubConditionType() throws SQLException {
		if(pcd.getDcmPubCondition() == null) {
			return;
		} // end if
		pcd.getDcmPubCondition().removeAllElements();
		CoupConditionTypeDAO cctDAO = CoupConditionTypeDAO.getInstance();
		List<CoupConditionTypeVO> listCctVO = cctDAO.selectAllCoupConditionType();
		for (int i = 0; i < listCctVO.size(); i++) {
			pcd.getDcmPubCondition().addElement(listCctVO.get(i).getConditionTypeName().replace("{}", "N"));	
		} // end for
	} // searchAllCoupPubConditionType
	
	public void searchPublishableCouponKind() throws SQLException {
		DefaultTableModel dtm = pcd.getDtmCoupKind();
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
	
} // class
