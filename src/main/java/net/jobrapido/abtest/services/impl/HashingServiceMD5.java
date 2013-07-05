package net.jobrapido.abtest.services.impl;

import java.io.UnsupportedEncodingException;

import net.jobrapido.abtest.services.HashingServiceBase;

public class HashingServiceMD5 extends HashingServiceBase {

	@Override
	public String getHashOfGivenString(String toBeHashed) {
		
		if ( ! initializeMessageDigest() ) return "";
		
		String result = "";
		
		try {
			byte[] bytesOfMessage = toBeHashed.getBytes("UTF-16");
			byte[] thedigest = getMd().digest(bytesOfMessage);
			
			StringBuffer sb = new StringBuffer();
			for (byte b : thedigest) {
				sb.append(Integer.toHexString((int) (b & 0xff)));
			}
			System.out.println(sb.toString());
			result = sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			System.out.println("some error occurred during MD5 of " + toBeHashed);
			return "";
		}
		
		return result;
	}
	

}
