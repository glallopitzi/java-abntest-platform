package net.jobrapido.experiments.entities;

import java.util.List;

public abstract class ExperimentOEC {
	private String name;
	private List<ExperimentLog> metricsOfInterest;
	
	public abstract double evaluate();
}
