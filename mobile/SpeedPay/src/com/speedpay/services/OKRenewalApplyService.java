package com.speedpay.services;

import java.util.ArrayList;

import com.speedpay.net.Http;

import android.util.Pair;

public class OKRenewalApplyService implements Runnable{

	int renewalApply_id;
	private final static String uri = "okRenewalApplyClient";
	
	
	public OKRenewalApplyService(int renewalApply_id) {
		super();
		this.renewalApply_id = renewalApply_id;
	}
	
	
	public OKRenewalApplyService() {
		super();
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
		params.add(new Pair<String, String>("renewalApply_id", String.valueOf(renewalApply_id)));
		
		Http http = new Http(uri);
		String body = http.doPost(params);
	}

}
