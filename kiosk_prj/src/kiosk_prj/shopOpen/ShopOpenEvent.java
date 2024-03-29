package kiosk_prj.shopOpen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ShopOpenEvent extends WindowAdapter implements ActionListener {

	private ShopOpenDesign sod;
	private ShopOpenConfirmDesign socd;
	private JLabel mainJlOpenDate;
	
	private final static int CAL_WIDTH = 7;
	private final static int CAL_HEIGHT = 6;
	
	private JButton[][] jbDates; //일부분 버튼
	private JLabel jlSelectedDate; //선택한 일 라벨
	
	private JButton jbSetOpenDate; //년 증가 버튼
	private JButton jbLeftMonth; //달 감소버튼
	private JButton jbRightMonth; //달 증가 버튼
	private JButton jbLeftYear; //년 감소 버튼
	private JButton jbRightYear; //년 증가 버튼
	private JButton jbExit; //년 증가 버튼
	
	private JLabel jlViewYearMonth; //보여지는 달력의 년,달
	
	private int calYear;
	private int calMonth;
	private int calDayOfMon;
	private int calLastDate;
	private final int calLastDateOfMonth[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	
	private int calDates[][];
	private Calendar cal;

	private String openDate;
	
	public ShopOpenEvent(ShopOpenDesign sod, ShopOpenConfirmDesign socd) {
		this.sod = sod;
		
			mainJlOpenDate = sod.getMainJlOpenDate();
			jbDates = sod.getJbDates(); //일부분 버튼
			jlSelectedDate = sod.getJlSelectedDate(); //선택한 일 라벨
			jbSetOpenDate = sod.getJbSetOpenDate(); //개점 설정!!!
			jbLeftMonth = sod.getJbLeftMonth(); //달 감소버튼
			jbRightMonth = sod.getJbRightMonth(); //달 증가 버튼
			jbLeftYear = sod.getJbLeftYear(); //년 감소 버튼
			jbRightYear = sod.getJbRightYear(); //년 증가 버튼
			jbExit = sod.getJbExit();
			jlViewYearMonth = sod.getJlViewYearMonth(); //보여지는 달력의 년,달
			
			calYear = sod.getCalYear();
			calMonth = sod.getCalMonth();
			calDayOfMon = sod.getCalDayOfMon();
			calLastDate = sod.getCalLastDate();
			calDates = sod.getCalDates();
			
	}//ShopOpenEvent
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == jbSetOpenDate) {
			openDate = jlSelectedDate.getText();
			new ShopOpenConfirmDesign(sod, openDate);
		}//end if
		if(e.getSource() == jbLeftMonth) {
			moveMonth(-1);
		}//end if
		if(e.getSource() == jbRightMonth) {
			moveMonth(1);
		}//end if
		if(e.getSource() == jbLeftYear) {
			moveMonth(-12);
		}//end if
		if(e.getSource() == jbRightYear) {
			moveMonth(12);
		}//end if
		if(e.getSource() == jbExit) {
			sod.dispose();
		}//end if
		
		//여기서 for문을 돌려도 속도에 문제는 없을까
		for (int i = 0; i < CAL_HEIGHT; i++) {
			for (int j = 0; j < CAL_WIDTH; j++) {
				if (e.getSource() == jbDates[i][j]) {
					calDayOfMon = calDates[i][j];
					if(calMonth<10 && calDayOfMon<10) {
						jlSelectedDate.setText(calYear + "-0" + (calMonth + 1) + "-0" + calDayOfMon);
					}else if(calMonth<10) {
						jlSelectedDate.setText(calYear + "-0" + (calMonth + 1) + "-" + calDayOfMon);
					}else if(calDayOfMon<10) {
						jlSelectedDate.setText(calYear + "-" + (calMonth + 1) + "-0" + calDayOfMon);
					}//end if
				}//end if
			}//end for
		}//end for
		
		
		if(socd != null) {
			if(e.getSource() == socd.getJbOk()) {
				openDate = jlSelectedDate.getText();
				
				try {
					insertOpenDate(openDate);
				} catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "이미 개점되었던 영업일은 설정할 수 없습니다.");
					return;
				}
				
				new ShopOpenCompleteDesign(sod, openDate);
				socd.dispose();
				sod.dispose();
			}//end if
			if(e.getSource() == socd.getJbNo()) {
				socd.dispose();
			}//end if
		}//end if
		
	}//actionPerformed

	@Override
	public void windowClosing(WindowEvent e) {
		sod.dispose();
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
		jlViewYearMonth.setText(calYear + "년 " + (calMonth + 1) + "월"); //메인페이지의 영업일 라벨에 영업일을 세팅함
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
				String fontColor = "black";
				if (j == 0) {
					fontColor = "red";
				}else if (j == 6) {
					fontColor = "blue";
				}//end if
				
				jbDates[i][j].setText("<html><font color=" + fontColor + ">" + calDates[i][j] + "</font></html>");
				jbDates[i][j].removeAll();
		
				if (calDates[i][j] == 0) {
					jbDates[i][j].setVisible(false);
				} else {
					jbDates[i][j].setVisible(true);
				}//else
			}//end for
		}//end for
	}//setDate
	
	/**
	 * 개점일을 insert하고, 시퀀스를 삭제했다가 추가해서 초기화시키는 method
	 * @throws SQLException 
	 */
	private void insertOpenDate(String openData) throws SQLException {
		
		sod.getMainJlOpenDate().setText("영업일자 : " + openData);
		
		ShopOpenCloseDAO soDAO = ShopOpenCloseDAO.getInstance();
		soDAO.insertOpenDate(openDate);
		soDAO.updateSequence();
		
	}//insertOpenDate

	public void setSocd(ShopOpenConfirmDesign socd) {
		this.socd = socd;
	}
	
}//class
