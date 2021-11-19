package sosoya.mvc.model.dto;

public class BasketVO {
	// 멤버변수
	private int basketCode;
	private String id;
	private int goodsCode;
	private String basketRegdate;
	private int basketTotalprice;
	private int basketGoodsCount;
	
	private GoodsVO goodsVO;
	
	// 생성자
	public BasketVO() {}
	public BasketVO(String id, int goodsCode, int basketGoodsCount) {
		this.id = id;
		this.goodsCode = goodsCode;
		this.basketGoodsCount = basketGoodsCount;
	}
	public BasketVO(int basketCode, String id, int goodsCode, String basketRegdate, int basketTotalprice,
			int basketGoodsCount) {
		super();
		this.basketCode = basketCode;
		this.id = id;
		this.goodsCode = goodsCode;
		this.basketRegdate = basketRegdate;
		this.basketTotalprice = basketTotalprice;
		this.basketGoodsCount = basketGoodsCount;
	}
	public BasketVO(int basketCode, String id, int goodsCode, String basketRegdate, int basketTotalprice,
			int basketGoodsCount, GoodsVO goodsVO) {
		super();
		this.basketCode = basketCode;
		this.id = id;
		this.goodsCode = goodsCode;
		this.basketRegdate = basketRegdate;
		this.basketTotalprice = basketTotalprice;
		this.basketGoodsCount = basketGoodsCount;
		this.goodsVO = goodsVO;
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
	public int getBasketGoodsCount() {
		return basketGoodsCount;
	}
	public void setBasketGoodsCount(int basketGoodsCount) {
		this.basketGoodsCount = basketGoodsCount;
	}
	public GoodsVO getGoodsVO() {
		return goodsVO;
	}
	public void setGoodsVO(GoodsVO goodsVO) {
		this.goodsVO = goodsVO;
	}
	
	// toString
	@Override
	public String toString() {
		return  "상품이름:" + goodsVO.getGoodsName() + " ==> [ 상품단가:" + goodsVO.getGoodsPrice() + 
		"원, 상품개수:" + basketGoodsCount + "개, 상품총가격:" + basketTotalprice + "원, 장바구니코드:" + basketCode + " ]";
	}

}