package kiosk_prj.coupon;

import static java.lang.String.valueOf;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class AddCouponDesign extends JDialog{
	
	private ManageCouponDesign mcd;
	
	private JTextField jtfCouponKindName, jtfDiscount;
	private JButton jbtnMonth1, jbtnMonth3, jbtnYear1;
	private JComboBox<String> jcbPeriodDetail;
	private DefaultComboBoxModel<String> dcbmPeriodDetail;
	private JRadioButton jrbPeriodDefault, jrbPeriodDetail;
	private JRadioButton jrbPublishableOk, jrbPublishableNo;
	private JButton jbtnGoMain, jbtnAddCoupon, jbtnCancel;
	
	public AddCouponDesign(ManageCouponDesign mcd) {
		super(mcd, "쿠폰 등록", true);
		
		this.mcd = mcd;
		
		JLabel jlblCouponKindName = new JLabel("쿠폰 이름");
		JLabel jlblDiscount = new JLabel("할인 금액");
		
		jtfCouponKindName = new JTextField(20);
		jtfDiscount = new JTextField(20);
		jtfDiscount.setDocument(new UnsignedIntegerDocument(5, 0, 99999));
		
		jbtnMonth1 = new JButton("1개월");
		jbtnMonth3 = new JButton("3개월");
		jbtnYear1 = new JButton("1년");
		jrbPeriodDefault = new JRadioButton("기본 설정");
		jrbPeriodDefault.setSelected(true);
		jrbPeriodDetail = new JRadioButton("상세 설정");
		ButtonGroup bgPeriod = new ButtonGroup();
		bgPeriod.add(jrbPeriodDefault);
		bgPeriod.add(jrbPeriodDetail);
		
		dcbmPeriodDetail = new DefaultComboBoxModel<String>();
		for(int i = 1; i < 13; i++) {
			dcbmPeriodDetail.addElement(valueOf(i).concat("개월"));
		} // end for
		jcbPeriodDetail = new JComboBox<String>(dcbmPeriodDetail);
		jcbPeriodDetail.setEnabled(false);
		
		JLabel jlblPublishable = new JLabel("발급 가능");
		jrbPublishableOk = new JRadioButton("예");
		jrbPublishableOk.setSelected(true);
		jrbPublishableNo = new JRadioButton("아니오");
		ButtonGroup bgPublishOption = new ButtonGroup();
		bgPublishOption.add(jrbPublishableOk);
		bgPublishOption.add(jrbPublishableNo);
		
		jbtnGoMain = new JButton("메인으로");
		jbtnAddCoupon = new JButton("등록");
		jbtnCancel = new JButton("취소");
		
		AddCouponEvent ace = new AddCouponEvent(this);
		jrbPeriodDefault.addActionListener(ace);
		jrbPeriodDetail.addActionListener(ace);
		jcbPeriodDetail.addActionListener(ace);
		jbtnMonth1.addActionListener(ace);
		jbtnMonth3.addActionListener(ace);
		jbtnYear1.addActionListener(ace);
		jbtnGoMain.addActionListener(ace);
		jbtnAddCoupon.addActionListener(ace);
		jbtnCancel.addActionListener(ace);
		
		setLayout(null);
		
		JPanel jpPeriod = new JPanel();
		jpPeriod.setLayout(null);
		jrbPeriodDefault.setBounds(30, 30, 100, 80);
		jrbPeriodDetail.setBounds(30, 140, 100, 80);
		jbtnMonth1.setBounds(140, 20, 150, 100);
		jbtnMonth3.setBounds(320, 20, 150, 100);
		jbtnYear1.setBounds(500, 20, 150, 100);
		jcbPeriodDetail.setBounds(140, 150, 150, 60);
		jpPeriod.add(jrbPeriodDefault);
		jpPeriod.add(jrbPeriodDetail);
		jpPeriod.add(jbtnMonth1);
		jpPeriod.add(jbtnMonth3);
		jpPeriod.add(jbtnYear1);
		jpPeriod.add(jcbPeriodDetail);
		jpPeriod.setBorder(new TitledBorder("이용 기간"));
		
		jlblCouponKindName.setBounds(30, 10, 60, 60);
		jlblDiscount.setBounds(30, 70, 60, 80);
		jtfCouponKindName.setBounds(125, 10, 600, 60);
		jtfDiscount.setBounds(125, 80, 600, 60);
		jpPeriod.setBounds(25, 160, 700, 250);
		jlblPublishable.setBounds(30, 410, 100, 80);
		jrbPublishableOk.setBounds(140, 410, 100, 80);
		jrbPublishableNo.setBounds(250, 410, 100, 80);
		jbtnGoMain.setBounds(175, 500, 120, 80);
		jbtnAddCoupon.setBounds(315, 500, 120, 80);
		jbtnCancel.setBounds(455, 500, 120, 80);
		
		add(jlblCouponKindName);
		add(jlblDiscount);
		add(jtfCouponKindName);
		add(jtfDiscount);
		add(jpPeriod);
		add(jlblPublishable);
		add(jrbPublishableOk);
		add(jrbPublishableNo);
		add(jbtnGoMain);
		add(jbtnAddCoupon);
		add(jbtnCancel);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		// 455, 130, 1024, 768
		setBounds(mcd.getX() + 200, mcd.getY() + 100, 750, 650);
		setVisible(true);
	} // AddCouponDesign

	public ManageCouponDesign getMcd() {
		return mcd;
	}

	public JTextField getJtfCouponKindName() {
		return jtfCouponKindName;
	}

	public JTextField getJtfDiscount() {
		return jtfDiscount;
	}

	public JButton getJbtnMonth1() {
		return jbtnMonth1;
	}

	public JButton getJbtnMonth3() {
		return jbtnMonth3;
	}

	public JButton getJbtnYear1() {
		return jbtnYear1;
	}

	public JComboBox<String> getJcbPeriodDetail() {
		return jcbPeriodDetail;
	}

	public DefaultComboBoxModel<String> getDcbmPeriodDetail() {
		return dcbmPeriodDetail;
	}

	public JRadioButton getJrbPeriodDefault() {
		return jrbPeriodDefault;
	}

	public JRadioButton getJrbPeriodDetail() {
		return jrbPeriodDetail;
	}

	public JRadioButton getJrbPublishableOk() {
		return jrbPublishableOk;
	}

	public JRadioButton getJrbPublishableNo() {
		return jrbPublishableNo;
	}

	public JButton getJbtnGoMain() {
		return jbtnGoMain;
	}

	public JButton getJbtnAddCoupon() {
		return jbtnAddCoupon;
	}

	public JButton getJbtnCancel() {
		return jbtnCancel;
	}
	
} // class
