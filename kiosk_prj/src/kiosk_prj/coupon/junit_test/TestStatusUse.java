package kiosk_prj.coupon.junit_test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import kiosk_prj.coupon.vo.StatusUse;

class TestStatusUse {

	@Test
	void test() {
		System.out.println(StatusUse.USABLE.getName());
		System.out.println(StatusUse.USABLE.getIntVal());
		System.out.println(StatusUse.USABLE.getStrVal());
		
	}

}
