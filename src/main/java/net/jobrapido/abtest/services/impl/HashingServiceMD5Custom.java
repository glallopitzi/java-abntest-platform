package net.jobrapido.abtest.services.impl;

import java.io.UnsupportedEncodingException;

import net.jobrapido.abtest.services.HashingServiceBase;

public class HashingServiceMD5Custom extends HashingServiceBase {

	@Override
	public String getHashOfGivenString(String toBeHashed) {
		
		if ( ! initializeMessageDigest() ) return "";
		
		byte[] bytesOfMessage = null;
		try {
			bytesOfMessage = toBeHashed.getBytes("UTF-16");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			System.out.println("some error occurred during getting bytes from string to be hashed");
			return "";
		}
		
		byte messageDigest[] = getMd().digest(bytesOfMessage);

		StringBuffer hexString = new StringBuffer();
        for ( int i = 0; i < messageDigest.length; i++ ) {
            String hex = Integer.toHexString(0xFF & messageDigest[i]);
            if( hex.length() == 1 )
                hexString.append('0');

            hexString.append(hex);
        }

        return hexString.toString();
	}

}
