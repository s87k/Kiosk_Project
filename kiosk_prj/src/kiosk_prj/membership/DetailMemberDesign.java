package kiosk_prj.membership;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

@SuppressWarnings("serial")
public class DetailMemberDesign extends JDialog {
	private JButton jOrderList, jCouponList, jConsumeTrend;
	private String phoneNum;
	
	public DetailMemberDesign(JDialog MemberShipDesign, String title, String phoneNum) {
		super(MemberShipDesign, "회원 상세", true);
		this.phoneNum = phoneNum;
		
		//이미지
		ImageIcon imgOrderList = new ImageIcon(getClass().getClassLoader().getResource("BTorderlist.png"));
		ImageIcon imgCoupon = new ImageIcon(getClass().getClassLoader().getResource("BTcoupon.png"));
		ImageIcon imgPattern = new ImageIcon(getClass().getClassLoader().getResource("BTpattern.png"));
		
		//컴포넌트
		jOrderList = new JButton(imgOrderList);
		jCouponList = new JButton(imgCoupon);
		jConsumeTrend = new JButton(imgPattern);
		
		//배치 관리자 해제
		setLayout(null);
		
		//컴포넌트 배치
		jOrderList.setBounds(87, 220, 100, 100);
		jCouponList.setBounds(237, 220, 100, 100);
		jConsumeTrend.setBounds(387, 220, 100, 100);
		
		jOrderList.setBorderPainted(false);
		jCouponList.setBorderPainted(false);
		jConsumeTrend.setBorderPainted(false);
		
		jOrderList.setContentAreaFilled(false);
		jCouponList.setContentAreaFilled(false);
		jConsumeTrend.setContentAreaFilled(false);
		
		
		//컴포넌트 추가
		add(jOrderList);
		add(jCouponList);
		add(jConsumeTrend);
		
		//ActionListener 추가
		DetailMemberEvent dme = new DetailMemberEvent(this);
		jOrderList.addActionListener(dme);
		jCouponList.addActionListener(dme);
		jConsumeTrend.addActionListener(dme);
		
		setBounds(MemberShipDesign.getX()+350, MemberShipDesign.getY(), 574,648);
		getContentPane().setBackground(new Color(0xECEDFA));
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public JButton getjOrderList() {
		return jOrderList;
	}

	public void setjOrderList(JButton jOrderList) {
		this.jOrderList = jOrderList;
	}

	public JButton getjCouponList() {
		return jCouponList;
	}

	public void setjCouponList(JButton jCouponList) {
		this.jCouponList = jCouponList;
	}

	public JButton getjConsumeTrend() {
		return jConsumeTrend;
	}

	public void setjConsumeTrend(JButton jConsumeTrend) {
		this.jConsumeTrend = jConsumeTrend;
	}
}
