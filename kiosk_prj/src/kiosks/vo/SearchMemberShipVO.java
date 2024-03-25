package kiosks.vo;

import java.sql.Date;

public class SearchMemberShipVO {

	private String phoneNumber;
	private String memberName;
	private String memberBirth;

	public SearchMemberShipVO() {

	}// SearchMemberShipVO

	public SearchMemberShipVO(String phoneNumber, String memberName, String memberBirth) {
		super();
		this.phoneNumber = phoneNumber;
		this.memberName = memberName;
		this.memberBirth = memberBirth;
	}
	

	public SearchMemberShipVO(String phoneNumber) {
		super();
		this.phoneNumber = phoneNumber;
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

	public String getMemberBirth() {
		return memberBirth;
	}

	public void setMemberBirth(String memberBirth) {
		this.memberBirth = memberBirth;
	}

	@Override
	public String toString() {
		return "SearchMemberShipVO [phoneNumber=" + phoneNumber + ", memberName=" + memberName + ", memberBirth="
				+ memberBirth + "]";
	}

	
	
	
	

}//class
