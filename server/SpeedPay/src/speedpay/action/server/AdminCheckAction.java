package speedpay.action.server;
import com.opensymphony.xwork2.ActionSupport;
import speedpay.dao.User;
import speedpay.dao.UserDAO;
public class AdminCheckAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserDAO userDao;
	private User user;
	private int userId;
	
	@Override
	public String execute() throws Exception {
		user = userDao.findById(userId);
		user.setUserIsChecked(1);
		userDao.getHibernateTemplate().update(user);
		return SUCCESS;
	}

	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
}
