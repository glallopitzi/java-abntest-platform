package net.jobrapido.abtest.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class HashingServiceBase implements HashingService {

	private MessageDigest md;
	
	@Override
	public abstract String getHashOfGivenString(String toBeHashed);
	
	protected boolean initializeMessageDigest() {
		if ( getMd() == null ){
			try {
				setMd( MessageDigest.getInstance("MD5") );
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
				System.out.println("some error occurred during initialization of MD5 algorithm");
				return false;
			}
		}
		return true;
		
	}
	
	public MessageDigest getMd() {
		return md;
	}

	public void setMd(MessageDigest md) {
		this.md = md;
	}

}
