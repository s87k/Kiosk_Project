package kiosk_prj.coupon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kiosk_prj.coupon.vo.CouponPublishVO;

public class CouponPublishDAO {
	
	private static CouponPublishDAO cpDAO;
	
	private CouponPublishDAO() {
	} // CouponPublishDAO
	
	public static CouponPublishDAO getInstance() {
		if(cpDAO == null) {
			cpDAO = new CouponPublishDAO();
		} // end if
		return cpDAO;
	} // getInstance
	
	public void insertCoupPub(CouponPublishVO cpVO) throws SQLException {
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
			String insertCoupPub = "insert into COUPON_PUBLISH(COUP_KIND_NO, CONDITION_PRICE_NO) values (?, ?)";
			pstmt = con.prepareStatement(insertCoupPub);
			
			// 4. 바인드 변수에 값 설정
			pstmt.setInt(1, cpVO.getCoupKindNo());
			pstmt.setInt(2, cpVO.getConditionPriceNo());
			
			// 5. 쿼리문 수행 후 결과 얻기
			//		부모(Statement)의 executeXxx(sql)메소드는 절대로 사용하지 않는다 
			//		-> 실행해도 값이 안 나옴
			//		=> 매개변수 없는 executeXxx() 메소드를 사용해야 한다
			pstmt.executeUpdate();
		} finally {
			// 6. 연결 끊기
			dbCon.dbClose(null, pstmt, con);
		} // end finally
			
	} // insertCoupPub
	
	public List<CouponPublishVO> selectAllCoupPub() throws SQLException {
		List<CouponPublishVO> list = new ArrayList<CouponPublishVO>();
		
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
			String selectAllCoupPub = "select COUP_KIND_NO, CONDITION_PRICE_NO from COUPON_PUBLISH";
			pstmt = con.prepareStatement(selectAllCoupPub);
			
			// 4. 바인드 변수에 값 설정
						
			// 5. 쿼리문 수행 후 결과 얻기
			//		부모(Statement)의 executeXxx(sql)메소드는 절대로 사용하지 않는다 
			//		-> 실행해도 값이 안 나옴
			//		=> 매개변수 없는 executeXxx() 메소드를 사용해야 한다
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new CouponPublishVO(rs.getInt("COUP_KIND_NO"), rs.getInt("CONDITION_PRICE_NO")));
			} // end while
		} finally {
			// 6. 연결 끊기
			dbCon.dbClose(rs, pstmt, con);
		} // end finally 
		
		return list;
	} // selectAllCoupPub
	
	public CouponPublishVO selectOneCoupPub(int coupKindNo, int conditionPriceNo) throws SQLException {
		CouponPublishVO cpVO = null;
		
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
			String selectOneCoupPub = "select COUP_KIND_NO, CONDITION_PRICE_NO from COUPON_PUBLISH where COUP_KIND_NO=? and CONDITION_PRICE_NO=?";
			pstmt = con.prepareStatement(selectOneCoupPub);
			
			// 4. 바인드 변수에 값 설정
			pstmt.setInt(1, coupKindNo);
			pstmt.setInt(2, conditionPriceNo);
			
			// 5. 쿼리문 수행 후 결과 얻기
			//		부모(Statement)의 executeXxx(sql)메소드는 절대로 사용하지 않는다 
			//		-> 실행해도 값이 안 나옴
			//		=> 매개변수 없는 executeXxx() 메소드를 사용해야 한다
			rs = pstmt.executeQuery();
			if(rs.next()) {
				cpVO = new CouponPublishVO(rs.getInt("COUP_KIND_NO"), rs.getInt("CONDITION_PRICE_NO"));
			} // end if
		} finally {
			// 6. 연결 끊기
			dbCon.dbClose(rs, pstmt, con);
		} // end finally 
		
		return cpVO;
	} // selectOneCoupPub
	
} // class
