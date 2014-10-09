package speedpay.action.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import speedpay.bean.BorrowProof;
import speedpay.dao.Borrowproof;
import speedpay.dao.BorrowproofDAO;
import speedpay.dao.User;
import speedpay.dao.UserDAO;

public class MyBorrowClientAction {
	private String user_id;
	private UserDAO userDao;
	
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
		
		User user=userDao.findById(Integer.parseInt(user_id));
		Set set=user.getBorrowproofsForBorrowProofRepayUserId();
		ArrayList<BorrowProof> list=new ArrayList<BorrowProof>();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		for (Object borrowProof : set) {
			BorrowProof b=new BorrowProof(((Borrowproof)borrowProof).getBorrowProofId(), ((Borrowproof)borrowProof).getUserByBorrowProofBorrowUserId().getUserId(),  ((Borrowproof)borrowProof).getUserByBorrowProofRepayUserId().getUserId(),  dateFormat.format(((Borrowproof)borrowProof).getBorrowProofBorrowTime()), dateFormat.format(((Borrowproof)borrowProof).getBorrowProofRepayTime()),((Borrowproof)borrowProof).getBorrowProofIsRepayed() , ((Borrowproof)borrowProof).getBorrowProofSum());
			list.add(b);
			//User borrowUser=userDao.findById(((Borrowproof)borrowProof).getUserByBorrowProofBorrowUserId().getUserId());
			
		}
		
		JSONArray jsonArray = new JSONArray();
		jsonArray.addAll(list);
		
		
		System.out.println(jsonArray.toString());
		out.println(jsonArray.toString());
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
	
}
