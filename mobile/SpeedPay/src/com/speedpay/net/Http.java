package com.speedpay.net;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.util.Pair;

public class Http {
	private String uri = null;

	public Http(String uri) {
		super();
		this.uri = uri;
	}

	public Http() {
		super();
	}

	@SuppressWarnings("finally")
	public String doPost(ArrayList<Pair<String, String>> params) {
		System.out.println("--->doPost");
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		NameValuePair nameValuePair = null;

		for (Pair<String, String> pair : params) {
			nameValuePair = new BasicNameValuePair(pair.first, pair.second);
			nameValuePairs.add(nameValuePair);
		}

		HttpEntity requestHttpEntity = null;
		HttpPost httpPost = null;
		HttpClient client = null;
		HttpResponse response = null;
		HttpEntity responseHttpEntity = null;
		String result = null;

		// requestHttpEntity=new UrlEncodedFormEntity(nameValuePairs);
		try {
			ConnNet connNet=new ConnNet();
			httpPost=connNet.gethttPost(uri);
			
			//httpPost=new HttpPost("http://192.168.0.23:8080/SpeedPay/userloginclient");
			
			requestHttpEntity = new UrlEncodedFormEntity(nameValuePairs,
					HTTP.UTF_8);
			httpPost.setEntity(requestHttpEntity);
			client = new DefaultHttpClient();
			response = client.execute(httpPost);
			
			System.out.println("--->HttpStatus"+response.getStatusLine().getStatusCode());
			
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

				responseHttpEntity = response.getEntity();
				if (null != responseHttpEntity) {
					result = EntityUtils.toString(responseHttpEntity, "utf-8");
				}
				else
				{
					System.out.println("responseHttpEntity==null");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
