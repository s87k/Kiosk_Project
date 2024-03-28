package kiosk_prj.membership;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ModifyMemberDesign extends JDialog{
	private JTextField jPhoneNum, jName, birthDay;
	private JComboBox<String> grade;
	private JButton modify, exit, delete;
	private String exPhoneNum;
	private String phoneNum;
	
	public ModifyMemberDesign(MemberShipDesign MemberShipDesign, String title, String phoneNum) {
		super(MemberShipDesign, "회원수정", true);
		this.phoneNum = phoneNum;
		
		//이미지
		ImageIcon imgUpdate = new ImageIcon(getClass().getClassLoader().getResource("BTmemberUpdate.png"));
		ImageIcon imgCancle = new ImageIcon(getClass().getClassLoader().getResource("BTaddCancle.png"));
		ImageIcon imgDelete = new ImageIcon(getClass().getClassLoader().getResource("BTmemberDelete.png"));
		
		//콤보박스 아이템 설정
		String[] grades = {"신규 회원", "귀하신 분", "VVIP"};
		//텍스트 필드 설정
		JLabel jlPhone = new JLabel("연락처");
		JLabel jlName = new JLabel("이름");
		JLabel jlBirthDay = new JLabel("생년월일");
		JLabel jlGrade = new JLabel("등급");
		
		jPhoneNum = new JTextField(phoneNum);
		jPhoneNum.setEditable(false);
		jName = new JTextField();
		birthDay = new JTextField();
		grade = new JComboBox<>(grades);
		
		//버튼 설정
		modify = new JButton(imgUpdate);
		exit = new JButton(imgCancle);
		delete = new JButton(imgDelete);
		
		//폰트
		Font font = new Font("맑은 고딕",Font.BOLD,25);
		jlPhone.setFont(font);
		jlName.setFont(font);
		jlBirthDay.setFont(font);
		jlGrade.setFont(font);
		
		jPhoneNum.setFont(font);
		jPhoneNum.setFont(font);
		birthDay.setFont(font);
		grade.setFont(font);
		
		//배치 관리자 해제
		setLayout(null);
		
		//컴포넌트 배치
		//텍스트 필드
		jlPhone.setBounds(100, 50, 80, 40);
		jlName.setBounds(100, 110, 80, 40);
		jlBirthDay.setBounds(100, 170, 80, 40);
		jlGrade.setBounds(100, 230, 80, 40);
		
		jPhoneNum.setBounds(180, 50, 250, 50);
		jName.setBounds(180, 110, 250, 50);
		birthDay.setBounds(180, 170, 250, 50);
		grade.setBounds(180, 230, 250, 40);
		
		//버튼
		modify.setBounds(80, 425, 100, 100);
		exit.setBounds(230, 425, 100, 100);
		delete.setBounds(380, 425, 100, 100);
		
	
		add(jlPhone);
		add(jPhoneNum);
		add(jlName);
		add(jName);
		add(jlBirthDay);
		add(birthDay);
		add(jlGrade);
		add(grade);
		
		add(modify);
		add(exit);
		add(delete);
		
		ModifyMemberEvent mme = new ModifyMemberEvent(this);
		exit.addActionListener(mme);
		modify.addActionListener(mme);
		delete.addActionListener(mme);
		
		getContentPane().setBackground(new Color(0xECEDFA));
		setBounds(MemberShipDesign.getX()+350, MemberShipDesign.getY(), 574,648);
		setVisible(true);
	}


	public String getPhoneNum() {
		return phoneNum;
	}


	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}


	public JButton getDelete() {
		return delete;
	}


	public void setDelete(JButton delete) {
		this.delete = delete;
	}


	public String getExPhoneNum() {
		return exPhoneNum;
	}


	public void setExPhoneNum(String exPhoneNum) {
		this.exPhoneNum = exPhoneNum;
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

	public JButton getModify() {
		return modify;
	}

	public void setModify(JButton modify) {
		this.modify = modify;
	}

	public JButton getExit() {
		return exit;
	}

	public void setExit(JButton exit) {
		this.exit = exit;
	}
}
