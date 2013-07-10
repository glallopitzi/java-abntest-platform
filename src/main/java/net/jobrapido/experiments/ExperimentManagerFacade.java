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

	@Inject private ExperimentManager abTestManager;
	@Inject private RandomizationService randomizationService;
		
	private Map<Experiment, Integer> result = new HashMap<Experiment, Integer>();
	private Map<ExperimentVariant, Integer> clusterResult = new HashMap<ExperimentVariant, Integer>();
	
	public void run(){
		System.out.println( "------------ Demo BEGIN.." );
		
		// call this at very beginning of your app
		abTestManager.init();
		
//		createSomeABTests();
		
		evaluateSomeUsers();
		
//		modifySomeABTest("change me");
		
//		evaluateSomeUser("giancarlolallopizzi@gmail.com");
//		evaluateSomeUser("giancarlo.lallopizzi@jobrapido.com");

		
//		abTestManager.printCurrentConfiguration();
		
		System.out.println( "------------ Demo END" );
	}



	
	
	
	private void evaluateSomeUsers() {
		
		List<String> randomUserIds = generateRandomUserIds();
		
		for (String userId : randomUserIds) {
			evaluateSomeUser( String.valueOf( userId ) );
		}
				
		for (Experiment item : result.keySet()) {
			System.out.println(item + ": " + result.get(item));
			List<ExperimentVariant> clusters = item.getClusters();
			for (ExperimentVariant abTestCluster : clusters) {
				System.out.println(abTestCluster + ": " + clusterResult.get(abTestCluster));
			}
		}
		
	}

	private List<String> generateRandomUserIds() {

		List<String> randomeUserIds = new ArrayList<String>();
		
		for( long counter = 1; counter < 10000; counter++ ){
			long randomLong = (long) ( counter * randomizationService.getRandomDouble() );
//			String randomString = randomizationService.getRandomString();
			String randomEmail = randomizationService.getRandomEmailAddress();
			
			String userId = randomizationService.getRandomBoolean() ? String.valueOf(randomLong) : randomEmail;
			
//			System.out.println(userId);
			
			randomeUserIds.add(userId);
			
		}
		
		return randomeUserIds;
	}






	private void createSomeABTests() {
		String[] abTestNames = {"link to inbox one", "link to inbox two","mailto light one","mailto light two"};
		for (String string : abTestNames) {
			createSomeABTest(string);	
		}
	}

	private void evaluateSomeUser(String userId) {
		ExperimentUser dummyABTestUser = abTestManager.createDummyABTestUser( userId );
		
		Experiment abTestForUser = abTestManager.getABTestForUser( dummyABTestUser );
		if ( abTestForUser != null ){
			if (result.containsKey(abTestForUser)){
				Integer count = result.get(abTestForUser);
				result.put(abTestForUser, count + 1);
			}else{
				result.put(abTestForUser, 1);
			}
		
			
			ExperimentVariant abTestClusterForUserAndABTest = abTestManager.getABTestClusterForUserAndABTest(abTestForUser, dummyABTestUser);
			if ( abTestClusterForUserAndABTest != null ){
//				System.out.println( abTestClusterForUserAndABTest.toString() );
				if (clusterResult.containsKey(abTestClusterForUserAndABTest)){
					Integer count = clusterResult.get(abTestClusterForUserAndABTest);
					clusterResult.put(abTestClusterForUserAndABTest, count + 1);
				}else{
					clusterResult.put(abTestClusterForUserAndABTest, 1);
				}
				
			}
						
		} else {
			System.out.println("the user \"" + userId + "\" is not assigned to any test");
		}
	}
	
	private void modifySomeABTest(String name){
		Experiment abTestByName = abTestManager.getABTestByName(name);
		abTestManager.disableABTest(abTestByName);
		abTestManager.enableABTest(abTestByName);
		abTestManager.printActiveTests();
	}

	private void createSomeABTest(String name) {
		
		Experiment createDummyABTest = randomizationService.getRandomBoolean() ? abTestManager.createDummyABTestRandom(name, randomizationService.getRandomLong()) : abTestManager.createDummyABTest50(name, randomizationService.getRandomLong());
		
		abTestManager.createABTest(createDummyABTest);
		abTestManager.enableABTest(createDummyABTest);
		abTestManager.printCurrentConfiguration();
	}

}
