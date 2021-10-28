package mz.co.witchallenge.app.ws.shared;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class MyUtils {
	
	private final static String ALPHABET = "0123456789ABCDEFGHJIKLMNOPQRSTUVXWYZabcdefghijklmnopqrstuvxwzy";
	private final static Random RANDOM = new SecureRandom();
	
	public String generatedString(int length) {
		return generatedRandomString(length);
	}

	private String generatedRandomString(int length) {
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i <= length; i++)
			builder.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		return builder.toString();
	}

}
