package kiosk_prj.adminMain;

public class currentOrderVO {
	private String menuType, menuName, openDate;
	private int amount;
	
	public currentOrderVO(String menuType, String menuName, String openDate, int amount) {
		this.menuType = menuType;
		this.menuName = menuName;
		this.openDate = openDate;
		this.amount = amount;
		
	}

	public currentOrderVO(String menuType, String menuName, int amount) {
		this.menuType = menuType;
		this.menuName = menuName;
		this.amount = amount;
	}

	public currentOrderVO(int amount) {
		this.amount = amount;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "currentOrderVO [menuType=" + menuType + ", menuName=" + menuName + ", openDate=" + openDate
				+ ", amount=" + amount + "]";
	}
	
	
}
