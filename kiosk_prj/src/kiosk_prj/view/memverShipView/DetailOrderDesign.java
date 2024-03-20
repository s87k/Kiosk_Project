package kiosk_prj.view.memverShipView;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kiosk_prj.controller.memberShipEvent.DetailOrderEvent;

@SuppressWarnings("serial")
public class DetailOrderDesign extends JDialog{
	private JTable orderTable;
	private DefaultTableModel dtmOrderTable;
	private JButton back;
	
	public DetailOrderDesign(JDialog DetailMemberDesign, String title) {
	super(DetailMemberDesign, "회원상세 >>> 주문내역" );
	String[] cspColumns = {"번호", "주문시간", "상품명", "결재금액"};
	
	dtmOrderTable = new DefaultTableModel(cspColumns, 0);
	orderTable = new JTable(dtmOrderTable);
	JScrollPane  jspConsumePattern = new JScrollPane(orderTable);
	
	back = new JButton("돌아가기");
	//컴포넌트 배치
	jspConsumePattern.setBounds(10, 10, 500, 480);
	back.setBounds(300, 500, 100, 100);
	//컴포넌트 추가
	add(jspConsumePattern);
	add(back);
	//배치 관리자 해제
	setLayout(null);
	//ActionListener 등록
	DetailOrderEvent doe = new DetailOrderEvent(this);
	back.addActionListener(doe);
	
	setVisible(true);
	setBounds(DetailMemberDesign.getX(), DetailMemberDesign.getY(), 574, 648);
}

public JTable getConsumePattern() {
	return orderTable;
}

public void setConsumePattern(JTable consumePattern) {
	this.orderTable = consumePattern;
}

public JButton getBack() {
	return back;
}

public void setBack(JButton back) {
	this.back = back;
}

public DefaultTableModel getDtmConsumePattern() {
	return dtmOrderTable;
}

public void setDtmConsumePattern(DefaultTableModel dtmConsumePattern) {
	this.dtmOrderTable = dtmConsumePattern;
}
}
