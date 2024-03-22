package kiosk_prj.coupon.vo;

public class CoupConditionPriceVO {
	private int conditionPriceNo;
	private int conditionTypeNo;
	private int conditionPrice;
	
	public CoupConditionPriceVO() {
	}
	
	public CoupConditionPriceVO(int conditionPriceNo, int conditionTypeNo, int conditionPrice) {
		this.conditionPriceNo = conditionPriceNo;
		this.conditionTypeNo = conditionTypeNo;
		this.conditionPrice = conditionPrice;
	}
	
	public CoupConditionPriceVO(int conditionTypeNo, int conditionPrice) {
		this.conditionTypeNo = conditionTypeNo;
		this.conditionPrice = conditionPrice;
	}
	
	public int getConditionPriceNo() {
		return conditionPriceNo;
	}
	public void setConditionPriceNo(int conditionPriceNo) {
		this.conditionPriceNo = conditionPriceNo;
	}
	public int getConditionTypeNo() {
		return conditionTypeNo;
	}
	public void setConditionTypeNo(int conditionTypeNo) {
		this.conditionTypeNo = conditionTypeNo;
	}
	public int getConditionPrice() {
		return conditionPrice;
	}
	public void setConditionPrice(int conditionPrice) {
		this.conditionPrice = conditionPrice;
	}

	@Override
	public String toString() {
		return "CoupConditionPriceVO [conditionPriceNo=" + conditionPriceNo + ", conditionTypeNo=" + conditionTypeNo
				+ ", conditionPrice=" + conditionPrice + "]";
	}
	
}
