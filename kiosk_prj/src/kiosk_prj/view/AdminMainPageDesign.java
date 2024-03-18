package kiosk_prj.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
	private JPanel jpSalesPanel,		//
					jpOrderStatusPanel,	//주문현황
					jpCouponPanel;		//쿠폰관리
	
	private DefaultTableModel dtmStatus;
	
	public AdminMainPageDesign() {
		super("main");
		
		//이미지
		
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
		jspJtOrderResult.setBounds(60,50,500,600);
		
		int buttonX1 = 600;
		int buttonX2 = 780;
		
		arrJbMenuFilter[0].setBounds(buttonX1,50,170,60);	//Coffee
		arrJbMenuFilter[1].setBounds(buttonX2,50,170,60);	//Non Coffee
		arrJbMenuFilter[2].setBounds(buttonX1,120,170,60);	//Tea
		arrJbMenuFilter[3].setBounds(buttonX2,120,170,60);	//Smoothie
		arrJbMenuFilter[4].setBounds(buttonX1,190,170,60);	//전체
		
		jbMeniInfo.setBounds(buttonX2,190,170,60);
		jbOrderStatus.setBounds(buttonX1,260,170,60);
		jbSales.setBounds(buttonX2,260,170,60);
		jbOpen.setBounds(buttonX1,330,170,60);
		jbClosd.setBounds(buttonX2,330,170,60);
		jbUserManagement.setBounds(buttonX1,400,170,60);
		jbOperate.setBounds(buttonX2,400,170,60);
		jbCoupon.setBounds(buttonX1,470,170,60);
		jbTrends.setBounds(buttonX2,470,170,60);
		
		
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
		
		
		//배경 색
		Color backgroundColor = new Color(0xECEDFA);
		getContentPane().setBackground(backgroundColor);
		
		//이벤트 등록
		AdminMainPageEvent le = new AdminMainPageEvent(this);
		
		jbUserManagement.addActionListener(le);
		
		
		
		
		//기타 설정 등등등드읃으등ㄷ
		setVisible(true);
		setResizable(false);//창 크기 변경 불가능
		setBounds(455,130,1024,768);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//AdminMainPageDesign

	public JTable getJtStatus() {
		return jtStatus;
	}

	public void setJtStatus(JTable jtStatus) {
		this.jtStatus = jtStatus;
	}

	public JButton[] getArrJbMenuFilter() {
		return arrJbMenuFilter;
	}

	public void setArrJbMenuFilter(JButton[] arrJbMenuFilter) {
		this.arrJbMenuFilter = arrJbMenuFilter;
	}

	public JButton getJbMeniInfo() {
		return jbMeniInfo;
	}

	public void setJbMeniInfo(JButton jbMeniInfo) {
		this.jbMeniInfo = jbMeniInfo;
	}

	public JButton getJbOrderStatus() {
		return jbOrderStatus;
	}

	public void setJbOrderStatus(JButton jbOrderStatus) {
		this.jbOrderStatus = jbOrderStatus;
	}

	public JButton getJbSales() {
		return jbSales;
	}

	public void setJbSales(JButton jbSales) {
		this.jbSales = jbSales;
	}

	public JButton getJbOpen() {
		return jbOpen;
	}

	public void setJbOpen(JButton jbOpen) {
		this.jbOpen = jbOpen;
	}

	public JButton getJbClosd() {
		return jbClosd;
	}

	public void setJbClosd(JButton jbClosd) {
		this.jbClosd = jbClosd;
	}

	public JButton getJbUserManagement() {
		return jbUserManagement;
	}

	public void setJbUserManagement(JButton jbUserManagement) {
		this.jbUserManagement = jbUserManagement;
	}

	public JButton getJbOperate() {
		return jbOperate;
	}

	public void setJbOperate(JButton jbOperate) {
		this.jbOperate = jbOperate;
	}

	public JButton getJbCoupon() {
		return jbCoupon;
	}

	public void setJbCoupon(JButton jbCoupon) {
		this.jbCoupon = jbCoupon;
	}

	public JButton getJbTrends() {
		return jbTrends;
	}

	public void setJbTrends(JButton jbTrends) {
		this.jbTrends = jbTrends;
	}

	public JPanel getJpSalesPanel() {
		return jpSalesPanel;
	}

	public void setJpSalesPanel(JPanel jpSalesPanel) {
		this.jpSalesPanel = jpSalesPanel;
	}

	public JPanel getJpOrderStatusPanel() {
		return jpOrderStatusPanel;
	}

	public void setJpOrderStatusPanel(JPanel jpOrderStatusPanel) {
		this.jpOrderStatusPanel = jpOrderStatusPanel;
	}

	public JPanel getJpCouponPanel() {
		return jpCouponPanel;
	}

	public void setJpCouponPanel(JPanel jpCouponPanel) {
		this.jpCouponPanel = jpCouponPanel;
	}

	public DefaultTableModel getDtmStatus() {
		return dtmStatus;
	}

	public void setDtmStatus(DefaultTableModel dtmStatus) {
		this.dtmStatus = dtmStatus;
	}



	
	
}//class
