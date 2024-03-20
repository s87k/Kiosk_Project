package kiosk_prj.DAO.memberShipDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kiosk_prj.DAO.DbConnection;
import kiosk_prj.vo.memberShipVO.MemberShipCouponVO;
import kiosk_prj.vo.memberShipVO.MemberShipOrderVO;
import kiosk_prj.vo.memberShipVO.MemberShipVO;

public class MemberShipDAO {
	private static MemberShipDAO msDAO;

	private MemberShipDAO() {

	}

	public static MemberShipDAO getInstance() {
		if (msDAO == null) {
			msDAO = new MemberShipDAO();
		} // end if
		return msDAO;
	}// getInstance

	public void insertMember(MemberShipVO msVO) throws SQLException {
		DbConnection dbCon = DbConnection.getInstance();

		// 1. 드라이버 로딩
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// 2.
			String id = "Kiosk";
			String pass = "4";
			con = dbCon.getConnection(id, pass);
			// 3.
			String insertMember = "insert into MEMBERSHIP(PHONE_NUMBER, MEMBER_NAME)" + "values(?,?)";
			pstmt = con.prepareStatement(insertMember);
			// 4. 바인드 변수 설정
			pstmt.setString(1, msVO.getPhoneNum());
			pstmt.setString(2, msVO.getMemberName());
			// 5. 쿼리문 실행
			pstmt.executeUpdate();
		} finally {
			dbCon.dbClose(null, pstmt, con);
		}
	}

	public int updateMember(MemberShipVO msVO) throws SQLException {
		int cnt = 0;
		DbConnection dbCon = DbConnection.getInstance();

		// 1. 드라이버 로딩
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// 2
			String id = "Kiosk";
			String pass = "4";
			con = dbCon.getConnection(id, pass);
			// 3.
			String updateMember = "update MEMBERSHIP set PHONE_NUMBER = ?, MEMBER_NAME = ?, MEMBER_BIRTH = ?, GRADE = ? WHERE = PHONE_NUMBER = ?";

			pstmt = con.prepareStatement(updateMember);

			// 4.
			pstmt.setString(1, msVO.getPhoneNum());
			pstmt.setString(2, msVO.getMemberName());
			pstmt.setString(3, msVO.getMemberBirth());
			pstmt.setString(4, msVO.getGrade());
			// 5.
			pstmt.executeUpdate();
		} finally {
			dbCon.dbClose(null, pstmt, con);
		}
		return cnt;
	}

	public List<MemberShipVO> allMember(String phoneNumber) throws SQLException {
		List<MemberShipVO> memberList = new ArrayList<MemberShipVO>();

		DbConnection dbCon = DbConnection.getInstance();

		// 1.
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 2.
		try {
			String id = "Kiosk";
			String pass = "4";
			con = dbCon.getConnection(id, pass);
			// 3.
			String allMember = "select MEMBER_NAME, PHONE_NUMBER, MEMBER_BIRTH, MEMBER_GRADE from MEMBERSHIP WHERE PHONE_NUMBER like ?";
			pstmt = con.prepareStatement(allMember);
			// 4.
			pstmt.setString(1, "%" + phoneNumber);
			MemberShipVO msVO = null;

			rs = pstmt.executeQuery();
			while (rs.next()) {
				msVO = new MemberShipVO(rs.getString("MEMBER_NAME"), rs.getString("PHONE_NUMBER"),
						rs.getString("MEMBER_BIRTH"), rs.getString("MEMBER_GRADE"));
				memberList.add(msVO);
			}
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		}

		return memberList;
	}
	public List<MemberShipVO> allMember() throws SQLException {
		List<MemberShipVO> memberList = new ArrayList<MemberShipVO>();

		DbConnection dbCon = DbConnection.getInstance();

		// 1.
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 2.
		try {
			String id = "Kiosk";
			String pass = "4";
			con = dbCon.getConnection(id, pass);
			// 3.
			String allMember = "select MEMBER_NAME, PHONE_NUMBER, MEMBER_BIRTH, MEMBER_GRADE from MEMBERSHIP ";
			pstmt = con.prepareStatement(allMember);
			// 4.
			MemberShipVO msVO = null;

			rs = pstmt.executeQuery();
			while (rs.next()) {
				msVO = new MemberShipVO(rs.getString("MEMBER_NAME"), rs.getString("PHONE_NUMBER"),
						rs.getString("MEMBER_BIRTH"), rs.getString("MEMBER_GRADE"));
				memberList.add(msVO);
			}
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		}

		return memberList;
	}

	public List<MemberShipOrderVO> memberOrderList(String phoneNumber) throws SQLException {
		List<MemberShipOrderVO> list = new ArrayList<MemberShipOrderVO>();
		DbConnection dbCon = DbConnection.getInstance();

		// 1.
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 2.
		try {
			String id = "Kiosk";
			String pass = "4";
			con = dbCon.getConnection(id, pass);
			// 3.
			String memberOrder = "SELECT s.order_time, b.menu_name, s.amount "
					+ "FROM SUMMARY_ORDER s, BEVERAGE_MANAGEMENT b, DETAILED_ORDER d "
					+ "WHERE s.order_number = d.order_number AND b.menu_code = d.menu_code " 
					+ "AND s.phone_number = ?";
			// 4.
			pstmt = con.prepareStatement(memberOrder);
			// 5.
			pstmt.setString(1, phoneNumber);
			MemberShipOrderVO msoVO = null;

			rs = pstmt.executeQuery();
			while (rs.next()) {
				msoVO = new MemberShipOrderVO(
						rs.getString("order_time"), 
						rs.getString("menu_name"),
						rs.getInt("amount"));
				list.add(msoVO);
			}
			// 6.연결끊기
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		}
		return list;
	}

	public List<MemberShipCouponVO> memberCouponList(String phoneNumber) throws SQLException {
		List<MemberShipCouponVO> list = new ArrayList<MemberShipCouponVO>();
		DbConnection dbCon = DbConnection.getInstance();

		// 1.
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 2.
		try {
			String id = "Kiosk";
			String pass = "4";
			con = dbCon.getConnection(id, pass);
			// 3.
			String memberCoupon = "SELECT ct.coup_kind_name, ct.discount, ch.publish_date, ch.use_coup_date, ch.status_use " 
					+"FROM coupon_held ch, coupon_type ct " 
					+"WHERE ch.coup_kind_no = ct.coup_kind_no "
					+"AND ch.phone_number = ?";
			//4.
			pstmt = con.prepareStatement(memberCoupon);
			//5.
			pstmt.setString(1, phoneNumber);
			MemberShipCouponVO mscVO = null;
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				mscVO = new MemberShipCouponVO(
						rs.getString("coup_kind_name"),
						rs.getInt("discount"),
						rs.getString("publish_date"),
						rs.getString("use_coup_date"),
						rs.getString("status_use")
						);
				list.add(mscVO);
			}
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		}
		return list;
	}

}// class
