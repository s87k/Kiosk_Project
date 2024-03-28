package kiosk_prj.membership;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import kiosk_prj.membership.dao.MemberShipDAO;
import kiosk_prj.membership.vo.MemberShipCouponVO;

public class DetailCouponEvent extends WindowAdapter implements ActionListener{
	private DetailCouponDesign dcd;
	
	public DetailCouponEvent(DetailCouponDesign dcd) {
		this.dcd = dcd;
		setCouponList(dcd.getPhoneNum());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == dcd.getBack()) {
			dcd.dispose();
		}
	}
	
	public void setCouponList(String phoneNumber) {

		MemberShipDAO msDAO = MemberShipDAO.getInstance();
		try {
			List<MemberShipCouponVO> couponList = msDAO.memberCouponList(phoneNumber);
			// 모델 객체 값 설정
			DefaultTableModel dtm = dcd.getDtmCouponTable();
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
				}
				case 2: {
					// 6-4 쿠폰 사용 여부의 값이 2일 경우
					// 이 값은 개점설정된 일자와 쿠폰 만료일의 값을 비교하여 개점 날짜가 만료일의 값을 지났을 때 설정해
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
