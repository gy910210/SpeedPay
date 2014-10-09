package speedpay.dao;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Purchasecontent entity. @author MyEclipse Persistence Tools
 */

public class Purchasecontent implements java.io.Serializable {

	// Fields

	private Integer purchaseContentId;
	private User userByPurchaseContentMarketId;
	private User userByPurchaseContentUserId;
	private Double purchaseContentConsumptionSum;
	private Date purchaseContentConsumptionTime;
	private Set goodsContents = new HashSet(0);

	// Constructors

	/** default constructor */
	public Purchasecontent() {
	}

	/** minimal constructor */
	public Purchasecontent(Double purchaseContentConsumptionSum,
			Date purchaseContentConsumptionTime) {
		this.purchaseContentConsumptionSum = purchaseContentConsumptionSum;
		this.purchaseContentConsumptionTime = purchaseContentConsumptionTime;
	}

	/** full constructor */
	public Purchasecontent(User userByPurchaseContentMarketId,
			User userByPurchaseContentUserId,
			Double purchaseContentConsumptionSum,
			Date purchaseContentConsumptionTime, Set goodsContents) {
		this.userByPurchaseContentMarketId = userByPurchaseContentMarketId;
		this.userByPurchaseContentUserId = userByPurchaseContentUserId;
		this.purchaseContentConsumptionSum = purchaseContentConsumptionSum;
		this.purchaseContentConsumptionTime = purchaseContentConsumptionTime;
		this.goodsContents = goodsContents;
	}

	// Property accessors

	public Integer getPurchaseContentId() {
		return this.purchaseContentId;
	}

	public void setPurchaseContentId(Integer purchaseContentId) {
		this.purchaseContentId = purchaseContentId;
	}

	public User getUserByPurchaseContentMarketId() {
		return this.userByPurchaseContentMarketId;
	}

	public void setUserByPurchaseContentMarketId(
			User userByPurchaseContentMarketId) {
		this.userByPurchaseContentMarketId = userByPurchaseContentMarketId;
	}

	public User getUserByPurchaseContentUserId() {
		return this.userByPurchaseContentUserId;
	}

	public void setUserByPurchaseContentUserId(User userByPurchaseContentUserId) {
		this.userByPurchaseContentUserId = userByPurchaseContentUserId;
	}

	public Double getPurchaseContentConsumptionSum() {
		return this.purchaseContentConsumptionSum;
	}

	public void setPurchaseContentConsumptionSum(
			Double purchaseContentConsumptionSum) {
		this.purchaseContentConsumptionSum = purchaseContentConsumptionSum;
	}

	public Date getPurchaseContentConsumptionTime() {
		return this.purchaseContentConsumptionTime;
	}

	public void setPurchaseContentConsumptionTime(
			Date purchaseContentConsumptionTime) {
		this.purchaseContentConsumptionTime = purchaseContentConsumptionTime;
	}

	public Set getGoodsContents() {
		return this.goodsContents;
	}

	public void setGoodsContents(Set goodsContents) {
		this.goodsContents = goodsContents;
	}

}