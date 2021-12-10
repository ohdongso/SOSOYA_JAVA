package sosoya.mvc.model.dto;

public class PaymentVO {
	// 멤버변수
	private int paymentCode;
	private int ordersCode;
	private String id;
	private String paymentDate;
	
	// DB에 존재하지 않는 멤버변수
	private MemberVO memberVO;
	private OrdersVO ordersVO;
	
	// 생성자
	public PaymentVO() {}
	public PaymentVO(int paymentCode, int ordersCode, String id, String paymentDate) {
		super();
		this.paymentCode = paymentCode;
		this.ordersCode = ordersCode;
		this.id = id;
		this.paymentDate = paymentDate;
	}	
	public PaymentVO(int paymentCode, int ordersCode, String id, String paymentDate, MemberVO memberVO,
			OrdersVO ordersVO) {
		super();
		this.paymentCode = paymentCode;
		this.ordersCode = ordersCode;
		this.id = id;
		this.paymentDate = paymentDate;
		this.memberVO = memberVO;
		this.ordersVO = ordersVO;
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
	public MemberVO getMemberVO() {
		return memberVO;
	}
	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}
	public OrdersVO getOrdersVO() {
		return ordersVO;
	}
	public void setOrdersVO(OrdersVO ordersVO) {
		this.ordersVO = ordersVO;
	}
	
	// toString
	@Override
	public String toString() {
		return "PaymentVO [paymentCode=" + paymentCode + ", ordersCode=" + ordersCode + ", id=" + id + ", paymentDate="
				+ paymentDate + ", memberVO=" + memberVO + ", ordersVO=" + ordersVO + "]";
	}
}