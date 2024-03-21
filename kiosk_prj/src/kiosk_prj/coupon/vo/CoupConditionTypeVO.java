package kiosk_prj.coupon.vo;

public class CoupConditionTypeVO {
	private int conditionTypeNo;
	private String conditionTypeName;
	
	public CoupConditionTypeVO() {
	}
	
	public CoupConditionTypeVO(int conditionTypeNo, String conditionTypeName) {
		this.conditionTypeNo = conditionTypeNo;
		this.conditionTypeName = conditionTypeName;
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

	@Override
	public String toString() {
		return "CoupConditionTypeVO [conditionTypeNo=" + conditionTypeNo + ", conditionTypeName=" + conditionTypeName
				+ "]";
	}
	
}
