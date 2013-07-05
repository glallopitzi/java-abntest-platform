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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] makeXORBetween(byte[] a, byte[] b) {
		// TODO Auto-generated method stub
		return null;
	}

}
