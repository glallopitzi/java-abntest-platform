package net.jobrapido.abtest.entities;

import java.util.List;

public class ExperimentOEC {
	private String name;
	private List<ExperimentLog> metricsOfInterest;
	
	public double evaluate(){
		return 0d;
	}
}
