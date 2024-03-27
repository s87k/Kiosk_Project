package kiosk_prj.coupon.vo;

public enum StatusUse {
	USABLE("사용 가능", 0, "0"),
	ALREADY_USE("사용 완료", 1, "1"), 
	EXPIRE("만료됨", 2, "2"),
	UN_USABLE("사용 불가", 3, "3"),
	ALL("전체", 4, "4");
	
	private final String name;
	private final int intVal;
	private final String strVal;
	
	private StatusUse(String name, int intVal, String strVal) {
		this.name = name;
		this.intVal = intVal;
		this.strVal = strVal;
	}

	public String getName() {
		return name;
	}
	public int getIntVal() {
		return intVal;
	}
	public String getStrVal() {
		return strVal;
	}
}
