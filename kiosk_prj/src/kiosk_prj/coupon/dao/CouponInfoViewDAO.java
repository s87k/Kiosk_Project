package kiosk_prj.coupon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kiosk_prj.coupon.vo.CouponAddedInfoVO;
import kiosk_prj.coupon.vo.CouponKindVO;
import kiosk_prj.coupon.vo.CouponPubInfoVO;
import kiosk_prj.coupon.vo.StatusUse;

public class CouponInfoViewDAO {

	private static CouponInfoViewDAO civDAO;
	
	private CouponInfoViewDAO() {
	}
	
	public static CouponInfoViewDAO getInstance() {
		if(civDAO == null) {
			civDAO = new CouponInfoViewDAO();
		} // end if
		return civDAO;
	} // CouponAddedInfoDAO
	
	public List<CouponAddedInfoVO> searchAllAddedCouponView() throws SQLException {
		List<CouponAddedInfoVO> listCaivVO = new ArrayList<CouponAddedInfoVO>();
		
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
			String selectCoupKind = "select CONDITION_PRICE, CONDITION_TYPE_NO, COUP_KIND_NO, COUP_KIND_NAME, DISCOUNT, FLAG_DISABLE, EXPIRES_PERIOD, CONDITION from COUPON_ADDED_INFO_VIEW";
			pstmt = con.prepareStatement(selectCoupKind);
			
			// 4. 바인드 변수에 값 설정
			
			// 5. 쿼리문 수행 후 결과 얻기
			//		부모(Statement)의 executeXxx(sql)메소드는 절대로 사용하지 않는다 
			//		-> 실행해도 값이 안 나옴
			//		=> 매개변수 없는 executeXxx() 메소드를 사용해야 한다
			rs = pstmt.executeQuery();	// 조회된 결과를 움직일 수 있는 커서의 제어권을 받는다
			while(rs.next()) {				// 쿼리문 실행했을 때 조회 결과가 있다면
				listCaivVO.add(new CouponAddedInfoVO(rs.getInt("CONDITION_PRICE"), rs.getInt("CONDITION_TYPE_NO"), rs.getInt("COUP_KIND_NO"), rs.getString("COUP_KIND_NAME"), rs.getInt("discount"), rs.getString("flag_DISABLE").equals("0") ? false:true, rs.getInt("EXPIRES_PERIOD"), rs.getString("CONDITION")));
			} // end if
			
		} finally {
			// 6. 연결 끊기
			dbCon.dbClose(rs, pstmt, con);
		} // end finally 
		
		return listCaivVO;
	} // searchAllAddedCouponView
	
	public List<CouponPubInfoVO> selectPubCouponView() throws SQLException {
		return selectPubCouponView(StatusUse.ALL.getIntVal());
	} // searchPubCouponView
	
	public List<CouponPubInfoVO> selectPubCouponView(int mode) throws SQLException {
		List<CouponPubInfoVO> listCpiVO = new ArrayList<CouponPubInfoVO>();
		
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
			StringBuilder selectCoupKind = new StringBuilder("select COUP_PUB_CODE, COUP_KIND_NAME, DISCOUNT, MEMBER_NAME, PHONE_NUMBER, STATUS_USE, PUBLISH_DATE, USE_COUP_DATE, EXPIRE_DATE from COUPON_PUBLISH_INFO_VIEW	");
			
			if(mode == StatusUse.USABLE.getIntVal()) {
				selectCoupKind.append("	where status_use='0' ");
			} else if (mode == StatusUse.ALREADY_USE.getIntVal()) {
				selectCoupKind.append("	where status_use='1' ");	
			} else if (mode == StatusUse.EXPIRE.getIntVal()) {
				selectCoupKind.append("	where status_use='2' ");	
			} else if (mode == StatusUse.UN_USABLE.getIntVal()) {
				selectCoupKind.append("	where status_use='1' or status_use='2' ");	
			} // end else if
			selectCoupKind.append(" order by status_use ");
			pstmt = con.prepareStatement(selectCoupKind.toString());
			
			// 4. 바인드 변수에 값 설정
			
			// 5. 쿼리문 수행 후 결과 얻기
			//		부모(Statement)의 executeXxx(sql)메소드는 절대로 사용하지 않는다 
			//		-> 실행해도 값이 안 나옴
			//		=> 매개변수 없는 executeXxx() 메소드를 사용해야 한다
			rs = pstmt.executeQuery();	// 조회된 결과를 움직일 수 있는 커서의 제어권을 받는다
			String statusUse = "";
			while(rs.next()) {				// 쿼리문 실행했을 때 조회 결과가 있다면
				statusUse = rs.getString("status_use");
				listCpiVO.add(new CouponPubInfoVO(rs.getString("COUP_PUB_CODE"), 
						rs.getString("COUP_KIND_NAME"), rs.getInt("discount"), 
						rs.getString("MEMBER_NAME"), rs.getString("PHONE_NUMBER"), 
						statusUse.equals(StatusUse.USABLE.getStrVal()) ? StatusUse.USABLE.getName() : 
							(statusUse.equals(StatusUse.ALREADY_USE.getStrVal()) ? StatusUse.ALREADY_USE.getName() : StatusUse.EXPIRE.getName()), 
						rs.getDate("PUBLISH_DATE"), rs.getDate("USE_COUP_DATE"), rs.getDate("EXPIRE_DATE")));
			} // end if
			
		} finally {
			// 6. 연결 끊기
			dbCon.dbClose(rs, pstmt, con);
		} // end finally 		
		
		return listCpiVO;
	} // searchPubCouponView
	
} // CouponAddedInfoDAO
