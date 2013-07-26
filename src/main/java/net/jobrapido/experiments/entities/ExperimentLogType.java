package net.jobrapido.experiments.entities;

public enum ExperimentLogType {
	
	PAGEVIEW, SUBSCRIPTION, CLICK, DISPLAY, NEWVISIT;
	
	@Override
	public String toString() {
		return super.toString().toLowerCase();
	}
}
