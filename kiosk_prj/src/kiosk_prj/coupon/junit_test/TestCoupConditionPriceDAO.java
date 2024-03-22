package kiosk_prj.coupon.junit_test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import kiosk_prj.coupon.dao.CoupConditionPriceDAO;
import kiosk_prj.coupon.vo.CoupConditionPriceVO;

class TestCoupConditionPriceDAO {
	
	// given
	CoupConditionPriceDAO ccpDAO;

	@BeforeEach
	@Test
	void testGetInstance() {
		// given
		
		// when 
		ccpDAO = CoupConditionPriceDAO.getInstance();
		
		// then
		assertNotNull(ccpDAO);
	}

	@Disabled
	@Test
	void testInsertCoupConditionPrice() throws SQLException {
		// given
		CoupConditionPriceVO ccpVO = new CoupConditionPriceVO(1, 5000);
		
		// when
		// then
		assertDoesNotThrow(()->ccpDAO.insertCoupConditionPrice(ccpVO));
	}
	
	@Disabled
	@Test
	void testUpdateCoupConditionPrice() throws SQLException {
		// given
		CoupConditionPriceVO ccpVO = new CoupConditionPriceVO(21, 2, 12345);
		int cnt = 0;
		
		// when
		cnt = ccpDAO.updateCoupConditionPrice(ccpVO);
		
		// then
		assertEquals(cnt, 1);
	}
	
	@Test
	void testSelectOneCoupConditionPrice() throws SQLException {
		// given
		int CONDITION_PRICE_NO = 21;
		CoupConditionPriceVO ccpVO = null;
		
		// when 
		ccpVO = ccpDAO.selectOneCoupConditionPrice(CONDITION_PRICE_NO);
		
		// then
		assertNotNull(ccpVO);
		if(ccpVO != null) {
			System.out.println(ccpVO);
		}
	}

}
