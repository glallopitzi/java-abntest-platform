package net.jobrapido.abtest.services.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import net.jobrapido.abtest.services.HashingService;

public class HashingServiceMD5 implements HashingService {

	@Override
	public String getHashOfGivenString(String toBeHashed) {
		String clearName = toBeHashed;
		String result = "";
		try {
			byte[] bytesOfMessage = clearName.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] thedigest = md.digest(bytesOfMessage);
			result = thedigest.toString();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

}
