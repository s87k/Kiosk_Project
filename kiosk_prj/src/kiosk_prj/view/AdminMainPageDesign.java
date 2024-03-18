package kiosk_prj.view;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kiosk_prj.controller.AdminMainPageEvent;

@SuppressWarnings("serial")
public class AdminMainPageDesign extends JFrame {
	
	private JTable jtStatus;
	private JButton[] arrJbMenuFilter;
	private JButton jbMeniInfo, jbOrderStatus, jbSales, jbOpen, jbClosd,
					jbUserManagement, jbOperate, jbCoupon, jbTrends;
	
	private DefaultTableModel dtmStatus;
	
	public AdminMainPageDesign() {
		super("main");
		
		//이미지
		ImageIcon imgBackground =
				new ImageIcon("C:/Users/user/git/Kiosk_Project/kiosk_prj/src/kiosk_prj/image/main.png");
//		ImageIcon imgBackground =
//				new ImageIcon("D:/Kiosk_Project/kiosk_prj/src/kiosk_prj/image/main.png");
		
		//컴포넌트
		String[] coluumnName = {"종류","상품명","가격"};
		dtmStatus = new DefaultTableModel(coluumnName,0);
		jtStatus = new JTable(dtmStatus);
		JScrollPane jspJtOrderResult = new JScrollPane(jtStatus);
		
		arrJbMenuFilter = new JButton[5];
		arrJbMenuFilter[0] = new JButton("Coffee");
		arrJbMenuFilter[1] = new JButton("Non Coffee");
		arrJbMenuFilter[2] = new JButton("Tea");
		arrJbMenuFilter[3] = new JButton("Smoothie");
		arrJbMenuFilter[4] = new JButton("전체");
		jbMeniInfo = new JButton("메뉴정보");
		jbOrderStatus = new JButton("주문현황");
		jbSales = new JButton("매출");
		jbOpen = new JButton("개점");
		jbClosd = new JButton("마감");
		jbUserManagement = new JButton("사용자관리");
		jbOperate = new JButton("운영");
		jbCoupon = new JButton("쿠폰 관리");
		jbTrends = new JButton("소비 트렌드");
		JLabel lbBackground = new JLabel(imgBackground);
		
		//테이블 컬럼 넓이 변경
		jtStatus.getColumnModel().getColumn(0).setPreferredWidth(150);
		jtStatus.getColumnModel().getColumn(1).setPreferredWidth(200);
		jtStatus.getColumnModel().getColumn(2).setPreferredWidth(150);
		
		// 텍스트 중앙에 정렬
//		jlId.setHorizontalAlignment(JLabel.CENTER);
		
		//폰트
		Font font = new Font("맑은 고딕", Font.BOLD, 21);
		jtStatus.setFont(font);
		
		//배치관리자 해제
		setLayout(null);
		
		//컴포넌트 배치
		int buttonX1 = 600;
		int buttonX2 = 780;
		
		jspJtOrderResult.setBounds(60,90,500,600);
		arrJbMenuFilter[0].setBounds(buttonX1,90,170,60);	//Coffee
		arrJbMenuFilter[1].setBounds(buttonX2,90,170,60);	//Non Coffee
		arrJbMenuFilter[2].setBounds(buttonX1,160,170,60);	//Tea
		arrJbMenuFilter[3].setBounds(buttonX2,160,170,60);	//Smoothie
		arrJbMenuFilter[4].setBounds(buttonX1,230,170,60);	//전체
		jbMeniInfo.setBounds(buttonX2,230,170,60);
		jbOrderStatus.setBounds(buttonX1,300,170,60);
		jbSales.setBounds(buttonX2,300,170,60);
		jbOpen.setBounds(buttonX1,370,170,60);
		jbClosd.setBounds(buttonX2,370,170,60);
		jbUserManagement.setBounds(buttonX1,440,170,60);
		jbOperate.setBounds(buttonX2,440,170,60);
		jbCoupon.setBounds(buttonX1,510,170,60);
		jbTrends.setBounds(buttonX2,510,170,60);
		lbBackground.setBounds(0,0,1024,768);
		
		//컴포넌트 등록
		add(jspJtOrderResult);
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
		add(lbBackground);
		
		//이벤트 등록

		AdminMainPageEvent ampe = new AdminMainPageEvent(this);
		arrJbMenuFilter[0].addActionListener(ampe);	//Coffee
		arrJbMenuFilter[1].addActionListener(ampe);	//Non Coffee
		arrJbMenuFilter[2].addActionListener(ampe);	//Tea
		arrJbMenuFilter[3].addActionListener(ampe);	//Smoothie
		arrJbMenuFilter[4].addActionListener(ampe);	//전체
		jbMeniInfo.addActionListener(ampe);
		jbOrderStatus.addActionListener(ampe);
		jbSales.addActionListener(ampe);
		jbOpen.addActionListener(ampe);
		jbClosd.addActionListener(ampe);
		jbUserManagement.addActionListener(ampe);
		jbOperate.addActionListener(ampe);
		jbCoupon.addActionListener(ampe);
		jbTrends.addActionListener(ampe);
//Management.addActionListener(le);
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



}//class
