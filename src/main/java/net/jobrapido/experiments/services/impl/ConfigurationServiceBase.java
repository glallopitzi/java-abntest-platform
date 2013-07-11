package net.jobrapido.experiments.services.impl;

import java.util.ArrayList;
import java.util.List;

import net.jobrapido.experiments.entities.Experiment;
import net.jobrapido.experiments.entities.ExperimentVariant;
import net.jobrapido.experiments.services.ConfigurationService;

public abstract class ConfigurationServiceBase implements ConfigurationService {

	private List<Experiment> allConfiguredExperiments;
	
	@Override
	public List<Experiment> getAllConfiguredExperiments() {
		if ( allConfiguredExperiments == null ) { loadConfiguration(); }
		return this.allConfiguredExperiments;
	}

	@Override
	public List<Experiment> getAllActiveExperiments() {
		if ( allConfiguredExperiments == null ) { loadConfiguration(); }
		List<Experiment> allActiveExperiments = new ArrayList<Experiment>();
		for (Experiment experiment : this.allConfiguredExperiments) {
			if (experiment.isActive()) allActiveExperiments.add(experiment);
		}
		return allActiveExperiments;
	}

	
	@Override
	public long getTotalExperimentVariantsWeight(Experiment experiment) {
		long tot = 0;
		List<ExperimentVariant> allExperimentVariants = experiment.getVariants();
		for (ExperimentVariant experimentVariant : allExperimentVariants) {
			tot += experimentVariant.getWeight();
		}
		return tot;
	}
	
	@Override
	public long getTotalActiveExperimentsWeight() {
		long tot = 0;
		List<Experiment> allActiveExperiments = getAllActiveExperiments();
		for (Experiment experiment : allActiveExperiments) {
			tot += experiment.getExperimentWeight();
		}
		return tot;
	}
	
	
	

	@Override
	public abstract boolean flushConfiguration();

	@Override
	public boolean flushAndReloadConfiguration() {
		return flushConfiguration() && loadConfiguration();
	}

	@Override
	public abstract boolean loadConfiguration();

	@Override
	public boolean addExperiment(Experiment experiment) {
		if ( ! allConfiguredExperiments.contains( experiment ) ) {
			if ( allConfiguredExperiments.add( experiment ) ) {
				return flushAndReloadConfiguration();
			}
		}
		return false;
	}

	@Override
	public boolean removeExperiment(Experiment experiment) {
		if ( allConfiguredExperiments.contains( experiment ) ) {
			if ( allConfiguredExperiments.remove( experiment ) ) {
				return flushAndReloadConfiguration();
			}
		} 
		return false;
	}

	@Override
	public boolean updateExperiment(Experiment experiment) {
		int index = allConfiguredExperiments.indexOf(experiment);
		if ( index >= 0 ) {
			if ( allConfiguredExperiments.set( index, experiment ) != null ) {
				return flushAndReloadConfiguration();
			}
		}
		return false;
	}

	public void setAllConfiguredExperiments(List<Experiment> allConfiguredExperiments) {
		this.allConfiguredExperiments = allConfiguredExperiments;
	}

}
