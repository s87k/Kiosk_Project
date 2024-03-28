package kiosk_prj.coupon.junit_test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import kiosk_prj.coupon.dao.CouponInfoViewDAO;
import kiosk_prj.coupon.vo.CouponAddedInfoVO;
import kiosk_prj.coupon.vo.CouponPubInfoVO;
import kiosk_prj.coupon.vo.StatusUse;

class TestCouponInfoViewDAO {

	CouponInfoViewDAO civDAO;
	
	@Test
	@BeforeEach
	void testGetInstance() {
		// given
		// when
		civDAO = CouponInfoViewDAO.getInstance();
		
		// then
		assertNotNull(civDAO);
	}

	@Disabled
	@Test
	void testSearchAllAddedCouponView() {
		// given
		List<CouponAddedInfoVO> listCaivVO = new ArrayList<CouponAddedInfoVO>(); 
		
		// when
		// then
		assertDoesNotThrow(()->civDAO.searchAllAddedCouponView());
		
		// when
		try {
			listCaivVO = civDAO.searchAllAddedCouponView();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// then
		assertNotNull(listCaivVO);
		if(listCaivVO != null) {
			for (int i = 0; i < listCaivVO.size(); i++) {
				System.out.println(listCaivVO.get(i));
			}
		}
		
	}
	
	@Test
	void testSearchPubCouponView() {
		// given
		List<CouponPubInfoVO> listCpiVO = new ArrayList<CouponPubInfoVO>();
		
		// when
		// then
		assertDoesNotThrow(()-> civDAO.selectPubCouponView());
		
		// when
		try {
//			listCpiVO = civDAO.searchPubCouponView();
//			listCpiVO = civDAO.searchPubCouponView(CouponInfoViewDAO.STATUS_EXPIRE);
			listCpiVO = civDAO.selectPubCouponView(StatusUse.ALL.getIntVal());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(listCpiVO);
		
		if(listCpiVO != null) {
			for (int i = 0; i < listCpiVO.size(); i++) {
				System.out.println(listCpiVO.get(i));
			}
		}
		
	}

}
