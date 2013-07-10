package net.jobrapido.experiments.services;

import java.math.BigInteger;

public interface HashingService {

	public String getHashOfGivenString(String toBeHashed);
	
	public BigInteger toBigInteger(String hashKey);
	
	public String fromBigInteger(BigInteger hashKey);
}
