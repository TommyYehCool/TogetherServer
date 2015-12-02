package com.exfantasy.server.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

public class GcmUtil {
	private static final String API_KEY = "AIzaSyAFEDkHc4KGtuq5Jjh88qP3y_Q8dKBf0aQ";

	public static void test() {
		JSONObject jGcmData = new JSONObject();
		JSONObject jData = new JSONObject();
		
		jData.put("message", "Hello from Tommy's Spring Server");
		
		jGcmData.put("to", "/topic/global");
		jGcmData.put("data", jData);
		
		try {
			// Crate connection to send GCM Message request
			URL url = new URL("https://android.googleapis.com/gcm/send");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestProperty("Authorization", "key=" + API_KEY);
	        conn.setRequestProperty("Content-Type", "application/json");
	        conn.setRequestMethod("POST");
	        conn.setDoOutput(true);
	        
	        // Send GCM message content.
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(jGcmData.toString().getBytes());

            // Read GCM response.
            InputStream inputStream = conn.getInputStream();
            String resp = IOUtils.toString(inputStream);
            System.out.println(resp);
            System.out.println("Check your device/emulator for notification or logcat for " +
                    "confirmation of the receipt of the GCM message.");
		} catch (IOException e) {
			System.out.println("Unable to send GCM message.");
            System.out.println("Please ensure that API_KEY has been replaced by the server " +
                    "API key, and that the device's registration token is correct (if specified).");
            e.printStackTrace();
		}
	}
}
