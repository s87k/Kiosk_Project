//package kr.co.sist.manumanage;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// *  DAO (Data Access Object)
// */
//public class MenuDAO {
//	private static MenuDAO mDAO = null;
//	
//	private MenuDAO() {
//		
//		
//	}
//	
//	public static MenuDAO getInstance() {
//		if(mDAO ==null) {
//			mDAO = new MenuDAO();
//
//		}
//		
//		
//		return mDAO;
//		
//	}
//	
//	
//	public void insertMenu( MenuVO mVO) throws SQLException{
//		//1. 드라이버로딩
//		try {
//			Class.forName("oracle.jdbc.OracleDriver");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}//end catch
//		 String url="jdbc:oracle:thin:@localhost:1521:orcl";
//		 String id="scott";
//		 String pass="tiger";
//		 
//		 Connection con=null;
//		 Statement stmt=null;
//		 try {
//		//2. 커넥션얻기: autocommit
//			 con=DriverManager.getConnection(url, id, pass);
//    	//3. 쿼리문 생성객체 얻기 
//			 stmt=con.createStatement();
//		//4. 쿼리문 수행 후 결과 얻기
//			 StringBuilder insertMenu=new StringBuilder();
//			 insertMenu
//			 .append("insert into menuInfo(menuCode,typeName,menuName,menuPrice) values(")
//			 .append(mVO.getMenuCode()).append(", '").append(mVO.getTypeName())
//			 .append(", '").append(mVO.getMenuName()).append(", '")
//			 .append(mVO.getMenuPrice()).append(")");
//			 
//			 //insert는 1건 추가 아니면 예외
//			 /*int cnt=*/stmt.executeUpdate(insertMenu.toString());
//			 
//		 }finally {
//		//5.연결끊기
//			 if(stmt != null) { stmt.close(); }//end if
//			 if(con != null) { con.close(); }//end if
//		 }//end finally
//		 
//	}//insertMenu
//	
//	public int updateMenu( MenuVO mVO) throws SQLException{
//		int cnt=0;
//		//1.드라이버 로딩
//		try {
//			Class.forName("oracle.jdbc.OracleDriver");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}//end catch
//		
//		
//		String url="jdbc:oracle:thin:@localhost:1521:orcl";
//		String id="scott";
//		String pass="tiger";
//		
//		Connection con=null;
//		Statement stmt=null;
//		
//		try {
//		//2.커넥션 얻기
//			con=DriverManager.getConnection(url,id,pass);
//		//3.쿼리문 생성객체 얻기
//			stmt=con.createStatement();
//		//4.쿼리문 수행 후 결과 얻기	
//			//메뉴에 해당하는 메뉴코드를 찾아서 종류명,메뉴명,메뉴가격을 변경
//			StringBuilder updateMenu=new StringBuilder();
//			updateMenu
//			.append("  update menu   ")
//			.append(" set   typeName='").append(mVO.getTypeName())
//			.append(" set   menuName='").append(mVO.getMenuName())
//			.append(" set   menuPrice='").append(mVO.getMenuPrice());
//			
//			cnt=stmt.executeUpdate(updateMenu.toString());
//			
//		}finally {
//		//5.연결끊기
//			if( stmt !=null ) { stmt.close(); }//end if
//			if( con != null ) { con.close(); }//end if
//		}//end finally
//		
//		return cnt;
//
//	}//updateMenu
//	
//	public int deleteMenu( String menuCode ) throws SQLException{
//		 int cnt=0;
//		 //1.드라이버 로딩
//		 try {
//			 Class.forName("oracle.jdbc.OracleDriver");
//		 } catch (ClassNotFoundException e) {
//		 e.printStackTrace();
//		 }//end catch
//		 
//		 String url="jdbc:oracle:thin:@localhost:1521:orcl";
//		 String id="scott";
//		 String pass="tiger";
//			
//		 Connection con=null;
//		 Statement stmt=null;
//		 	
//		 try {
//		 //2.커넥션 얻기
//			con=DriverManager.getConnection(url,id,pass);
//		 //3.쿼리문 생성객체 얻기
//		 	stmt=con.createStatement();
//		 //4.쿼리문 수행 후 결과 얻기	 
//		 //메뉴코드에 해당하는 레코드를 찾아서 메뉴를 삭제
//			StringBuilder deleteMenu=new StringBuilder();
//			deleteMenu
//			.append("  delete from menu where menuCode=").append(menuCode);
//			
//			cnt=stmt.executeUpdate(deleteMenu.toString());
//			
//		 }finally {
//		//5.연결끊기
//			if( stmt !=null ) { stmt.close(); }//end if
//			if( con != null ) { con.close(); }//end if 
//		 }//end finally
//		 
//		 return cnt;
//	}//deleteMenu
//
//	
//	public List<MenuVO> selectMenuInfo() throws SQLException{
//		List<MenuVO> list=new ArrayList<MenuVO>();
//		DbConnection dbcon = DbConnection.getInstance();
//		Connection con=null;
//		PreparedStatement pstmt=null;
//		ResultSet rs=null;
//
//
//		try {
//			
//			String id="scott";
//			String pass="tiger";
//			
//		//2. 커넥션 얻기
//			con=dbcon.getConnection(id, pass);
//		//3. 쿼리문 생성객체 얻기
//			
//			String selectMenu="select menuCode, typeName, menuName,"
//					+ " menuImg, menuPrice, inputDate from menuInfo";
//			
//			pstmt=con.prepareStatement(selectMenu);
//		//4. 쿼리문 수행 후 결과 얻기
//
//			rs=pstmt.executeQuery();
//			
//			MenuVO mVO=null;
//			while( rs.next() ) {//조회된 결과에서 다음 레코드가 존재?
//				mVO=new MenuVO(rs.getString("menuCode"),rs.getString("typeName"),
//						rs.getString("menuName"),rs.getString("menuImg"),
//						rs.getInt("menuPrice"),rs.getDate("inputDate"));
//					
//				list.add(mVO);
//			}//end while
//			
//		}finally {
//		//5. 연결 끊기
//			dbcon.dbClose(rs, pstmt, con);
//		}//end finally
//		
//		return list;
//	}//selectMenuInfo
//	
//	public int setSoldout( String menuCode ) throws SQLException{
//		 int cnt=0;
//		 //1.드라이버 로딩
//		 try {
//			 Class.forName("oracle.jdbc.OracleDriver");
//		 } catch (ClassNotFoundException e) {
//		 e.printStackTrace();
//		 }//end catch
//		 
//		 String url="jdbc:oracle:thin:@localhost:1521:orcl";
//		 String id="scott";
//		 String pass="tiger";
//			
//		 Connection con=null;
//		 Statement stmt=null;
//		 	
//		 try {
//		 //2.커넥션 얻기
//			con=DriverManager.getConnection(url,id,pass);
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
//
//}//class