package kiosk_prj.coupon.vo;

public enum ExpirePeriod {
	MONTH1("1개월", 1),
	MONTH3("3개월", 3),
	YEAR1("1년", 12);
	
	private final String name;
	private final int month;
	
	private ExpirePeriod(String name, int month) {
		this.name = name;
		this.month = month;
	}
	public String getName() {
		return name;
	}
	public int getMonth() {
		return month;
	}
}
