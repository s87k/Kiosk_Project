package kiosks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import kiosk_prj.adminMain.AdminMainPageDesign;
import kiosk_prj.coupon.dao.CouponHeldDAO;
import kiosk_prj.coupon.dao.CouponPublishDAO;
import kiosk_prj.coupon.vo.CouponAutoPubVO;
import kiosks.CompletePayPageDesign;
import kiosks.StartPageDesign;
import kiosks.dao.OrderMenuDAO;
import kiosks.dao.SelectCouponDAO;
import kiosks.vo.DetailedOrderVO;
import kiosks.vo.SelectCouponVO;

public class CompletePayPageEvent extends WindowAdapter implements ActionListener{

	private CompletePayPageDesign cppd;
	private String phoneNum;

	public CompletePayPageEvent(CompletePayPageDesign cppd) {
		this.cppd = cppd;
		phoneNum = cppd.getPhoneNum();
		if(PaymentPageDesign.strPhoneNum != null && !PaymentPageDesign.strPhoneNum.equals("")) {
			//발급된 쿠폰 보여주기
			newPublishCoup();
			//결제완료 후 보유 쿠폰 개수
			heldCoup(phoneNum);
		} // end if
			
		
		PaymentPageDesign.amount = 0;
		PaymentPageDesign.coupPubCode = "";
		PaymentPageDesign.strPhoneNum = "";
	}//CompletePayPageEvent
	
	/**
	 * 결제 후 발급된 쿠폰 이름 보여주기
	 */
	private void newPublishCoup() {
		CouponPublishDAO cpDAO = CouponPublishDAO.getInstance();
		CouponHeldDAO chDAO  = CouponHeldDAO.getInstance();
		try {
			List<CouponAutoPubVO> listCapVO = cpDAO.selectShouldPublishCoup(PaymentPageDesign.strPhoneNum, PaymentPageDesign.amount);
			for (CouponAutoPubVO capVO : listCapVO) {
			}
			chDAO.insertCoupHeld(listCapVO);
			if(listCapVO != null && listCapVO.size() != 0) {
				StringBuilder sb = new StringBuilder();
				CouponAutoPubVO capVO = null;
				for (int i = 0; i < listCapVO.size(); i++) {
					capVO = listCapVO.get(i);
					sb.append("\"").append(capVO.getCoupKindName()).append("\" 쿠폰 (").append(capVO.getDiscount()).append("원 할인)\n");
				} // end for
				
				JOptionPane.showMessageDialog(cppd, sb.toString(), "쿠폰 발급 완료", JOptionPane.INFORMATION_MESSAGE);
			} // end if
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(cppd, "쿠폰 발급에 문제가 생겼습니다");
			e.printStackTrace();
		} // end catch
	}//orderUpdate
	
	/**
	 * 보유쿠폰 개수 보여주기(결제 후 발급받은 쿠폰 개수 포함)
	 * @param phonNum
	 */
	private void heldCoup(String phonNum) {
		SelectCouponDAO scDAO = SelectCouponDAO.getInstance();
		try {
			List<SelectCouponVO> list = scDAO.searchCoupByPhoneNum(phoneNum);
			if(!list.isEmpty()) {
				cppd.getMemberCoup().setText(
						phoneNum + " 님의 현재 보유 쿠폰 : " + list.size() + " 장");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}//heldCoup
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cppd.getToStart()) {
			new StartPageDesign();
			cppd.dispose();
		}
	}//actionPerformed
	
	@Override
	public void windowClosed(WindowEvent e) {
		cppd.dispose();
	}

}//class
