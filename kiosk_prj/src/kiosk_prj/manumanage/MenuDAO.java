package kiosk_prj.manumanage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kiosk_prj.DAO.DbConnection;


/**
 *  DAO (Data Access Object)
 */
public class MenuDAO {
	private static MenuDAO mDAO = null;
	
	private MenuDAO() {
		
	}
	
	public static MenuDAO getInstance() {
		if(mDAO == null) {
			mDAO = new MenuDAO();
		}
		
		return mDAO;
	}
	
	
	public void insertMenu( MenuVO mVO) throws SQLException{
		
		//1. 드라이버로딩
		
		DbConnection dbCon = DbConnection.getInstance();
		Connection con = null;
		PreparedStatement pstmt = null;
		
		 try {
		//2. 커넥션얻기: autocommit
			String id="Kiosk";
			String pass="4";
			con=dbCon.getConnection(id, pass);
    	//3. 쿼리문 생성객체 얻기 
			String insertMenu=
					"insert into beverage_management(menu_Code,type_Code,menu_Name,menu_Price,menu_Img)values(?,?,?,?,?)";
			 pstmt=con.prepareStatement(insertMenu);
		//4. 쿼리문 수행 후 결과 얻기
			 pstmt.setString(1, mVO.getMenuCode());
			 pstmt.setString(2, mVO.getTypeCode());
			 pstmt.setString(3, mVO.getMenuName());
			 pstmt.setInt(4, mVO.getMenuPrice());
			 pstmt.setString(5, mVO.getMenuImg());
		//5.쿼리문 수행 후 결과 얻기 ( 주의 : Statement의 executeXxx(SQL)는 절대로 사용하지 않는다.) 
			 pstmt.executeUpdate();
			 }finally {
		//6.연결 끊기
				dbCon.dbClose(null, pstmt, con);
			}//end finally
		}//insertMenu
		
	
	public int updateMenu( MenuVO mVO) throws SQLException{
		int cnt=0;
		
		DbConnection dbCon=DbConnection.getInstance();
		
		//1.
		Connection con=null;
		PreparedStatement pstmt=null;
				
		try {
		//2. 커넥션얻기: autocommit
			String id="Kiosk";
			String pass="4";
			con=dbCon.getConnection(id, pass);		
		//3.쿼리문 생성객체 얻기
			//메뉴에 해당하는 메뉴코드를 찾아서 종류명,메뉴명,메뉴가격을 변경
			StringBuilder updateMenu=new StringBuilder();
			updateMenu
			.append("  update beverage_management   ")
			.append("  set menu_Name=?, menu_Price=?, menu_Img=? ")
			.append("  where menu_Code=? ");
			
			pstmt=con.prepareStatement(updateMenu.toString());
		//4.바인드변수에 값 설정
			pstmt.setString(1, mVO.getMenuName());
			pstmt.setInt(2, mVO.getMenuPrice());
			pstmt.setString(3, mVO.getMenuImg());
			pstmt.setString(4, mVO.getMenuCode());
		
		//5.쿼리문 수행 후 결과 얻기
			cnt=pstmt.executeUpdate();
		}finally {
		//6.연결 끊기
			dbCon.dbClose(null, pstmt, con);
		}//end finally

		return cnt;
	}//updateMenu
	
	public int deleteMenu(String menuCode, String typeCode ) throws SQLException{
		 int cnt=0;
		 
		 DbConnection dbCon=DbConnection.getInstance();
		 
		 //1.드라이버 로딩
		 Connection con=null;
		 PreparedStatement pstmt=null;
		 
		 try {
		 //2.
			 String id="Kiosk";
			 String pass="4";
				
		 con=dbCon.getConnection(id, pass);
		//3.
		 String deleteMenu="delete from beverage_management where menu_Code=? and type_Code=?";
		 pstmt=con.prepareStatement( deleteMenu );
		 //4.
		 pstmt.setString(1, menuCode);
		 pstmt.setString(2, typeCode);
		 //5.
		 cnt=pstmt.executeUpdate();
		 }finally {
		//6.
			dbCon.dbClose(null, pstmt, con);
		 }//end finally
				
		return cnt;
	}//deleteMenu

	
	public List<MenuVO> selectMenuInfo() throws SQLException{
		List<MenuVO> list=new ArrayList<MenuVO>();
		
		DbConnection dbcon = DbConnection.getInstance();
		
		//1.드라이버로딩
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
		//2.커넥션 얻기
			String id="Kiosk";
			String pass="4";
			con=dbcon.getConnection(id, pass);
		//3. 쿼리문 생성객체 얻기
			String selectMenu="select menu_Code, type_Code, "
					+ " menu_Name, menu_Price, menu_Img "
					+ " from beverage_management order by menu_Code asc ";
			pstmt=con.prepareStatement(selectMenu);
		//4.바인드변수에 값 설정
		//5.쿼리문 수행 후 결과 얻기
			rs=pstmt.executeQuery();
				
			MenuVO mVO=null;
			while( rs.next() ) {//조회된 결과에서 다음 레코드가 존재?
				mVO=new MenuVO(rs.getString("menu_Code"),rs.getString("type_Code"),
						rs.getString("menu_Name"),rs.getInt("menu_Price"),rs.getString("menu_Img"));
					
				list.add(mVO);
			}//end while
			
		}finally {
		//5. 연결 끊기
			dbcon.dbClose(rs, pstmt, con);
		}//end finally
		
		return list;
	}//selectMenuInfo


//	public int setSoldout( String menuCode ) throws SQLException{
//		 int cnt=0;
//		 //1.드라이버 로딩
//		 try {
//			 Class.forName("oracle.jdbc.OracleDriver");
//		 } catch (ClassNotFoundException e) {
//		 e.printStackTrace();
//		 }//end catch
//		 
//		 String id="Kiosk";
//		 String pass="4";
//			
//		 Connection con=null;
//		 Statement stmt=null;
//		 	
//		 try {
//		 //2.커넥션 얻기
//			con=DriverManager.getConnection(id,pass);
//		 //3.쿼리문 생성객체 얻기
//		 	stmt=con.createStatement();
//		 //4.쿼리문 수행 후 결과 얻기	 
//		 //메뉴코드에 해당하는 레코드를 찾아서 메뉴를 품절설정함
//			StringBuilder setSoldout=new StringBuilder();
//			setSoldout
//			.append("  select from menu where menuCode=").append(menuCode);
//			
//			cnt=stmt.executeUpdate(setSoldout.toString());
//			
//		 }finally {
//		//5.연결끊기
//			if( stmt !=null ) { stmt.close(); }//end if
//			if( con != null ) { con.close(); }//end if 
//		 }//end finally
//		 
//		 return cnt;
//	}//setSoldout

}//class