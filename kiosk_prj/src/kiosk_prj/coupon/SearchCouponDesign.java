package kiosk_prj.coupon;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class SearchCouponDesign extends JDialog {
	
	ManageCouponDesign mcd;
	
	JTabbedPane jtbpCoupSearch;
	JTable jtabCoupType, jtabCoupPub, jtabCoupPubUsable, jtabCoupPubUnusable;
	DefaultTableModel dtmCoupType, dtmCoupPub, dtmCoupPubUsable, dtmCoupPubUnusable;
	
	public SearchCouponDesign(ManageCouponDesign mcd) {
		this(mcd, 0, 1, 2, 3);
	} // SearchCouponDesign
	
	public SearchCouponDesign(ManageCouponDesign mcd, int...sortCoupon) {
		this.mcd = mcd;
		
		jtbpCoupSearch = new JTabbedPane();
		
		JPanel jpCoupAdded = new JPanel();
		JPanel jpCoupPublished = new JPanel();
		JPanel jpCoupUsable = new JPanel();
		JPanel jpCoupUnusable = new JPanel();
		
		dtmCoupType = new DefaultTableModel(null, new String[] {"번호", "종류 코드", "쿠폰 이름", "할인액", "발급 가능", "이용 기간", "발급 조건"});
		String[] columnName = {"번호", "쿠폰 번호", "쿠폰 이름", "할인액", "이름", "연락처", "상태", "발급일", "사용일", "만료일"};
		dtmCoupPub = new DefaultTableModel(null, columnName);
		dtmCoupPubUsable = new DefaultTableModel(null, columnName);
		dtmCoupPubUnusable = new DefaultTableModel(null, columnName);
		
		jtabCoupType = new JTable(dtmCoupType);
		jtabCoupPub = new JTable(dtmCoupPub);
		jtabCoupPubUsable = new JTable(dtmCoupPubUsable);
		jtabCoupPubUnusable = new JTable(dtmCoupPubUnusable);
		
		JScrollPane jspCoupType = new JScrollPane(jtabCoupType);
		JScrollPane jspCoupPub = new JScrollPane(jtabCoupPub);
		JScrollPane jspPubUsable = new JScrollPane(jtabCoupPubUsable);
		JScrollPane jspPubUnusable = new JScrollPane(jtabCoupPubUnusable);
		
		jpCoupAdded.setLayout(null);
		jpCoupPublished.setLayout(null);
		jpCoupUsable.setLayout(null);
		jpCoupUnusable.setLayout(null);
		
		jspCoupType.setBounds(10, 20, 810, 560);
		jspCoupPub.setBounds(10, 20, 810, 560);
		jspPubUsable.setBounds(10, 20, 810, 560);
		jspPubUnusable.setBounds(10, 20, 810, 560);	
		
		jpCoupAdded.add(jspCoupType);
		jpCoupPublished.add(jspCoupPub);
		jpCoupUsable.add(jspPubUsable);
		jpCoupUnusable.add(jspPubUnusable);
		
		jtbpCoupSearch.add("등록된 쿠폰", jpCoupAdded);
		jtbpCoupSearch.add("발급된 쿠폰", jpCoupPublished);
		jtbpCoupSearch.add("사용가능 쿠폰", jpCoupUsable);
		jtbpCoupSearch.add("사용불가 쿠폰", jpCoupUnusable);
		
		jtbpCoupSearch.setBounds(0, 0, 750, 650);
		add(jtbpCoupSearch);
		
		setBounds(mcd.getX() + 170, mcd.getY() + 110, 850, 650);
		setVisible(true);
	} // SearchCouponDesign
}
