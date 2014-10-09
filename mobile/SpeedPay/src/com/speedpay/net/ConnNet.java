package com.speedpay.net;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.http.client.methods.HttpPost;

public class ConnNet {
	private static final String BASE_URL = "http://192.168.1.118:8080/SpeedPay/";

	public HttpURLConnection getConn(String urlpath) {
		String finalurl = BASE_URL + urlpath;
		HttpURLConnection connection = null;
		try {
			URL url = new URL(finalurl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Charset", "UTF-8");
			connection.setRequestProperty("Connection", "Keep-Alive"); 
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return connection;

	}

	public HttpPost gethttPost(String uripath) {
		HttpPost httpPost = new HttpPost(BASE_URL + uripath);

		System.out.println("--->"+BASE_URL + uripath);
		return httpPost;
	}
}
