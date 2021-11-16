package sosoya.mvc.model.dto;

public class GoodsVO {
	// 멤버변수
	private int goodsCode;
	private String goodsName;
	private int goodsPrice;
	private int goodsStock;
	private String goodsRegDate;
	private int goodsReviewCount;
	private float goodsGradeAvg;
	private int goodsCategory; // 1,과자류 2,젤리 3,사탕&껌 4,초콜릿
	
	
	// 생성자
	public GoodsVO() {}
	public GoodsVO(int goodsCode, String goodsName, int goodsPrice, int goodsStock, String goodsRegDate,
			int goodsReviewCount, int goodsCategory, float goodsGradeAvg) {
		this.goodsCode = goodsCode;
		this.goodsName = goodsName;
		this.goodsPrice = goodsPrice;
		this.goodsStock = goodsStock;
		this.goodsRegDate = goodsRegDate;
		this.goodsReviewCount = goodsReviewCount;
		this.goodsCategory = goodsCategory;
		this.goodsGradeAvg = goodsGradeAvg;
	}
	
	// getter, setter
	public int getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(int goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public int getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(int goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public int getGoodsStock() {
		return goodsStock;
	}
	public void setGoodsStock(int goodsStock) {
		this.goodsStock = goodsStock;
	}
	public String getGoodsRegDate() {
		return goodsRegDate;
	}
	public void setGoodsRegDate(String goodsRegDate) {
		this.goodsRegDate = goodsRegDate;
	}
	public int getGoodsReviewCount() {
		return goodsReviewCount;
	}
	public void setGoodsReviewCount(int goodsReviewCount) {
		this.goodsReviewCount = goodsReviewCount;
	}
	public int getGoodsCategory() {
		return goodsCategory;
	}
	public void setGoodsCategory(int goodsCategory) {
		this.goodsCategory = goodsCategory;
	}
	public float getGoodsGradeAvg() {
		return goodsGradeAvg;
	}
	public void setGoodsGradeAvg(float goodsGradeAvg) {
		this.goodsGradeAvg = goodsGradeAvg;
	}
	
	// toString()
	@Override
	public String toString() {
		return  "상품코드:" + goodsCode + " ==> [ " +
				"상품이름:" + goodsName + ", " +
				"상품가격:" + goodsPrice + "원, " + 
				"상품재고:" + goodsStock + "개, " +
				"종류:" + category(goodsCategory) + ", " + 
				"등록일:" + goodsRegDate + ", " + 
				"리뷰개수:" + goodsReviewCount + ", " + 
				"평점:" + goodsGradeAvg + 		
				" ]";
	}
	
	public String category(int goodsCategory) {
		String result = "";
		if(goodsCategory == 1) result = "과자류";
		else if(goodsCategory == 2) result = "젤리";
		else if(goodsCategory == 3) result = "사탕&껌";
		else if(goodsCategory == 4) result = "초콜릿";
			
		return result;
	}
}