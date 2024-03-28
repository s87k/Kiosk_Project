package kiosks.vo;

public class OrderMenuVO {
	private String menuCode;
	private String typeCode;
	private String menuName;
	private int menuPrice;
	private String menuImg;
	
	public OrderMenuVO() {
		
	}//OrderMenuVO

	public OrderMenuVO(String menuCode, String typeCode, String menuName, int menuPrice, String menuImg) {
		super();
		this.menuCode = menuCode;
		this.typeCode = typeCode;
		this.menuName = menuName;
		this.menuPrice = menuPrice;
		this.menuImg = menuImg;
	}
	

	public OrderMenuVO(String menuImg) {
		super();
		this.menuImg = menuImg;
	}
	
	

	public OrderMenuVO(String menuImg, String menuName, int menuPrice) {
		super();
		this.menuName = menuName;
		this.menuPrice = menuPrice;
		this.menuImg = menuImg;
	}
	
	public OrderMenuVO(String menuCode, String typeCode) {
		super();
		this.menuCode = menuCode;
		this.typeCode = typeCode;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getMenuPrice() {
		return menuPrice;
	}

	public void setMenuPrice(int menuPrice) {
		this.menuPrice = menuPrice;
	}

	public String getMenuImg() {
		return menuImg;
	}

	public void setMenuImg(String menuImg) {
		this.menuImg = menuImg;
	}

	@Override
	public String toString() {
		return "OrderMenuVO [menuCode=" + menuCode + ", typeCode=" + typeCode + ", menuName=" + menuName
				+ ", menuPrice=" + menuPrice + ", menuImg=" + menuImg + "]";
	}
	
	
}
