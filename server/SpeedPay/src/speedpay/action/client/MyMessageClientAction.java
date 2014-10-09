package speedpay.action.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import speedpay.bean.BorrowProof;
import speedpay.bean.MyMessage;
import speedpay.dao.Message;
import speedpay.dao.User;
import speedpay.dao.UserDAO;

public class MyMessageClientAction {
	private String user_id;
	private UserDAO userDao;

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
		
		User user=userDao.findById(Integer.parseInt(user_id));
		Set set=user.getMessages();
		
		ArrayList<MyMessage> list=new ArrayList<MyMessage>();
		for (Object temp : set) {
			MyMessage message=new MyMessage(((Message)temp).getMessageId(), ((Message)temp).getUser().getUserId(), ((Message)temp).getMessageContent(), ((Message)temp).getMessageType());
			list.add(message);
		}
		
		JSONArray jsonArray = new JSONArray();
		jsonArray.addAll(list);
		
		
		System.out.println(jsonArray.toString());
		out.println(jsonArray.toString());
		out.flush();
		out.close();
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

}
