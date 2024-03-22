package kiosk_prj.coupon.vo;

public class CouponPublishVO {
	private int coupKindNo;
	private int conditionPriceNo;
	
	public CouponPublishVO() {
	}
	
	public CouponPublishVO(int coupKindNo, int conditionPriceNo) {
		super();
		this.coupKindNo = coupKindNo;
		this.conditionPriceNo = conditionPriceNo;
	}
	
	public int getCoupKindNo() {
		return coupKindNo;
	}
	public void setCoupKindNo(int coupKindNo) {
		this.coupKindNo = coupKindNo;
	}
	public int getConditionPriceNo() {
		return conditionPriceNo;
	}
	public void setConditionPriceNo(int conditionPriceNo) {
		this.conditionPriceNo = conditionPriceNo;
	}

	@Override
	public String toString() {
		return "CouponPublishVO [coupKindNo=" + coupKindNo + ", conditionPriceNo=" + conditionPriceNo + "]";
	}
	
}
