package net.jobrapido.abtest.services.impl;

import java.util.ArrayList;
import java.util.List;

import net.jobrapido.abtest.entities.Experiment;
import net.jobrapido.abtest.entities.ExperimentVariant;
import net.jobrapido.abtest.services.ConfigurationService;

public abstract class ConfigurationServiceBase implements ConfigurationService {

	private List<Experiment> allConfiguredABTests;
	
	@Override
	public List<Experiment> getAllConfiguredABTests() {
		if ( allConfiguredABTests == null ) { loadConfiguration(); }
		return this.allConfiguredABTests;
	}

	@Override
	public List<Experiment> getAllActiveABTests() {
		if ( allConfiguredABTests == null ) { loadConfiguration(); }
		List<Experiment> allActiveABTests = new ArrayList<Experiment>();
		for (Experiment abTest : this.allConfiguredABTests) {
			if (abTest.isActive()) allActiveABTests.add(abTest);
		}
		return allActiveABTests;
	}

	
	@Override
	public long getTotalTestClustersWeight(Experiment abTest) {
		long tot = 0;
		List<ExperimentVariant> allABTestClusters = abTest.getClusters();
		for (ExperimentVariant abTestCluster : allABTestClusters) {
			tot += abTestCluster.getWeight();
		}
		return tot;
	}
	
	@Override
	public long getTotalActiveTestsWeight() {
		long tot = 0;
		List<Experiment> allActiveABTests = getAllActiveABTests();
		for (Experiment abTest : allActiveABTests) {
			tot += abTest.getTestWeight();
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
	public boolean addABTest(Experiment abtest) {
		if (allConfiguredABTests.add(abtest)){
			return flushAndReloadConfiguration();
		} 
		return false;
	}

	@Override
	public boolean removeABTest(Experiment abtest) {
		if (allConfiguredABTests.remove(abtest)){
			return flushAndReloadConfiguration();
		} 
		return false;
	}

	@Override
	public boolean updateABTest(Experiment abtest) {
		int index = allConfiguredABTests.indexOf(abtest);
		if ( index >= 0){
			if (allConfiguredABTests.set( index, abtest ) != null){
				return flushAndReloadConfiguration();
			}
		}
		return false;
	}

	public void setAllConfiguredABTests(List<Experiment> allConfiguredABTests) {
		this.allConfiguredABTests = allConfiguredABTests;
	}

}
