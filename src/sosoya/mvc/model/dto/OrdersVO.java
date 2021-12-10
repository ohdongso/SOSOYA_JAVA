package sosoya.mvc.model.dto;

import java.util.ArrayList; 
import java.util.List;

public class OrdersVO {
	// 멤버변수
	private int ordersCode;
	private String id;
	private String ordersDi;
	private int ordersTotalprice;
	private String ordersDate;
	
	private List<OrdersDetailsVO> ordersDetailsList = new ArrayList<>();
	
	// 생성자
	public OrdersVO() {}
	public OrdersVO(int ordersCode, String id,String ordersDi, int ordersTotalprice, String ordersDate) {
		this.ordersCode = ordersCode;
		this.id = id;
		this.ordersDi = ordersDi;
		this.ordersTotalprice = ordersTotalprice;
		this.ordersDate = ordersDate;
	}
	
	// getter, setter
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
	public String getOrdersDi() {
		return ordersDi;
	}
	public void setOrdersDi(String ordersDi) {
		this.ordersDi = ordersDi;
	}
	public int getOrdersTotalprice() {
		return ordersTotalprice;
	}
	public void setOrdersTotalprice(int ordersTotalprice) {
		this.ordersTotalprice = ordersTotalprice;
	}
	public String getOrdersDate() {
		return ordersDate;
	}
	public void setOrdersDate(String ordersDate) {
		this.ordersDate = ordersDate;
	}
	public List<OrdersDetailsVO> getOrdersDetailsList() {
		return ordersDetailsList;
	}
	public void setOrdersDetailsList(List<OrdersDetailsVO> ordersDetailsList) {
		this.ordersDetailsList = ordersDetailsList;
	}
	
	// toString
	@Override
	public String toString() {
		return "OrdersVO [ordersCode=" + ordersCode + ", id=" + id + ", ordersDi=" + ordersDi + ", ordersTotalprice="
				+ ordersTotalprice + ", ordersDate=" + ordersDate + ", ordersDetailsList=" + ordersDetailsList + "]";
	}
}