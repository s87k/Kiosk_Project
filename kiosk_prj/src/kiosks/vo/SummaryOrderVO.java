package kiosks.vo;

import java.sql.Timestamp;

public class SummaryOrderVO {

	private String orderNumber;
	private String shopOpen;
	private Timestamp orderTime;
	private int amount;
	private String progress;
	private String orderForm;
	private String phoneNumber;
	
	
	
	public SummaryOrderVO() {
	}

	public SummaryOrderVO(String orderNumber, String shopOpen, Timestamp orderTime, int amount, String progress,
			String orderForm, String phoneNumber) {
		super();
		this.orderNumber = orderNumber;
		this.shopOpen = shopOpen;
		this.orderTime = orderTime;
		this.amount = amount;
		this.progress = progress;
		this.orderForm = orderForm;
		this.phoneNumber = phoneNumber;
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

	public Timestamp getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public String getOrderForm() {
		return orderForm;
	}

	public void setOrderForm(String orderForm) {
		this.orderForm = orderForm;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "SummaryOrderVO [orderNumber=" + orderNumber + ", shopOpen=" + shopOpen + ", orderTime=" + orderTime
				+ ", amount=" + amount + ", progress=" + progress + ", orderForm=" + orderForm + ", phoneNumber="
				+ phoneNumber + "]";
	}
	
	
}
