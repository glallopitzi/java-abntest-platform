package net.jobrapido.abtest.entities;

import java.nio.ByteBuffer;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

public class ABTestUser {
	private String userId;
	private String hashKey;
	private String assignedTest;
	private String assignedTestCluster;
	
	

	public long toLong(){
		byte[] bytes = null;
		try {
			bytes = Hex.decodeHex(getHashKey().toCharArray());
		} catch (DecoderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ByteBuffer.wrap(bytes).getLong();
	}
	
	
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getHashKey() {
		return hashKey;
	}
	public void setHashKey(String hashKey) {
		this.hashKey = hashKey;
	}
	public String getAssignedTest() {
		return assignedTest;
	}
	public void setAssignedTest(String assignedTest) {
		this.assignedTest = assignedTest;
	}
	public String getAssignedTestCluster() {
		return assignedTestCluster;
	}
	public void setAssignedTestCluster(String assignedTestCluster) {
		this.assignedTestCluster = assignedTestCluster;
	}
	
}
