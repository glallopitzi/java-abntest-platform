package net.jobrapido.abtest.services.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import net.jobrapido.abtest.services.HashingService;

public class HashingServiceMD5 implements HashingService {

	private MessageDigest md;
	
	@Override
	public String getHashOfGivenString(String toBeHashed) {
		if ( getMd() == null ){
			try {
				setMd( MessageDigest.getInstance("MD5") );
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
				System.out.println("some error occurred during initialization of MD5 algorithm");
			}
		}
		
		String clearName = toBeHashed;
		String result = "";
		
		try {
			byte[] bytesOfMessage = clearName.getBytes("UTF-8");
			byte[] thedigest = getMd().digest(bytesOfMessage);
			
			StringBuffer sb = new StringBuffer();
			for (byte b : thedigest) {
				sb.append(Integer.toHexString((int) (b & 0xff)));
			}
			
			result = sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			System.out.println("some error occurred during MD5 of " + clearName);
			return "";
		}
		
		return result;
	}

	public MessageDigest getMd() {
		return md;
	}

	public void setMd(MessageDigest md) {
		this.md = md;
	}

}
