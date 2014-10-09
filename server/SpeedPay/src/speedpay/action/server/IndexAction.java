package speedpay.action.server;

import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport{

	/**首页 
	 * @author Shang
	 * @Time 2013/8/4
	 */
	private static final long serialVersionUID = 3663475117085395994L;

	@Override
	public String execute() throws Exception {
		return "success";
	}
}
