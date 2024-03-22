package kiosk_prj.shopOpen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import kiosk_prj.dao.DbConnection;

public class ShopOpenCloseDAO {
	
	private static ShopOpenCloseDAO soDAO;
	
	private ShopOpenCloseDAO() {
	}//ShopOpenDAO
	
	public static ShopOpenCloseDAO getInstance() {
		if(soDAO==null) {
			soDAO = new ShopOpenCloseDAO();
		}//end if
		return soDAO;
	}//getInstance
	
	/**
	 * 개점일을 insert
	 * @param OpenDate
	 * @throws SQLException
	 */
	public void insertOpenDate(String OpenDate) throws SQLException{
		
		DbConnection dbCon = DbConnection.getInstance();
		// 1.
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
		// 2.
			String id = "kiosk";
			String pass = "4";
			con = dbCon.getConnection(id, pass);
		// 3. 쿼리문 생성객체 얻기
			StringBuilder insertOpenDate = new StringBuilder();
			insertOpenDate
			.append(" insert into shop_open(shop_open) ")
			.append(" values(?) ");
			
			pstmt=con.prepareStatement(insertOpenDate.toString());
		// 4. 바인드 변수에 값 설정
			pstmt.setString(1, OpenDate);
		// 5. 쿼리문 수행 후 결과 얻기
			pstmt.executeQuery();
			
		}finally {
		// 6. 연결끊기
			dbCon.dbClose(null, pstmt, con);
		}//end finally
		
	}//selectZipcode
	
	/**
	 * 마감일을 insert
	 */
	public void insertCloseDate(String closeDate) throws SQLException{
		
		DbConnection dbCon = DbConnection.getInstance();
		// 1.
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
		// 2.
			String id = "kiosk";
			String pass = "4";
			con = dbCon.getConnection(id, pass);
		// 3. 쿼리문 생성객체 얻기
			StringBuilder insertCloseDate = new StringBuilder();
			insertCloseDate
			.append(" update  shop_open ")
			.append(" set     shop_close=? ")
			.append(" where   shop_open=? ");
			
			pstmt=con.prepareStatement(insertCloseDate.toString());
		// 4. 바인드 변수에 값 설정
			pstmt.setString(1, closeDate);
			pstmt.setString(2, closeDate);
		// 5. 쿼리문 수행 후 결과 얻기
			pstmt.executeQuery();
			
		}finally {
		// 6. 연결끊기
			dbCon.dbClose(null, pstmt, con);
		}//end finally
		
	}//insertCloseDate
	
	/**
	 * 해당 영업일의 총 판매정보를 select
	 */
	public void selectDailySettlemnet(String OpenDate) {
		
	}//selectDailySettlemnet
	
}//class
