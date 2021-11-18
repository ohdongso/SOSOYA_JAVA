package sosoya.mvc.model.dto;

public class BasketVO {
	// 멤버변수, 이슈(goods객체를 멤버변수로 넣을걸 그랬다.)
	private int basketCode;
	private String id;
	private int goodsCode;
	private String basketRegdate;
	private int basketTotalprice;
	private int goodsCount;
	private int goodsPrice;
	private String goodsName;
	
	// 생성자
	public BasketVO() {}
	public BasketVO(String id, int goodsCode, int goodsCount, String goodsName) {
		this.id = id;
		this.goodsCode = goodsCode;
		this.goodsCount = goodsCount;
		this.goodsName = goodsName;
	}
	public BasketVO(int basketCode, String id, int goodsCode, String basketRegdate, int basketTotalprice,
			int goodsCount, int goodsPrice, String goodsName) {
		super();
		this.basketCode = basketCode;
		this.id = id;
		this.goodsCode = goodsCode;
		this.basketRegdate = basketRegdate;
		this.basketTotalprice = basketTotalprice;
		this.goodsCount = goodsCount;
		this.goodsPrice = goodsPrice;
		this.goodsName = goodsName;
	}
	
	// getter, setter
	public int getBasketCode() {
		return basketCode;
	}
	public void setBasketCode(int basketCode) {
		this.basketCode = basketCode;
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
	public String getBasketRegdate() {
		return basketRegdate;
	}
	public void setBasketRegdate(String basketRegdate) {
		this.basketRegdate = basketRegdate;
	}
	public int getBasketTotalprice() {
		return basketTotalprice;
	}
	public void setBasketTotalprice(int basketTotalprice) {
		this.basketTotalprice = basketTotalprice;
	}
	public int getGoodsCount() {
		return goodsCount;
	}
	public void setGoodsCount(int goodsCount) {
		this.goodsCount = goodsCount;
	}
	public int getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(int goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	// toString
	@Override
	public String toString() {
		return  "상품이름:" + goodsName + " ==> [ 상품단가:" + goodsPrice + "원, 상품개수:" + goodsCount + "개, 상품총가격:" + basketTotalprice + "원, 장바구니코드:" + basketCode + " ]";
	}
	
	
}