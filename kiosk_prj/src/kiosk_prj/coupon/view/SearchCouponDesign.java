package kiosk_prj.coupon.view;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kiosk_prj.coupon.controller.SearchCouponEvent;

@SuppressWarnings("serial")
public class SearchCouponDesign extends JPanel {
	
	private ManageCouponDesign mcd;
	
	private JTabbedPane jtbpCoupSearch;
	private JTable jtabCoupType, jtabCoupPub, jtabCoupPubUsable, jtabCoupPubUnusable;
	private DefaultTableModel dtmCoupType, dtmCoupPub, dtmCoupPubUsable, dtmCoupPubUnusable;
	
	public SearchCouponDesign(ManageCouponDesign mcd) {
		this(mcd, 0, 1, 2, 3);
	} // SearchCouponDesign
	
	public SearchCouponDesign(ManageCouponDesign mcd, int...indTabPane) {
		this.mcd = mcd;
		
		jtbpCoupSearch = new JTabbedPane();
		
		String[] columnName = {"번호", "쿠폰 번호", "쿠폰 이름", "할인액", "이름", "연락처", "상태", "발급일", "사용일", "만료일"};
		SearchCouponEvent sce = new SearchCouponEvent(this);
		jtbpCoupSearch.addChangeListener(sce);
		
		for (int i = 0; i < indTabPane.length; i++) {
			if(indTabPane[i] == 0) {
				JPanel jpCoupAdded = new JPanel();
				dtmCoupType = new DefaultTableModel(null, new String[] {"번호", "종류 코드", "쿠폰 이름", "할인액", "발급 가능", "이용 기간", "발급 조건"});
				jtabCoupType = new JTable(dtmCoupType);
				JScrollPane jspCoupType = new JScrollPane(jtabCoupType);
				jpCoupAdded.setLayout(null);
				jspCoupType.setBounds(10, 20, 810, 560);
				jpCoupAdded.add(jspCoupType);
				jtbpCoupSearch.add("등록된 쿠폰", jpCoupAdded);
			} else if (indTabPane[i] == 1) {
				JPanel jpCoupPublished = new JPanel();
				dtmCoupPub = new DefaultTableModel(null, columnName);
				jtabCoupPub = new JTable(dtmCoupPub);
				JScrollPane jspCoupPub = new JScrollPane(jtabCoupPub);
				jpCoupPublished.setLayout(null);
				jspCoupPub.setBounds(10, 20, 810, 560);
				jpCoupPublished.add(jspCoupPub);
				jtbpCoupSearch.add("발급된 쿠폰", jpCoupPublished);
			} else if (indTabPane[i] == 2) {
				JPanel jpCoupUsable = new JPanel();
				dtmCoupPubUsable = new DefaultTableModel(null, columnName);
				jtabCoupPubUsable = new JTable(dtmCoupPubUsable);
				JScrollPane jspPubUsable = new JScrollPane(jtabCoupPubUsable);
				jpCoupUsable.setLayout(null);
				jspPubUsable.setBounds(10, 20, 810, 560);
				jpCoupUsable.add(jspPubUsable);
				jtbpCoupSearch.add("사용가능 쿠폰", jpCoupUsable);
			} else if (indTabPane[i] == 3) {
				JPanel jpCoupUnusable = new JPanel();
				dtmCoupPubUnusable = new DefaultTableModel(null, columnName);
				jtabCoupPubUnusable = new JTable(dtmCoupPubUnusable);
				JScrollPane jspPubUnusable = new JScrollPane(jtabCoupPubUnusable);
				jpCoupUnusable.setLayout(null);
				jspPubUnusable.setBounds(10, 20, 810, 560);	
				jpCoupUnusable.add(jspPubUnusable);
				jtbpCoupSearch.add("사용불가 쿠폰", jpCoupUnusable);
			} // end else if
		} // end for
		
//		jtbpCoupSearch.set
		
		jtbpCoupSearch.setBounds(170, 100, 830, 620);
		mcd.add(jtbpCoupSearch);
		jtbpCoupSearch.setVisible(false);
		/*
		mcd.revalidate();
		mcd.repaint();
		
		jtbpCoupSearch.setBounds(0, 0, 750, 650);
		add(jtbpCoupSearch);
		
		setBounds(mcd.getX() + 170, mcd.getY() + 110, 850, 650);
		setVisible(true);
		*/
	} // SearchCouponDesign

	public ManageCouponDesign getMcd() {
		return mcd;
	}

	public JTabbedPane getJtbpCoupSearch() {
		return jtbpCoupSearch;
	}

	public JTable getJtabCoupType() {
		return jtabCoupType;
	}

	public JTable getJtabCoupPub() {
		return jtabCoupPub;
	}

	public JTable getJtabCoupPubUsable() {
		return jtabCoupPubUsable;
	}

	public JTable getJtabCoupPubUnusable() {
		return jtabCoupPubUnusable;
	}

	public DefaultTableModel getDtmCoupType() {
		return dtmCoupType;
	}

	public DefaultTableModel getDtmCoupPub() {
		return dtmCoupPub;
	}

	public DefaultTableModel getDtmCoupPubUsable() {
		return dtmCoupPubUsable;
	}

	public DefaultTableModel getDtmCoupPubUnusable() {
		return dtmCoupPubUnusable;
	}
}
