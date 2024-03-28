package kiosk_prj.membership;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import kiosk_prj.DAO.DbConnection;
import kiosk_prj.membership.dao.MemberShipDAO;
import kiosk_prj.membership.vo.MemberShipCouponVO;
import kiosk_prj.membership.vo.MemberShipOrderVO;
import kiosk_prj.membership.vo.MemberShipVO;

@SuppressWarnings("serial")
public class MemberShipEvent extends WindowAdapter implements ActionListener, MouseListener {
	private MemberShipDesign msd;

	public MemberShipEvent(MemberShipDesign msd) {
		this.msd = msd;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton numberArr[] = msd.getInputNumber();
		String currentText = msd.getjPhoneNum().getText();
		String phoneNum = msd.getjPhoneNum().getText();
		String name = msd.getjName().getText();

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
			int selectedRowIndex = msd.getMemberList().getSelectedRow();

			if (selectedRowIndex == -1) {
				JOptionPane.showMessageDialog(msd, "회원을 선택해주세요.");
			} else {
				phoneNum = (String) msd.getMemberList().getValueAt(selectedRowIndex, 2); // 선택된 행의 전화번호 얻기
				new ModifyMemberDesign(msd, "회원 수정", phoneNum);
			}
		}

		if (e.getSource() == msd.getDetailMember()) {
			new DetailMemberDesign(msd, "회원 상세", phoneNum);
		}

		if (e.getSource() == msd.getCheckMember()) {
			setMemberTable(phoneNum, name);
		}

	}

	public void setMemberTable(String phoneNum, String name) {

		MemberShipDAO msDAO = MemberShipDAO.getInstance();
		try {
			// 입력된 전화번호의 뒷자리에 해당하는 회원정보 가져오기
			List<MemberShipVO> list = null;
			if (!phoneNum.isEmpty() && !name.isEmpty()) {
				list = msDAO.searchByPhoneNumberAndName(phoneNum, name);
			}
			// 전화번호만 입력된 경우
			else if (!phoneNum.isEmpty()) {
				list = msDAO.searchMemberByPhoneNum(phoneNum);
			}
			// 이름만 입력된 경우
			else if (!name.isEmpty()) {
				list = msDAO.searchMemberByName(name);
			}
			// 아무것도 입력되지 않은 경우
			else {
				list = msDAO.allMember();
			}
			// 모델 객체 값 설정
			DefaultTableModel dtm = msd.getDtmMemberList();
			// 값 설정 전에 모델객체 초기화
			dtm.setRowCount(0);

			String[] rowData = null;
			MemberShipVO msVO = null;

			if (list.isEmpty()) {
				JOptionPane.showMessageDialog(msd, phoneNum + "은 존재하지 않습니다.");
			} // end if
			for (int i = 0; i < list.size(); i++) {
				msVO = list.get(i);
				rowData = new String[5];
				rowData[0] = "" + (i + 1) + "";
				rowData[1] = msVO.getPhoneNum();
				rowData[2] = msVO.getMemberName();
				rowData[3] = msVO.getMemberBirth();
				rowData[4] = msVO.getGrade();

				// 행 등록
				dtm.addRow(rowData);
			} // end for
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch
	}// setMemberTable

	public void setMemberOrderTable(String phoneNum) {

		MemberShipDAO msDAO = MemberShipDAO.getInstance();
		try {
			List<MemberShipOrderVO> list = msDAO.memberOrderList(phoneNum);
			// 모델 객체 값 설정
			DefaultTableModel dtm = msd.getDtmOrderList();
			// 값 설정 전에 모델객체 초기화
			dtm.setRowCount(0);

			String[] rowData = null;
			MemberShipOrderVO msoVO = null;

			for (int i = 0; i < list.size(); i++) {
				msoVO = list.get(i);
				rowData = new String[4];

				rowData[0] = msoVO.getWatingNum();
				rowData[1] = msoVO.getOrderTime();
				rowData[2] = msoVO.getMenuName();
				rowData[3] = "" + msoVO.getAmount();

				// 행 등록
				dtm.addRow(rowData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}// setMemberOrder

	public void setCouponList(String phoneNum) {

		String openDate = msd.getOpenDate();
		if (openDate == null) {
			openDate = "";
		} else {
			String[] parts = openDate.split(":");
			String dateOnly = parts[1].trim();

			MemberShipDAO msDAO = MemberShipDAO.getInstance();
			try {
				List<MemberShipCouponVO> couponList = msDAO.memberCouponList(phoneNum);
				// 모델 객체 값 설정
				DefaultTableModel dtm = msd.getDtmCouponList();
				// 값 설정 전에 모델 초기화
				dtm.setRowCount(0);

				String[] rowData = null;
				MemberShipCouponVO mscVO = null;
				for (int i = 0; i < couponList.size(); i++) {
					mscVO = couponList.get(i);
					rowData = new String[6];
					// 1. 번호
					rowData[0] = "" + (i + 1) + "";
					// 2. 쿠폰 이름
					rowData[1] = mscVO.getCouponName();
					// 3. 쿠폰 할인액
					rowData[2] = "" + mscVO.getDisCount();
					// 4. 쿠폰 발급일
					rowData[3] = mscVO.getPublishDate();
					// 5. 쿠폰 만료일 설정
					// 5-1 쿠폰 만료일 설정을 위해 쿠폰 발급일을 가져옴
					String publishDate = mscVO.getPublishDate();
					// 5-2 DB에 설정되어 있는 쿠폰 만료일의 값을 int로 파싱
					int expireDateValue = mscVO.getExpiresPeriod();
					// 5-3 쿠폰 만료일의 값을 발급일에 추가하기 위해 "-"를 기준으로 발급일을 자름
					String[] publishDateParts = publishDate.split("-");
					// 5-4 쿠폰 만료일을 더했을 때, 12월이 넘어가면 연도의 값을 더해줘야 하므로, 발급일의 연도의 값을 가져옴
					int publishYear = Integer.parseInt(publishDateParts[0]);
					// 5-5 쿠폰 만료일을 더하기 위해 발급일 월의 값을 가져옴
					int publishMonth = Integer.parseInt(publishDateParts[1]);
					// 5-6 쿠폰 만료일 설정을 위해 발급월의 값에 만료일의 값을 더해줌
					int totalMonth = publishMonth + expireDateValue;
					// 5-7 만약 만료일의 값과 발급일의 값을 더했을 때, 12가 넘어가면 다음년도로 넘어감
					int adjustedYear = publishYear + (totalMonth / 12);
					// 5-8 만료일이 다음 년도로 남어가게 되면, 더한 값에 12를 나눈 나머지로 만료일의 월을 설정함
					int adjustedMonth = totalMonth % 12;
					// 5-8 만료일의 출력 형식을 발급일의 출력 형식에 맞게 바꿔줌
					String adjustDate = adjustedYear + "-" + adjustedMonth + "-" + publishDateParts[2];
					// 5-9 쿠폰 만료일
					rowData[4] = adjustDate;

					// 5-10 개점일 "-" 기준으로 자르기
					String[] openDateParts = dateOnly.split("-");
					// 5-11 개점
					int openDateYear = Integer.parseInt(openDateParts[0]);
					int openDateMonth = Integer.parseInt(openDateParts[1]);
					int openDateDay = Integer.parseInt(openDateParts[2]);

					String[] pdp = publishDateParts[2].split(" ");
					int adJustDay = Integer.parseInt(pdp[0]);
					// 6 쿠폰 상태(나중에 개점 날짜에 따라 쿠폰 사용 가능 여부 바꿔줄 것)
					// 6-1 쿠폰 사용여부를 받기 위해 getUseStatus의 값을 int로 파싱해줌
					int useStatus = Integer.parseInt(mscVO.getUseStatus());
					switch (useStatus) {
					case 0: {
						// 6-2 쿠폰 사용 여부의 값이 0일 경우
						rowData[5] = "사용가능";
						break;
					}
					case 1: {
						// 6-3 쿠폰 사용 여부의 값이 1일 경우
						rowData[5] = "사용완료";
						break;
					}
					case 2: {
						// 6-4 쿠폰 사용 여부의 값이 2일 경우
						// 이 값은 개점설정된 일자와 쿠폰 만료일의 값을 비교하여 개점 날짜가 만료일의 값을 지났을 때 설정해
						rowData[5] = "만료됨";
						break;
					}
					}
					if (openDate != null) {
						if ((openDateYear > adjustedYear) || (openDateMonth > adjustedMonth)
								|| (openDateDay > adJustDay)) {
							rowData[5] = "만료됨";
						}
					}
					dtm.addRow(rowData);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == msd.getMemberList()) {
			int selectedRowIndex = msd.getMemberList().getSelectedRow();
			String phoneNum = (String) msd.getMemberList().getValueAt(selectedRowIndex, 2);
			String name = (String) msd.getMemberList().getValueAt(selectedRowIndex, 1);

			msd.setjName(name);
			msd.setjPhoneNum(phoneNum);
			setMemberOrderTable(phoneNum);
			setCouponList(phoneNum);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}// class
