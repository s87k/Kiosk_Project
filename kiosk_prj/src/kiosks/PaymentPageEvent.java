package kiosks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kiosks.CompletePayPageDesign;
import kiosks.PaymentPageDesign;
import kiosks.PhoneNumDesign;

public class PaymentPageEvent extends WindowAdapter implements ActionListener, MouseListener{

	private PaymentPageDesign ppd;
	
	private JTable orderMenuList;
	private DefaultTableModel dtmOrderMemuList;
	
	public PaymentPageEvent(PaymentPageDesign ppd) {
		this.ppd = ppd;
		
		orderMenuList = ppd.getOrderMenuList();
		dtmOrderMemuList = ppd.getDtmOrderMemuList();
	}//PaymentPageEvent

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ppd.getBtnCheckCoupon()) {
			PhoneNumDesign pnd = new PhoneNumDesign(ppd, "번호 조회");
			pnd.getOrderAmount().setText(ppd.getOrderPrice().getText()+" 원");
		}
		if(e.getSource() == ppd.getCreditCard()) {
			new CompletePayPageDesign();
			ppd.dispose();
		}
		if(e.getSource() == ppd.getCash()) {
			new CompletePayPageDesign();
			ppd.dispose();
		}
	}//actionPerformed
	
	@Override
	public void windowClosed(WindowEvent e) {
		ppd.dispose();
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
