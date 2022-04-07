package sosoya.mvc.model.dto;

public class ErVO {
	private int erCode;
	private String id;
	private int ordersCode;
	private int odersDetailsCode;
	private int goodsCode;
	private int erCategory;
	private String erTitle;
	private String erContent;
	private String erDi;
	private String erRegdate;
	private int erState;
	
	public ErVO() {}
	public ErVO(int erCode, String id, int ordersCode, int odersDetailsCode, int goodsCode, int erCategory,
			String erTitle, String erContent, String erDi, String erRegdate, int erState) {
		super();
		this.erCode = erCode;
		this.id = id;
		this.ordersCode = ordersCode;
		this.odersDetailsCode = odersDetailsCode;
		this.goodsCode = goodsCode;
		this.erCategory = erCategory;
		this.erTitle = erTitle;
		this.erContent = erContent;
		this.erDi = erDi;
		this.erRegdate = erRegdate;
		this.erState = erState;
	}

	public int getErCode() {
		return erCode;
	}

	public void setErCode(int erCode) {
		this.erCode = erCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getOrdersCode() {
		return ordersCode;
	}

	public void setOrdersCode(int ordersCode) {
		this.ordersCode = ordersCode;
	}

	public int getOdersDetailsCode() {
		return odersDetailsCode;
	}

	public void setOdersDetailsCode(int odersDetailsCode) {
		this.odersDetailsCode = odersDetailsCode;
	}

	public int getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(int goodsCode) {
		this.goodsCode = goodsCode;
	}

	public int getErCategory() {
		return erCategory;
	}

	public void setErCategory(int erCategory) {
		this.erCategory = erCategory;
	}

	public String getErTitle() {
		return erTitle;
	}

	public void setErTitle(String erTitle) {
		this.erTitle = erTitle;
	}

	public String getErContent() {
		return erContent;
	}

	public void setErContent(String erContent) {
		this.erContent = erContent;
	}

	public String getErDi() {
		return erDi;
	}

	public void setErDi(String erDi) {
		this.erDi = erDi;
	}

	public String getErRegdate() {
		return erRegdate;
	}

	public void setErRegdate(String erRegdate) {
		this.erRegdate = erRegdate;
	}

	public int getErState() {
		return erState;
	}

	public void setErState(int erState) {
		this.erState = erState;
	}

	@Override
	public String toString() {
		return "ErVO [erCode=" + erCode + ", id=" + id + ", ordersCode=" + ordersCode + ", odersDetailsCode="
				+ odersDetailsCode + ", goodsCode=" + goodsCode + ", erCategory=" + erCategory + ", erTitle=" + erTitle
				+ ", erContent=" + erContent + ", erDi=" + erDi + ", erRegdate=" + erRegdate + ", erState=" + erState
				+ "]";
	}
}