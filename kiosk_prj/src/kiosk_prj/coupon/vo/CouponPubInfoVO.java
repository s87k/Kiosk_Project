package kiosk_prj.coupon.vo;

import java.sql.Date;

public class CouponPubInfoVO {
	private String coupKindName;
	private int discount;
	private String phoneNumber;
	private String memberName;
	private String statusUse;
	private Date publishDate;
	private Date useCoupDate;
	private Date expireDate;
	
	public CouponPubInfoVO() {
	}
	
	public CouponPubInfoVO(String coupKindName, int discount, String phoneNumber, String memberName, String statusUse,
			Date publishDate, Date useCoupDate, Date expireDate) {
		this.coupKindName = coupKindName;
		this.discount = discount;
		this.phoneNumber = phoneNumber;
		this.memberName = memberName;
		this.statusUse = statusUse;
		this.publishDate = publishDate;
		this.useCoupDate = useCoupDate;
		this.expireDate = expireDate;
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
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getStatusUse() {
		return statusUse;
	}
	public void setStatusUse(String statusUse) {
		this.statusUse = statusUse;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public Date getUseCoupDate() {
		return useCoupDate;
	}
	public void setUseCoupDate(Date useCoupDate) {
		this.useCoupDate = useCoupDate;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	
}
