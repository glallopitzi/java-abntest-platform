package net.jobrapido.experiments.services;

import java.util.List;

import net.jobrapido.experiments.entities.Experiment;

public interface ConfigurationService {
	public List<Experiment> getAllConfiguredABTests();
	public List<Experiment> getAllActiveABTests();
	
	public long getTotalActiveTestsWeight();
	public long getTotalTestClustersWeight(Experiment abTest);
	
	public boolean flushConfiguration();
	public boolean flushAndReloadConfiguration();
	public boolean loadConfiguration();
	
	public boolean addABTest(Experiment abtest);
	public boolean removeABTest(Experiment abtest);
	public boolean updateABTest(Experiment abtest);
}
