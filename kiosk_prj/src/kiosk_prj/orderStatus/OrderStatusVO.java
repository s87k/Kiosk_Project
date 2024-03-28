package kiosk_prj.orderStatus;

public class OrderStatusVO {
	private String orderNumber	;
	private String menuName;
	private String orderTime;
	private int amount;
	private String prograss;
	
	/**
	 * String orderNumber, String menuName, String orderTime, int amount, String prograss<br>
	 * 주문번호, 메뉴이름, 주문시간, 가격, 진행상황
	 * @param orderNumber
	 * @param menuName
	 * @param orderTime
	 * @param amount
	 * @param prograss
	 */
	public OrderStatusVO(String orderNumber, String menuName, String orderTime, int amount, String prograss) {
		super();
		this.orderNumber = orderNumber;
		this.menuName = menuName;
		this.orderTime = orderTime;
		this.amount = amount;
		this.prograss = prograss;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public String getMenuName() {
		return menuName;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public int getAmount() {
		return amount;
	}

	public String getPrograss() {
		return prograss;
	}

	@Override
	public String toString() {
		return "OrderStatusVO [orderNumber=" + orderNumber + ", menuName=" + menuName + ", orderTime=" + orderTime
				+ ", amount=" + amount + ", prograss=" + prograss + "]";
	}
	
}
