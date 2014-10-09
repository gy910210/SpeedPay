package speedpay.action.client;

import java.text.SimpleDateFormat;

import speedpay.dao.Borrowproof;
import speedpay.dao.BorrowproofDAO;
import speedpay.dao.Transferproof;
import speedpay.dao.TransferproofDAO;
import speedpay.dao.User;
import speedpay.dao.UserDAO;

public class InsertBorrowProofClientAction {
	private String borrowProof_borrowUserId;
	private String borrowProof_repayUserId;
	private String borrowProof_borrowTime;
	private String borrowProof_sum;
	private String borrowProof_repayTime;
	private UserDAO userDao;
	private BorrowproofDAO borrowproofDao;
	
	
	public void doAction() {
		User borrow=userDao.findById(Integer.parseInt(borrowProof_borrowUserId));
		User repay=userDao.findById(Integer.parseInt(borrowProof_repayUserId));
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Borrowproof borrowproof=new Borrowproof(repay, borrow, dateFormat.parse(borrowProof_borrowTime), Double.parseDouble(borrowProof_sum),dateFormat.parse(borrowProof_repayTime) , 0, null, null);
			
			borrow.setUserBalance(borrow.getUserBalance()-Double.parseDouble(borrowProof_sum));
			userDao.getHibernateTemplate().update(borrow);
			
			repay.setUserBalance(repay.getUserBalance()+Double.parseDouble(borrowProof_sum));
			userDao.getHibernateTemplate().update(repay);
			borrowproofDao.save(borrowproof);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public String getBorrowProof_borrowUserId() {
		return borrowProof_borrowUserId;
	}


	public void setBorrowProof_borrowUserId(String borrowProof_borrowUserId) {
		this.borrowProof_borrowUserId = borrowProof_borrowUserId;
	}


	public String getBorrowProof_repayUserId() {
		return borrowProof_repayUserId;
	}


	public void setBorrowProof_repayUserId(String borrowProof_repayUserId) {
		this.borrowProof_repayUserId = borrowProof_repayUserId;
	}


	public String getBorrowProof_borrowTime() {
		return borrowProof_borrowTime;
	}


	public void setBorrowProof_borrowTime(String borrowProof_borrowTime) {
		this.borrowProof_borrowTime = borrowProof_borrowTime;
	}


	public String getBorrowProof_sum() {
		return borrowProof_sum;
	}


	public void setBorrowProof_sum(String borrowProof_sum) {
		this.borrowProof_sum = borrowProof_sum;
	}


	public String getBorrowProof_repayTime() {
		return borrowProof_repayTime;
	}


	public void setBorrowProof_repayTime(String borrowProof_repayTime) {
		this.borrowProof_repayTime = borrowProof_repayTime;
	}


	public UserDAO getUserDao() {
		return userDao;
	}


	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}


	public BorrowproofDAO getBorrowproofDao() {
		return borrowproofDao;
	}


	public void setBorrowproofDao(BorrowproofDAO borrowproofDao) {
		this.borrowproofDao = borrowproofDao;
	}
	
}
