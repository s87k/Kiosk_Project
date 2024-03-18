package kiosk_prj.view;

import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class MemberShipDesign extends JDialog {
	private JTextField jPhoneNum, jName, memberNum, birthDay;
	private JComboBox<String> grade;
	private JButton[] inputNumber;
	private JButton searchMember, addMember, detailMember, modifyMember, checkMember, exit, add, cancel, orderHistory,
	couponbtn, consumePattern, back;
	private JTable memberList, orderList, couponList;
	private JPanel detailMemberPanel, addMemberPanel, orderListPanel, couponListPanel, consumeListPanel, modifyMemberPanel;
	
	public MemberShipDesign(JFrame AdminMainPageDesign, String title) {
		super(AdminMainPageDesign,"회원관리");
		
		//컴포넌트
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
		
		//배치 관리자 해제
		setLayout(null);
		//컴포넌트 배치
		inputNumber[0].setBounds(getBounds());
		
		//다이얼로그 위치 설정
		setVisible(true);
		setBounds(AdminMainPageDesign.getX(), AdminMainPageDesign.getY(), 1024, 768);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		
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
