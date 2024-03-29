package kiosks;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import kiosks.JoinMembershipEvent;
import kiosks.OrderDetailEvent;

@SuppressWarnings("serial")
public class JoinMembershipDesign extends JDialog {

	private PhoneNumDesign pnd;
	private JTextField jtfPhoneNum, jtfName;
	private JComboBox<String> comYear;
	private JComboBox<String> comMonth;
	private JComboBox<String> comDay;
	private JButton cancel, join;
	private Font font;

	public JoinMembershipDesign(PhoneNumDesign pnd, String title) {
		super(pnd, "회원가입");
		setLayout(null);

		// 회원가입 로고 추가
		ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("login_logo.png"));
		Image image = icon.getImage();
		Image scaledImage = image.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		JLabel menuImg = new JLabel(scaledIcon);
		menuImg.setPreferredSize(new Dimension(120, 120));
		menuImg.setBounds(30, 30, 120, 120);

		// 회원가입 라벨
		JLabel jlJoinMembership = new JLabel("회원가입");
		font = jlJoinMembership.getFont();
		jlJoinMembership.setFont(font.deriveFont(Font.PLAIN, 25));
		jlJoinMembership.setBounds(200, 50, 150, 30);

		// 전화번호 추가
		JLabel jlPhoneNum = new JLabel("전화번호*");
		font = jlPhoneNum.getFont();
		jlPhoneNum.setFont(font.deriveFont(Font.PLAIN, 20));
		jlPhoneNum.setForeground(Color.RED);
		jlPhoneNum.setBounds(50, 180, 100, 30);

		jtfPhoneNum = new JTextField(15);
		font = jtfPhoneNum.getFont();
		jtfPhoneNum.setFont(font.deriveFont(Font.PLAIN, 20));
		jtfPhoneNum.setEditable(false);
		jtfPhoneNum.setBounds(50, 230, 200, 50);

		// 생년월일 추가
		JLabel jlBirth = new JLabel("생년월일*");
		font = jlBirth.getFont();
		jlBirth.setFont(font.deriveFont(Font.PLAIN, 20));
		jlBirth.setForeground(Color.RED);
		jlBirth.setBounds(50, 310, 100, 30);

		// 생년월일 콤보 박스
		comYear = new JComboBox<>(getYearArray());
		comMonth = new JComboBox<>(getMonthArray());
		comDay = new JComboBox<>(getDayArray());
		comYear.setSelectedItem("1990");
		
		// 년
		font = comYear.getFont();
		comYear.setFont(font.deriveFont(Font.PLAIN, 20));
		comYear.setBounds(50, 350, 100, 50);
		
		JLabel jlYear = new JLabel("년");
		font = jlYear.getFont();
		jlYear.setFont(font.deriveFont(Font.BOLD, 23));
		jlYear.setBounds(160, 365, 50, 30);
		
		// 월
		font = comMonth.getFont();
		comMonth.setFont(font.deriveFont(Font.PLAIN, 20));
		comMonth.setBounds(230, 350, 70, 50);
		
		JLabel jlMonth = new JLabel("월");
		font = jlMonth.getFont();
		jlMonth.setFont(font.deriveFont(Font.BOLD, 23));
		jlMonth.setBounds(310, 365, 50, 30);
		
		// 일
		font = comDay.getFont();
		comDay.setFont(font.deriveFont(Font.PLAIN, 20));
		comDay.setBounds(380, 350, 70, 50);
		
		JLabel jlDay = new JLabel("일");
		font = jlDay.getFont();
		jlDay.setFont(font.deriveFont(Font.BOLD, 23));
		jlDay.setBounds(460, 365, 50, 30);


		// 이름 추가
		JLabel jlName = new JLabel("이름*");
		font = jlName.getFont();
		jlName.setFont(font.deriveFont(Font.PLAIN, 20));
		jlName.setForeground(Color.RED);
		jlName.setBounds(50, 430, 100, 30);

		jtfName = new JTextField(10);
		font = jtfName.getFont();
		jtfName.setFont(font.deriveFont(Font.PLAIN, 20));
		jtfName.setBounds(50, 470, 100, 50);
		
		//취소 버튼
		cancel = new JButton("취소");
		cancel.setBounds(160, 550, 100, 50);
		
		//회원가입 버튼
		join = new JButton("회원가입");
		join.setBounds(290, 550, 100, 50);
		

		add(menuImg);
		add(jlJoinMembership);
		add(jlPhoneNum);
		add(jtfPhoneNum);
		add(jlBirth);
		add(comYear);
		add(jlYear);
		add(comMonth);
		add(jlMonth);
		add(comDay);
		add(jlDay);
		add(jlName);
		add(jtfName);
		add(cancel);
		add(join);

		// actionListener 추가
		JoinMembershipEvent jme = new JoinMembershipEvent(this, pnd);
		cancel.addActionListener(jme);
		join.addActionListener(jme);

		addWindowListener(jme);

		getContentPane().setBackground(new Color(0xECEDFA));
		setSize(600, 800);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);

	}// JoinMembershipDesign


	private static String[] getMonthArray() {
		String[] months = new String[12];
		for(int i = 0; i < 9; i++) {
			months[i] = "0" + (i+1);
		}
		for(int i = 9; i < 12; i++) {
			months[i] = Integer.toString(i+1);
		}
		return months;
	}

	private static String[] getDayArray() {
		String[] days = new String[31];
		for(int i = 0; i < 9; i++) {
			days[i] = "0" + (i+1);
		}
		for(int i = 9; i < 31; i++) {
			days[i] = Integer.toString(i+1);
		}
		return days;
	}

	private static String[] getYearArray() {
		String[] years = new String[125];
		for(int i = 0; i < years.length; i++) {
			years[i] = Integer.toString(1900+i);
		}
		return years;
	}

	public JTextField getJtfPhoneNum() {
		return jtfPhoneNum;
	}



	public JComboBox<String> getComYear() {
		return comYear;
	}

	public JComboBox<String> getComMonth() {
		return comMonth;
	}

	public JComboBox<String> getComDay() {
		return comDay;
	}

	public JTextField getJtfName() {
		return jtfName;
	}

	public JButton getCancel() {
		return cancel;
	}

	public JButton getJoin() {
		return join;
	}
	

}// class