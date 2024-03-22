package kiosk_prj.view;

import javax.swing.JButton;
import javax.swing.JDialog;


@SuppressWarnings("serial")
public class ManageCouponDesign extends JDialog {
	
	JButton jbtnAddCoupType, jbtnPublishCoup, jbtnSearchCoup, jbtnModifyCoup;
	JButton jbtnGoToMain;
	
//	public ManageCoupon(ParentFrame pf) {
//		super(pf, "쿠폰 관리", true);	// modal. Dialog가 띄워지면 부모 JFrame 창이 선택되지 않음
	public ManageCouponDesign() {
		setTitle("쿠폰 관리");
		
		jbtnAddCoupType = new JButton("쿠폰 등록");
		jbtnPublishCoup = new JButton("쿠폰 발급");
		jbtnSearchCoup = new JButton("쿠폰 조회");
		jbtnModifyCoup = new JButton("쿠폰 수정");
		jbtnGoToMain = new JButton("메인으로");
		
		setLayout(null);
		
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
		
		setBounds(455, 130, 1024, 768);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	} // ManageCoupon
	
	public static void main(String[] args) {
		// 테스트용 
		new ManageCouponDesign();
	} // main
	
} // class
