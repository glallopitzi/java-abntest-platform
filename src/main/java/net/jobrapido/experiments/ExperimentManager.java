package net.jobrapido.experiments;

import java.util.ArrayList;
import java.util.List;

import net.jobrapido.experiments.entities.Experiment;
import net.jobrapido.experiments.entities.ExperimentUser;
import net.jobrapido.experiments.entities.ExperimentVariant;
import net.jobrapido.experiments.services.ConfigurationService;
import net.jobrapido.experiments.services.DataPathService;
import net.jobrapido.experiments.services.HashingService;
import net.jobrapido.experiments.services.StatisticalService;
import net.jobrapido.experiments.services.UserAssignmentService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ExperimentManager {
	
	private StatisticalService statisticalService;
	private UserAssignmentService assignmentService;
	private DataPathService dataPathService;
	private ConfigurationService configurationService;
	private HashingService hashingService;

	@Inject public void setStatisticalService(StatisticalService statisticalService) {
		this.statisticalService = statisticalService;
	}

	@Inject public void setAssignmentService(UserAssignmentService assignmentService) {
		this.assignmentService = assignmentService;
	}

	@Inject public void setDataPathService(DataPathService dataPathService) {
		this.dataPathService = dataPathService;
	}

	@Inject public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	@Inject public void setHashingService(HashingService hashingService) {
		this.hashingService = hashingService;
	}
	
	
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
	
	public boolean createABTest(Experiment abtest){
		return configurationService.addABTest(abtest);
	}
	
	public boolean removeABTest(Experiment abtest){
		return configurationService.removeABTest(abtest);
	}
	
	public boolean updateABTest(Experiment abtest){
		return configurationService.updateABTest(abtest);
	}
	
	public boolean enableABTest(Experiment abtest){
		abtest.activate();
		return configurationService.updateABTest(abtest);
	}
	
	public boolean disableABTest(Experiment abtest){
		abtest.disable();
		return configurationService.updateABTest(abtest);
	}
	
	
	
	
	
	/*
	 * User related methods (test/cluster assignment, get active test for user, force assignment, etc..)
	 * 
	 */
	
	public Experiment getABTestByName(String name){
		for (Experiment abtest : configurationService.getAllConfiguredABTests()) {
			if (abtest.getName().equals(name)) return abtest;
		}
		return null;
	}
	
	public Experiment getABTestForUser(ExperimentUser abtestUser){
		return assignmentService.getABTestForUser(abtestUser);
	}
	
	public ExperimentVariant getABTestClusterForUserAndABTest(Experiment abTest, ExperimentUser abtestUser){
		return assignmentService.getABTestClusterForUserAndABTest(abTest, abtestUser);
	}
	
	public void forceABTestForUser(Experiment abTest){
		// TODO
	}
	
	public void forceABTestClusterForUser(Experiment abTest, ExperimentVariant abTestCluster){
		// TODO
	}
	
	
	
	
	
	
	
	
	
	
	/*
	 * Development utilities methods
	 * 
	 * 
	 */
	
	public Experiment createDummyABTest50(String name, long id) {
  		Experiment abTest = new Experiment( name, id );
	    abTest.setHashKey( hashingService.getHashOfGivenString( abTest.getName() + abTest.getId() ) );
	    List<ExperimentVariant> abTestClustersFiftyFifty = getFiftyFiftyClusterList(abTest);
	    abTest.setClusters(abTestClustersFiftyFifty);
		return abTest;
	}
	
	public Experiment createDummyABTestRandom(String name, long id) {
  		Experiment abTest = new Experiment( name, id );
  		abTest.setHashKey( hashingService.getHashOfGivenString( abTest.getName() + abTest.getId() ) );
  		List<ExperimentVariant> abTestClustersFiftyFifty = getRandomClusterList(abTest);
	    abTest.setClusters(abTestClustersFiftyFifty);
		return abTest;
	}
	
	
	public List<ExperimentVariant> getFiftyFiftyClusterList(Experiment abTest){
		List<ExperimentVariant> abTestClustersFiftyFifty = new ArrayList<ExperimentVariant>();
  		abTestClustersFiftyFifty.add(new ExperimentVariant(1l, 1l, abTest.getHashKey()));
  		abTestClustersFiftyFifty.add(new ExperimentVariant(2l, 1l, abTest.getHashKey()));
  		return abTestClustersFiftyFifty;
	}
	
	public List<ExperimentVariant> getRandomClusterList(Experiment abTest){
		List<ExperimentVariant> abTestClustersFiftyFifty = new ArrayList<ExperimentVariant>();
		
		// TODO make random cluster list here
		abTestClustersFiftyFifty.add(new ExperimentVariant(1l, 1l, abTest.getHashKey()));
  		abTestClustersFiftyFifty.add(new ExperimentVariant(2l, 2l, abTest.getHashKey()));
  		abTestClustersFiftyFifty.add(new ExperimentVariant(3l, 3l, abTest.getHashKey()));
  		abTestClustersFiftyFifty.add(new ExperimentVariant(4l, 3l, abTest.getHashKey()));
  		
  		return abTestClustersFiftyFifty;
	}
	
	public ExperimentUser createDummyABTestUser(String name){
		ExperimentUser abTestUser = new ExperimentUser();
		abTestUser.setUserId(name);
		abTestUser.setHashKey( hashingService.getHashOfGivenString( name ) );
		return abTestUser;
	}
	
	public void printActiveTests(){
		System.out.println("-- BEGIN ACTIVE TESTS");
		for (Experiment configuredABTest : configurationService.getAllActiveABTests()) {
			System.out.println(configuredABTest.toString());
			System.out.println("--------");
		}
		System.out.println("---- END ACTIVE TESTS");
	}
	
	public void printCurrentConfiguration(){
		System.out.println("-- BEGIN");
		for (Experiment configuredABTest : configurationService.getAllConfiguredABTests()) {
			System.out.println(configuredABTest.toString());
			System.out.println("--------");
		}
		System.out.println("---- END");
	}
}
