package net.jobrapido.abtest.services.impl;

import java.security.SecureRandom;
import java.util.Random;

import net.jobrapido.abtest.services.RandomizationService;

public class RandomizationServiceHashAndPartition implements
		RandomizationService {

	private static final Random RANDOM = new SecureRandom();
	
	@Override
	public double getRandomDouble() {
		return Math.random();
	}

	@Override
	public String getRandomString() {
		String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789+@";
		String pw = "";
		int randomStringLength = (int) (getRandomDouble() * 12) + 6;
		for ( int i=0; i < randomStringLength; i++ ) {
      		int index = (int)( RANDOM.nextDouble() * letters.length() );
      		pw += letters.substring( index, index + 1 );
  		}
  		return pw;
	}

	@Override
	public boolean getRandomBoolean() {
		return ( getRandomDouble() > 0.5 ) ? true : false ;
	}

}
