package sosoya.mvc.model.dto;

import java.util.List;

public class OrdersDetailsVO {
	// 멤버변수
	private int ordersDetailsCode;
	private int ordersCode;
	private String id;
	private List<Integer> goodsCode;
	private int ordersDetailsPrice;
	private int ordersDetailsCount;
	private int ordersDetailsTotalPrice;
	
	// 생성자
	public OrdersDetailsVO() {}
	public OrdersDetailsVO(int ordersDetailsCode, int ordersCode, String id, List<Integer> goodsCode,
			int ordersDetailsPrice, int ordersDetailsCount, int ordersDetailsTotalPrice) {
		super();
		this.ordersDetailsCode = ordersDetailsCode;
		this.ordersCode = ordersCode;
		this.id = id;
		this.goodsCode = goodsCode;
		this.ordersDetailsPrice = ordersDetailsPrice;
		this.ordersDetailsCount = ordersDetailsCount;
		this.ordersDetailsTotalPrice = ordersDetailsTotalPrice;
	}
	
	// getter, setter
	public int getOrdersDetailsCode() {
		return ordersDetailsCode;
	}
	public void setOrdersDetailsCode(int ordersDetailsCode) {
		this.ordersDetailsCode = ordersDetailsCode;
	}
	public int getOrdersCode() {
		return ordersCode;
	}
	public void setOrdersCode(int ordersCode) {
		this.ordersCode = ordersCode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<Integer> getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(List<Integer> goodsCode) {
		this.goodsCode = goodsCode;
	}
	public int getOrdersDetailsPrice() {
		return ordersDetailsPrice;
	}
	public void setOrdersDetailsPrice(int ordersDetailsPrice) {
		this.ordersDetailsPrice = ordersDetailsPrice;
	}
	public int getOrdersDetailsCount() {
		return ordersDetailsCount;
	}
	public void setOrdersDetailsCount(int ordersDetailsCount) {
		this.ordersDetailsCount = ordersDetailsCount;
	}
	public int getOrdersDetailsTotalPrice() {
		return ordersDetailsTotalPrice;
	}
	public void setOrdersDetailsTotalPrice(int ordersDetailsTotalPrice) {
		this.ordersDetailsTotalPrice = ordersDetailsTotalPrice;
	}
	
	// tostring
	@Override
	public String toString() {
		return "OrdersDetailsVO [ordersDetailsCode=" + ordersDetailsCode + ", ordersCode=" + ordersCode + ", id=" + id
				+ ", goodsCode=" + goodsCode + ", ordersDetailsPrice=" + ordersDetailsPrice + ", ordersDetailsCount="
				+ ordersDetailsCount + ", ordersDetailsTotalPrice=" + ordersDetailsTotalPrice + "]";
	}
}