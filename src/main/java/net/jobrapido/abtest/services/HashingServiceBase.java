package net.jobrapido.abtest.services;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

public abstract class HashingServiceBase implements HashingService {

	private MessageDigest md;
	
	@Override
	public abstract String getHashOfGivenString(String toBeHashed);
	
	@Override
	public abstract String makeXORBetween(String a, String b);

	@Override
	public abstract byte[] makeXORBetween(byte[] a, byte[] b);
	
	
	
	@Override
	public long getLongFromString(String hashKey) {
		byte[] bytes = null;
		try {
			bytes = Hex.decodeHex(hashKey.toCharArray());
		} catch (DecoderException e) {
			e.printStackTrace();
		}
		return ByteBuffer.wrap(bytes).getLong();
	}
	
	
	
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
	
	protected byte[] getBytesArrayFromString(String toByteArray){
		byte[] result = null;
		
		try {
			result = toByteArray.getBytes("UTF-16");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			System.out.println("some error occurred during getting bytes from string: " + toByteArray);
		}
		
		return result;
	}
	
	
	protected String getStringFromBytesArray(byte[] messageDigest){
		StringBuffer hexString = new StringBuffer();
        for ( int i = 0; i < messageDigest.length; i++ ) {
            String hex = Integer.toHexString(0xFF & messageDigest[i]);
            if( hex.length() == 1 )
                hexString.append('0');

            hexString.append(hex);
        }
                
        return hexString.toString();
	}
	
	
	
	
	
	public MessageDigest getMd() {
		return md;
	}

	public void setMd(MessageDigest md) {
		this.md = md;
	}

}
