package com.exfantasy.server.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * https://github.com/googlesamples/google-services/blob/master/android/gcm/gcmsender/src/main/java/gcm/play/android/samples/com/gcmsender/GcmSender.java 
 * 
 * @author Tommy
 */
public class GcmUtil {

	private static final Logger logger = LoggerFactory.getLogger(GcmUtil.class);
	
	private static final String API_KEY = "AIzaSyAFEDkHc4KGtuq5Jjh88qP3y_Q8dKBf0aQ";

	public static boolean sendGcmMessage(String topic, String message) {
		logger.info("Prepare to send message: <" + message + "> to Topic: <" + topic + ">...");
		
		JSONObject jGcmData = new JSONObject();
		JSONObject jData = new JSONObject();
		
		jData.put("message", message);
		
		jGcmData.put("to", "/topics/" + topic);
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

            logger.info("Send GCM message succeed, GCM response: " + resp);

            return true;
		} catch (IOException e) {
			logger.error("Unable to send GCM message, err-msg: <" + e.getMessage() + ">", e);
			return false;
		}
	}
}
