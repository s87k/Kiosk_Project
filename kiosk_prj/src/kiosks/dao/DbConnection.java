package kiosks.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {
	private static DbConnection dbCon;

	private DbConnection() {

	}// DbConnection

	public static DbConnection getInstance() {
		if (dbCon == null) {
			dbCon = new DbConnection();
		}
		return dbCon;
	}// getInstance

	public Connection getConnection(String id, String pass) throws SQLException {
		Connection con = null;
		// 1. 드라이버 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException cne) {
			cne.printStackTrace();
		} // end catch

		String url = "jdbc:oracle:thin:@192.168.10.220:1521:orcl";

		con = DriverManager.getConnection(url, id, pass);
		return con;
	}// getConnection
	
//	public void test() throws SQLException {
//		Connection con=getConnection("Kiosk", "4");
//		PreparedStatement s1=con.prepareStatement("drop sequence seq_order_number");
//		s1.execute();
//		PreparedStatement s=con.prepareStatement("create sequence seq_order_number increment by 1 start with 1 maxvalue 99999");
//		s.execute();
//		
//		
//	}
//	
//	public static void main(String[] args) {
//		try {
//			DbConnection.getInstance().test();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

	public void dbClose(ResultSet rs, Statement stmt, Connection con) throws SQLException {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}
}// class
