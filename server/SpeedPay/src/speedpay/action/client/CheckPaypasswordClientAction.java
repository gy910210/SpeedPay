package speedpay.action.client;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import speedpay.dao.User;
import speedpay.dao.UserDAO;

public class CheckPaypasswordClientAction {
	private String paypassword;
	private String user_id;
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
		
		User user=userDao.findById(Integer.parseInt(user_id));
		if(user.getUserPayPassword().equals(paypassword))
		{
			json.accumulate("flag", true);
		}
		else
		{
			json.accumulate("flag", false);
		}
		
		out.println(json.toString());
		out.flush();
		out.close();
		
	}

	public String getPaypassword() {
		return paypassword;
	}

	public void setPaypassword(String paypassword) {
		this.paypassword = paypassword;
	}

	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	
}
