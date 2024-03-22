package kiosk_prj.settlement;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import kiosk_prj.adminMain.AdminMainPageDesign;

@SuppressWarnings("serial")
public class SettlementDesign extends JDialog {
	
	private static final int CAL_WIDTH = 7;
	private final static int CAL_HEIGHT = 6;
	
	private AdminMainPageDesign ampd;
	
	private int calDates[][] = new int[CAL_HEIGHT][CAL_WIDTH];
	private int calYear;
	private int calMonth;
	private int calDayOfMon;
	private int calLastDate;
	
	private JTextArea[][] jtaDates; //일 부분
	private JTextArea jtaMonthly; //월 매출 합산
	private JButton jbLeftMonth; //달 감소버튼
	private JButton jbRightMonth; //달 증가 버튼
	private JButton jbSetPeriod; //기간설정버튼
	private JButton jbExit;
	
	private JLabel jlViewYearMonth; //달력의 현재 년,월 라벨
	private JLabel jlInquiryYearMonth;//조회 년월 라벨
	
	private Calendar today = Calendar.getInstance();
	
	public SettlementDesign(AdminMainPageDesign ampd) {
		super(ampd, "매출 정보", true);
		
		//이미지
		ImageIcon imgBackground = new ImageIcon("kiosk_prj/src/kiosk_prj/image/settlement.png");
		ImageIcon imgExit = new ImageIcon("kiosk_prj/src/kiosk_prj/image/BTexit.png");
		ImageIcon imgL = new ImageIcon("kiosk_prj/src/kiosk_prj/image/L.png");
		ImageIcon imgR = new ImageIcon("kiosk_prj/src/kiosk_prj/image/R.png");
		
		//컴포넌트
		JLabel lbBackground = new JLabel(imgBackground);
		jbExit = new JButton(imgExit);
		jbLeftMonth = new JButton(imgL);
		jbRightMonth = new JButton(imgR);
		jbSetPeriod = new JButton("설정기간 내 매출 조회");
		jlInquiryYearMonth = new JLabel("조회 년월 : ");
		jlViewYearMonth = new JLabel("24.03.");
		jtaDates = new JTextArea[6][7];
		jtaMonthly = new JTextArea("월 매출 합산");
	
		//버튼 테두리 삭제ㅠㅠ
		jbExit.setBorderPainted(false);
		jbLeftMonth.setBorderPainted(false);
		jbRightMonth.setBorderPainted(false);
		jbSetPeriod.setBorderPainted(false);
		
		jbLeftMonth.setContentAreaFilled(false);
		jbRightMonth.setContentAreaFilled(false);
		
		//오늘의 날짜 설정!
		calYear = today.get(Calendar.YEAR);
		calMonth = today.get(Calendar.MONTH);
		calDayOfMon = today.get(Calendar.DAY_OF_MONTH);
		
		SettlementEvent sme = new SettlementEvent(this);
		sme.insertDate(today);
		
		//날짜값이 필요한 컴포넌트
		jlViewYearMonth.setText(calYear + "." + (calMonth + 1) + ".");

		if(calMonth<10) {
			jlViewYearMonth.setText(calYear + ".0" + (calMonth + 1));
		}//end if
		
		JLabel jlToday = new JLabel("Today : " + calYear + "년 " + (calMonth+1) + "월 " + calDayOfMon + "일");

		//폰트
		Font defaultFont = new Font("맑은 고딕", Font.BOLD, 14);
		Font bigFont = new Font("맑은 고딕", Font.BOLD, 30);
		jlViewYearMonth.setFont(bigFont);
		jlInquiryYearMonth.setFont(bigFont);
		jlToday.setFont(defaultFont);
		
		//텍스트 정렬
		jlToday.setHorizontalAlignment(JLabel.RIGHT);
		
		//배경색
		jlViewYearMonth.setOpaque(true);
		jlViewYearMonth.setBackground(new Color(0xDCDDE8));
		
		//달력ㅠㅠ
		JPanel jPanelCalendar = new JPanel();
		jPanelCalendar.setBackground(new Color(0xDCDDE8));
		
		for(int i=0 ; i<CAL_HEIGHT ; i++) { //일 부분 버튼
			for(int j=0 ; j<CAL_WIDTH ; j++) {
				jtaDates[i][j] = new JTextArea();
				jtaDates[i][j].setEditable(false);
				jtaDates[i][j].setBackground(Color.WHITE);
				jtaDates[i][j].setOpaque(true);
				jPanelCalendar.add(jtaDates[i][j]);
				
				jtaDates[i][j].setFont(defaultFont);
			}//end for
		}//end for
		sme.setDate();
		
		// 텍스트 중앙에 정렬
		jlViewYearMonth.setHorizontalAlignment(JLabel.CENTER);
		
		// 테두리
		jPanelCalendar.setBorder(new LineBorder(Color.GRAY, 1));
		
		//배치관리자
		jPanelCalendar.setLayout(new GridLayout(0, 7, 2, 2));
		setLayout(null);
		
		//컴포넌트 배치
		jPanelCalendar.setBounds(30,140,945,440);
		jbExit.setBounds(800,10,165,43);
		jbLeftMonth.setBounds(200,70,65,65);
		jbRightMonth.setBounds(395,70,65,65);
		jbSetPeriod.setBounds(30,600,200,105);
		jlInquiryYearMonth.setBounds(32,70,200,65);
		jlViewYearMonth.setBounds(270,80,120,45);
		jtaMonthly.setBounds(730,600,245,105);
		jlToday.setBounds(565,18,220,35);
		lbBackground.setBounds(0,0,1024,768);
		
		
		// 컴포넌트 등록
		add(jPanelCalendar);
		add(jbExit);
		add(jbLeftMonth);
		add(jbRightMonth);
		add(jbSetPeriod);
		add(jlInquiryYearMonth);
		add(jlViewYearMonth);
		add(jtaMonthly);
		add(jlToday);
		add(lbBackground);
		
		//이벤트 등록
		jbExit.addActionListener(sme);
		jbLeftMonth.addActionListener(sme);
		jbRightMonth.addActionListener(sme);
		jbSetPeriod.addActionListener(sme);
		
		setSize(1024,768);
		setLocation(ampd.getX(),ampd.getY());
		setResizable(false);//창 크기 변경 불가능
		setVisible(true);
	}//SettlementDesign

	public static int getCalWidth() {
		return CAL_WIDTH;
	}

	public static int getCalHeight() {
		return CAL_HEIGHT;
	}

	public AdminMainPageDesign getAmpd() {
		return ampd;
	}

	public int[][] getCalDates() {
		return calDates;
	}

	public int getCalYear() {
		return calYear;
	}

	public int getCalMonth() {
		return calMonth;
	}

	public int getCalDayOfMon() {
		return calDayOfMon;
	}

	public int getCalLastDate() {
		return calLastDate;
	}

	public JTextArea[][] getJtaDates() {
		return jtaDates;
	}

	public JTextArea getJtaMonthly() {
		return jtaMonthly;
	}

	public JButton getJbLeftMonth() {
		return jbLeftMonth;
	}

	public JButton getJbRightMonth() {
		return jbRightMonth;
	}

	public JButton getJbSetPeriod() {
		return jbSetPeriod;
	}

	public JButton getJbExit() {
		return jbExit;
	}

	public JLabel getJlViewYearMonth() {
		return jlViewYearMonth;
	}

	public JLabel getJlInquiryYearMonth() {
		return jlInquiryYearMonth;
	}

	public Calendar getToday() {
		return today;
	}
	
}//class
