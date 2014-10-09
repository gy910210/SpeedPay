package com.speedpay.services;

import java.util.ArrayList;

import com.speedpay.net.Http;

import android.util.Pair;

public class InsertPurchaseContentService implements Runnable
{
	String purchaseContent_marketId;
	String purchaseContent_userId;
	String purchaseContent_consumptionSum;
	String purchaseContent_consumptionTime;
	
	private final static String uri = "insertPurchaseContentClient";
	
	public InsertPurchaseContentService() {
		super();
	}



	public InsertPurchaseContentService(String purchaseContent_marketId,
			String purchaseContent_userId,
			String purchaseContent_consumptionSum,
			String purchaseContent_consumptionTime) {
		super();
		this.purchaseContent_marketId = purchaseContent_marketId;
		this.purchaseContent_userId = purchaseContent_userId;
		this.purchaseContent_consumptionSum = purchaseContent_consumptionSum;
		this.purchaseContent_consumptionTime = purchaseContent_consumptionTime;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
		params.add(new Pair<String, String>("purchaseContent_marketId", purchaseContent_marketId));
		params.add(new Pair<String, String>("purchaseContent_userId", purchaseContent_userId));
		params.add(new Pair<String, String>("purchaseContent_consumptionSum", purchaseContent_consumptionSum));
		params.add(new Pair<String, String>("purchaseContent_consumptionTime", purchaseContent_consumptionTime));
		
		Http http = new Http(uri);
		String body = http.doPost(params);
	}
}
