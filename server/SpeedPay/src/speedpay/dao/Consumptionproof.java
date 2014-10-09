package speedpay.dao;

import java.util.Date;

/**
 * Consumptionproof entity. @author MyEclipse Persistence Tools
 */

public class Consumptionproof implements java.io.Serializable {

	// Fields

	private Integer consumptionProofId;
	private User userByConsumptionProofUserId;
	private User userByConsumptionProofShopId;
	private Date consumptionProofTime;
	private Double consumptionProofSum;
	private String consumptionProofCause;

	// Constructors

	/** default constructor */
	public Consumptionproof() {
	}

	/** minimal constructor */
	public Consumptionproof(Date consumptionProofTime,
			Double consumptionProofSum) {
		this.consumptionProofTime = consumptionProofTime;
		this.consumptionProofSum = consumptionProofSum;
	}

	/** full constructor */
	public Consumptionproof(User userByConsumptionProofUserId,
			User userByConsumptionProofShopId, Date consumptionProofTime,
			Double consumptionProofSum, String consumptionProofCause) {
		this.userByConsumptionProofUserId = userByConsumptionProofUserId;
		this.userByConsumptionProofShopId = userByConsumptionProofShopId;
		this.consumptionProofTime = consumptionProofTime;
		this.consumptionProofSum = consumptionProofSum;
		this.consumptionProofCause = consumptionProofCause;
	}

	// Property accessors

	public Integer getConsumptionProofId() {
		return this.consumptionProofId;
	}

	public void setConsumptionProofId(Integer consumptionProofId) {
		this.consumptionProofId = consumptionProofId;
	}

	public User getUserByConsumptionProofUserId() {
		return this.userByConsumptionProofUserId;
	}

	public void setUserByConsumptionProofUserId(
			User userByConsumptionProofUserId) {
		this.userByConsumptionProofUserId = userByConsumptionProofUserId;
	}

	public User getUserByConsumptionProofShopId() {
		return this.userByConsumptionProofShopId;
	}

	public void setUserByConsumptionProofShopId(
			User userByConsumptionProofShopId) {
		this.userByConsumptionProofShopId = userByConsumptionProofShopId;
	}

	public Date getConsumptionProofTime() {
		return this.consumptionProofTime;
	}

	public void setConsumptionProofTime(Date consumptionProofTime) {
		this.consumptionProofTime = consumptionProofTime;
	}

	public Double getConsumptionProofSum() {
		return this.consumptionProofSum;
	}

	public void setConsumptionProofSum(Double consumptionProofSum) {
		this.consumptionProofSum = consumptionProofSum;
	}

	public String getConsumptionProofCause() {
		return this.consumptionProofCause;
	}

	public void setConsumptionProofCause(String consumptionProofCause) {
		this.consumptionProofCause = consumptionProofCause;
	}

}