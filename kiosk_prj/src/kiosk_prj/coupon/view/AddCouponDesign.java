package kiosk_prj.coupon.view;

import static java.lang.String.valueOf;

import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import kiosk_prj.coupon.StringLimitLengthDocument;
import kiosk_prj.coupon.UnsignedIntegerDocument;
import kiosk_prj.coupon.controller.AddCouponEvent;
import kiosk_prj.coupon.vo.ExpirePeriod;

@SuppressWarnings("serial")
public class AddCouponDesign extends JDialog{
	
	private ManageCouponDesign mcd;
	
	private JTextField jtfCouponKindName, jtfDiscount;
	private JButton[] arrJbtnPeriod;
	private JComboBox<String> jcbPeriodDetail;
	private DefaultComboBoxModel<String> dcbmPeriodDetail;
	private JRadioButton jrbPeriodDefault, jrbPeriodDetail;
	private JRadioButton jrbPublishableOk, jrbPublishableNo;
	private JButton jbtnGoMain, jbtnAddCoupon, jbtnCancel;
	private ImageIcon[] arrIiBtnPeriod, arrIiBtnPeriodClick;
	
	public AddCouponDesign(ManageCouponDesign mcd) {
		super(mcd, "쿠폰 등록", true);
		
		this.mcd = mcd;
		
		Font font = new Font("맑은 고딕", Font.BOLD, 20);
		
		JLabel jlblCouponKindName = new JLabel("쿠폰 이름");
		JLabel jlblDiscount = new JLabel("할인 금액");
		jlblCouponKindName.setFont(font);
		jlblDiscount.setFont(font);
		
		jtfCouponKindName = new JTextField(20);
		jtfCouponKindName.setDocument(new StringLimitLengthDocument(31));
		jtfCouponKindName.setFont(font);
		jtfCouponKindName.setHorizontalAlignment(JTextField.CENTER);
		jtfDiscount = new JTextField(20);
		jtfDiscount.setDocument(new UnsignedIntegerDocument(5, 1, 99999));
		jtfDiscount.setFont(font);
		jtfDiscount.setHorizontalAlignment(JTextField.RIGHT);
		
		arrIiBtnPeriod = new ImageIcon[ExpirePeriod.values().length];
		arrIiBtnPeriodClick = new ImageIcon[ExpirePeriod.values().length];
		
		arrIiBtnPeriod[ExpirePeriod.MONTH1.ordinal()] = new ImageIcon(getClass().getClassLoader().getResource("btn_month_1_select_not.png"));
		arrIiBtnPeriod[ExpirePeriod.MONTH3.ordinal()] = new ImageIcon(getClass().getClassLoader().getResource("btn_month_3_select_not.png"));
		arrIiBtnPeriod[ExpirePeriod.YEAR1.ordinal()] = new ImageIcon(getClass().getClassLoader().getResource("btn_year_1_select_not.png"));
		
		arrIiBtnPeriodClick[ExpirePeriod.MONTH1.ordinal()] = new ImageIcon(getClass().getClassLoader().getResource("btn_month_1_select.png"));
		arrIiBtnPeriodClick[ExpirePeriod.MONTH3.ordinal()] = new ImageIcon(getClass().getClassLoader().getResource("btn_month_3_select.png"));
		arrIiBtnPeriodClick[ExpirePeriod.YEAR1.ordinal()] = new ImageIcon(getClass().getClassLoader().getResource("btn_year_1_select.png"));
	
		arrJbtnPeriod = new JButton[3];
		arrJbtnPeriod[ExpirePeriod.MONTH1.ordinal()] = new JButton("1개월");
		arrJbtnPeriod[ExpirePeriod.MONTH3.ordinal()] = new JButton("3개월");
		arrJbtnPeriod[ExpirePeriod.YEAR1.ordinal()] = new JButton("1년");
		arrJbtnPeriod[ExpirePeriod.MONTH1.ordinal()].setIcon((arrIiBtnPeriod[ExpirePeriod.MONTH1.ordinal()]));
		arrJbtnPeriod[ExpirePeriod.MONTH3.ordinal()].setIcon((arrIiBtnPeriod[ExpirePeriod.MONTH3.ordinal()]));
		arrJbtnPeriod[ExpirePeriod.YEAR1.ordinal()].setIcon((arrIiBtnPeriod[ExpirePeriod.YEAR1.ordinal()]));
		
		jrbPeriodDefault = new JRadioButton("기본 설정");
		jrbPeriodDefault.setSelected(true);
		jrbPeriodDefault.setFont(font);
		jrbPeriodDetail = new JRadioButton("상세 설정");
		jrbPeriodDetail.setFont(font);
		ButtonGroup bgPeriod = new ButtonGroup();
		bgPeriod.add(jrbPeriodDefault);
		bgPeriod.add(jrbPeriodDetail);
		
		dcbmPeriodDetail = new DefaultComboBoxModel<String>();
		for(int i = 1; i < 13; i++) {
			dcbmPeriodDetail.addElement(valueOf(i).concat("개월"));
		} // end for
		jcbPeriodDetail = new JComboBox<String>(dcbmPeriodDetail);
		jcbPeriodDetail.setEnabled(false);
		jcbPeriodDetail.setFont(font);
		
		JLabel jlblPublishable = new JLabel("발급 가능");
		jlblPublishable.setFont(font);
		jrbPublishableOk = new JRadioButton("예");
		jrbPublishableOk.setSelected(true);
		jrbPublishableOk.setFont(font);
		jrbPublishableNo = new JRadioButton("아니오");
		jrbPublishableNo.setFont(font);
		ButtonGroup bgPublishOption = new ButtonGroup();
		bgPublishOption.add(jrbPublishableOk);
		bgPublishOption.add(jrbPublishableNo);
		
		ImageIcon iiGoMain = new ImageIcon(getClass().getClassLoader().getResource("btn_go_back.png"));
		ImageIcon iiAddCoupon = new ImageIcon(getClass().getClassLoader().getResource("btn_add.png"));
		ImageIcon iiCancel = new ImageIcon(getClass().getClassLoader().getResource("btn_cancel.png"));
		
		jbtnGoMain = new JButton("<-");
		jbtnAddCoupon = new JButton("등록");
		jbtnCancel = new JButton("취소");
		
		jbtnGoMain.setIcon(iiGoMain);
		jbtnAddCoupon.setIcon(iiAddCoupon);
		jbtnCancel.setIcon(iiCancel);
		
		AddCouponEvent ace = new AddCouponEvent(this);
		jrbPeriodDefault.addActionListener(ace);
		jrbPeriodDetail.addActionListener(ace);
		jcbPeriodDetail.addActionListener(ace);
		arrJbtnPeriod[ExpirePeriod.MONTH1.ordinal()].addActionListener(ace);
		arrJbtnPeriod[ExpirePeriod.MONTH3.ordinal()].addActionListener(ace);
		arrJbtnPeriod[ExpirePeriod.YEAR1.ordinal()].addActionListener(ace);
		jbtnGoMain.addActionListener(ace);
		jbtnAddCoupon.addActionListener(ace);
		jbtnCancel.addActionListener(ace);
		addWindowListener(ace);
		
		setLayout(null);
		
		JPanel jpPeriod = new JPanel();
		jpPeriod.setLayout(null);
		jrbPeriodDefault.setBounds(30, 30, 120, 80);
		jrbPeriodDetail.setBounds(30, 140, 120, 80);
		arrJbtnPeriod[ExpirePeriod.MONTH1.ordinal()].setBounds(160, 20, 150, 100);
		arrJbtnPeriod[ExpirePeriod.MONTH3.ordinal()].setBounds(340, 20, 150, 100);
		arrJbtnPeriod[ExpirePeriod.YEAR1.ordinal()].setBounds(520, 20, 150, 100);
		jcbPeriodDetail.setBounds(160, 150, 150, 60);
		jpPeriod.add(jrbPeriodDefault);
		jpPeriod.add(jrbPeriodDetail);
		jpPeriod.add(arrJbtnPeriod[ExpirePeriod.MONTH1.ordinal()]);
		jpPeriod.add(arrJbtnPeriod[ExpirePeriod.MONTH3.ordinal()]);
		jpPeriod.add(arrJbtnPeriod[ExpirePeriod.YEAR1.ordinal()]);
		jpPeriod.add(jcbPeriodDetail);
		TitledBorder tbPeriod = new TitledBorder("이용기간");
		tbPeriod.setTitleFont(font);
		jpPeriod.setBorder(tbPeriod);
		
		jlblCouponKindName.setBounds(30, 10, 1000, 60);
		jlblDiscount.setBounds(30, 70, 100, 80);
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

	public JButton[] getArrJbtnPeriod() {
		return arrJbtnPeriod;
	}

	public ImageIcon[] getArrIiBtnPeriod() {
		return arrIiBtnPeriod;
	}

	public ImageIcon[] getArrIiBtnPeriodClick() {
		return arrIiBtnPeriodClick;
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
