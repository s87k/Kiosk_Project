package kiosks;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kiosks.JoinMembershipEvent;
import kiosks.UsingCouponEvent;

public class UsingCouponDesign extends JDialog {
	private PhoneNumDesign pnd;
	private JTable couponList;
	private DefaultTableModel dtmCouponList;
	private JButton cancel, use;
	private Font font;

	public UsingCouponDesign(PhoneNumDesign pnd, String title) {
		super(pnd, "쿠폰 조회");
		setLayout(null);

		// 사용가능한 보유 쿠폰 라벨
		JLabel availableCoupon = new JLabel("사용가능한 쿠폰");
		font = availableCoupon.getFont();
		availableCoupon.setFont(font.deriveFont(Font.PLAIN, 25));
		availableCoupon.setBounds(160, 50, 200, 50);

		// 보유 쿠폰 JTable
		String[] couponTable = { "쿠폰 이름", "유효기간" };
		dtmCouponList = new DefaultTableModel(couponTable, 0) {
			// 셀 수정 불가
			public boolean isCellEditable(int row, int colum) {
				return false;
			}
		};
		couponList = new JTable(dtmCouponList);
		// 열 이동 불가
		couponList.getTableHeader().setReorderingAllowed(false);

		JScrollPane scrollPane = new JScrollPane(couponList);
		scrollPane.setBorder(BorderFactory.createTitledBorder("쿠폰 목록"));
		scrollPane.setBounds(70, 130, 350, 300);

		// 취소 & 사용하기 쿠폰
		cancel = new JButton("취소");
		cancel.setBounds(140, 450, 100, 30);

		use = new JButton("사용하기");
		use.setBounds(270, 450, 100, 30);

		add(availableCoupon);
		add(scrollPane);
		add(cancel);
		add(use);

		// actionListener 추가
		UsingCouponEvent uce = new UsingCouponEvent(this, pnd);

		couponList.addMouseListener(uce);

		cancel.addActionListener(uce);
		use.addActionListener(uce);

		addWindowListener(uce);

		setSize(500, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);

	}// UsingCouponDesign

	public JTable getCouponList() {
		return couponList;
	}

	public DefaultTableModel getDtmCouponList() {
		return dtmCouponList;
	}

	public JButton getCancel() {
		return cancel;
	}

	public JButton getUse() {
		return use;
	}

//	public static void main(String[] args) {
//		new UsingCouponDesign();
//	}//main

}// class
