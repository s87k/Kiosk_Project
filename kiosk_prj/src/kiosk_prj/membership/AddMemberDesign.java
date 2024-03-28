package kiosk_prj.membership;


import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
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
		
		//이미지
		ImageIcon imgAdd = new ImageIcon(getClass().getClassLoader().getResource("BTadd.png"));
		ImageIcon imgCancle = new ImageIcon(getClass().getClassLoader().getResource("BTaddCancle.png"));
		
		/**
		 * 텍스트 필드 설정
		 */
		JLabel jlPhone = new JLabel("연락처");
		JLabel jlName = new JLabel("이름");
		JLabel jlBirthDay = new JLabel("생년월일");
		
		jlPhone.setHorizontalAlignment(JLabel.RIGHT);
		jlName.setHorizontalAlignment(JLabel.RIGHT);
		jlBirthDay.setHorizontalAlignment(JLabel.RIGHT);
		
		jPhoneNum = new JTextField();
		jName = new JTextField();
		birthDay = new JTextField();
		
		
		/**
		 * 버튼 설정
		 */
		add = new JButton(imgAdd);
		exit = new JButton(imgCancle);
		
		add.setBorderPainted(false);
		add.setContentAreaFilled(false);
		exit.setBorderPainted(false);
		exit.setContentAreaFilled(false);
		
		//폰트
		Font Biiig = new Font("맑은 고딕",Font.BOLD,23);
		jlPhone.setFont(Biiig);
		jlName.setFont(Biiig);
		jlBirthDay.setFont(Biiig);
		jPhoneNum.setFont(Biiig);
		jName.setFont(Biiig);
		birthDay.setFont(Biiig);
		
		/**
		 * 배치 관리자 해제 
		 */
		setLayout(null);
		//컴포넌트 배치
		
		//텍스트 필드
		jlPhone.setHorizontalAlignment(JLabel.RIGHT);
		jlName.setHorizontalAlignment(JLabel.RIGHT);
		jlBirthDay.setHorizontalAlignment(JLabel.RIGHT);
		
		jlPhone.setBounds(58, 150, 120, 40);
		jlName.setBounds(58, 200, 120, 40);
		jlBirthDay.setBounds(58, 250, 120, 40);
		
		jPhoneNum.setBounds(180, 150, 250, 50);
		jName.setBounds(180, 200, 250, 50);
		birthDay.setBounds(180, 250, 250, 50);
		
		
		//버튼
		add.setBounds(150, 440, 100, 100);
		exit.setBounds(310, 440, 100, 100);
		
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
		getContentPane().setBackground(new Color(0xECEDFA));
		setVisible(true);
		setBounds(MemberShipDesign.getX()+230, MemberShipDesign.getY()+40, 574,648);
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
