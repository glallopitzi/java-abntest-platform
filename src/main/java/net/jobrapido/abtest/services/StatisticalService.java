package net.jobrapido.abtest.services;

import net.jobrapido.abtest.entities.ABTestResult;

public interface StatisticalService {
	public boolean isABTestResultStatisticalConfident(ABTestResult abTestResult);
}
