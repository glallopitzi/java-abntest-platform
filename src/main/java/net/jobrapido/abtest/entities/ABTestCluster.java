package net.jobrapido.abtest.entities;

public class ABTestCluster {
	private long id;
	private long weight;
	
	private long rampUpTime;
	private long rampUpStep;
	private long rampUpInitialThreshold;
	private long rampUpFinalThreshold;
	
	public ABTestCluster() {
		// TODO Auto-generated constructor stub
	}

	
	
	public ABTestCluster(long id, long weight) {
		super();
		this.id = id;
		this.weight = weight;
	}

	
	@Override
	public String toString() {
		return "cluster[id("+getId()+"), weigth("+getWeight()+")]";
	}
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getWeight() {
		return weight;
	}

	public void setWeight(long weight) {
		this.weight = weight;
	}

	public long getRampUpTime() {
		return rampUpTime;
	}

	public void setRampUpTime(long rampUpTime) {
		this.rampUpTime = rampUpTime;
	}

	public long getRampUpStep() {
		return rampUpStep;
	}

	public void setRampUpStep(long rampUpStep) {
		this.rampUpStep = rampUpStep;
	}

	public long getRampUpInitialThreshold() {
		return rampUpInitialThreshold;
	}

	public void setRampUpInitialThreshold(long rampUpInitialThreshold) {
		this.rampUpInitialThreshold = rampUpInitialThreshold;
	}

	public long getRampUpFinalThreshold() {
		return rampUpFinalThreshold;
	}

	public void setRampUpFinalThreshold(long rampUpFinalThreshold) {
		this.rampUpFinalThreshold = rampUpFinalThreshold;
	}
	
}
