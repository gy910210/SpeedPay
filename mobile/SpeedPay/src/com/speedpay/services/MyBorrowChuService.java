package com.speedpay.services;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Handler;
import android.os.Message;
import android.util.Pair;

import com.speedpay.bean.BorrowProof;
import com.speedpay.net.Http;

public class MyBorrowChuService implements Runnable{
	private final static String uri = "myBorrowChuClient";
	int user_id;
	Handler handler;
	
	
	
	public MyBorrowChuService() {
		super();
	}
	

	public MyBorrowChuService(int user_id, Handler handler) {
		super();
		this.user_id = user_id;
		this.handler = handler;
	}

	public void run() {
		// TODO Auto-generated method stub
		ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
		params.add(new Pair<String, String>("user_id", String.valueOf(user_id)));
		Http http = new Http(uri);
		String body = http.doPost(params);

		System.out.println("body:" + body);
		
		ArrayList<BorrowProof> list=new ArrayList<BorrowProof>();
		try {
			
			if (null != body) {
				JSONArray array = new JSONArray(body); 
				for(int i=0;i<array.length();i++)
				{
					JSONObject json=array.getJSONObject(i);
					BorrowProof borrowProof=new BorrowProof(json.getInt("borrowProofId"), json.getInt("borrowUserId"), json.getInt("repayUserId"), json.getString("borrowTime"), json.getString("repayTime"), json.getInt("isRepayed"), json.getDouble("borrowSum"));
					list.add(borrowProof);
				}
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		Message msg=handler.obtainMessage();
		msg.obj=list;
		handler.sendMessage(msg);
	}
}
