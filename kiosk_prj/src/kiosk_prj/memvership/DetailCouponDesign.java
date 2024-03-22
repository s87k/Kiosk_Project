package kiosk_prj.memvership;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class DetailCouponDesign extends JDialog {
	private JTable couponTable;
	private JButton back;
	private DefaultTableModel dtmCouponTable;
	private String phoneNum;

	public DetailCouponDesign(DetailMemberDesign dmd, String title, String phoneNum) {
		super(dmd, "회원상세 >>> 쿠폰목록", true);
		this.phoneNum = phoneNum;
		String[] cspColumns = { "번호", "쿠폰명", "할인액", "발급일", "사용일", "만료일", "상태" };

		dtmCouponTable = new DefaultTableModel(cspColumns, 0);
		couponTable = new JTable(dtmCouponTable);
		JScrollPane jspConsumePattern = new JScrollPane(couponTable);

		back = new JButton("돌아가기");
		// 컴포넌트 배치
		jspConsumePattern.setBounds(10, 10, 500, 480);
		back.setBounds(300, 500, 100, 100);
		// 컴포넌트 추가
		add(jspConsumePattern);
		add(back);
		// 배치 관리자 해제
		setLayout(null);
		// ActionListener 등록
		DetailCouponEvent dce = new DetailCouponEvent(this);
		back.addActionListener(dce);

		setBounds(dmd.getX(), dmd.getY(), 574, 648);
		setVisible(true);
	}

	public JTable getCouponTable() {
		return couponTable;
	}

	public void setCouponTable(JTable couponTable) {
		this.couponTable = couponTable;
	}

	public DefaultTableModel getDtmCouponTable() {
		return dtmCouponTable;
	}

	public void setDtmCouponTable(DefaultTableModel dtmCouponTable) {
		this.dtmCouponTable = dtmCouponTable;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public JTable getConsumePattern() {
		return couponTable;
	}

	public void setConsumePattern(JTable consumePattern) {
		this.couponTable = consumePattern;
	}

	public JButton getBack() {
		return back;
	}

	public void setBack(JButton back) {
		this.back = back;
	}

	public DefaultTableModel getDtmConsumePattern() {
		return dtmCouponTable;
	}

	public void setDtmConsumePattern(DefaultTableModel dtmConsumePattern) {
		this.dtmCouponTable = dtmConsumePattern;
	}
}
