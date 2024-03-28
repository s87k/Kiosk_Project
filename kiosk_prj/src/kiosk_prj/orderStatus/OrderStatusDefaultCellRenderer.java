package kiosk_prj.orderStatus;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

@SuppressWarnings("serial")
public class OrderStatusDefaultCellRenderer extends AbstractCellEditor implements TableCellEditor, TableCellRenderer, ActionListener {
	
	private OrderStatusDesign osd;
	private ImageIcon imageIcon;
	private JButton jbUseAble;
	private String orderNum, values;
	private int deleteNum;
	 
	public OrderStatusDefaultCellRenderer(OrderStatusDesign osd) {
		this.osd = osd;
		imageIcon = new ImageIcon("kiosk_prj/src/kiosk_prj/image/BTcheck.png");
		jbUseAble = new JButton(imageIcon);
		jbUseAble.addActionListener(this);
	}//OrderStatusDefaultCellRenderer

	@Override
	public Component getTableCellRendererComponent
		(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		if (value == null) {
			return null;
		}//end if
		
		jbUseAble.setIcon(imageIcon);
		return jbUseAble;
	}//getTableCellRendererComponent

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		if (value == null) {
			return null;
		} // end if
		
		this.values = value.toString();
		jbUseAble.setIcon(imageIcon);
		jbUseAble.setText(value.toString());
		return jbUseAble;
	}//getTableCellEditorComponent
	
	@Override
	public void actionPerformed(ActionEvent e) {
		orderNum = values.toString().split(",")[0];
		switch(JOptionPane.showConfirmDialog(null, orderNum + "번의 주문 상태를 '완료'로 변경할까요?")) {
		case JOptionPane.NO_OPTION : 
		case JOptionPane.CANCEL_OPTION : break;
		case JOptionPane.OK_OPTION :
			jbUseAble.setVisible(false);
			jbUseAble.setText(null);
			this.deleteNum = 1;
			osd.getJtOrderStatus().setValueAt("완료", Integer.parseInt(values.toString().split(",")[1]), 4);
			modifyProgress(orderNum);
			break;
		}//end switch
	}//actionPerformed
	
	@Override
	public Object getCellEditorValue() {
		if(deleteNum==1) {//취소가 눌러지면
			deleteNum=0;
			return null;
		}//end if
		return values;
	}//getCellEditorValue
	
	public void modifyProgress(String orderNum) {
		OrderStatusDAO osDAO = OrderStatusDAO.getInstance();
		try {
			int cnt = osDAO.updateProgress(orderNum);
			JOptionPane.showMessageDialog(null, cnt + "건 완료처리 되었습니다!");
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
	}//modifyProgress
	
}//class
