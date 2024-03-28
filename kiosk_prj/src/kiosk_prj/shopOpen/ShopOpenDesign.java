package kiosk_prj.shopOpen;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import kiosk_prj.adminMain.AdminMainPageDesign;

@SuppressWarnings("serial")
public class ShopOpenDesign extends JDialog {
	
	private static final int CAL_WIDTH = 7;
	private final static int CAL_HEIGHT = 6;
	private final String DAY_OF_WEEK_NAME[] = {"SUN", "MON", "TUE", "WED", "THR", "FRI", "SAT"};
	
	private AdminMainPageDesign ampd;
	private JLabel mainJlOpenDate;
	
	private int calDates[][] = new int[CAL_HEIGHT][CAL_WIDTH];
	private int calYear;
	private int calMonth;
	private int calDayOfMon;
	private int calLastDate;
	
	private JButton[] jbWeekOfDay; //요일버튼
	private JButton[][] jbDates; //일부분 버튼
	private JLabel jlSelectedDate; //선택한 일 라벨
	
	private JButton jbSetOpenDate; //개점 설정!!!
	private JButton jbLeftMonth; //달 감소버튼
	private JButton jbRightMonth; //달 증가 버튼
	private JButton jbLeftYear; //년 감소 버튼
	private JButton jbRightYear; //년 증가 버튼
	private JButton jbExit;
	
	private JLabel jlViewYearMonth; //보여지는 달력의 년,달
	private JLabel jlOpenText;
	
	private Calendar today = Calendar.getInstance();
	
	public ShopOpenDesign(AdminMainPageDesign ampd) {
		super(ampd, "개점", true);
		
		mainJlOpenDate = ampd.getJlOpenDate();
		
		//이미지
		ImageIcon imgBackground = new ImageIcon(getClass().getClassLoader().getResource("shopOpen.png"));
		ImageIcon imgL = new ImageIcon(getClass().getClassLoader().getResource("L.png"));
		ImageIcon imgLL = new ImageIcon(getClass().getClassLoader().getResource("LL.png"));
		ImageIcon imgR = new ImageIcon(getClass().getClassLoader().getResource("R.png"));
		ImageIcon imgRR = new ImageIcon(getClass().getClassLoader().getResource("RR.png"));
		ImageIcon imgOpenBt = new ImageIcon(getClass().getClassLoader().getResource("ShopOpenBt.png"));
		ImageIcon imgExit = new ImageIcon(getClass().getClassLoader().getResource("BTexit.png"));
		
		//컴포넌트
		JLabel lbBackground = new JLabel(imgBackground);
		jbSetOpenDate = new JButton(imgOpenBt);
		jbLeftYear = new JButton(imgLL);
		jbRightYear = new JButton(imgRR);
		jbLeftMonth = new JButton(imgL);
		jbRightMonth = new JButton(imgR);
		jbExit = new JButton(imgExit);
		jlOpenText = new JLabel("개점 설정일");
		jbWeekOfDay = new JButton[7];
		jbDates = new JButton[6][7];
		jlViewYearMonth = new JLabel(); //달력의 년, 월 보여주는 라벨
		jlSelectedDate = new JLabel();
		
		//버튼 테두리 삭제ㅠㅠ
		jbSetOpenDate.setBorderPainted(false);
		jbLeftYear.setBorderPainted(false);
		jbRightYear.setBorderPainted(false);
		jbLeftMonth.setBorderPainted(false);
		jbRightMonth.setBorderPainted(false);
		jbExit.setBorderPainted(false);
		
		//오늘의 날짜 설정!
		calYear = today.get(Calendar.YEAR);
		calMonth = today.get(Calendar.MONTH);
		calDayOfMon = today.get(Calendar.DAY_OF_MONTH);
		
		ShopOpenEvent soe = new ShopOpenEvent(this, null);
		soe.insertDate(today);
		
		//날짜값이 필요한 컴포넌트
		jlViewYearMonth.setText(calYear + "년 " + (calMonth + 1) + "월");
		
		if(calMonth<10 && calDayOfMon<10) {
			jlSelectedDate.setText(calYear + "-0" + (calMonth + 1) + "-0" + calDayOfMon);
		}else if(calMonth<10) {
			jlSelectedDate.setText(calYear + "-0" + (calMonth + 1) + "-" + calDayOfMon);
		}else if(calDayOfMon<10) {
			jlSelectedDate.setText(calYear + "-" + (calMonth + 1) + "-0" + calDayOfMon);
		}//end if
		
		JLabel jlToday = new JLabel("Today : " + calYear + "년 " + (calMonth+1) + "월 " + calDayOfMon + "일");
		
		//폰트
		Font defaultFont = new Font("맑은 고딕", Font.PLAIN, 18);
		jlSelectedDate.setFont(new Font("맑은 고딕", Font.BOLD, 50));
		jlOpenText.setFont(new Font("맑은 고딕", Font.BOLD, 35));
		jlOpenText.setForeground(new Color(0x514963));
		jlViewYearMonth.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		jlToday.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		jlToday.setForeground(new Color(0x854355));
		
		//배경색
		jlOpenText.setOpaque(true);
		jlOpenText.setBackground(new Color(0xC2C2C2));
		jlSelectedDate.setOpaque(true);
		jlSelectedDate.setBackground(Color.WHITE);
		
		//달력ㅠㅠ
		JPanel jPanelCalendar = new JPanel();
		jPanelCalendar.setBackground(new Color(0xDCDDE8));
		
		for(int i=0; i<CAL_WIDTH; i++) { //요일 부분 버튼
			jbWeekOfDay[i] = new JButton(DAY_OF_WEEK_NAME[i]);
			jbWeekOfDay[i].setBorderPainted(false);
			jbWeekOfDay[i].setContentAreaFilled(false);
			jbWeekOfDay[i].setForeground(Color.WHITE);
			
			if (i == 0) {
				jbWeekOfDay[i].setBackground(new Color(0xC83232));
			}else if (i == 6) {
				jbWeekOfDay[i].setBackground(new Color(0x3264C8));
			}else {
				jbWeekOfDay[i].setBackground(new Color(0x969696));
			}//end else
			
			jbWeekOfDay[i].setOpaque(true);
			jbWeekOfDay[i].setFocusPainted(false);
			jPanelCalendar.add(jbWeekOfDay[i]);
		}//end for
		
		
		for(int i=0 ; i<CAL_HEIGHT ; i++) { //일 부분 버튼
			for(int j=0 ; j<CAL_WIDTH ; j++) {
				jbDates[i][j] = new JButton();
				jbDates[i][j].setBorderPainted(false);
				jbDates[i][j].setContentAreaFilled(false);
				jbDates[i][j].setBackground(Color.WHITE);
				jbDates[i][j].setOpaque(true);
				jbDates[i][j].addActionListener(soe);
				jPanelCalendar.add(jbDates[i][j]);
				
				jbDates[i][j].setFont(defaultFont);
				
			}//end for
		}//end for
		
		soe.setDate();
		
		
		// 텍스트 중앙에 정렬
		jlViewYearMonth.setHorizontalAlignment(JLabel.CENTER);
		jlSelectedDate.setHorizontalAlignment(JLabel.CENTER);
		jlOpenText.setHorizontalAlignment(JLabel.CENTER);
		jlToday.setHorizontalAlignment(JLabel.CENTER);
				
		// 테두리
		jPanelCalendar.setBorder(new LineBorder(Color.GRAY, 1));
//		jlToday.setBorder(new LineBorder(Color.BLACK, 2));
		
		//배치관리자
		jPanelCalendar.setLayout(new GridLayout(0, 7, 2, 2));
		setLayout(null);
		
		//컴포넌트 배치
		jPanelCalendar.setBounds(30,160,620,530);
		jlOpenText.setBounds(665,205,325,70);
		jlToday.setBounds(665,400,325,40);
		jlViewYearMonth.setBounds(220,75,243,70);
		jbLeftYear.setBounds(30,95,90,40);
		jbLeftMonth.setBounds(125,95,90,40);
		jbRightMonth.setBounds(465,95,90,40);
		jbRightYear.setBounds(560,95,90,40);
		jbExit.setBounds(800,10,165,43);
		jlSelectedDate.setBounds(665,275,325,100);
		jbSetOpenDate.setBounds(665,540,325,150);
		lbBackground.setBounds(0,0,1024,768); //제일 나중에
		
		// 컴포넌트 등록
		add(jPanelCalendar);
		add(jlSelectedDate);
		add(jlViewYearMonth);
		add(jbLeftYear); // <<
		add(jbLeftMonth); // <
		add(jbRightMonth); // >
		add(jbRightYear); // >>
		add(jbExit);
		add(jlOpenText);
		add(jbSetOpenDate);
		add(jlToday);
		add(lbBackground); //배경 등록 제일 나중에
		
		//이벤트 등록
		jbLeftYear.addActionListener(soe);
		jbLeftMonth.addActionListener(soe);
		jbRightMonth.addActionListener(soe);
		jbRightYear.addActionListener(soe);
		jbSetOpenDate.addActionListener(soe);
		jbExit.addActionListener(soe);
		// +날짜버튼
		
		
		setSize(1024,768);
		setLocation(ampd.getX(),ampd.getY());
		setResizable(false);//창 크기 변경 불가능
		setVisible(true);
	}//ShopOpenDesign
	
	public JLabel getMainJlOpenDate() {
		return mainJlOpenDate;
	}

	public static int getCalWidth() {
		return CAL_WIDTH;
	}

	public static int getCalHeight() {
		return CAL_HEIGHT;
	}

	public String[] getDAY_OF_WEEK_NAME() {
		return DAY_OF_WEEK_NAME;
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

	public JButton[] getJbWeekOfDay() {
		return jbWeekOfDay;
	}

	public JButton[][] getJbDates() {
		return jbDates;
	}

	public JLabel getJlSelectedDate() {
		return jlSelectedDate;
	}

	public JButton getJbLeftMonth() {
		return jbLeftMonth;
	}

	public JButton getJbRightMonth() {
		return jbRightMonth;
	}

	public JButton getJbLeftYear() {
		return jbLeftYear;
	}

	public JButton getJbRightYear() {
		return jbRightYear;
	}

	public JLabel getJlViewYearMonth() {
		return jlViewYearMonth;
	}

	public JLabel getJlOpenText() {
		return jlOpenText;
	}

	public Calendar getToday() {
		return today;
	}

	public JButton getJbSetOpenDate() {
		return jbSetOpenDate;
	}

	public JButton getJbExit() {
		return jbExit;
	}
}//class
