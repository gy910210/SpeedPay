package com.speedpay.services;

import java.util.ArrayList;

import android.util.Pair;

import com.speedpay.net.Http;

public class InsertBorrowProofService implements Runnable{

	private final static String uri = "insertBorrowProofClient";
	private String borrowProof_borrowUserId;
	private String borrowProof_repayUserId;
	private String borrowProof_borrowTime;
	private String borrowProof_sum;
	private String borrowProof_repayTime;
	
	
	
	public InsertBorrowProofService(String borrowProof_borrowUserId,
			String borrowProof_repayUserId, String borrowProof_borrowTime,
			String borrowProof_sum, String borrowProof_repayTime) {
		super();
		this.borrowProof_borrowUserId = borrowProof_borrowUserId;
		this.borrowProof_repayUserId = borrowProof_repayUserId;
		this.borrowProof_borrowTime = borrowProof_borrowTime;
		this.borrowProof_sum = borrowProof_sum;
		this.borrowProof_repayTime = borrowProof_repayTime;
	}



	@Override
	public void run() {
		
		System.out.println("borrowProof_repayTime"+borrowProof_repayTime);
		// TODO Auto-generated method stub
		ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
		params.add(new Pair<String, String>("borrowProof_borrowUserId", borrowProof_borrowUserId));
		params.add(new Pair<String, String>("borrowProof_repayUserId", borrowProof_repayUserId));
		params.add(new Pair<String, String>("borrowProof_borrowTime", borrowProof_borrowTime));
		params.add(new Pair<String, String>("borrowProof_sum", borrowProof_sum));
		params.add(new Pair<String, String>("borrowProof_repayTime", borrowProof_repayTime));
		
		Http http = new Http(uri);
		String body = http.doPost(params);
	}

}
