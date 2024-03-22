package kiosk_prj.coupon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import kiosk_prj.coupon.vo.CouponHeldVO;

public class CouponHeldDAO {
	
	private static CouponHeldDAO chDAO;
	
	private CouponHeldDAO() {
	} // CouponHeldDAO
	
	public static CouponHeldDAO getInstance() {
		if(chDAO == null) {
			chDAO = new CouponHeldDAO();
		} // end if
		return chDAO;
	} // getInstance
	
	public void insertCoupHeld(CouponHeldVO chVO) throws SQLException {
		
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
			String insertCoupHeld = "insert into ";
			pstmt = con.prepareStatement(insertCoupHeld);
			
			// 4. 바인드 변수에 값 설정
			
			// 5. 쿼리문 수행 후 결과 얻기
			//		부모(Statement)의 executeXxx(sql)메소드는 절대로 사용하지 않는다 
			//		-> 실행해도 값이 안 나옴
			//		=> 매개변수 없는 executeXxx() 메소드를 사용해야 한다
			
			
		} finally {
			
		} // end finally 
		
	} // insertCoupHeld

} // class
