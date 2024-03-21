package kiosk_prj.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kiosk_prj.view.OrderDesign;
import kiosk_prj.view.OrderDetailDesgin;
import kiosk_prj.view.PaymentPageDesign;

public class OrderEvent extends WindowAdapter implements ActionListener, MouseListener{

	private OrderDesign od;
	
	private JTable cartList;
	private DefaultTableModel dtmCartList;
	
	public OrderEvent(OrderDesign od) {
		this.od = od;
		
		cartList = od.getCartList();
		dtmCartList = od.getDtmCartList();
	}//OrderEvent
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == od.getEspresso()) {
			System.out.println("에스프레소");
		} 
		if ((ae.getSource() == od.getAmericano())||(ae.getSource() == od.getBestAmericano())) {
			System.out.println("아메리카노");
			new OrderDetailDesgin(od, "옵션선택");
			
		}
		if ((ae.getSource() == od.getCafeLatte())||(ae.getSource() == od.getBestCafeLatte())) {
			System.out.println("카페라떼");
		} 
		if ((ae.getSource() == od.getVanillaLatte())||(ae.getSource() == od.getBestVanillaLatte())) {
			System.out.println("바닐라라떼");
		}
		if (ae.getSource() == od.getChocolateLatte()) {
			System.out.println("초고라떼");
		} 
		if (ae.getSource() == od.getToffeeNutLatte()) {
			System.out.println("토피넛라떼");
		} 
		if (ae.getSource() == od.getGreenTeaLatte()) {
			System.out.println("녹차라떼");
		} 
		if (ae.getSource() == od.getDalgonaLatte()) {
			System.out.println("달고나라떼");
		} 
		if (ae.getSource() == od.getPeachTea()) {
			System.out.println("복숭아 티");
		} 
		if (ae.getSource() == od.getLemonTea()) {
			System.out.println("레몬 티");
		} 
		if (ae.getSource() == od.getGrapefruitTea()) {
			System.out.println("자몽 티");
		} 
		if (ae.getSource() == od.getCamomileTea()) {
			System.out.println("캐모마일 티");
		} 
		if (ae.getSource() == od.getYogurtSmoothie()) {
			System.out.println("요거트 스무디");
		} 
		if (ae.getSource() == od.getMilkShake()) {
			System.out.println("딸기 스무디");
		} 
		if (ae.getSource() == od.getStrowberrySmoothie()) {
			System.out.println("밀크쉐이크");
		} 
		if (ae.getSource() == od.getJavachipFrappe()) {
			System.out.println("자바칩 프라페");
		} 
		if (ae.getSource() == od.getCancel()) {
			System.out.println("취소");
		} 
		if (ae.getSource() == od.getPay()) {
			System.out.println("결제하기");
			od.dispose();
			PaymentPageDesign.main(new String[]{});
		}
	}//actionPerformed
	
	@Override
	public void windowClosed(WindowEvent e) {
		od.dispose();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

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
