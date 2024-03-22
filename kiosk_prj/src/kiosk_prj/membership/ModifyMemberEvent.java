package kiosk_prj.membership;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;

import javax.swing.JOptionPane;


import kiosk_prj.membership.dao.MemberShipDAO;
import kiosk_prj.membership.vo.MemberShipVO;
import kiosk_prj.adminMain.AdminMainPageDesign;
import kiosk_prj.membership.dao.MemberShipDAO;
import kiosk_prj.membership.vo.MemberShipVO;


public class ModifyMemberEvent extends WindowAdapter implements ActionListener {
	private ModifyMemberDesign mmd;
	
	
	public ModifyMemberEvent(ModifyMemberDesign mmd) {
		this.mmd = mmd;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == mmd.getExit()) {
			mmd.dispose();
		}
		if(e.getSource() == mmd.getModify()) {
			modify();
		}
		
	}
	public void modify() {
		MemberShipVO mVO = new MemberShipVO();
		
		
		
		mVO.setPhoneNum(mmd.getjPhoneNum().getText());
		System.out.println(mmd.getjPhoneNum().getText());
		mVO.setMemberName(mmd.getjName().getText());
		mVO.setMemberBirth(mmd.getBirthDay().getText());
		mVO.setGrade(mmd.getGrade().getSelectedItem().toString());
		

		
		MemberShipDAO mDAO = MemberShipDAO.getInstance();
		try {
			mDAO.updateMember(mVO, mmd.getjPhoneNum().getText());
			JOptionPane.showMessageDialog(mmd, "회원 수정이 완료되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(mmd, "회원 수정에 실패하였습니다.");
		}
	}

}
