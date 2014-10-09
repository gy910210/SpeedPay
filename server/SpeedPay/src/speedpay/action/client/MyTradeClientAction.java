package speedpay.action.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import speedpay.dao.Consumptionproof;
import speedpay.dao.Purchasecontent;
import speedpay.dao.Transferproof;
import speedpay.dao.User;
import speedpay.dao.UserDAO;
import speedpay.dao.Withdrawproof;

public class MyTradeClientAction {
	private String user_id;
	private UserDAO userDao;
	private String query_type;
	
	public void doAction() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		User user=userDao.findById(Integer.parseInt(user_id));
		ArrayList<ArrayList<String>> list=new ArrayList<ArrayList<String>>();
		for(int i=0;i<12;i++)
		{
			list.add(new ArrayList<String>());
		}
		System.out.println(list.size());
		switch(Integer.parseInt(query_type))
		{
		case 0:
			
			Set set=user.getPurchasecontentsForPurchaseContentUserId();
			for (Object item : set) {
				Purchasecontent temp=(Purchasecontent)item;
				System.out.println(temp.getPurchaseContentConsumptionTime().getMonth());
				list.get(temp.getPurchaseContentConsumptionTime().getMonth()).add("消费超市: "+temp.getUserByPurchaseContentMarketId().getUserName()+"  消费时间: "+sdf.format(temp.getPurchaseContentConsumptionTime())+"  消费金额:  "+String.valueOf(temp.getPurchaseContentConsumptionSum()));
			}
			break;
		case 1:
			Set set1=user.getConsumptionproofsForConsumptionProofUserId();
			for (Object item : set1) {
				Consumptionproof temp=(Consumptionproof)item;
				list.get(temp.getConsumptionProofTime().getMonth()).add("消费商户: "+temp.getUserByConsumptionProofShopId().getUserName()+"  消费时间: "+sdf.format(temp.getConsumptionProofTime())+"  消费金额:  "+String.valueOf(temp.getConsumptionProofSum())+"\n消费原因:  "+temp.getConsumptionProofCause());
			}
			break;
		case 2:
			Set setT=user.getTransferproofsForTransferProofTransferUserId();
			Set setR=user.getTransferproofsForTransferProofReceiverUserId();
			for(Object item : setT)
			{
				Transferproof temp=(Transferproof)item;
				list.get(temp.getTransferProofTime().getMonth()).add("类型:  转出"+"  转入人:  "+temp.getUserByTransferProofReceiverUserId().getUserName()+"  转账金额:  "+temp.getTransferProofSum()+"  转账时间:  "+sdf.format(temp.getTransferProofTime())+"\n转账原因:  "+temp.getTransferProofCause());
				
			}
			for(Object item : setR)
			{
				Transferproof temp=(Transferproof)item;
				list.get(temp.getTransferProofTime().getMonth()).add("类型:  转入"+"  转出人:  "+temp.getUserByTransferProofTransferUserId().getUserName()+"  转账金额:  "+temp.getTransferProofSum()+"  转账时间:  "+sdf.format(temp.getTransferProofTime())+"\n转账原因:  "+temp.getTransferProofCause());
			}
			break;
		case 3:
			Set set2=user.getWithdrawproofs();
			for(Object item : set2)
			{
				Withdrawproof temp=(Withdrawproof)item;
				list.get(temp.getWithdrawProofTime().getMonth()).add("取款ATM:  "+temp.getWithdrawProofAtmId()+"  取款金额:  "+temp.getWithdrawProofSum()+"  取款时间:  "+sdf.format(temp.getWithdrawProofTime()));
			}
			break;
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

	public String getQuery_type() {
		return query_type;
	}

	public void setQuery_type(String query_type) {
		this.query_type = query_type;
	}
	
}
