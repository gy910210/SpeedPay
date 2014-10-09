package speedpay.action.server;

import java.util.List;
import org.apache.struts2.ServletActionContext;
import speedpay.dao.Admin;
import speedpay.dao.AdminDAO;
import speedpay.dao.User;
import speedpay.dao.UserDAO;
import com.opensymphony.xwork2.ActionSupport;
import speedpay.services.PageDivision;
public class AdminLoginAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Admin admin;
	private AdminDAO adminDao;
	private String account;
	private String password;
	private List <User> userlist;
	private UserDAO userDao;
	private int userId;
	private User user;
	private PageDivision pd;
	private int pageNum=1;
	
	@Override
	public String execute() throws Exception {
		pd=new PageDivision(userDao.findAll().size(), 6, pageNum);
		userlist =userDao.findAll();
		return SUCCESS;
	}
	public String login(){
		admin = adminDao.findById(account);
		if (admin == null){
			this.addActionError("管理员不存在");
			return "fail";
		}else if(admin.getAdminPassword().equals(password)){
			
			return "login";
		}else{
			this.addActionError("密码输入错误");
			return "fail";
		}
	}
	
	public String check(){
		user = userDao.findById(userId);
		user.setUserIsChecked(1);
		userDao.getHibernateTemplate().update(user);
		return "check";
		
	}
	
	
	
	public PageDivision getPd() {
		return pd;
	}
	public void setPd(PageDivision pd) {
		this.pd = pd;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
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

	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public AdminDAO getAdminDao() {
		return adminDao;
	}
	public void setAdminDao(AdminDAO adminDao) {
		this.adminDao = adminDao;
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

	public List <User> getUserlist() {
		return userlist;
	}

	public void setUserlist(List <User> userlist) {
		this.userlist = userlist;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
