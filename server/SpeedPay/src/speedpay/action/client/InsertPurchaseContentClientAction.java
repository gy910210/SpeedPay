package speedpay.action.client;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import speedpay.dao.Purchasecontent;
import speedpay.dao.PurchasecontentDAO;
import speedpay.dao.User;
import speedpay.dao.UserDAO;

public class InsertPurchaseContentClientAction {

	
	private String purchaseContent_marketId;
	private String purchaseContent_userId;
	private String purchaseContent_consumptionSum;
	private String purchaseContent_consumptionTime;
	private PurchasecontentDAO purchasecontentDao;
	private UserDAO userDao;
	
	public void doAction() {
		
		User custom=userDao.findById(Integer.parseInt(purchaseContent_userId));
		User market=userDao.findById(Integer.parseInt(purchaseContent_marketId));
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Purchasecontent purchasecontent=new Purchasecontent(market, custom, Double.parseDouble(purchaseContent_consumptionSum),dateFormat.parse(purchaseContent_consumptionTime), null);
			custom.setUserBalance(custom.getUserBalance()-Double.parseDouble(purchaseContent_consumptionSum));
			market.setUserBalance(market.getUserBalance()+Double.parseDouble(purchaseContent_consumptionSum));
			userDao.getHibernateTemplate().update(market);
			userDao.getHibernateTemplate().update(custom);
			purchasecontentDao.save(purchasecontent);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public String getPurchaseContent_marketId() {
		return purchaseContent_marketId;
	}


	public void setPurchaseContent_marketId(String purchaseContent_marketId) {
		this.purchaseContent_marketId = purchaseContent_marketId;
	}


	public String getPurchaseContent_userId() {
		return purchaseContent_userId;
	}


	public void setPurchaseContent_userId(String purchaseContent_userId) {
		this.purchaseContent_userId = purchaseContent_userId;
	}


	public String getPurchaseContent_consumptionSum() {
		return purchaseContent_consumptionSum;
	}


	public void setPurchaseContent_consumptionSum(
			String purchaseContent_consumptionSum) {
		this.purchaseContent_consumptionSum = purchaseContent_consumptionSum;
	}


	public String getPurchaseContent_consumptionTime() {
		return purchaseContent_consumptionTime;
	}


	public void setPurchaseContent_consumptionTime(
			String purchaseContent_consumptionTime) {
		this.purchaseContent_consumptionTime = purchaseContent_consumptionTime;
	}


	public PurchasecontentDAO getPurchasecontentDao() {
		return purchasecontentDao;
	}


	public void setPurchasecontentDao(PurchasecontentDAO purchasecontentDao) {
		this.purchasecontentDao = purchasecontentDao;
	}


	public UserDAO getUserDao() {
		return userDao;
	}


	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}
	
}
