package com.speedpay.services;

import java.util.ArrayList;

import org.json.JSONObject;

import com.speedpay.bean.MyMessage;
import com.speedpay.net.Http;

import android.os.Handler;
import android.os.Message;
import android.util.Pair;

public class GetMessageService implements Runnable{
	
	
	boolean isRun;
	int user_id;
	Handler handler;
	private final static String uri = "getMessageClient";
	
	
	
	public GetMessageService(int user_id, Handler handler) {
		super();
		isRun=true;
		this.user_id = user_id;
		this.handler = handler;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (isRun) {
			Message msg = handler.obtainMessage();
			ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
			params.add(new Pair<String, String>("user_id", String.valueOf(user_id)));
			
			Http http = new Http(uri);
			String body = http.doPost(params);
			System.out.println("body:" + body);
			
			MyMessage message=null;
			try {
				JSONObject jsonObject = new JSONObject(body);
				if (null != body) {
					boolean flag = new JSONObject(body).getBoolean("isAdd");
					if(flag==false)
					{
						message=null;
					}
					else
					{
						message=new MyMessage(jsonObject.getInt("message_id"), jsonObject.getInt("message_userId"), jsonObject.getString("message_content"), jsonObject.getInt("message_type"));
					}
				}
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			msg.obj=message;
			handler.sendMessage(msg);
		}
	}
}
