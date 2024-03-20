package kiosk_prj.view.memverShipView;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kiosk_prj.controller.memberShipEvent.SearchMemberEvent;

@SuppressWarnings("serial")
public class SearchMemberDesign extends JDialog {
	JButton exit;
	JTable allMemberTable;
	DefaultTableModel dtmAllMemberTable;
	
	public SearchMemberDesign(JDialog MemberShipDesign, String title) {
		super(MemberShipDesign, "전체 회원목록");
		
		//컴포넌트
		String[] memberColumns = {"번호", "이름", "연락처", "생년월일", "등급"};
		dtmAllMemberTable = new DefaultTableModel(memberColumns, 0);
		allMemberTable = new JTable(dtmAllMemberTable);
		JScrollPane jspMemberList = new JScrollPane(allMemberTable);
		
		allMemberTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		allMemberTable.getColumnModel().getColumn(1).setPreferredWidth(100);
		allMemberTable.getColumnModel().getColumn(2).setPreferredWidth(200);
		allMemberTable.getColumnModel().getColumn(3).setPreferredWidth(150);
		
		exit = new JButton("나가기");
		//컴포넌트 배치
		jspMemberList.setBounds(10, 10, 500, 480);
		exit.setBounds(300, 500, 100, 100);
		
		//컴포넌트 추가
		add(jspMemberList);
		add(exit);
		//배치관리자 해제
		setLayout(null);
		
		//ActionListener 등록
		SearchMemberEvent sme = new SearchMemberEvent(this);
		
		exit.addActionListener(sme);
		
		setVisible(true);
		setBounds(MemberShipDesign.getX()+350, MemberShipDesign.getY(), 574,648);
	}

	public JButton getExit() {
		return exit;
	}

	public void setExit(JButton exit) {
		this.exit = exit;
	}

	public JTable getAllMemberTable() {
		return allMemberTable;
	}

	public void setAllMemberTable(JTable allMemberTable) {
		this.allMemberTable = allMemberTable;
	}

	public DefaultTableModel getDtmAllMemberTable() {
		return dtmAllMemberTable;
	}

	public void setDtmAllMemberTable(DefaultTableModel dtmAllMemberTable) {
		this.dtmAllMemberTable = dtmAllMemberTable;
	}
}
