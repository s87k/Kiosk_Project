package kiosk_prj.orderStatus;

public class DetailOrderVO {
	
	private String orderNum;
	private String orderTime;
	private String orderForm;
	private String phoneNum;
	private int amount;
	
	/**
	 * String orderNum, String orderTime, String orderForm, String phoneNum, int amount<br>
	 * 주문번호, 주문시간, 주문방법, 폰번호, 가격
	 * @param orderNum
	 * @param orderTime
	 * @param orderForm
	 * @param phoneNum
	 * @param amount
	 */
	public DetailOrderVO(String orderNum, String orderTime, String orderForm, String phoneNum, int amount) {
		super();
		this.orderNum = orderNum;
		this.orderTime = orderTime;
		this.orderForm = orderForm;
		this.phoneNum = phoneNum;
		this.amount = amount;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public String getOrderForm() {
		return orderForm;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public int getAmount() {
		return amount;
	}

	@Override
	public String toString() {
		return "SettlementOrderInfoVO [orderNum=" + orderNum + ", orderTime=" + orderTime + ", orderForm=" + orderForm
				+ ", phoneNum=" + phoneNum + ", amount=" + amount + "]";
	}
	
}
