package kiosk_prj.coupon.junit_test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import kiosk_prj.coupon.dao.CouponPublishDAO;
import kiosk_prj.coupon.vo.CouponKindVO;
import kiosk_prj.coupon.vo.CouponPublishVO;

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
		int coupKindNo = 22;
		int conditionPriceNo = 21;
		CouponPublishVO cpVO = new CouponPublishVO(coupKindNo, conditionPriceNo);
		
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
	
	@Test
	void testSelectOneCoupPub() throws SQLException {
		// given
		int coupKindNo = 1, conditionPriceNo = 1;
		CouponPublishVO cpVO = null;
		
		// when
		cpVO = cpDAO.selectOneCoupPub(coupKindNo, conditionPriceNo);
		
		// then 
		assertNotNull(cpVO);
		if(cpVO != null) {
			System.out.println(cpVO);
		}
	}

}
