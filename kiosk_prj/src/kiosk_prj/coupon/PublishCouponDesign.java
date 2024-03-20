package kiosk_prj.coupon;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class PublishCouponDesign extends JDialog {
	
	ManageCouponDesign mcd;
	
	JTable jtabCoupType;
	DefaultTableModel dtmCoupType;
	JComboBox<String> jcbPubCondition;
	DefaultComboBoxModel<String> dcmPubCondition;
	JTextField jtfPubConditonVal;
	JButton jbtnGoMain, jbtnPublish, jbtnCancel;
	
	public PublishCouponDesign(ManageCouponDesign mcd) {
		super(mcd, "쿠폰 발급", true);
		
		this.mcd= mcd;
		
		String[] columnName = {"번호", "종류코드", "쿠폰명", "이용기간", "할인액", "발급가능"};
		dtmCoupType = new DefaultTableModel(null, columnName);
		jtabCoupType = new JTable(dtmCoupType);
		JScrollPane jspCoupType = new JScrollPane(jtabCoupType);
		
		JLabel jlblCondition = new JLabel("조건"); 
		JLabel jlblValue = new JLabel("값"); 
		dcmPubCondition = new DefaultComboBoxModel<String>();
		jcbPubCondition = new JComboBox<String>(dcmPubCondition);
		
		jtfPubConditonVal = new JTextField(10);
		jtfPubConditonVal.setDocument(new UnsignedIntegerDocument(5, 0, 99999));
		
		jbtnGoMain = new JButton("메인으로");
		jbtnPublish = new JButton("발급");
		jbtnCancel = new JButton("취소");
		
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
		jspCoupType.setBounds(30, 30, 680, 250);
		jpPubCondition.setBounds(30, 300, 680, 100);
		jbtnGoMain.setBounds(175, 500, 120, 80);
		jbtnPublish.setBounds(315, 500, 120, 80);
		jbtnCancel.setBounds(455, 500, 120, 80);
		
		add(jspCoupType);
		add(jpPubCondition);
		add(jbtnGoMain);
		add(jbtnPublish);
		add(jbtnCancel);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		// 455, 130, 1024, 768
		setBounds(mcd.getX() + 200, mcd.getY() + 100, 750, 650);
		setVisible(true);
	} // PublishCouponDesign
}
