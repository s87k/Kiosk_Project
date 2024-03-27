package kiosk_prj.shopOpen;

public class ShopCloseVO {
	
	private String menuType, menuName;
	private int amount;
	
	/**
	 * String menuType, String menuName, int amount<br>
	 * 오픈일, 메뉴종류, 메뉴이름, 샷 수, 가격
	 * 
	 * @param menuType
	 * @param menuName
	 * @param amount
	 */
	public ShopCloseVO(String menuType, String menuName, int amount) {
		this.menuType = menuType;
		this.menuName = menuName;
		this.amount = amount;
	}

	public String getMenuType() {
		return menuType;
	}

	public String getMenuName() {
		return menuName;
	}

	public int getAmount() {
		return amount;
	}

	@Override
	public String toString() {
		return "ShopCloseVO [menuType=" + menuType + ", menuName=" + menuName + ", amount=" + amount + "]";
	}

}//class
