package speedpay.action.client;

import speedpay.dao.Borrowproof;
import speedpay.dao.BorrowproofDAO;
import speedpay.dao.Renewalapply;
import speedpay.dao.RenewalapplyDAO;

public class OKRenewalApplyClientAction {
	
	String renewalApply_id;
	RenewalapplyDAO renewalapplyDao;
	BorrowproofDAO borrowproofDao;
	
	
	public void doAction() {
		Renewalapply renewalapply=renewalapplyDao.findById(Integer.parseInt(renewalApply_id));
		renewalapply.setRenewalApplyIsChecked(1);
		renewalapplyDao.getHibernateTemplate().update(renewalapply);
		
		Borrowproof borrowproof=renewalapply.getBorrowproof();
		borrowproof.setBorrowProofRepayTime(renewalapply.getRenewalApplyTime());
		borrowproofDao.getHibernateTemplate().update(borrowproof);
		
	}


	public String getRenewalApply_id() {
		return renewalApply_id;
	}


	public void setRenewalApply_id(String renewalApply_id) {
		this.renewalApply_id = renewalApply_id;
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
	
	
}
