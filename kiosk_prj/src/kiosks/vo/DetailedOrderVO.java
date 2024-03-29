package kiosks.vo;

public class DetailedOrderVO {

	private String orderNumber;
	private String shopOpen;
	private int num;
	private int waitingNumber;
	private String cupSize;
	private String temp;
	private int shot;
	private String menuCode;
	private String typeCode;
	
	public DetailedOrderVO() {
	}

	public DetailedOrderVO(String orderNumber, String shopOpen, int num, int waitingNumber, String cupSize, String temp,
			int shot, String menuCode, String typeCode) {
		super();
		this.orderNumber = orderNumber;
		this.shopOpen = shopOpen;
		this.num = num;
		this.waitingNumber = waitingNumber;
		this.cupSize = cupSize;
		this.temp = temp;
		this.shot = shot;
		this.menuCode = menuCode;
		this.typeCode = typeCode;
	}

	public DetailedOrderVO(int waitingNumber) {
		super();
		this.waitingNumber = waitingNumber;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getShopOpen() {
		return shopOpen;
	}

	public void setShopOpen(String shopOpen) {
		this.shopOpen = shopOpen;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getWaitingNumber() {
		return waitingNumber;
	}

	public void setWaitingNumber(int waitingNumber) {
		this.waitingNumber = waitingNumber;
	}

	public String getCupSize() {
		return cupSize;
	}

	public void setCupSize(String cupSize) {
		this.cupSize = cupSize;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public int getShot() {
		return shot;
	}

	public void setShot(int shot) {
		this.shot = shot;
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

	@Override
	public String toString() {
		return "DetailedOrderVO [orderNumber=" + orderNumber + ", shopOpen=" + shopOpen + ", num=" + num
				+ ", waitingNumber=" + waitingNumber + ", cupSize=" + cupSize + ", temp=" + temp + ", shot=" + shot
				+ ", menuCode=" + menuCode + ", typeCode=" + typeCode + "]";
	}
	
}
