package speedpay.action.client;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import speedpay.dao.Borrowproof;
import speedpay.dao.BorrowproofDAO;
import speedpay.dao.Repayproof;
import speedpay.dao.RepayproofDAO;
import speedpay.dao.User;
import speedpay.dao.UserDAO;

public class InsertRepayProofClientAction {

	String borrowProofId;
	String isOnTime;
	String repayProof_sum;
	String trueRepayTime;
	BorrowproofDAO borrowproofDao;
	RepayproofDAO repayproofDao;
	UserDAO userDao;
	
	public void doAction() {
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Borrowproof borrowproof=borrowproofDao.findById(Integer.parseInt(borrowProofId));
		try {
			Repayproof repayproof=new Repayproof(borrowproof, Double.parseDouble(repayProof_sum), sdf.parse(trueRepayTime), Integer.parseInt(isOnTime));
			
			repayproofDao.save(repayproof);
			
			User borrowchu=borrowproof.getUserByBorrowProofBorrowUserId();
			User borrowru=borrowproof.getUserByBorrowProofRepayUserId();
			
			borrowchu.setUserBalance(borrowchu.getUserBalance()+Double.parseDouble(repayProof_sum));
			borrowru.setUserBalance(borrowru.getUserBalance()-Double.parseDouble(repayProof_sum));
			borrowproof.setBorrowProofIsRepayed(1);
			if(borrowru.getUserLevel()<5)
				borrowru.setUserLevel(borrowru.getUserLevel()+1);
			
			borrowproofDao.getHibernateTemplate().update(borrowproof);
			userDao.getHibernateTemplate().update(borrowru);
			userDao.getHibernateTemplate().update(borrowchu);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public String getBorrowProofId() {
		return borrowProofId;
	}

	public void setBorrowProofId(String borrowProofId) {
		this.borrowProofId = borrowProofId;
	}

	public String getIsOnTime() {
		return isOnTime;
	}

	public void setIsOnTime(String isOnTime) {
		this.isOnTime = isOnTime;
	}

	public String getRepayProof_sum() {
		return repayProof_sum;
	}

	public void setRepayProof_sum(String repayProof_sum) {
		this.repayProof_sum = repayProof_sum;
	}

	public String getTrueRepayTime() {
		return trueRepayTime;
	}

	public void setTrueRepayTime(String trueRepayTime) {
		this.trueRepayTime = trueRepayTime;
	}

	public BorrowproofDAO getBorrowproofDao() {
		return borrowproofDao;
	}

	public void setBorrowproofDao(BorrowproofDAO borrowproofDao) {
		this.borrowproofDao = borrowproofDao;
	}

	public RepayproofDAO getRepayproofDao() {
		return repayproofDao;
	}

	public void setRepayproofDao(RepayproofDAO repayproofDao) {
		this.repayproofDao = repayproofDao;
	}

	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}
	
}
