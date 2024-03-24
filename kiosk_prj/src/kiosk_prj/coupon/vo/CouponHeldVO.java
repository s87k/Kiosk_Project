package kiosk_prj.coupon.vo;

import java.sql.Date;

public class CouponHeldVO {
	private String coupPubCode;
	private String phoneNumber;
	private Date publishDate;
	private String statusUse;
	private Date useCoupDate;
	private int conditionPrice;
	private int conditionTypeNo;
	private int coupKindNo;

	public CouponHeldVO() {
	}
	public CouponHeldVO(String coupPubCode, String phoneNumber, Date publishDate, String statusUse, Date useCoupDate,
			int conditionPrice, int conditionTypeNo, int coupKindNo) {
		this.coupPubCode = coupPubCode;
		this.phoneNumber = phoneNumber;
		this.publishDate = publishDate;
		this.statusUse = statusUse;
		this.useCoupDate = useCoupDate;
		this.conditionPrice = conditionPrice;
		this.conditionTypeNo = conditionTypeNo;
		this.coupKindNo = coupKindNo;
	}
	public CouponHeldVO(String phoneNumber, int conditionPrice, int conditionTypeNo, int coupKindNo) {
		this.phoneNumber = phoneNumber;
		this.conditionPrice = conditionPrice;
		this.conditionTypeNo = conditionTypeNo;
		this.coupKindNo = coupKindNo;
	}
	
	public String getCoupPubCode() {
		return coupPubCode;
	}
	public void setCoupPubCode(String coupPubCode) {
		this.coupPubCode = coupPubCode;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public String getStatusUse() {
		return statusUse;
	}
	public void setStatusUse(String statusUse) {
		this.statusUse = statusUse;
	}
	public Date getUseCoupDate() {
		return useCoupDate;
	}
	public void setUseCoupDate(Date useCoupDate) {
		this.useCoupDate = useCoupDate;
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
		return "CouponHeldVO [coupPubCode=" + coupPubCode + ", phoneNumber=" + phoneNumber + ", publishDate="
				+ publishDate + ", statusUse=" + statusUse + ", useCoupDate=" + useCoupDate + ", conditionPrice="
				+ conditionPrice + ", conditionTypeNo=" + conditionTypeNo + ", coupKindNo=" + coupKindNo + "]";
	}
}
