package net.jobrapido.abtest.services;

public interface HashingService {

	public String getHashOfGivenString(String toBeHashed);
	
	public String makeXORBetween(String a, String b);
	
	public byte[] makeXORBetween(byte[] a, byte[] b);
	
}
