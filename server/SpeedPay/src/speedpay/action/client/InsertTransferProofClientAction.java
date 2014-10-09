package speedpay.action.client;

import java.text.SimpleDateFormat;

import speedpay.dao.Transferproof;
import speedpay.dao.TransferproofDAO;
import speedpay.dao.User;
import speedpay.dao.UserDAO;
import speedpay.dao.Withdrawproof;

public class InsertTransferProofClientAction {
	
	private String transferProof_transferUserId;
	private String transferProof_receiverUserId;
	private String transferProof_time;
	private String transferProof_sum;
	private String transferProof_cause;
	private UserDAO userDao;
	private TransferproofDAO transferproofDao;
	
	public void doAction() {
		User transfer=userDao.findById(Integer.parseInt(transferProof_transferUserId));
		User receiver=userDao.findById(Integer.parseInt(transferProof_receiverUserId));
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Transferproof transferproof=new Transferproof(transfer, receiver, dateFormat.parse(transferProof_time), Double.parseDouble(transferProof_sum),transferProof_cause );
			transfer.setUserBalance(transfer.getUserBalance()-Double.parseDouble(transferProof_sum));
			userDao.getHibernateTemplate().update(transfer);
			
			receiver.setUserBalance(receiver.getUserBalance()+Double.parseDouble(transferProof_sum));
			userDao.getHibernateTemplate().update(receiver);
			transferproofDao.save(transferproof);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getTransferProof_transferUserId() {
		return transferProof_transferUserId;
	}

	public void setTransferProof_transferUserId(String transferProof_transferUserId) {
		this.transferProof_transferUserId = transferProof_transferUserId;
	}

	public String getTransferProof_receiverUserId() {
		return transferProof_receiverUserId;
	}

	public void setTransferProof_receiverUserId(String transferProof_receiverUserId) {
		this.transferProof_receiverUserId = transferProof_receiverUserId;
	}

	public String getTransferProof_time() {
		return transferProof_time;
	}

	public void setTransferProof_time(String transferProof_time) {
		this.transferProof_time = transferProof_time;
	}

	public String getTransferProof_sum() {
		return transferProof_sum;
	}

	public void setTransferProof_sum(String transferProof_sum) {
		this.transferProof_sum = transferProof_sum;
	}

	public String getTransferProof_cause() {
		return transferProof_cause;
	}

	public void setTransferProof_cause(String transferProof_cause) {
		this.transferProof_cause = transferProof_cause;
	}

	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	public TransferproofDAO getTransferproofDao() {
		return transferproofDao;
	}

	public void setTransferproofDao(TransferproofDAO transferproofDao) {
		this.transferproofDao = transferproofDao;
	}

}
