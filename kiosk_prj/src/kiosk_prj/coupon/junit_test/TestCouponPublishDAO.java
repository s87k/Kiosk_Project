package kiosk_prj.coupon.junit_test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import kiosk_prj.coupon.dao.CouponPublishDAO;
import kiosk_prj.coupon.vo.CoupConditionPriceVO;
import kiosk_prj.coupon.vo.CouponAutoPubVO;
import kiosk_prj.coupon.vo.CouponKindVO;
import kiosk_prj.coupon.vo.CouponPublishVO;
import kiosk_prj.coupon.vo.ResultVO;

class TestCouponPublishDAO {

	// given
	CouponPublishDAO cpDAO;
	
	@BeforeEach
	@Test
	void testGetInstance() {
		// given
		
		// when
		cpDAO = CouponPublishDAO.getInstance();
		
		// then
		assertNotNull(cpDAO);
	}

	@Disabled
	@Test
	void testInsertCoupPub() throws SQLException {
		// given
		int conditionPrice = 12345;
		int conditionTypeNo = 1;
		int coupKindNo = 5;
		CouponPublishVO cpVO = new CouponPublishVO(conditionPrice, conditionTypeNo, coupKindNo);
		
		// when
		// then
		assertDoesNotThrow(()->cpDAO.insertCoupPub(cpVO));
	}
	
	@Disabled
	@Test
	void testSelectAllCoupPub() throws SQLException {
		// given
		List<CouponPublishVO> list = null;
		
		// when 
		list = cpDAO.selectAllCoupPub();
		
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
	void testSelectOneCoupPub() throws SQLException {
		// given
		int conditionPrice = 12345;
		int conditionTypeNo = 1;
		int coupKindNo = 5;
		CouponPublishVO cpVO = null;
		
		// when
		cpVO = cpDAO.selectOneCoupPub(conditionPrice, conditionTypeNo, coupKindNo);
		
		// then 
		assertNotNull(cpVO);
		if(cpVO != null) {
			System.out.println(cpVO);
		}
	}
	
	@Disabled
	@Test
	void TesIinsertCoupPubProc() throws SQLException {
		// given
		CoupConditionPriceVO ccpVO = new CoupConditionPriceVO(1, 3210);
		CouponKindVO ckVO = new CouponKindVO();
		ckVO.setCoupKindNo(26);
		ResultVO rVO = null;
		
		// when
		rVO = cpDAO.insertCoupPubProc(ccpVO, ckVO);
		
		// then
		assertNotNull(rVO);
		if(rVO != null) {
			System.out.println(rVO);
		}
	}

	@Disabled
	@Test
	void TestIsCoupKindPublished() throws SQLException{
		// given
		boolean flagPublish = false;
		
		
		// when
		flagPublish = cpDAO.isCoupKindPublished(1);
		
		// then
		assertTrue(flagPublish);
		
		// when
		flagPublish = cpDAO.isCoupKindPublished(8);
		
		// then
		assertFalse(flagPublish);
	}

	@Test
	void TestSelectShouldPublishCoup() throws SQLException {
		// given
		List<CouponAutoPubVO> list = new ArrayList<CouponAutoPubVO>();
		
		// when
		list = cpDAO.selectShouldPublishCoup("01056567878", 15000);
		
		// then
		assertNotNull(list);
		if(list != null) {
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
		}
	}
}
