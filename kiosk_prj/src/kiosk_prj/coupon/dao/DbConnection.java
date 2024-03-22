package kiosk_prj.coupon.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {
	
	static final String ID = "prj1kiosk";
	static final String PASS = "tiger";
	static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	
	private static DbConnection dbCon;
	
	private DbConnection() {
		
	}//DbConnection
	public static DbConnection getInstance() {
		if(dbCon == null) {
			dbCon = new DbConnection();
		}
		return dbCon;
	}//getInstance
	
	public Connection getConnection(String url, String id, String pass)throws SQLException{
		Connection con = null;
		//1. 드라이버 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		}catch(ClassNotFoundException cne) {
			cne.printStackTrace();
		}//end catch
		
		con = DriverManager.getConnection(url, id, pass);
		return con;
	}//getConnection
	
	public Connection getConnection(String id, String pass)throws SQLException{
		return DriverManager.getConnection("jdbc:oracle:thin:@192.168.10.220:1521:orcl", id, pass);
	}//getConnection
	
	public void dbClose(ResultSet rs, Statement stmt, Connection con)throws SQLException{
		try {
			if(rs != null) {rs.close();}
			if(stmt != null) {stmt.close();}
		}
		finally {
			if(con != null) {con.close();}
		}
	}
}//class
