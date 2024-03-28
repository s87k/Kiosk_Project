package kiosk_prj.coupon.view;

import java.awt.Font;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
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
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import kiosk_prj.coupon.UnsignedIntegerDocument;
import kiosk_prj.coupon.controller.PublishCouponEvent;

@SuppressWarnings("serial")
public class PublishCouponDesign extends JDialog {
	
	private ManageCouponDesign mcd;
	
	private JTable jtabCoupKind;
	private DefaultTableModel dtmCoupKind;
	private JComboBox<String> jcbPubCondition;
	private DefaultComboBoxModel<String> dcmPubCondition;
	private JTextField jtfPubConditonVal;
	private JButton jbtnGoBack, jbtnPublish, jbtnCancel;
	
	public PublishCouponDesign(ManageCouponDesign mcd) {
		super(mcd, "쿠폰 발급", true);
		
		this.mcd= mcd;
		
		Font font = new Font("맑은 고딕", Font.BOLD, 20);
		
		String[] columnName = {"번호", "종류코드", "쿠폰명", "이용기간", "할인액", "발급가능"};
		dtmCoupKind = new DefaultTableModel(null, columnName) {
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		jtabCoupKind = new JTable(dtmCoupKind);
		jtabCoupKind.setFont(font);
		jtabCoupKind.setRowHeight(25);
		jtabCoupKind.getColumn(columnName[0]).setPreferredWidth(5);
		jtabCoupKind.getColumn(columnName[1]).setPreferredWidth(5);
		jtabCoupKind.getColumn(columnName[2]).setPreferredWidth(200);
		jtabCoupKind.getColumn(columnName[3]).setPreferredWidth(5);
		jtabCoupKind.getColumn(columnName[4]).setPreferredWidth(60);
		jtabCoupKind.getColumn(columnName[5]).setPreferredWidth(20);
		jtabCoupKind.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		DefaultTableCellRenderer dtcrCenter = new DefaultTableCellRenderer();
		DefaultTableCellRenderer dtcrRight = new DefaultTableCellRenderer();
		dtcrCenter.setHorizontalAlignment(SwingConstants.CENTER); 
		dtcrRight.setHorizontalAlignment(SwingConstants.RIGHT); 
		TableColumnModel tcm = jtabCoupKind.getColumnModel();
		
		tcm.getColumn(0).setCellRenderer(dtcrRight);
		tcm.getColumn(1).setCellRenderer(dtcrRight);
		tcm.getColumn(3).setCellRenderer(dtcrRight);
		tcm.getColumn(4).setCellRenderer(dtcrRight);
		tcm.getColumn(5).setCellRenderer(dtcrCenter);

		JScrollPane jspCoupKind = new JScrollPane(jtabCoupKind);
		
		JLabel jlblCondition = new JLabel("조건"); 
		jlblCondition.setFont(font);
		JLabel jlblValue = new JLabel("값");
		jlblValue.setFont(font);
		dcmPubCondition = new DefaultComboBoxModel<String>();
		jcbPubCondition = new JComboBox<String>(dcmPubCondition);
		jcbPubCondition.setFont(font);
		
		jtfPubConditonVal = new JTextField(10);
		jtfPubConditonVal.setDocument(new UnsignedIntegerDocument(8, 1, 99999999));
		jtfPubConditonVal.setFont(font);
		
		ImageIcon iiGoBack = new ImageIcon(getClass().getClassLoader().getResource("btn_go_back.png"));
		ImageIcon iiPublish = new ImageIcon(getClass().getClassLoader().getResource("btn_pub.png"));
		ImageIcon iiCancel = new ImageIcon(getClass().getClassLoader().getResource("btn_cancel.png"));
		
		jbtnGoBack = new JButton(iiGoBack);
		jbtnPublish = new JButton(iiPublish);
		jbtnCancel = new JButton(iiCancel);
		
		PublishCouponEvent pce = new PublishCouponEvent(this);
		jbtnGoBack.addActionListener(pce);
		jbtnPublish.addActionListener(pce);
		jbtnCancel.addActionListener(pce);
		addWindowListener(pce);
		
		JPanel jpPubCondition = new JPanel();
		jpPubCondition.setLayout(null);
		jlblCondition.setBounds(30, 40, 50, 60);
		jlblValue.setBounds(480, 40, 60, 60);
		jcbPubCondition.setBounds(80, 40, 370, 60);
		jtfPubConditonVal.setBounds(510, 40, 150, 60);
		jpPubCondition.add(jlblCondition);
		jpPubCondition.add(jlblValue);
		jpPubCondition.add(jcbPubCondition);
		jpPubCondition.add(jtfPubConditonVal);
		TitledBorder tbCondition = new TitledBorder("발급 조건");
		tbCondition.setTitleFont(font);
		jpPubCondition.setBorder(tbCondition);
		
		setLayout(null);
		jspCoupKind.setBounds(30, 30, 680, 250);
		jpPubCondition.setBounds(30, 300, 680, 140);
		jbtnGoBack.setBounds(175, 500, 120, 80);
		jbtnPublish.setBounds(315, 500, 120, 80);
		jbtnCancel.setBounds(455, 500, 120, 80);
		
		add(jspCoupKind);
		add(jpPubCondition);
		add(jbtnGoBack);
		add(jbtnPublish);
		add(jbtnCancel);
		
		try {
			pce.searchPublishableCouponKind();
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

	public JTable getJtabCoupKind() {
		return jtabCoupKind;
	}

	public DefaultTableModel getDtmCoupKind() {
		return dtmCoupKind;
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

	public JButton getJbtnGoBack() {
		return jbtnGoBack;
	}

	public JButton getJbtnPublish() {
		return jbtnPublish;
	}

	public JButton getJbtnCancel() {
		return jbtnCancel;
	}
	
}
