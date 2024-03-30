package kiosk_prj.coupon.vo;

import java.sql.Date;

public class CouponKindVO {
	private int coupKindNo;
	private String adminId;
	private String coupKindName;
	private int discount;
	private int expiresPeriod;
	private Date inputDate;
	private boolean flagPublishable;
	private Date deleteDate;
	private boolean flagDelete;
	private String img;
	
	public CouponKindVO() {
	}

	public CouponKindVO(int coupKindNo, String adminId, String coupKindName, int discount, int expiresPeriod,
			Date inputDate, boolean flagPublishable, Date deleteDate, boolean flagDelete, String img) {
		this.coupKindNo = coupKindNo;
		this.adminId = adminId;
		this.coupKindName = coupKindName;
		this.discount = discount;
		this.expiresPeriod = expiresPeriod;
		this.inputDate = inputDate;
		this.flagPublishable = flagPublishable;
		this.deleteDate = deleteDate;
		this.flagDelete = flagDelete;
		this.img = img;
	}
	
	public CouponKindVO(String adminId, String coupKindName, int discount, int expiresPeriod, boolean flagPublishable) {
		this.adminId = adminId;
		this.coupKindName = coupKindName;
		this.discount = discount;
		this.expiresPeriod = expiresPeriod;
		this.flagPublishable = flagPublishable;
	}
	public CouponKindVO(String adminId, String coupKindName, int discount, int expiresPeriod, boolean flagPublishable, String img) {
		this.adminId = adminId;
		this.coupKindName = coupKindName;
		this.discount = discount;
		this.expiresPeriod = expiresPeriod;
		this.flagPublishable = flagPublishable;
		this.img = img;
	}

	public int getCoupKindNo() {
		return coupKindNo;
	}

	public void setCoupKindNo(int coupKindNo) {
		this.coupKindNo = coupKindNo;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
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

	public int getExpiresPeriod() {
		return expiresPeriod;
	}

	public void setExpiresPeriod(int expiresPeriod) {
		this.expiresPeriod = expiresPeriod;
	}

	public Date getInputDate() {
		return inputDate;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}

	public boolean isFlagPublishable() {
		return flagPublishable;
	}

	public void setFlagPublishable(boolean flagPublishable) {
		this.flagPublishable = flagPublishable;
	}

	public Date getDeleteDate() {
		return deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}

	public boolean isFlagDelete() {
		return flagDelete;
	}

	public void setFlagDelete(boolean flagDelete) {
		this.flagDelete = flagDelete;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "CouponKindVO [coupKindNo=" + coupKindNo + ", adminId=" + adminId + ", coupKindName=" + coupKindName
				+ ", discount=" + discount + ", expiresPeriod=" + expiresPeriod + ", inputDate=" + inputDate
				+ ", flagPublishable=" + flagPublishable + ", deleteDate=" + deleteDate + ", flagDelete=" + flagDelete
				+ ", img=" + img + "]";
	}
		
}
