package kiosks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kiosks.JoinMembershipDesign;
import kiosks.PaymentPageDesign;
import kiosks.dao.SearchMemberShipDAO;
import kiosks.vo.SearchMemberShipVO;

public class JoinMembershipEvent extends WindowAdapter implements ActionListener {

	private JoinMembershipDesign jmd;
	private PhoneNumDesign pnd;

	public JoinMembershipEvent(JoinMembershipDesign jmd, PhoneNumDesign pnd) {
		this.jmd = jmd;
		this.pnd = pnd;
	}// JoinMembershipEvent

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jmd.getCancel()) {
			pnd.dispose();
		}
		if (e.getSource() == jmd.getJoin()) {
			addMembership();
		}
	}// actionPerformed

	private void addMembership() {
		SearchMemberShipVO smsVO = new SearchMemberShipVO();
		String phoneNum = jmd.getJtfPhoneNum().getText();
		String name = jmd.getJtfName().getText();
		StringBuilder births = new StringBuilder();
		births.append(jmd.getComYear().getSelectedItem().toString())
		.append(jmd.getComMonth().getSelectedItem().toString())
		.append(jmd.getComDay().getSelectedItem().toString());
		String birth = births.toString();

		if (!(phoneNum.length() < 10 || birth.length() != 8 || name.length() > 15)) {

			// 회원번호 추가
			smsVO.setPhoneNumber(phoneNum);
			// 회원 생년월일 추가
			smsVO.setMemberBirth(birth);
			// 회원 이름 추가
			smsVO.setMemberName(name);

			SearchMemberShipDAO smsDAO = SearchMemberShipDAO.getInstance();
			try {
				smsDAO.insertMemberShip(smsVO);
				JOptionPane.showMessageDialog(jmd, "회원 등록이 완료되었습니다.");
				pnd.dispose();// 결제창으로 돌아감.
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(jmd, "회원 등록에 실패했습니다.");
				return;
			}// end catch
		} else {
			JOptionPane.showMessageDialog(jmd, "<html>입력하신 정보를 확인해주세요. <br> 전화번호 최소 10자리, 생년월일 8자리를 입력해주세요. </html>");
		}
	}

	@Override
	public void windowClosed(WindowEvent e) {
		jmd.dispose();
	}

}// class
