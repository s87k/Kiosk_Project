package kiosk_prj.coupon.vo;

public class CouponAutoPubVO {
	private String phoneNumber;
	private String coupKindName;
	private int discount;
	private int expiresPeriod;
	private String conditionMsg;
	private int conditionPrice;
	private int conditionTypeNo;
	private int coupKindNo;
	
	public CouponAutoPubVO() {
	}
	public CouponAutoPubVO(int conditionPrice, int conditionTypeNo, int coupKindNo) {
		this.conditionPrice = conditionPrice;
		this.conditionTypeNo = conditionTypeNo;
		this.coupKindNo = coupKindNo;
	}
	public CouponAutoPubVO(String phoneNumber, int conditionPrice, int conditionTypeNo, int coupKindNo) {
		this.phoneNumber = phoneNumber;
		this.conditionPrice = conditionPrice;
		this.conditionTypeNo = conditionTypeNo;
		this.coupKindNo = coupKindNo;
	}
	public CouponAutoPubVO(String phoneNumber, String coupKindName, int discount, int expiresPeriod,
			String conditionMsg, int conditionPrice, int conditionTypeNo, int coupKindNo) {
		this.phoneNumber = phoneNumber;
		this.coupKindName = coupKindName;
		this.discount = discount;
		this.expiresPeriod = expiresPeriod;
		this.conditionMsg = conditionMsg;
		this.conditionPrice = conditionPrice;
		this.conditionTypeNo = conditionTypeNo;
		this.coupKindNo = coupKindNo;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getCoupKindName() {
		return coupKindName;
	}
	public void setCoupKindName(String coupKindName) {
		this.coupKindName = coupKindName;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public int getExpiresPeriod() {
		return expiresPeriod;
	}
	public void setExpiresPeriod(int expiresPeriod) {
		this.expiresPeriod = expiresPeriod;
	}
	public String getConditionMsg() {
		return conditionMsg;
	}
	public void setConditionMsg(String conditionMsg) {
		this.conditionMsg = conditionMsg;
	}
	public int getConditionPrice() {
		return conditionPrice;
	}
	public void setConditionPrice(int conditionPrice) {
		this.conditionPrice = conditionPrice;
	}
	public int getConditionTypeNo() {
		return conditionTypeNo;
	}
	public void setConditionTypeNo(int conditionTypeNo) {
		this.conditionTypeNo = conditionTypeNo;
	}
	public int getCoupKindNo() {
		return coupKindNo;
	}
	public void setCoupKindNo(int coupKindNo) {
		this.coupKindNo = coupKindNo;
	}
	@Override
	public String toString() {
		return "CouponAutoPubVO [phoneNumber=" + phoneNumber + ", coupKindName=" + coupKindName + ", discount="
				+ discount + ", expiresPeriod=" + expiresPeriod + ", conditionMsg=" + conditionMsg + ", conditionPrice="
				+ conditionPrice + ", conditionTypeNo=" + conditionTypeNo + ", coupKindNo=" + coupKindNo + "]";
	}
} // class
