package kiosk_prj.adminMain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import kiosk_prj.coupon.ManageCouponDesign;
import kiosk_prj.membership.MemberShipDesign;
import kiosk_prj.orderStatus.OrderStatusDesign;
import kiosk_prj.settlement.SettlementDesign;
import kiosk_prj.shopClose.ShopCloseDesign;
import kiosk_prj.shopOpen.ShopOpenDesign;


public class AdminMainPageEvent extends WindowAdapter implements ActionListener, MouseListener {
	
	private AdminMainPageDesign amod;
	private JButton jbCoffee, jbNonCoffee, jbTea, jbSmoothie, jbAll,
				jbMeniInfo, jbOrderStatus, jbSales, jbOpen, jbClosd,
				jbUserManagement, jbOperate, jbCoupon, jbTrends;
	private JLabel jlOpenDate;
	
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
		
	}//AdminMainPageEvent

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == jbCoffee) {
			System.out.println("ㅁㄴㅇㄻㄴㅇㄹ");
		}//end if
		if(e.getSource() == jbOrderStatus) {
			OrderStatusDialog();
		}//end if
		if(e.getSource() == jbOpen) {
			new ShopOpenDesign(amod);
		}//end if
		if(e.getSource() == jbUserManagement) {
			new MemberShipDesign(amod, null);
		}//end if
		if(e.getSource() == jbClosd) {
			int openDate = jlOpenDate.getText().length();
			if(openDate>17) {
				JOptionPane.showMessageDialog(null, "마감은 개점설정 이전에 사용할 수 없습니다.");
				return;
			}//end if
			new ShopCloseDesign(amod);
		}//end if
		if(e.getSource() == jbSales) {
			new SettlementDesign(amod);
		}//end if
	
		if(e.getSource() == jbCoupon) {
			new ManageCouponDesign(amod, null);
		}//end if
	}//actionPerformed
	
	public void OrderStatusDialog() {
		new OrderStatusDesign(amod);
	}//OrderStatusDialog
	
	@Override
	public void windowClosing(WindowEvent e) {
		amod.dispose();
	}//windowClosing

	@Override
	public void mouseClicked(MouseEvent e) {
		//jtable용?
		
		
	}//mouseClicked
	
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

}//class
