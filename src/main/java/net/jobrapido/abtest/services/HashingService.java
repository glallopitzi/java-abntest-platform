package net.jobrapido.abtest.services;

import java.math.BigInteger;

public interface HashingService {

	public String getHashOfGivenString(String toBeHashed);
	
	public String makeXORBetween(String a, String b);
	
	public byte[] makeXORBetween(byte[] a, byte[] b);
	
	public long getLongFromString(String hashKey);
	
	public BigInteger toBigInteger(String hashKey);
	
	public String fromBigInteger(BigInteger hashKey);
}
