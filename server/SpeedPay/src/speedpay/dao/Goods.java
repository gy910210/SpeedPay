package speedpay.dao;

import java.util.HashSet;
import java.util.Set;

/**
 * Goods entity. @author MyEclipse Persistence Tools
 */

public class Goods implements java.io.Serializable {

	// Fields

	private Integer goodsId;
	private String goodsName;
	private Double goodsPrice;
	private Set goodsContents = new HashSet(0);

	// Constructors

	/** default constructor */
	public Goods() {
	}

	/** minimal constructor */
	public Goods(String goodsName, Double goodsPrice) {
		this.goodsName = goodsName;
		this.goodsPrice = goodsPrice;
	}

	/** full constructor */
	public Goods(String goodsName, Double goodsPrice, Set goodsContents) {
		this.goodsName = goodsName;
		this.goodsPrice = goodsPrice;
		this.goodsContents = goodsContents;
	}

	// Property accessors

	public Integer getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return this.goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Double getGoodsPrice() {
		return this.goodsPrice;
	}

	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public Set getGoodsContents() {
		return this.goodsContents;
	}

	public void setGoodsContents(Set goodsContents) {
		this.goodsContents = goodsContents;
	}

}