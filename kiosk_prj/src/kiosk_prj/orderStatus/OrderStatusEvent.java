package kiosk_prj.orderStatus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class OrderStatusEvent extends WindowAdapter implements ActionListener, MouseListener {
	
	private OrderStatusDesign osd;
	private JButton jbExit;
	private JTable jtOrderStatus;
	private String openDate;
	private DefaultTableModel dtmOrderStatus;
	private JTextArea jtaTotalOrderInfo, jtaDetailOrderInfo;
	private JLabel jlTotalPrice;
	
	
	public OrderStatusEvent(OrderStatusDesign osd) {
		this.osd = osd;
		
		jbExit = osd.getJbExit();
		jtOrderStatus = osd.getJtOrderStatus();
		dtmOrderStatus = osd.getDtmOrderStatus();
		jtaTotalOrderInfo = osd.getJtaTotalOrderInfo();
		jtaDetailOrderInfo = osd.getJtaDetailOrderInfo();
		jlTotalPrice = osd.getJlTotalPrice();
		
		openDate = osd.getJlOpenDate().getText().split(":")[1].trim();
		
		setOrder();
	}//OrderStatusEvent
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == jbExit) {
			osd.dispose();
		}//end if
	}//actionPerformed
	
	@Override
	public void windowClosing(WindowEvent e) {
		osd.dispose();
	}//windowClosing

	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(e.getSource() == jtOrderStatus) {
			String orderNum = jtOrderStatus.getValueAt(jtOrderStatus.getSelectedRow(), 0).toString();
			setDetailOrder(orderNum);
			setOrderInfo(orderNum);
		}//end if
		
	}//mouseClicked

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	public void setOrder() {
		OrderStatusDAO osDAO = OrderStatusDAO.getInstance();
		try {
			List<OrderStatusVO> list = osDAO.selectOrderStatus(openDate);
			//이건 어드민쪽에 해놔야함
//			if(list.isEmpty()) {
//				JOptionPane.showMessageDialog(null, "해당 날짜에 대한 주문정보가 존재하지 않습니다.");
//			}//end if
			
			if(list.isEmpty()) {
				return;
			}//end if
			
			dtmOrderStatus.setRowCount(0);
			
			DecimalFormat df = new DecimalFormat("#,###,###");
			String[] rowData = new String[6];
			OrderStatusVO osVO = null;
			for(int i=0 ; i<list.size() ; i++) {
				osVO = list.get(i);
				
				rowData[0] = osVO.getOrderNumber();
				rowData[1] = osVO.getOrderTime();
				rowData[2] = osVO.getMenuName();
				rowData[3] = df.format(osVO.getAmount()) ;
				rowData[4] = osVO.getPrograss();
				if(!(osVO.getPrograss().equals("완료"))) {
					rowData[5] = osVO.getOrderNumber()+","+i;
				}else{
					rowData[5] = null;
				}//end else
				
				dtmOrderStatus.addRow(rowData);
			}//end for
			
			//버그있음
			//버튼을 삭제하면 에디터 안의 보관된값이 null로 설정되어버리는거같음.
			//그 상태에서 다른 버튼을 누르면 다른버튼도 안보이게 됨ㅋㅋ
			//6시간 붙잡고잇다가 포기함
			//누가 좀 해결해줘
			jtOrderStatus.getColumnModel().getColumn(5).setCellEditor(new OrderStatusDefaultCellRenderer(osd));
			jtOrderStatus.getColumnModel().getColumn(5).setCellRenderer(new OrderStatusDefaultCellRenderer(osd));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}//setOrder
	
	public void setDetailOrder(String orderNum) {
		OrderStatusDAO osDAO = OrderStatusDAO.getInstance();
		
		try {
			List<String> list = osDAO.selectDetailOrder(orderNum);
			
			StringBuilder sb = new StringBuilder();
			for(int i=0 ; i<list.size() ; i++) {
				sb.append(list.get(i)).append("\n"); 
			}//end for
			jtaTotalOrderInfo.setText(sb.toString());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
	}//setDetailOrder
	
	private void setOrderInfo(String orderNum) {
		OrderStatusDAO osDAO = OrderStatusDAO.getInstance();
		DecimalFormat df = new DecimalFormat("#,###,###");
		try {
			DetailOrderVO stoVO = osDAO.selectOrderInfo(orderNum);
			
			StringBuilder sb = new StringBuilder();
			sb
			.append("주문번호 : ").append(stoVO.getOrderNum()).append("\n")
			.append("주문시간 : ").append(stoVO.getOrderTime()).append("\n")
			.append("결제방법 : ").append(stoVO.getOrderForm()).append("\n")
			.append("회원번호 : ").append(stoVO.getPhoneNum());
			jtaDetailOrderInfo.setText(sb.toString());
			jlTotalPrice.setText(df.format(stoVO.getAmount()) + "원");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
	}//setOrderInfo

}//class
