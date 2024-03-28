package kiosk_prj.orderStatus;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import kiosk_prj.adminMain.AdminMainPageDesign;

@SuppressWarnings("serial")
public class OrderStatusDesign extends JDialog {
	
	private AdminMainPageDesign ampd;
	private JLabel jlOpenDate;
	
	private JLabel jlTotalOrder, jlTotalPrice;
	private JButton jbMenuComplete, jbExit;
	private JTextArea jtaTotalOrderInfo, jtaDetailOrderInfo;
	private JTable jtOrderStatus;
	private DefaultTableModel dtmOrderStatus;
	
	private String openDate;
	
	public OrderStatusDesign(AdminMainPageDesign ampd) {
		super(ampd, "주문 현황 목록", true);
		this.ampd = ampd;
		jlOpenDate = ampd.getJlOpenDate();
		
		//이미지
		ImageIcon imgBackground = new ImageIcon(getClass().getClassLoader().getResource("OrderStatus.png"));
		ImageIcon exit = new ImageIcon(getClass().getClassLoader().getResource("exit.png"));
		
		//컴포넌트
		JLabel lbBackground = new JLabel(imgBackground);
		String[] coluumnName = {"번호","주문시간","상품명","결제금액","진행현황","완료"};
		dtmOrderStatus = new DefaultTableModel(coluumnName,0);
		jtOrderStatus = new JTable(dtmOrderStatus){
			//테이블 수정 불가설정
			@Override
			public boolean isCellEditable(int row, int column) {
				if(column != 5) {
					return false;
				}
				return true;
			}
		};
		JScrollPane jspJtOrderStatus = new JScrollPane(jtOrderStatus);
		jlTotalOrder = new JLabel("총 주문");
		jlTotalPrice = new JLabel("0원");
//		jbMenuComplete = new JButton(); //누르면 진행현황 바뀌는용도...
		jbExit = new JButton(exit);
		jtaTotalOrderInfo = new JTextArea();
		jtaDetailOrderInfo = new JTextArea();

		
		JLabel line1 = new JLabel();
		JLabel line2 = new JLabel();
		JLabel line3 = new JLabel();
		line1.setBorder(new LineBorder(Color.BLACK,1,true));
		line2.setBorder(new LineBorder(Color.BLACK,1,true));
		line3.setBorder(new LineBorder(Color.BLACK,1,true));	//drawline 쓸 줄 몰라서 라벨로 그음^^!
		
		//테이블 컬럼 넓이 변경
		jtOrderStatus.getColumnModel().getColumn(0).setPreferredWidth(40);
		jtOrderStatus.getColumnModel().getColumn(1).setPreferredWidth(100);
		jtOrderStatus.getColumnModel().getColumn(2).setPreferredWidth(280);
		jtOrderStatus.getColumnModel().getColumn(3).setPreferredWidth(90);
		jtOrderStatus.getColumnModel().getColumn(4).setPreferredWidth(100);
		jtOrderStatus.getColumnModel().getColumn(5).setPreferredWidth(40);
		
		jtOrderStatus.setRowHeight(28);
		
		//폰트
		Font font = new Font("맑은 고딕", Font.BOLD, 21);
		Font biggg = new Font("맑은 고딕", Font.BOLD, 30);
		jtOrderStatus.setFont(new Font("맑은 고딕", Font.PLAIN, 21));
		jtaTotalOrderInfo.setFont(font);
		jtaDetailOrderInfo.setFont(font);
		jlTotalOrder.setFont(biggg);
		jlTotalPrice.setFont(biggg);
		
		//텍스트 중앙에 정렬
		jlTotalPrice.setHorizontalAlignment(JLabel.CENTER);
		
		//텍스트 수정 불가능
		jtaTotalOrderInfo.setEditable(false);
		jtaDetailOrderInfo.setEditable(false);
		
		//색..?
		jlTotalPrice.setOpaque(true);
		jlTotalPrice.setBackground(new Color(0x9FBAC2));
		
		//배치관리자 해제
		setLayout(null);
		
		//컴포넌트 배치
		jspJtOrderStatus.setBounds(20,80,650,550);
		jlTotalOrder.setBounds(690,70,200,50);
		jlTotalPrice.setBounds(685,645,305,85);
		jtaTotalOrderInfo.setBounds(690,130,295,270);
		jtaDetailOrderInfo.setBounds(690,420,295,198);
		jbExit.setBounds(0,645,670,85);
		line1.setBounds(685,120,305,1);
		line2.setBounds(685,410,305,1);
		line3.setBounds(685,628,305,1);
		lbBackground.setBounds(0,0,1024,768); //제일 나중에
		
		//컴포넌트 등록
		add(jspJtOrderStatus);
		add(jlTotalOrder);
		add(jlTotalPrice);
		add(jtaTotalOrderInfo);
		add(jtaDetailOrderInfo);
		add(jbExit);
		add(line1);
		add(line2);
		add(line3);
		add(lbBackground); //제일 나중에
		
		
		//이벤트 등록
		OrderStatusEvent ose = new OrderStatusEvent(this);
		jbExit.addActionListener(ose);
		jtOrderStatus.addMouseListener(ose);
		
		
		setSize(1024,768);
		setLocation(ampd.getX(),ampd.getY());
		setResizable(false);//창 크기 변경 불가능
		setVisible(true);
	}//OrderStatusDesgin

	public AdminMainPageDesign getAmpd() {
		return ampd;
	}

	public JLabel getJlTotalOrder() {
		return jlTotalOrder;
	}

	public JLabel getJlTotalPrice() {
		return jlTotalPrice;
	}

	public JButton getJbMenuComplete() {
		return jbMenuComplete;
	}

	public JButton getJbExit() {
		return jbExit;
	}

	public JTextArea getJtaTotalOrderInfo() {
		return jtaTotalOrderInfo;
	}

	public JTextArea getJtaDetailOrderInfo() {
		return jtaDetailOrderInfo;
	}

	public JTable getJtOrderStatus() {
		return jtOrderStatus;
	}

	public DefaultTableModel getDtmOrderStatus() {
		return dtmOrderStatus;
	}

	public String getOpenDate() {
		return openDate;
	}

	public JLabel getJlOpenDate() {
		return jlOpenDate;
	}
	
}//class
