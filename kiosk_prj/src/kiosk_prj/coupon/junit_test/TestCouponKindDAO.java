package kiosk_prj.coupon.junit_test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import kiosk_prj.coupon.dao.CouponKindDAO;
import kiosk_prj.coupon.vo.CouponKindVO;

class TestCouponKindDAO {
	
	CouponKindDAO ckDAO;
	
	@BeforeEach
	void getInstance() {
		ckDAO = CouponKindDAO.getInstance();
	}

	@Disabled
	@Test
	void testGetInstance() {
	}
	
	@Disabled
	@Test
	void testUpdate() throws SQLException {
		// given
		CouponKindVO ckVO = new CouponKindVO("admin", "테스트 쿠폰 (수정됨2)", 110, 4, true);
		ckVO.setCoupKindNo(22);
		
		// when
		int cnt = ckDAO.updateCoupKind(ckVO);
		System.out.println(cnt);
		
		// then
		assertEquals(cnt, 1);
	}

	@Disabled
	@Test
	void testInsertCoupKind() {
		// given
		CouponKindVO ckVO = new CouponKindVO("admin", "테스트 쿠폰", 1100, 2, true);
		
		// when
		// then
		assertDoesNotThrow(()-> ckDAO.insertCoupKind(ckVO));	
		
	}
	
	@Disabled
	@Test
	void testDelete() throws SQLException {
		// given
		int coupKindNo = 23;
		
		// when
		int cnt = ckDAO.deleteCoupKind(coupKindNo);
		System.out.println(cnt);
		
		// then
		assertEquals(cnt, 1);
		
	}
	
	@Disabled
	@Test
	void testSelectOne() throws SQLException {
		// given
		int coupKindNo = 22;
		
		// when
		CouponKindVO ckVO = ckDAO.selectOneCoupKind(coupKindNo);

		// then
		System.out.println(ckVO);
		assertNotNull(ckVO);
	}
	
//	@Disabled
	@Test
	void testSelectAll() throws SQLException {
		// given
		
		// when
		List<CouponKindVO> list = ckDAO.selectAllCoupKind();
		
		// then
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		assertNotNull(list);
	}
	
	@Disabled
	@Test
	void testSelectPublishable() throws SQLException {
		// given
		boolean flagPublishable = false;
		
		// when
		List<CouponKindVO> list = ckDAO.selectFlagPublishCoupKind(flagPublishable);
		
		// then
		for(int i = 0; i < list.size(); i++) {
			assertEquals(list.get(i).isFlagPublishable(), flagPublishable);
			System.out.println(list.get(i));
		}
		assertNotNull(list);
	}
}
