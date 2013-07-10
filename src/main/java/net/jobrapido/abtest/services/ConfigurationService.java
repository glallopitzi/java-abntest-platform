package net.jobrapido.abtest.services;

import java.util.List;

import net.jobrapido.abtest.entities.Experiment;

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
