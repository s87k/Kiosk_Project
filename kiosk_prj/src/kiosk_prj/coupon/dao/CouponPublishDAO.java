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
import kiosk_prj.coupon.vo.CouponAutoPubVO;
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
			
			StringBuilder updateCoupPub = new StringBuilder("update COUPON_PUBLISH set CONDITION_PRICE=?, CONDITION_TYPE_NO=? ");
			boolean flagSame = cpVOOld.isFlagDisable() == cpVONew.isFlagDisable(); 
			if(flagSame) {
				updateCoupPub.append(" where CONDITION_PRICE=? and CONDITION_TYPE_NO=? and COUP_KIND_NO=?");
			} else {
				updateCoupPub.append(" , FLAG_DISABLE=?, DISABLE_DATE=sysdate where CONDITION_PRICE=? and CONDITION_TYPE_NO=? and COUP_KIND_NO=?");
			} // end else
			pstmt = con.prepareStatement(updateCoupPub.toString());
			
			// 4. 바인드 변수에 값 설정
			pstmt.setInt(1, cpVONew.getConditionPrice());
			pstmt.setInt(2, cpVONew.getConditionTypeNo());
			if(flagSame) {
				pstmt.setInt(3, cpVOOld.getConditionPrice());
				pstmt.setInt(4, cpVOOld.getConditionTypeNo());
				pstmt.setInt(5, cpVOOld.getCoupKindNo());
			} else {
				pstmt.setString(3, cpVONew.isFlagDisable() == true ? "1" : "0");
				pstmt.setInt(4, cpVOOld.getConditionPrice());
				pstmt.setInt(5, cpVOOld.getConditionTypeNo());
				pstmt.setInt(6, cpVOOld.getCoupKindNo());
			} // end else
			
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
			String selectAllCoupPub = "select condition_price, condition_type_no, coup_kind_no, FLAG_DISABLE, DISABLE_DATE from COUPON_PUBLISH";
			pstmt = con.prepareStatement(selectAllCoupPub);
			
			// 4. 바인드 변수에 값 설정
						
			// 5. 쿼리문 수행 후 결과 얻기
			//		부모(Statement)의 executeXxx(sql)메소드는 절대로 사용하지 않는다 
			//		-> 실행해도 값이 안 나옴
			//		=> 매개변수 없는 executeXxx() 메소드를 사용해야 한다
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new CouponPublishVO(rs.getInt("condition_price"), rs.getInt("condition_type_no"), rs.getInt("coup_kind_no"), rs.getString("FLAG_DISABLE").equals("0") ? false : true, rs.getDate("FLAG_DISABLE_DATE")));
			} // end while
		} finally {
			// 6. 연결 끊기
			dbCon.dbClose(rs, pstmt, con);
		} // end finally 
		
		return list;
	} // selectAllCoupPub
	
	public List<CouponAutoPubVO> selectShouldPublishCoup(String phoneNumber, int amount) throws SQLException {
		List<CouponAutoPubVO> listCapVO = new ArrayList<CouponAutoPubVO>();
		
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
			StringBuilder selectShouldPublishCoup = new StringBuilder();
			selectShouldPublishCoup
			.append("	select	? PHONE_NUMBER, ck.COUP_KIND_NAME, ck.discount, ck.EXPIRES_PERIOD, replace(cpct.CONDITION_TYPE_NAME, '{}', csh.CONDITION_PRICE) condition_msg, csh.CONDITION_PRICE, cpct.CONDITION_TYPE_NO, ck.COUP_KIND_NO	")
			.append("	from	(select	CONDITION_PRICE, CONDITION_TYPE_NO, COUP_KIND_NO	")
			.append("			from	(select	cp.CONDITION_PRICE, cp.CONDITION_TYPE_NO, cp.COUP_KIND_NO, ch.should_publish, cp.FLAG_DISABLE	")
			.append("					from	COUPON_PUBLISH cp,	")
			.append("							(select	CONDITION_PRICE, CONDITION_TYPE_NO, COUP_KIND_NO, '0' should_publish	")
			.append("							from	coupon_held	")
			.append("							where	PHONE_NUMBER=?	")
			.append("							group by (CONDITION_PRICE, CONDITION_TYPE_NO, COUP_KIND_NO)) ch	")
			.append("					where	(ch.CONDITION_PRICE(+)=cp.condition_price and ch.CONDITION_TYPE_NO(+)=cp.CONDITION_TYPE_NO and ch.COUP_KIND_NO(+)=cp.COUP_KIND_NO)	")
			.append("					)	")
			.append("			where	should_publish is null and CONDITION_TYPE_NO='1' and FLAG_DISABLE='1' and CONDITION_PRICE <	")
			.append("					(select	sum(amount)	")
			.append("					from	SUMMARY_ORDER	")
			.append("					where 	PHONE_NUMBER=?)	")
			.append("			) csh, coupon_kind ck, COUPON_PUBLISH_CONDITION_TYPE cpct	")
			.append("	where	(csh.COUP_KIND_NO=ck.COUP_KIND_NO and csh.CONDITION_TYPE_NO=cpct.CONDITION_TYPE_NO)	")
			.append("	union all	")
			.append("	select	? PHONE_NUMBER, ck.COUP_KIND_NAME, ck.discount, ck.EXPIRES_PERIOD, replace(cpct.CONDITION_TYPE_NAME, '{}', cp.CONDITION_PRICE) condition_msg, cp.CONDITION_PRICE, cp.CONDITION_TYPE_NO, cp.COUP_KIND_NO	")
			.append("	from	coupon_publish cp, COUPON_KIND ck, COUPON_PUBLISH_CONDITION_TYPE cpct	")
			.append("	where	(cp.COUP_KIND_NO=ck.COUP_KIND_NO and cp.CONDITION_TYPE_NO=cpct.CONDITION_TYPE_NO) and	")
			.append("			(cp.CONDITION_TYPE_NO=2 and cp.flag_disable=1 and cp.CONDITION_PRICE <= ?)	");
			/*
			.append("	select 	? PHONE_NUMBER, ck.COUP_KIND_NAME, ck.discount, ck.EXPIRES_PERIOD, replace(cpct.CONDITION_TYPE_NAME, '{}', csh.CONDITION_PRICE) condition_msg, csh.CONDITION_PRICE, csh.CONDITION_TYPE_NO, csh.COUP_KIND_NO	")
			.append("	from	COUPON_KIND ck,	")
			.append("			(select	CONDITION_PRICE,CONDITION_TYPE_NO,COUP_KIND_NO	")
			.append("			from	(	")
			.append("					select	cp.CONDITION_PRICE, cp.CONDITION_TYPE_NO, cp.COUP_KIND_NO, cp.FLAG_DISABLE	")
			.append("					from	coupon_publish cp, coupon_held ch	")
			.append("					where	(ch.CONDITION_PRICE(+)=cp.condition_price and ch.CONDITION_TYPE_NO(+)=cp.CONDITION_TYPE_NO and ch.COUP_KIND_NO(+)=cp.COUP_KIND_NO) and	")
			.append("							(ch.phone_number is null or ch.PHONE_NUMBER not like ?)	")
			.append("					)	")
			.append("			where	CONDITION_TYPE_NO=1 and  flag_disable='1' and CONDITION_PRICE <	")
			.append("					(select	sum(amount)	")
			.append("					from	SUMMARY_ORDER	")
			.append("					where 	PHONE_NUMBER=?)) csh	")
			.append("					, COUPON_PUBLISH_CONDITION_TYPE cpct	")
			.append("	where	csh.COUP_KIND_NO=ck.COUP_KIND_NO and csh.CONDITION_TYPE_NO=cpct.CONDITION_TYPE_NO	")
			.append("	union all	")
			.append("	select	? PHONE_NUMBER, ck.COUP_KIND_NAME, ck.discount, ck.EXPIRES_PERIOD, replace(cpct.CONDITION_TYPE_NAME, '{}', cp.CONDITION_PRICE) condition_msg, cp.CONDITION_PRICE, cp.CONDITION_TYPE_NO, cp.COUP_KIND_NO	")
			.append("	from	coupon_publish cp, COUPON_KIND ck, COUPON_PUBLISH_CONDITION_TYPE cpct	")
			.append("	where	(cp.COUP_KIND_NO=ck.COUP_KIND_NO and cp.CONDITION_TYPE_NO=cpct.CONDITION_TYPE_NO) and	")
			.append("			(cp.CONDITION_TYPE_NO=2 and cp.flag_disable=1 and cp.CONDITION_PRICE <= ?)	");
			*/
			pstmt = con.prepareStatement(selectShouldPublishCoup.toString());
			
			// 4. 바인드 변수에 값 설정
			pstmt.setString(1, phoneNumber);
			pstmt.setString(2, phoneNumber);
			pstmt.setString(3, phoneNumber);
			pstmt.setString(4, phoneNumber);
			pstmt.setInt(5, amount);
						
			// 5. 쿼리문 수행 후 결과 얻기
			//		부모(Statement)의 executeXxx(sql)메소드는 절대로 사용하지 않는다 
			//		-> 실행해도 값이 안 나옴
			//		=> 매개변수 없는 executeXxx() 메소드를 사용해야 한다
			rs = pstmt.executeQuery();
			while(rs.next()) {
				listCapVO.add(new CouponAutoPubVO(rs.getString("PHONE_NUMBER"), rs.getString("COUP_KIND_NAME"), rs.getInt("DISCOUNT"), rs.getInt("EXPIRES_PERIOD"),
						rs.getString("CONDITION_MSG"), rs.getInt("CONDITION_PRICE"), rs.getInt("CONDITION_TYPE_NO"), rs.getInt("COUP_KIND_NO")));
			} // end while
		} finally {
			// 6. 연결 끊기
			dbCon.dbClose(rs, pstmt, con);
		} // end finally 
		
		return listCapVO;
	} // selectShouldPublishCoup
	
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
			String selectOneCoupPub = "select condition_price, condition_type_no, coup_kind_no, FLAG_DISABLE, DISABLE_DATE from COUPON_PUBLISH where condition_price=? and condition_type_no=? and coup_kind_no=?";
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
				cpVO = new CouponPublishVO(rs.getInt("condition_price"), rs.getInt("condition_type_no"), rs.getInt("coup_kind_no"), rs.getString("FLAG_DISABLE").equals("0") ? false : true, rs.getDate("DISABLE_DATE"));
			} // end if
		} finally {
			// 6. 연결 끊기
			dbCon.dbClose(rs, pstmt, con);
		} // end finally 
		
		return cpVO;
	} // selectOneCoupPub
	
	public boolean isCoupKindPublished(int coupKindNo) throws SQLException {
		boolean flagPublish = false;
		
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
			String selectOneCoupPub = "select '0' output from COUPON_PUBLISH where coup_kind_no=?";
			pstmt = con.prepareStatement(selectOneCoupPub);
			
			// 4. 바인드 변수에 값 설정
			pstmt.setInt(1, coupKindNo);
			
			// 5. 쿼리문 수행 후 결과 얻기
			//		부모(Statement)의 executeXxx(sql)메소드는 절대로 사용하지 않는다 
			//		-> 실행해도 값이 안 나옴
			//		=> 매개변수 없는 executeXxx() 메소드를 사용해야 한다
			rs = pstmt.executeQuery();
			if(rs.next()) {
				flagPublish = true;
			} // end if
		} finally {
			// 6. 연결 끊기
			dbCon.dbClose(rs, pstmt, con);
		} // end finally 
		
		return flagPublish;
	} // selectOneCoupPub
	
} // class
