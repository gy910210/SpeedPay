package speedpay.action.client;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import speedpay.dao.User;
import speedpay.dao.UserDAO;

public class GetUserClientAction {
	private UserDAO userDao;
	private String user_id;
	
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
		json.accumulate("success", true);
		//json.accumulate("user", user);
		json.accumulate("userId", user.getUserId());
		json.accumulate("userPassword", user.getUserPassword());
		json.accumulate("userBalance", user.getUserBalance());
		json.accumulate("userBankcardNum", user.getUserBankcardNum());
		json.accumulate("userName", user.getUserName());
		json.accumulate("userType", user.getUserType());
		json.accumulate("userIsChecked", user.getUserIsChecked());
		json.accumulate("userLevel", user.getUserLevel());
		json.accumulate("userPhoneNum", user.getUserPhoneNum());
		json.accumulate("userIDNum", user.getUserIdnum());
		
		out.println(json.toString());
		out.flush();
		out.close();
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
