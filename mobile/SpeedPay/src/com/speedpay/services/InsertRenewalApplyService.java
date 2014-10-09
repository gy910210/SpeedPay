package com.speedpay.services;

import java.util.ArrayList;

import com.speedpay.net.Http;

import android.util.Pair;

public class InsertRenewalApplyService implements Runnable{

	
	int borrowProofId;
	String renewalApply_time;
	private final static String uri = "insertRenewalApplyClient";
	
	
	public InsertRenewalApplyService(int borrowProofId, String renewalApply_time) {
		super();
		this.borrowProofId = borrowProofId;
		this.renewalApply_time = renewalApply_time;
	}

	

	public InsertRenewalApplyService() {
		super();
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
		params.add(new Pair<String, String>("borrowProofId", String.valueOf(borrowProofId)));
		params.add(new Pair<String, String>("renewalApply_time", renewalApply_time));
		
		Http http = new Http(uri);
		String body = http.doPost(params);
	}

}
