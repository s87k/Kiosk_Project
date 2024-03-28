package kiosk_prj.coupon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kiosk_prj.coupon.vo.CouponKindVO;
import oracle.security.o3logon.a;

public class CouponKindDAO {
	private static CouponKindDAO ckDAO;
	
	private CouponKindDAO() {
	} // CouponKindDAO
	
	public static CouponKindDAO getInstance() {
		if(ckDAO == null) {
			ckDAO = new CouponKindDAO();
		} // end if
		return ckDAO;
	} // getInstance
	
	public void insertCoupKind(CouponKindVO ckVO) throws SQLException {
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
			StringBuilder insertCoupKind = new StringBuilder("insert into coupon_kind(COUP_KIND_NO, ID, COUP_KIND_NAME, DISCOUNT, expires_period, FLAG_PUBLISHABLE, DELETE_DATE, FLAG_DELETE) ")
				.append("values (seq_coupon_kind_no.nextval, ?, ?, ?, ?, ?, ?, ?)");
			pstmt = con.prepareStatement(insertCoupKind.toString());
			
			// 4. 바인드 변수에 값 설정
			pstmt.setString(1, ckVO.getAdminId());
			pstmt.setString(2, ckVO.getCoupKindName());
			pstmt.setInt(3, ckVO.getDiscount());
			pstmt.setInt(4, ckVO.getExpiresPeriod());
			pstmt.setString(5, ckVO.isFlagPublishable() == false ? "0" : "1");
			pstmt.setDate(6, ckVO.getDeleteDate());
			pstmt.setString(7, ckVO.isFlagDelete() == false ? "0" : "1");
			
			// 5. 쿼리문 수행 후 결과 얻기
			//		부모(Statement)의 executeXxx(sql)메소드는 절대로 사용하지 않는다 
			//		-> 실행해도 값이 안 나옴
			//		=> 매개변수 없는 executeXxx() 메소드를 사용해야 한다
			pstmt.executeUpdate();
		} finally {
			// 6. 연결 끊기
			dbCon.dbClose(null, pstmt, con);
		} // end finally 
	} // insertCoupKind
	
	public int updateCoupKind(CouponKindVO ckVO) throws SQLException {
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
			String updateCoupKind = "update coupon_kind set coup_kind_name=?, FLAG_PUBLISHABLE=?  where coup_kind_no=?";
			pstmt = con.prepareStatement(updateCoupKind);
			
			// 4. 바인드 변수에 값 설정
			pstmt.setString(1, ckVO.getCoupKindName());
			pstmt.setString(2, ckVO.isFlagPublishable() == false ? "0" : "1");
			pstmt.setInt(3, ckVO.getCoupKindNo());
			
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
	} // updateCoupKind
	
	public int deleteCoupKind(int coupKindNo) throws SQLException{
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
			String deleteCoupKind = "update coupon_kind set FLAG_PUBLISHABLE='0', DELETE_DATE=sysdate, FLAG_DELETE='1' where coup_kind_no=?";
			pstmt = con.prepareStatement(deleteCoupKind);
			
			// 4. 바인드 변수에 값 설정
			pstmt.setInt(1, coupKindNo);
			
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
	} // deleteCoupKind
	
	public CouponKindVO selectOneCoupKind(int coupKindNo) throws SQLException {
		CouponKindVO ckVO = null;
		
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
			String selectCoupKind = "select ID, COUP_KIND_NAME, DISCOUNT, expires_period, INPUT_DATE, FLAG_PUBLISHABLE, DELETE_DATE, FLAG_DELETE from coupon_kind where coup_kind_no=? and flag_delete='0'";
			pstmt = con.prepareStatement(selectCoupKind);
			
			// 4. 바인드 변수에 값 설정
			pstmt.setInt(1, coupKindNo);
			
			// 5. 쿼리문 수행 후 결과 얻기
			//		부모(Statement)의 executeXxx(sql)메소드는 절대로 사용하지 않는다 
			//		-> 실행해도 값이 안 나옴
			//		=> 매개변수 없는 executeXxx() 메소드를 사용해야 한다
			rs = pstmt.executeQuery();	// 조회된 결과를 움직일 수 있는 커서의 제어권을 받는다
			if(rs.next()) {				// 쿼리문 실행했을 때 조회 결과가 있다면
				ckVO = new CouponKindVO(coupKindNo, rs.getString("id"), rs.getString("coup_kind_name"), rs.getInt("discount"), rs.getInt("expires_period"), rs.getDate("input_date"), rs.getString("flag_publishable").equals("0") ? false:true, rs.getDate("delete_date"), rs.getString("flag_delete").equals("0") ? false : true);
			} // end if
			
		} finally {
			// 6. 연결 끊기
			dbCon.dbClose(rs, pstmt, con);
		} // end finally 
		
		return ckVO;
	} // CouponKindVO
	
	public List<CouponKindVO> selectAllCoupKind() throws SQLException {
		List<CouponKindVO> list = new ArrayList<CouponKindVO>();
		
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
			String selectCoupKind = "select COUP_KIND_NO, ID, COUP_KIND_NAME, DISCOUNT, expires_period, INPUT_DATE, FLAG_PUBLISHABLE, DELETE_DATE, FLAG_DELETE from coupon_kind where flag_delete='0' order by FLAG_PUBLISHABLE desc, COUP_KIND_NO";
			pstmt = con.prepareStatement(selectCoupKind);
			
			// 4. 바인드 변수에 값 설정
			
			// 5. 쿼리문 수행 후 결과 얻기
			//		부모(Statement)의 executeXxx(sql)메소드는 절대로 사용하지 않는다 
			//		-> 실행해도 값이 안 나옴
			//		=> 매개변수 없는 executeXxx() 메소드를 사용해야 한다
			rs = pstmt.executeQuery();	// 조회된 결과를 움직일 수 있는 커서의 제어권을 받는다
			while(rs.next()) {				// 쿼리문 실행했을 때 조회 결과가 있다면
				list.add(new CouponKindVO(rs.getInt("coup_kind_no"), rs.getString("id"), rs.getString("coup_kind_name"), rs.getInt("discount"), rs.getInt("expires_period"), rs.getDate("input_date"), rs.getString("flag_publishable").equals("0") ? false:true, rs.getDate("delete_date"), rs.getString("flag_delete").equals("0") ? false : true));
			} // end if
			
		} finally {
			// 6. 연결 끊기
			dbCon.dbClose(rs, pstmt, con);
		} // end finally 
		
		return list;
	} // CouponKindVO
	
	public List<CouponKindVO> selectFlagPublishCoupKind(boolean flagPublishable) throws SQLException {
		
		List<CouponKindVO> list = new ArrayList<CouponKindVO>();
		
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
			String selectCoupKind = "select COUP_KIND_NO, ID, COUP_KIND_NAME, DISCOUNT, expires_period, INPUT_DATE, FLAG_PUBLISHABLE, DELETE_DATE, FLAG_DELETE from coupon_kind where flag_publishable=? and flag_delete='0'";
			pstmt = con.prepareStatement(selectCoupKind);
			
			// 4. 바인드 변수에 값 설정
			pstmt.setString(1, flagPublishable == false ? "0" : "1");
			
			// 5. 쿼리문 수행 후 결과 얻기
			//		부모(Statement)의 executeXxx(sql)메소드는 절대로 사용하지 않는다 
			//		-> 실행해도 값이 안 나옴
			//		=> 매개변수 없는 executeXxx() 메소드를 사용해야 한다
			rs = pstmt.executeQuery();	// 조회된 결과를 움직일 수 있는 커서의 제어권을 받는다
			while(rs.next()) {				// 쿼리문 실행했을 때 조회 결과가 있다면
				list.add(new CouponKindVO(rs.getInt("coup_kind_no"), rs.getString("id"), rs.getString("coup_kind_name"), rs.getInt("discount"), rs.getInt("expires_period"), rs.getDate("input_date"), rs.getString("flag_publishable").equals("0") ? false:true, rs.getDate("delete_date"), rs.getString("flag_delete").equals("0") ? false : true));
			} // end if
			
		} finally {
			// 6. 연결 끊기
			dbCon.dbClose(rs, pstmt, con);
		} // end finally 
		
		return list;
		
	} // selectFlagPublishCoupKind
	
} // class
