package kiosk_prj.coupon.controller;

import kiosk_prj.coupon.vo.CouponPublishVO;
import static java.lang.Math.pow;
import static java.lang.String.valueOf;

public class ConvertCouponRadix {
	
	private static ConvertCouponRadix cc62r;
	
	private ConvertCouponRadix() {
	}
	
	public static ConvertCouponRadix getInstance() {
		if(cc62r == null) {
			cc62r = new ConvertCouponRadix();
		} // end if
		return cc62r;
	} // ConvertCoup62Radix
	
	public String CouponPublishVOToRadix62(CouponPublishVO caiVO) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(lpad(decimalToRadix62(caiVO.getConditionPrice()), 5, "0"));
		sb.append(lpad(decimalToRadix62(caiVO.getConditionTypeNo()), 2, "0"));
		sb.append(lpad(decimalToRadix62(caiVO.getCoupKindNo()), 3, "0"));
		
		return sb.toString();
	} // CouponPublishVO2Radix62
	
	public CouponPublishVO Radix62ToCouponPublishVO(String couponCode) {
		CouponPublishVO caiVO = new CouponPublishVO();
		
		caiVO.setConditionPrice((int)convertRadix62ToDecimal(couponCode.substring(0, 5)));
		caiVO.setConditionTypeNo((int)convertRadix62ToDecimal(couponCode.substring(5, 7)));
		caiVO.setCoupKindNo((int)convertRadix62ToDecimal(couponCode.substring(7, 10)));
		
		return caiVO;
	} // CouponPublishVO
	
	private String lpad(String str, int digit, String pad) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < digit - str.length(); i++) {
			sb.append(pad);
		} // end for
		sb.append(str);
		
		return sb.toString();
	} // lpad
	
	private String decimalToRadix62(int decimal) {
		String radix62 = "";
		
		int quot = decimal / 62;
		int remain = decimal % 62;
		if(quot != 0) {
			radix62 = decimalToRadix62(quot).concat(valueOf(decimalToR62(remain)));
		} else {
			radix62 = valueOf(decimalToR62(remain));
		} // end else
		return radix62;
	} // decimalToRadix62
	
	private long convertRadix62ToDecimal(String radix62) {
		long decimal = 0;
		int length = radix62.length();
		for(int i = 0; i < length; i++) {
			decimal += pow(62, i) *	r62ToDecimal(radix62.charAt(length - i - 1));
		} // end for
		
		return decimal;
	} // ConvertRadix62ToDecimal
	
	private int r62ToDecimal(char r62) {
		int decimal = 0;
		if(r62 > 96) {
			decimal = r62 - 87;
		} else if (r62 > 64 && r62 < 91) {
			decimal = r62 - 29;
		} else {
			decimal = r62 - 48;
		} // end else
		
		return decimal;
	} // r62ToDecimal
	
	private char decimalToR62(int decimal) {
		char r62 = 48;
		if(decimal > 35 && decimal < 62) {
			r62 = (char)(decimal + 29);
		} else if (decimal > 9 && decimal < 36) {
			r62 = (char)(decimal + 87);
		} else {
			r62 = (char)(decimal + 48);
		} // end else
		return r62;
	} // decimalToR62
	
} // class
