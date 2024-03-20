package kiosk_prj.coupon;

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
public class ModifyCouponDesign extends JDialog {
	
	ManageCouponDesign mcd;
	
	JTextField jtfCouponKindName;
	JLabel jlblCouponKindNo, jlblDiscount, jlblPeriod;
	JComboBox<String> jcbPubCondition;
	DefaultComboBoxModel<String> dcmPubCondition;
	JTextField jtfPubConditonVal;
	JRadioButton jrbPublishableOk, jrbPublishableNo;
	JButton jbtnOk, jbtnCancel, jbtnDeleteCoup;
	
	public ModifyCouponDesign(ManageCouponDesign mcd) {
		super(mcd, "쿠폰 수정", true);
		
		this.mcd = mcd;
		
		JLabel jlblRowCouponKindName = new JLabel("쿠폰 이름");
		JLabel jlblRowCouponKindNo = new JLabel("쿠폰 종류 번호");
		JLabel jlblRowDiscount = new JLabel("할인 금액");
		JLabel jlblRowPeriod = new JLabel("이용 기간");
		
		jtfCouponKindName = new JTextField(20);
		jlblCouponKindNo = new JLabel();
		jlblDiscount = new JLabel();
		jlblPeriod = new JLabel();
		
		// 테스트용 라벨 setText
		jlblCouponKindNo.setText("0001");
		jlblDiscount.setText("1000원");
		jlblPeriod.setText("3 개월");
		
		dcmPubCondition = new DefaultComboBoxModel<String>();
		jcbPubCondition = new JComboBox<String>(dcmPubCondition);
		jtfPubConditonVal = new JTextField(20);
		
		JLabel jlblCondition = new JLabel("조건");
		JLabel jlblValue= new JLabel("값");
		
		JLabel jlblRowPublishable = new JLabel("발급 가능");
		jrbPublishableOk = new JRadioButton("예");
		jrbPublishableOk.setSelected(true);
		jrbPublishableNo = new JRadioButton("아니오");
		ButtonGroup bgPublishCondition = new ButtonGroup();
		bgPublishCondition.add(jrbPublishableOk);
		bgPublishCondition.add(jrbPublishableNo);
		
		jbtnOk = new JButton("확인");
		jbtnCancel = new JButton("취소");
		jbtnDeleteCoup = new JButton("삭제");
		
		JPanel jpPubCondition = new JPanel();
		jpPubCondition.setLayout(null);
		jlblCondition.setBounds(30, 20, 50, 60);
		jlblValue.setBounds(480, 20, 60, 60);
		jcbPubCondition.setBounds(80, 20, 370, 60);
		jtfPubConditonVal.setBounds(510, 20, 150, 60);
		jpPubCondition.add(jlblCondition);
		jpPubCondition.add(jlblValue);
		jpPubCondition.add(jcbPubCondition);
		jpPubCondition.add(jtfPubConditonVal);
		jpPubCondition.setBorder(new TitledBorder("발급 조건"));
		
		setLayout(null);
		
		jlblRowCouponKindName.setBounds(30, 10, 80, 60);
		jtfCouponKindName.setBounds(135, 10, 550, 60);
		jlblRowCouponKindNo.setBounds(30, 80, 80, 60);
		jlblCouponKindNo.setBounds(135, 80, 600, 60);
		jlblRowDiscount.setBounds(30, 150, 80, 60);
		jlblDiscount.setBounds(135, 150, 600, 60);
		jlblRowPeriod.setBounds(25, 220, 80, 60);
		jlblPeriod.setBounds(135, 220, 600, 60);
		jpPubCondition.setBounds(30, 290, 690, 120);
		jlblRowPublishable.setBounds(30, 420, 80, 60);
		jrbPublishableOk.setBounds(135, 420, 120, 60);
		jrbPublishableNo.setBounds(270, 420, 120, 60);
		jbtnOk.setBounds(175, 500, 120, 80);
		jbtnCancel.setBounds(315, 500, 120, 80);
		jbtnDeleteCoup.setBounds(455, 500, 120, 80);
		
		add(jlblRowCouponKindName);
		add(jtfCouponKindName);
		add(jlblRowCouponKindNo);
		add(jlblCouponKindNo);
		add(jlblRowDiscount);
		add(jlblDiscount);
		add(jlblRowPeriod);
		add(jlblPeriod);
		add(jpPubCondition);
		add(jlblRowPublishable);
		add(jrbPublishableOk);
		add(jrbPublishableNo);
		add(jbtnOk);
		add(jbtnCancel);
		add(jbtnDeleteCoup);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		// 455, 130, 1024, 768
		setBounds(mcd.getX() + 200, mcd.getY() + 100, 750, 650);
		setVisible(true);
	} // ModifyCouponDesign
} // class
