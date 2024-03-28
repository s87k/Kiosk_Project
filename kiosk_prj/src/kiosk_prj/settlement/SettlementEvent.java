package kiosk_prj.settlement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class SettlementEvent extends WindowAdapter implements ActionListener, KeyListener {
	
	private SettlementDesign smd;
	private SettlementSetDaysDialog smsdDialog;
	private SettlementShowDialog smswDialog;
	
	private static final int CAL_WIDTH = 7;
	private final static int CAL_HEIGHT = 6;
	
	private int calYear;
	private int calMonth;
	private int calDayOfMon;
	private int calLastDate;
	private final int calLastDateOfMonth[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	
	private int calDates[][];
	private Calendar cal;
	
	private JTextPane[][] jtpDates; //일 부분
	private JTextPane jtpMonthly; //월 매출 합산
	private JButton jbLeftMonth; //달 감소버튼
	private JButton jbRightMonth; //달 증가 버튼
	private JButton jbSetPeriod; //기간설정버튼
	private JButton jbExit;
	private JLabel jlViewYearMonth; //달력의 현재 년,월 라벨
	
	private JTextField jtfSetStartDay, jtfSetEndDay;
	private JButton jbOk, jbNo;
	
	public SettlementEvent(SettlementDesign smd) {
		this.smd = smd;
		
		calYear = smd.getCalYear();
		calMonth = smd.getCalMonth();
		calDayOfMon = smd.getCalDayOfMon();
		calLastDate = smd.getCalLastDate();
		calDates = smd.getCalDates();
		
		jtpDates = smd.getJtpDates();
		jtpMonthly = smd.getJtpMonthly();
		jbLeftMonth = smd.getJbLeftMonth();
		jbRightMonth = smd.getJbRightMonth();
		jbSetPeriod = smd.getJbSetPeriod();
		jbExit = smd.getJbExit();
		jlViewYearMonth = smd.getJlViewYearMonth();
	}//SettlementEvent
	
	public SettlementEvent(SettlementSetDaysDialog smsdDialog) {
		this.smsdDialog = smsdDialog;
		
		jtfSetStartDay = smsdDialog.getJtfSetStartDay();
		jtfSetEndDay = smsdDialog.getJtfSetEndDay();
		
		jbOk = smsdDialog.getJbOk();
		jbNo = smsdDialog.getJbNo();
	}//SettlementEvent
	
	public SettlementEvent(SettlementShowDialog smswDialog) {
		this.smswDialog = smswDialog;
		
		try {
			setResult();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
	}//SettlementEvent
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(smd != null) {
			if(e.getSource() == jbExit) {
				smd.dispose();
			}//end if
			if(e.getSource() == jbLeftMonth) {
				moveMonth(-1);
			}//end if
			if(e.getSource() == jbRightMonth) {
				moveMonth(1);
			}//end if
			if(e.getSource() == jbSetPeriod) {
				new SettlementSetDaysDialog(smd);
			}//end if
		}//end if
		
		if(smsdDialog != null) {
			if(e.getSource() == jbOk) {
				verifyInputData();
			}//end if
			if(e.getSource() == jbNo) {
				smsdDialog.dispose();
			}//end if
		}//end if
		
		if(smswDialog != null) {
			if(e.getSource() == smswDialog.getJbExit()) {
				smswDialog.dispose();
			}//end if
		}//end if
	}//actionPerformed

	@Override
	public void windowClosing(WindowEvent e) {
		smd.dispose();
	}//windowClosing
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getSource() == jtfSetStartDay) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				jtfSetEndDay.requestFocus();
			}//end if
		}//end if
		if(e.getSource() == jtfSetEndDay) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				jtfSetStartDay.requestFocus();
				verifyInputData();
			}//end if
		}//end if
		
	}//keyPressed
	
	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
	}
	
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
		SettlementDAO stmDAO = SettlementDAO.getInstance();
		StringBuilder sb = new StringBuilder();
		DecimalFormat df = new DecimalFormat("#,###,###");
		List<Integer> list = null;
		String date = "";
		int amount = 0;
		int totalAmount = 0;
		int count = 0;
		int totalCount = 0;
		
		String fontColor = "";
		
		for(int i=0 ; i<CAL_HEIGHT ; i++) { //일 부분 버튼
			for(int j=0 ; j<CAL_WIDTH ; j++) {
				
				if(calMonth<9 && calDates[i][j]<10) {
					date = calYear + "-0" + (calMonth+1) + "-0" + calDates[i][j];
				}else if(calMonth<9) {
					date = calYear + "-0" + (calMonth+1) + "-" + calDates[i][j];
				}else if(calDates[i][j]<10) {
					date = calYear + "-" + (calMonth+1) + "-0" + calDates[i][j];
				}//end if
				
				try {
					list = stmDAO.selectOneDaySettlement(date);
					if(list!=null) { //해당일의 매출정보가 있다면..!
						for(int k=0 ; k<list.size() ; k++) {
							amount += list.get(k);
							count++;
							totalAmount += list.get(k);
							totalCount++;
							
						}//end for
					}//end if
					
					fontColor = "black";
					if(j == 0) {
						fontColor = "red";
					}else if(j == 6) {
						fontColor = "blue";
					}//end else
					
					sb
					.append("<BODY><b><font color=\"")
					.append(fontColor)
					.append("\" size=\"5\" face=\"맑은 고딕\">") 
					.append(calDates[i][j])
					.append("</font></b><br>")
					.append("<font color=\"#1E1E1E\" size=\"4\" face=\"맑은 고딕\">")
					.append(df.format(amount))
					.append("<br>")
					.append(" (" + count + ")")
					.append("</font></BODY>");
					jtpDates[i][j].setText(sb.toString());
					jtpDates[i][j].removeAll();
					sb.delete(0, sb.length()); //StringBuiler 초기화
					
					if (calDates[i][j] == 0) {
						jtpDates[i][j].setVisible(false);
					} else {
						jtpDates[i][j].setVisible(true);
					}//else
					
					amount = 0;
					count = 0;
					
				} catch (SQLException e) {
					e.printStackTrace();
				}//end catch
				
			}//end for
		}//end for
		
		sb
		.append("<BODY><b><font color=\"#423857\" size=\"6\" face=\"맑은 고딕\">")
		.append("월 매출 합산")
		.append("</font></b><br>")
		.append("<font color=\"#1E1E1E\" size=\"4\" face=\"맑은 고딕\">")
		.append(df.format(totalAmount)).append("원<br>일평균 ")
		.append(df.format(totalAmount/calLastDateOfMonth[calMonth]))
		.append("원<br> ")//일평균
		.append("(" + totalCount + ")");
		jtpMonthly.setText(sb.toString());
		sb.delete(0, sb.length());
	}//setDate
	
	private void verifyInputData() {
		String startDay = jtfSetStartDay.getText();
		String endDay = jtfSetEndDay.getText();
		
		if( !( startDay.matches("(\\d{4})-(\\d{2})-(\\d{2})")
				&& endDay.matches("(\\d{4})-(\\d{2})-(\\d{2})") ) ) {
			JOptionPane.showMessageDialog(null, "\'YYYY-MM-DD\'의 형식으로 입력해주세요");
			return;
		}//end if
		
		try {
			
			SettlementDAO stmDAO = SettlementDAO.getInstance();
			List<SettlementPeriodVO> list = stmDAO.selectSettlementPeriod(startDay, endDay);
			
			if(list.size()<1) {
				JOptionPane.showMessageDialog(null, "입력일의 매출정보가 없거나, 종료일이 시작일보다 빠릅니다.");
				return;
			}//end if
			
			smsdDialog.dispose();
			new SettlementShowDialog(smd, startDay, endDay);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
	}//selectSettlement
	
	public void setResult() throws ParseException, SQLException{
		String startDay = smswDialog.getStartDay();
		String endDay = smswDialog.getEndDay();
		
		SettlementDAO stmDAO = SettlementDAO.getInstance();
		List<SettlementPeriodVO> list = stmDAO.selectSettlementPeriod(startDay, endDay);
		
		//시작일과 종료일의 날짜 차이를 구함.
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = format.parse(startDay);
		Date d2 = format.parse(endDay);
		long Day = ((d2.getTime()-d1.getTime())/1000)/(24*60*60) +1 ;
		
		SettlementPeriodVO smpVO = null;
		Map<String, Integer> mapCountMenu = new HashMap<String, Integer>();
		int totalAmount = 0;
		int totalCount = 0;
		for(int i=0 ; i<list.size() ; i++) {
			smpVO = list.get(i);
			//메뉴이름은 key로 넣었을때, value가 없으면 최초 value로 1을 입력. value는 잔수
			if(mapCountMenu.get(smpVO.getMenuName())==null) {
				mapCountMenu.put(smpVO.getMenuName(), 1); 
			}else {
				mapCountMenu.put(smpVO.getMenuName(), mapCountMenu.get(smpVO.getMenuName())+1); 
			}//end else
			totalAmount += smpVO.getMenuPrice();
			totalCount++;
		}//end for
		
		StringBuilder sb = new StringBuilder();
		DecimalFormat df = new DecimalFormat("#,###,###");
		
		//판매 건수가 2건 이상이면 판매잔수를 비교해야함.
		if(list.size()>1) {
			List<Map.Entry<String, Integer>> tempList = new ArrayList<>(mapCountMenu.entrySet());
			
			Collections.sort(tempList, new Comparator<Map.Entry<String, Integer>>() {
				@Override
				public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
					if(o1.getValue() > o2.getValue()) {
						return -1;
					}else if(o1.getValue() < o2.getValue()){
						return 1;
					}
					return o1.getKey().compareTo(o2.getKey());
				}//compare
			});
			
			sb
			.append("설정기간 총 매출 금액 : ").append(df.format(totalAmount) + "원\n")
			.append("일 평균 : ").append(df.format(totalAmount/(int)Day) + "원\n")
			.append("설정기간 총 판매 잔 수 : ").append("(" + totalCount + ")\n\n");
			
			double percentage = 0;
			Map<String, Integer> mapSorted = new LinkedHashMap<String, Integer>();
			for(Iterator<Map.Entry<String, Integer>> iter = tempList.iterator(); iter.hasNext();) {
				Map.Entry<String, Integer> entry = iter.next();
				mapSorted.put(entry.getKey(), entry.getValue());
				
				percentage = (double)entry.getValue()/totalCount;
				
				sb
				.append(entry.getKey())
				.append(" (" + entry.getValue() + "건, " + String.format("%.1f", percentage*100) +"%)\n");
			}//end for
			smswDialog.getJtaResult().setText(sb.toString());
			
		}else{//판매건수가 1건일때는 비교 안해도 됨.
			
			smpVO = list.get(0);
			String menuName = smpVO.getMenuName();
			int menuAmount = smpVO.getMenuPrice();
			
			sb
			.append("설정기간 총 매출 금액 : ").append(df.format(menuAmount) + "원\n")
			.append("일 평균 : ").append(df.format(menuAmount) + "원\n")
			.append("설정기간 총 판매 잔 수 : ").append("(1)\n\n")
			.append(menuName + " (1건, 100%)");
			
			smswDialog.getJtaResult().setText(sb.toString());
			
		}//end else
		
	}//setResult
	
	public void setSmd(SettlementDesign smd) {
		this.smd = smd;
	}

}//class
