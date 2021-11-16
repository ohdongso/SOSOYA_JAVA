package sosoya.mvc.model.dto;

public class FaqVO {
	// 멤버변수
	private int faqCode;
	private int faqCategory; // 1,환불 2,교환 3,주문 4,결제 5,회원등급
	private String faqTitle;
	private String faqContent;
	
	// 생성자
	public FaqVO() {}
	public FaqVO(int faqCode, int faqCategory, String faqTitle, String faqContent) {
		this.faqCode = faqCode;
		this.faqCategory = faqCategory;
		this.faqTitle = faqTitle;
		this.faqContent = faqContent;
	}
	
	// getter, setter
	public int getFaqCode() {
		return faqCode;
	}
	public void setFaqCode(int faqCode) {
		this.faqCode = faqCode;
	}
	public int getFaqCategory() {
		return faqCategory;
	}
	public void setFaqCategory(int faqCategory) {
		this.faqCategory = faqCategory;
	}
	public String getFaqTitle() {
		return faqTitle;
	}
	public void setFaqTitle(String faqTitle) {
		this.faqTitle = faqTitle;
	}
	public String getFaqContent() {
		return faqContent;
	}
	public void setFaqContent(String faqContent) {
		this.faqContent = faqContent;
	}
}