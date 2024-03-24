package kiosk_prj.coupon.view;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import kiosk_prj.coupon.controller.ConvertCouponRadix;
import kiosk_prj.coupon.controller.SearchCouponEvent;
import kiosk_prj.coupon.dao.CouponInfoViewDAO;
import kiosk_prj.coupon.vo.CouponAddedInfoVO;
import kiosk_prj.coupon.vo.CouponPubInfoVO;
import kiosk_prj.coupon.vo.CouponPublishVO;
import kiosk_prj.coupon.vo.StatusUse;

import static java.lang.String.valueOf;

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
		
		jtbpCoupSearch = new JTabbedPane();
		
		String[] columnName = {"번호", "쿠폰 번호", "쿠폰 이름", "할인액", "이름", "연락처", "상태", "발급일", "사용일", "만료일"};
		SearchCouponEvent sce = new SearchCouponEvent(this);
		CouponInfoViewDAO civDAO = CouponInfoViewDAO.getInstance();
		List<CouponAddedInfoVO> listCaivVO = new ArrayList<CouponAddedInfoVO>();
		CouponAddedInfoVO caivVO = null;
		ConvertCouponRadix ccr = ConvertCouponRadix.getInstance();
		String couponCode = "";
		int num = 1;
		
		JPanel jpCoupKind = new JPanel();
		JPanel jpCoupIssue = new JPanel();
		
		dtmCoupKind = new DefaultTableModel(null, new String[] {"번호", "종류코드", "쿠폰명", "이용기간", "할인액", "발급가능"}) {
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		jtabCoupKind = new JTable(dtmCoupKind);
		jtabCoupKind.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		dtmCoupIssue = new DefaultTableModel(null, new String[] {"번호", "식별 코드", "쿠폰 이름", "할인액", "발급 가능", "이용 기간", "발급 조건"}) {
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		jtabCoupIssue = new JTable(dtmCoupIssue);
		jtabCoupIssue.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
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
			sce.searchPublishableCouponType();
			
			listCaivVO = civDAO.searchAllAddedCouponView();
			Iterator<CouponAddedInfoVO> itaCaiv = listCaivVO.iterator();
			while(itaCaiv.hasNext()) {
				caivVO = itaCaiv.next();
				couponCode = ccr.CouponPublishVOToRadix62(new CouponPublishVO(caivVO.getConditionPrice(), caivVO.getConditionTypeNo(), caivVO.getCoupKindNo()));
				dtmCoupIssue.addRow(new Object[] {num++, couponCode, 
						caivVO.getCoupKindName(), caivVO.getDiscount(), 
						caivVO.getFlagPublishable() == false ? "X": "O", 
						caivVO.getExpiresPeriod(), caivVO.getCondition()});
			} // end while
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
				addRow2DtmCoupPub(dtmCoupPub, civDAO.searchPubCouponView());
				addRow2DtmCoupPub(dtmCoupPubUsable, civDAO.searchPubCouponView(StatusUse.USABLE.getIntVal()));
				addRow2DtmCoupPub(dtmCoupPubUnUsable, civDAO.searchPubCouponView(StatusUse.UN_USABLE.getIntVal()));
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
	
	public void addRow2DtmCoupPub(DefaultTableModel dtm, List<CouponPubInfoVO> listCpiVO) {
		Iterator<CouponPubInfoVO> itaCpi = listCpiVO.iterator();
		
		CouponPubInfoVO cpiVO = null;
		Object[] arrElement = null;
		Date publishDate = null;
		Date useCoupDate = null;
		Date expireDate = null;
		
		SimpleDateFormat sdfFull = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		SimpleDateFormat sdfYMD = new SimpleDateFormat("yyyy-MM-dd");
		
		int cnt = 1;
		while(itaCpi.hasNext()) {
			cpiVO = itaCpi.next();
			publishDate = cpiVO.getPublishDate();
			useCoupDate = cpiVO.getUseCoupDate();
			expireDate = cpiVO.getExpireDate();
			arrElement = new Object[] {cnt++, cpiVO.getCoupPubCode(), cpiVO.getCoupKindName(),
					cpiVO.getDiscount(), cpiVO.getMemberName(), cpiVO.getPhoneNumber(), cpiVO.getStatusUse(),
					publishDate != null ? sdfFull.format(publishDate) : "-",
					useCoupDate != null ? sdfFull.format(useCoupDate) : "-",
					expireDate != null ? sdfYMD.format(expireDate) : "-"};
			dtm.addRow(arrElement);
		} // end while
	} // addRow2DtmCoupPub

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
