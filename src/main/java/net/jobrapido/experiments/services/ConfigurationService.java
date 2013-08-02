package net.jobrapido.experiments.services;

import java.util.List;

import net.jobrapido.experiments.entities.Experiment;

public interface ConfigurationService {
	public List<Experiment> getAllConfiguredExperiments();
	public List<Experiment> getAllActiveExperiments();
	
	public long getTotalActiveExperimentsWeight();
	public long getTotalExperimentVariantsWeight(Experiment experiment);
	
	public boolean flushConfiguration();
	public boolean flushAndReloadConfiguration();
	public boolean loadConfiguration();
	public boolean deleteConfiguration();
	
	public boolean addExperiment(Experiment experiment);
	public boolean removeExperiment(Experiment experiment);
	public boolean updateExperiment(Experiment experiment);
}
