package kiosk_prj.manumanage;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {

    private static DbConnection dbcon;

    private DbConnection() {

    }// DbConnection

    public static DbConnection getInstance() {
        if (dbcon == null) {
            dbcon = new DbConnection();
        }

        return dbcon;
    }// getInstance

    public Connection getConnection(String url, String id, String pass) throws SQLException {
        Connection con = null;
        //1. 드라이버 로딩
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //2. Connection 얻기
        con = DriverManager.getConnection(url, id, pass);

        return con;

    }// getConnection

  
/**
 * @param id
 * @param pass
 * @return
 * @throws SQLException
 */
public Connection getConnection(String id, String pass) throws SQLException {
    Connection con = null;//1. 드라이버 로딩
    	try {
	        Class.forName("oracle.jdbc.OracleDriver");
	    }catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }//end catch
	
    	//2.Connection얻기
	      String url = "jdbc:oracle:thin:@192.168.10.220:1521:orcl";
	      con = DriverManager.getConnection(url, id, pass);
	
	        return con;
	
	    }// getConnection
	

/**
 * @param rs
 * @param stmt
 * @param con
 * @throws SQLException
 */
public void dbClose(ResultSet rs, Statement stmt, Connection con)
	     throws SQLException{
	    try {
		 if( rs !=null ) {rs.close(); } //end if
		 if( stmt !=null ) {stmt.close(); }//end if
	  }finally {
		if( con !=null ) {con.close(); }//end if
	  }//end finally
	  }//dbClose

}// class
