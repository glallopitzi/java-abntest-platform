package net.jobrapido.experiments.services.impl;

import java.security.SecureRandom;
import java.util.Random;

import net.jobrapido.experiments.services.RandomizationService;

public class RandomizationServiceHashAndPartition implements
		RandomizationService {

	private static final Random RANDOM = new SecureRandom();
	private String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789";
	private String[] emailDomanis = {"google.com", "yahoo.com", "live.com", "email.com", "outlook.com", "jobrapido.com"};
	
	
	@Override
	public double getRandomDouble() {
		return Math.random();
	}

	@Override
	public String getRandomString() {
		StringBuilder sb = new StringBuilder(20);
		int randomStringLength = (int) (getRandomDouble() * 12) + 6;
		for ( int i=0; i < randomStringLength; i++ ) {
      		int index = (int)( RANDOM.nextDouble() * letters.length() );
      		sb.append(letters.substring( index, index + 1 ));
  		}
  		return sb.toString();
	}

	@Override
	public String getRandomString(int minLength, int maxLength) {
		StringBuilder sb = new StringBuilder(maxLength);
		int randomStringLength = (int) (getRandomDouble() * (maxLength - minLength)) + minLength;
		for ( int i=0; i < randomStringLength; i++ ) {
      		int index = (int)( RANDOM.nextDouble() * letters.length() );
      		sb.append(letters.substring( index, index + 1 ));
  		}
  		return sb.toString();
	}
	
	@Override
	public boolean getRandomBoolean() {
		return ( getRandomDouble() >= 0.5 ) ? true : false ;
	}

	@Override
	public String getRandomEmailAddress() {
		String emailNamePart = getRandomString(2, 12);
		int emailProviderRandomIndex = (int) getRandomLong(0, emailDomanis.length);
		return emailNamePart + "@" + emailDomanis[emailProviderRandomIndex];
	}

	@Override
	public long getRandomLong() {
		return (long) (getRandomDouble() * ( 100000 ) );
	}

	@Override
	public long getRandomLong(long min, long max) {
		return (long) (getRandomDouble() * ( max - min )) + min;
	}



}
