package kiosk_prj.manumanage;

import java.awt.Font;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


@SuppressWarnings("serial")
public class MenuManageDesign extends JFrame {

	private JTable menuList;
	private JTextField jtfMenucode, jtfTypecode, jtfMenuname, jtfPrice, jtfMenuImg;
	private JButton jbAdd, jbUpdate, jbDelete,
	                jbMenuType, jbGoback;
	private DefaultTableModel dtmMenuList;  
	
//	public MenumanageDesign(AdminMainPageDesign ampd) {
//		super(ampd, "메뉴관리", true);
//		this.ampd = ampd;

	public MenuManageDesign() {
		super("메뉴관리");
		
		//배치 관리자 해제 
		setLayout(null);
		
		//폰트
		Font font=new Font("맑은 고딕",Font.BOLD, 14 );
		
		//컴포넌트 
		//라벨
		JLabel jlblMenucode=new JLabel("메뉴코드");
		JLabel jlblTypecode=new JLabel("종류코드");
		JLabel jlblMenuname=new JLabel("메뉴명");
		JLabel jlblPrice=new JLabel("가격");
		JLabel jlblMenuImg=new JLabel("이미지");
		
		//추가,삭제,변경,품절설정,조회 버튼
		jbAdd = new JButton("추가");
		jbDelete = new JButton("삭제");
		jbUpdate = new JButton("변경");
		jbMenuType = new JButton("종류관리");
		jbGoback = new JButton("뒤로가기");
		
		//메뉴 정보 입력창
		jtfMenucode = new JTextField(10);
		jtfTypecode = new JTextField(10);
		jtfMenuname = new JTextField(10);
		jtfPrice = new JTextField(10);
		jtfMenuImg = new JTextField(10);
		
		//메뉴정보 조회 테이블
		String[] columnName = {"메뉴코드","종류코드","메뉴명","가격","이미지"};
		dtmMenuList = new DefaultTableModel(columnName,0);
		menuList = new JTable(dtmMenuList);
		JScrollPane jspMenuList = new JScrollPane(menuList);
		jspMenuList.setBorder(new TitledBorder("메뉴정보 조회"));
		
		//J테이블 컬럼의 넓이 변경
		menuList.getColumnModel().getColumn(0).setPreferredWidth(110);
		menuList.getColumnModel().getColumn(1).setPreferredWidth(80);
		menuList.getColumnModel().getColumn(2).setPreferredWidth(130);
		menuList.getColumnModel().getColumn(3).setPreferredWidth(70);
		menuList.getColumnModel().getColumn(4).setPreferredWidth(150);
		
		//컴포넌트 배치-테이블
		jspMenuList.setBounds(350, 50, 620, 500);
		
		//컴포넌트 배치-라벨
		jlblMenucode.setBounds(50, 120, 50, 40);
		jlblTypecode.setBounds(50, 170, 50, 40);
		jlblMenuname.setBounds(50, 220, 50, 40);
		jlblPrice.setBounds(50, 270, 50, 40);
		jlblMenuImg.setBounds(50, 320, 50, 40);
		
		//컴포넌트 배치-빈칸
		jtfMenucode.setBounds(150, 120, 150, 40);
		jtfTypecode.setBounds(150, 170, 150, 40);
		jtfMenuname.setBounds(150, 220, 150, 40);
		jtfPrice.setBounds(150, 270, 150, 40);
		jtfMenuImg.setBounds(150, 320, 150, 40);
		
		//컴포넌트 배치-버튼
		jbAdd.setBounds(80, 430, 100, 70);
		jbDelete.setBounds(190, 430, 100, 70);
		jbUpdate.setBounds(80, 510, 100, 70);
		jbMenuType.setBounds(190, 510, 100, 70);
		jbGoback.setBounds(840, 590, 110, 60);
		
		//컴포넌트 등록
		add(jspMenuList);
		
		add(jlblMenucode);
		add(jlblTypecode);
		add(jlblMenuname);
		add(jlblPrice);
		add(jlblMenuImg);
		
		add(jtfMenucode);
		add(jtfTypecode);
		add(jtfMenuname);
		add(jtfPrice);
		add(jtfMenuImg);
		add(jbAdd);
		add(jbUpdate);
		add(jbDelete);
		add(jbMenuType);
		add(jbGoback);
		
		
		//이벤트 등록
		MenuManageEvent mme=new MenuManageEvent(this);
		
		jtfMenucode.addActionListener(mme);
		jtfTypecode.addActionListener(mme);
		jtfMenuname.addActionListener(mme);
		jtfPrice.addActionListener(mme);
		jtfMenuImg.addActionListener(mme);
		jbAdd.addActionListener(mme);
		jbUpdate.addActionListener(mme);
		jbDelete.addActionListener(mme);
		jbMenuType.addActionListener(mme);
		jbGoback.addActionListener(mme);
		
		mme.selectAllMenu();
		
		setSize(1024, 768);
		setLocationRelativeTo(null);//모니터 가운데다 띄우는 method
		setVisible(true);
		
	}//MenuManageDesign


	public JTable getMenuList() {
		return menuList;
	}

	public JTextField getJtfMenucode() {
		return jtfMenucode;
	}

	public JTextField getJtfTypecode() {
		return jtfTypecode;
	}

	public JTextField getJtfMenuname() {
		return jtfMenuname;
	}

	public JTextField getJtfPrice() {
		return jtfPrice;
	}
	
	public JTextField getJtfMenuImg() {
		return jtfMenuImg;
	}

	public JButton getJbAdd() {
		return jbAdd;
	}

	public JButton getJbUpdate() {
		return jbUpdate;
	}

	public JButton getJbDelete() {
		return jbDelete;
	}

	public JButton getJbAddType() {
		return jbMenuType;
	}

	public JButton getJbGoback() {
		return jbGoback;
	}

	public DefaultTableModel getDtmMenuList() {
		return dtmMenuList;
	}
	
}//class