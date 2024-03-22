package kiosk_prj.memvership;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import kiosk_prj.DAO.DbConnection;
import kiosk_prj.membership.dao.MemberShipDAO;
import kiosk_prj.membership.vo.MemberShipVO;

public class SearchMemberEvent extends WindowAdapter implements ActionListener {
	private SearchMemberDesign smd;

	public SearchMemberEvent(SearchMemberDesign smd) {
		this.smd = smd;
		setMemberTable();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == smd.getExit()) {
			smd.dispose();
		}
	}
		public void setMemberTable() {
		
		MemberShipDAO msDAO = MemberShipDAO.getInstance();
		try {
			//입력된 전화번호의 뒷자리에 해당하는 회원정보 가져오기
			List<MemberShipVO> list = msDAO.allMember();
			//모델 객체 값 설정
			DefaultTableModel dtm = smd.getDtmAllMemberTable();
			//값 설정 전에 모델객체 초기화
			dtm.setRowCount(0);
			
			String[] rowData = null;
			MemberShipVO msVO = null;
			
//			if(list.isEmpty()) {
//				JOptionPane.showMessageDialog(smd, phoneNum + "은 존재하지 않습니다.");
//			}//end if
			for(int i = 0; i < list.size(); i++) {
				msVO = list.get(i);
			rowData = new String[5];
			rowData[0] = "" + (i + 1) + "";
			rowData[1] = msVO.getPhoneNum();
			rowData[2] = msVO.getMemberName();
			rowData[3] = msVO.getMemberBirth();
			rowData[4] = msVO.getGrade();
			
			//행 등록
			dtm.addRow(rowData);
			}//end for
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
	}//setMemberTable
}
