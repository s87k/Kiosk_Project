package kiosk_prj.coupon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.String.valueOf;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class AddCouponDesign extends JDialog implements ActionListener {
	
	JTextField jtfCouponKindName, jtfDiscount;
	JButton jbtnMonth1, jbtnMonth3, jbtnYear1;
	JComboBox<String> jcbPeriodDetail;
	DefaultComboBoxModel<String> dcbmPeriodDetail;
	JRadioButton jrbPeriodDefault, jrbPeriodDetail;
	JRadioButton jrbPublishableOk, jrbPublishableNo;
	JButton jbtnGoMain, jbtnAddCoupon, jbtnCancel;
	
	public AddCouponDesign(ManageCouponDesign mcd) {
		super(mcd, "쿠폰 등록", true);
		
		JLabel jlblCouponKindName = new JLabel("쿠폰 이름");
		JLabel jlblDiscount = new JLabel("할인 금액");
		
		jtfCouponKindName = new JTextField(20);
		jtfDiscount = new JTextField(20);
		jtfDiscount.setDocument(new UnsignedIntegerDocument(5, 0, 99999));
		
		jrbPeriodDefault = new JRadioButton("기본 설정");
		jbtnMonth1 = new JButton("1개월");
		jbtnMonth3 = new JButton("3개월");
		jbtnYear1 = new JButton("1년");
		jrbPeriodDetail = new JRadioButton("상세 설정");
		
		dcbmPeriodDetail = new DefaultComboBoxModel<String>();
		for(int i = 1; i < 13; i++) {
			dcbmPeriodDetail.addElement(valueOf(i).concat("개월"));
		} // end for
		jcbPeriodDetail = new JComboBox<String>(dcbmPeriodDetail);
		
		jrbPublishableOk = new JRadioButton("예");
		jrbPublishableNo = new JRadioButton("아니오");
		
		jbtnGoMain = new JButton("메인으로");
		jbtnAddCoupon = new JButton("등록");
		jbtnCancel = new JButton("취소");
		
		setLayout(null);
		
		jlblCouponKindName.setBounds(30, 20, 60, 40);
		jlblDiscount.setBounds(30, 60, 60, 40);
		
		jtfCouponKindName.setBounds(100, 20, 100, 40);
		jtfDiscount.setBounds(100, 60, 100, 40);
		
		add(jlblCouponKindName);
		add(jlblDiscount);
		add(jtfCouponKindName);
		add(jtfDiscount);
		
		// 455, 130, 1024, 768
		setBounds(mcd.getX() + 200, mcd.getY() + 100, 750, 650);
		setVisible(true);
	} // AddCouponDesign

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	} // actionPerformed

} // class
