package com.speedpay.services;

import java.util.ArrayList;

import com.speedpay.net.Http;

import android.util.Pair;

public class InsertRepayProofService implements Runnable{

	int borrowProofId;
	int isOnTime;
	double repayProof_sum;
	String trueRepayTime;
	private final static String uri = "insertRepayProofClient";
	
	public InsertRepayProofService(int borrowProofId, int isOnTime,
			double repayProof_sum, String trueRepayTime) {
		super();
		this.borrowProofId = borrowProofId;
		this.isOnTime = isOnTime;
		this.repayProof_sum = repayProof_sum;
		this.trueRepayTime = trueRepayTime;
	}

	
	public InsertRepayProofService() {
		super();
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
		params.add(new Pair<String, String>("borrowProofId", String.valueOf(borrowProofId)));
		params.add(new Pair<String, String>("isOnTime", String.valueOf(isOnTime)));
		params.add(new Pair<String, String>("repayProof_sum", String.valueOf(repayProof_sum)));
		params.add(new Pair<String, String>("trueRepayTime", trueRepayTime));
		
		Http http = new Http(uri);
		String body = http.doPost(params);
	}

}
