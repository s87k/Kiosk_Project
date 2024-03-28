package kiosk_prj.manumanage;

import java.awt.Font;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


@SuppressWarnings("serial")
public class MenuTypeDesign extends JDialog  {
	
	private JTable typeList;
	private JTextField jtfTypecode, jtfTypename;
	private JButton jbAdd, jbUpdate, jbDelete,
			jbClose;
	private DefaultTableModel dtmTypeList;
	
	public MenuTypeDesign(JFrame MenuManageDesign, String title) {
		super(MenuManageDesign,"종류관리");
		
		//배치 관리자 해제
		setLayout(null);
		
		//폰트
		Font font=new Font("맑은 고딕",Font.BOLD, 14 );
		
		//컴포넌트
		//라벨
		JLabel jlblTypecode=new JLabel("종류코드");
		JLabel jlblTypename=new JLabel("종류명");
		
		//추가,삭제,변경,품절설정,조회 버튼
		jbAdd = new JButton("추가");
		jbDelete = new JButton("삭제");
		jbUpdate = new JButton("변경");
		jbClose = new JButton("나가기");
		
		//종류 정보 입력창
		jtfTypecode = new JTextField();
		jtfTypename = new JTextField();
		
		//종류정보 조회 테이블
		String[] columnName = {"종류코드","종류명"};
		dtmTypeList = new DefaultTableModel(columnName,0);
		typeList = new JTable(dtmTypeList);
		JScrollPane jspTypeList = new JScrollPane(typeList);
		jspTypeList.setBorder(new TitledBorder("종류정보 조회"));
		
		//J테이블 컬럼의 넓이 변경
		typeList.getColumnModel().getColumn(0).setPreferredWidth(150);
		typeList.getColumnModel().getColumn(1).setPreferredWidth(150);
		
		//컴포넌트 배치-테이블
		jspTypeList.setBounds(290, 30, 370, 330);		
		
		//컴포넌트 배치-라벨
		jlblTypecode.setBounds(40, 100, 50, 40);
		jlblTypename.setBounds(40, 150, 50, 40);
		
		//컴포넌트 배치-빈칸
		jtfTypecode.setBounds(120, 100, 120, 40);
		jtfTypename.setBounds(120, 150, 120, 40);
		
		//컴포넌트 배치-버튼
		jbAdd.setBounds(60, 250, 80, 60);
		jbDelete.setBounds(150, 250, 80, 60);
		jbUpdate.setBounds(150, 320, 80, 60);
		jbClose.setBounds(550, 380, 100, 50);
		
		//컴포넌트 등록
		add(jspTypeList);
		
		add(jlblTypecode);
		add(jlblTypename);
		
		add(jtfTypecode);
		add(jtfTypename);
		add(jbAdd);
		add(jbDelete);
		add(jbUpdate);
		add(jbClose);
		
		//이벤트 등록
		MenuTypeEvent mte=new MenuTypeEvent(this);
		
		jtfTypecode.addActionListener(mte);
		jtfTypename.addActionListener(mte);
		jbAdd.addActionListener(mte);
		jbDelete.addActionListener(mte);
		jbUpdate.addActionListener(mte);
		jbClose.addActionListener(mte);
		
		mte.selectAllType();
		
		//다이얼로그 위치 설정
		setBounds(MenuManageDesign.getX(), MenuManageDesign.getY(), 720, 500);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}//MenuTypeDesign

	public JTable getTypeList() {
		return typeList;
	}

	public void setTypeList(JTable typeList) {
		this.typeList = typeList;
	}

	public JTextField getJtfTypecode() {
		return jtfTypecode;
	}

	public void setJtfTypecode(JTextField jtfTypecode) {
		this.jtfTypecode = jtfTypecode;
	}

	public JTextField getJtfTypename() {
		return jtfTypename;
	}

	public void setJtfTypename(JTextField jtfTypename) {
		this.jtfTypename = jtfTypename;
	}

	public JButton getJbAdd() {
		return jbAdd;
	}

	public void setJbAdd(JButton jbAdd) {
		this.jbAdd = jbAdd;
	}

	public JButton getJbUpdate() {
		return jbUpdate;
	}

	public void setJbUpdate(JButton jbUpdate) {
		this.jbUpdate = jbUpdate;
	}

	public JButton getJbDelete() {
		return jbDelete;
	}

	public void setJbDelete(JButton jbDelete) {
		this.jbDelete = jbDelete;
	}

	public JButton getJbClose() {
		return jbClose;
	}

	public void setJbClose(JButton jbClose) {
		this.jbClose = jbClose;
	}

	public DefaultTableModel getDtmTypeList() {
		return dtmTypeList;
	}

	public void setDtmTypeList(DefaultTableModel dtmTypeList) {
		this.dtmTypeList = dtmTypeList;
	}
	
}//class
