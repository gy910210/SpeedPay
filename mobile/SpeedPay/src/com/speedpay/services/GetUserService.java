package com.speedpay.services;

import java.util.ArrayList;

import org.json.JSONObject;

import android.util.Pair;

import com.speedpay.bean.User;
import com.speedpay.net.Http;

public class GetUserService 
{
	private final static String uri = "getUserClient";
	public User getUser(String user_id) {
		ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
		params.add(new Pair<String, String>("user_id", user_id));
		
		User user = null;

		Http http = new Http(uri);
		String body = http.doPost(params);

		System.out.println("body:" + body);
		
		try {
			if (null != body) {
				//JSONObject jsonObject1 = new JSONObject(body).getJSONObject("success");
					JSONObject jsonObject2 = new JSONObject(body);
					user = new User();
					user.setUser_id(jsonObject2.getInt("userId"));
					user.setUser_password(jsonObject2.getString("userPassword"));
					user.setUser_balance(jsonObject2.getDouble("userBalance"));
					user.setUser_bankcardNum(jsonObject2
							.getString("userBankcardNum"));
					user.setUser_name(jsonObject2.getString("userName"));
					user.setUser_phoneNum(jsonObject2.getString("userPhoneNum"));
					user.setUser_type(jsonObject2.getInt("userType"));
					user.setUser_isChecked(jsonObject2.getInt("userIsChecked"));
					user.setUser_level(jsonObject2.getInt("userLevel"));
					user.setUser_IDNum(jsonObject2.getString("userIDNum"));
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
}
