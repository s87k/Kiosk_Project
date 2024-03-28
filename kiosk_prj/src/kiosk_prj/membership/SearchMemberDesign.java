package kiosk_prj.membership;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class SearchMemberDesign extends JDialog {
	JButton exit;
	JTable allMemberTable;
	DefaultTableModel dtmAllMemberTable;
	
	public SearchMemberDesign(JDialog MemberShipDesign, String title) {
		super(MemberShipDesign, "전체 회원목록");
		
		//이미지
		ImageIcon imgExit = new ImageIcon(getClass().getClassLoader().getResource("BTexit2.png"));
		
		//컴포넌트
		String[] memberColumns = {"번호", "이름", "연락처", "생년월일", "등급"};
		dtmAllMemberTable = new DefaultTableModel(memberColumns, 0);
		allMemberTable = new JTable(dtmAllMemberTable);
		JScrollPane jspMemberList = new JScrollPane(allMemberTable);
		
		allMemberTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		allMemberTable.getColumnModel().getColumn(1).setPreferredWidth(100);
		allMemberTable.getColumnModel().getColumn(2).setPreferredWidth(200);
		allMemberTable.getColumnModel().getColumn(3).setPreferredWidth(150);
		
		exit = new JButton(imgExit);
		exit.setBorderPainted(false);
		exit.setContentAreaFilled(false);
		
		//컴포넌트 배치
		jspMemberList.setBounds(32, 10, 500, 480);
		exit.setBounds(187, 500, 200, 90);
		
		//컴포넌트 추가
		add(jspMemberList);
		add(exit);
		//배치관리자 해제
		setLayout(null);
		
		//ActionListener 등록
		SearchMemberEvent sme = new SearchMemberEvent(this);
		
		exit.addActionListener(sme);
		
		getContentPane().setBackground(new Color(0xECEDFA));
		setVisible(true);
		setBounds(MemberShipDesign.getX()+230, MemberShipDesign.getY()+40, 574,648);
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
