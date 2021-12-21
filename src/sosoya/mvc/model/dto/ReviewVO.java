package sosoya.mvc.model.dto;

public class ReviewVO {
	// 멤버변수
	private int reviewCode;
	private String id;
	private int goodsCode;
	private String reviewTitle;
	private String reviewContent;
	private int reviewGrade;
	private String reviewRegdate;
	
	private GoodsVO goodsVO;
	private MemberVO memberVO;
	
	// 생성자
	public ReviewVO() {}
	public ReviewVO(int reviewCode, String reviewTitle, String reviewContent, int reviewGrade, MemberVO memberVO) {
		super();
		this.reviewCode = reviewCode;
		this.reviewTitle = reviewTitle;
		this.reviewContent = reviewContent;
		this.reviewGrade = reviewGrade;
		this.memberVO = memberVO;
	}
	public ReviewVO(int reviewCode, String id, int goodsCode, String reviewTitle, String reviewContent, int reviewGrade,
			String reviewRegdate) {
		super();
		this.reviewCode = reviewCode;
		this.id = id;
		this.goodsCode = goodsCode;
		this.reviewTitle = reviewTitle;
		this.reviewContent = reviewContent;
		this.reviewGrade = reviewGrade;
		this.reviewRegdate = reviewRegdate;
	}
	public ReviewVO(int reviewCode, String id, int goodsCode, String reviewTitle, String reviewContent, int reviewGrade,
			String reviewRegdate, GoodsVO goodsVO, MemberVO memberVO) {
		super();
		this.reviewCode = reviewCode;
		this.id = id;
		this.goodsCode = goodsCode;
		this.reviewTitle = reviewTitle;
		this.reviewContent = reviewContent;
		this.reviewGrade = reviewGrade;
		this.reviewRegdate = reviewRegdate;
		this.goodsVO = goodsVO;
		this.memberVO = memberVO;
	}
	
	// getter, setter
	public int getReviewCode() {
		return reviewCode;
	}
	public void setReviewCode(int reviewCode) {
		this.reviewCode = reviewCode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(int goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getReviewTitle() {
		return reviewTitle;
	}
	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	public int getReviewGrade() {
		return reviewGrade;
	}
	public void setReviewGrade(int reviewGrade) {
		this.reviewGrade = reviewGrade;
	}
	public String getReviewRegdate() {
		return reviewRegdate;
	}
	public void setReviewRegdate(String reviewRegdate) {
		this.reviewRegdate = reviewRegdate;
	}
	public GoodsVO getGoodsVO() {
		return goodsVO;
	}
	public void setGoodsVO(GoodsVO goodsVO) {
		this.goodsVO = goodsVO;
	}
	public MemberVO getMemberVO() {
		return memberVO;
	}
	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}

	// toString
	@Override
	public String toString() {
		return "ReviewVO [reviewCode=" + reviewCode + ", id=" + id + ", goodsCode=" + goodsCode + ", reviewTitle="
				+ reviewTitle + ", reviewContent=" + reviewContent + ", reviewGrade=" + reviewGrade + ", reviewRegdate="
				+ reviewRegdate + ", goodsVO=" + goodsVO + ", memberVO=" + memberVO + "]";
	}
}