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
public class ExperimentManagerLocalImpl implements ExperimentManager {
	
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
	
	public ExperimentUser getExperimentUser(String name){
		ExperimentUser experimentUser = new ExperimentUser(name, hashingService.getHashOfGivenString( name ));
		return experimentUser;
	}
	
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
	
	public void forceExperimentForUser(Experiment experiment, ExperimentUser experimentUser){
		// TODO
	}
	
	public void forceExperimentVariantForUser(Experiment experiment, ExperimentUser experimentUser, ExperimentVariant experimentCluster){
		// TODO
	}
	
	
}
