package net.jobrapido.abtest.services;

import net.jobrapido.abtest.entities.ExperimentResult;

public interface StatisticalService {
	public boolean isABTestResultStatisticalConfident(ExperimentResult abTestResult);
}
