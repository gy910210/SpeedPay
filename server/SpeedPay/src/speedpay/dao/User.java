package speedpay.dao;

import java.util.HashSet;
import java.util.Set;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String userName;
	private String userPhoneNum;
	private String userPassword;
	private String userBankcardNum;
	private String userIdnum;
	private Integer userIsChecked;
	private Double userBalance;
	private Integer userType;
	private Integer userLevel;
	private String userPayPassword;
	private Set consumptionproofsForConsumptionProofShopId = new HashSet(0);
	private Set borrowproofsForBorrowProofRepayUserId = new HashSet(0);
	private Set borrowproofsForBorrowProofBorrowUserId = new HashSet(0);
	private Set withdrawproofs = new HashSet(0);
	private Set consumptionproofsForConsumptionProofUserId = new HashSet(0);
	private Set purchasecontentsForPurchaseContentMarketId = new HashSet(0);
	private Set purchasecontentsForPurchaseContentUserId = new HashSet(0);
	private Set messages = new HashSet(0);
	private Set transferproofsForTransferProofTransferUserId = new HashSet(0);
	private Set transferproofsForTransferProofReceiverUserId = new HashSet(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String userName, String userPhoneNum, String userPassword,
			String userIdnum, Integer userIsChecked, Double userBalance,
			Integer userType, Integer userLevel) {
		this.userName = userName;
		this.userPhoneNum = userPhoneNum;
		this.userPassword = userPassword;
		this.userIdnum = userIdnum;
		this.userIsChecked = userIsChecked;
		this.userBalance = userBalance;
		this.userType = userType;
		this.userLevel = userLevel;
	}

	/** full constructor */
	public User(String userName, String userPhoneNum, String userPassword,
			String userBankcardNum, String userIdnum, Integer userIsChecked,
			Double userBalance, Integer userType, Integer userLevel,String userPayPassword,
			Set consumptionproofsForConsumptionProofShopId,
			Set borrowproofsForBorrowProofRepayUserId,
			Set borrowproofsForBorrowProofBorrowUserId, Set withdrawproofs,
			Set consumptionproofsForConsumptionProofUserId,
			Set purchasecontentsForPurchaseContentMarketId,
			Set purchasecontentsForPurchaseContentUserId, Set messages,
			Set transferproofsForTransferProofTransferUserId,
			Set transferproofsForTransferProofReceiverUserId) {
		this.userName = userName;
		this.userPhoneNum = userPhoneNum;
		this.userPassword = userPassword;
		this.userBankcardNum = userBankcardNum;
		this.userIdnum = userIdnum;
		this.userIsChecked = userIsChecked;
		this.userBalance = userBalance;
		this.userType = userType;
		this.userLevel = userLevel;
		this.userPayPassword = userPayPassword;
		this.consumptionproofsForConsumptionProofShopId = consumptionproofsForConsumptionProofShopId;
		this.borrowproofsForBorrowProofRepayUserId = borrowproofsForBorrowProofRepayUserId;
		this.borrowproofsForBorrowProofBorrowUserId = borrowproofsForBorrowProofBorrowUserId;
		this.withdrawproofs = withdrawproofs;
		this.consumptionproofsForConsumptionProofUserId = consumptionproofsForConsumptionProofUserId;
		this.purchasecontentsForPurchaseContentMarketId = purchasecontentsForPurchaseContentMarketId;
		this.purchasecontentsForPurchaseContentUserId = purchasecontentsForPurchaseContentUserId;
		this.messages = messages;
		this.transferproofsForTransferProofTransferUserId = transferproofsForTransferProofTransferUserId;
		this.transferproofsForTransferProofReceiverUserId = transferproofsForTransferProofReceiverUserId;
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhoneNum() {
		return this.userPhoneNum;
	}

	public void setUserPhoneNum(String userPhoneNum) {
		this.userPhoneNum = userPhoneNum;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserBankcardNum() {
		return this.userBankcardNum;
	}

	public void setUserBankcardNum(String userBankcardNum) {
		this.userBankcardNum = userBankcardNum;
	}

	public String getUserIdnum() {
		return this.userIdnum;
	}

	public void setUserIdnum(String userIdnum) {
		this.userIdnum = userIdnum;
	}

	public Integer getUserIsChecked() {
		return this.userIsChecked;
	}

	public void setUserIsChecked(Integer userIsChecked) {
		this.userIsChecked = userIsChecked;
	}

	public Double getUserBalance() {
		return this.userBalance;
	}

	public void setUserBalance(Double userBalance) {
		this.userBalance = userBalance;
	}

	public Integer getUserType() {
		return this.userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getUserLevel() {
		return this.userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	public Set getConsumptionproofsForConsumptionProofShopId() {
		return this.consumptionproofsForConsumptionProofShopId;
	}

	public void setConsumptionproofsForConsumptionProofShopId(
			Set consumptionproofsForConsumptionProofShopId) {
		this.consumptionproofsForConsumptionProofShopId = consumptionproofsForConsumptionProofShopId;
	}

	public Set getBorrowproofsForBorrowProofRepayUserId() {
		return this.borrowproofsForBorrowProofRepayUserId;
	}

	public void setBorrowproofsForBorrowProofRepayUserId(
			Set borrowproofsForBorrowProofRepayUserId) {
		this.borrowproofsForBorrowProofRepayUserId = borrowproofsForBorrowProofRepayUserId;
	}

	public Set getBorrowproofsForBorrowProofBorrowUserId() {
		return this.borrowproofsForBorrowProofBorrowUserId;
	}

	public void setBorrowproofsForBorrowProofBorrowUserId(
			Set borrowproofsForBorrowProofBorrowUserId) {
		this.borrowproofsForBorrowProofBorrowUserId = borrowproofsForBorrowProofBorrowUserId;
	}

	public Set getWithdrawproofs() {
		return this.withdrawproofs;
	}

	public void setWithdrawproofs(Set withdrawproofs) {
		this.withdrawproofs = withdrawproofs;
	}

	public Set getConsumptionproofsForConsumptionProofUserId() {
		return this.consumptionproofsForConsumptionProofUserId;
	}

	public void setConsumptionproofsForConsumptionProofUserId(
			Set consumptionproofsForConsumptionProofUserId) {
		this.consumptionproofsForConsumptionProofUserId = consumptionproofsForConsumptionProofUserId;
	}

	public Set getPurchasecontentsForPurchaseContentMarketId() {
		return this.purchasecontentsForPurchaseContentMarketId;
	}

	public void setPurchasecontentsForPurchaseContentMarketId(
			Set purchasecontentsForPurchaseContentMarketId) {
		this.purchasecontentsForPurchaseContentMarketId = purchasecontentsForPurchaseContentMarketId;
	}

	public Set getPurchasecontentsForPurchaseContentUserId() {
		return this.purchasecontentsForPurchaseContentUserId;
	}

	public void setPurchasecontentsForPurchaseContentUserId(
			Set purchasecontentsForPurchaseContentUserId) {
		this.purchasecontentsForPurchaseContentUserId = purchasecontentsForPurchaseContentUserId;
	}

	public Set getMessages() {
		return this.messages;
	}

	public void setMessages(Set messages) {
		this.messages = messages;
	}

	public Set getTransferproofsForTransferProofTransferUserId() {
		return this.transferproofsForTransferProofTransferUserId;
	}

	public void setTransferproofsForTransferProofTransferUserId(
			Set transferproofsForTransferProofTransferUserId) {
		this.transferproofsForTransferProofTransferUserId = transferproofsForTransferProofTransferUserId;
	}

	public Set getTransferproofsForTransferProofReceiverUserId() {
		return this.transferproofsForTransferProofReceiverUserId;
	}

	public void setTransferproofsForTransferProofReceiverUserId(
			Set transferproofsForTransferProofReceiverUserId) {
		this.transferproofsForTransferProofReceiverUserId = transferproofsForTransferProofReceiverUserId;
	}

	public String getUserPayPassword() {
		return userPayPassword;
	}

	public void setUserPayPassword(String userPayPassword) {
		this.userPayPassword = userPayPassword;
	}

}