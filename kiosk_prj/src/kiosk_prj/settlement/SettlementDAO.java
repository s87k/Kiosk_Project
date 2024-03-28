package kiosk_prj.settlement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kiosk_prj.DAO.DbConnection;
import kiosk_prj.orderStatus.OrderStatusVO;

public class SettlementDAO {
	
	private static SettlementDAO stmDAO;
	
	private SettlementDAO() {
	}//SettlementDAO
	
	public static SettlementDAO getInstance() {
		if(stmDAO==null) {
			stmDAO = new SettlementDAO();
		}//end if
		return stmDAO;
	}//getInstance
	
	/**
	 * 매출버튼을 눌렀을때, 바로 보이는 달력에 각 일의 매출정보를 select하는 method
	 * @param date
	 * @return
	 * @throws SQLException
	 */
	public List<Integer> selectOneDaySettlement(String date) throws SQLException {
		List<Integer> list = new ArrayList<Integer>();
		
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
			StringBuilder selectOneDaySettlement = new StringBuilder();
			selectOneDaySettlement
			.append(" select  bm.menu_price+(dto.shot*500) menu_price ")
			.append(" from    detailed_order dto, beverage_management bm ")
			.append(" where   dto.menu_code=bm.menu_code and dto.shop_open=? ");
			
			pstmt=con.prepareStatement(selectOneDaySettlement.toString());
		// 4. 바인드 변수에 값 설정
			pstmt.setString(1, date);
		// 5. 쿼리문 수행 후 결과 얻기
			rs=pstmt.executeQuery();
			
			int temp = 0;
			while(rs.next()) {//조회된 결과에서 다음 레코드가 존재?
				temp = rs.getInt("menu_price");
				list.add(temp);
			}//end while
			
		}finally {
		// 6. 연결끊기
			dbCon.dbClose(rs, pstmt, con);
		}//end finally
		
		return list;
	}//selectOneDaySettlement
	
	/**
	 * 시작일, 종료일을 String형으로 받아, 매개변수로 받은 기간동안의 매출 정보를 select하는 method
	 * 시작일보다 종료일이 늦으면 안됨***********
	 * @param startDay
	 * @param endDay
	 * @return
	 * @throws SQLException 
	 */
	public List<SettlementPeriodVO> selectSettlementPeriod(String startDay, String endDay) throws SQLException{
		List<SettlementPeriodVO> list = new ArrayList<SettlementPeriodVO>();
		
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
			StringBuilder selectSettlementPeriod = new StringBuilder();
			selectSettlementPeriod
			.append(" select  bm.menu_name, bm.menu_price+(dto.shot*500) menu_price ")
			.append(" from    beverage_management bm, detailed_order dto ")
			.append(" where	  dto.menu_code=bm.menu_code and dto.shop_open between ? and ? ");
			
			pstmt=con.prepareStatement(selectSettlementPeriod.toString());
		// 4. 바인드 변수에 값 설정
			pstmt.setString(1, startDay);
			pstmt.setString(2, endDay);
		// 5. 쿼리문 수행 후 결과 얻기
			rs=pstmt.executeQuery();
			
			SettlementPeriodVO smpVO = null;
			while(rs.next()) {//조회된 결과에서 다음 레코드가 존재?
				smpVO = new SettlementPeriodVO(rs.getString("menu_name"), rs.getInt("menu_price"));
				list.add(smpVO);
			}//end while
		}finally {
		// 6. 연결끊기
			dbCon.dbClose(rs, pstmt, con);
		}//end finally
		
		return list;
	}//selectSettlementPeriod

}//class
