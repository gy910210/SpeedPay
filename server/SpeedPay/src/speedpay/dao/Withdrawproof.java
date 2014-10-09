package speedpay.dao;

import java.util.Date;

/**
 * Withdrawproof entity. @author MyEclipse Persistence Tools
 */

public class Withdrawproof implements java.io.Serializable {

	// Fields

	private Integer withdrawProofId;
	private User user;
	private String withdrawProofAtmId;
	private Date withdrawProofTime;
	private Double withdrawProofSum;

	// Constructors

	/** default constructor */
	public Withdrawproof() {
	}

	/** minimal constructor */
	public Withdrawproof(String withdrawProofAtmId, Date withdrawProofTime,
			Double withdrawProofSum) {
		this.withdrawProofAtmId = withdrawProofAtmId;
		this.withdrawProofTime = withdrawProofTime;
		this.withdrawProofSum = withdrawProofSum;
	}

	/** full constructor */
	public Withdrawproof(User user, String withdrawProofAtmId,
			Date withdrawProofTime, Double withdrawProofSum) {
		this.user = user;
		this.withdrawProofAtmId = withdrawProofAtmId;
		this.withdrawProofTime = withdrawProofTime;
		this.withdrawProofSum = withdrawProofSum;
	}

	// Property accessors

	public Integer getWithdrawProofId() {
		return this.withdrawProofId;
	}

	public void setWithdrawProofId(Integer withdrawProofId) {
		this.withdrawProofId = withdrawProofId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getWithdrawProofAtmId() {
		return this.withdrawProofAtmId;
	}

	public void setWithdrawProofAtmId(String withdrawProofAtmId) {
		this.withdrawProofAtmId = withdrawProofAtmId;
	}

	public Date getWithdrawProofTime() {
		return this.withdrawProofTime;
	}

	public void setWithdrawProofTime(Date withdrawProofTime) {
		this.withdrawProofTime = withdrawProofTime;
	}

	public Double getWithdrawProofSum() {
		return this.withdrawProofSum;
	}

	public void setWithdrawProofSum(Double withdrawProofSum) {
		this.withdrawProofSum = withdrawProofSum;
	}

}