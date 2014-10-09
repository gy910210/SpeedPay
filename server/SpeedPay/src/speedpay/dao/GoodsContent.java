package speedpay.dao;

/**
 * GoodsContent entity. @author MyEclipse Persistence Tools
 */

public class GoodsContent implements java.io.Serializable {

	// Fields

	private Integer goodsContentId;
	private Purchasecontent purchasecontent;
	private Goods goods;
	private Integer goodsContentQuantity;

	// Constructors

	/** default constructor */
	public GoodsContent() {
	}

	/** minimal constructor */
	public GoodsContent(Integer goodsContentQuantity) {
		this.goodsContentQuantity = goodsContentQuantity;
	}

	/** full constructor */
	public GoodsContent(Purchasecontent purchasecontent, Goods goods,
			Integer goodsContentQuantity) {
		this.purchasecontent = purchasecontent;
		this.goods = goods;
		this.goodsContentQuantity = goodsContentQuantity;
	}

	// Property accessors

	public Integer getGoodsContentId() {
		return this.goodsContentId;
	}

	public void setGoodsContentId(Integer goodsContentId) {
		this.goodsContentId = goodsContentId;
	}

	public Purchasecontent getPurchasecontent() {
		return this.purchasecontent;
	}

	public void setPurchasecontent(Purchasecontent purchasecontent) {
		this.purchasecontent = purchasecontent;
	}

	public Goods getGoods() {
		return this.goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Integer getGoodsContentQuantity() {
		return this.goodsContentQuantity;
	}

	public void setGoodsContentQuantity(Integer goodsContentQuantity) {
		this.goodsContentQuantity = goodsContentQuantity;
	}

}