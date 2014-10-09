package com.speedpay.services;

import java.util.ArrayList;

import org.json.JSONObject;

import com.speedpay.net.Http;

import android.util.Pair;

public class IsUserService 
{
	private final static String uri = "isUserClient";
	public boolean isUser(String phone)
	{
		System.out.println("--->IsUserService in");
		ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
		params.add(new Pair<String, String>("phone", phone));
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
		return flag;
	}
}
