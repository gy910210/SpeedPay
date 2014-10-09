package speedpay.dao;

import java.util.Date;

/**
 * Renewalapply entity. @author MyEclipse Persistence Tools
 */

public class Renewalapply implements java.io.Serializable {

	// Fields

	private Integer renewalApplyId;
	private Borrowproof borrowproof;
	private Date renewalApplyTime;
	private Integer renewalApplyIsChecked;

	// Constructors

	/** default constructor */
	public Renewalapply() {
	}

	/** minimal constructor */
	public Renewalapply(Date renewalApplyTime, Integer renewalApplyIsChecked) {
		this.renewalApplyTime = renewalApplyTime;
		this.renewalApplyIsChecked = renewalApplyIsChecked;
	}

	/** full constructor */
	public Renewalapply(Borrowproof borrowproof, Date renewalApplyTime,
			Integer renewalApplyIsChecked) {
		this.borrowproof = borrowproof;
		this.renewalApplyTime = renewalApplyTime;
		this.renewalApplyIsChecked = renewalApplyIsChecked;
	}

	// Property accessors

	public Integer getRenewalApplyId() {
		return this.renewalApplyId;
	}

	public void setRenewalApplyId(Integer renewalApplyId) {
		this.renewalApplyId = renewalApplyId;
	}

	public Borrowproof getBorrowproof() {
		return this.borrowproof;
	}

	public void setBorrowproof(Borrowproof borrowproof) {
		this.borrowproof = borrowproof;
	}

	public Date getRenewalApplyTime() {
		return this.renewalApplyTime;
	}

	public void setRenewalApplyTime(Date renewalApplyTime) {
		this.renewalApplyTime = renewalApplyTime;
	}

	public Integer getRenewalApplyIsChecked() {
		return this.renewalApplyIsChecked;
	}

	public void setRenewalApplyIsChecked(Integer renewalApplyIsChecked) {
		this.renewalApplyIsChecked = renewalApplyIsChecked;
	}

}