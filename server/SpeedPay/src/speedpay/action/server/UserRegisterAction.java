package speedpay.action.server;
import java.util.List;

import speedpay.dao.User;
import speedpay.dao.UserDAO;
import com.opensymphony.xwork2.ActionSupport;
public class UserRegisterAction extends ActionSupport{

	/**用户注册
	 * @author Shang
	 * @TIME 2013/8/4
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private UserDAO userDao;
	private String password1;
	private String password2;
	private String userName;
	private String userPhoneNum;
	private String userIdnum;
	private String userBankcardNum;
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public String add() throws Exception{
		List <User> userlist = userDao.findByUserPhoneNum(userPhoneNum);
		if(userlist.size() == 0){
		user.setUserPhoneNum(userPhoneNum);
		}else{
		this.addActionError("此手机号已被注册，请重新输入");	
		return "fail";
		}
		if(password1.equals(password2)){
			user.setUserPassword(password1);
		}else{
			this.addActionError("密码输入不一致，请重新输入");
			return "fail";
		}
		user.setUserIsChecked(0);		//用户是否被审核 初始默认不开通
		user.setUserBalance(500.00);			//用户余额 初始为500。00    			
		user.setUserLevel(3);
		user.setUserBankcardNum(userBankcardNum);
		user.setUserPhoneNum(userPhoneNum);
		user.setUserIdnum(userIdnum);
		user.setUserName(userName);
		user.setUserPayPassword("111111");
		userDao.save(user);
		return "add";
	}
	public UserRegisterAction() {
		user=new User();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhoneNum() {
		return userPhoneNum;
	}

	public void setUserPhoneNum(String userPhoneNum) {
		this.userPhoneNum = userPhoneNum;
	}

	public String getUserIdnum() {
		return userIdnum;
	}

	public void setUserIdnum(String userIdnum) {
		this.userIdnum = userIdnum;
	}

	public String getUserBankcardNum() {
		return userBankcardNum;
	}

	public void setUserBankcardNum(String userBankcardNum) {
		this.userBankcardNum = userBankcardNum;
	}



	
}
