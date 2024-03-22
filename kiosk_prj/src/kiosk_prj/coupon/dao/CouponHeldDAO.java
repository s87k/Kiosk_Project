package kiosk_prj.coupon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
			String insertCoupHeld = "insert into COUPON_HELD (COUP_PUB_CODE, PHONE_NUMBER, PUBLISH_DATE, COUP_KIND_NO, CONDITION_PRICE_NO) values (seq_coup_pub_code.nextval, ?, sysdate, ?, ?)";
			pstmt = con.prepareStatement(insertCoupHeld);
			
			// 4. 바인드 변수에 값 설정
			pstmt.setString(1, chVO.getPhoneNumber());
			pstmt.setInt(2, chVO.getCoupKindNo());
			pstmt.setInt(3, chVO.getConditionPriceNo());
			
			// 5. 쿼리문 수행 후 결과 얻기
			//		부모(Statement)의 executeXxx(sql)메소드는 절대로 사용하지 않는다 
			//		-> 실행해도 값이 안 나옴
			//		=> 매개변수 없는 executeXxx() 메소드를 사용해야 한다
			pstmt.executeUpdate();
		} finally {
			dbCon.dbClose(null, pstmt, con);
		} // end finally 
		
	} // insertCoupHeld
	
	public int updateCoupHeld(CouponHeldVO chVO) throws SQLException {
		int cnt = 0;
		
		// 1. 드라이버 로딩
		DbConnection dbCon = DbConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			// 2. 커넥션 얻기
			con = dbCon.getConnection(DbConnection.URL, DbConnection.ID, DbConnection.PASS);
			
			// 3. 쿼리문 생성 객체 얻기
			String updateCoupHeld = "update COUPON_HELD set STATUS_USE=?, USE_COUP_DATE=sysdate where COUP_PUB_CODE=?";
			pstmt = con.prepareStatement(updateCoupHeld);
			
			// 4. 바인드 변수에 값 설정
			pstmt.setString(1, chVO.getStatusUse());
			pstmt.setString(2, chVO.getCoupPubCode());
			
			// 5. 쿼리문 수행 후 결과 얻기
			cnt = pstmt.executeUpdate();
		} finally {
			dbCon.dbClose(null, pstmt, con);
		} // end finally 
		
		return cnt;
	} // updateCoupHeld
	
	public List<CouponHeldVO> selectAllCoupHeld() throws SQLException {
		List<CouponHeldVO> list = new ArrayList<CouponHeldVO>();
		
		// 1. 드라이버 로딩
		DbConnection dbCon = DbConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// 2. 커넥션 얻기
			con = dbCon.getConnection(DbConnection.URL, DbConnection.ID, DbConnection.PASS);
			
			// 3. 쿼리문 생성 객체 얻기
			String selectAllCoupHeld = "select COUP_PUB_CODE, PHONE_NUMBER, PUBLISH_DATE, STATUS_USE, USE_COUP_DATE, COUP_KIND_NO, CONDITION_PRICE_NO from COUPON_HELD";
			pstmt = con.prepareStatement(selectAllCoupHeld);
			
			// 4. 바인드 변수에 값 설정
			
			// 5. 쿼리문 수행 후 결과 얻기
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new CouponHeldVO(rs.getString("COUP_PUB_CODE"), rs.getString("PHONE_NUMBER"), rs.getDate("PUBLISH_DATE"), rs.getString("STATUS_USE"), rs.getDate("USE_COUP_DATE"), rs.getInt("COUP_KIND_NO"), rs.getInt("CONDITION_PRICE_NO")));
			} // end while
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		} // end finally 
		
		return list;
	} // selectAllCoupHeld
	
	public CouponHeldVO selectOneCoupHeld(String coupPubCode) throws SQLException {
		CouponHeldVO chVO = null;
		
		// 1. 드라이버 로딩
		DbConnection dbCon = DbConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// 2. 커넥션 얻기
			con = dbCon.getConnection(DbConnection.URL, DbConnection.ID, DbConnection.PASS);
			
			// 3. 쿼리문 생성 객체 얻기
			String selectOneCoupHeld = "select PHONE_NUMBER, PUBLISH_DATE, STATUS_USE, USE_COUP_DATE, COUP_KIND_NO, CONDITION_PRICE_NO from COUPON_HELD where COUP_PUB_CODE=?";
			pstmt = con.prepareStatement(selectOneCoupHeld);
			
			// 4. 바인드 변수에 값 설정
			pstmt.setString(1, coupPubCode);
			
			// 5. 쿼리문 수행 후 결과 얻기
			rs = pstmt.executeQuery();
			if(rs.next()) {
				chVO = new CouponHeldVO(coupPubCode, rs.getString("PHONE_NUMBER"), rs.getDate("PUBLISH_DATE"), rs.getString("STATUS_USE"), rs.getDate("USE_COUP_DATE"), rs.getInt("COUP_KIND_NO"), rs.getInt("CONDITION_PRICE_NO"));
			} // end while
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		} // end finally 
		
		return chVO;
	} // selectOneCoupHeld
	
	public List<CouponHeldVO> selectUserCoupHeld(String phoneNumber) throws SQLException{
		List<CouponHeldVO> list = new ArrayList<CouponHeldVO>();
		
		// 1. 드라이버 로딩
		DbConnection dbCon = DbConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// 2. 커넥션 얻기
			con = dbCon.getConnection(DbConnection.URL, DbConnection.ID, DbConnection.PASS);
			
			// 3. 쿼리문 생성 객체 얻기
			String selectUserCoupHeld = "select COUP_PUB_CODE, PHONE_NUMBER, PUBLISH_DATE, STATUS_USE, USE_COUP_DATE, COUP_KIND_NO, CONDITION_PRICE_NO from COUPON_HELD where PHONE_NUMBER=?";
			pstmt = con.prepareStatement(selectUserCoupHeld);
			
			// 4. 바인드 변수에 값 설정
			pstmt.setString(1, phoneNumber);
			
			// 5. 쿼리문 수행 후 결과 얻기
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new CouponHeldVO(rs.getString("COUP_PUB_CODE"), phoneNumber, rs.getDate("PUBLISH_DATE"), rs.getString("STATUS_USE"), rs.getDate("USE_COUP_DATE"), rs.getInt("COUP_KIND_NO"), rs.getInt("CONDITION_PRICE_NO")));
			} // end while
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		} // end finally 
		
		return list;
	} // selectUserCoupHeld
	
} // class
