package com.speedpay.services;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.speedpay.bean.BorrowProof;
import com.speedpay.bean.MyMessage;
import com.speedpay.net.Http;

import android.os.Handler;
import android.os.Message;
import android.util.Pair;

public class MyMessageService implements Runnable{
	
	private final static String uri = "myMessageClient";
	int user_id;
	Handler handler;
	
	
	public MyMessageService(int user_id, Handler handler) {
		super();
		this.user_id = user_id;
		this.handler = handler;
	}

	
	public MyMessageService() {
		super();
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
		params.add(new Pair<String, String>("user_id", String.valueOf(user_id)));
		Http http = new Http(uri);
		String body = http.doPost(params);
		System.out.println("body:" + body);
		
		ArrayList<MyMessage> list=new ArrayList<MyMessage>();
		try {
			
			if (null != body) {
				JSONArray array = new JSONArray(body); 
				for(int i=0;i<array.length();i++)
				{
					JSONObject json=array.getJSONObject(i);
					MyMessage message=new MyMessage(json.getInt("message_id"), json.getInt("message_userId"), json.getString("message_content"), json.getInt("message_type"));
					list.add(message);
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
