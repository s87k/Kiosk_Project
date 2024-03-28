package kiosk_prj.membership;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class DetailOrderDesign extends JDialog {
	private JTable orderTable;
	private DefaultTableModel dtmOrderTable;
	private JButton back;
	private String phoneNum;

	public JTable getOrderTable() {
		return orderTable;
	}

	public void setOrderTable(JTable orderTable) {
		this.orderTable = orderTable;
	}

	public DefaultTableModel getDtmOrderTable() {
		return dtmOrderTable;
	}

	public void setDtmOrderTable(DefaultTableModel dtmOrderTable) {
		this.dtmOrderTable = dtmOrderTable;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public DetailOrderDesign(DetailMemberDesign dmd, String title, String phoneNum) {
		super(dmd, "회원상세 >>> 주문내역", true);
		this.phoneNum = phoneNum;
		String[] cspColumns = { "번호", "주문시간", "상품명", "결재금액" };

		dtmOrderTable = new DefaultTableModel(cspColumns, 0);
		orderTable = new JTable(dtmOrderTable);
		JScrollPane jspConsumePattern = new JScrollPane(orderTable);

		ImageIcon imgExit = new ImageIcon(getClass().getClassLoader().getResource("BTback.png"));
		
		back = new JButton(imgExit);
		// 컴포넌트 배치
		jspConsumePattern.setBounds(32, 10, 500, 480);
		back.setBounds(237,500,100,100);
		// 컴포넌트 추가
		add(jspConsumePattern);
		add(back);
		// 배치 관리자 해제
		setLayout(null);
		// ActionListener 등록
		DetailOrderEvent doe = new DetailOrderEvent(this);
		back.addActionListener(doe);
		
		getContentPane().setBackground(new Color(0xECEDFA));
		setBounds(dmd.getX(), dmd.getY(), 574, 648);
		setVisible(true);
	}
//	}

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
