package kiosk_prj.orderStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kiosk_prj.DAO.DbConnection;

public class OrderStatusDAO {
	
	private static OrderStatusDAO osDAO;
	
	private OrderStatusDAO() {
	}
	
	public static OrderStatusDAO getInstance() {
		if(osDAO==null) {
			osDAO = new OrderStatusDAO();
		}//end if
		return osDAO;
	}//getInstance
	
	/**
	 * 메인 테이블에 세팅할 해당 영업일의 주문 정보를 select하는 method
	 * @param OpenDate
	 * @return
	 * @throws SQLException
	 */
	public List<OrderStatusVO> selectOrderStatus(String OpenDate) throws SQLException{
		List<OrderStatusVO> list = new ArrayList<OrderStatusVO>();
		
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
			StringBuilder selectOrderStatus = new StringBuilder();
			selectOrderStatus
			.append(" select	so.order_number, bm.menu_name, to_char(so.order_time, 'hh24:mi:ss') order_time, so.amount, so.progress ")
			.append(" from      summary_order so, detailed_order dto, beverage_management bm ")
			.append(" where     dto.menu_code=bm.menu_code and so.order_number=dto.order_number and dto.num=1 and so.shop_open=? ")
			.append(" order by	so.order_time desc ");
			
			pstmt=con.prepareStatement(selectOrderStatus.toString());
		// 4. 바인드 변수에 값 설정
			pstmt.setString(1, OpenDate);
		// 5. 쿼리문 수행 후 결과 얻기
			rs=pstmt.executeQuery();
			
			OrderStatusVO osVO = null;
			while(rs.next()) {//조회된 결과에서 다음 레코드가 존재?
				osVO = new OrderStatusVO(rs.getString("order_number"), rs.getString("menu_name"),
						rs.getString("order_time"), rs.getInt("amount"), rs.getString("progress"));
				list.add(osVO);
			}//end while
			
		}finally {
		// 6. 연결끊기
			dbCon.dbClose(rs, pstmt, con);
		}//end finally
		
		return list;
	}//selectOrderStatus
	
	public List<String> selectDetailOrder(String orderNum) throws SQLException{
		List<String> list = new ArrayList<String>();
		
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
			StringBuilder selectDetailOrder = new StringBuilder();
			selectDetailOrder
			.append(" select  bm.menu_name ")
			.append(" from    detailed_order dto, beverage_management bm ")
			.append(" where   dto.menu_code=bm.menu_code and order_number=? ");
			
			pstmt=con.prepareStatement(selectDetailOrder.toString());
		// 4. 바인드 변수에 값 설정
			pstmt.setString(1, orderNum);
		// 5. 쿼리문 수행 후 결과 얻기
			rs=pstmt.executeQuery();
			
			while(rs.next()) {//조회된 결과에서 다음 레코드가 존재?
				list.add(rs.getString("menu_name"));
			}//end while
			
		}finally {
		// 6. 연결끊기
			dbCon.dbClose(rs, pstmt, con);
		}//end finally
		
		return list;
	}//selectDetailOrder
	
	public DetailOrderVO selectOrderInfo(String orderNum) throws SQLException{
		DetailOrderVO stoVO = null;
		
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
			StringBuilder selectOrderInfo = new StringBuilder();
			selectOrderInfo
			.append(" select  order_number, to_char(order_time, 'mm-dd hh24:mi:ss') order_time, order_form, phone_number, amount ")
			.append(" from    summary_order ")
			.append(" where   order_number=? ");
			
			pstmt=con.prepareStatement(selectOrderInfo.toString());
		// 4. 바인드 변수에 값 설정
			pstmt.setString(1, orderNum);
		// 5. 쿼리문 수행 후 결과 얻기
			rs=pstmt.executeQuery();
			
			while(rs.next()) {//조회된 결과에서 다음 레코드가 존재?
				stoVO = new DetailOrderVO(rs.getString("order_number"), rs.getString("order_time"),
						rs.getString("order_form"), rs.getString("phone_number"),
						rs.getInt("amount"));
			}//end while
			
		}finally {
		// 6. 연결끊기
			dbCon.dbClose(rs, pstmt, con);
		}//end finally
		
		return stoVO;
	}//selectOrderInfo
	
	public int updateProgress(String orderNum) throws SQLException {
		int cnt = 0;
		DbConnection dbCon = DbConnection.getInstance();
		
		// 1.
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// 2.
			String id = "kiosk";
			String pass = "4";
			con = dbCon.getConnection(id, pass);
			
			// 3.
			StringBuilder updateProgress = new StringBuilder();
			updateProgress
			.append(" update  summary_order ")
			.append(" set     progress='완료' ")
			.append(" where   order_number=? ");
			
			pstmt=con.prepareStatement(updateProgress.toString());
			// 4. 바인드 변수에 값 설정
			pstmt.setString(1, orderNum);
			// 5. 쿼리문 수행 후 결과 얻기
			cnt = pstmt.executeUpdate();
		}finally {
			// 6. 연결끊기
			dbCon.dbClose(null, pstmt, con);
		}//end finally
		
		return cnt;
	}//updateProgress
	
}//class
