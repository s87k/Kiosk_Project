package kiosk_prj.settlement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class SettlementEvent extends WindowAdapter implements ActionListener {
	
	private SettlementDesign smd;
	
	private static final int CAL_WIDTH = 7;
	private final static int CAL_HEIGHT = 6;
	
	private int calYear;
	private int calMonth;
	private int calDayOfMon;
	private int calLastDate;
	private final int calLastDateOfMonth[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	
	private int calDates[][];
	private Calendar cal;
	
	private JTextArea[][] jtaDates; //일 부분
	private JTextArea jtaMonthly; //월 매출 합산
	private JButton jbLeftMonth; //달 감소버튼
	private JButton jbRightMonth; //달 증가 버튼
	private JButton jbSetPeriod; //기간설정버튼
	private JButton jbExit;
	private JLabel jlViewYearMonth; //달력의 현재 년,월 라벨
	
	public SettlementEvent(SettlementDesign smd) {
		this.smd = smd;
		
		calYear = smd.getCalYear();
		calMonth = smd.getCalMonth();
		calDayOfMon = smd.getCalDayOfMon();
		calLastDate = smd.getCalLastDate();
		calDates = smd.getCalDates();
		
		jtaDates = smd.getJtaDates();
		jtaMonthly = smd.getJtaMonthly();
		jbLeftMonth = smd.getJbLeftMonth();
		jbRightMonth = smd.getJbRightMonth();
		jbSetPeriod = smd.getJbSetPeriod();
		jbExit = smd.getJbExit();
		jlViewYearMonth = smd.getJlViewYearMonth();
		
	}//SettlementEvent
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == jbExit) {
			smd.dispose();
		}//end if
		if(e.getSource() == jbLeftMonth) {
			moveMonth(-1);
		}//end if
		if(e.getSource() == jbRightMonth) {
			moveMonth(1);
		}//end if
	}//actionPerformed

	@Override
	public void windowClosing(WindowEvent e) {
		smd.dispose();
	}//windowClosing
	
	/**
	 * 현재달로 부터 n달 전후를 받아 달력 배열을 만드는 함수<br>
	 * (1년은 +12, -12달로 이동 가능)
	 * @param mon
	 */
	public void moveMonth(int mon) {
		calMonth += mon;
		if (calMonth > 11) {
			while (calMonth > 11) {
				calYear++;
				calMonth -= 12;
			}//end while
		}else if (calMonth < 0) {
			while (calMonth < 0) {
				calYear--;
				calMonth += 12;
			}//end while
		}//end if
		
		cal = new GregorianCalendar(calYear, calMonth, calDayOfMon); //오늘날짜로 설정함
		if(calMonth<9) {
			jlViewYearMonth.setText(calYear + ".0" + (calMonth + 1) + ".");
		}else {
			jlViewYearMonth.setText(calYear + "." + (calMonth + 1) + ".");
		}//end else
		insertDate(cal); //달력배열에 다시 날짜를 세팅함
		setDate(); //달력 배열로 날짜 버튼을 보여주기/숨기기
	}//moveMonth
	
	/**
	 * 1일의 위치와 마지막 날짜를 구하는 method
	 * @param cal
	 */
	public void insertDate (Calendar cal) {
		int calStartingPos = (cal.get(Calendar.DAY_OF_WEEK) + 7 - (cal.get(Calendar.DAY_OF_MONTH)) % 7) % 7;
		if (calMonth == 1) {
			calLastDate = calLastDateOfMonth[calMonth] + leapCheck(calYear);
		} else {
			calLastDate = calLastDateOfMonth[calMonth];
		}//end else
		
		// 달력 배열 초기화
		for (int i = 0; i < CAL_HEIGHT; i++) {
			for (int j = 0; j < CAL_WIDTH; j++) {
				calDates[i][j] = 0;
			}//end for
		}//end for
		
		// 달력 배열에 값 채워넣기
		for (int i = 0, num = 1, k = 0; i < CAL_HEIGHT; i++) {
			if (i == 0) {
				k = calStartingPos;
			} else {
				k = 0;
			}//end if
			
			for (int j = k; j < CAL_WIDTH; j++) {
				if (num <= calLastDate)
					calDates[i][j] = num++;
			}//end for
		}//end for
		
	}//insertDate

	/**
	 * 윤년 체크 method
	 * @param year
	 * @return
	 */
	private int leapCheck(int year) { // 윤년인지 확인하는 함수
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
			return 1;
		}else {
			return 0;
		}//end else
	}//leapCheck
	
	/**
	 * 달력 일 버튼부분에 텍스트를 세팅해주는 method
	 */
	public void setDate() {
		for(int i=0 ; i<CAL_HEIGHT ; i++) { //일 부분 버튼
			for(int j=0 ; j<CAL_WIDTH ; j++) {
				
				jtaDates[i][j].setText(calDates[i][j] + "\n10,000");
				jtaDates[i][j].removeAll();
		
				if (calDates[i][j] == 0) {
					jtaDates[i][j].setVisible(false);
				} else {
					jtaDates[i][j].setVisible(true);
				}//else
			}//end for
		}//end for
	}//setDate

	
}//class
