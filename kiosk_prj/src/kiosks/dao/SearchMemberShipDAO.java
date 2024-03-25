package kiosks.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kiosks.vo.SearchMemberShipVO;

public class SearchMemberShipDAO {

	private static SearchMemberShipDAO smsDAO;

	private SearchMemberShipDAO() {

	}// SearchMemberShipDAO

	public static SearchMemberShipDAO getInstance() {
		if (smsDAO == null) {
			smsDAO = new SearchMemberShipDAO();
		} // end if
		return smsDAO;
	}// getInstance

	/**
	 * 회원의 전화번호 가져오기
	 * 
	 * @param phoneNumber
	 * @return
	 * @throws SQLException
	 */
	public List<SearchMemberShipVO> selectPhoneNum(String phoneNumber) throws SQLException {
		List<SearchMemberShipVO> list = new ArrayList<SearchMemberShipVO>();

		DbConnection dbCon = DbConnection.getInstance();

		// 1. 드라이버 로딩
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 2. 커넥션 얻기
			String id = "kiosk";
			String pass = "4";
			con = dbCon.getConnection(id, pass);

			// 3. 쿼리문 생성객체 얻기
			String selectPhoneNum = 
					" select PHONE_NUMBER from MEMBERSHIP where PHONE_NUMBER = ? ";

			pstmt = con.prepareStatement(selectPhoneNum);
			
			// 4. 바인드 변수에 값넣기
			pstmt.setString(1, phoneNumber);

			// 5. 쿼리문 수행 후 결과 얻기
			SearchMemberShipVO smsVO = null;
			rs = pstmt.executeQuery();
			while (rs.next()) {
				smsVO = new SearchMemberShipVO(rs.getString("PHONE_NUMBER"));
				list.add(smsVO);
			} // end while
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		} // end finally
		return list;
	}// selectPhoneNum

	/**
	 * 회원 추가
	 * @param smsVO
	 * @throws SQLException
	 */
	public void insertMemberShip(SearchMemberShipVO smsVO) throws SQLException {
		DbConnection dbCon = DbConnection.getInstance();

		// 1. 드라이버 로딩
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			// 2. 커넥션 얻기
			String id = "kiosk";
			String pass = "4";
			con = dbCon.getConnection(id, pass);
			
			// 3. 쿼리문 생성객체 얻기
			String insertMember = 
					" insert into membership(PHONE_NUMBER,MEMBER_NAME, MEMBER_BIRTH) values(?,?,?) ";
			pstmt = con.prepareStatement(insertMember);
			
			// 4. 바인드변수에 값 설정
			pstmt.setString(1, smsVO.getPhoneNumber());
			pstmt.setString(2, smsVO.getMemberName());
			pstmt.setString(3, smsVO.getMemberBirth());
			
			// 5. 쿼리문 수행 후 결과 얻기
			pstmt.executeUpdate();
		} finally {
			// 6. 연결 끊기
			dbCon.dbClose(null, pstmt, con);
		} // end finally
	}//insertMemberShip

}
// class
