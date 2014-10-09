package speedpay.bean;


public class BorrowProof 
{
	private int borrowProofId;
	private int borrowUserId;
	private int repayUserId;
	private String borrowTime;
	private String repayTime;
	private int isRepayed;
	private double borrowSum;
	public BorrowProof(int borrowProofId, int borrowUserId, int repayUserId,
			String borrowTime, String repayTime, int isRepayed, double borrowSum) {
		super();
		this.borrowProofId = borrowProofId;
		this.borrowUserId = borrowUserId;
		this.repayUserId = repayUserId;
		this.borrowTime = borrowTime;
		this.repayTime = repayTime;
		this.isRepayed = isRepayed;
		this.borrowSum = borrowSum;
	}
	public BorrowProof() {
		super();
	}
	public int getBorrowProofId() {
		return borrowProofId;
	}
	public void setBorrowProofId(int borrowProofId) {
		this.borrowProofId = borrowProofId;
	}
	public int getBorrowUserId() {
		return borrowUserId;
	}
	public void setBorrowUserId(int borrowUserId) {
		this.borrowUserId = borrowUserId;
	}
	public int getRepayUserId() {
		return repayUserId;
	}
	public void setRepayUserId(int repayUserId) {
		this.repayUserId = repayUserId;
	}
	public String getBorrowTime() {
		return borrowTime;
	}
	public void setBorrowTime(String borrowTime) {
		this.borrowTime = borrowTime;
	}
	public String getRepayTime() {
		return repayTime;
	}
	public void setRepayTime(String repayTime) {
		this.repayTime = repayTime;
	}
	public int getIsRepayed() {
		return isRepayed;
	}
	public void setIsRepayed(int isRepayed) {
		this.isRepayed = isRepayed;
	}
	public double getBorrowSum() {
		return borrowSum;
	}
	public void setBorrowSum(double borrowSum) {
		this.borrowSum = borrowSum;
	}
	@Override
	public String toString() {
		return "BorrowProof [borrowProofId=" + borrowProofId
				+ ", borrowUserId=" + borrowUserId + ", repayUserId="
				+ repayUserId + ", borrowTime=" + borrowTime + ", repayTime="
				+ repayTime + ", isRepayed=" + isRepayed + ", borrowSum="
				+ borrowSum + "]";
	}
	
	
}
