package kiosk_prj.coupon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kiosk_prj.coupon.vo.CoupConditionTypeVO;

public class CoupConditionTypeDAO {
	
	private static CoupConditionTypeDAO cctDAO;
	
	private CoupConditionTypeDAO() {
	} // CoupConditionTypeDAO
	
	public static CoupConditionTypeDAO getInstance() {
		if(cctDAO == null) {
			cctDAO = new CoupConditionTypeDAO();
		} // end if
		return cctDAO;
	} // getInstance
	
	public List<CoupConditionTypeVO> selectAllCoupConditionType() throws SQLException {
		
		List<CoupConditionTypeVO> list = new ArrayList<CoupConditionTypeVO>();
		
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
			String selectCoupKind = "select CONDITION_TYPE_NO, CONDITION_TYPE_NAME from COUPON_PUBLISH_CONDITION_TYPE";
			pstmt = con.prepareStatement(selectCoupKind);
			
			// 4. 바인드 변수에 값 설정
			
			// 5. 쿼리문 수행 후 결과 얻기
			//		부모(Statement)의 executeXxx(sql)메소드는 절대로 사용하지 않는다 
			//		-> 실행해도 값이 안 나옴
			//		=> 매개변수 없는 executeXxx() 메소드를 사용해야 한다
			rs = pstmt.executeQuery();	// 조회된 결과를 움직일 수 있는 커서의 제어권을 받는다
			while(rs.next()) {				// 쿼리문 실행했을 때 조회 결과가 있다면
				list.add(new CoupConditionTypeVO(rs.getInt("CONDITION_TYPE_NO"), rs.getString("CONDITION_TYPE_NAME")));
			} // end if
			
		} finally {
			// 6. 연결 끊기
			dbCon.dbClose(rs, pstmt, con);
		} // end finally 
		
		return list;
		
	} // selectAllCoupConditionType
	
	public CoupConditionTypeVO selectOneCoupConditionType(int conditionTypeNo) throws SQLException {
		CoupConditionTypeVO cctVO = null;
		
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
			String selectCoupKind = "select CONDITION_TYPE_NAME from COUPON_PUBLISH_CONDITION_TYPE where CONDITION_TYPE_NO=?";
			pstmt = con.prepareStatement(selectCoupKind);
			
			// 4. 바인드 변수에 값 설정
			pstmt.setInt(1, conditionTypeNo);
			
			// 5. 쿼리문 수행 후 결과 얻기
			//		부모(Statement)의 executeXxx(sql)메소드는 절대로 사용하지 않는다 
			//		-> 실행해도 값이 안 나옴
			//		=> 매개변수 없는 executeXxx() 메소드를 사용해야 한다
			rs = pstmt.executeQuery();	// 조회된 결과를 움직일 수 있는 커서의 제어권을 받는다
			if(rs.next()) {				// 쿼리문 실행했을 때 조회 결과가 있다면
				cctVO = new CoupConditionTypeVO(conditionTypeNo, rs.getString("CONDITION_TYPE_NAME"));
			} // end if
			
		} finally {
			// 6. 연결 끊기
			dbCon.dbClose(rs, pstmt, con);
		} // end finally 
		
		return cctVO;
	} // selectOneCoupConditionType
	
}
