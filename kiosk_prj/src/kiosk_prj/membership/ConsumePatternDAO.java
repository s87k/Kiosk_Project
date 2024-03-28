package kiosk_prj.membership;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kiosk_prj.DAO.DbConnection;

public class ConsumePatternDAO {
	private static ConsumePatternDAO cpDAO;
	
	private ConsumePatternDAO() {
		
	}// ConsumePatternDAO
	
	public static ConsumePatternDAO getInstance() {
		if (cpDAO == null) {
			cpDAO = new ConsumePatternDAO();
		}// end if
		return cpDAO;
	}// getInstance
	public List<ConsumePatternVO> searchConusmePattern(String phoneNum) throws SQLException {
		List<ConsumePatternVO> list = new ArrayList<ConsumePatternVO>();
		DbConnection dbCon = DbConnection.getInstance();
		
		//1.
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//2.
		try {
			String id = "Kiosk";
			String pass = "4";
			con = dbCon.getConnection(id, pass);
			//3.
			String consumePattern = 
					"SELECT b.menu_name, COUNT(*) AS sales_count, SUM(so.amount) AS total_amount " +
                    "FROM SUMMARY_ORDER so " +
                    "INNER JOIN DETAILED_ORDER d ON so.order_number = d.order_number " +
                    "INNER JOIN BEVERAGE_MANAGEMENT b ON d.menu_code = b.menu_code " +
                    "WHERE so.phone_number = ? " +
                    "GROUP BY so.phone_number, b.menu_name";
			//4.
			pstmt = con.prepareStatement(consumePattern);
			//5.
			pstmt.setString(1, phoneNum);
			ConsumePatternVO cVO = null;
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				cVO = new ConsumePatternVO(
						rs.getString("menu_name"),
						rs.getInt("sales_count"),
						rs.getInt("total_amount"));
				list.add(cVO);
			}
			System.out.println(cVO);
		}finally {
			dbCon.dbClose(rs, pstmt, con);
		}
		
		return list;
	}
	
}
