package net.jobrapido.experiments;

import java.util.ArrayList;
import java.util.List;

import net.jobrapido.experiments.entities.Experiment;
import net.jobrapido.experiments.entities.ExperimentUser;
import net.jobrapido.experiments.entities.ExperimentVariant;
import net.jobrapido.experiments.manager.ExperimentManager;
import net.jobrapido.experiments.services.ConfigurationService;
import net.jobrapido.experiments.services.HashingService;
import net.jobrapido.experiments.services.RandomizationService;

import com.google.inject.Inject;

public class ExperimentManagerTestHelper {
	
	public long USER_NUMBER_TO_EVALUATE = 1000000;
	
	@Inject private ExperimentManager experimentsManager;
	@Inject private RandomizationService randomizationService;
	@Inject private HashingService hashingService;
	@Inject private ConfigurationService configurationService;
	
	
	public List<String> generateRandomUserIds() {
		List<String> randomeUserIds = new ArrayList<String>();
		for( long counter = 1; counter < USER_NUMBER_TO_EVALUATE; counter++ ){
			long randomLong = (long) ( counter * randomizationService.getRandomDouble() );
			String randomEmail = randomizationService.getRandomEmailAddress();
			String userId = randomizationService.getRandomBoolean() ? String.valueOf(randomLong) : randomEmail;
			randomeUserIds.add(userId);
		}
		return randomeUserIds;
	}
	
	
	public ExperimentUser createDummyExperimentUser(String name){
		ExperimentUser experimentUser = new ExperimentUser();
		experimentUser.setUserId(name);
		experimentUser.setHashKey( hashingService.getHashOfGivenString( name ) );
		return experimentUser;
	}
	
	public void createSomeExperiments() {
		String[] experimentNames = {"link to inbox one", "link to inbox two","mailto light one","mailto light two", "subscription div one", "smart jobalert one"};
		for (String exp : experimentNames) {
			createSomeExperiment(exp);	
		}
	}
	
	public void createConfiguration(){
		experimentsManager.init();
		createSomeExperiments();
	}
	
	public void deleteConfiguration(){
		configurationService.deleteConfiguration();
	}
	
	
	
	
	
	
	
	private void createSomeExperiment(String name) {
		
		Experiment dummyExperiment = randomizationService.getRandomBoolean() ? createDummyExperimentRandom(name, randomizationService.getRandomLong()) : createDummyExperiment50(name, randomizationService.getRandomLong());
		
		experimentsManager.createExperiment(dummyExperiment);
		experimentsManager.enableExperiment(dummyExperiment);
//		printCurrentConfiguration();
	}
	
	private Experiment createDummyExperiment50(String name, long id) {
  		Experiment experiment = new Experiment( name, id, hashingService.getHashOfGivenString( name + id ) );
	    List<ExperimentVariant> experimentClustersFiftyFifty = getFiftyFiftyVariantsList(experiment);
	    experiment.setVariants(experimentClustersFiftyFifty);
	    long randomExperimentWeight = randomizationService.getRandomLong(1, 9);
	    experiment.setExperimentWeight(randomExperimentWeight);
		return experiment;
	}
	private Experiment createDummyExperimentRandom(String name, long id) {
		Experiment experiment = new Experiment( name, id, hashingService.getHashOfGivenString( name + id ) );
	    List<ExperimentVariant> experimentClustersFiftyFifty = getRandomVariantsList(experiment);
	    experiment.setVariants(experimentClustersFiftyFifty);
	    long randomExperimentWeight = randomizationService.getRandomLong(1, 9);
	    experiment.setExperimentWeight(randomExperimentWeight);
		return experiment;
	}
	
	private List<ExperimentVariant> getRandomVariantsList(Experiment experiment){
		List<ExperimentVariant> experimentVariantsFiftyFifty = new ArrayList<ExperimentVariant>();
		
		long randomNumberOfVariants = randomizationService.getRandomLong(2, 10);
		
		for (long i = 1l; i <= randomNumberOfVariants; i++){
			long randomWeightOfVariant = randomizationService.getRandomLong(1, 10);
			experimentVariantsFiftyFifty.add(new ExperimentVariant(i, randomWeightOfVariant, experiment.getHashKey()));
		}
  		
  		return experimentVariantsFiftyFifty;
	}
	
	private List<ExperimentVariant> getFiftyFiftyVariantsList(Experiment experiment){
		List<ExperimentVariant> experimentVariantsFiftyFifty = new ArrayList<ExperimentVariant>();
  		experimentVariantsFiftyFifty.add(new ExperimentVariant(1l, 1l, experiment.getHashKey()));
  		experimentVariantsFiftyFifty.add(new ExperimentVariant(2l, 1l, experiment.getHashKey()));
  		return experimentVariantsFiftyFifty;
	}
	
}
