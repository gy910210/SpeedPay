package speedpay.action.client;

import java.text.SimpleDateFormat;

import speedpay.dao.Purchasecontent;
import speedpay.dao.User;
import speedpay.dao.UserDAO;
import speedpay.dao.Withdrawproof;
import speedpay.dao.WithdrawproofDAO;

public class InsertWithdrawProofClientAction 
{
	private String withdrawProof_userId;
	private String withdrawProof_atmID ;
	private String withdrawProof_time;
	private String withdrawProof_sum;
	private UserDAO userDao;
	private WithdrawproofDAO withdrawproofDao;
	
	public void doAction() {
		User custom=userDao.findById(Integer.parseInt(withdrawProof_userId));
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Withdrawproof withdrawproof=new Withdrawproof(custom, withdrawProof_atmID, dateFormat.parse(withdrawProof_time), Double.parseDouble(withdrawProof_sum));
			custom.setUserBalance(custom.getUserBalance()-Double.parseDouble(withdrawProof_sum));
			userDao.getHibernateTemplate().update(custom);
			withdrawproofDao.save(withdrawproof);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public String getWithdrawProof_userId() {
		return withdrawProof_userId;
	}

	public void setWithdrawProof_userId(String withdrawProof_userId) {
		this.withdrawProof_userId = withdrawProof_userId;
	}

	public String getWithdrawProof_atmID() {
		return withdrawProof_atmID;
	}

	public void setWithdrawProof_atmID(String withdrawProof_atmID) {
		this.withdrawProof_atmID = withdrawProof_atmID;
	}

	public String getWithdrawProof_time() {
		return withdrawProof_time;
	}

	public void setWithdrawProof_time(String withdrawProof_time) {
		this.withdrawProof_time = withdrawProof_time;
	}

	public String getWithdrawProof_sum() {
		return withdrawProof_sum;
	}

	public void setWithdrawProof_sum(String withdrawProof_sum) {
		this.withdrawProof_sum = withdrawProof_sum;
	}

	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	public WithdrawproofDAO getWithdrawproofDao() {
		return withdrawproofDao;
	}

	public void setWithdrawproofDao(WithdrawproofDAO withdrawproofDao) {
		this.withdrawproofDao = withdrawproofDao;
	}
}
