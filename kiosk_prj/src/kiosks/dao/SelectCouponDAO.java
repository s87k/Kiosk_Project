package kiosks.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kiosks.vo.SelectCouponVO;

public class SelectCouponDAO {

	private static SelectCouponDAO scDAO;

	private SelectCouponDAO() {

	}

	public static SelectCouponDAO getInstance() {
		if (scDAO == null) {
			scDAO = new SelectCouponDAO();
		} // end if
		return scDAO;
	}// getInstance

	/**
	 * 회원 번호로 사용가능한 보유 쿠폰 조회
	 * 
	 * @param phoneNumber
	 * @return
	 * @throws SQLException
	 */
	public List<SelectCouponVO> searchCoupByPhoneNum(String phoneNumber) throws SQLException {
		List<SelectCouponVO> list = new ArrayList<SelectCouponVO>();

		DbConnection dbCon = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String id = "Kiosk";
			String pass = "4";
			con = dbCon.getConnection(id, pass);

			String searchCoup = " select ck.discount, ch.COUP_PUB_CODE, ck.coup_kind_name, ch.publish_date, ck.expires_period "
					+ " from COUPON_HELD ch, COUPON_KIND ck " + " where ch.coup_kind_no = ck.coup_kind_no "
					+ " and ch.status_use = '0' and ch.phone_number = ? order by ck.coup_kind_name";
			pstmt = con.prepareStatement(searchCoup);
			pstmt.setString(1, phoneNumber);
			SelectCouponVO scVO = null;

			rs = pstmt.executeQuery();
			while (rs.next()) {
				scVO = new SelectCouponVO(rs.getString("COUP_PUB_CODE"), rs.getInt("discount"), rs.getString("coup_kind_name"), rs.getString("publish_date"),
						rs.getString("expires_period"));
				list.add(scVO);
			}
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		}
		return list;
	}// searchCoupByPhoneNum

	public List<SelectCouponVO> searchDiscount(String couponName) throws SQLException {
		List<SelectCouponVO> list = new ArrayList<SelectCouponVO>();

		DbConnection dbCon = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String id = "Kiosk";
			String pass = "4";
			con = dbCon.getConnection(id, pass);

			String searchCoup = " select DISCOUNT " + " from   COUPON_KIND " + " where coup_kind_name=? ";
			pstmt = con.prepareStatement(searchCoup);
			pstmt.setString(1, couponName);
			SelectCouponVO scVO = null;

			rs = pstmt.executeQuery();
			while (rs.next()) {
				scVO = new SelectCouponVO(rs.getInt("DISCOUNT"));
				list.add(scVO);
			}
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		}
		return list;
	}// searchDiscount
}
// class
