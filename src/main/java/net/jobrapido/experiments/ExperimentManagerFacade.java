package net.jobrapido.experiments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import net.jobrapido.experiments.entities.Experiment;
import net.jobrapido.experiments.entities.ExperimentUser;
import net.jobrapido.experiments.entities.ExperimentVariant;
import net.jobrapido.experiments.services.RandomizationService;

import com.google.inject.Inject;

@Named
public class ExperimentManagerFacade {

	
	
	private long USER_NUMBER_TO_EVALUATE = 100000;
	
	
	
	@Inject private ExperimentManager experimentsManager;
	@Inject private RandomizationService randomizationService;
		
	private Map<Experiment, Integer> experimentAssignmentResult = new HashMap<Experiment, Integer>();
	private Map<ExperimentVariant, Integer> variantAssignmentResult = new HashMap<ExperimentVariant, Integer>();
	
	public void run(){
		System.out.println( "------------ Demo BEGIN.." );
		
		// call this at very beginning of your app
		experimentsManager.init();
		
//		createSomeExperiments();
		
		evaluateSomeUsers();
		
//		modifySomeExperiment("change me");
		
//		evaluateSomeUser("giancarlolallopizzi@gmail.com");
//		evaluateSomeUser("giancarlo.lallopizzi@jobrapido.com");

		
//		experimentManager.printCurrentConfiguration();
		
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
		String[] experimentNames = {"link to inbox one", "link to inbox two","mailto light one","mailto light two"};
		for (String exp : experimentNames) {
			createSomeExperiment(exp);	
		}
	}

	private void evaluateSomeUser(String userId) {
		ExperimentUser dummyExperimentUser = experimentsManager.createDummyExperimentUser( userId );
		
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
		experimentsManager.printActiveExperiments();
	}

	private void createSomeExperiment(String name) {
		
		Experiment dummyExperiment = randomizationService.getRandomBoolean() ? experimentsManager.createDummyExperimentRandom(name, randomizationService.getRandomLong()) : experimentsManager.createDummyExperiment50(name, randomizationService.getRandomLong());
		
		experimentsManager.createExperiment(dummyExperiment);
		experimentsManager.enableExperiment(dummyExperiment);
		experimentsManager.printCurrentConfiguration();
	}

}
