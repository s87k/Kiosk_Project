package kiosk_prj.coupon;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
//public class ManageCouponDesign extends JPanel {
public class ManageCouponDesign extends JDialog {
	
	public ManageCouponDesign(JFrame AdminMainPageDesign, String title) {
		JOptionPane.showMessageDialog(null, "쿠폰 관리는 다른 디자인 클래스로 연결되어야 합니다\n테스트를 희망하시면 kiosk_prj.coupon.TestRunCoupon.java 를 실행시켜주세요");
	} // ManageCouponDesign
	
	/*
	private JButton jbtnAddCoupType, jbtnPublishCoup, jbtnSearchCoup, jbtnModifyCoup;
	private JButton jbtnGoToMain;
	private SearchCouponDesign scdSearch, scdModify;
	
	public ManageCouponDesign(JFrame AdminMainPageDesign, String title) {
		super(AdminMainPageDesign, "쿠폰 관리");
		
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
		
		setBounds(AdminMainPageDesign.getX(), AdminMainPageDesign.getY(), 1024, 768);
		setVisible(true);
	} // ManageCoupon
	

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
	 */
	
} // class
