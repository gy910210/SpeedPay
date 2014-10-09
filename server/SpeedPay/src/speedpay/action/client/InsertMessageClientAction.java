package speedpay.action.client;

import speedpay.dao.Message;
import speedpay.dao.MessageDAO;
import speedpay.dao.User;
import speedpay.dao.UserDAO;

public class InsertMessageClientAction {
	
	private String user_phone;
	private String erCodeStr;
	private String type;
	MessageDAO messageDao;
	UserDAO userDao;
	
	public void doAction() {
		User user=(User) userDao.findByUserPhoneNum(user_phone).get(0);
		Message message=new Message(user, erCodeStr, Integer.parseInt(type));
		messageDao.save(message);
		
	}
	public String getErCodeStr() {
		return erCodeStr;
	}
	public void setErCodeStr(String erCodeStr) {
		this.erCodeStr = erCodeStr;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public MessageDAO getMessageDao() {
		return messageDao;
	}
	public void setMessageDao(MessageDAO messageDao) {
		this.messageDao = messageDao;
	}
	public UserDAO getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	
	
}
