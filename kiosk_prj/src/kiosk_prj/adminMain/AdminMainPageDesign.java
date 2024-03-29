package kiosk_prj.adminMain;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


@SuppressWarnings("serial")
public class AdminMainPageDesign extends JFrame {

	private JTable jtStatus;
	private JButton[] arrJbMenuFilter;
	private JButton jbMeniInfo, jbOrderStatus, jbSales, jbOpen, jbClosd, jbUserManagement, jbOperate, jbCoupon,
			jbTrends, jbEasterEgg;
	private DefaultTableModel dtmStatus;
	private JLabel jlOpenDate, sumAmount;

	private String adminId;
	
	public AdminMainPageDesign(String adminId) {
		super("main");
		this.adminId = adminId;

		ImageIcon imgBackground = new ImageIcon(getClass().getClassLoader().getResource("main.png"));
		
		//아이콘 이미지
		ImageIcon imgIcon1 = new ImageIcon(getClass().getClassLoader().getResource("mainCoffee.png"));
		ImageIcon imgIcon2 = new ImageIcon(getClass().getClassLoader().getResource("mainNonCoffee.png"));
		ImageIcon imgIcon3 = new ImageIcon(getClass().getClassLoader().getResource("mainTea.png"));
		ImageIcon imgIcon4 = new ImageIcon(getClass().getClassLoader().getResource("mainSmoothie.png"));
		ImageIcon imgIcon5 = new ImageIcon(getClass().getClassLoader().getResource("mainAll.png"));
		
		ImageIcon imgIcon6 = new ImageIcon(getClass().getClassLoader().getResource("mainMenuManage.png"));
		ImageIcon imgIcon7 = new ImageIcon(getClass().getClassLoader().getResource("mainProgress.png"));
		ImageIcon imgIcon8 = new ImageIcon(getClass().getClassLoader().getResource("mainSettlement.png"));
		ImageIcon imgIcon9 = new ImageIcon(getClass().getClassLoader().getResource("mainShopOpen.png"));
		ImageIcon imgIcon10 = new ImageIcon(getClass().getClassLoader().getResource("mainShopClose.png"));
		
		ImageIcon imgIcon11 = new ImageIcon(getClass().getClassLoader().getResource("mainMemberShip.png"));
		ImageIcon imgIcon12 = new ImageIcon(getClass().getClassLoader().getResource("mainKiosk.png"));
		ImageIcon imgIcon13 = new ImageIcon(getClass().getClassLoader().getResource("mainCoupon.png"));
		ImageIcon imgIcon14 = new ImageIcon(getClass().getClassLoader().getResource("mainTrend.png"));
		
		
		// 컴포넌트
		String[] coluumnName = { "종류", "상품명", "가격" };
		dtmStatus = new DefaultTableModel(coluumnName, 0);
		jtStatus = new JTable(dtmStatus);
		JScrollPane jspJtOrderResult = new JScrollPane(jtStatus);

		arrJbMenuFilter = new JButton[5];
		arrJbMenuFilter[0] = new JButton(imgIcon1);
		arrJbMenuFilter[1] = new JButton(imgIcon2);
		arrJbMenuFilter[2] = new JButton(imgIcon3);
		arrJbMenuFilter[3] = new JButton(imgIcon4);
		arrJbMenuFilter[4] = new JButton(imgIcon5);
		jbMeniInfo = new JButton(imgIcon6);
		jbOrderStatus = new JButton(imgIcon7);
		jbSales = new JButton(imgIcon8);
		jbOpen = new JButton(imgIcon9);
		jbClosd = new JButton(imgIcon10);
		jbUserManagement = new JButton(imgIcon11);
		jbOperate = new JButton(imgIcon12);
		jbCoupon = new JButton(imgIcon13);
		jbTrends = new JButton(imgIcon14);
		jbEasterEgg = new JButton();
		JLabel lbBackground = new JLabel(imgBackground);
		jlOpenDate = new JLabel("영업일자 : 개점설정이 필요합니다"); // 영업일자 바뀔때마다 업데이트되어야함.
		sumAmount = new JLabel("오늘 매출 : ");
		// 테이블 컬럼 넓이 변경
		jtStatus.getColumnModel().getColumn(0).setPreferredWidth(150);
		jtStatus.getColumnModel().getColumn(1).setPreferredWidth(200);
		jtStatus.getColumnModel().getColumn(2).setPreferredWidth(150);
		jtStatus.setRowHeight(28);

		// 텍스트 정렬
		jlOpenDate.setHorizontalAlignment(JLabel.RIGHT);

		// 폰트
		Font font = new Font("맑은 고딕", Font.BOLD, 21);
		jtStatus.setFont(font);
		sumAmount.setFont(font);
		jlOpenDate.setFont(font);
		
		// 배치관리자 해제
		setLayout(null);

		// 컴포넌트 배치
		int buttonX1 = 600;
		int buttonX2 = 780;

		jspJtOrderResult.setBounds(60, 90, 500, 500);
		sumAmount.setBounds(60,590, 500, 100);
		
		arrJbMenuFilter[0].setBounds(buttonX1, 90, 170, 60); // Coffee
		arrJbMenuFilter[1].setBounds(buttonX2, 90, 170, 60); // Non Coffee
		arrJbMenuFilter[2].setBounds(buttonX1, 160, 170, 60); // Tea
		arrJbMenuFilter[3].setBounds(buttonX2, 160, 170, 60); // Smoothie
		arrJbMenuFilter[4].setBounds(buttonX1, 230, 170, 60); // 전체
		jbMeniInfo.setBounds(buttonX2, 230, 170, 60);
		jbOrderStatus.setBounds(buttonX1, 300, 170, 60);
		jbSales.setBounds(buttonX2, 300, 170, 60);
		jbOpen.setBounds(buttonX1, 370, 170, 60);
		jbClosd.setBounds(buttonX2, 370, 170, 60);
		jbUserManagement.setBounds(buttonX1, 440, 170, 60);
		jbOperate.setBounds(buttonX2, 440, 170, 60);
		jbCoupon.setBounds(buttonX1, 510, 170, 60);
		jbTrends.setBounds(buttonX2, 510, 170, 60);
		jbEasterEgg.setBounds(0, 0, 50, 50);
		jlOpenDate.setBounds(500, 30, 490, 30);
		lbBackground.setBounds(0, 0, 1024, 768);
		
		// 컴포넌트 등록
		add(jspJtOrderResult);
		add(sumAmount);
		add(arrJbMenuFilter[0]);
		add(arrJbMenuFilter[1]);
		add(arrJbMenuFilter[2]);
		add(arrJbMenuFilter[3]);
		add(arrJbMenuFilter[4]);
		add(jbMeniInfo);
		add(jbOrderStatus);
		add(jbSales);
		add(jbOpen);
		add(jbClosd);
		add(jbUserManagement);
		add(jbOperate);
		add(jbCoupon);
		add(jbTrends);
		add(jbEasterEgg);
		jbEasterEgg.setBorderPainted(false);
		jbEasterEgg.setContentAreaFilled(false);
		add(jlOpenDate);
		add(lbBackground);

		// 이벤트 등록
		
		AdminMainPageEvent ampe = new AdminMainPageEvent(this);
		arrJbMenuFilter[0].addActionListener(ampe); // Coffee
		arrJbMenuFilter[1].addActionListener(ampe); // Non Coffee
		arrJbMenuFilter[2].addActionListener(ampe); // Tea
		arrJbMenuFilter[3].addActionListener(ampe); // Smoothie
		arrJbMenuFilter[4].addActionListener(ampe); // 전체
		jbMeniInfo.addActionListener(ampe);
		jbOrderStatus.addActionListener(ampe);
		jbSales.addActionListener(ampe);
		jbOpen.addActionListener(ampe);
		jbClosd.addActionListener(ampe);
		jbUserManagement.addActionListener(ampe);
		jbOperate.addActionListener(ampe);
		jbCoupon.addActionListener(ampe);
		jbEasterEgg.addActionListener(ampe);
		jbTrends.addActionListener(ampe);

		setVisible(true);
		setSize(1024,768);
		setLocationRelativeTo(null); //모니터 가운데다 띄우는 method
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//Management.addActionListener(le);
	}

	public JButton getJbEasterEgg() {
		return jbEasterEgg;
	}

	public void setJbEasterEgg(JButton jbEasterEgg) {
		this.jbEasterEgg = jbEasterEgg;
	}

	public JLabel getSumAmount() {
		return sumAmount;
	}

	public void setSumAmount(JLabel sumAmount) {
		this.sumAmount = sumAmount;
	}

	public JButton[] getArrJbMenuFilter() {
		return arrJbMenuFilter;
	}

	public JButton getJbMeniInfo() {
		return jbMeniInfo;
	}

	public JButton getJbOrderStatus() {
		return jbOrderStatus;
	}

	public JButton getJbSales() {
		return jbSales;
	}

	public JButton getJbOpen() {
		return jbOpen;
	}

	public JButton getJbClosd() {
		return jbClosd;
	}

	public JButton getJbUserManagement() {
		return jbUserManagement;
	}

	public JButton getJbOperate() {
		return jbOperate;
	}

	public JButton getJbCoupon() {
		return jbCoupon;
	}

	public JButton getJbTrends() {
		return jbTrends;
	}

	public DefaultTableModel getDtmStatus() {
		return dtmStatus;
	}

	public JLabel getJlOpenDate() {
		return jlOpenDate;
	}

	public JTable getJtStatus() {
		return jtStatus;
	}

	public void setJtStatus(JTable jtStatus) {
		this.jtStatus = jtStatus;
	}

	public void setArrJbMenuFilter(JButton[] arrJbMenuFilter) {
		this.arrJbMenuFilter = arrJbMenuFilter;
	}

	public void setJbMeniInfo(JButton jbMeniInfo) {
		this.jbMeniInfo = jbMeniInfo;
	}

	public void setJbOrderStatus(JButton jbOrderStatus) {
		this.jbOrderStatus = jbOrderStatus;
	}

	public void setJbSales(JButton jbSales) {
		this.jbSales = jbSales;
	}

	public void setJbOpen(JButton jbOpen) {
		this.jbOpen = jbOpen;
	}

	public void setJbClosd(JButton jbClosd) {
		this.jbClosd = jbClosd;
	}

	public void setJbUserManagement(JButton jbUserManagement) {
		this.jbUserManagement = jbUserManagement;
	}

	public void setJbOperate(JButton jbOperate) {
		this.jbOperate = jbOperate;
	}

	public void setJbCoupon(JButton jbCoupon) {
		this.jbCoupon = jbCoupon;
	}

	public void setJbTrends(JButton jbTrends) {
		this.jbTrends = jbTrends;
	}

	public void setDtmStatus(DefaultTableModel dtmStatus) {
		this.dtmStatus = dtmStatus;
	}

	public String getAdminId() {
		return adminId;
	}
	
}// class
