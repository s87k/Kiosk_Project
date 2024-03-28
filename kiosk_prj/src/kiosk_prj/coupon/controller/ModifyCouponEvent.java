package kiosk_prj.coupon.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import kiosk_prj.coupon.UpdateDefaultModelImpl;
import kiosk_prj.coupon.dao.CoupConditionTypeDAO;
import kiosk_prj.coupon.dao.CouponKindDAO;
import kiosk_prj.coupon.dao.CouponPublishDAO;
import kiosk_prj.coupon.view.ModifyCouponDesign;
import kiosk_prj.coupon.vo.CoupConditionTypeVO;
import kiosk_prj.coupon.vo.CouponKindVO;
import kiosk_prj.coupon.vo.CouponPublishVO;

public class ModifyCouponEvent extends WindowAdapter implements ActionListener, UpdateDefaultModelImpl {

	private ModifyCouponDesign mcd;
	
	public ModifyCouponEvent(ModifyCouponDesign mcd) {
		this.mcd = mcd;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == mcd.getJbtnCancel()) {
			closeDialog();
		} // end if 
		if(ae.getSource() == mcd.getJbtnOk()) {
			try {
				modifyCoupon();
			} catch (SQLException se) {
				se.printStackTrace();
				if(se.getErrorCode() == 2292) {
					JOptionPane.showMessageDialog(mcd, "이미 회원에게 발급된 쿠폰은 조건 수정이 불가능합니다\n추가 발급을 막으려면 비활성화를 해주세요");
				} else {
					JOptionPane.showMessageDialog(mcd, "쿠폰 수정에 실패했습니다");
				} // end else
				return;
			} // end catch
		} // end if 
		if(ae.getSource() == mcd.getJbtnDeleteCoup()) {
			deleteCoupon();
		} // end if 
	} // actionPerformed
	
	public void deleteCoupon() {
		if(mcd.getCpVO().getConditionTypeNo() != ModifyCouponDesign.PUBLISH_NOT) {
			return;
		}
		int coupKindNo = mcd.getCpVO().getCoupKindNo();
		String msg = "발급된 쿠폰은 삭제할 수 없습니다.\n이 쿠폰을 더이상 자동 발급 조건에 등록하지 못하도록 발급가능을 '아니오'로 변경해주세요";
		
		CouponPublishDAO cpDAO = CouponPublishDAO.getInstance();
		try {
			if(cpDAO.isCoupKindPublished(coupKindNo)) {
				JOptionPane.showMessageDialog(mcd, msg);
				return;
			} // end if
		} catch (SQLException se) {
			JOptionPane.showMessageDialog(mcd, "삭제 조건 조회에 실패했습니다");
			return;
		} // end catch
		
		switch(JOptionPane.showConfirmDialog(mcd, "정말 삭제하시겠습니까?")) {
		case JOptionPane.OK_OPTION:
			CouponKindDAO ckDAO = CouponKindDAO.getInstance();
			int cnt = 0;
			try {
				cnt = ckDAO.deleteCoupKind(coupKindNo);
				if(cnt == 1) {
					msg = "쿠폰 삭제되었습니다";
				} else {
					msg = "쿠폰 삭제에 실패했습니다";
				} // end else
			} catch (SQLException se) {
				msg = "쿠폰 삭제에 실패했습니다";
			} // end catch
			JOptionPane.showMessageDialog(mcd, msg);
		} // end case
	} // deleteCoupon
	
	public void modifyCoupon() throws SQLException {
		CouponKindDAO ckDAO = CouponKindDAO.getInstance();
		CouponPublishVO cpVOOld = mcd.getCpVO();
		CouponKindVO ckVO = ckDAO.selectOneCoupKind(cpVOOld.getCoupKindNo());
		
		String coupKindName = mcd.getJtfCouponKindName().getText().trim();
		boolean flagPublishable = mcd.getJrbPublishableOk().isSelected();
		boolean flagChanged = false;
		
		int cnt = 0;
		
		if( ! ckVO.getCoupKindName().equals(coupKindName) || ckVO.isFlagPublishable() != flagPublishable) {
			ckVO.setCoupKindName(coupKindName);
			ckVO.setFlagPublishable(flagPublishable);
			
			cnt = ckDAO.updateCoupKind(ckVO);
			if(cnt != 1) {
				JOptionPane.showMessageDialog(mcd, "쿠폰 정보 수정에 실패했습니다");
				return;
			} // if
			flagChanged = true;
		} // end if
		
		if(cpVOOld.getConditionTypeNo() != ModifyCouponDesign.PUBLISH_NOT) {
			String strConditionPrice =  mcd.getJtfPubConditonVal().getText().trim();
			if(strConditionPrice == null || strConditionPrice.equals("")) {
				JOptionPane.showMessageDialog(mcd, "값은 꼭 입력되어야합니다");
				return;	
			} // end if
			int conditionPrice = Integer.parseInt(strConditionPrice);
			int conditionTypeNo = mcd.getJcbPubCondition().getSelectedIndex() + 1;
			boolean flagDisable = mcd.getJrbPublishableOk().isSelected() == true ? true : false;
			
			if(cpVOOld.getConditionPrice() != conditionPrice || cpVOOld.getCoupKindNo() != conditionTypeNo || cpVOOld.isFlagDisable() != flagDisable) {
				CouponPublishVO cpVONew = new CouponPublishVO();
				cpVONew.setConditionPrice(conditionPrice);
				cpVONew.setConditionTypeNo(conditionTypeNo);
				cpVONew.setFlagDisable(flagDisable);
				
				CouponPublishDAO cpDAO = CouponPublishDAO.getInstance();
				cnt = cpDAO.updateCoupPub(cpVOOld, cpVONew);
				if(cnt != 1) {
					JOptionPane.showMessageDialog(mcd, "쿠폰 정보 수정에 실패했습니다");
					return;
				} // end if
				flagChanged = true;
			} // end if
		} // end if
		
		if( ! flagChanged) {
			JOptionPane.showMessageDialog(mcd, "변경 사항이 없습니다");
		} else {
			JOptionPane.showMessageDialog(mcd, "쿠폰 정보 수정에 성공했습니다");
		} // end else
		
	} // modifyCoupon

	public void closeDialog() {
		mcd.dispose();
	} // closeDialog
	
	@Override
	public void windowClosing(WindowEvent e) {
		closeDialog();
	}

	@Override
	public void searchAllCoupPubConditionType() throws SQLException {
		CoupConditionTypeDAO cctDAO = CoupConditionTypeDAO.getInstance();
		List<CoupConditionTypeVO> listCctVO = cctDAO.selectAllCoupConditionType();
		for (int i = 0; i < listCctVO.size(); i++) {
			mcd.getDcmPubCondition().addElement(listCctVO.get(i).getConditionTypeName().replace("{}", "N"));	
		} // end for
	} // searchAllCoupPubConditionType

} // class
