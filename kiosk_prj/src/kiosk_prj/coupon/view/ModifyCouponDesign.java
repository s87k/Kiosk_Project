package kiosk_prj.coupon.view;

import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import static java.lang.String.valueOf;

import java.awt.Font;

import kiosk_prj.coupon.UnsignedIntegerDocument;
import kiosk_prj.coupon.controller.ModifyCouponEvent;
import kiosk_prj.coupon.dao.CouponKindDAO;
import kiosk_prj.coupon.vo.CouponKindVO;
import kiosk_prj.coupon.vo.CouponPublishVO;

@SuppressWarnings("serial")
public class ModifyCouponDesign extends JDialog {
	
	private ManageCouponDesign mcd;
	private CouponPublishVO cpVO;
	
	private JTextField jtfCouponKindName;
	private JTextField jtfCouponKindNo, jtfDiscount, jtfPeriod;
	private JComboBox<String> jcbPubCondition;
	private DefaultComboBoxModel<String> dcmPubCondition;
	private JTextField jtfPubConditonVal;
	private JRadioButton jrbPublishableOk, jrbPublishableNo;
	private JButton jbtnOk, jbtnCancel, jbtnDeleteCoup;
	
	private JButton jbtnGoMain;
	
	public static final int PUBLISH_NOT = 0;
	
	public ModifyCouponDesign(ManageCouponDesign mcd, CouponPublishVO cpVO) {
		super(mcd, "쿠폰 수정", true);
		
		this.mcd = mcd;
		this.cpVO = cpVO;
		
		Font font = new Font("맑은 고딕", Font.BOLD, 20);
		
		JLabel jlblRowCouponKindName = new JLabel("쿠폰 이름");
		JLabel jlblRowCouponKindNo = new JLabel("쿠폰 종류 번호");
		JLabel jlblRowDiscount = new JLabel("할인 금액");
		JLabel jlblRowPeriod = new JLabel("이용 기간");
		
		jlblRowCouponKindName.setFont(font);
		jlblRowCouponKindNo.setFont(font);
		jlblRowDiscount.setFont(font);
		jlblRowPeriod.setFont(font);
		
		jtfCouponKindName = new JTextField(20);
		jtfCouponKindNo = new JTextField(20);
		jtfDiscount = new JTextField(20);
		jtfPeriod = new JTextField(20);
		
		jtfCouponKindNo.setEditable(false);
		jtfDiscount.setEditable(false);
		jtfPeriod.setEditable(false);
		
		jtfCouponKindName.setFont(font);
		jtfCouponKindNo.setFont(font);
		jtfDiscount.setFont(font);
		jtfPeriod.setFont(font);
				
		dcmPubCondition = new DefaultComboBoxModel<String>();
		jcbPubCondition = new JComboBox<String>(dcmPubCondition);
		jcbPubCondition.setFont(font);
		jtfPubConditonVal = new JTextField(20);
		jtfPubConditonVal.setDocument(new UnsignedIntegerDocument(8, 1, 99999999));
		jtfPubConditonVal.setFont(font);
				
		JLabel jlblCondition = new JLabel("조건");
		JLabel jlblValue= new JLabel("값");
		
		jlblCondition.setFont(font);
		jlblValue.setFont(font);
		
		JLabel jlblRowPublishable = new JLabel();
		jlblRowPublishable.setFont(font);
		jrbPublishableOk = new JRadioButton();
		jrbPublishableOk.setSelected(true);
		jrbPublishableNo = new JRadioButton();
		jrbPublishableOk.setFont(font);
		jrbPublishableNo.setFont(font);
		ButtonGroup bgPublishCondition = new ButtonGroup();
		bgPublishCondition.add(jrbPublishableOk);
		bgPublishCondition.add(jrbPublishableNo);
		
		ImageIcon iiOk = new ImageIcon(getClass().getClassLoader().getResource("btn_modify.png"));
		ImageIcon iiCancel = new ImageIcon(getClass().getClassLoader().getResource("btn_cancel.png"));
		
		jbtnOk = new JButton(iiOk);
		jbtnCancel = new JButton(iiCancel);
		
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
		
		ModifyCouponEvent mce = new ModifyCouponEvent(this);
		jbtnCancel.addActionListener(mce);
		jbtnOk.addActionListener(mce);
		
		CouponKindVO ckVO = null;
		CouponKindDAO ckDAO = CouponKindDAO.getInstance();
		try {
			 ckVO = ckDAO.selectOneCoupKind(cpVO.getCoupKindNo());
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(mcd, "선택된 쿠폰 조회에 실패했습니다");
			return;
		} // end catch
		
		jtfCouponKindNo.setText(valueOf(ckVO.getCoupKindNo()));
		jtfDiscount.setText(valueOf(ckVO.getDiscount()).concat("원"));
		jtfPeriod.setText(valueOf(ckVO.getExpiresPeriod()).concat("개월"));
		jtfCouponKindName.setText(ckVO.getCoupKindName());
		
		if(cpVO.getConditionTypeNo() == PUBLISH_NOT) {
			jcbPubCondition.setEnabled(false);
			jtfPubConditonVal.setEnabled(false);
			jlblRowPublishable.setText("발급 가능");
			jrbPublishableOk.setText("예");
			jrbPublishableNo.setText("아니오");
			if(ckVO.isFlagPublishable()) {
				jrbPublishableOk.setSelected(true);
			} else {
				jrbPublishableNo.setSelected(true);
			} // end else
			ImageIcon iiDeleteCoup = new ImageIcon(getClass().getClassLoader().getResource("btn_delete.png"));
			jbtnDeleteCoup = new JButton(iiDeleteCoup);
			jbtnDeleteCoup.addActionListener(mce);
			jbtnDeleteCoup.setBounds(175, 500, 120, 80);	
			add(jbtnDeleteCoup);
		} else {
			try {
				mce.searchAllCoupPubConditionType();
				jtfPubConditonVal.setText(valueOf(cpVO.getConditionPrice()));
				jcbPubCondition.setSelectedIndex(cpVO.getConditionTypeNo() - 1);
				jlblRowPublishable.setText("자동 발급 활성화");
				jrbPublishableOk.setText("활성화");
				jrbPublishableNo.setText("비활성화");
				if(cpVO.isFlagDisable()) {
					jrbPublishableOk.setSelected(true);
				} else {
					jrbPublishableNo.setSelected(true);
				} // end else
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(mcd, "쿠폰 발급 조건 조회에 실패했습니다");
				e.printStackTrace();
				return;
			} // end catch
		} // end if
		
		setLayout(null);
		
		jlblRowCouponKindName.setBounds(30, 10, 140, 60);
		jtfCouponKindName.setBounds(175, 10, 540, 60);
		jlblRowCouponKindNo.setBounds(30, 80, 140, 60);
		jtfCouponKindNo.setBounds(175, 80, 540, 60);
		jlblRowDiscount.setBounds(30, 150, 140, 60);
		jtfDiscount.setBounds(175, 150, 540, 60);
		jlblRowPeriod.setBounds(25, 220, 140, 60);
		jtfPeriod.setBounds(175, 220, 540, 60);
		jpPubCondition.setBounds(30, 290, 690, 120);
		jlblRowPublishable.setBounds(30, 420, 180, 60);
		jrbPublishableOk.setBounds(195, 420, 120, 60);
		jrbPublishableNo.setBounds(330, 420, 120, 60);
		jbtnOk.setBounds(315, 500, 120, 80);	
		jbtnCancel.setBounds(455, 500, 120, 80);	
		
		add(jlblRowCouponKindName);
		add(jtfCouponKindName);
		add(jlblRowCouponKindNo);
		add(jtfCouponKindNo);
		add(jlblRowDiscount);
		add(jtfDiscount);
		add(jlblRowPeriod);
		add(jtfPeriod);
		add(jpPubCondition);
		add(jlblRowPublishable);
		add(jrbPublishableOk);
		add(jrbPublishableNo);
		add(jbtnOk);
		add(jbtnCancel);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		// 455, 130, 1024, 768
		setBounds(mcd.getX() + 200, mcd.getY() + 100, 750, 650);
		setVisible(true);
	} // ModifyCouponDesign

	public ManageCouponDesign getMcd() {
		return mcd;
	}

	public CouponPublishVO getCpVO() {
		return cpVO;
	}

	public JTextField getJtfCouponKindName() {
		return jtfCouponKindName;
	}

	public JTextField getJlblCouponKindNo() {
		return jtfCouponKindNo;
	}

	public JTextField getJlblDiscount() {
		return jtfDiscount;
	}

	public JTextField getJlblPeriod() {
		return jtfPeriod;
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

	public JRadioButton getJrbPublishableOk() {
		return jrbPublishableOk;
	}

	public JRadioButton getJrbPublishableNo() {
		return jrbPublishableNo;
	}

	public JButton getJbtnOk() {
		return jbtnOk;
	}

	public JButton getJbtnCancel() {
		return jbtnCancel;
	}

	public JButton getJbtnDeleteCoup() {
		return jbtnDeleteCoup;
	}

	public JButton getJbtnGoMain() {
		return jbtnGoMain;
	}
	
} // class
