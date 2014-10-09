package speedpay.action.server;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import speedpay.dao.User;
import speedpay.dao.UserDAO;
import com.opensymphony.xwork2.ActionSupport;
public class UserCenterAction extends ActionSupport{
	/**用户中心
	 * @author Shang
	 * @Time 2013/8/6
	 */
	private static final long serialVersionUID = 6526532041298859770L;
	private User user;
	private UserDAO userDao;
	private String type;
	private String check;
	private String banknum;
	private String oldpd;
	private String newpd1;
	private String userPhoneNum;
	private String banknum1;
	private String oldpaypd;
	private String newpaypd1;
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		User user=(User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(user.getUserType()==0)
		{
			type="顾客";
		}
		else
		{
			if(user.getUserType()==1)
			{
				type="超市";
			}
			else
			{
				type="商户";
			}
		}
		
		if (user.getUserIsChecked() == 0){
			check= "未被审核通过";
		}else{
			check ="已通过审核";
		}
		
		if(user.getUserBankcardNum() == null)
		{
			banknum = "未绑定银行卡";
			
		}else{
			banknum = user.getUserBankcardNum();
		}
		return SUCCESS;
	}
	
	public String password(){  //修改密码
		List <User> userlist = userDao.findByUserPhoneNum(userPhoneNum);
		return "password";
	}
	
	public String passworded(){
		User user=(User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if (!(user.getUserPassword().equalsIgnoreCase(oldpd))){
		this.addActionError("您输入的原始密码不正确，请重新输入");
		return "fail";
		}else{
			user.setUserPassword(newpd1);
			userDao.getHibernateTemplate().update(user);
			return "passworded";
		}
	}
	
	public String info(){		//修改绑定银行卡
		return "info";
	}
	public String infoed(){
		User user=(User) ServletActionContext.getRequest().getSession().getAttribute("user");
		user.setUserBankcardNum(banknum1);
		userDao.getHibernateTemplate().update(user);
		return "infoed";
	}
	
	public String paypsd(){
		User user=(User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if (!(user.getUserPayPassword().equalsIgnoreCase(oldpaypd))){
		this.addActionError("您输入的原始密码不正确，请重新输入");
		return "fail";
		}else{
			user.setUserPayPassword(newpaypd1);
			userDao.getHibernateTemplate().update(user);
			return "paypsd";
		}
		
	}
	public String getBanknum1() {
		return banknum1;
	}

	public void setBanknum1(String banknum1) {
		this.banknum1 = banknum1;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCheck() {
		return check;
	}
	public void setCheck(String check) {
		this.check = check;
	}

	public String getBanknum() {
		return banknum;
	}

	public void setBanknum(String banknum) {
		this.banknum = banknum;
	}

	public String getOldpd() {
		return oldpd;
	}

	public void setOldpd(String oldpd) {
		this.oldpd = oldpd;
	}

	public String getNewpd1() {
		return newpd1;
	}

	public void setNewpd1(String newpd1) {
		this.newpd1 = newpd1;
	}

	public String getUserPhoneNum() {
		return userPhoneNum;
	}

	public void setUserPhoneNum(String userPhoneNum) {
		this.userPhoneNum = userPhoneNum;
	}

	public String getOldpaypd() {
		return oldpaypd;
	}

	public void setOldpaypd(String oldpaypd) {
		this.oldpaypd = oldpaypd;
	}

	public String getNewpaypd1() {
		return newpaypd1;
	}

	public void setNewpaypd1(String newpaypd1) {
		this.newpaypd1 = newpaypd1;
	}
	
}
