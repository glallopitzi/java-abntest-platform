package net.jobrapido.abtest.services.impl;

import net.jobrapido.abtest.services.HashingServiceBase;

public class HashingServiceMD5 extends HashingServiceBase {

	@Override
	public String getHashOfGivenString(String toBeHashed) {
		
		if ( ! initializeMessageDigest() ) return "";
		
		byte[] bytesOfMessage = getBytesArrayFromString(toBeHashed);
		if ( bytesOfMessage == null ) return "";
		
		byte[] thedigest = getMd().digest(bytesOfMessage);
		
		StringBuffer sb = new StringBuffer();
		for (byte b : thedigest) {
			sb.append(Integer.toHexString((int) (b & 0xff)));
		}
		
		return sb.toString();
	}

	@Override
	public String makeXORBetween(String a, String b) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] makeXORBetween(byte[] a, byte[] b) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
