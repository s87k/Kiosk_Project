package kiosk_prj.coupon.junit_test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import kiosk_prj.coupon.controller.ConvertCouponRadix;
import kiosk_prj.coupon.vo.CouponPublishVO;

class TestConvertCouponRadix {

	ConvertCouponRadix ccr;
	
	@Test
	@BeforeEach
	void testGetInstance() {
		ccr = ConvertCouponRadix.getInstance();
	}

	@Test
	void testCouponPublishVOToRadix62() {
		System.out.println(ccr.CouponPublishVOToRadix62(new CouponPublishVO(10000, 2, 1)));
		System.out.println(ccr.CouponPublishVOToRadix62(new CouponPublishVO(50000, 1, 2)));
		System.out.println(ccr.CouponPublishVOToRadix62(new CouponPublishVO(100000, 1, 3)));
		
	}

	@Test
	void testRadix62ToCouponPublishVO() {
	}

}
