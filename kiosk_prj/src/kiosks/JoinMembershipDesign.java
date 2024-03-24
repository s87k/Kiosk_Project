package kiosks;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import kiosks.JoinMembershipEvent;
import kiosks.OrderDetailEvent;

@SuppressWarnings("serial")
public class JoinMembershipDesign extends JFrame {

	private JTextField jtfPhoneNum, jtfYear, jtfMonth, jtfDay, jtfName;
	private JButton cancel, join;
	private Font font;

	public JoinMembershipDesign() {
		setLayout(null);

		// 회원가입 로고 추가
		ImageIcon icon = new ImageIcon(getClass().getResource("/kiosk_prj/image/login_logo.png"));
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
		jtfPhoneNum.setBounds(50, 230, 200, 50);

		// 생년월일 추가
		JLabel jlBirth = new JLabel("생년월일*");
		font = jlBirth.getFont();
		jlBirth.setFont(font.deriveFont(Font.PLAIN, 20));
		jlBirth.setForeground(Color.RED);
		jlBirth.setBounds(50, 310, 100, 30);

		// 년
		jtfYear = new JTextField(4);
		font = jtfYear.getFont();
		jtfYear.setFont(font.deriveFont(Font.PLAIN, 20));
		jtfYear.setBounds(50, 350, 100, 50);
		
		JLabel jlYear = new JLabel("년");
		font = jlYear.getFont();
		jlYear.setFont(font.deriveFont(Font.BOLD, 23));
		jlYear.setBounds(160, 365, 50, 30);
		
		// 월
		jtfMonth = new JTextField(2);
		font = jtfMonth.getFont();
		jtfMonth.setFont(font.deriveFont(Font.PLAIN, 20));
		jtfMonth.setBounds(230, 350, 70, 50);
		
		JLabel jlMonth = new JLabel("월");
		font = jlMonth.getFont();
		jlMonth.setFont(font.deriveFont(Font.BOLD, 23));
		jlMonth.setBounds(310, 365, 50, 30);
		
		// 일
		jtfDay = new JTextField(2);
		font = jtfDay.getFont();
		jtfDay.setFont(font.deriveFont(Font.PLAIN, 20));
		jtfDay.setBounds(380, 350, 70, 50);
		
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
		add(jtfYear);
		add(jlYear);
		add(jtfMonth);
		add(jlMonth);
		add(jtfDay);
		add(jlDay);
		add(jlName);
		add(jtfName);
		add(cancel);
		add(join);

		// actionListener 추가
		JoinMembershipEvent jme = new JoinMembershipEvent(this);
		cancel.addActionListener(jme);
		join.addActionListener(jme);

		addWindowListener(jme);

		setSize(600, 800);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);

	}// JoinMembershipDesign

	public static void main(String[] args) {
		new JoinMembershipDesign();
	}// main

	public JTextField getJtfPhoneNum() {
		return jtfPhoneNum;
	}

	public JTextField getJtfYear() {
		return jtfYear;
	}

	public JTextField getJtfMonth() {
		return jtfMonth;
	}

	public JTextField getJtfDay() {
		return jtfDay;
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