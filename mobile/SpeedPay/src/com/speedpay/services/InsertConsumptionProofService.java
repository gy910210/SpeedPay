package com.speedpay.services;

import java.util.ArrayList;

import org.json.JSONObject;

import com.speedpay.net.Http;

import android.os.Handler;
import android.os.Message;
import android.util.Pair;

public class InsertConsumptionProofService implements Runnable{

	private String consumptionProof_shopId;
	private String consumptionProof_userId;
	private String consumptionProof_time;
	private String consumptionProof_sum;
	private String consumptionProof_cause;
	Handler handler;
	
	private final static String uri = "insertConsumptionProofClient";
	
	
	public InsertConsumptionProofService(String consumptionProof_shopId,
			String consumptionProof_userId, String consumptionProof_time,
			String consumptionProof_sum, String consumptionProof_cause,
			Handler handler) {
		super();
		this.consumptionProof_shopId = consumptionProof_shopId;
		this.consumptionProof_userId = consumptionProof_userId;
		this.consumptionProof_time = consumptionProof_time;
		this.consumptionProof_sum = consumptionProof_sum;
		this.consumptionProof_cause = consumptionProof_cause;
		this.handler = handler;
	}




	@Override
	public void run() {
		// TODO Auto-generated method stub
		ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
		params.add(new Pair<String, String>("consumptionProof_shopId", consumptionProof_shopId));
		params.add(new Pair<String, String>("consumptionProof_userId", consumptionProof_userId));
		params.add(new Pair<String, String>("consumptionProof_time", consumptionProof_time));
		params.add(new Pair<String, String>("consumptionProof_sum", consumptionProof_sum));
		params.add(new Pair<String, String>("consumptionProof_cause", consumptionProof_cause));
		
		Message msg = handler.obtainMessage();
		Http http = new Http(uri);
		String body = http.doPost(params);
		System.out.println(body);
		
		try {
			if (null != body) {
				JSONObject jsonObject = new JSONObject(body);
				msg.obj=jsonObject.getBoolean("success");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		handler.sendMessage(msg);
	}
}
