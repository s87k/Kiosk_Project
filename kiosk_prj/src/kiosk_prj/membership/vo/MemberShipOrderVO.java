package kiosk_prj.membership.vo;

public class MemberShipOrderVO {
	private String watingNum,orderTime, menuName, phoneNumber;
	private int amount;
	
	public MemberShipOrderVO(String watingNum, String orderTime, String menuName, int amount) {
		this.watingNum = watingNum;
		this.orderTime = orderTime;
		this.menuName = menuName;
		this.amount = amount;
	}

	public String getWatingNum() {
		return watingNum;
	}

	public void setWatingNum(String watingNum) {
		this.watingNum = watingNum;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "MemberShipOrderVO [watingNum ="+ watingNum + ", orderTime=" + orderTime + ", menuName=" + menuName + ", amount=" + amount + "]";
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
