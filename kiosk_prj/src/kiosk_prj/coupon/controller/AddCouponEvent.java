package kiosk_prj.coupon.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kiosk_prj.coupon.dao.CouponKindDAO;
import kiosk_prj.coupon.view.AddCouponDesign;
import kiosk_prj.coupon.vo.CouponKindVO;

import static java.lang.Integer.parseInt;

public class AddCouponEvent extends WindowAdapter implements ActionListener {
	
	private AddCouponDesign acd;
	private int periodMonth;
	
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
		if(ae.getSource() == acd.getJrbPeriodDefault()) {	// 이용기간 - 기본 설정 JRadioButton
			enablePeriodSetting(true);
		} // end if
		if(ae.getSource() == acd.getJrbPeriodDetail()) {	// 이용기간 - 상세 설정 JRadioButton
			enablePeriodSetting(false);
		} // end if
		if(ae.getSource() == acd.getJbtnGoMain()) {			// 메인으로 가기 버튼
			closeDialog();
			acd.getMcd().dispose();
		} // end if
		if(ae.getSource() == acd.getJbtnAddCoupon()) {		// 등록 버튼
			addCouponKind();
		} // end if
		if(ae.getSource() == acd.getJbtnCancel()) {			// 취소 버튼
			acd.dispose();
		} // end if
		if(ae.getSource() == acd.getJbtnMonth1()) {			// 이용기간 - 기본 설정 - 1개월 JButton
			acd.getJcbPeriodDetail().setSelectedIndex(acd.getDcbmPeriodDetail().getIndexOf("1개월"));
			periodMonth = 1;
		} // end if
		if(ae.getSource() == acd.getJbtnMonth3()) {			// 이용기간 - 기본 설정 - 3개월 JButton
			acd.getJcbPeriodDetail().setSelectedIndex(acd.getDcbmPeriodDetail().getIndexOf("3개월"));
			periodMonth = 3;
		} // end if
		if(ae.getSource() == acd.getJbtnYear1()) {			// 이용기간 - 기본 설정 - 1년 JButton
			acd.getJcbPeriodDetail().setSelectedIndex(acd.getDcbmPeriodDetail().getIndexOf("12개월"));
			periodMonth = 12;
		} // end if
		
	} // actionPerformed
	
	public void addCouponKind() {
		CouponKindVO ckVO = null;
		
		String coupKindName = acd.getJtfCouponKindName().getText().trim();
		
		if(coupKindName.equals("") || coupKindName == null) {
			JOptionPane.showMessageDialog(acd, "쿠폰 이름을 입력해주세요");
			return;
		} // end if
		if(coupKindName.length() > 33) {
			JOptionPane.showMessageDialog(acd, "쿠폰 이름은 33자 이내로 작성해주세요");
			return;
		} // end if
		
		int discount = 0;
		try {
			discount = parseInt(acd.getJtfDiscount().getText().trim());
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(acd, "할인 금액을 입력해주세요");
			return;
		} // end catch
		
		int period = 0;
		if(acd.getJrbPeriodDefault().isSelected()) {
			period = periodMonth;
		} else {
			period = parseInt(acd.getDcbmPeriodDetail().getElementAt(acd.getJcbPeriodDetail().getSelectedIndex()).replace("개월", ""));
		} // end else
		if(period == 0) {
			JOptionPane.showMessageDialog(acd, "이용 기간을 입력해주세요");
			return;
		} // end if
		boolean flagPublishable = acd.getJrbPublishableOk().isSelected() == true ? true : false;
		
		
		ckVO = new CouponKindVO("김병년", coupKindName, discount, period, flagPublishable);
		CouponKindDAO ckDAO = CouponKindDAO.getInstance();
		try {
			ckDAO.insertCoupKind(ckVO);
		} catch (SQLException se) {
			JOptionPane.showMessageDialog(acd, "쿠폰 등록에 실패했습니다");
			se.printStackTrace();
			return;
		} // end catch
		JOptionPane.showMessageDialog(acd, "쿠폰이 정상적으로 등록되었습니다");
		
	} // publishCoupon
	
	public void closeDialog() {
		acd.dispose();
	} // closeDialog

	@Override
	public void windowClosing(WindowEvent e) {
		closeDialog();
	}

} // class
