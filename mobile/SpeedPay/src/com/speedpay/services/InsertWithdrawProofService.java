package com.speedpay.services;

import java.util.ArrayList;

import android.util.Pair;

import com.speedpay.net.Http;

public class InsertWithdrawProofService implements Runnable
{
	private final static String uri = "insertWithdrawProofClient";
	private String withdrawProof_userId;
	private String withdrawProof_atmID ;
	private String withdrawProof_time;
	private String withdrawProof_sum;
	
	
	
	public InsertWithdrawProofService(String withdrawProof_userId,
			String withdrawProof_atmID, String withdrawProof_time,
			String withdrawProof_sum) {
		super();
		this.withdrawProof_userId = withdrawProof_userId;
		this.withdrawProof_atmID = withdrawProof_atmID;
		this.withdrawProof_time = withdrawProof_time;
		this.withdrawProof_sum = withdrawProof_sum;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
		params.add(new Pair<String, String>("withdrawProof_userId", withdrawProof_userId));
		params.add(new Pair<String, String>("withdrawProof_atmID", withdrawProof_atmID));
		params.add(new Pair<String, String>("withdrawProof_time", withdrawProof_time));
		params.add(new Pair<String, String>("withdrawProof_sum", withdrawProof_sum));
		
		Http http = new Http(uri);
		String body = http.doPost(params);
	}
	
}
