package kiosk_prj.membership;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class AddMemberDesign extends JDialog {
	private JTextField jPhoneNum, jName, birthDay;
	
	private JButton add, exit;
	
	public AddMemberDesign(JDialog MemberShipDesign, String title) {
		super(MemberShipDesign, "회원등록");
		
		//컴포넌트
		//콤보박스 아이템 설정
		
		/**
		 * 텍스트 필드 설정
		 */
		JLabel jlPhone = new JLabel("연락처");
		JLabel jlName = new JLabel("이름");
		JLabel jlBirthDay = new JLabel("생년월일");
		
		jPhoneNum = new JTextField();
		jName = new JTextField();
		birthDay = new JTextField();
		
		
		/**
		 * 버튼 설정
		 */
		add = new JButton("등록");
		exit = new JButton("취소");
		
		/**
		 * 배치 관리자 해제 
		 */
		setLayout(null);
		//컴포넌트 배치
		
		//텍스트 필드
		jlPhone.setBounds(100, 50, 50, 40);
		jPhoneNum.setBounds(150, 50, 250, 50);
		jlName.setBounds(100, 110, 50, 40);
		jName.setBounds(150, 110, 250, 50);
		jlBirthDay.setBounds(100, 170, 50, 40);
		birthDay.setBounds(150, 170, 250, 50);
		
		
		//버튼
		add.setBounds(150, 500, 100, 100);
		exit.setBounds(300, 500, 100, 100);
		
	
		add(jlPhone);
		add(jPhoneNum);
		add(jlName);
		add(jName);
		add(jlBirthDay);
		add(birthDay);
	
		
		
		add(add);
		add(exit);
		
		/**
		 * action_event 등록 
		 */
		AddMemberEvent ame = new AddMemberEvent(this);
		add.addActionListener(ame);
		exit.addActionListener(ame);
		setVisible(true);
		setBounds(MemberShipDesign.getX()+350, MemberShipDesign.getY(), 574,648);
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

	public JButton getAdd() {
		return add;
	}

	public void setAdd(JButton add) {
		this.add = add;
	}

	public JButton getExit() {
		return exit;
	}

	public void setExit(JButton exit) {
		this.exit = exit;
	}
}
