package kiosk_prj.shopClose;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kiosk_prj.shopOpen.ShopCloseVO;
import kiosk_prj.shopOpen.ShopOpenCloseDAO;

public class ShopCloseEvent extends WindowAdapter implements ActionListener {
	
	private ShopCloseDesign scd;
	private ShopCloseConfirmDesign sccd;
	private JLabel mainJlOpenDate;
	
	private JLabel jlTotalCount, jlTotalAmount;
	private JTable jtClose;
	private DefaultTableModel dtmClose;
	private JButton jbExit, jbInsertClose;
	private String openDate;
	
	public ShopCloseEvent(ShopCloseDesign scd, ShopCloseConfirmDesign sccd) {
		this.scd = scd;
		this.sccd = sccd;
		
		openDate = scd.getMainJlOpenDate().getText().split(":")[1].trim();
		setDailySettlement(openDate);
	}//ShopCloseEvent
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == scd.getJbInsertClose()) {
			new ShopCloseConfirmDesign(scd,openDate);
		}//end if
		if(e.getSource() == scd.getJbExit()) {
			scd.dispose();
		}//end if
		
		if(e.getSource() == sccd.getJbOk()) {
			insertCloseDate(openDate);
		}//end if
		if(e.getSource() == sccd.getJbNo()) {
			sccd.dispose();
		}//end if
		
	}//actionPerformed

	@Override
	public void windowClosing(WindowEvent e) {
		scd.dispose();
	}//windowClosing
	
	/**
	 * 마감일자를 insert하는 method
	 */
	private void insertCloseDate(String openDate) {
		
		ShopOpenCloseDAO soDAO = ShopOpenCloseDAO.getInstance();
		try {
			soDAO.insertCloseDate(openDate);
			new ShopCloseCompleteDesign(scd, openDate);
			System.out.println("마감일 기준의 일 정산 정보 출력~~~~~");
			sccd.dispose();
			scd.dispose();
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
	}//insertClose
	
	/**
	 * 다이얼로그 창을 띄울 때, JTable에 영업일의 총 판매 기록을 보여주는 method
	 */
	public void setDailySettlement(String openDate) {
		
		jlTotalCount = scd.getJlTotalCount();
		jlTotalAmount = scd.getJlTotalAmount();
		jtClose = scd.getJtClose();
		dtmClose = scd.getDtmClose();
		
		//DAO로 판매정보 가져와야함.
		ShopOpenCloseDAO soDAO = ShopOpenCloseDAO.getInstance();
		try {
			List<ShopCloseVO> list = soDAO.selectDailySettlemnet(openDate);
			
			if(list.isEmpty()) {
				JOptionPane.showMessageDialog(scd, "[" + openDate
							+ "] 해당 날짜에 대한 매출정보가 존재하지 않습니다.");
			}//end if
			
			dtmClose.setRowCount(0);
			
			String[] rowData = new String[4];
			ShopCloseVO scVO = list.get(0);
			String lastMenuName = scVO.getMenuName();
			String lastMenuType = scVO.getMenuType();
			int amount = 0;
			int totalAmount = 0;
			int count = 0;
			int totalCount = 0;
			
			for(int i=0 ; i<list.size() ; i++) {
				scVO = list.get(i);
				
				if(!scVO.getMenuName().equals(lastMenuName)) { //목록에 없는 메뉴라면
					
					if(!scVO.getMenuType().equals(lastMenuType)) { //목록에 없는 종류라면
						rowData[0] = "";
					}else {
						rowData[0] = lastMenuType;
					}//end else
					rowData[1] = lastMenuName;
					rowData[2] = String.valueOf(count);
					rowData[3] = String.valueOf(amount);
					
					//Model객체의 행으로 등록
					dtmClose.addRow(rowData);
					
					lastMenuName = scVO.getMenuName();
					lastMenuType = scVO.getMenuType();
					count = 1;
					totalCount++;
					amount = scVO.getAmount();
					totalAmount += scVO.getAmount();
				}else { //목록에 이미 추가되어있는 메뉴라면
					count++;
					amount += scVO.getAmount();
					totalCount++;
					totalAmount += scVO.getAmount();
				}//end else
				
			}//end for
			
			//마지막꺼 테이블에 올리기~
			if(dtmClose.getRowCount()>0) {
				lastMenuType = dtmClose.getValueAt(dtmClose.getRowCount()-1, 0).toString();
				if(!scVO.getMenuType().equals(lastMenuType)) {
					rowData[0] = scVO.getMenuType();
				}else {
					rowData[0] = "";
				}//end else
			}else {
				rowData[0] = scVO.getMenuType();
			}//end else
			
			rowData[1] = scVO.getMenuName();
			rowData[2] = String.valueOf(count);
			rowData[3] = String.valueOf(amount);
			dtmClose.addRow(rowData);
			
			DecimalFormat df = new DecimalFormat("#,###,###");
			
			jlTotalCount.setText(totalCount+"잔 ");
			jlTotalAmount.setText(" " + df.format(totalAmount) + "원");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
	}//selectDailySettlement

	public void setSccd(ShopCloseConfirmDesign sccd) {
		this.sccd = sccd;
	}

}//class
