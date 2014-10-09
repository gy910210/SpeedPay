package com.speedpay.services;

import java.util.ArrayList;

import org.json.JSONObject;

import com.speedpay.net.Http;

import android.os.Handler;
import android.os.Message;
import android.util.Pair;

public class CheckQrCodeService implements Runnable{

	Handler handler;
	String qrCode_content;
	private final static String uri = "checkQrCodeClient";
	
	public CheckQrCodeService(Handler handler, String qrCode_content) {
		super();
		this.handler = handler;
		this.qrCode_content = qrCode_content;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
		params.add(new Pair<String, String>("qrCode_content", String.valueOf(qrCode_content)));
		
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
