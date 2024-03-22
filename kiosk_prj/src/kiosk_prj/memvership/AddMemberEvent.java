package kiosk_prj.memvership;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kiosk_prj.membership.dao.MemberShipDAO;
import kiosk_prj.membership.vo.MemberShipVO;

public class AddMemberEvent extends WindowAdapter implements ActionListener{
	private AddMemberDesign admd;
	public AddMemberEvent(AddMemberDesign admd) {
		this.admd = admd;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == admd.getExit()) {
			admd.dispose();
		}
		if(e.getSource() == admd.getAdd()) {
			add();
		}
	}
	public void add() {
		MemberShipVO mVO = new MemberShipVO();
		
		mVO.setPhoneNum(admd.getjPhoneNum().getText());
		mVO.setMemberName(admd.getjName().getText());
		mVO.setMemberBirth(admd.getBirthDay().getText());
		
		MemberShipDAO mDAO = MemberShipDAO.getInstance();
		try {
			mDAO.insertMember(mVO);
			JOptionPane.showMessageDialog(admd, "회원 등록이 완료되었습니다.");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(admd, "회원 등록에 실패하였습니다.");
		}
	}
}
