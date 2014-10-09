package com.speedpay.services;

import java.util.ArrayList;

import com.speedpay.net.Http;

import android.util.Pair;

public class InsertQrCodeService implements Runnable{

	String qrCode_content;
	private final static String uri = "insertQrCodeClient";
	
	public InsertQrCodeService(String qrCode_content) {
		super();
		this.qrCode_content = qrCode_content;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
		params.add(new Pair<String, String>("qrCode_content", String.valueOf(qrCode_content)));
		
		Http http = new Http(uri);
		String body = http.doPost(params);
	}

}
