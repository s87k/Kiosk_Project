package kiosk_prj.shopOpen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kiosk_prj.DAO.DbConnection;

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
	 * 해당 영업일의 총 판매정보를 select //마감용
	 */
	public List<ShopCloseVO> selectDailySettlemnet(String OpenDate) throws SQLException{
		List<ShopCloseVO> list = new ArrayList<ShopCloseVO>();
		
		DbConnection dbCon = DbConnection.getInstance();
		// 1.
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		// 2.
			String id = "kiosk";
			String pass = "4";
			con = dbCon.getConnection(id, pass);
		// 3. 쿼리문 생성객체 얻기
			StringBuilder selectDailySettlemnet = new StringBuilder();
			selectDailySettlemnet
			.append(" select    m.type_name, b.menu_name, b.menu_price+(d.shot*500) price ")
			.append(" from      detailed_order d, beverage_management b, menu_type m ")
			.append(" where	    d.menu_code=b.menu_code and m.type_code=b.type_code and shop_open=? ")
			.append(" order by	m.TYPE_CODE, b.MENU_NAME ");
			
			pstmt=con.prepareStatement(selectDailySettlemnet.toString());
		// 4. 바인드 변수에 값 설정
			pstmt.setString(1, OpenDate);
		// 5. 쿼리문 수행 후 결과 얻기
			rs=pstmt.executeQuery();
			
			ShopCloseVO scVO = null;
			while(rs.next()) {//조회된 결과에서 다음 레코드가 존재?
				scVO = new ShopCloseVO(rs.getString("type_name"), rs.getString("menu_name"),
						rs.getInt("price"));
				list.add(scVO);
			}//end while
			
		}finally {
		// 6. 연결끊기
			dbCon.dbClose(rs, pstmt, con);
		}//end finally
		
		return list;
	}//selectDailySettlemnet
	
	/**
	 * 개점시마다 시퀀스를 삭제하고, 재생성해서 시퀀스를 초기화하는 method
	 * @throws SQLException 
	 */
	public void updateSequence() throws SQLException {
		
		DbConnection dbCon = DbConnection.getInstance();
		// 1.
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		
		try {
		// 2.
			String id = "kiosk";
			String pass = "4";
			con = dbCon.getConnection(id, pass);
		// 3. 쿼리문 생성객체 얻기
			//삭제
			String deleteSequence = 
				" drop sequence seq_order_number ";
			pstmt1=con.prepareStatement(deleteSequence.toString());
			pstmt1.execute();
			
			//생성
			String createSequence = 
				" create sequence seq_order_number increment by 1 start with 1 maxvalue 99999 ";
			pstmt2=con.prepareStatement(createSequence.toString());
			pstmt2.execute();
			
			
		}finally {
		// 6. 연결끊기
			if(con != null) { con.close(); };
			if(pstmt1 != null) { pstmt1.close(); };
			if(pstmt2 != null) { pstmt2.close(); };
		}//end finally
		
	}//updateSequence
	
}//class
