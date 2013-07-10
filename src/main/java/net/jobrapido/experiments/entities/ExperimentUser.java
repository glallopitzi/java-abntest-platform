package net.jobrapido.experiments.entities;


public class ExperimentUser {
	private String userId;
	private String hashKey;
	private String assignedExperiment;
	private String assignedExperimentVariant;
	
	
	public ExperimentUser(){}
	
	public ExperimentUser(String userId, String hashKey){
		this.userId = userId;
		this.hashKey = hashKey;
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
	public String getAssignedExperiment() {
		return assignedExperiment;
	}
	public void setAssignedExperiment(String assignedExperiment) {
		this.assignedExperiment = assignedExperiment;
	}
	public String getAssignedExperimentVariant() {
		return assignedExperimentVariant;
	}
	public void setAssignedExperimentVariant(String assignedExperimentVariant) {
		this.assignedExperimentVariant = assignedExperimentVariant;
	}
	
}
