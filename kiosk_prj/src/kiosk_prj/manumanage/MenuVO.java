package kiosk_prj.manumanage;

import java.sql.Date;

public class MenuVO {
	private String menuCode,typeCode,menuName,menuImg;
	private int menuPrice;
	private Date inputDate;
	
	public MenuVO() {
		
	}

	public MenuVO(String menuCode, String typeCode, String menuName, String menuImg, int menuPrice, Date inputDate) {
		this.menuCode = menuCode;
		this.typeCode = typeCode;
		this.menuName = menuName;
		this.menuImg = menuImg;
		this.menuPrice = menuPrice;
		this.inputDate = inputDate;
	}
	
	public MenuVO(String menuCode, String typeCode, String menuName, int menuPrice, String menuImg) {
		this.menuCode = menuCode;
		this.typeCode = typeCode;
		this.menuName = menuName;
		this.menuPrice = menuPrice;
		this.menuImg = menuImg;
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

	public String getMenuImg() {
		return menuImg;
	}

	public void setMenuImg(String menuImg) {
		this.menuImg = menuImg;
	}

	public int getMenuPrice() {
		return menuPrice;
	}

	public void setMenuPrice(int menuPrice) {
		this.menuPrice = menuPrice;
	}

	public Date getInputDate() {
		return inputDate;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}

	@Override
	public String toString() {
		return "MenuVO [menuCode=" + menuCode + ", typeCode=" + typeCode + ", menuName=" + menuName + ", menuImg="
				+ menuImg + ", menuPrice=" + menuPrice + ", inputDate=" + inputDate + "]";
	}

	
	
}
