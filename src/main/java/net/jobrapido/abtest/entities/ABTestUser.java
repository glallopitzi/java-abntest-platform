package net.jobrapido.abtest.entities;

public class ABTestUser {
	private String userId;
	private String hashKey;
	private String assignedTest;
	private String assignedTestCluster;
	
	
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
