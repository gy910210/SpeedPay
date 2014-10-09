package speedpay.action.server;

import java.util.List;

import javax.jms.Session;

import org.apache.struts2.ServletActionContext;

import speedpay.dao.User;
import speedpay.dao.UserDAO;
import com.opensymphony.xwork2.ActionSupport;
public class UserLoginAction extends ActionSupport{

	/**用户登录 
	 * @author Shang
	 * @Time 2013/8/4
	 */
	private static final long serialVersionUID = 1L;
	
	private String account;	 //手机号
	private String password;
	private UserDAO userDao;
	
	@Override
	public void validate() {
		this.clearActionErrors();
		if(account == null ||password == null || password.equals("")|| account.equals("")){
			this.addActionError("用户名或密码不能为空！");
		}
	}
	
	public String execute(){
		//System.out.println(account);
		@SuppressWarnings("unchecked")
		List <User> usertableList = userDao.findByUserPhoneNum(account); 
		if(usertableList.size() == 0){
			this.addActionError("用户名错误！");
			return "fail";
		}else if(usertableList.get(0).getUserPassword().equals(password)){
			ServletActionContext.getRequest().getSession().setAttribute("user", usertableList.get(0));
			return SUCCESS;
		}else
		{
			this.addActionError("密码错误");
			return "fail";
		}
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}


	
	
}
