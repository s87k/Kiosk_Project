package kiosk_prj.coupon.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import kiosk_prj.coupon.vo.CoupConditionPriceVO;
import kiosk_prj.coupon.vo.CouponKindVO;
import kiosk_prj.coupon.vo.CouponPublishVO;
import kiosk_prj.coupon.vo.ResultVO;

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
			String insertCoupPub = "insert into COUPON_PUBLISH(condition_price, condition_type_no, coup_kind_no) values (?, ?, ?)";
			pstmt = con.prepareStatement(insertCoupPub);
			
			// 4. 바인드 변수에 값 설정
			pstmt.setInt(1, cpVO.getConditionPrice());
			pstmt.setInt(2, cpVO.getConditionTypeNo());
			pstmt.setInt(3, cpVO.getCoupKindNo());
			
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
	
	@Deprecated
	public ResultVO insertCoupPubProc(CoupConditionPriceVO ccpVO, CouponKindVO ckVO) throws SQLException {
		// 1. 드라이버 로딩
		DbConnection dbCon = DbConnection.getInstance();
		
		Connection con = null;
		CallableStatement cstmt = null;
		ResultVO rVO = null;
		
		try {
			// 2. 커넥션 얻기
			con = dbCon.getConnection(DbConnection.URL, DbConnection.ID, DbConnection.PASS);
			
			// 3. 프로시저 호출 객체 얻기
			cstmt = con.prepareCall("{ call insert_coup_pub(?, ?, ?, ?, ?) }");
			
			// 4. 바인드 변수에 값 설정
			//	4.1. in parameter
			cstmt.setInt(1, ccpVO.getConditionTypeNo());
			cstmt.setInt(2, ccpVO.getConditionPrice());
			cstmt.setInt(3, ckVO.getCoupKindNo());
			
			//	4.2. out parameter
			cstmt.registerOutParameter(4, Types.NUMERIC);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			
			// 5. 쿼리문 수행 후 결과 얻기
			
			cstmt.execute();
			rVO = new ResultVO(cstmt.getInt(4), cstmt.getString(5)); 
		} finally {
			// 6. 연결 끊기
			dbCon.dbClose(null, cstmt, con);
		} // end finally
		
		return rVO;
	} // insertCoupPubProc
	
	public int updateCoupPub(CouponPublishVO cpVOOld, CouponPublishVO cpVONew) throws SQLException {
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
			String updateCoupPub = "update COUPON_PUBLISH set CONDITION_PRICE=?, CONDITION_TYPE_NO=?  where CONDITION_PRICE=? and CONDITION_TYPE_NO=? and COUP_KIND_NO=?";
			pstmt = con.prepareStatement(updateCoupPub);
			
			// 4. 바인드 변수에 값 설정
			pstmt.setInt(1, cpVONew.getConditionPrice());
			pstmt.setInt(2, cpVONew.getConditionTypeNo());
			pstmt.setInt(3, cpVOOld.getConditionPrice());
			pstmt.setInt(4, cpVOOld.getConditionTypeNo());
			pstmt.setInt(5, cpVOOld.getCoupKindNo());
			
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
			String selectAllCoupPub = "select condition_price, condition_type_no, coup_kind_no from COUPON_PUBLISH";
			pstmt = con.prepareStatement(selectAllCoupPub);
			
			// 4. 바인드 변수에 값 설정
						
			// 5. 쿼리문 수행 후 결과 얻기
			//		부모(Statement)의 executeXxx(sql)메소드는 절대로 사용하지 않는다 
			//		-> 실행해도 값이 안 나옴
			//		=> 매개변수 없는 executeXxx() 메소드를 사용해야 한다
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new CouponPublishVO(rs.getInt("condition_price"), rs.getInt("condition_type_no"), rs.getInt("coup_kind_no")));
			} // end while
		} finally {
			// 6. 연결 끊기
			dbCon.dbClose(rs, pstmt, con);
		} // end finally 
		
		return list;
	} // selectAllCoupPub
	
	public CouponPublishVO selectOneCoupPub(int conditionPrice, int conditionTypeNo, int coupKindNo) throws SQLException {
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
			String selectOneCoupPub = "select condition_price, condition_type_no, coup_kind_no from COUPON_PUBLISH where condition_price=? and condition_type_no=? and coup_kind_no=?";
			pstmt = con.prepareStatement(selectOneCoupPub);
			
			// 4. 바인드 변수에 값 설정
			pstmt.setInt(1, conditionPrice);
			pstmt.setInt(2, conditionTypeNo);
			pstmt.setInt(3, coupKindNo);
			
			// 5. 쿼리문 수행 후 결과 얻기
			//		부모(Statement)의 executeXxx(sql)메소드는 절대로 사용하지 않는다 
			//		-> 실행해도 값이 안 나옴
			//		=> 매개변수 없는 executeXxx() 메소드를 사용해야 한다
			rs = pstmt.executeQuery();
			if(rs.next()) {
				cpVO = new CouponPublishVO(rs.getInt("condition_price"), rs.getInt("condition_type_no"), rs.getInt("coup_kind_no"));
			} // end if
		} finally {
			// 6. 연결 끊기
			dbCon.dbClose(rs, pstmt, con);
		} // end finally 
		
		return cpVO;
	} // selectOneCoupPub
	
} // class
