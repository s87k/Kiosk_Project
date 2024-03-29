package kiosks;

import java.awt.Color;
import java.awt.Font;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;



@SuppressWarnings("serial")
public class UsingCouponDesign extends JDialog {
	private JTable couponList;
	private DefaultTableModel dtmCouponList;
	private JButton cancel, use;
	private Font font;
	private String phoneNumber;


	public UsingCouponDesign(PaymentPageDesign ppd, String phoneNumber) {
		super(ppd, "쿠폰 조회", true);
		this.phoneNumber = phoneNumber;
		setLayout(null);

		// 사용가능한 보유 쿠폰 라벨
		JLabel availableCoupon = new JLabel("사용가능한 쿠폰");
		font = availableCoupon.getFont();
		availableCoupon.setFont(font.deriveFont(Font.PLAIN, 25));
		availableCoupon.setBounds(160, 50, 200, 50);

		// 보유 쿠폰 JTable
		String[] couponTable = { "쿠폰 이름", "유효기간", "할인액", "쿠폰번호" };
		dtmCouponList = new DefaultTableModel(couponTable, 0) {
			// 셀 수정 불가
			public boolean isCellEditable(int row, int colum) {
				return false;
			}
		};
		
		couponList = new JTable(dtmCouponList);
		
		//테이블 정렬 : 유효기간을 기준으로 오름차순 정렬
//		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(dtmCouponList);
//		sorter.setComparator(1, (Object o1, Object o2) -> ((String) o1).compareTo((String) o2));
//		sorter.setSortKeys(Arrays.asList(new RowSorter.SortKey(1, SortOrder.ASCENDING)));
//		
//		couponList.setRowSorter(sorter);
//		
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
		UsingCouponEvent uce = new UsingCouponEvent(this, ppd);

		couponList.addMouseListener(uce);

		cancel.addActionListener(uce);
		use.addActionListener(uce);

		addWindowListener(uce);

		getContentPane().setBackground(new Color(0xECEDFA));
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	



//	public static void main(String[] args) {
//		new UsingCouponDesign();
//	}//main

}// class
