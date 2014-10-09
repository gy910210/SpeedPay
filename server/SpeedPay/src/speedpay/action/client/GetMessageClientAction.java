package speedpay.action.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;


import speedpay.dao.MessageDAO;
import speedpay.dao.User;
import speedpay.dao.UserDAO;
import speedpay.dao.Message;

public class GetMessageClientAction {
	
	String user_id;
	UserDAO userDao;
	MessageDAO messageDao;
	
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
		
		int num=0;
		PrintWriter se = null;
		try
		{
			//se = new PrintWriter(new File("E:\\messageNum.txt"));
			Scanner in=new Scanner(new FileReader("E:\\messageNum.txt"));
			
			in.hasNext();	
			num=Integer.parseInt(in.next());
			//System.out.println("num"+num);
			
			in.close();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		User user=userDao.findById(Integer.parseInt(user_id));
		Set messages=user.getMessages();
		
		
		JSONObject json=new JSONObject();
		
		if(messages.size()>num)
		{
			json.accumulate("isAdd", true);
			int an=0;
			for (Object object : messages) {
				if(((Message)object).getMessageId()>an)
				{
					an=((Message)object).getMessageId();
				}
			}
			
			Message temp=messageDao.findById(an);
			//MyMessage message=new MyMessage(((Message)temp).getMessageId(), ((Message)temp).getUser().getUserId(), ((Message)temp).getMessageContent(), ((Message)temp).getMessageType());
			json.accumulate("message_id", temp.getMessageId());
			json.accumulate("message_userId", temp.getUser().getUserId());
			json.accumulate("message_content", temp.getMessageContent());
			json.accumulate("message_type", temp.getMessageType());
			
			try {
				se = new PrintWriter(new File("E:\\messageNum.txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			se.println(messages.size());
			se.flush();
			se.close();
			
		}
		else
		{
			json.accumulate("isAdd", false);
		}
		
		System.out.println(json.toString());
		out.println(json.toString());
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

	public MessageDAO getMessageDao() {
		return messageDao;
	}

	public void setMessageDao(MessageDAO messageDao) {
		this.messageDao = messageDao;
	}
	
	
}
