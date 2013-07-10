package net.jobrapido.experiments.services;

import net.jobrapido.experiments.entities.ExperimentResult;

public interface StatisticalService {
	public boolean isABTestResultStatisticalConfident(ExperimentResult abTestResult);
}
