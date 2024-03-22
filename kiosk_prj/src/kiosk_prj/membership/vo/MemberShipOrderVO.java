package kiosk_prj.membership.vo;

public class MemberShipOrderVO {
	private String orderTime, menuName, phoneNumber;
	private int amount;
	
	public MemberShipOrderVO(String orderTime, String menuName, int amount) {
		this.orderTime = orderTime;
		this.menuName = menuName;
		this.amount = amount;
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
		return "MemberShipOrderVO [orderTime=" + orderTime + ", menuName=" + menuName + ", amount=" + amount + "]";
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
