package kiosk_prj.coupon.view;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import kiosk_prj.coupon.controller.SearchCouponEvent;
import kiosk_prj.coupon.dao.CouponInfoViewDAO;
import kiosk_prj.coupon.vo.StatusUse;

import java.awt.Font;

@SuppressWarnings("serial")
public class SearchCouponDesign extends JPanel {
	
	private ManageCouponDesign mcd;
	
	private JTabbedPane jtbpCoupSearch;
	private JTable jtabCoupKind, jtabCoupIssue, jtabCoupPub, jtabCoupPubUsable, jtabCoupPubUnusable;
	private DefaultTableModel dtmCoupKind, dtmCoupIssue, dtmCoupPub, dtmCoupPubUsable, dtmCoupPubUnUsable;
	
	public static final int TAB_ONLY_ADD_COUPON = 0;
	public static final int TAB_ALL = 1;
	
	public SearchCouponDesign(ManageCouponDesign mcd) {
		this(mcd, TAB_ALL);
	} // SearchCouponDesign
	
	public SearchCouponDesign(ManageCouponDesign mcd, int tabMode) {
		this.mcd = mcd;
		
		Font font = new Font("맑은 고딕", Font.BOLD, 20);
		
		jtbpCoupSearch = new JTabbedPane();
		jtbpCoupSearch.setFont(font);
		
		String[] columnName = {"번호", "쿠폰 번호", "쿠폰 이름", "할인액", "이름", "연락처", "상태", "발급일", "사용일", "만료일"};
		SearchCouponEvent sce = new SearchCouponEvent(this);
		CouponInfoViewDAO civDAO = CouponInfoViewDAO.getInstance();
		
		JPanel jpCoupKind = new JPanel();
		JPanel jpCoupIssue = new JPanel();
		
		dtmCoupKind = new DefaultTableModel(null, new String[] {"번호", "등록번호", "쿠폰 이름", "이용 가능 기간", "할인액", "발급가능"}) {
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		jtabCoupKind = new JTable(dtmCoupKind);
		jtabCoupKind.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableCellRenderer dtcrCenter = new DefaultTableCellRenderer();
		DefaultTableCellRenderer dtcrRight = new DefaultTableCellRenderer();
		dtcrCenter.setHorizontalAlignment(SwingConstants.CENTER); 
		dtcrRight.setHorizontalAlignment(SwingConstants.RIGHT); 
		TableColumnModel tcmCoupKind = jtabCoupKind.getColumnModel();
		
		tcmCoupKind.getColumn(0).setCellRenderer(dtcrRight);
		tcmCoupKind.getColumn(1).setCellRenderer(dtcrRight);
		tcmCoupKind.getColumn(3).setCellRenderer(dtcrRight);
		tcmCoupKind.getColumn(4).setCellRenderer(dtcrRight);
		tcmCoupKind.getColumn(5).setCellRenderer(dtcrCenter);
		
		dtmCoupIssue = new DefaultTableModel(null, new String[] {"번호", "식별 코드", "쿠폰 이름", "할인액", "자동 발급 활성화", "이용 기간", "발급 조건"}) {
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		jtabCoupIssue = new JTable(dtmCoupIssue);
		jtabCoupIssue.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jtabCoupIssue.getColumn("식별 코드").setPreferredWidth(100);
		jtabCoupIssue.getColumn("쿠폰 이름").setPreferredWidth(150);
		jtabCoupIssue.getColumn("발급 조건").setPreferredWidth(300);
		jtabCoupIssue.getColumn("자동 발급 활성화").setPreferredWidth(100);
		TableColumnModel tcmCoupIssue = jtabCoupIssue.getColumnModel();
		
		tcmCoupIssue.getColumn(0).setCellRenderer(dtcrRight);
		tcmCoupIssue.getColumn(3).setCellRenderer(dtcrRight);
		tcmCoupIssue.getColumn(4).setCellRenderer(dtcrCenter);
		tcmCoupIssue.getColumn(5).setCellRenderer(dtcrRight);
		
		JScrollPane jspCoupKind = new JScrollPane(jtabCoupKind);
		JScrollPane jspCoupIssue = new JScrollPane(jtabCoupIssue);
		
		jtabCoupKind.addMouseListener(sce);
		jtabCoupIssue.addMouseListener(sce);
		
		jpCoupKind.setLayout(null);
		jpCoupIssue.setLayout(null);
		jspCoupKind.setBounds(10, 20, 810, 560);
		jspCoupIssue.setBounds(10, 20, 810, 560);
		
		jpCoupKind.add(jspCoupKind);
		jpCoupIssue.add(jspCoupIssue);
		
		jtbpCoupSearch.add("등록된 쿠폰", jpCoupKind);
		jtbpCoupSearch.add("발급된 쿠폰", jpCoupIssue);
		jtbpCoupSearch.addChangeListener(sce);
		try {
			sce.renewPublishableCouponKind();
			sce.renewCoupIssueTable();
		} catch (SQLException se) {
			se.printStackTrace();
			JOptionPane.showMessageDialog(mcd, "등록된 쿠폰 조회에 실패했습니다");
			return;
		} // end catch
			
		if(tabMode != TAB_ONLY_ADD_COUPON) {
			
			JPanel jpCoupPublished = new JPanel();
			JPanel jpCoupUsable = new JPanel();
			JPanel jpCoupUnusable = new JPanel();
			
			dtmCoupPub = new DefaultTableModel(null, columnName) {
				@Override
			    public boolean isCellEditable(int row, int column) {
			       return false;
			    }
			};
			dtmCoupPubUsable = new DefaultTableModel(null, columnName) {
				@Override
			    public boolean isCellEditable(int row, int column) {
			       return false;
			    }
			};
			dtmCoupPubUnUsable = new DefaultTableModel(null, columnName) {
				@Override
			    public boolean isCellEditable(int row, int column) {
			       return false;
			    }
			};
			
			jtabCoupPub = new JTable(dtmCoupPub);
			jtabCoupPubUsable = new JTable(dtmCoupPubUsable);
			jtabCoupPubUnusable = new JTable(dtmCoupPubUnUsable);
			
			jtabCoupPub.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jtabCoupPubUsable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jtabCoupPubUnusable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			JTable[] arrJtab = new JTable[] {jtabCoupPub, jtabCoupPubUsable, jtabCoupPubUnusable};
			TableColumnModel tcm = null;
			for(int i = 0; i < arrJtab.length; i++) {
				tcm = arrJtab[i].getColumnModel();
				
				tcm.getColumn(0).setCellRenderer(dtcrRight);
				tcm.getColumn(3).setCellRenderer(dtcrRight);
				tcm.getColumn(6).setCellRenderer(dtcrCenter);
				arrJtab[i].getColumn(columnName[0]).setPreferredWidth(1);
				arrJtab[i].getColumn(columnName[1]).setPreferredWidth(80);
				arrJtab[i].getColumn(columnName[3]).setPreferredWidth(5);
				arrJtab[i].getColumn(columnName[4]).setPreferredWidth(10);
				arrJtab[i].getColumn(columnName[5]).setPreferredWidth(50);
				arrJtab[i].getColumn(columnName[6]).setPreferredWidth(25);
				arrJtab[i].getColumn(columnName[9]).setPreferredWidth(25);
			} // end for
			
			jtabCoupPub.addMouseListener(null);
			jtabCoupPubUsable.addMouseListener(null);
			jtabCoupPubUnusable.addMouseListener(null);
			
			JScrollPane jspCoupPub = new JScrollPane(jtabCoupPub);
			JScrollPane jspPubUsable = new JScrollPane(jtabCoupPubUsable);
			JScrollPane jspPubUnusable = new JScrollPane(jtabCoupPubUnusable);
			
			jpCoupPublished.setLayout(null);
			jpCoupUsable.setLayout(null);
			jpCoupUnusable.setLayout(null);
			
			jspCoupPub.setBounds(10, 20, 810, 560);
			jspPubUsable.setBounds(10, 20, 810, 560);
			jspPubUnusable.setBounds(10, 20, 810, 560);	
			
			jpCoupPublished.add(jspCoupPub);
			jpCoupUsable.add(jspPubUsable);
			jpCoupUnusable.add(jspPubUnusable);
			
			jtbpCoupSearch.add("회원 보유 쿠폰", jpCoupPublished);
			jtbpCoupSearch.add("사용가능 쿠폰", jpCoupUsable);
			jtbpCoupSearch.add("사용불가 쿠폰", jpCoupUnusable);
			
			try {
				sce.renewRow2DtmCoupPub(dtmCoupPub, civDAO.selectPubCouponView());
				sce.renewRow2DtmCoupPub(dtmCoupPubUsable, civDAO.selectPubCouponView(StatusUse.USABLE.getIntVal()));
				sce.renewRow2DtmCoupPub(dtmCoupPubUnUsable, civDAO.selectPubCouponView(StatusUse.UN_USABLE.getIntVal()));
			} catch (SQLException se) {
				se.printStackTrace();
				JOptionPane.showMessageDialog(mcd, "발급된 쿠폰 조회에 실패했습니다");
				return;
			} // end catch
		} // end if
		
//		jtbpCoupSearch.set
		
		jtbpCoupSearch.setBounds(170, 100, 830, 620);
		mcd.add(jtbpCoupSearch);
		jtbpCoupSearch.setVisible(false);
//		jtbpCoupSearch.setVisible(true);
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

	public JTable getJtabCoupKind() {
		return jtabCoupKind;
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

	public DefaultTableModel getDtmCoupKind() {
		return dtmCoupKind;
	}

	public DefaultTableModel getDtmCoupPub() {
		return dtmCoupPub;
	}

	public DefaultTableModel getDtmCoupPubUsable() {
		return dtmCoupPubUsable;
	}

	public DefaultTableModel getDtmCoupPubUnusable() {
		return dtmCoupPubUnUsable;
	}

	public JTable getJtabCoupIssue() {
		return jtabCoupIssue;
	}

	public DefaultTableModel getDtmCoupIssue() {
		return dtmCoupIssue;
	}
}
