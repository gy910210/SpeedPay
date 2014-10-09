package com.speedpay.services;

import java.util.ArrayList;

import android.util.Pair;

import com.speedpay.net.Http;

public class InsertMessageService implements Runnable{

	
	private final static String uri = "insertMessageClient";
	private String user_phone;
	private String erCodeStr;
	private String type;
	
	
	public InsertMessageService(String user_phone, String erCodeStr, String type) {
		super();
		this.user_phone = user_phone;
		this.erCodeStr = erCodeStr;
		this.type = type;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
		params.add(new Pair<String, String>("user_phone", user_phone));
		params.add(new Pair<String, String>("erCodeStr", erCodeStr));
		params.add(new Pair<String, String>("type", type));
		
		Http http = new Http(uri);
		String body = http.doPost(params);
	}

}
