package com.speedpay.services;

import java.util.ArrayList;

import org.json.JSONObject;

import com.speedpay.net.Http;

import android.os.Handler;
import android.os.Message;
import android.util.Pair;

public class CheckPaypasswordService implements Runnable{
	
	Handler handler;
	String paypassword;
	int user_id;
	private final static String uri = "checkPaypasswordClient";
	
	
	
	public CheckPaypasswordService(Handler handler, String paypassword,
			int user_id) {
		super();
		this.handler = handler;
		this.paypassword = paypassword;
		this.user_id = user_id;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
		params.add(new Pair<String, String>("paypassword", paypassword));
		params.add(new Pair<String, String>("user_id", String.valueOf(user_id)));
		
		Http http = new Http(uri);
		String body = http.doPost(params);
		
		System.out.println("body:" + body);
		
		boolean flag=false;
		try {
			if (null != body) {
				JSONObject jsonObject = new JSONObject(body);
				if((null!=jsonObject.get("flag"))&&jsonObject.getBoolean("flag"))
					flag=true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		Message m=handler.obtainMessage();
		m.obj=flag;
		handler.sendMessage(m);
		
	}
	
	
}
