package com.speedpay.services;

import java.util.ArrayList;

import com.speedpay.net.Http;

import android.util.Pair;

public class DeleteMessageService implements Runnable{

	int message_id;
	private final static String uri = "deleteMessageClient";
	
	
	public DeleteMessageService(int message_id) {
		super();
		this.message_id = message_id;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
		params.add(new Pair<String, String>("message_id", String.valueOf(message_id)));
		Http http = new Http(uri);
		String body = http.doPost(params);
	}
	
}
