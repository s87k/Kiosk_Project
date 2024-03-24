package kiosk_prj.coupon.view;

import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import kiosk_prj.coupon.UnsignedIntegerDocument;
import kiosk_prj.coupon.controller.PublishCouponEvent;

@SuppressWarnings("serial")
public class PublishCouponDesign extends JDialog {
	
	private ManageCouponDesign mcd;
	
	private JTable jtabCoupType;
	private DefaultTableModel dtmCoupType;
	private JComboBox<String> jcbPubCondition;
	private DefaultComboBoxModel<String> dcmPubCondition;
	private JTextField jtfPubConditonVal;
	private JButton jbtnGoMain, jbtnPublish, jbtnCancel;
	
	public PublishCouponDesign(ManageCouponDesign mcd) {
		super(mcd, "쿠폰 발급", true);
		
		this.mcd= mcd;
		
		String[] columnName = {"번호", "종류코드", "쿠폰명", "이용기간", "할인액", "발급가능"};
		dtmCoupType = new DefaultTableModel(null, columnName) {
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		jtabCoupType = new JTable(dtmCoupType);
		jtabCoupType.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane jspCoupType = new JScrollPane(jtabCoupType);
		
		JLabel jlblCondition = new JLabel("조건"); 
		JLabel jlblValue = new JLabel("값"); 
		dcmPubCondition = new DefaultComboBoxModel<String>();
		jcbPubCondition = new JComboBox<String>(dcmPubCondition);
		
		jtfPubConditonVal = new JTextField(10);
		jtfPubConditonVal.setDocument(new UnsignedIntegerDocument(8, 1, 99999999));
		
		jbtnGoMain = new JButton("메인으로");
		jbtnPublish = new JButton("발급");
		jbtnCancel = new JButton("취소");
		
		PublishCouponEvent pce = new PublishCouponEvent(this);
		jbtnGoMain.addActionListener(pce);
		jbtnPublish.addActionListener(pce);
		jbtnCancel.addActionListener(pce);
		addWindowListener(pce);
		
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
		
		try {
			pce.searchPublishableCouponType();
		} catch (SQLException se) {
			JOptionPane.showMessageDialog(mcd, "추가된 쿠폰을 목록을 조회하는 데 문제가 발생했습니다");
			se.printStackTrace();
		} // end catch
		try {
			pce.searchAllCoupPubConditionType();
		} catch (SQLException se) {
			JOptionPane.showMessageDialog(mcd, "쿠폰 발급 조건을 조회하는 데 문제가 발생했습니다");
			se.printStackTrace();
		} // end catch
		
		// 455, 130, 1024, 768
		setBounds(mcd.getX() + 200, mcd.getY() + 100, 750, 650);
		setVisible(true);
	} // PublishCouponDesign

	public ManageCouponDesign getMcd() {
		return mcd;
	}

	public JTable getJtabCoupType() {
		return jtabCoupType;
	}

	public DefaultTableModel getDtmCoupType() {
		return dtmCoupType;
	}

	public JComboBox<String> getJcbPubCondition() {
		return jcbPubCondition;
	}

	public DefaultComboBoxModel<String> getDcmPubCondition() {
		return dcmPubCondition;
	}

	public JTextField getJtfPubConditonVal() {
		return jtfPubConditonVal;
	}

	public JButton getJbtnGoMain() {
		return jbtnGoMain;
	}

	public JButton getJbtnPublish() {
		return jbtnPublish;
	}

	public JButton getJbtnCancel() {
		return jbtnCancel;
	}
	
}
