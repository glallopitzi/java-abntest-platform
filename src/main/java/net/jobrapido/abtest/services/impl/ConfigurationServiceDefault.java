package net.jobrapido.abtest.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;

import net.jobrapido.abtest.entities.ABTest;
import net.jobrapido.abtest.entities.ABTestCluster;
import net.jobrapido.abtest.services.ConfigurationService;
import net.jobrapido.abtest.services.HashingService;

public class ConfigurationServiceDefault implements ConfigurationService {

	
	@Inject HashingService hashingService;
	
	@Override
	public List<ABTest> getAllConfiguredABTests() {
		
		// TODO get info from configuration file or from global on mongo db, for now we assume we can get this info
		
		List<ABTest> allConfiguredABTest = new ArrayList<ABTest>();
		ABTest abTest = null;
		List<ABTestCluster> abTestClustersFiftyFifty = new ArrayList<ABTestCluster>();
		abTestClustersFiftyFifty.add(new ABTestCluster(1l, 1l));
		abTestClustersFiftyFifty.add(new ABTestCluster(2l, 1l));
		
		List<ABTestCluster> abTestClusters2 = new ArrayList<ABTestCluster>();
		abTestClusters2.add(new ABTestCluster(1l, 1l));
		abTestClusters2.add(new ABTestCluster(2l, 3l));
		abTestClusters2.add(new ABTestCluster(3l, 6l));
		
		abTest = new ABTest( "prova 1", 1l );
		abTest.setHashKey( hashingService.getHashOfGivenString( abTest.getName() + abTest.getId() ) );
		abTest.setClusters(abTestClustersFiftyFifty);
		allConfiguredABTest.add( abTest );
		
		abTest = new ABTest( "conversion rate", 2l );
		abTest.setHashKey( hashingService.getHashOfGivenString( abTest.getName() + abTest.getId() ) );
		abTest.setClusters(abTestClustersFiftyFifty);
		allConfiguredABTest.add( abTest );

		abTest = new ABTest( "prova 3", 3l );
		abTest.setHashKey( hashingService.getHashOfGivenString( abTest.getName() + abTest.getId() ) );
		abTest.setClusters(abTestClusters2);
		abTest.activate();
		allConfiguredABTest.add( abTest );
		
		abTest = new ABTest( "prova 4", 4l );
		abTest.setHashKey( hashingService.getHashOfGivenString( abTest.getName() + abTest.getId() ) );
		abTest.setClusters(abTestClusters2);
		allConfiguredABTest.add( abTest );
		
		abTest = new ABTest( "email translation", 5l );
		abTest.setHashKey( hashingService.getHashOfGivenString( abTest.getName() + abTest.getId() ) );
		abTest.setClusters(abTestClusters2);
		abTest.activate();
		allConfiguredABTest.add( abTest );
		
		abTest = new ABTest( "prova 6", 6l );
		abTest.setHashKey( hashingService.getHashOfGivenString( abTest.getName() + abTest.getId() ) );
		abTest.setClusters(abTestClusters2);
		allConfiguredABTest.add( abTest );
		
		abTest = new ABTest( "prova 7", 7l );
		abTest.setHashKey( hashingService.getHashOfGivenString( abTest.getName() + abTest.getId() ) );
		abTest.setClusters(abTestClustersFiftyFifty);
		allConfiguredABTest.add( abTest );
		
		abTest = new ABTest( "link to inbox two", 8l );
		abTest.setHashKey( hashingService.getHashOfGivenString( abTest.getName() + abTest.getId() ) );
		abTest.setClusters(abTestClusters2);
		abTest.activate();
		abTest.close();
		allConfiguredABTest.add( abTest );
		
		return allConfiguredABTest;
	}

	@Override
	public List<ABTest> getAllActiveABTests() {
		List<ABTest> allConfiguredABTests = getAllConfiguredABTests();
		List<ABTest> allActiveABTests = new ArrayList<ABTest>();
		for (ABTest abTest : allConfiguredABTests) {
			if (abTest.isActive()) allActiveABTests.add(abTest);
		}
		return allActiveABTests;
	}

	@Override
	public List<ABTest> getAllConfiguredABTestsFromFile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ABTest> getAllConfiguredABTestsFromDB() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ABTest> getAllActiveABTestsFromFile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ABTest> getAllActiveABTestsFromDB() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean flushConfigurationToFile() {
		// TODO Auto-generated method stub
		System.out.println("flush configuration on file or over global on mongo db");
		return false;
	}

	@Override
	public boolean addABTest(ABTest abtest) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeABTest(ABTest abtest) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateABTest(ABTest abtest) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean flushConfigurationToDB() {
		// TODO Auto-generated method stub
		return false;
	}

}
