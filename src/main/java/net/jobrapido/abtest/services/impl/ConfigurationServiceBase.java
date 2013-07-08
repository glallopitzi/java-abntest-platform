package net.jobrapido.abtest.services.impl;

import java.util.ArrayList;
import java.util.List;

import net.jobrapido.abtest.entities.ABTest;
import net.jobrapido.abtest.entities.ABTestCluster;
import net.jobrapido.abtest.services.ConfigurationService;

public abstract class ConfigurationServiceBase implements ConfigurationService {

	private List<ABTest> allConfiguredABTests;
	
	@Override
	public List<ABTest> getAllConfiguredABTests() {
		if ( allConfiguredABTests == null ) { loadConfiguration(); }
		return this.allConfiguredABTests;
	}

	@Override
	public List<ABTest> getAllActiveABTests() {
		if ( allConfiguredABTests == null ) { loadConfiguration(); }
		List<ABTest> allActiveABTests = new ArrayList<ABTest>();
		for (ABTest abTest : this.allConfiguredABTests) {
			if (abTest.isActive()) allActiveABTests.add(abTest);
		}
		return allActiveABTests;
	}

	
	@Override
	public long getTotalTestClustersWeight(ABTest abTest) {
		long tot = 0;
		List<ABTestCluster> allABTestClusters = abTest.getClusters();
		for (ABTestCluster abTestCluster : allABTestClusters) {
			tot += abTestCluster.getWeight();
		}
		return tot;
	}
	
	@Override
	public long getTotalActiveTestsWeight() {
		long tot = 0;
		List<ABTest> allActiveABTests = getAllActiveABTests();
		for (ABTest abTest : allActiveABTests) {
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
	public boolean addABTest(ABTest abtest) {
		if (allConfiguredABTests.add(abtest)){
			return flushAndReloadConfiguration();
		} 
		return false;
	}

	@Override
	public boolean removeABTest(ABTest abtest) {
		if (allConfiguredABTests.remove(abtest)){
			return flushAndReloadConfiguration();
		} 
		return false;
	}

	@Override
	public boolean updateABTest(ABTest abtest) {
		int index = allConfiguredABTests.indexOf(abtest);
		if ( index >= 0){
			if (allConfiguredABTests.set( index, abtest ) != null){
				return flushAndReloadConfiguration();
			}
		}
		return false;
	}

	public void setAllConfiguredABTests(List<ABTest> allConfiguredABTests) {
		this.allConfiguredABTests = allConfiguredABTests;
	}

}
