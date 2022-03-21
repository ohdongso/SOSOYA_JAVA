package sosoya.mvc.model.dto;

public class OrdersDetailsVO {
	// 멤버변수
	private int ordersDetailsCode;
	private int ordersCode;
	private int goodsCode;
	private int ordersDetailsPrice; // 제품단가
	private int ordersDetailsCount; // 구매수량
	private int ordersDetailsTotalPrice; // 총가격
	
	private String goodsName;
	
	// 생성자
	public OrdersDetailsVO() {}
	public OrdersDetailsVO(int ordersDetailsCode, int ordersCode, int goodsCode,
			int ordersDetailsPrice, int ordersDetailsCount, int ordersDetailsTotalPrice) {
		super();
		this.ordersDetailsCode = ordersDetailsCode;
		this.ordersCode = ordersCode;
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
	public int getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(int goodsCode) {
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
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	// toString()
	@Override
	public String toString() {
		return "주문상세 [상품이름:" + goodsName + ", 상품단가:" + ordersDetailsPrice + "원, 상품개수:"
				+ ordersDetailsCount + "개, 총금액:" + ordersDetailsTotalPrice + "원]";
	}
}