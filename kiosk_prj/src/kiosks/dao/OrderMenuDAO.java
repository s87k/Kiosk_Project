package kiosks.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kiosks.vo.DetailedOrderVO;
import kiosks.vo.OrderMenuVO;
import kiosks.vo.SummaryOrderVO;

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

	public String selectOrderNum() throws SQLException {
		String orderNum="";
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
			pstmt = con.prepareStatement("select SEQ_ORDER_NUMBER.nextval val from dual");

			// 4. 바인드 변수에 값넣기

			// 5. 쿼리문 수행 후 결과 얻기
			rs = pstmt.executeQuery();
			while (rs.next()) {
				orderNum=rs.getString("val");
			} // end while
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		} // end finally
		return orderNum;
	}
	
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
			selectAllMenu.append(" select menu_img ").append(" from BEVERAGE_MANAGEMENT ")
					.append(" where type_code = ?");
//			.append(" order by menu_img ");//정렬기준 뭘로?

			pstmt = con.prepareStatement(selectAllMenu.toString());

			// 4. 바인드 변수에 값넣기
			pstmt.setString(1, typeCode);

			// 5. 쿼리문 수행 후 결과 얻기
			OrderMenuVO omVO = null;
			rs = pstmt.executeQuery();
			while (rs.next()) {
				omVO = new OrderMenuVO(rs.getString("menu_img"));
				list.add(omVO);
			} // end while
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		} // end finally
		return list;
	}// selectMenuImg

	/**
	 * 탑3 메뉴 이미지 가져오기
	 * 
	 * @param menuCode
	 * @return
	 * @throws SQLException
	 */
	public List<OrderMenuVO> selectTop3Img() throws SQLException {
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
			String selectTop3Img = " select     b.menu_img "
					+ " from      detailed_order d, beverage_management b, menu_type m "
					+ "    where        d.menu_code=b.menu_code and m.type_code=b.type_code  "
					+ "    order by    m.TYPE_CODE  ";

			pstmt = con.prepareStatement(selectTop3Img.toString());

			// 4. 쿼리문 수행 후 결과 얻기
			OrderMenuVO omVO = null;
			rs = pstmt.executeQuery();
			while (rs.next()) {
				omVO = new OrderMenuVO(rs.getString("menu_img"));
				list.add(omVO);
			} // end while
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		} // end finally
		return list;
	}// selectMenuImg

	/**
	 * 옵션 선택 이미지, 메뉴명, 메뉴가격 가져오기
	 * 
	 * @param menuCode
	 * @return
	 * @throws SQLException
	 */
	public List<OrderMenuVO> selectMenuDetail(String menuImg) throws SQLException {
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
			selectAllMenu.append(" select menu_img, menu_name, menu_price ").append(" from BEVERAGE_MANAGEMENT ")
					.append(" where menu_img = ?");
//			.append(" order by menu_name ");//정렬기준 뭘로?

			pstmt = con.prepareStatement(selectAllMenu.toString());

			// 4. 바인드 변수에 값넣기
			pstmt.setString(1, menuImg);

			// 5. 쿼리문 수행 후 결과 얻기
			rs = pstmt.executeQuery();

			OrderMenuVO omVO = null;
			while (rs.next()) {
				omVO = new OrderMenuVO(rs.getString("menu_img"), rs.getString("menu_name"), rs.getInt("menu_price"));
				list.add(omVO);
			} // end while
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		} // end finally
		return list;
	}// selectMenuDetail
	
	public void insertSummaryOrder(SummaryOrderVO soVO) throws SQLException {
		DbConnection dbCon = DbConnection.getInstance();

		// 1. 드라이버 로딩
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// 2. 커넥션 얻기
			String id = "kiosk";
			String pass = "4";
			con = dbCon.getConnection(id, pass);
			StringBuilder insertOrder = new StringBuilder();
			insertOrder
			.append("insert into summary_order(ORDER_NUMBER, SHOP_OPEN, ORDER_TIME, AMOUNT, PROGRESS, ORDER_FORM, PHONE_NUMBER) ")
			.append(" values(?, ?, ?, ?, ?, ?, ?)");//1
			pstmt = con.prepareStatement(insertOrder.toString());

			// 4. 바인드변수에 값 설정
			pstmt.setString(1, soVO.getOrderNumber());
			pstmt.setString(2, soVO.getShopOpen());
			pstmt.setTimestamp(3, soVO.getOrderTime());
			pstmt.setInt(4, soVO.getAmount());
			pstmt.setString(5, soVO.getProgress());
			pstmt.setString(6, soVO.getOrderForm());
			pstmt.setString(7, soVO.getPhoneNumber());

			// 5. 쿼리문 수행 후 결과 얻기
			pstmt.executeUpdate();
		} finally {
			// 6. 연결 끊기
			dbCon.dbClose(null, pstmt, con);
		} // end finally
	}//insertSummaryOrder
	
	public void insertDetailedOrder(DetailedOrderVO doVO) throws SQLException {
		DbConnection dbCon = DbConnection.getInstance();
		
		// 1. 드라이버 로딩
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			// 2. 커넥션 얻기
			String id = "kiosk";
			String pass = "4";
			con = dbCon.getConnection(id, pass);
			StringBuilder insertOrder = new StringBuilder();
			insertOrder
			.append(" insert into DETAILED_ORDER(ORDER_NUMBER, SHOP_OPEN, WAITING_NUMBER, CUP_SIZE, TEMP, SHOT, MENU_CODE, TYPE_CODE, NUM) ")
			.append(" values(?, ?, WAITING_NUMBER.nextval, ?, ?, ?, ?, ?, DETAILED_ORDER_SEQ.nextval) ");
			pstmt = con.prepareStatement(insertOrder.toString());
			
			// 4. 바인드변수에 값 설정
			pstmt.setString(1, doVO.getOrderNumber());
			pstmt.setString(2, doVO.getShopOpen());
			pstmt.setString(3, doVO.getCupSize());
			pstmt.setString(4, doVO.getTemp());
			pstmt.setInt(5, doVO.getShot());
			pstmt.setString(6, doVO.getMenuCode());
			pstmt.setString(7, doVO.getTypeCode());
			
			// 5. 쿼리문 수행 후 결과 얻기
			pstmt.executeUpdate();
		} finally {
			// 6. 연결 끊기
			dbCon.dbClose(null, pstmt, con);
		} // end finally
	}//insertSummaryOrder
	
	public List<OrderMenuVO> selectCodes(String menuName) throws SQLException {
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
			selectAllMenu.append(" select MENU_CODE, TYPE_CODE ").append(" from BEVERAGE_MANAGEMENT ")
					.append(" where MENU_NAME = ? ");

			pstmt = con.prepareStatement(selectAllMenu.toString());

			// 4. 바인드 변수에 값넣기
			pstmt.setString(1, menuName);

			// 5. 쿼리문 수행 후 결과 얻기
			rs = pstmt.executeQuery();

			OrderMenuVO omVO = null;
			while (rs.next()) {
				omVO = new OrderMenuVO(rs.getString("MENU_CODE"), rs.getString("TYPE_CODE"));
				list.add(omVO);
			} // end while
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		} // end finally
		return list;
	}// selectMenuDetail
	
	/**
	 * 대기 번호 가져오기
	 * @param orderNumber
	 * @param shopOpen
	 * @return
	 * @throws SQLException
	 */
	public List<DetailedOrderVO> selectWaitingNum(String orderNumber, String shopOpen) throws SQLException {
		List<DetailedOrderVO> list = new ArrayList<DetailedOrderVO>();
		
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
			String selectAllMenu = " select waiting_number from DETAILED_ORDER where ORDER_NUMBER=? and SHOP_OPEN=? order by waiting_number desc ";
			
			pstmt = con.prepareStatement(selectAllMenu);
			
			// 4. 바인드 변수에 값넣기
			pstmt.setString(1, orderNumber);
			pstmt.setString(2, shopOpen);
			
			// 5. 쿼리문 수행 후 결과 얻기
			rs = pstmt.executeQuery();
			
			DetailedOrderVO doVO = null;
			while (rs.next()) {
				doVO = new DetailedOrderVO(rs.getInt("waiting_number"));
				list.add(doVO);
			} // end while
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		} // end finally
		return list;
	}// selectMenuDetail
	
	
	
}// class
