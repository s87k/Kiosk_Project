package kiosk_prj.trend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kiosk_prj.DAO.DbConnection;

public class TrendDAO {
	private static TrendDAO tDAO;

	private TrendDAO() {

	}

	public static TrendDAO getInstance() {
		if (tDAO == null) {
			tDAO = new TrendDAO();
		}
		return tDAO;
	}

	public List<TrendVO> searchTrend() throws SQLException {
		List<TrendVO> list = new ArrayList<TrendVO>();

		DbConnection dbCon = DbConnection.getInstance();
		// 1.
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String id = "Kiosk";
			String pass = "4";
			con = dbCon.getConnection(id, pass);
			// 3.
			String searchTrend = 
					"select     b.menu_name, b.menu_price+(d.shot*500) price "
					+ " from      detailed_order d, beverage_management b, menu_type m "
					+ "	where	    d.menu_code=b.menu_code and m.type_code=b.type_code  "
					+ "	order by	m.TYPE_CODE  ";
			
			pstmt = con.prepareStatement(searchTrend);
			
			rs = pstmt.executeQuery();
			
			TrendVO tVO = null;
			while(rs.next()) {
				tVO = new TrendVO(
						rs.getString("menu_name"),
						rs.getInt("price"));
				list.add(tVO);
			}
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		}

		return list;
	}
}
