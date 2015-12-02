package com.exfantasy.server.util;

import java.io.IOException;

import org.springframework.util.StringUtils;

import com.google.android.gcm.server.InvalidRequestException;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class GcmUtil {
	private static final String GCM_API_KEY = "AIzaSyAFEDkHc4KGtuq5Jjh88qP3y_Q8dKBf0aQ";

	public static void test() {
		Sender sender = new Sender(GCM_API_KEY);
		
		Message msg = new Message.Builder().addData("message", "Hello from Spring Server").build();
		
		try {
			Result result = sender.send(msg, "978444275273", 3);
			
			String errorCdName = result.getErrorCodeName();
			if (StringUtils.isEmpty(errorCdName)) {
				System.out.println("GCM notification is sent successfully, " + result.toString());
				return;
			}
			System.out.println("Error occurred while sending push notification :" + result.getErrorCodeName());

		} catch (InvalidRequestException e) {
            System.out.println("Invalid Request");
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
	}
}
