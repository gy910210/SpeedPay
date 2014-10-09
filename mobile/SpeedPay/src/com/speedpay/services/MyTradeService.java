package com.speedpay.services;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.speedpay.bean.MyMessage;
import com.speedpay.net.Http;

import android.os.Handler;
import android.os.Message;
import android.util.Pair;

public class MyTradeService implements Runnable {

	private int user_id;
	private int query_type;
	Handler handler;
	private final static String uri = "myTradeClient";

	public MyTradeService() {
		super();
	}

	public MyTradeService(int user_id, int query_type, Handler handler) {
		super();
		this.user_id = user_id;
		this.query_type = query_type;
		this.handler = handler;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
		params.add(new Pair<String, String>("user_id", String.valueOf(user_id)));
		params.add(new Pair<String, String>("query_type", String
				.valueOf(query_type)));
		Http http = new Http(uri);
		String body = http.doPost(params);
		System.out.println("body:" + body);

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		for(int i=0;i<12;i++)
		{
			list.add(new ArrayList<String>());
		}
		try {

			if (null != body) {
				JSONArray array = new JSONArray(body);
				for (int i = 0; i < 12; i++) {
					JSONArray temp = array.getJSONArray(i);
					ArrayList<String> strs=new ArrayList<String>();
					for(int j=0;j<temp.length();j++)
					{
						String json=temp.getString(j);
						System.out.println(json);
						strs.add(json);
					}
					list.add(strs);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Message msg = handler.obtainMessage();
		msg.obj=list;
		System.out.println("list--"+list);
		System.out.println("list.size()--"+list.size());
		handler.sendMessage(msg);
	}

}
