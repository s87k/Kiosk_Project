package kiosk_prj.coupon.view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import kiosk_prj.adminMain.AdminMainPageDesign;
import kiosk_prj.coupon.controller.ManageCouponEvent;
import kiosk_prj.coupon.vo.ManageButton;

@SuppressWarnings("serial")
//public class ManageCouponDesign extends JPanel {
public class ManageCouponDesign extends JDialog {
	
	private AdminMainPageDesign ampd;
	
	private JButton[] arrJbtnFunc;
	private JButton jbtnGoToMain;
	private SearchCouponDesign scdSearch, scdModify;
	private ImageIcon[] arrIiBtn, arrIiBtnClick;
	
	private int lastClickedButton;
	
	private ManageCouponEvent mce;
	
	public ManageCouponDesign(AdminMainPageDesign ampd) {
		this.ampd = ampd;
		setTitle("쿠폰 관리");
		setModal(true);

		arrIiBtn = new ImageIcon[4];
		arrIiBtn[ManageButton.ADD.ordinal()] = new ImageIcon(getClass().getClassLoader().getResource("btn_add_select_not.png"));
		arrIiBtn[ManageButton.PUBLISH.ordinal()] = new ImageIcon(getClass().getClassLoader().getResource("btn_pub_select_not.png"));
		arrIiBtn[ManageButton.SEARCH.ordinal()] = new ImageIcon(getClass().getClassLoader().getResource("btn_search_select_not.png"));
		arrIiBtn[ManageButton.MODIFY.ordinal()] = new ImageIcon(getClass().getClassLoader().getResource("btn_modify_select_not.png"));
		
		arrIiBtnClick = new ImageIcon[4];
		arrIiBtnClick[ManageButton.ADD.ordinal()] = new ImageIcon(getClass().getClassLoader().getResource("btn_add_select.png"));
		arrIiBtnClick[ManageButton.PUBLISH.ordinal()] = new ImageIcon(getClass().getClassLoader().getResource("btn_pub_select.png"));
		arrIiBtnClick[ManageButton.SEARCH.ordinal()] = new ImageIcon(getClass().getClassLoader().getResource("btn_search_select.png"));
		arrIiBtnClick[ManageButton.MODIFY.ordinal()] = new ImageIcon(getClass().getClassLoader().getResource("btn_modify_select.png"));
		
		ImageIcon iiBtnGoToMain = new ImageIcon(getClass().getClassLoader().getResource("btn_go_main.png"));
		ImageIcon iiBackground = new ImageIcon(getClass().getClassLoader().getResource("bg_manage_coupon.png"));
		
		arrJbtnFunc = new JButton[4];
		arrJbtnFunc[ManageButton.ADD.ordinal()] = new JButton(arrIiBtn[ManageButton.ADD.ordinal()]);
		arrJbtnFunc[ManageButton.PUBLISH.ordinal()] = new JButton(arrIiBtn[ManageButton.PUBLISH.ordinal()]);
		arrJbtnFunc[ManageButton.SEARCH.ordinal()] = new JButton(arrIiBtn[ManageButton.SEARCH.ordinal()]);
		arrJbtnFunc[ManageButton.MODIFY.ordinal()] = new JButton(arrIiBtn[ManageButton.MODIFY.ordinal()]);
		
		jbtnGoToMain = new JButton(iiBtnGoToMain);
		JLabel jlblBackground = new JLabel(iiBackground);
		
		setLayout(null);
		
		scdSearch = new SearchCouponDesign(this);
		scdModify = new SearchCouponDesign(this, 0);
		
		jlblBackground.setBounds(0, 0, 1024, 768);
		arrJbtnFunc[ManageButton.ADD.ordinal()].setBounds(10, 60, 150, 150);
		arrJbtnFunc[ManageButton.PUBLISH.ordinal()].setBounds(10, 220, 150, 150);
		arrJbtnFunc[ManageButton.SEARCH.ordinal()].setBounds(10, 380, 150, 150);
		arrJbtnFunc[ManageButton.MODIFY.ordinal()].setBounds(10, 540, 150, 150);
		jbtnGoToMain.setBounds(914, 40, 100, 60);
		
		add(arrJbtnFunc[ManageButton.ADD.ordinal()]);
		add(arrJbtnFunc[ManageButton.PUBLISH.ordinal()]);
		add(arrJbtnFunc[ManageButton.SEARCH.ordinal()]);
		add(arrJbtnFunc[ManageButton.MODIFY.ordinal()]);
		add(jbtnGoToMain);
		add(jlblBackground);
		
		mce = new ManageCouponEvent(this);
		arrJbtnFunc[ManageButton.ADD.ordinal()].addActionListener(mce);
		arrJbtnFunc[ManageButton.PUBLISH.ordinal()].addActionListener(mce);
		arrJbtnFunc[ManageButton.SEARCH.ordinal()].addActionListener(mce);
		arrJbtnFunc[ManageButton.MODIFY.ordinal()].addActionListener(mce);
		jbtnGoToMain.addActionListener(mce);
		
		setBounds(ampd.getX(), ampd.getY(), 1024, 768);
		setVisible(true);
	} // ManageCoupon
	
	public AdminMainPageDesign getAmpd() {
		return ampd;
	}
	public JButton[] getArrJbtnFunc() {
		return arrJbtnFunc;
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

	public int getLastClickedButton() {
		return lastClickedButton;
	}

	public void setLastClickedButton(int lastClickedButton) {
		this.lastClickedButton = lastClickedButton;
	}

	public ImageIcon[] getArrIiBtn() {
		return arrIiBtn;
	}

	public ImageIcon[] getArrIiBtnClick() {
		return arrIiBtnClick;
	}

	public ManageCouponEvent getMce() {
		return mce;
	}

//	public static void main(String[] args) {
//		// 테스트용 
//		new ManageCouponDesign(new AdminMainPageDesign("김병년"));
//	} // main
	
} // class
