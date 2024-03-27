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
import kiosks.vo.SummaryOrderVO;

public class PaymentPageEvent extends WindowAdapter implements ActionListener, MouseListener{

	private PaymentPageDesign ppd;
	
	private JTable orderMenuList;
	private DefaultTableModel dtmOrderMemuList;
	
	private String phoneNum;
	
	public PaymentPageEvent(PaymentPageDesign ppd) {
		this.ppd = ppd;
		
		orderMenuList = ppd.getOrderMenuList();
		dtmOrderMemuList = ppd.getDtmOrderMemuList();
		
	}//PaymentPageEvent

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ppd.getBtnCheckCoupon()) {
			PhoneNumDesign pnd = new PhoneNumDesign(ppd);
		}
		if(e.getSource() == ppd.getCreditCard()) {
			//주문VO에 값 넣기
			SummaryOrderVO soVO = new SummaryOrderVO();
			//결제금액
			int totalPrice = Integer.parseInt(ppd.getTotalPrice().getText());
			soVO.setAmount(totalPrice);
			//진행현황
			soVO.setProgress("주문완료");
			//결제방법
			soVO.setOrderForm("카드");
			//전화번호
			phoneNum = ppd.getPhoneNum();
			soVO.setPhoneNumber(phoneNum);
			new CompletePayPageDesign(phoneNum);
			
			ppd.dispose();
		}
		if(e.getSource() == ppd.getCash()) {
			//주문VO에 값 넣기
			SummaryOrderVO soVO = new SummaryOrderVO();
			//결제금액
			int totalPrice = Integer.parseInt(ppd.getTotalPrice().getText());
			soVO.setAmount(totalPrice);
			//진행현황
			soVO.setProgress("주문완료");
			//결제방법
			soVO.setOrderForm("현금");
			//전화번호
			phoneNum = ppd.getPhoneNum();
			soVO.setPhoneNumber(phoneNum);
			new CompletePayPageDesign(phoneNum);
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
