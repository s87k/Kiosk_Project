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
public class MenuTypeDAO {
	private static MenuTypeDAO mtDAO = null;
	
	private MenuTypeDAO() {
		
	}
	
	public static MenuTypeDAO getInstance() {
		if(mtDAO == null) {
			mtDAO = new MenuTypeDAO();
		}
		
		return mtDAO;
	}
	

	public void insertType( MenuTypeVO mtVO) throws SQLException{
		
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
			String insertType=
					"insert into menu_type(type_Code,type_name)values(?,?)";
			 pstmt=con.prepareStatement(insertType);
		//4. 쿼리문 수행 후 결과 얻기
			 pstmt.setString(1, mtVO.getTypeCode());
			 pstmt.setString(2, mtVO.getTypeName());
			 
		//5.쿼리문 수행 후 결과 얻기 ( 주의 : Statement의 executeXxx(SQL)는 절대로 사용하지 않는다.) 
			 pstmt.executeUpdate();
			 }finally {
		//6.연결 끊기
				dbCon.dbClose(null, pstmt, con);
			}//end finally
		}//insertType
		
	public int updateType( MenuTypeVO mtVO) throws SQLException{
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
			StringBuilder updateType=new StringBuilder();
			updateType
			.append("  update menu_type   ")
			.append("  set type_Name=?    ")
			.append("  where type_Code=?  ");
			
			pstmt=con.prepareStatement(updateType.toString());
		//4.바인드변수에 값 설정
			pstmt.setString(1, mtVO.getTypeName());
			pstmt.setString(2, mtVO.getTypeCode());
		
		//5.쿼리문 수행 후 결과 얻기
			cnt=pstmt.executeUpdate();
		}finally {
		//6.연결 끊기
			dbCon.dbClose(null, pstmt, con);
		}//end finally

		return cnt;
	}//updateType
	
	public int deleteType(String typeCode ) throws SQLException{
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
		 String deleteType="delete from menu_type where type_Code=?";
		 pstmt=con.prepareStatement( deleteType );
		 //4.
		 pstmt.setString(1, typeCode);
		 //5.
		 cnt=pstmt.executeUpdate();
		 }finally {
		//6.
			dbCon.dbClose(null, pstmt, con);
		 }//end finally
				
		return cnt;
	}//deleteType
	
	public List<MenuTypeVO> selectTypeInfo() throws SQLException{
		List<MenuTypeVO> list=new ArrayList<MenuTypeVO>();
		
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
			String selectType="select type_Code, type_Name "
					+ " from menu_type order by type_Code asc ";
			pstmt=con.prepareStatement(selectType);
		//4.바인드변수에 값 설정
		//5.쿼리문 수행 후 결과 얻기
			rs=pstmt.executeQuery();
				
			MenuTypeVO mtVO=null;
			while( rs.next() ) {//조회된 결과에서 다음 레코드가 존재?
				mtVO=new MenuTypeVO(rs.getString("type_Code"),rs.getString("type_Name"));
					
				list.add(mtVO);
			}//end while
			
		}finally {
		//5. 연결 끊기
			dbcon.dbClose(rs, pstmt, con);
		}//end finally
		
		return list;
	}//selectTypeInfo

}//class
