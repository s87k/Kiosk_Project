package kiosk_prj.settlement;

public class SettlementPeriodVO {
	
	private String menuName;
	private int menuPrice;
	
	public SettlementPeriodVO(String menuName, int menuPrice) {
		super();
		this.menuName = menuName;
		this.menuPrice = menuPrice;
	}

	public String getMenuName() {
		return menuName;
	}

	public int getMenuPrice() {
		return menuPrice;
	}

	@Override
	public String toString() {
		return "SettlementPeriodVO [menuName=" + menuName + ", menuPrice=" + menuPrice + "]";
	}
	
}
