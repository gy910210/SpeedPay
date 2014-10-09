package com.speedpay.services;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Pair;

import com.speedpay.bean.User;
import com.speedpay.net.Http;
import com.speedpay.utils.Session;

public class LoginService {
	// private User user;
	private final static String uri = "userloginclient";

	@SuppressWarnings("finally")
	public String login(String account, String password) {

		System.out.println("--->LoginService in");
		ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
		params.add(new Pair<String, String>("account", account));
		params.add(new Pair<String, String>("password", password));

		User user = null;
		String message = null;
		Http http = new Http(uri);
		String body = http.doPost(params);

		System.out.println("body:" + body);

		try {
			if (null != body) {
				// JSONObject jsonObject1 = new
				// JSONObject(body).getJSONObject("success");
				String flag = new JSONObject(body).getString("success");
				if (flag.equals("notCheck")) {
					message = "您未通过审核!";
				} else {

					if (flag.equals("error")) {
						message = "您的用户名或密码错误!";
					} else {
						JSONObject jsonObject2 = new JSONObject(body);
						user = new User();
						user.setUser_id(jsonObject2.getInt("userId"));
						user.setUser_password(jsonObject2
								.getString("userPassword"));
						user.setUser_balance(jsonObject2
								.getDouble("userBalance"));
						user.setUser_bankcardNum(jsonObject2
								.getString("userBankcardNum"));
						user.setUser_name(jsonObject2.getString("userName"));
						user.setUser_phoneNum(jsonObject2
								.getString("userPhoneNum"));
						user.setUser_type(jsonObject2.getInt("userType"));
						user.setUser_isChecked(jsonObject2
								.getInt("userIsChecked"));
						user.setUser_level(jsonObject2.getInt("userLevel"));
						user.setUser_IDNum(jsonObject2.getString("userIDNum"));
						System.out.println("--->LoginService" + user);
						
						System.out.println("----"+user);
						Session session=Session.getSession();
						session.put("user", user);
						message="您的身份验证成功!";
					}
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
}
