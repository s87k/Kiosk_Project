package kiosk_prj.coupon.vo;

public class CouponPublishVO {
	private int conditionPrice;
	private int conditionTypeNo;
	private int coupKindNo;
	
	public CouponPublishVO() {
	}

	public CouponPublishVO(int conditionPrice, int conditionTypeNo, int coupKindNo) {
		this.conditionPrice = conditionPrice;
		this.conditionTypeNo = conditionTypeNo;
		this.coupKindNo = coupKindNo;
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
		return "CouponPublishVO [conditionPrice=" + conditionPrice + ", conditionTypeNo=" + conditionTypeNo
				+ ", coupKindNo=" + coupKindNo + "]";
	}
	
}
