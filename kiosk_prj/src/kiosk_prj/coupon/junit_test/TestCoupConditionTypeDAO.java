package kiosk_prj.coupon.junit_test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kiosk_prj.coupon.dao.CoupConditionTypeDAO;
import kiosk_prj.coupon.vo.CoupConditionTypeVO;

class TestCoupConditionTypeDAO {
	
	// given
	CoupConditionTypeDAO cctDAO;
		
	@BeforeEach
	@Test
	void testGetInstance() {
		// given
		
		// when
		cctDAO = CoupConditionTypeDAO.getInstance();
		
		// then
		assertNotNull(cctDAO);
	}

	@Disabled
	@DisplayName("모든 발급 조건명 조회")
	@Test
	void testSelectAllCoupConditionType() throws SQLException {
		
		// given
		
		// when
		List<CoupConditionTypeVO> list = cctDAO.selectAllCoupConditionType();
		
		// then
		assertNotNull(list);
		if(list != null) {
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
		}
	}
	
	@DisplayName("발급 조건명 하나 조회")
	@Test
	void testSelectOne() throws SQLException {
		// given
		CoupConditionTypeVO cctVO = null;
		int conditionTypeNo = 1;
		
		
		// when 
		cctVO = cctDAO.selectOneCoupConditionType(conditionTypeNo);
		
		// then
		assertNotNull(cctVO);
		if(cctVO != null) {
			System.out.println(cctVO);
		}
	}

}
