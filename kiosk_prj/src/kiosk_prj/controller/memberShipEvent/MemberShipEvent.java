package kiosk_prj.controller.memberShipEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import kiosk_prj.DAO.memberShipDAO.MemberShipDAO;
import kiosk_prj.view.memverShipView.AddMemberDesign;
import kiosk_prj.view.memverShipView.DetailMemberDesign;
import kiosk_prj.view.memverShipView.MemberShipDesign;
import kiosk_prj.view.memverShipView.ModifyMemberDesign;
import kiosk_prj.view.memverShipView.SearchMemberDesign;
import kiosk_prj.vo.memberShipVO.MemberShipCouponVO;
import kiosk_prj.vo.memberShipVO.MemberShipOrderVO;
import kiosk_prj.vo.memberShipVO.MemberShipVO;

@SuppressWarnings("serial")
public class MemberShipEvent extends WindowAdapter implements ActionListener {
	private MemberShipDesign msd;

	public MemberShipEvent(MemberShipDesign msd) {
		this.msd = msd;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton numberArr[] = msd.getInputNumber();
		String currentText = msd.getjPhoneNum().getText();
		String phoneNum = msd.getjPhoneNum().getText();
//		if(phoneNum.isEmpty()) {
//			JOptionPane.showMessageDialog(msd, "전화번호 입력은 필수입니다");
//			return;
//		}//end if
		
		

		// 숫자 입력 버튼 for문 설정하여 코드 가독성 향상
		if (currentText.length() <= 10) {
			for (int i = 0; i <= 9; i++) {
				if (e.getSource() == numberArr[i]) {
					msd.getjPhoneNum().setText(currentText + i);
					break;
				}
			}
		}

		if (e.getSource() == numberArr[10]) {
			if (!currentText.isEmpty()) {
				String newText = currentText.substring(0, currentText.length() - 1);
				msd.getjPhoneNum().setText(newText);
			}
		}
		if (e.getSource() == numberArr[11]) {
			msd.getjPhoneNum().setText("");
		}

		if (e.getSource() == msd.getExit()) {
			msd.dispose();
		}
		
		if (e.getSource() == msd.getAddMember()) {
			new AddMemberDesign(msd, "회원등록");
		}
		
		if (e.getSource() == msd.getSearchMember()) {
			new SearchMemberDesign(msd, "전체 회원목록");
		}
		
		if (e.getSource() == msd.getModifyMember()) {
			new ModifyMemberDesign(msd, "회원 수정");
		}
		
		if (e.getSource() == msd.getDetailMember()) {
			new DetailMemberDesign(msd, "회원 상세");
		}
		
		if (e.getSource() == msd.getCheckMember()) {
			setMemberTable(phoneNum);
			setMemberOrderTable(phoneNum);
			setCouponList(phoneNum);
		}
		
		
	}
	
	public void setMemberTable(String phoneNum) {
		
		MemberShipDAO msDAO = MemberShipDAO.getInstance();
		try {
			//입력된 전화번호의 뒷자리에 해당하는 회원정보 가져오기
			List<MemberShipVO> list = msDAO.allMember(phoneNum);
			//모델 객체 값 설정
			DefaultTableModel dtm = msd.getDtmMemberList();
			//값 설정 전에 모델객체 초기화
			dtm.setRowCount(0);
			
			String[] rowData = null;
			MemberShipVO msVO = null;
			
			if(list.isEmpty()) {
				JOptionPane.showMessageDialog(msd, phoneNum + "은 존재하지 않습니다.");
			}//end if
			for(int i = 0; i < list.size(); i++) {
				msVO = list.get(i);
			rowData = new String[5];
			rowData[0] = "" + (i + 1) + "";
			rowData[1] = msVO.getPhoneNum();
			rowData[2] = msVO.getMemberName();
			rowData[3] = msVO.getMemberBirth();
			rowData[4] = msVO.getGrade();
			
			//행 등록
			dtm.addRow(rowData);
			}//end for
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
	}//setMemberTable
	
	public void setMemberOrderTable(String phoneNum) {
		
		MemberShipDAO msDAO = MemberShipDAO.getInstance();
			try {
				List<MemberShipOrderVO> list = msDAO.memberOrderList(phoneNum);
				//모델 객체 값 설정
				DefaultTableModel dtm = msd.getDtmOrderList();
				//값 설정 전에 모델객체 초기화
				dtm.setRowCount(0);
				
				String[] rowData = null;
				MemberShipOrderVO msoVO = null;
				
				for(int i = 0; i < list.size(); i++) {
					msoVO = list.get(i);
					rowData = new String[4];
					rowData[0] = "" + (i + 1) + "";
					rowData[1] = msoVO.getOrderTime();
					rowData[2] = msoVO.getMenuName();
					rowData[3] ="" + msoVO.getAmount();
					
					//행 등록
					dtm.addRow(rowData);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	}//setMemberOrder
	public void setCouponList(String phoneNumber) {
		
		MemberShipDAO msDAO = MemberShipDAO.getInstance();
		try {
			List<MemberShipCouponVO> couponList = msDAO.memberCouponList(phoneNumber);
			//모델 객체 값 설정
			DefaultTableModel dtm = msd.getDtmCouponList();
			//값 설정 전에 모델 초기화
			dtm.setRowCount(0);
			
			String[] rowData = null;
			MemberShipCouponVO  mscVO = null;
			
			for(int i = 0; i < couponList.size(); i++) {
				mscVO = couponList.get(i);
				rowData = new String[6];
				rowData[0] = "" + (i + 1) + "";
				rowData[1] = mscVO.getCouponName();
				rowData[2] = ""+mscVO.getDisCount();
				rowData[3] = mscVO.getPublishDate();
//				rowData[4]
				
				dtm.addRow(rowData);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}//class
