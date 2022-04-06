package sosoya.mvc.model.dto;

public class ErVO {
	private int erCode;
	private String id;
	private int ordersCode;
	private int goodsCode;
	private int erCategory;
	private String erTitle;
	private String erContent;
	private String ordersDate;
	
	public ErVO() {}
	public ErVO(int erCode, String id, int ordersCode, int goodsCode, int erCategory, String erTitle, String erContent,
			String ordersDate) {
		super();
		this.erCode = erCode;
		this.id = id;
		this.ordersCode = ordersCode;
		this.goodsCode = goodsCode;
		this.erCategory = erCategory;
		this.erTitle = erTitle;
		this.erContent = erContent;
		this.ordersDate = ordersDate;
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
	public String getOrdersDate() {
		return ordersDate;
	}
	public void setOrdersDate(String ordersDate) {
		this.ordersDate = ordersDate;
	}
	
	@Override
	public String toString() {
		return "ErVO [erCode=" + erCode + ", id=" + id + ", ordersCode=" + ordersCode + ", goodsCode=" + goodsCode
				+ ", erCategory=" + erCategory + ", erTitle=" + erTitle + ", erContent=" + erContent + ", ordersDate="
				+ ordersDate + "]";
	}
}