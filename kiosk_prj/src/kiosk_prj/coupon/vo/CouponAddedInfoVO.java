package kiosk_prj.coupon.vo;

public class CouponAddedInfoVO {
	private int coupKindNo;
	private String coupKindName;
	private int discount;
	private boolean flagPublishable;
	private int expiresPeriod;
	private int conditionTypeNo;
	private String conditionTypeName;
	private int conditionPriceNo;
	private int conditionPrice;
	
	public CouponAddedInfoVO() {
	}
	
	public CouponAddedInfoVO(int coupKindNo, String coupKindName, int discount, boolean flagPublishable,
			int expiresPeriod, int conditionTypeNo, String conditionTypeName, int conditionPriceNo,
			int conditionPrice) {
		this.coupKindNo = coupKindNo;
		this.coupKindName = coupKindName;
		this.discount = discount;
		this.flagPublishable = flagPublishable;
		this.expiresPeriod = expiresPeriod;
		this.conditionTypeNo = conditionTypeNo;
		this.conditionTypeName = conditionTypeName;
		this.conditionPriceNo = conditionPriceNo;
		this.conditionPrice = conditionPrice;
	}
	
	public int getCoupKindNo() {
		return coupKindNo;
	}
	public void setCoupKindNo(int coupKindNo) {
		this.coupKindNo = coupKindNo;
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
	public boolean isFlagPublishable() {
		return flagPublishable;
	}
	public void setFlagPublishable(boolean flagPublishable) {
		this.flagPublishable = flagPublishable;
	}
	public int getExpiresPeriod() {
		return expiresPeriod;
	}
	public void setExpiresPeriod(int expiresPeriod) {
		this.expiresPeriod = expiresPeriod;
	}
	public int getConditionTypeNo() {
		return conditionTypeNo;
	}
	public void setConditionTypeNo(int conditionTypeNo) {
		this.conditionTypeNo = conditionTypeNo;
	}
	public String getConditionTypeName() {
		return conditionTypeName;
	}
	public void setConditionTypeName(String conditionTypeName) {
		this.conditionTypeName = conditionTypeName;
	}
	public int getConditionPriceNo() {
		return conditionPriceNo;
	}
	public void setConditionPriceNo(int conditionPriceNo) {
		this.conditionPriceNo = conditionPriceNo;
	}
	public int getConditionPrice() {
		return conditionPrice;
	}
	public void setConditionPrice(int conditionPrice) {
		this.conditionPrice = conditionPrice;
	}
	
}
