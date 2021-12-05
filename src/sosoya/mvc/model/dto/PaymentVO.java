package sosoya.mvc.model.dto;

public class PaymentVO {
	// 멤버변수
	private int paymentCode;
	private int ordersCode;
	private String id;
	private String paymentDate;
	
	// 생성자
	public PaymentVO() {}
	public PaymentVO(int paymentCode, int ordersCode, String id, String paymentDate) {
		super();
		this.paymentCode = paymentCode;
		this.ordersCode = ordersCode;
		this.id = id;
		this.paymentDate = paymentDate;
	}
	
	// getter, setter
	public int getPaymentCode() {
		return paymentCode;
	}
	public void setPaymentCode(int paymentCode) {
		this.paymentCode = paymentCode;
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
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	// toString
	@Override
	public String toString() {
		return "PaymentVO [paymentCode=" + paymentCode + ", ordersCode=" + ordersCode + ", id=" + id + ", paymentDate="
				+ paymentDate + "]";
	}
}