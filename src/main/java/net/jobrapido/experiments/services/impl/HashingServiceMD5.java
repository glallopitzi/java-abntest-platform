package net.jobrapido.experiments.services.impl;

import java.math.BigInteger;

import net.jobrapido.experiments.services.HashingService;

import org.apache.commons.codec.digest.DigestUtils;

public class HashingServiceMD5 implements HashingService {

	@Override
	public String getHashOfGivenString(String toBeHashed) {
		return DigestUtils.md5Hex(toBeHashed);
	}

	@Override
	public BigInteger toBigInteger(String hashKey){
		return new BigInteger(DigestUtils.md5(hashKey));
	}

	@Override
	public String fromBigInteger(BigInteger hashKey){
		return DigestUtils.md5Hex(hashKey.toByteArray());
	}
	

}
