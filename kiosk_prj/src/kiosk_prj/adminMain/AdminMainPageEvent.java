package kiosk_prj.adminMain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import kiosk_prj.coupon.ManageCouponDesign;
import kiosk_prj.membership.MemberShipDesign;
import kiosk_prj.orderStatus.OrderStatusDesign;
import kiosk_prj.settlement.SettlementDesign;
import kiosk_prj.shopClose.ShopCloseDesign;
import kiosk_prj.shopOpen.ShopOpenDesign;

public class AdminMainPageEvent extends WindowAdapter implements ActionListener, MouseListener {

	private AdminMainPageDesign amod;
	private JButton jbCoffee, jbNonCoffee, jbTea, jbSmoothie, jbAll, jbMeniInfo, jbOrderStatus, jbSales, jbOpen,
			jbClosd, jbUserManagement, jbOperate, jbCoupon, jbTrends;
	private JLabel jlOpenDate;
	private String openDate;
	private String dateOnly;

	public AdminMainPageEvent(AdminMainPageDesign amod) {
		this.amod = amod;
		
		
		JButton jbArr[] = amod.getArrJbMenuFilter();
		jbCoffee = jbArr[0];
		jbNonCoffee = jbArr[1];
		jbTea = jbArr[2];
		jbSmoothie = jbArr[3];
		jbAll = jbArr[4];

		jbMeniInfo = amod.getJbMeniInfo();
		jbOrderStatus = amod.getJbOrderStatus();
		jbSales = amod.getJbSales();
		jbOpen = amod.getJbOpen();
		jbClosd = amod.getJbClosd();
		jbUserManagement = amod.getJbUserManagement();
		jbOperate = amod.getJbOperate();
		jbCoupon = amod.getJbCoupon();
		jbTrends = amod.getJbTrends();
		jlOpenDate = amod.getJlOpenDate();

	}// AdminMainPageEvent

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbCoffee) {
			System.out.println("agagag");
		} // end if
		if (e.getSource() == jbOrderStatus) {
			OrderStatusDialog();
		} // end if
		if (e.getSource() == jbOpen) {
			openDate = amod.getJlOpenDate().getText();
			dateOnly = openDate.substring(9);
			
			new ShopOpenDesign(amod);
			currentOrderStatus(dateOnly);
			sumAmount(dateOnly);
			
		} // end if
		if (e.getSource() == jbUserManagement) {
			new MemberShipDesign(amod, null);
		} // end if
		if (e.getSource() == jbClosd) {
			int openDate = jlOpenDate.getText().length();
			if (openDate > 17) {
				JOptionPane.showMessageDialog(null, "마감은 개점설정 이전에 사용할 수 없습니다.");
				return;
			} // end if
			new ShopCloseDesign(amod);
		} // end if
		if (e.getSource() == jbSales) {
			new SettlementDesign(amod);
		} // end if

		if (e.getSource() == jbCoupon) {
			new ManageCouponDesign(amod, null);
		} // end if
	}// actionPerformed

	public void currentOrderStatus(String dateOnly) {
		openDate = jlOpenDate.getText();
		dateOnly = openDate.substring(7);
		
		currentOrderDAO coDAO = currentOrderDAO.getInstance();

		try {
			List<currentOrderVO> list = coDAO.searchCurrentOrder(dateOnly);
			
			DefaultTableModel dtm = amod.getDtmStatus();
			dtm.setRowCount(0);

			String[] rowData = null;
			currentOrderVO coVO = null;

			for (int i = 0; i < list.size(); i++) {
				coVO = list.get(i);
				rowData = new String[3];
				rowData[0] = coVO.getMenuType();
				rowData[1] = coVO.getMenuName();
				rowData[2] = "" + coVO.getAmount();

				dtm.addRow(rowData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void sumAmount(String dateOnly) {
		openDate = jlOpenDate.getText(); 
		dateOnly = openDate.substring(7);
		currentOrderDAO coDAO = currentOrderDAO.getInstance();
		
		try {
			currentOrderVO coVO = null;
			
			
			coVO = coDAO.sumAmount(dateOnly);
			
			amod.getSumAmount().setText("오늘 매출: " + coVO.getAmount());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void OrderStatusDialog() {
		new OrderStatusDesign(amod);
	}// OrderStatusDialog

	@Override
	public void windowClosing(WindowEvent e) {
		amod.dispose();
	}// windowClosing

	@Override
	public void mouseClicked(MouseEvent e) {
		// jtable용?

	}// mouseClicked

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}// class
