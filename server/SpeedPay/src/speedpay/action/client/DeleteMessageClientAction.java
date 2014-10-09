package speedpay.action.client;

import speedpay.dao.Message;
import speedpay.dao.MessageDAO;

public class DeleteMessageClientAction {

	String message_id;
	MessageDAO messageDao;
	
	public void doAction() {
		Message m=messageDao.findById(Integer.parseInt(message_id));
		messageDao.delete(m);
	}

	public String getMessage_id() {
		return message_id;
	}

	public void setMessage_id(String message_id) {
		this.message_id = message_id;
	}

	public MessageDAO getMessageDao() {
		return messageDao;
	}

	public void setMessageDao(MessageDAO messageDao) {
		this.messageDao = messageDao;
	}
	
	
}
