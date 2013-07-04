package net.jobrapido.abtest;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;

import net.jobrapido.abtest.entities.ABTest;
import net.jobrapido.abtest.entities.ABTestCluster;
import net.jobrapido.abtest.entities.ABTestUser;
import net.jobrapido.abtest.services.ConfigurationService;
import net.jobrapido.abtest.services.DataPathService;
import net.jobrapido.abtest.services.HashingService;
import net.jobrapido.abtest.services.RandomizationService;
import net.jobrapido.abtest.services.StatisticalService;
import net.jobrapido.abtest.services.UserAssignmentService;

public class ABTestManager {
	
	@Inject private RandomizationService randomizationService;
	@Inject private StatisticalService statisticalService;
	@Inject private UserAssignmentService assignmentService;
	@Inject private DataPathService dataPathService;
	@Inject private ConfigurationService configurationService;
	@Inject private HashingService hashingService;
		
	public void init(){
		configurationService.loadConfiguration();
	}
	
	public void reloadConfiguration(){
		init();
	}
	
	public void flushConfiguration(){
		configurationService.flushConfiguration();
	}
	
	
	
	
	public ABTest createDummyABTest(String name, long id) {
    	ABTest abTest = null;
  		List<ABTestCluster> abTestClustersFiftyFifty = new ArrayList<ABTestCluster>();
  		abTestClustersFiftyFifty.add(new ABTestCluster(1l, 1l));
  		abTestClustersFiftyFifty.add(new ABTestCluster(2l, 1l));
  		abTest = new ABTest( name, id );
	    abTest.setHashKey( hashingService.getHashOfGivenString( abTest.getName() + abTest.getId() ) );
	    abTest.setClusters(abTestClustersFiftyFifty);
		return abTest;
	}
	
	public ABTestUser createDummyABTestUser(String name){
		ABTestUser abTestUser = new ABTestUser();
		abTestUser.setUserId(name);
		abTestUser.setHashKey(hashingService.getHashOfGivenString(name));
		return abTestUser;
	}
	
	public void printActiveTests(){
		System.out.println("-- BEGIN ACTIVE TESTS");
		for (ABTest configuredABTest : configurationService.getAllActiveABTests()) {
			System.out.println(configuredABTest.toString());
			System.out.println("--------");
		}
		System.out.println("---- END ACTIVE TESTS");
	}
	
	public void printCurrentConfiguration(){
		System.out.println("-- BEGIN");
		for (ABTest configuredABTest : configurationService.getAllConfiguredABTests()) {
			System.out.println(configuredABTest.toString());
			System.out.println("--------");
		}
		System.out.println("---- END");
	}
	
	
	
	
	public boolean createABTest(ABTest abtest){
		return configurationService.addABTest(abtest);
	}
	
	public boolean removeABTest(ABTest abtest){
		return configurationService.removeABTest(abtest);
	}
	
	public boolean updateABTest(ABTest abtest){
		return configurationService.updateABTest(abtest);
	}
	
	public boolean enableABTest(ABTest abtest){
		abtest.activate();
		return configurationService.updateABTest(abtest);
	}
	
	public boolean disableABTest(ABTest abtest){
		abtest.disable();
		return configurationService.updateABTest(abtest);
	}
	
	
	
	
	
	public ABTest getABTestByName(String name){
		for (ABTest abtest : configurationService.getAllConfiguredABTests()) {
			if (abtest.getName().equals(name)) return abtest;
		}
		return null;
	}
	
	
	public ABTest getABTestForUser(ABTestUser abtestUser){return null;}
	public ABTestCluster getABTestClusterForUserAndABTest(ABTestUser abtestUser){return null;}


}
