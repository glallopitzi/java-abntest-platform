package net.jobrapido.abtest.entities;

import java.util.List;

public class ABTestOEC {
	private String name;
	private List<ABTestLog> metricsOfInterest;
	
	public double evaluate(){
		return 0d;
	}
}
