package net.jobrapido.experiments.entities;

public class ExperimentVariant {
	private long id;
	private long weight;
	
	private String experimentHashKey;
	
	private long rampUpTime;
	private long rampUpStep;
	private long rampUpInitialThreshold;
	private long rampUpFinalThreshold;
	
		
	public ExperimentVariant(long id, long weight, String experimentHashKey) {
		super();
		this.experimentHashKey = experimentHashKey;
		this.id = id;
		this.weight = weight;
	}
	

	
	@Override
	public boolean equals(Object obj) {
		return ( this.getExperimentHashKey().equals(((ExperimentVariant)obj).getExperimentHashKey()) && this.getId() == ((ExperimentVariant)obj).getId() );
	}
	
	@Override
	public String toString() {
		return "variant[id("+getId()+"), weigth("+getWeight()+"), experimentHashKey("+getExperimentHashKey()+")]";
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

	public String getExperimentHashKey() {
		return experimentHashKey;
	}

	public void setExperimentHashKey(String experimentHashKey) {
		this.experimentHashKey = experimentHashKey;
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
