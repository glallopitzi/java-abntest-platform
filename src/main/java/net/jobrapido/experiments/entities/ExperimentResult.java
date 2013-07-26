package net.jobrapido.experiments.entities;

import java.util.List;

public class ExperimentResult {
	private String experimentHashKey;
	private boolean hasWinner;
	private ExperimentVariant winner;
	private List<ExperimentLog> metricsOfInterest;
	
	public String getExperimentHashKey() {
		return experimentHashKey;
	}
	public void setExperimentHashKey(String experimentHashKey) {
		this.experimentHashKey = experimentHashKey;
	}
	public boolean isHasWinner() {
		return hasWinner;
	}
	public void setHasWinner(boolean hasWinner) {
		this.hasWinner = hasWinner;
	}
	public ExperimentVariant getWinner() {
		return winner;
	}
	public void setWinner(ExperimentVariant winner) {
		this.winner = winner;
	}
	public List<ExperimentLog> getMetricsOfInterest() {
		return metricsOfInterest;
	}
	public void setMetricsOfInterest(List<ExperimentLog> metricsOfInterest) {
		this.metricsOfInterest = metricsOfInterest;
	}
	
}
