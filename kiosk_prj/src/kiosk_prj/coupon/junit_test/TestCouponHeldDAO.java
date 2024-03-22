package kiosk_prj.coupon.junit_test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import kiosk_prj.coupon.dao.CouponHeldDAO;
import kiosk_prj.coupon.vo.CouponHeldVO;

class TestCouponHeldDAO {

	CouponHeldDAO chDAO;
	
	@BeforeEach
	@Test
	void testGetInstance() {
		chDAO = CouponHeldDAO.getInstance();
		assertNotNull(chDAO);
	}

	@Disabled
	@Test
	void testInsertCoupHeld() {
		// given
		CouponHeldVO chVO = new CouponHeldVO("01012345678", 1, 1);
		
		// when
		// then
		assertDoesNotThrow(()-> chDAO.insertCoupHeld(chVO));
	}
	
//	@Disabled
	@Test
	void testUpdateCoupHeld() throws SQLException {
		// given
		CouponHeldVO chVO = new CouponHeldVO();
		chVO.setStatusUse("1");
		chVO.setCoupPubCode("2               ");
		int cnt = 0;
		
		// when
		cnt = chDAO.updateCoupHeld(chVO);
		
		// then
		assertEquals(1, cnt);
	}
	
	@Disabled
	@Test
	void testSelectAllCoupHeld() throws SQLException {
		// given
			
		// when
		List<CouponHeldVO> list = chDAO.selectAllCoupHeld();
		
		// then
		assertNotNull(list);
		if(list != null) {
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
		}
	}
	
	@Disabled
	@Test
	void testSelectOneCoupHeld() throws SQLException {
		// given
		CouponHeldVO chVO = null;
		String coupPubCode = "2               ";
		
		// when
		chVO = chDAO.selectOneCoupHeld(coupPubCode);
		
		// then
		assertNotNull(chVO);
		if(chVO != null) {
			System.out.println(chVO);
		}
	}
	
	@Test
	void TestSelectUserCoupHeld() throws SQLException {
		// given
		List<CouponHeldVO> list = new ArrayList<CouponHeldVO>();
		String phoneNumber = "01012345678";
		
		// when
		list=  chDAO.selectUserCoupHeld(phoneNumber);
		
		// then
		assertNotNull(list);
		if(list != null) {
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
		}
	}

}
