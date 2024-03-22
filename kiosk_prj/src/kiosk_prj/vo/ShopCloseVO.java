package kiosk_prj.vo;

public class ShopCloseVO {
	
	private String menuType, menuName, amount;
	private int count;
	
	/**
	 * String openDate, String menuType, String menuName, int count, String amount<br>
	 * 오픈일, 메뉴종류, 메뉴이름, 잔 수, 가격
	 * 
	 * @param menuType
	 * @param menuName
	 * @param count
	 * @param amount
	 */
	public ShopCloseVO(String menuType, String menuName, int count, String amount) {
		this.menuType = menuType;
		this.menuName = menuName;
		this.count = count;
		this.amount = amount;
	}

	public String getMenuType() {
		return menuType;
	}

	public String getMenuName() {
		return menuName;
	}

	public String getAmount() {
		return amount;
	}

	public int getCount() {
		return count;
	}

}//class
