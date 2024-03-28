package kiosk_prj.coupon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kiosk_prj.coupon.vo.CouponAutoPubVO;
import kiosk_prj.coupon.vo.CouponHeldVO;

public class CouponHeldDAO {
	
	private static CouponHeldDAO chDAO;
	
	public static final int USABLE = 0;
	public static final int USED = 1;
	public static final int EXPIRE = 2;
	public static final int UN_USABLE = 3;
	public static final int ALL	= 4;
	
	private CouponHeldDAO() {
	} // CouponHeldDAO
	
	public static CouponHeldDAO getInstance() {
		if(chDAO == null) {
			chDAO = new CouponHeldDAO();
		} // end if
		return chDAO;
	} // getInstance
	
	public void insertCoupHeld(List<CouponAutoPubVO> listCapVO) throws SQLException {
		CouponAutoPubVO capVO = null;
		for (int i = 0; i < listCapVO.size(); i++) {
			capVO = listCapVO.get(i);
			insertCoupHeld(new CouponHeldVO(capVO.getPhoneNumber(), capVO.getConditionPrice(), capVO.getConditionTypeNo(), capVO.getCoupKindNo()));
		} // end for
	} // insertCoupHeld
	
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
			StringBuilder insertCoupHeld = new StringBuilder();
			insertCoupHeld.append("insert into COUPON_HELD (COUP_PUB_CODE, PHONE_NUMBER, condition_price, condition_type_no, coup_kind_no) values ( ")
			.append(" 'C_'||SF_CVT_DEC2HEXA(?, 62, 5)||SF_CVT_DEC2HEXA(?, 62, 2)||SF_CVT_DEC2HEXA(?, 62, 3)||SF_CVT_DEC2HEXA(seq_coup_pub_code.nextval, 62, 4) ")
			.append(" , ?, ?, ?, ?) ");
			pstmt = con.prepareStatement(insertCoupHeld.toString());
			
			// 4. 바인드 변수에 값 설정
			pstmt.setInt(1, chVO.getConditionPrice());
			pstmt.setInt(2, chVO.getConditionTypeNo());
			pstmt.setInt(3, chVO.getCoupKindNo());
			pstmt.setString(4, chVO.getPhoneNumber());
			pstmt.setInt(5, chVO.getConditionPrice());
			pstmt.setInt(6, chVO.getConditionTypeNo());
			pstmt.setInt(7, chVO.getCoupKindNo());
			
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
	
	/**
	 * 기간이 만료된 쿠폰을 status_use='2' 로 전환시켜주는 일
	 * update지만 0이 반환된다고 해서 실패가 아님
	 * @return
	 * @throws SQLException
	 */
	public int updateExpireCoupon() throws SQLException {
		int cnt = 0;
		
		// 1. 드라이버 로딩
		DbConnection dbCon = DbConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			// 2. 커넥션 얻기
			con = dbCon.getConnection(DbConnection.URL, DbConnection.ID, DbConnection.PASS);
			
			// 3. 쿼리문 생성 객체 얻기
			StringBuilder updateExpireCoupon = new StringBuilder();
			updateExpireCoupon
			.append("	update	(	")
			.append("			select	to_date(to_char(add_months(ch.PUBLISH_DATE + 1, ck.EXPIRES_PERIOD), 'yyyy-mm-dd')) expire_date, ch.STATUS_USE	")
			.append("			from	COUPON_HELD ch, COUPON_KIND ck	")
			.append("			where	ch.COUP_KIND_NO=ck.COUP_KIND_NO	")
			.append("			)	")
			.append("	set		status_use='2'	")
			.append("	where	status_use='0' and sysdate > expire_date	");
			
			pstmt = con.prepareStatement(updateExpireCoupon.toString());
			
			// 4. 바인드 변수에 값 설정
			
			// 5. 쿼리문 수행 후 결과 얻기
			cnt = pstmt.executeUpdate();
		} finally {
			dbCon.dbClose(null, pstmt, con);
		} // end finally 
		
		return cnt;
	} // updateExpireCoupon
	
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
			String selectAllCoupHeld = "select COUP_PUB_CODE, PHONE_NUMBER, PUBLISH_DATE, STATUS_USE, USE_COUP_DATE, condition_price, condition_type_no, coup_kind_no from COUPON_HELD";
			pstmt = con.prepareStatement(selectAllCoupHeld);
			
			// 4. 바인드 변수에 값 설정
			
			// 5. 쿼리문 수행 후 결과 얻기
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new CouponHeldVO(rs.getString("COUP_PUB_CODE"), rs.getString("PHONE_NUMBER"), rs.getDate("PUBLISH_DATE"), rs.getString("STATUS_USE"), rs.getDate("USE_COUP_DATE"), rs.getInt("condition_price"), rs.getInt("condition_type_no"), rs.getInt("coup_kind_no")));
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
			String selectOneCoupHeld = "select PHONE_NUMBER, PUBLISH_DATE, STATUS_USE, USE_COUP_DATE, condition_price, condition_type_no, coup_kind_no from COUPON_HELD where COUP_PUB_CODE=?";
			pstmt = con.prepareStatement(selectOneCoupHeld);
			
			// 4. 바인드 변수에 값 설정
			pstmt.setString(1, coupPubCode);
			
			// 5. 쿼리문 수행 후 결과 얻기
			rs = pstmt.executeQuery();
			if(rs.next()) {
				chVO = new CouponHeldVO(coupPubCode, rs.getString("PHONE_NUMBER"), rs.getDate("PUBLISH_DATE"), rs.getString("STATUS_USE"), rs.getDate("USE_COUP_DATE"), rs.getInt("condition_price"), rs.getInt("condition_type_no"), rs.getInt("coup_kind_no"));
			} // end while
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		} // end finally 
		
		return chVO;
	} // selectOneCoupHeld
	
	/**
	 * 사용자 한 명에게 그동안 발급됐던 모든 쿠폰 조회
	 * @param phoneNumber
	 * @return
	 * @throws SQLException
	 */
	public List<CouponHeldVO> selectUserCoupHeld(String phoneNumber) throws SQLException{
		return selectUserCoupHeld(phoneNumber, ALL);
	} // selectUserCoupHeld
	
	/**
	 * 사용자 한 명에게 발급된 쿠폰 중에서 특정 조건의 쿠폰 조회
	 * mode = 0: 사용 가능
	 * mode = 1: 사용 완료
	 * mode = 2: 만료됨
	 * mode = 3: 사용 불가 (사용완료 + 만료됨)
	 * mode = 4: 전체
	 * 
	 * @param phoneNumber
	 * @param mode
	 * @return
	 * @throws SQLException
	 */
	public List<CouponHeldVO> selectUserCoupHeld(String phoneNumber, int mode) throws SQLException{
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
			StringBuilder selectUserCoupHeld = new StringBuilder("select COUP_PUB_CODE, PHONE_NUMBER, PUBLISH_DATE, STATUS_USE, USE_COUP_DATE, condition_price, condition_type_no, coup_kind_no from COUPON_HELD where PHONE_NUMBER=? ");
			switch(mode) {
			case USABLE:
				selectUserCoupHeld.append(" and status_use='0'");
				break;
			case USED:
				selectUserCoupHeld.append(" and status_use='1'");
				break;
			case EXPIRE:
				selectUserCoupHeld.append(" and status_use='2'");
				break;
			case UN_USABLE:
				selectUserCoupHeld.append(" and (status_use='1' or status_use='2')");
				break;
			} // end case
			pstmt = con.prepareStatement(selectUserCoupHeld.toString());
			
			// 4. 바인드 변수에 값 설정
			pstmt.setString(1, phoneNumber);
			
			// 5. 쿼리문 수행 후 결과 얻기
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new CouponHeldVO(rs.getString("COUP_PUB_CODE"), phoneNumber, rs.getDate("PUBLISH_DATE"), rs.getString("STATUS_USE"), rs.getDate("USE_COUP_DATE"), rs.getInt("condition_price"), rs.getInt("condition_type_no"), rs.getInt("coup_kind_no")));
			} // end while
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		} // end finally 
		
		return list;
	} // selectUserCoupHeld
	
} // class
