package com.auctions.system.module.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class MailRestClient {
	
	public static void sendMail(String address) throws IOException{
		String param = "address=" + address;
		URL url = new URL("http://localhost:8143/sendMail");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setDoOutput(true);
		DataOutputStream os = new DataOutputStream(conn.getOutputStream());
				os.write(param.getBytes(StandardCharsets.UTF_8));
				os.flush();
		conn.getInputStream();
		conn.disconnect();
	}
	
}
