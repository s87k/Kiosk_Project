package kiosk_prj.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kiosk_prj.dao.ShopOpenCloseDAO;
import kiosk_prj.view.ShopCloseCompleteDesign;
import kiosk_prj.view.ShopCloseConfirmDesign;
import kiosk_prj.view.ShopCloseDesign;

public class ShopCloseEvent extends WindowAdapter implements ActionListener {
	
	private ShopCloseDesign scd;
	private ShopCloseConfirmDesign sccd;
	private JLabel mainJlOpenDate;
	
	private JLabel jlTotalCount, jlTotalAmount;
	private JTable jtClose;
	private DefaultTableModel dtmClose;
	private JButton jbExit, jbInsertClose;
	private String OpenDate;
	
	public ShopCloseEvent(ShopCloseDesign scd, ShopCloseConfirmDesign sccd) {
		this.scd = scd;
		this.sccd = sccd;
		
		OpenDate = scd.getMainJlOpenDate().getText().split(":")[1].trim();
	}//ShopCloseEvent
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == scd.getJbInsertClose()) {
			new ShopCloseConfirmDesign(scd,OpenDate);
		}//end if
		if(e.getSource() == scd.getJbExit()) {
			scd.dispose();
		}//end if
		
		if(e.getSource() == sccd.getJbOk()) {
			insertCloseDate(OpenDate);
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
	public void selectDailySettlement() {
		
		jlTotalCount = scd.getJlTotalCount();
		jlTotalAmount = scd.getJlTotalAmount();
		jtClose = scd.getJtClose();
		dtmClose = scd.getDtmClose();
		
		//DAO로 판매정보 가져와야함.
		//셋텍스트어쩌구
		
	}//selectDailySettlement

	public void setSccd(ShopCloseConfirmDesign sccd) {
		this.sccd = sccd;
	}

}//class
