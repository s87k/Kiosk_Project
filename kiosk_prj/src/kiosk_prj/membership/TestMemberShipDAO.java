package kiosk_prj.membership;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import kiosk_prj.membership.dao.MemberShipDAO;
import kiosk_prj.membership.vo.MemberShipVO;

class TestMemberShipDAO {

	@Test
	void testUpdateMember() throws SQLException {
		MemberShipVO msVO = new MemberShipVO("01112345678", "가나다라", "19081231", "신규");
		String phoneNumber= "01012345678";
		MemberShipDAO mDAO = MemberShipDAO.getInstance();
		int cnt = mDAO.updateMember(msVO, phoneNumber);
		assertEquals(cnt, 1);
	}

}
