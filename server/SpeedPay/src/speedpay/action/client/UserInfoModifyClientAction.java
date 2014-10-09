package speedpay.action.client;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import speedpay.dao.User;
import speedpay.dao.UserDAO;

public class UserInfoModifyClientAction {
	private UserDAO userDao;
	private String user_id;
	private String password_modify;
	
	public void doAction() {
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		System.out.println("user_id:"+user_id+"password_modify"+password_modify);
		
		User user=userDao.findById(Integer.parseInt(user_id));
		
		user.setUserPassword(password_modify);
		userDao.getHibernateTemplate().update(user);
		
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONObject json=new JSONObject();
		json.accumulate("success", true);
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

	public String getPassword_modify() {
		return password_modify;
	}

	public void setPassword_modify(String password_modify) {
		this.password_modify = password_modify;
	}
	
}
