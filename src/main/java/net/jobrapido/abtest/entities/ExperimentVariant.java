package net.jobrapido.abtest.entities;

public class ExperimentVariant {
	private long id;
	private long weight;
	
	private String abTestHashKey;
	
	private long rampUpTime;
	private long rampUpStep;
	private long rampUpInitialThreshold;
	private long rampUpFinalThreshold;
	
	public ExperimentVariant() {
		// TODO Auto-generated constructor stub
	}

	
	public ExperimentVariant(long id, long weight, String abTestHashhKey) {
		super();
		this.abTestHashKey = abTestHashhKey;
		this.id = id;
		this.weight = weight;
	}
	
	public ExperimentVariant(long id, long weight) {
		super();
		this.id = id;
		this.weight = weight;
	}

	
	@Override
	public boolean equals(Object obj) {
		return ( this.getAbTestHashKey().equals(((ExperimentVariant)obj).getAbTestHashKey()) && this.getId() == ((ExperimentVariant)obj).getId() );
	}
	
	@Override
	public String toString() {
		return "variant[id("+getId()+"), weigth("+getWeight()+"), abTestHashKey("+getAbTestHashKey()+")]";
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

	public String getAbTestHashKey() {
		return abTestHashKey;
	}



	public void setAbTestHashKey(String abTestHashKey) {
		this.abTestHashKey = abTestHashKey;
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
