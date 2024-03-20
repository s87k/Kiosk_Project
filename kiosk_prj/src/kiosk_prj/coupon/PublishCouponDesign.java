package kiosk_prj.coupon;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class PublishCouponDesign extends JDialog {
	
	JTable jtabCoupType;
	DefaultTableModel dtmCoupType;
	JComboBox<String> jcbPubCondition;
	DefaultComboBoxModel<String> dcmPubCondition;
	JTextField jtfPubConditonVal;
	JButton jbtnGoBack, jbtnAdd, jbtnCancel;
	
	public PublishCouponDesign(ManageCouponDesign mcd) {
		super(mcd, "쿠폰 발급", true);
		
		String[] columnName = {"번호", "종류코드", "쿠폰명", "이용기간", "할인액", "발급가능"};
		dtmCoupType = new DefaultTableModel(null, columnName);
		jtabCoupType = new JTable(dtmCoupType);
		
		dcmPubCondition = new DefaultComboBoxModel<String>();
		jcbPubCondition = new JComboBox<String>(dcmPubCondition);
		
		jtfPubConditonVal = new JTextField(10);
		jtfPubConditonVal.setDocument(new UnsignedIntegerDocument(5, 0, 99999));
		
		JPanel jpPubCondition = new JPanel();
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		// 455, 130, 1024, 768
		setBounds(mcd.getX() + 200, mcd.getY() + 100, 750, 650);
		setVisible(true);
	} // PublishCouponDesign
}
