package speedpay.dao;

import java.util.Date;

/**
 * Repayproof entity. @author MyEclipse Persistence Tools
 */

public class Repayproof implements java.io.Serializable {

	// Fields

	private Integer repayProofId;
	private Borrowproof borrowproof;
	private Double repayProofSum;
	private Date repayProofTrueRepayTime;
	private Integer repayProofIsOnTime;

	// Constructors

	/** default constructor */
	public Repayproof() {
	}

	/** minimal constructor */
	public Repayproof(Double repayProofSum, Date repayProofTrueRepayTime,
			Integer repayProofIsOnTime) {
		this.repayProofSum = repayProofSum;
		this.repayProofTrueRepayTime = repayProofTrueRepayTime;
		this.repayProofIsOnTime = repayProofIsOnTime;
	}

	/** full constructor */
	public Repayproof(Borrowproof borrowproof, Double repayProofSum,
			Date repayProofTrueRepayTime, Integer repayProofIsOnTime) {
		this.borrowproof = borrowproof;
		this.repayProofSum = repayProofSum;
		this.repayProofTrueRepayTime = repayProofTrueRepayTime;
		this.repayProofIsOnTime = repayProofIsOnTime;
	}

	// Property accessors

	public Integer getRepayProofId() {
		return this.repayProofId;
	}

	public void setRepayProofId(Integer repayProofId) {
		this.repayProofId = repayProofId;
	}

	public Borrowproof getBorrowproof() {
		return this.borrowproof;
	}

	public void setBorrowproof(Borrowproof borrowproof) {
		this.borrowproof = borrowproof;
	}

	public Double getRepayProofSum() {
		return this.repayProofSum;
	}

	public void setRepayProofSum(Double repayProofSum) {
		this.repayProofSum = repayProofSum;
	}

	public Date getRepayProofTrueRepayTime() {
		return this.repayProofTrueRepayTime;
	}

	public void setRepayProofTrueRepayTime(Date repayProofTrueRepayTime) {
		this.repayProofTrueRepayTime = repayProofTrueRepayTime;
	}

	public Integer getRepayProofIsOnTime() {
		return this.repayProofIsOnTime;
	}

	public void setRepayProofIsOnTime(Integer repayProofIsOnTime) {
		this.repayProofIsOnTime = repayProofIsOnTime;
	}

}