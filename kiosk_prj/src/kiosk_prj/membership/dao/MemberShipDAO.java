package kiosk_prj.membership.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import kiosk_prj.DAO.DbConnection;
import kiosk_prj.adminMain.currentOrderVO;
import kiosk_prj.membership.MemberShipDesign;
import kiosk_prj.membership.vo.MemberShipCouponVO;
import kiosk_prj.membership.vo.MemberShipOrderVO;
import kiosk_prj.membership.vo.MemberShipVO;

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

	public int updateMember(MemberShipVO msVO, String phoneNumber) throws SQLException {
		int cnt = 0;
		DbConnection dbCon = DbConnection.getInstance();
//		MemberShipVO mVO = new MemberShipVO();
//		MemberShipDesign msd = new MemberShipDesign(new JFrame(), "회원관리");

		// 1. 드라이버 로딩
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// 2
			String id = "Kiosk";
			String pass = "4";
			con = dbCon.getConnection(id, pass);
			// 3.
			String updateMember = "update MEMBERSHIP set PHONE_NUMBER =?, MEMBER_NAME = ?, MEMBER_BIRTH = ?, MEMBER_GRADE = ? WHERE PHONE_NUMBER = ?";

			pstmt = con.prepareStatement(updateMember);

			// 4.
			pstmt.setString(1, msVO.getPhoneNum());
			pstmt.setString(2, msVO.getMemberName());
			pstmt.setString(3, msVO.getMemberBirth());
			pstmt.setString(4, msVO.getGrade());
			pstmt.setString(5, phoneNumber);

			// 5.
			pstmt.executeUpdate();
		} finally {
			dbCon.dbClose(null, pstmt, con);
		}
		return cnt;
	}

	public int deleteMember(MemberShipVO msVO, String phoneNumber) throws SQLException {
		int cnt = 0;
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
			String deleteMember = "update MEMBERSHIP set FLAG_DELETE = ? WHERE PHONE_NUMBER = ?";
			// 4.
			pstmt = con.prepareStatement(deleteMember);

			pstmt.setString(1, "1");
			pstmt.setString(2, phoneNumber);
			// 5.
			pstmt.executeUpdate();
		} finally {
			dbCon.dbClose(null, pstmt, con);
		}
		return cnt;
	}

	public List<MemberShipVO> searchMemberByPhoneNum(String phoneNumber) throws SQLException {
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
			String allMember = ""
					+ "select MEMBER_NAME, "
					+ "PHONE_NUMBER, MEMBER_BIRTH, "
					+ "MEMBER_GRADE from MEMBERSHIP "
					+ "WHERE FLAG_DELETE = '0' AND PHONE_NUMBER like ?";
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

	public List<MemberShipVO> searchMemberByName(String name) throws SQLException {
		List<MemberShipVO> memberList = new ArrayList<MemberShipVO>();
		DbConnection dbCon = DbConnection.getInstance();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 2.
		try {
			String id = "Kiosk";
			String pass = "4";
			con = dbCon.getConnection(id, pass);
			// 3.
			String allMember = "select MEMBER_NAME, PHONE_NUMBER, MEMBER_BIRTH, MEMBER_GRADE from MEMBERSHIP WHERE FLAG_DELETE = '0' AND MEMBER_NAME like ?";
			pstmt = con.prepareStatement(allMember);
			// 4.
			pstmt.setString(1, "%" + name);
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

	public List<MemberShipVO> searchByPhoneNumberAndName(String phoneNumber, String name) throws SQLException {
		List<MemberShipVO> memberList = new ArrayList<>();

		DbConnection dbCon = DbConnection.getInstance();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String id = "Kiosk";
			String pass = "4";
			con = dbCon.getConnection(id, pass);
			String sql = "SELECT MEMBER_NAME, PHONE_NUMBER, MEMBER_BIRTH, MEMBER_GRADE FROM MEMBERSHIP WHERE FLAG_DELETE = '0' AND PHONE_NUMBER like ? AND MEMBER_NAME = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + phoneNumber);
			pstmt.setString(2, name);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MemberShipVO msVO = new MemberShipVO(rs.getString("MEMBER_NAME"), rs.getString("PHONE_NUMBER"),
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
			String allMember = "select MEMBER_NAME, PHONE_NUMBER, MEMBER_BIRTH, MEMBER_GRADE from MEMBERSHIP where FLAG_DELETE = ? ";
			pstmt = con.prepareStatement(allMember);
			pstmt.setString(1, "0");
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
			String memberOrder = "SELECT d.waiting_number, s.order_time, b.menu_name, s.amount "
					+ "FROM SUMMARY_ORDER s, BEVERAGE_MANAGEMENT b, DETAILED_ORDER d "
					+ "WHERE s.order_number = d.order_number AND b.menu_code = d.menu_code " + "AND s.phone_number = ?";
			// 4.
			pstmt = con.prepareStatement(memberOrder);
			// 5.
			pstmt.setString(1, phoneNumber);
			MemberShipOrderVO msoVO = null;

			rs = pstmt.executeQuery();
			while (rs.next()) {
				msoVO = new MemberShipOrderVO(rs.getString("waiting_number"), rs.getString("order_time"),
						rs.getString("menu_name"), rs.getInt("amount"));
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
			String memberCoupon = 
					  "SELECT ct.coup_kind_name, ct.discount, ch.publish_date, ct.expires_period, ch.use_coup_date, ch.status_use "
					+ "FROM coupon_held ch, coupon_kind ct " + "WHERE ch.coup_kind_no = ct.coup_kind_no "
					+ "AND ch.phone_number = ?";
			// 4.
			pstmt = con.prepareStatement(memberCoupon);
			// 5.
			pstmt.setString(1, phoneNumber);
			MemberShipCouponVO mscVO = null;

			rs = pstmt.executeQuery();
			while (rs.next()) {
				mscVO = new MemberShipCouponVO(
						rs.getString("coup_kind_name"), 
						rs.getInt("discount"),
						rs.getString("publish_date"), 
						rs.getInt("expires_period"),
						rs.getString("use_coup_date"), 
						rs.getString("status_use"));
				list.add(mscVO);
			}
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		}
		return list;
	}// memberCouponList

}// class
