package kiosk_prj.membership.vo;



public class MemberShipVO {
	String phoneNum, memberName, memberBirth, grade, holdingCoupon, exPhoneNum;
	boolean deleteFlag = true;
	public MemberShipVO() {
		
	}
	
	public MemberShipVO(String phoneNum, String memberName, String memberBirth, String grade) {
		this.phoneNum = phoneNum;
		this.memberName = memberName;
		this.memberBirth = memberBirth;
		this.grade = grade;
	}
	
	public MemberShipVO(String phoneNum, String memberName, String memberBirth, String grade, String exPhoneNum) {
		this.phoneNum = phoneNum;
		this.memberName = memberName;
		this.memberBirth = memberBirth;
		this.grade = grade;
		this.exPhoneNum = exPhoneNum;
	}

	public MemberShipVO(String phoneNum, String memberName, String memberBirth, String grade, boolean deleteFlag) {
		this.phoneNum = phoneNum;
		this.memberName = memberName;
		this.memberBirth = memberBirth;
		this.grade = grade;
		this.deleteFlag = deleteFlag;
	}


	public boolean isDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getExPhoneNum() {
		return exPhoneNum;
	}

	public void setExPhoneNum(String exPhoneNum) {
		this.exPhoneNum = exPhoneNum;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberBirth() {
		return memberBirth;
	}

	public void setMemberBirth(String memberBirth) {
		this.memberBirth = memberBirth;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getHoldingCoupon() {
		return holdingCoupon;
	}

	public void setHoldingCoupon(String holdingCoupon) {
		this.holdingCoupon = holdingCoupon;
	}

	@Override
	public String toString() {
		return "MemberShipVO [phoneNum=" + phoneNum + ", memberName=" + memberName + ", memberBirth=" + memberBirth
				+ ", grade=" + grade + ", holdingCoupon=" + holdingCoupon + ", exPhoneNum=" + exPhoneNum + "]";
	}

}
