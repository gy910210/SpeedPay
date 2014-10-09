package speedpay.action.client;

import java.text.SimpleDateFormat;

import speedpay.dao.Borrowproof;
import speedpay.dao.BorrowproofDAO;
import speedpay.dao.Message;
import speedpay.dao.MessageDAO;
import speedpay.dao.Renewalapply;
import speedpay.dao.RenewalapplyDAO;
import speedpay.dao.User;
import speedpay.dao.UserDAO;

public class InsertRenewalApplyClientAction {
	
	String borrowProofId;
	String renewalApply_time;
	
	RenewalapplyDAO renewalapplyDao;
	BorrowproofDAO borrowproofDao;
	UserDAO userDao;
	MessageDAO messageDao;
	
	public void doAction() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Borrowproof borrowproof=borrowproofDao.findById(Integer.parseInt(borrowProofId));
		
		try {
			Renewalapply renewalapply=new Renewalapply(borrowproof, sdf.parse(renewalApply_time), 0);
			renewalapplyDao.save(renewalapply);
			
			//borrowproof.setBorrowProofRepayTime(sdf.parse(renewalApply_time));
			//sborrowproofDao.getHibernateTemplate().update(borrowproof);
			
			User user=borrowproof.getUserByBorrowProofBorrowUserId();
			String str="借款ID:"+borrowproof.getBorrowProofId()+" , 借入人:"+borrowproof.getUserByBorrowProofRepayUserId().getUserName()+"\n约定还款时间:"+sdf.format(borrowproof.getBorrowProofRepayTime())+"\n展期时间:"+renewalApply_time+"/"+renewalapply.getRenewalApplyId();
			Message message=new Message(user, str, 2);
			
			messageDao.save(message);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	public String getBorrowProofId() {
		return borrowProofId;
	}

	public void setBorrowProofId(String borrowProofId) {
		this.borrowProofId = borrowProofId;
	}

	public String getRenewalApply_time() {
		return renewalApply_time;
	}

	public void setRenewalApply_time(String renewalApply_time) {
		this.renewalApply_time = renewalApply_time;
	}

	public RenewalapplyDAO getRenewalapplyDao() {
		return renewalapplyDao;
	}

	public void setRenewalapplyDao(RenewalapplyDAO renewalapplyDao) {
		this.renewalapplyDao = renewalapplyDao;
	}

	public BorrowproofDAO getBorrowproofDao() {
		return borrowproofDao;
	}

	public void setBorrowproofDao(BorrowproofDAO borrowproofDao) {
		this.borrowproofDao = borrowproofDao;
	}

	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	public MessageDAO getMessageDao() {
		return messageDao;
	}

	public void setMessageDao(MessageDAO messageDao) {
		this.messageDao = messageDao;
	}
	
	
}
