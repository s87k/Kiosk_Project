package kiosks.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kiosks.vo.OrderMenuVO;

public class OrderMenuDAO {

	private static OrderMenuDAO odDAO;

	private OrderMenuDAO() {

	}// OrderMenuDAO

	public static OrderMenuDAO getInstance() {
		if (odDAO == null) {
			odDAO = new OrderMenuDAO();
		} // end if
		return odDAO;
	}// getInstance

	/**
	 * 메뉴 이미지 가져오기
	 * 
	 * @param menuCode
	 * @return
	 * @throws SQLException
	 */
	public List<OrderMenuVO> selectMenuImg(String typeCode) throws SQLException {
		List<OrderMenuVO> list = new ArrayList<OrderMenuVO>();

		DbConnection dbCon = DbConnection.getInstance();

		// 1. 드라이버 로딩
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 2. 커넥션 얻기
			String id = "kiosk";
			String pass = "4";
			con = dbCon.getConnection(id, pass);

			// 3. 쿼리문 생성객체 얻기
			StringBuilder selectAllMenu = new StringBuilder();
			selectAllMenu
			.append(" select menu_img ")
			.append(" from BEVERAGE_MANAGEMENT ")
			.append(" where type_code = ?");
//			.append(" order by menu_name ");//정렬기준 뭘로?

			pstmt = con.prepareStatement(selectAllMenu.toString());

			// 4. 바인드 변수에 값넣기
			pstmt.setString(1, typeCode);

			// 5. 쿼리문 수행 후 결과 얻기
			rs = pstmt.executeQuery();

			OrderMenuVO omVO = null;
			while (rs.next()) {
				omVO = new OrderMenuVO(rs.getString("menu_code"), rs.getString("type_code"), rs.getString("menu_name"),
						rs.getInt("menu_price"), rs.getString("menu_img"));
				list.add(omVO);
			} // end while
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		} // end finally
		return list;
	}// selectAllMenu
	
	/**
	 * 옵션 선택 이미지, 메뉴명, 메뉴가격 가져오기
	 * 
	 * @param menuCode
	 * @return
	 * @throws SQLException
	 */
	public List<OrderMenuVO> selectMenuDetail(String typeCode, String menuCode) throws SQLException {
		List<OrderMenuVO> list = new ArrayList<OrderMenuVO>();
		
		DbConnection dbCon = DbConnection.getInstance();
		
		// 1. 드라이버 로딩
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// 2. 커넥션 얻기
			String id = "kiosk";
			String pass = "4";
			con = dbCon.getConnection(id, pass);
			
			// 3. 쿼리문 생성객체 얻기
			StringBuilder selectAllMenu = new StringBuilder();
			selectAllMenu
			.append(" select menu_img, menu_name, menu_price ")
			.append(" from BEVERAGE_MANAGEMENT ")
			.append(" where type_code=? and menu_code=? ");
//			.append(" order by menu_name ");//정렬기준 뭘로?
			
			pstmt = con.prepareStatement(selectAllMenu.toString());
			
			// 4. 바인드 변수에 값넣기
			pstmt.setString(1, typeCode);
			pstmt.setString(2, menuCode);
			
			// 5. 쿼리문 수행 후 결과 얻기
			rs = pstmt.executeQuery();
			
			OrderMenuVO omVO = null;
			while (rs.next()) {
				omVO = new OrderMenuVO(rs.getString("menu_code"), rs.getString("type_code"), rs.getString("menu_name"),
						rs.getInt("menu_price"), rs.getString("menu_img"));
				list.add(omVO);
			} // end while
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		} // end finally
		return list;
	}// selectAllMenu

}// class
