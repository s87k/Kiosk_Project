package kiosk_prj.membership.vo;

public class MemberShipCouponVO {
	private String couponName, publishDate, useDate, useStatus;
	private int disCount, expiresPeriod;

	public MemberShipCouponVO(String couponName, int disCount, String publishDate, int expiresPeriod, String useDate,
			String useStauts) {
		this.couponName = couponName;
		this.disCount = disCount;
		this.publishDate = publishDate;
		this.useDate = useDate;
		this.expiresPeriod = expiresPeriod;
		this.useStatus = useStauts;
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

	public String getUseDate() {
		return useDate;
	}

	public void setUseDate(String useDate) {
		this.useDate = useDate;
	}
	
	public int getExpiresPeriod() {
		return expiresPeriod;
	}

	public void setExpiresPeriod(int expiresPeriod) {
		this.expiresPeriod = expiresPeriod;
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
		return "MemberShipCouponVO [couponName=" + couponName + ", publishDate=" + publishDate + ", useDate=" + useDate
				+ ", useStatus=" + useStatus + ", disCount=" + disCount + ", expiresPeriod=" + expiresPeriod + "]";
	}


}
