package kiosk_prj.vo.memberShipVO;

public class MemberShipCouponVO {
	private String couponName, publishDate, useDate, expireDate, useStatus;
	private int disCount;

	public MemberShipCouponVO(String couponName, int disCount, String publishDate, String useDate, String expireDate,
			String useStauts) {
		this.couponName = couponName;
		this.disCount = disCount;
		this.publishDate = publishDate;
		this.useDate = useDate;
		this.expireDate = expireDate;
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

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
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
				+ ", expireDate=" + expireDate + ", useStatus=" + useStatus + ", disCount=" + disCount + "]";
	}

}
