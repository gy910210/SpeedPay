package speedpay.action.client;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Size;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;

import speedpay.dao.User;
import speedpay.dao.UserDAO;

import com.opensymphony.xwork2.ActionSupport;

public class UserLoginClientAction {

	private static final long serialVersionUID = 1L;

	private String account;
	private String password;
	private UserDAO userDao;

	public void doAction() {
		System.out.println("--->execute()");
		System.out.println("account: " + account);
		System.out.println("password: " + password);

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = null;
		BufferedWriter se = null;
		try {
			out = response.getWriter();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JSONObject json = new JSONObject();
		List<User> usertableList = userDao.findByUserPhoneNum(account);
		if (usertableList.size() == 0) {
			System.out.println("--->用户名错误！");
			json.accumulate("success", "error");
		} else {
			User user = usertableList.get(0);
			if (user.getUserPassword().equals(password)) {
				
				if (user.getUserIsChecked() == 1) {
					json.accumulate("success", "success");

					json.accumulate("userId", user.getUserId());
					json.accumulate("userPassword", user.getUserPassword());
					json.accumulate("userBalance", user.getUserBalance());
					json.accumulate("userBankcardNum",
							user.getUserBankcardNum());
					json.accumulate("userName", user.getUserName());
					json.accumulate("userType", user.getUserType());
					json.accumulate("userIsChecked", user.getUserIsChecked());
					json.accumulate("userLevel", user.getUserLevel());
					json.accumulate("userPhoneNum", user.getUserPhoneNum());
					json.accumulate("userIDNum", user.getUserIdnum());
				} else {
					json.accumulate("success", "notCheck");
				}

				/*
				 * try { se = new BufferedWriter(new
				 * FileWriter("E:\\messageNum.txt"));
				 * se.write(String.valueOf(user.getMessages().size()));
				 * se.flush(); se.close(); } catch (Exception e) { // TODO
				 * Auto-generated catch block e.printStackTrace(); }
				 * System.out.println(user.getMessages().size());
				 */
			} else {
				System.out.println("--->密码错误！");
				json.accumulate("success", "error");
			}
		}

		out.println(json.toString());
		out.flush();
		out.close();

	}

	// @JSON(serialize=false)
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	// @JSON(serialize=false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// @JSON(serialize=false)
	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}
}
