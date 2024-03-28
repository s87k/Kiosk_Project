package kiosk_prj.shopClose;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kiosk_prj.adminMain.AdminMainPageDesign;

@SuppressWarnings("serial")
public class ShopCloseDesign extends JDialog {
	
	private AdminMainPageDesign ampd;
	private JLabel mainJlOpenDate;
	
	private JLabel jlTitle, jlTotalCount, jlTotalAmount;
	private JTable jtClose;
	private DefaultTableModel dtmClose;
	private JButton jbExit, jbInsertClose;
	
	public ShopCloseDesign(AdminMainPageDesign ampd) {
		super(ampd, "마감", true);
		this.ampd = ampd;
		mainJlOpenDate = ampd.getJlOpenDate();
		
		//이미지
		ImageIcon imgBackground = new ImageIcon(getClass().getClassLoader().getResource("shopClose.png"));
		ImageIcon imgExit = new ImageIcon(getClass().getClassLoader().getResource("BTexit.png"));
		ImageIcon imginsertClose = new ImageIcon(getClass().getClassLoader().getResource("ShopCloseBt.png"));
		
		//컴포넌트
		JLabel lbBackground = new JLabel(imgBackground);
		String[] coluumnName = {"그룹","메뉴","수량","금액"};
		dtmClose = new DefaultTableModel(coluumnName,0);
		jbInsertClose = new JButton(imginsertClose);
		jbExit = new JButton(imgExit);
		jlTitle = new JLabel("  메뉴별 집계");
		jlTotalCount = new JLabel("00잔 ");
		jlTotalAmount = new JLabel(" 00,000원");
		jtClose = new JTable(dtmClose){
			//테이블 수정 불가설정
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JScrollPane jspJtClose = new JScrollPane(jtClose);
		
		//테이블 컬럼 넓이 변경
		jtClose.getColumnModel().getColumn(0).setPreferredWidth(100);
		jtClose.getColumnModel().getColumn(1).setPreferredWidth(405);
		jtClose.getColumnModel().getColumn(2).setPreferredWidth(70);
		jtClose.getColumnModel().getColumn(3).setPreferredWidth(150);
		jtClose.setRowHeight(28);

		//폰트
		Font font = new Font("맑은 고딕", Font.BOLD, 21);
		Font middleFont = new Font("맑은 고딕", Font.BOLD, 28);
		Font bigggFont = new Font("맑은 고딕", Font.BOLD, 35);
		jtClose.setFont(font);
		jlTitle.setFont(bigggFont);
		jlTotalCount.setFont(middleFont);
		jlTotalAmount.setFont(middleFont);
		
		//텍스트 중앙에 정렬
		jlTotalCount.setHorizontalAlignment(JLabel.RIGHT);
		
		//색.
		jlTitle.setOpaque(true);
		jlTitle.setBackground(new Color(0xDCDDE8));
		jlTotalCount.setOpaque(true);
		jlTotalCount.setBackground(new Color(0xDCDDE8));
		jlTotalAmount.setOpaque(true);
		jlTotalAmount.setBackground(new Color(0xC6C7D1));
		
		jtClose.setBackground(Color.WHITE); //테이블 배경색
		jtClose.setGridColor(Color.GRAY); //테두리 색
		
		
		
		//배치관리자 해제
		setLayout(null);
		
		//컴포넌트 배치
		jbInsertClose.setBounds(800,600,165,80);
		jlTitle.setBounds(40,100,725,50);
		jspJtClose.setBounds(40,150,725,500);
		jlTotalCount.setBounds(40,647,525,50);
		jlTotalAmount.setBounds(565,647,200,50);
		jbExit.setBounds(800,10,165,43);
		
		lbBackground.setBounds(0,0,1024,768);
		
		//컴포넌트 등록
		add(jbInsertClose);
		add(jlTitle);
		add(jspJtClose);
		add(jlTotalCount);
		add(jlTotalAmount);
		add(jbExit);
		
		
		add(lbBackground);
		
		//이벤트 등록
		ShopCloseConfirmDesign sccd = new ShopCloseConfirmDesign();
		ShopCloseEvent sce = new ShopCloseEvent(this, sccd);
		jbInsertClose.addActionListener(sce);
		jbExit.addActionListener(sce);
		
		setSize(1024,768);
		setLocation(ampd.getX(),ampd.getY());
		setResizable(false);//창 크기 변경 불가능
		setVisible(true);
	}//ShopCloseDesign

	public AdminMainPageDesign getAmpd() {
		return ampd;
	}

	public JLabel getJlTitle() {
		return jlTitle;
	}

	public JLabel getJlTotalCount() {
		return jlTotalCount;
	}

	public JLabel getJlTotalAmount() {
		return jlTotalAmount;
	}

	public JTable getJtClose() {
		return jtClose;
	}

	public DefaultTableModel getDtmClose() {
		return dtmClose;
	}

	public JButton getJbExit() {
		return jbExit;
	}

	public JButton getJbInsertClose() {
		return jbInsertClose;
	}

	public JLabel getMainJlOpenDate() {
		return mainJlOpenDate;
	}
	
}//class
