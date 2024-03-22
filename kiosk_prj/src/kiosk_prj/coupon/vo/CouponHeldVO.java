package kiosk_prj.coupon.vo;

import java.sql.Date;

public class CouponHeldVO {
	private String coupPubCode;
	private String phoneNumber;
	private Date publishDate;
	private String statusUse;
	private Date useCoupDate;
	private int coupKindNo;
	private int conditionPriceNo;
	
	public CouponHeldVO() {
	}
	
	public CouponHeldVO(String coupPubCode, String phoneNumber, Date publishDate, String statusUse, Date useCoupDate,
			int coupKindNo, int conditionPriceNo) {
		this.coupPubCode = coupPubCode;
		this.phoneNumber = phoneNumber;
		this.publishDate = publishDate;
		this.statusUse = statusUse;
		this.useCoupDate = useCoupDate;
		this.coupKindNo = coupKindNo;
		this.conditionPriceNo = conditionPriceNo;
	}
	
	public CouponHeldVO(String phoneNumber, int coupKindNo, int conditionPriceNo) {
		this.phoneNumber = phoneNumber;
		this.coupKindNo = coupKindNo;
		this.conditionPriceNo = conditionPriceNo;
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
		return "CouponHeldVO [coupPubCode=" + coupPubCode + ", phoneNumber=" + phoneNumber + ", publishDate="
				+ publishDate + ", statusUse=" + statusUse + ", useCoupDate=" + useCoupDate + ", coupKindNo="
				+ coupKindNo + ", conditionPriceNo=" + conditionPriceNo + "]";
	}
	
}
