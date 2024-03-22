package kiosk_prj.coupon.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import kiosk_prj.coupon.dao.CouponKindDAO;
import kiosk_prj.coupon.view.PublishCouponDesign;
import kiosk_prj.coupon.vo.CouponKindVO;
import static java.lang.String.valueOf;

public class PublishCouponEvent extends WindowAdapter implements ActionListener {

	private PublishCouponDesign pcd;
	
	public PublishCouponEvent(PublishCouponDesign pcd) {
		this.pcd = pcd;
	} // PublishCouponEvent
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == pcd.getJbtnGoMain()) {
			closeDialog();
			pcd.getMcd().dispose();
		} // end if
		if(ae.getSource() == pcd.getJbtnPublish()) {
			
		} // end if
		if(ae.getSource() == pcd.getJbtnCancel()) {
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

	@Override
	public void windowOpened(WindowEvent we) {
		searchAllCouponKind();
	} // windowOpened
	
	public void searchAllCouponKind() {
		CouponKindDAO ckDAO = CouponKindDAO.getInstance();
		List<CouponKindVO> ckList;
		try {
			ckList = ckDAO.selectAllCoupKind();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(pcd, "추가된 쿠폰을 목록을 조회하는 데 문제가 발생했습니다");
			e.printStackTrace();
			return;
		} // end catch
		CouponKindVO ckVO = null;
		
		int i = 1;
		Iterator<CouponKindVO> ita = ckList.iterator();
		while(ita.hasNext()) {
			ckVO = ita.next();
			pcd.getDtmCoupType().addRow(new String[] {valueOf(i++), valueOf(ckVO.getCoupKindNo()), ckVO.getCoupKindName(), valueOf(ckVO.getExpiresPeriod()).concat("개월"), valueOf(ckVO.getDiscount()).concat("원"), ckVO.isFlagPublishable() == true ? "O" : "X"});
		} // end while
	}
	
} // class
