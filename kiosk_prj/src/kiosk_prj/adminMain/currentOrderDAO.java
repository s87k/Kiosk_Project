package kiosk_prj.adminMain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kiosk_prj.DAO.DbConnection;

public class currentOrderDAO {
	private static currentOrderDAO coDAO;

	private currentOrderDAO() {

	}

	public static currentOrderDAO getInstance() {
		if (coDAO == null) {
			coDAO = new currentOrderDAO();
		} // end if
		return coDAO;
	}// getInstance

	public List<currentOrderVO> searchCurrentOrder(String openDate) throws SQLException {
		List<currentOrderVO> list = new ArrayList<currentOrderVO>();
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
			String searchStatus = "SELECT mt.TYPE_NAME, bm.MENU_NAME, bm.MENU_PRICE + (dto.SHOT*500) menu_price "
					+ "FROM DETAILED_ORDER dto, BEVERAGE_MANAGEMENT bm, MENU_TYPE mt "
					+ "WHERE mt.TYPE_CODE=bm.TYPE_CODE AND dto.menu_code = bm.menu_code AND SHOP_OPEN= ?";
			pstmt = con.prepareStatement(searchStatus);
			// 4.
			pstmt.setString(1, openDate);
			// 5.
			currentOrderVO coVO = null;
			rs = pstmt.executeQuery();
			while (rs.next()) {
				coVO = new currentOrderVO(rs.getString("TYPE_NAME"), rs.getString("MENU_NAME"),
						rs.getInt("menu_price"));
				list.add(coVO);
			}
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		}
		return list;
	}

	public currentOrderVO sumAmount(String openDate) throws SQLException {
		currentOrderVO coVO = null;
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
			String sumAmount = 
					"SELECT SUM(bm.MENU_PRICE + (dto.SHOT * 500)) AS total_menu_price "
		                       + "FROM DETAILED_ORDER dto "
		                       + "JOIN BEVERAGE_MANAGEMENT bm ON dto.menu_code = bm.menu_code "
		                       + "JOIN MENU_TYPE mt ON mt.TYPE_CODE = bm.TYPE_CODE "
		                       + "WHERE SHOP_OPEN = ?";
			pstmt = con.prepareStatement(sumAmount);
			// 4.
			pstmt.setString(1, openDate);
			// 5.
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				coVO = new currentOrderVO(rs.getInt("total_menu_price"));
			}
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		}
		return coVO;
	}
}
