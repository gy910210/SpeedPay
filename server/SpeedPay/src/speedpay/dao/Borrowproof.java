package speedpay.dao;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Borrowproof entity. @author MyEclipse Persistence Tools
 */

public class Borrowproof implements java.io.Serializable {

	// Fields

	private Integer borrowProofId;
	private User userByBorrowProofRepayUserId;
	private User userByBorrowProofBorrowUserId;
	private Date borrowProofBorrowTime;
	private Double borrowProofSum;
	private Date borrowProofRepayTime;
	private Integer borrowProofIsRepayed;
	private Set repayproofs = new HashSet(0);
	private Set renewalapplies = new HashSet(0);

	// Constructors

	/** default constructor */
	public Borrowproof() {
	}

	/** minimal constructor */
	public Borrowproof(Date borrowProofBorrowTime, Double borrowProofSum,
			Date borrowProofRepayTime, Integer borrowProofIsRepayed) {
		this.borrowProofBorrowTime = borrowProofBorrowTime;
		this.borrowProofSum = borrowProofSum;
		this.borrowProofRepayTime = borrowProofRepayTime;
		this.borrowProofIsRepayed = borrowProofIsRepayed;
	}

	/** full constructor */
	public Borrowproof(User userByBorrowProofRepayUserId,
			User userByBorrowProofBorrowUserId, Date borrowProofBorrowTime,
			Double borrowProofSum, Date borrowProofRepayTime,
			Integer borrowProofIsRepayed, Set repayproofs, Set renewalapplies) {
		this.userByBorrowProofRepayUserId = userByBorrowProofRepayUserId;
		this.userByBorrowProofBorrowUserId = userByBorrowProofBorrowUserId;
		this.borrowProofBorrowTime = borrowProofBorrowTime;
		this.borrowProofSum = borrowProofSum;
		this.borrowProofRepayTime = borrowProofRepayTime;
		this.borrowProofIsRepayed = borrowProofIsRepayed;
		this.repayproofs = repayproofs;
		this.renewalapplies = renewalapplies;
	}

	// Property accessors

	public Integer getBorrowProofId() {
		return this.borrowProofId;
	}

	public void setBorrowProofId(Integer borrowProofId) {
		this.borrowProofId = borrowProofId;
	}

	public User getUserByBorrowProofRepayUserId() {
		return this.userByBorrowProofRepayUserId;
	}

	public void setUserByBorrowProofRepayUserId(
			User userByBorrowProofRepayUserId) {
		this.userByBorrowProofRepayUserId = userByBorrowProofRepayUserId;
	}

	public User getUserByBorrowProofBorrowUserId() {
		return this.userByBorrowProofBorrowUserId;
	}

	public void setUserByBorrowProofBorrowUserId(
			User userByBorrowProofBorrowUserId) {
		this.userByBorrowProofBorrowUserId = userByBorrowProofBorrowUserId;
	}

	public Date getBorrowProofBorrowTime() {
		return this.borrowProofBorrowTime;
	}

	public void setBorrowProofBorrowTime(Date borrowProofBorrowTime) {
		this.borrowProofBorrowTime = borrowProofBorrowTime;
	}

	public Double getBorrowProofSum() {
		return this.borrowProofSum;
	}

	public void setBorrowProofSum(Double borrowProofSum) {
		this.borrowProofSum = borrowProofSum;
	}

	public Date getBorrowProofRepayTime() {
		return this.borrowProofRepayTime;
	}

	public void setBorrowProofRepayTime(Date borrowProofRepayTime) {
		this.borrowProofRepayTime = borrowProofRepayTime;
	}

	public Integer getBorrowProofIsRepayed() {
		return this.borrowProofIsRepayed;
	}

	public void setBorrowProofIsRepayed(Integer borrowProofIsRepayed) {
		this.borrowProofIsRepayed = borrowProofIsRepayed;
	}

	public Set getRepayproofs() {
		return this.repayproofs;
	}

	public void setRepayproofs(Set repayproofs) {
		this.repayproofs = repayproofs;
	}

	public Set getRenewalapplies() {
		return this.renewalapplies;
	}

	public void setRenewalapplies(Set renewalapplies) {
		this.renewalapplies = renewalapplies;
	}

}