package speedpay.action.client;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import speedpay.dao.User;
import speedpay.dao.UserDAO;

public class IsUserClientAction {
	
	private String phone;
	private UserDAO userDao;
	
	public void doAction() {
		
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = null;
		try {
			out = response.getWriter();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONObject json=new JSONObject();
		List<User> usertableList = userDao.findByUserPhoneNum(phone);
		
		if (usertableList.size() == 0) 
		{
			json.accumulate("flag", false);
		} 
		else 
		{
			json.accumulate("flag", true);
		}
		
		out.println(json.toString());
		out.flush();
		out.close();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}
	
}
