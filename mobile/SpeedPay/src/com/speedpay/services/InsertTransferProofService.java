package com.speedpay.services;

import java.util.ArrayList;

import android.util.Pair;

import com.speedpay.net.Http;

public class InsertTransferProofService implements Runnable
{
	private final static String uri = "insertTransferProofClient";
	private String transferProof_transferUserId;
	private String transferProof_receiverUserId;
	private String transferProof_time;
	private String transferProof_sum;
	private String transferProof_cause;
	
	
	public InsertTransferProofService(String transferProof_transferUserId,
			String transferProof_receiverUserId, String transferProof_time,
			String transferProof_sum, String transferProof_cause) {
		super();
		this.transferProof_transferUserId = transferProof_transferUserId;
		this.transferProof_receiverUserId = transferProof_receiverUserId;
		this.transferProof_time = transferProof_time;
		this.transferProof_sum = transferProof_sum;
		this.transferProof_cause = transferProof_cause;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
		params.add(new Pair<String, String>("transferProof_transferUserId", transferProof_transferUserId));
		params.add(new Pair<String, String>("transferProof_receiverUserId", transferProof_receiverUserId));
		params.add(new Pair<String, String>("transferProof_time", transferProof_time));
		params.add(new Pair<String, String>("transferProof_sum", transferProof_sum));
		params.add(new Pair<String, String>("transferProof_cause", transferProof_cause));
		
		Http http = new Http(uri);
		String body = http.doPost(params);
	}
	
}
