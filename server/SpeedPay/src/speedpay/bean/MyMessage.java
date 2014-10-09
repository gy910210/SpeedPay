package speedpay.bean;


public class MyMessage 
{
	public static final int TYPE_TRANSFER=0;
	public static final int TYPE_BORROW=1;
	public static final int TYPE_RENEW=2;
	public static final int TYPE_REPAY=3;
	
	private int message_id;
	private int message_userId;
	private String message_content;
	private int message_type;
	
	public MyMessage(int message_id, int message_userId, String message_content,
			int message_type) {
		super();
		this.message_id = message_id;
		this.message_userId = message_userId;
		this.message_content = message_content;
		this.message_type = message_type;
	}

	public MyMessage() {
		super();
	}

	public int getMessage_id() {
		return message_id;
	}

	public void setMessage_id(int message_id) {
		this.message_id = message_id;
	}

	public int getMessage_userId() {
		return message_userId;
	}

	public void setMessage_userId(int message_userId) {
		this.message_userId = message_userId;
	}

	public String getMessage_content() {
		return message_content;
	}

	public void setMessage_content(String message_content) {
		this.message_content = message_content;
	}

	public int getMessage_type() {
		return message_type;
	}

	public void setMessage_type(int message_type) {
		this.message_type = message_type;
	}

	@Override
	public String toString() {
		return "Message [message_id=" + message_id + ", message_userId="
				+ message_userId + ", message_content=" + message_content
				+ ", message_type=" + message_type + "]";
	}
	
	
	
}
