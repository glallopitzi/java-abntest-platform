package net.jobrapido.experiments;

import java.util.ArrayList;
import java.util.List;

import net.jobrapido.experiments.entities.Experiment;
import net.jobrapido.experiments.entities.ExperimentUser;
import net.jobrapido.experiments.entities.ExperimentVariant;
import net.jobrapido.experiments.services.ConfigurationService;
import net.jobrapido.experiments.services.DataPathService;
import net.jobrapido.experiments.services.HashingService;
import net.jobrapido.experiments.services.RandomizationService;
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
	private RandomizationService randomizationService;

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
	
	@Inject public void setRandomizationService(RandomizationService randomizationService) {
		this.randomizationService = randomizationService;
	}
	
	/*
	 * Configuration related methods (init, flush, reload, add, del, upd, anable, disable)
	 * - init: initialize configuration, load persisted conf in memory
	 * - flush: persist configuration
	 * - reload: 
	 * - add: add a new experiment
	 * - del: remove a experiment
	 * - upd: update a experiment
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
	
	public boolean createExperiment(Experiment experiment){
		return configurationService.addExperiment(experiment);
	}
	
	public boolean removeExperiment(Experiment experiment){
		return configurationService.removeExperiment(experiment);
	}
	
	public boolean updateExperiment(Experiment experiment){
		return configurationService.updateExperiment(experiment);
	}
	
	public boolean enableExperiment(Experiment experiment){
		experiment.activate();
		return configurationService.updateExperiment(experiment);
	}
	
	public boolean disableExperiment(Experiment experiment){
		experiment.disable();
		return configurationService.updateExperiment(experiment);
	}
	
	
	
	
	
	/*
	 * User related methods (test/cluster assignment, get active test for user, force assignment, etc..)
	 * 
	 */
	
	public Experiment getExperimentByName(String experimentName){
		for (Experiment experiment : configurationService.getAllConfiguredExperiments()) {
			if (experiment.getName().equals(experimentName)) return experiment;
		}
		return null;
	}
	
	public Experiment getExperimentForUser(ExperimentUser experimentUser){
		return assignmentService.getExperimentForUser(experimentUser);
	}
	
	public ExperimentVariant getExperimentVariantForUserAndExperiment(Experiment experiment, ExperimentUser experimentUser){
		return assignmentService.getExperimentVariantForUserAndExperiment(experiment, experimentUser);
	}
	
	public void forceExperimentForUser(Experiment experiment){
		// TODO
	}
	
	public void forceExperimentVariantForUser(Experiment experiment, ExperimentVariant experimentCluster){
		// TODO
	}
	
	
	
	
	
	
	
	
	
	
	/*
	 * Development utilities methods
	 * 
	 * 
	 */
	
	public Experiment createDummyExperiment50(String name, long id) {
  		Experiment experiment = new Experiment( name, id, hashingService.getHashOfGivenString( name + id ) );
	    List<ExperimentVariant> experimentClustersFiftyFifty = getFiftyFiftyVariantsList(experiment);
	    experiment.setVariants(experimentClustersFiftyFifty);
	    long randomExperimentWeight = randomizationService.getRandomLong(1, 9);
	    experiment.setExperimentWeight(randomExperimentWeight);
		return experiment;
	}
	
	public Experiment createDummyExperimentRandom(String name, long id) {
		Experiment experiment = new Experiment( name, id, hashingService.getHashOfGivenString( name + id ) );
	    List<ExperimentVariant> experimentClustersFiftyFifty = getRandomVariantsList(experiment);
	    experiment.setVariants(experimentClustersFiftyFifty);
	    long randomExperimentWeight = randomizationService.getRandomLong(1, 9);
	    experiment.setExperimentWeight(randomExperimentWeight);
		return experiment;
	}
	
	
	public List<ExperimentVariant> getFiftyFiftyVariantsList(Experiment experiment){
		List<ExperimentVariant> experimentVariantsFiftyFifty = new ArrayList<ExperimentVariant>();
  		experimentVariantsFiftyFifty.add(new ExperimentVariant(1l, 1l, experiment.getHashKey()));
  		experimentVariantsFiftyFifty.add(new ExperimentVariant(2l, 1l, experiment.getHashKey()));
  		return experimentVariantsFiftyFifty;
	}
	
	public List<ExperimentVariant> getRandomVariantsList(Experiment experiment){
		List<ExperimentVariant> experimentVariantsFiftyFifty = new ArrayList<ExperimentVariant>();
		
		long randomNumberOfVariants = randomizationService.getRandomLong(2, 10);
		
		for (long i = 1l; i <= randomNumberOfVariants; i++){
			long randomWeightOfVariant = randomizationService.getRandomLong(1, 10);
			experimentVariantsFiftyFifty.add(new ExperimentVariant(i, randomWeightOfVariant, experiment.getHashKey()));
		}
  		
  		return experimentVariantsFiftyFifty;
	}
	
	public ExperimentUser createDummyExperimentUser(String name){
		ExperimentUser experimentUser = new ExperimentUser();
		experimentUser.setUserId(name);
		experimentUser.setHashKey( hashingService.getHashOfGivenString( name ) );
		return experimentUser;
	}
	
	public void printActiveExperiments(){
		System.out.println("-- BEGIN ACTIVE TESTS");
		for (Experiment allActiveExperiments : configurationService.getAllActiveExperiments()) {
			System.out.println(allActiveExperiments.toString());
			System.out.println("--------");
		}
		System.out.println("---- END ACTIVE TESTS");
	}
	
	public void printCurrentConfiguration(){
		System.out.println("-- BEGIN");
		for (Experiment configuredExperiment : configurationService.getAllConfiguredExperiments()) {
			System.out.println(configuredExperiment.toString());
			System.out.println("--------");
		}
		System.out.println("---- END");
	}
}
