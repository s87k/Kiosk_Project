package kiosk_prj.trend;

public class TrendVO {
	private String menuName;
	private int amount;
	
	public TrendVO() {
	}
	
	public TrendVO(String menuName, int amount) {
		this.menuName = menuName;
		this.amount = amount;
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
		return "TrendVO [menuName=" + menuName + ", amount=" + amount + "]";
	}
	
}
