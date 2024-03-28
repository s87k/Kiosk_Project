package kiosk_prj.membership;

import java.awt.Font;
import java.awt.Frame;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import kiosk_prj.adminMain.AdminMainPageDesign;

@SuppressWarnings("serial")
public class MemberShipDesign extends JDialog {
	private JTextField jPhoneNum, jName, memberNum;
	private JButton[] inputNumber;
	private JButton searchMember, addMember, detailMember, modifyMember, checkMember, exit;
	private JTable memberList, orderList, couponList;
	private JTabbedPane listTab;
	private DefaultTableModel dtmMemberList, dtmOrderList, dtmCouponList;
	private String openDate;

	public MemberShipDesign(AdminMainPageDesign ampd, String title, String openDate) {
		super(ampd, "회원관리");
		this.openDate = openDate;
		openDate = ampd.getJlOpenDate().getText();
		
		//이미지
		ImageIcon imgBackground = new ImageIcon(getClass().getClassLoader().getResource("membership.png"));
		ImageIcon imgExit = new ImageIcon(getClass().getClassLoader().getResource("BTexit.png"));
		ImageIcon imgSelect = new ImageIcon(getClass().getClassLoader().getResource("BTmembershipSelect.png"));
		
		inputNumber = new JButton[12];
		for(int i=0 ; i<10 ; i++) {
			ImageIcon imgNumPad = new ImageIcon(getClass().getClassLoader().getResource
					("num" + i + ".png"));
			inputNumber[i] = new JButton(imgNumPad);
			inputNumber[i].setBorderPainted(false);
			inputNumber[i].setContentAreaFilled(false);

		}//end for
		
		ImageIcon imgDel = new ImageIcon(getClass().getClassLoader().getResource("numDel.png"));
		ImageIcon imgDelDel = new ImageIcon(getClass().getClassLoader().getResource("numDelDel.png"));
		
		ImageIcon imgMember1 = new ImageIcon(getClass().getClassLoader().getResource("member1.png"));
		ImageIcon imgMember2 = new ImageIcon(getClass().getClassLoader().getResource("member2.png"));
		ImageIcon imgMember3 = new ImageIcon(getClass().getClassLoader().getResource("member3.png"));
		ImageIcon imgMember4 = new ImageIcon(getClass().getClassLoader().getResource("member4.png"));
		
		// 컴포넌트
		JLabel lbBackground = new JLabel(imgBackground);
		
		// 숫자 입력 버튼 설정
		inputNumber[10] = new JButton(imgDel);
		inputNumber[11] = new JButton(imgDelDel);
		
		inputNumber[10].setBorderPainted(false);
		inputNumber[10].setContentAreaFilled(false);
		inputNumber[11].setBorderPainted(false);
		inputNumber[11].setContentAreaFilled(false);
		
		// 회원 정보 조회 옵션
		searchMember = new JButton(imgMember1);
		addMember = new JButton(imgMember2);
		detailMember = new JButton(imgMember3);
		modifyMember = new JButton(imgMember4);

		// 회원 정보 입력창
		jName = new JTextField();
		jPhoneNum = new JTextField();
		checkMember = new JButton(imgSelect);
		
		// 회원 정보 조회 테이블
		String[] memberColumn = { "번호", "이름", "연락처", "생년월일","등급"};
		dtmMemberList = new DefaultTableModel(memberColumn, 0);
		memberList = new JTable(dtmMemberList);
		JScrollPane jspMemberList = new JScrollPane(memberList);

		memberList.getColumnModel().getColumn(0).setPreferredWidth(50);
		memberList.getColumnModel().getColumn(1).setPreferredWidth(50);
		memberList.getColumnModel().getColumn(2).setPreferredWidth(100);
		memberList.getColumnModel().getColumn(3).setPreferredWidth(100);
		memberList.getColumnModel().getColumn(4).setPreferredWidth(100);

		// 회원 정보 밑 쿠폰 목록 리스트 조회
		// 주문목록과 쿠폰 조회를 위한 tabPane 설정
		listTab = new JTabbedPane();
		memberList.setRowHeight(28);

		// 주문 목록 컬럼
		String[] orderColumn = { "대기번호", "주문시간", "상품명", "결제금액" };
		dtmOrderList = new DefaultTableModel(orderColumn, 0);
		orderList = new JTable(dtmOrderList);
		orderList.setRowHeight(28);
		JScrollPane jspOrderList = new JScrollPane(orderList);

		// 쿠폰 목록 컬럼
		String[] couponColumn = { "번호", "쿠폰명", "할인액", "발급일", "만료일", "상태" };
		dtmCouponList = new DefaultTableModel(couponColumn, 0);
		couponList = new JTable(dtmCouponList);
		couponList.setRowHeight(28);
		JScrollPane jspCouponList = new JScrollPane(couponList);
		couponList.getColumnModel().getColumn(0).setPreferredWidth(10);
		couponList.getColumnModel().getColumn(1).setPreferredWidth(60);
		couponList.getColumnModel().getColumn(2).setPreferredWidth(50);
		couponList.getColumnModel().getColumn(3).setPreferredWidth(120);
		couponList.getColumnModel().getColumn(4).setPreferredWidth(120);
		couponList.getColumnModel().getColumn(5).setPreferredWidth(40);

		listTab.add("주문목록", jspOrderList);
		listTab.add("쿠폰목록", jspCouponList);
		
		// 나가기 버튼
		exit = new JButton(imgExit);

		// 배치 관리자 해제
		setLayout(null);
		
		// 컴포넌트 배치
		// 숫자 입력 버튼
		int x = 125;
		int y = 550;
		inputNumber[0].setBounds(x, y, 50, 50);
		inputNumber[10].setBounds(x+50, y, 50, 50);
		inputNumber[11].setBounds(x+100, y, 50, 50);
		inputNumber[1].setBounds(x, y-50, 50, 50);
		inputNumber[2].setBounds(x+50, y-50, 50, 50);
		inputNumber[3].setBounds(x+100, y-50, 50, 50);
		inputNumber[4].setBounds(x, y-100, 50, 50);
		inputNumber[5].setBounds(x+50, y-100, 50, 50);
		inputNumber[6].setBounds(x+100, y-100, 50, 50);
		inputNumber[7].setBounds(x, y-150, 50, 50);
		inputNumber[8].setBounds(x+50, y-150, 50, 50);
		inputNumber[9].setBounds(x+100, y-150, 50, 50);

		// 회원정보 조회 옵션
		searchMember.setBounds(x+150, y-150, 100, 50);
		addMember.setBounds(x+150, y-100, 100, 50);
		detailMember.setBounds(x+150, y-50, 100, 50);
		modifyMember.setBounds(x+150, y, 100, 50);

		// 회원 조회 밑 입력창
		JLabel nameLabel = new JLabel("이름");
		JLabel phoneLabel = new JLabel("연락처");
		nameLabel.setHorizontalAlignment(JLabel.RIGHT);
		phoneLabel.setHorizontalAlignment(JLabel.RIGHT);
		
		//글꼴
		Font biiiiiig = new Font("맑은 고딕",Font.BOLD,23);
		nameLabel.setFont(biiiiiig);
		phoneLabel.setFont(biiiiiig);
		jName.setFont(biiiiiig);
		jPhoneNum.setFont(biiiiiig);
		
		couponList.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		memberList.setFont(new Font("맑은 고딕", Font.PLAIN, 18));

		nameLabel.setBounds(40, 100, 80, 50);
		phoneLabel.setBounds(40, 135, 80, 80);
		jName.setBounds(125, 105, 150, 50);
		jPhoneNum.setBounds(125, 155, 150, 50);
		checkMember.setBounds(275, 105, 100, 99);

		// 회원 정보 테이블
		jspMemberList.setBounds(420, 100, 500, 200);

		// 주문 목록, 쿠폰 목록 테이블
		listTab.setBounds(420, 300, 500, 350);

		// 나가기 버튼
		exit.setBounds(755, 665, 165, 43);
		lbBackground.setBounds(0,0,1024,768);

		add(inputNumber[0]);
		add(inputNumber[1]);
		add(inputNumber[2]);
		add(inputNumber[3]);
		add(inputNumber[4]);
		add(inputNumber[5]);
		add(inputNumber[6]);
		add(inputNumber[7]);
		add(inputNumber[8]);
		add(inputNumber[9]);
		add(inputNumber[10]);
		add(inputNumber[11]);

		add(searchMember);
		add(addMember);
		add(detailMember);
		add(modifyMember);

		add(checkMember);
		add(nameLabel);
		add(jName);
		add(phoneLabel);
		add(jPhoneNum);

		add(jspMemberList);
		add(listTab);

		add(exit);
		add(lbBackground);

		// 이벤트 등록
		MemberShipEvent mse = new MemberShipEvent(this);

		inputNumber[0].addActionListener(mse);
		inputNumber[1].addActionListener(mse);
		inputNumber[2].addActionListener(mse);
		inputNumber[3].addActionListener(mse);
		inputNumber[4].addActionListener(mse);
		inputNumber[5].addActionListener(mse);
		inputNumber[6].addActionListener(mse);
		inputNumber[7].addActionListener(mse);
		inputNumber[8].addActionListener(mse);
		inputNumber[9].addActionListener(mse);
		inputNumber[10].addActionListener(mse);
		inputNumber[11].addActionListener(mse);

		exit.addActionListener(mse);
		addMember.addActionListener(mse);
		searchMember.addActionListener(mse);
		modifyMember.addActionListener(mse);
		detailMember.addActionListener(mse);
		checkMember.addActionListener(mse);
		memberList.addMouseListener(mse);

		// 다이얼로그 위치 설정
		setBounds(ampd.getX(), ampd.getY(), 1024,768);
		setVisible(true);

	}
	
	public JTextField getjPhoneNum() {
		return jPhoneNum;
	}
	
	public void setjPhoneNum(String phoneNum) {
		jPhoneNum.setText(phoneNum);
	}
	
	public JTextField getjName() {
		return jName;
	}

	public void setjName(String name) {
		jName.setText(name);
	}
	
	public String getOpenDate() {
		return openDate;
	}
	
	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public JTextField getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(JTextField memberNum) {
		this.memberNum = memberNum;
	}

	public JButton[] getInputNumber() {
		return inputNumber;
	}

	public void setInputNumber(JButton[] inputNumber) {
		this.inputNumber = inputNumber;
	}

	public JButton getSearchMember() {
		return searchMember;
	}

	public void setSearchMember(JButton searchMember) {
		this.searchMember = searchMember;
	}

	public JButton getAddMember() {
		return addMember;
	}

	public JTabbedPane getListTab() {
		return listTab;
	}

	public void setListTab(JTabbedPane listTab) {
		this.listTab = listTab;
	}

	public DefaultTableModel getDtmMemberList() {
		return dtmMemberList;
	}

	public void setDtmMemberList(DefaultTableModel dtmMemberList) {
		this.dtmMemberList = dtmMemberList;
	}

	public DefaultTableModel getDtmOrderList() {
		return dtmOrderList;
	}

	public void setDtmOrderList(DefaultTableModel dtmOrderList) {
		this.dtmOrderList = dtmOrderList;
	}

	public DefaultTableModel getDtmCouponList() {
		return dtmCouponList;
	}

	public void setDtmCouponList(DefaultTableModel dtmCouponList) {
		this.dtmCouponList = dtmCouponList;
	}

	public void setAddMember(JButton addMember) {
		this.addMember = addMember;
	}

	public JButton getDetailMember() {
		return detailMember;
	}

	public void setDetailMember(JButton detailMember) {
		this.detailMember = detailMember;
	}

	public JButton getModifyMember() {
		return modifyMember;
	}

	public void setModifyMember(JButton modifyMember) {
		this.modifyMember = modifyMember;
	}

	public JButton getCheckMember() {
		return checkMember;
	}

	public void setCheckMember(JButton checkMember) {
		this.checkMember = checkMember;
	}

	public JButton getExit() {
		return exit;
	}

	public void setExit(JButton exit) {
		this.exit = exit;
	}

	public JTable getMemberList() {
		return memberList;
	}

	public void setMemberList(JTable memberList) {
		this.memberList = memberList;
	}

	public JTable getOrderList() {
		return orderList;
	}

	public void setOrderList(JTable orderList) {
		this.orderList = orderList;
	}

	public JTable getCouponList() {
		return couponList;
	}

	public void setCouponList(JTable couponList) {
		this.couponList = couponList;
	}

}
