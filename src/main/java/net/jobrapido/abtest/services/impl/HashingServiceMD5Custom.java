package net.jobrapido.abtest.services.impl;

import net.jobrapido.abtest.services.HashingServiceBase;

public class HashingServiceMD5Custom extends HashingServiceBase {

	@Override
	public String getHashOfGivenString(String toBeHashed) {
		if ( ! initializeMessageDigest() ) return "";
		
		byte[] bytesOfMessage = getBytesArrayFromString(toBeHashed);
		if ( bytesOfMessage == null ) return "";
		
		byte messageDigest[] = getMd().digest(bytesOfMessage);
		return getStringFromBytesArray(messageDigest);
	}

	@Override
	public String makeXORBetween(String a, String b) {
		
		if(a.length() != b.length()) return null;
		
		byte[] cBytes = makeXORBetween(getBytesArrayFromString(a), getBytesArrayFromString(b));
		
		String c = getStringFromBytesArray(cBytes);
		
		return c;
	}

	@Override
	public byte[] makeXORBetween(byte[] a, byte[] b) {
		
		if(a.length != b.length) return null;
		
		byte[] c = new byte[a.length]; 
		
		for(int i = 0; i < a.length; i++){
			c[i] = (byte) (a[i] ^ b[i]);
		}
		
		return c;
	}

	

}
