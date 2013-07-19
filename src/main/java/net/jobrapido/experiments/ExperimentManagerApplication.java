package net.jobrapido.experiments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import net.jobrapido.experiments.entities.Experiment;
import net.jobrapido.experiments.entities.ExperimentUser;
import net.jobrapido.experiments.entities.ExperimentVariant;
import net.jobrapido.experiments.manager.ExperimentManager;
import net.jobrapido.experiments.services.ConfigurationService;
import net.jobrapido.experiments.services.HashingService;
import net.jobrapido.experiments.services.RandomizationService;

import com.google.inject.Inject;

@Named
public class ExperimentManagerApplication {

	private long USER_NUMBER_TO_EVALUATE = 1000000;
	private Map<Experiment, Integer> experimentAssignmentResult = new HashMap<Experiment, Integer>();
	private Map<ExperimentVariant, Integer> variantAssignmentResult = new HashMap<ExperimentVariant, Integer>();

	
	@Inject private ExperimentManager experimentsManager;
	@Inject private RandomizationService randomizationService;
	@Inject private HashingService hashingService;
	@Inject private ConfigurationService configurationService;
	
	public void run(){
		System.out.println( "------------ Demo BEGIN.." );
		
		// call this at very beginning of your app
		experimentsManager.init();
		
//		createSomeExperiments();
		
		evaluateSomeUsers();
				
//		experimentsManager.printCurrentConfiguration();
		
		System.out.println( "------------ Demo END" );
	}



	
	
	
	private void evaluateSomeUsers() {
		
		List<String> randomUserIds = generateRandomUserIds();
		
		for (String userId : randomUserIds) {
			evaluateSomeUser( String.valueOf( userId ) );
		}
				
		for (Experiment item : experimentAssignmentResult.keySet()) {
			System.out.println(item + ": " + experimentAssignmentResult.get(item));
			List<ExperimentVariant> clusters = item.getVariants();
			for (ExperimentVariant experimentCluster : clusters) {
				System.out.println(experimentCluster + ": " + variantAssignmentResult.get(experimentCluster));
			}
		}
		
	}

	private List<String> generateRandomUserIds() {

		List<String> randomeUserIds = new ArrayList<String>();
		
		for( long counter = 1; counter < USER_NUMBER_TO_EVALUATE; counter++ ){
			long randomLong = (long) ( counter * randomizationService.getRandomDouble() );
//			String randomString = randomizationService.getRandomString();
			String randomEmail = randomizationService.getRandomEmailAddress();
			
			String userId = randomizationService.getRandomBoolean() ? String.valueOf(randomLong) : randomEmail;
			
//			System.out.println(userId);
			
			randomeUserIds.add(userId);
			
		}
		
		return randomeUserIds;
	}






	private void createSomeExperiments() {
		String[] experimentNames = {"link to inbox one", "link to inbox two","mailto light one","mailto light two", "subscription div one", "smart jobalert one"};
		for (String exp : experimentNames) {
			createSomeExperiment(exp);	
		}
	}

	private void evaluateSomeUser(String userId) {
		
		ExperimentUser dummyExperimentUser = createDummyExperimentUser( userId );
		
		Experiment experimentForUser = experimentsManager.getExperimentForUser( dummyExperimentUser );
		if ( experimentForUser != null ){
			if (experimentAssignmentResult.containsKey(experimentForUser)){
				Integer count = experimentAssignmentResult.get(experimentForUser);
				experimentAssignmentResult.put(experimentForUser, count + 1);
			}else{
				experimentAssignmentResult.put(experimentForUser, 1);
			}
		
			
			ExperimentVariant experimentVariantForUserAndExperiment = experimentsManager.getExperimentVariantForUserAndExperiment(experimentForUser, dummyExperimentUser);
			if ( experimentVariantForUserAndExperiment != null ){
				if (variantAssignmentResult.containsKey(experimentVariantForUserAndExperiment)){
					Integer count = variantAssignmentResult.get(experimentVariantForUserAndExperiment);
					variantAssignmentResult.put(experimentVariantForUserAndExperiment, count + 1);
				}else{
					variantAssignmentResult.put(experimentVariantForUserAndExperiment, 1);
				}
				
			}
						
		} else {
			System.out.println("the user \"" + userId + "\" is not assigned to any test");
		}
	}
	
	private void modifySomeExperiment(String experimentName){
		Experiment experiment = experimentsManager.getExperimentByName(experimentName);
		experimentsManager.disableExperiment(experiment);
		experimentsManager.enableExperiment(experiment);
		printActiveExperiments();
	}

	private void createSomeExperiment(String name) {
		
		Experiment dummyExperiment = randomizationService.getRandomBoolean() ? createDummyExperimentRandom(name, randomizationService.getRandomLong()) : createDummyExperiment50(name, randomizationService.getRandomLong());
		
		experimentsManager.createExperiment(dummyExperiment);
		experimentsManager.enableExperiment(dummyExperiment);
		printCurrentConfiguration();
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
	
	
	private List<ExperimentVariant> getFiftyFiftyVariantsList(Experiment experiment){
		List<ExperimentVariant> experimentVariantsFiftyFifty = new ArrayList<ExperimentVariant>();
  		experimentVariantsFiftyFifty.add(new ExperimentVariant(1l, 1l, experiment.getHashKey()));
  		experimentVariantsFiftyFifty.add(new ExperimentVariant(2l, 1l, experiment.getHashKey()));
  		return experimentVariantsFiftyFifty;
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
	
	
	private ExperimentUser createDummyExperimentUser(String name){
		ExperimentUser experimentUser = new ExperimentUser();
		experimentUser.setUserId(name);
		experimentUser.setHashKey( hashingService.getHashOfGivenString( name ) );
		return experimentUser;
	}

	private void printActiveExperiments(){
		for (Experiment allActiveExperiments : configurationService.getAllActiveExperiments()) {
			System.out.println(allActiveExperiments.toString());
			System.out.println("--------");
		}
	}
	
	private void printCurrentConfiguration(){
		for (Experiment configuredExperiment : configurationService.getAllConfiguredExperiments()) {
			System.out.println(configuredExperiment.toString());
			for (ExperimentVariant variant : configuredExperiment.getVariants()) {
				System.out.println(variant.toString());
			}
			System.out.println("--------");
		}
	}
}
