package net.jobrapido.experiments.entities;

import java.util.List;

public abstract class ExperimentOEC {
	
	private String name;
	
	private List<ExperimentLog> metricsOfInterest;
	
	public abstract double evaluate();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ExperimentLog> getMetricsOfInterest() {
		return metricsOfInterest;
	}

	public void setMetricsOfInterest(List<ExperimentLog> metricsOfInterest) {
		this.metricsOfInterest = metricsOfInterest;
	}
	
}
