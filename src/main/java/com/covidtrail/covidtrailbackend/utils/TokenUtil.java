package com.covidtrail.covidtrailbackend.utils;

import java.util.Base64;

public class TokenUtil {

	private TokenUtil() {}

	public static String generateBy(String username, String password) {
		return Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
	}
	
}
