package kiosk_prj.coupon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kiosk_prj.coupon.vo.CoupConditionPriceVO;

public class CoupConditionPriceDAO {
	
	private static CoupConditionPriceDAO ccpDAO;
	
	private CoupConditionPriceDAO() {
	} // CoupConditionPriceDAO
	
	public static CoupConditionPriceDAO getInstance() {
		if(ccpDAO == null) {
			ccpDAO = new CoupConditionPriceDAO();
		} // end if
		return ccpDAO;
	} // getInstance
	
	public void insertCoupConditionPrice(CoupConditionPriceVO ccpVO) throws SQLException {
		// 1. 드라이버 로딩
		DbConnection dbCon = DbConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			// 2. 커넥션 얻기
			con = dbCon.getConnection(DbConnection.URL, DbConnection.ID, DbConnection.PASS);
			
			// 3. 쿼리문 생성 객체 얻기
			// 		값이 들어가는 위치는 바인드 변수 `?`를 사용한다
			// 		바인드 변수에는 `'` 를 사용하지 않는다
			String insertCoupConditionPrice = "insert into COUPON_PUBLISH_CONDITION_PRICE(CONDITION_PRICE_NO, CONDITION_TYPE_NO, CONDITION_PRICE) values (SEQ_COND_PRICE_NO.nextval, ?, ?)";
			pstmt = con.prepareStatement(insertCoupConditionPrice);
			
			// 4. 바인드 변수에 값 설정
			pstmt.setInt(1, ccpVO.getConditionTypeNo());
			pstmt.setInt(2, ccpVO.getConditionPrice());
			
			// 5. 쿼리문 수행 후 결과 얻기
			//		부모(Statement)의 executeXxx(sql)메소드는 절대로 사용하지 않는다 
			//		-> 실행해도 값이 안 나옴
			//		=> 매개변수 없는 executeXxx() 메소드를 사용해야 한다
			pstmt.executeUpdate();
			
		} finally {
			// 6. 연결 끊기
			dbCon.dbClose(null, pstmt, con);
		} // end finally
		
	} // insertCoupConditionPrice
	
	public int updateCoupConditionPrice(CoupConditionPriceVO ccpVO) throws SQLException {
		int cnt = 0;
		
		// 1. 드라이버 로딩
		DbConnection dbCon = DbConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			// 2. 커넥션 얻기
			con = dbCon.getConnection(DbConnection.URL, DbConnection.ID, DbConnection.PASS);
			
			// 3. 쿼리문 생성 객체 얻기
			// 		값이 들어가는 위치는 바인드 변수 `?`를 사용한다
			// 		바인드 변수에는 `'` 를 사용하지 않는다
			String updateCoupConditionPrice = "update COUPON_PUBLISH_CONDITION_PRICE set CONDITION_TYPE_NO=?, CONDITION_PRICE=? where CONDITION_PRICE_NO=?";
			pstmt = con.prepareStatement(updateCoupConditionPrice);
			
			// 4. 바인드 변수에 값 설정
			pstmt.setInt(1, ccpVO.getConditionTypeNo());
			pstmt.setInt(2, ccpVO.getConditionPrice());
			pstmt.setInt(3, ccpVO.getConditionPriceNo());
			
			// 5. 쿼리문 수행 후 결과 얻기
			//		부모(Statement)의 executeXxx(sql)메소드는 절대로 사용하지 않는다 
			//		-> 실행해도 값이 안 나옴
			//		=> 매개변수 없는 executeXxx() 메소드를 사용해야 한다
			cnt = pstmt.executeUpdate();
		} finally {
			// 6. 연결 끊기
			dbCon.dbClose(null, pstmt, con);
		} // end finally 
		
		return cnt;
	} // updateCoupConditionPrice
	
	public CoupConditionPriceVO selectOneCoupConditionPrice(int conditionPriceNo) throws SQLException {
		CoupConditionPriceVO ccpVO = null;
		
		// 1. 드라이버 로딩
		DbConnection dbCon = DbConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// 2. 커넥션 얻기
			con = dbCon.getConnection(DbConnection.URL, DbConnection.ID, DbConnection.PASS);
			
			// 3. 쿼리문 생성 객체 얻기
			// 		값이 들어가는 위치는 바인드 변수 `?`를 사용한다
			// 		바인드 변수에는 `'` 를 사용하지 않는다
			String selectOneCoupConditionPrice = "select CONDITION_TYPE_NO, CONDITION_PRICE from COUPON_PUBLISH_CONDITION_PRICE where CONDITION_PRICE_NO=?";
			pstmt = con.prepareStatement(selectOneCoupConditionPrice);
			
			// 4. 바인드 변수에 값 설정
			pstmt.setInt(1, conditionPriceNo);
			
			// 5. 쿼리문 수행 후 결과 얻기
			//		부모(Statement)의 executeXxx(sql)메소드는 절대로 사용하지 않는다 
			//		-> 실행해도 값이 안 나옴
			//		=> 매개변수 없는 executeXxx() 메소드를 사용해야 한다
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ccpVO = new CoupConditionPriceVO(conditionPriceNo, rs.getInt("CONDITION_TYPE_NO"), rs.getInt("CONDITION_PRICE"));
			} // end if
			
		} finally {
			// 6. 연결 끊기
			dbCon.dbClose(rs, pstmt, con);
		} // end finally 
		
		return ccpVO;
	} // selectOneCoupConditionPrice

} // class
