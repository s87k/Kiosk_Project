package kiosk_prj.controller.memberShipEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import kiosk_prj.DAO.memberShipDAO.MemberShipDAO;
import kiosk_prj.view.memverShipView.DetailOrderDesign;
import kiosk_prj.vo.memberShipVO.MemberShipOrderVO;

public class DetailOrderEvent extends WindowAdapter implements ActionListener {
	private DetailOrderDesign dod;
	
	public DetailOrderEvent(DetailOrderDesign dod) {
		this.dod = dod;
		setOrderDetail(dod.getPhoneNum());
	}//DetailOrderEvent
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == dod.getBack()) {
			dod.dispose();
		}
		
	}//actionPerformed
	public void setOrderDetail(String phoneNum) {
		
		MemberShipDAO msDAO = MemberShipDAO.getInstance();
		try {
			List<MemberShipOrderVO> list = msDAO.memberOrderList(phoneNum);
			//모델 객체 값 설정
			DefaultTableModel dtm = dod.getDtmOrderTable();
			//값 설정 전 모델객체 초기화
			dtm.setRowCount(0);
			
			String[] rowData = null;
			MemberShipOrderVO msoVO = null;
			
			for(int i = 0; i < list.size(); i++) {
				msoVO = list.get(i);
				rowData = new String[4];
				rowData[0] = "" + (i+1);
				rowData[1] = msoVO.getOrderTime();
				rowData[2] = msoVO.getMenuName();
				rowData[3] = "" + msoVO.getAmount();
				
				//행 등록
				dtm.addRow(rowData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}//class
