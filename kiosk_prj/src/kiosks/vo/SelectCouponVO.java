package kiosks.vo;

public class SelectCouponVO {

	private String coupPubCode, couponName, publishDate, expireDate, useDate, useStatus;
	private int disCount;
	
	public SelectCouponVO() {
		
	}
	
	public SelectCouponVO(String coupPubCode, String couponName, String publishDate, String expireDate, String useDate,
			String useStatus, int disCount) {
		this.coupPubCode = coupPubCode;
		this.couponName = couponName;
		this.publishDate = publishDate;
		this.expireDate = expireDate;
		this.useDate = useDate;
		this.useStatus = useStatus;
		this.disCount = disCount;
	}

	public SelectCouponVO(String couponName, String publishDate, String expireDate, String useDate, String useStatus,
			int disCount) {
		super();
		this.couponName = couponName;
		this.publishDate = publishDate;
		this.expireDate = expireDate;
		this.useDate = useDate;
		this.useStatus = useStatus;
		this.disCount = disCount;
	}
	
	public SelectCouponVO(String couponName, String publishDate, String expireDate) {
		super();
		this.couponName = couponName;
		this.publishDate = publishDate;
		this.expireDate = expireDate;
	}
	public SelectCouponVO(String coupPubCode, int disCount, String couponName, String publishDate, String expireDate) {
		this.coupPubCode = coupPubCode;
		this.couponName = couponName;
		this.publishDate = publishDate;
		this.expireDate = expireDate;
		this.disCount = disCount;
	}
	
	public SelectCouponVO(String useDate, String useStatus) {
		super();
		this.useDate = useDate;
		this.useStatus = useStatus;
	}

	public SelectCouponVO(int disCount) {
		super();
		this.disCount = disCount;
	}

	public String getCoupPubCode() {
		return coupPubCode;
	}

	public void setCoupPubCode(String coupPubCode) {
		this.coupPubCode = coupPubCode;
	}

	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	public String getUseDate() {
		return useDate;
	}
	public void setUseDate(String useDate) {
		this.useDate = useDate;
	}
	public String getUseStatus() {
		return useStatus;
	}
	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}
	public int getDisCount() {
		return disCount;
	}
	public void setDisCount(int disCount) {
		this.disCount = disCount;
	}
	@Override
	public String toString() {
		return "SelectCouponVO [couponName=" + couponName + ", publishDate=" + publishDate + ", expireDate="
				+ expireDate + ", useDate=" + useDate + ", useStatus=" + useStatus + ", disCount=" + disCount + "]";
	}
	
	
}
