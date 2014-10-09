package speedpay.action.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import speedpay.dao.Consumptionproof;
import speedpay.dao.ConsumptionproofDAO;
import speedpay.dao.Purchasecontent;
import speedpay.dao.User;
import speedpay.dao.UserDAO;

public class InsertConsumptionProofClientAction {

	private String consumptionProof_shopId;
	private String consumptionProof_userId;
	private String consumptionProof_time;
	private String consumptionProof_sum;
	private String consumptionProof_cause;
	private UserDAO userDao;
	private ConsumptionproofDAO consumptionproofDao;

	public void doAction() {

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject json = new JSONObject();

		User shop = userDao.findById(Integer.parseInt(consumptionProof_shopId));
		User custom = userDao.findById(Integer
				.parseInt(consumptionProof_userId));
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			double temp = custom.getUserBalance()
					- Double.parseDouble(consumptionProof_sum);
			if (temp >= 0) {
				Consumptionproof consumptionproof = new Consumptionproof(
						custom, shop, dateFormat.parse(consumptionProof_time),
						Double.parseDouble(consumptionProof_sum),
						consumptionProof_cause);
				shop.setUserBalance(shop.getUserBalance()
						+ Double.parseDouble(consumptionProof_sum));
				custom.setUserBalance(temp);
				userDao.getHibernateTemplate().update(shop);
				userDao.getHibernateTemplate().update(custom);
				consumptionproofDao.save(consumptionproof);
				json.accumulate("success", true);
			} else {
				json.accumulate("success", false);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println(json.toString());
		out.flush();
		out.close();
	}

	public String getConsumptionProof_shopId() {
		return consumptionProof_shopId;
	}

	public void setConsumptionProof_shopId(String consumptionProof_shopId) {
		this.consumptionProof_shopId = consumptionProof_shopId;
	}

	public String getConsumptionProof_userId() {
		return consumptionProof_userId;
	}

	public void setConsumptionProof_userId(String consumptionProof_userId) {
		this.consumptionProof_userId = consumptionProof_userId;
	}

	public String getConsumptionProof_time() {
		return consumptionProof_time;
	}

	public void setConsumptionProof_time(String consumptionProof_time) {
		this.consumptionProof_time = consumptionProof_time;
	}

	public String getConsumptionProof_sum() {
		return consumptionProof_sum;
	}

	public void setConsumptionProof_sum(String consumptionProof_sum) {
		this.consumptionProof_sum = consumptionProof_sum;
	}

	public String getConsumptionProof_cause() {
		return consumptionProof_cause;
	}

	public void setConsumptionProof_cause(String consumptionProof_cause) {
		this.consumptionProof_cause = consumptionProof_cause;
	}

	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	public ConsumptionproofDAO getConsumptionproofDao() {
		return consumptionproofDao;
	}

	public void setConsumptionproofDao(ConsumptionproofDAO consumptionproofDao) {
		this.consumptionproofDao = consumptionproofDao;
	}

}
