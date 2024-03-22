package kiosk_prj.coupon.view;

import javax.swing.JButton;
import javax.swing.JDialog;

import kiosk_prj.coupon.controller.ManageCouponEvent;

@SuppressWarnings("serial")
//public class ManageCouponDesign extends JPanel {
public class ManageCouponDesign extends JDialog {
	
	private AdminMainPageDesign ampd;
	
	private JButton jbtnAddCoupType, jbtnPublishCoup, jbtnSearchCoup, jbtnModifyCoup;
	private JButton jbtnGoToMain;
	private SearchCouponDesign scdSearch, scdModify;
	
	public ManageCouponDesign(AdminMainPageDesign ampd) {
		this.ampd = ampd;
		ampd.setTitle("쿠폰 관리");
		
		jbtnAddCoupType = new JButton("쿠폰 등록");
		jbtnPublishCoup = new JButton("쿠폰 발급");
		jbtnSearchCoup = new JButton("쿠폰 조회");
		jbtnModifyCoup = new JButton("쿠폰 수정");
		jbtnGoToMain = new JButton("메인으로");
		
		setLayout(null);
		
		scdSearch = new SearchCouponDesign(this);
		scdModify = new SearchCouponDesign(this, 0);
		
		
		jbtnAddCoupType.setBounds(10, 60, 150, 150);
		jbtnPublishCoup.setBounds(10, 220, 150, 150);
		jbtnSearchCoup.setBounds(10, 380, 150, 150);
		jbtnModifyCoup.setBounds(10, 540, 150, 150);
		jbtnGoToMain.setBounds(914, 10, 100, 60);
		
		add(jbtnAddCoupType);
		add(jbtnPublishCoup);
		add(jbtnSearchCoup);
		add(jbtnModifyCoup);
		add(jbtnGoToMain);
		
		ManageCouponEvent mce = new ManageCouponEvent(this);		
		jbtnAddCoupType.addActionListener(mce);
		jbtnPublishCoup.addActionListener(mce);
		jbtnSearchCoup.addActionListener(mce);
		jbtnModifyCoup.addActionListener(mce);
		jbtnGoToMain.addActionListener(mce);
		
		setBounds(ampd.getX(), ampd.getY(), 1024, 768);
		setVisible(true);
	} // ManageCoupon
	
	public AdminMainPageDesign getAmpd() {
		return ampd;
	}

	public JButton getJbtnAddCoupType() {
		return jbtnAddCoupType;
	}

	public JButton getJbtnPublishCoup() {
		return jbtnPublishCoup;
	}

	public JButton getJbtnSearchCoup() {
		return jbtnSearchCoup;
	}

	public JButton getJbtnModifyCoup() {
		return jbtnModifyCoup;
	}

	public JButton getJbtnGoToMain() {
		return jbtnGoToMain;
	}
	
	public SearchCouponDesign getScdSearch() {
		return scdSearch;
	}

	public SearchCouponDesign getScdModify() {
		return scdModify;
	}

	public static void main(String[] args) {
		// 테스트용 
		new ManageCouponDesign(new AdminMainPageDesign());
	} // main
	
} // class
