package speedpay.dao;

import java.util.Date;

/**
 * Transferproof entity. @author MyEclipse Persistence Tools
 */

public class Transferproof implements java.io.Serializable {

	// Fields

	private Integer transferProofId;
	private User userByTransferProofTransferUserId;
	private User userByTransferProofReceiverUserId;
	private Date transferProofTime;
	private Double transferProofSum;
	private String transferProofCause;

	// Constructors

	/** default constructor */
	public Transferproof() {
	}

	/** minimal constructor */
	public Transferproof(Date transferProofTime, Double transferProofSum) {
		this.transferProofTime = transferProofTime;
		this.transferProofSum = transferProofSum;
	}

	/** full constructor */
	public Transferproof(User userByTransferProofTransferUserId,
			User userByTransferProofReceiverUserId, Date transferProofTime,
			Double transferProofSum, String transferProofCause) {
		this.userByTransferProofTransferUserId = userByTransferProofTransferUserId;
		this.userByTransferProofReceiverUserId = userByTransferProofReceiverUserId;
		this.transferProofTime = transferProofTime;
		this.transferProofSum = transferProofSum;
		this.transferProofCause = transferProofCause;
	}

	// Property accessors

	public Integer getTransferProofId() {
		return this.transferProofId;
	}

	public void setTransferProofId(Integer transferProofId) {
		this.transferProofId = transferProofId;
	}

	public User getUserByTransferProofTransferUserId() {
		return this.userByTransferProofTransferUserId;
	}

	public void setUserByTransferProofTransferUserId(
			User userByTransferProofTransferUserId) {
		this.userByTransferProofTransferUserId = userByTransferProofTransferUserId;
	}

	public User getUserByTransferProofReceiverUserId() {
		return this.userByTransferProofReceiverUserId;
	}

	public void setUserByTransferProofReceiverUserId(
			User userByTransferProofReceiverUserId) {
		this.userByTransferProofReceiverUserId = userByTransferProofReceiverUserId;
	}

	public Date getTransferProofTime() {
		return this.transferProofTime;
	}

	public void setTransferProofTime(Date transferProofTime) {
		this.transferProofTime = transferProofTime;
	}

	public Double getTransferProofSum() {
		return this.transferProofSum;
	}

	public void setTransferProofSum(Double transferProofSum) {
		this.transferProofSum = transferProofSum;
	}

	public String getTransferProofCause() {
		return this.transferProofCause;
	}

	public void setTransferProofCause(String transferProofCause) {
		this.transferProofCause = transferProofCause;
	}

}