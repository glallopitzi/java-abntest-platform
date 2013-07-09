package net.jobrapido.abtest;

import java.util.ArrayList;
import java.util.List;

import net.jobrapido.abtest.entities.ABTest;
import net.jobrapido.abtest.entities.ABTestCluster;
import net.jobrapido.abtest.entities.ABTestUser;
import net.jobrapido.abtest.services.ConfigurationService;
import net.jobrapido.abtest.services.DataPathService;
import net.jobrapido.abtest.services.HashingService;
import net.jobrapido.abtest.services.StatisticalService;
import net.jobrapido.abtest.services.UserAssignmentService;

import com.google.inject.Inject;

public class ABTestManager {
	
	@Inject private StatisticalService statisticalService;
	@Inject private UserAssignmentService assignmentService;
	@Inject private DataPathService dataPathService;
	@Inject private ConfigurationService configurationService;
	@Inject private HashingService hashingService;


	/*
	 * Configuration related methods (init, flush, reload, add, del, upd, anable, disable)
	 * - init: initialize configuration, load persisted conf in memory
	 * - flush: persist configuration
	 * - reload: 
	 * - add: add a new test
	 * - del: remove a test
	 * - upd: update a test
	 * ..
	 */

	public void init(){
		configurationService.loadConfiguration();
	}
	
	public void reloadConfiguration(){
		configurationService.loadConfiguration();
	}
	
	public void flushConfiguration(){
		configurationService.flushConfiguration();
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
	
	
	
	
	
	/*
	 * User related methods (test/cluster assignment, get active test for user, force assignment, etc..)
	 * 
	 */
	
	public ABTest getABTestByName(String name){
		for (ABTest abtest : configurationService.getAllConfiguredABTests()) {
			if (abtest.getName().equals(name)) return abtest;
		}
		return null;
	}
	
	public ABTest getABTestForUser(ABTestUser abtestUser){
		return assignmentService.getABTestForUser(abtestUser);
	}
	
	public ABTestCluster getABTestClusterForUserAndABTest(ABTest abTest, ABTestUser abtestUser){
		return assignmentService.getABTestClusterForUserAndABTest(abTest, abtestUser);
	}
	
	public void forceABTestForUser(ABTest abTest){
		// TODO
	}
	
	public void forceABTestClusterForUser(ABTest abTest, ABTestCluster abTestCluster){
		// TODO
	}
	
	
	
	
	
	
	
	
	
	
	/*
	 * Development utilities methods
	 * 
	 * 
	 */
	
	public ABTest createDummyABTest50(String name, long id) {
  		ABTest abTest = new ABTest( name, id );
	    abTest.setHashKey( hashingService.getHashOfGivenString( abTest.getName() + abTest.getId() ) );
	    List<ABTestCluster> abTestClustersFiftyFifty = getFiftyFiftyClusterList(abTest);
	    abTest.setClusters(abTestClustersFiftyFifty);
		return abTest;
	}
	
	public ABTest createDummyABTestRandom(String name, long id) {
  		ABTest abTest = new ABTest( name, id );
  		abTest.setHashKey( hashingService.getHashOfGivenString( abTest.getName() + abTest.getId() ) );
  		List<ABTestCluster> abTestClustersFiftyFifty = getRandomClusterList(abTest);
	    abTest.setClusters(abTestClustersFiftyFifty);
		return abTest;
	}
	
	
	public List<ABTestCluster> getFiftyFiftyClusterList(ABTest abTest){
		List<ABTestCluster> abTestClustersFiftyFifty = new ArrayList<ABTestCluster>();
  		abTestClustersFiftyFifty.add(new ABTestCluster(1l, 1l, abTest.getHashKey()));
  		abTestClustersFiftyFifty.add(new ABTestCluster(2l, 1l, abTest.getHashKey()));
  		return abTestClustersFiftyFifty;
	}
	
	public List<ABTestCluster> getRandomClusterList(ABTest abTest){
		List<ABTestCluster> abTestClustersFiftyFifty = new ArrayList<ABTestCluster>();
		
		// TODO make random cluster list here
		abTestClustersFiftyFifty.add(new ABTestCluster(1l, 1l, abTest.getHashKey()));
  		abTestClustersFiftyFifty.add(new ABTestCluster(2l, 2l, abTest.getHashKey()));
  		abTestClustersFiftyFifty.add(new ABTestCluster(3l, 3l, abTest.getHashKey()));
  		abTestClustersFiftyFifty.add(new ABTestCluster(4l, 3l, abTest.getHashKey()));
  		
  		return abTestClustersFiftyFifty;
	}
	
	public ABTestUser createDummyABTestUser(String name){
		ABTestUser abTestUser = new ABTestUser();
		abTestUser.setUserId(name);
		abTestUser.setHashKey( hashingService.getHashOfGivenString( name ) );
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
}
