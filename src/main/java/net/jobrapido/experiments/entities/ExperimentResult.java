package net.jobrapido.experiments.entities;

import java.util.List;

public class ExperimentResult {
	private boolean hasWinner;
	private ExperimentVariant winner;
	private List<ExperimentLog> metricsOfInterest;
}
