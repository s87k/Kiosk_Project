package kiosks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import kiosk_prj.adminMain.AdminMainPageDesign;
import kiosks.CompletePayPageDesign;
import kiosks.StartPageDesign;
import kiosks.dao.SelectCouponDAO;
import kiosks.vo.SelectCouponVO;

public class CompletePayPageEvent extends WindowAdapter implements ActionListener{

	private CompletePayPageDesign cppd;
	private String phoneNum;
	
	public CompletePayPageEvent(CompletePayPageDesign cppd) {
		this.cppd = cppd;
		phoneNum = cppd.getPhoneNum();
		//결제완료 후 보유 쿠폰 개수
		heldCoup(phoneNum);
		//발급된 쿠폰 보여주기
		newPublishCoup();
	}//CompletePayPageEvent
	
	/**
	 * 결제 후 발급된 쿠폰 이름 보여주기
	 */
	private void newPublishCoup() {
		
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
