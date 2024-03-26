package kiosk_prj.membership;

public class ConsumePatternVO {
	private String menuName;
	private int salesCount;
	
	private int amount;
	
	
	
	
	public ConsumePatternVO(String menuName, int salesCount, int amount) {
		this.menuName = menuName;
		this.salesCount = salesCount;
		this.amount = amount;
		
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public int getSalesCount() {
		return salesCount;
	}
	public void setSalesCount(int salesCount) {
		this.salesCount = salesCount;
	}

	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "ConsumePatternVO [menuName=" + menuName + ", salesCount=" + salesCount + ", salesRatio=" + 
				 ", amount=" + amount + ", amountRatio=" + "]";
	}
	
	
}
