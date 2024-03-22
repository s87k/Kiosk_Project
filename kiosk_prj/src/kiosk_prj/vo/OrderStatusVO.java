package kiosk_prj.vo;

import java.sql.Date;

public class OrderStatusVO {
	private String waitingNumber;
	private Date orderTime;
	private String menuName;
	private int price;
	private String status;
	
	/**
	 * String waitingNumber, Date orderTime, String menuName, int price, String status
	 * @param waitingNumber
	 * @param orderTime
	 * @param menuName
	 * @param price
	 * @param status
	 */
	public OrderStatusVO(String waitingNumber, Date orderTime, String menuName, int price, String status) {
		this.waitingNumber = waitingNumber;
		this.orderTime = orderTime;
		this.menuName = menuName;
		this.price = price;
		this.status = status;
	}

	public String getWaitingNumber() {
		return waitingNumber;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public String getMenuName() {
		return menuName;
	}

	public int getPrice() {
		return price;
	}

	public String getStatus() {
		return status;
	}
	
}
