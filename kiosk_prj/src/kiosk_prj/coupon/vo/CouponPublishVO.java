package kiosk_prj.coupon.vo;

import java.sql.Date;

public class CouponPublishVO {
	private int conditionPrice;
	private int conditionTypeNo;
	private int coupKindNo;
	private boolean flagDisable;
	private Date disableDate;
	
	public CouponPublishVO() {
	}

	public CouponPublishVO(int conditionPrice, int conditionTypeNo, int coupKindNo) {
		this.conditionPrice = conditionPrice;
		this.conditionTypeNo = conditionTypeNo;
		this.coupKindNo = coupKindNo;
	}
	public CouponPublishVO(int conditionPrice, int conditionTypeNo, int coupKindNo, boolean flagDisable,
			Date disableDate) {
		this.conditionPrice = conditionPrice;
		this.conditionTypeNo = conditionTypeNo;
		this.coupKindNo = coupKindNo;
		this.flagDisable = flagDisable;
		this.disableDate = disableDate;
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

	public boolean isFlagDisable() {
		return flagDisable;
	}

	public void setFlagDisable(boolean flagDisable) {
		this.flagDisable = flagDisable;
	}

	public Date getDisableDate() {
		return disableDate;
	}

	public void setDisableDate(Date disableDate) {
		this.disableDate = disableDate;
	}
}
