package kiosk_prj.view;

import java.awt.Frame;

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

import kiosk_prj.controller.MemberShipEvent;

@SuppressWarnings("serial")
public class MemberShipDesign extends JDialog {
	private JTextField jPhoneNum, jName, memberNum, birthDay;
	private JComboBox<String> grade;
	private JButton[] inputNumber;
	private JButton searchMember, addMember, detailMember, modifyMember, checkMember, exit, add, cancel, orderHistory,
	couponbtn, consumePattern, back;
	private JTable memberList, orderList, couponList;
	private JPanel detailMemberPanel, addMemberPanel, orderListPanel, couponListPanel, consumeListPanel, modifyMemberPanel;
	private JTabbedPane listTab;
	private DefaultTableModel dtmMemberList, dtmOrderList, dtmCouponList;
	
	public MemberShipDesign(JFrame AdminMainPageDesign, String title) {
		super(AdminMainPageDesign,"회원관리");
		
		//컴포넌트
		//숫자 입력 버튼 설정
		inputNumber = new JButton[12];
		inputNumber[0] = new JButton("0");
		inputNumber[1] = new JButton("1");
		inputNumber[2] = new JButton("2");
		inputNumber[3] = new JButton("3");
		inputNumber[4] = new JButton("4");
		inputNumber[5] = new JButton("5");
		inputNumber[6] = new JButton("6");
		inputNumber[7] = new JButton("7");
		inputNumber[8] = new JButton("8");
		inputNumber[9] = new JButton("9");
		inputNumber[10] = new JButton("<-");
		inputNumber[11] = new JButton("C");
		
		//회원 정보 조회 옵션
		searchMember = new JButton("회원목록");
		addMember = new JButton("회원등록");
		detailMember = new JButton("회원상세");
		modifyMember = new JButton("회원수정");
		
		//회원 정보 입력창
		jName = new JTextField();
		jPhoneNum = new JTextField();
		checkMember = new JButton("조회");
		
		
		//회원 정보 조회 테이블
		String[] memberColumn = {"번호", "이름", "연락처", "생년월일"};
		dtmMemberList = new DefaultTableModel(memberColumn,0);
		memberList = new JTable(dtmMemberList);
		JScrollPane jspMemberList = new JScrollPane(memberList);
		
		memberList.getColumnModel().getColumn(0).setPreferredWidth(50);
		memberList.getColumnModel().getColumn(1).setPreferredWidth(100);
		memberList.getColumnModel().getColumn(2).setPreferredWidth(200);
		memberList.getColumnModel().getColumn(3).setPreferredWidth(150);
		
		
		//회원 정보 밑 쿠폰 목록 리스트 조회
		//주문목록과 쿠폰 조회를 위한 tabPane 설정
		listTab = new JTabbedPane();
		
		//주문 목록 컬럼
		String[] orderColumn = {"번호","주문시간","상품명","결제금액"};
		dtmOrderList = new DefaultTableModel(orderColumn,0);
		orderList = new JTable(dtmOrderList);
		JScrollPane jspOrderList = new JScrollPane(orderList);
		
		//쿠폰 목록 컬럼
		String[] couponColumn = {"번호","쿠폰명","할인액","발급일","만료일","상태"};
		dtmCouponList = new DefaultTableModel(couponColumn,0);
		couponList = new JTable(dtmCouponList);
		JScrollPane jspCouponList = new JScrollPane(couponList);
		
		listTab.add("주문목록", jspOrderList);
		listTab.add("쿠폰목록", jspCouponList);
		
		//나가기 버튼
		exit = new JButton("나가기");
		
		
		
		//배치 관리자 해제
		setLayout(null);
		//컴포넌트 배치
		//숫자 입력 버튼
		inputNumber[0].setBounds(90, 450, 50, 50);
		inputNumber[10].setBounds(140, 450, 50, 50);
		inputNumber[11].setBounds(190, 450, 50, 50);
		inputNumber[1].setBounds(90, 400, 50, 50);
		inputNumber[2].setBounds(140, 400, 50, 50);
		inputNumber[3].setBounds(190, 400, 50, 50);
		inputNumber[4].setBounds(90, 350, 50, 50);
		inputNumber[5].setBounds(140, 350, 50, 50);
		inputNumber[6].setBounds(190, 350, 50, 50);
		inputNumber[7].setBounds(90, 300, 50, 50);
		inputNumber[8].setBounds(140, 300, 50, 50);
		inputNumber[9].setBounds(190, 300, 50, 50);
		
		//회원정보 조회 옵션
		searchMember.setBounds(240, 300, 100, 50);
		addMember.setBounds(240, 350, 100, 50);
		detailMember.setBounds(240, 400, 100, 50);
		modifyMember.setBounds(240, 450, 100, 50);
		
		//회원 조회 밑 입력창
		JLabel nameLabel = new JLabel("이름");
		JLabel phoneLabel = new JLabel("연락처");
		
		nameLabel.setBounds(50, 50, 50 ,40);
		jName.setBounds(90, 50, 150, 50);
		phoneLabel.setBounds(50, 100, 50, 40);
		jPhoneNum.setBounds(90, 100, 150, 50);
		checkMember.setBounds(240, 50, 100, 100);
		
		//회원 정보 테이블
		jspMemberList.setBounds(350, 50, 500, 200);
		
		//주문 목록, 쿠폰 목록 테이블
		listTab.setBounds(350, 250, 500, 350);
		
		//나가기 버튼
		exit.setBounds(750, 620, 100, 50);
		
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
		
		//이벤트 등록
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
		
		//다이얼로그 위치 설정
		setVisible(true);
		setBounds(AdminMainPageDesign.getX(), AdminMainPageDesign.getY(), 924, 768);
		
		
		
	}
	
	public JTextField getjPhoneNum() {
		return jPhoneNum;
	}
	
	public void setjPhoneNum(JTextField jPhoneNum) {
		this.jPhoneNum = jPhoneNum;
	}
	public JTextField getjName() {
		return jName;
	}
	public void setjName(JTextField jName) {
		this.jName = jName;
	}
	public JTextField getMemberNum() {
		return memberNum;
	}
	public void setMemberNum(JTextField memberNum) {
		this.memberNum = memberNum;
	}
	public JTextField getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(JTextField birthDay) {
		this.birthDay = birthDay;
	}
	public JComboBox<String> getGrade() {
		return grade;
	}
	public void setGrade(JComboBox<String> grade) {
		this.grade = grade;
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
	public JButton getAdd() {
		return add;
	}
	public void setAdd(JButton add) {
		this.add = add;
	}
	public JButton getCancel() {
		return cancel;
	}
	public void setCancel(JButton cancel) {
		this.cancel = cancel;
	}
	public JButton getOrderHistory() {
		return orderHistory;
	}
	public void setOrderHistory(JButton orderHistory) {
		this.orderHistory = orderHistory;
	}
	public JButton getCouponbtn() {
		return couponbtn;
	}
	public void setCouponbtn(JButton couponbtn) {
		this.couponbtn = couponbtn;
	}
	public JButton getConsumePattern() {
		return consumePattern;
	}
	public void setConsumePattern(JButton consumePattern) {
		this.consumePattern = consumePattern;
	}
	public JButton getBack() {
		return back;
	}
	public void setBack(JButton back) {
		this.back = back;
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
	public JPanel getDetailMemberPanel() {
		return detailMemberPanel;
	}
	public void setDetailMemberPanel(JPanel detailMemberPanel) {
		this.detailMemberPanel = detailMemberPanel;
	}
	public JPanel getAddMemberPanel() {
		return addMemberPanel;
	}
	public void setAddMemberPanel(JPanel addMemberPanel) {
		this.addMemberPanel = addMemberPanel;
	}
	public JPanel getOrderListPanel() {
		return orderListPanel;
	}
	public void setOrderListPanel(JPanel orderListPanel) {
		this.orderListPanel = orderListPanel;
	}
	public JPanel getCouponListPanel() {
		return couponListPanel;
	}
	public void setCouponListPanel(JPanel couponListPanel) {
		this.couponListPanel = couponListPanel;
	}
	public JPanel getConsumeListPanel() {
		return consumeListPanel;
	}
	public void setConsumeListPanel(JPanel consumeListPanel) {
		this.consumeListPanel = consumeListPanel;
	}
	public JPanel getModifyMemberPanel() {
		return modifyMemberPanel;
	}
	public void setModifyMemberPanel(JPanel modifyMemberPanel) {
		this.modifyMemberPanel = modifyMemberPanel;
	}
	
}
