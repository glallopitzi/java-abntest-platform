package net.jobrapido.abtest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.jobrapido.abtest.entities.ABTest;
import net.jobrapido.abtest.entities.ABTestCluster;
import net.jobrapido.abtest.entities.ABTestUser;
import net.jobrapido.abtest.services.RandomizationService;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

/**
 * Hello world!
 *
 */
public class App {
	
	@Inject private ABTestManager abTestManager;
	@Inject private RandomizationService randomizationService;
		
	private Map<ABTest, Integer> result = new HashMap<ABTest, Integer>();
	private Map<ABTestCluster, Integer> clusterResult = new HashMap<ABTestCluster, Integer>();
	
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
				
		for (ABTest item : result.keySet()) {
			System.out.println(item + ": " + result.get(item));
		}
		
		for (ABTestCluster item : clusterResult.keySet()){
			System.out.println(item + ": " + clusterResult.get(item));
		}
		
	}

	private List<String> generateRandomUserIds() {

		List<String> randomeUserIds = new ArrayList<String>();
		
		for( long counter = 1; counter < 1000000; counter++ ){
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
		String[] abTestNames = {"link to inbox one"};
//		String[] abTestNames = {"link to inbox one", "link to inbox two","mailto light one","mailto light two"};
		for (String string : abTestNames) {
			createSomeABTest(string);	
		}
	}

	private void evaluateSomeUser(String userId) {
		ABTestUser dummyABTestUser = abTestManager.createDummyABTestUser( userId );
		
		ABTest abTestForUser = abTestManager.getABTestForUser( dummyABTestUser );
		if ( abTestForUser != null ){
			if (result.containsKey(abTestForUser)){
				Integer count = result.get(abTestForUser);
				result.put(abTestForUser, count + 1);
			}else{
				result.put(abTestForUser, 1);
			}
		
			
			ABTestCluster abTestClusterForUserAndABTest = abTestManager.getABTestClusterForUserAndABTest(abTestForUser, dummyABTestUser);
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
		ABTest abTestByName = abTestManager.getABTestByName(name);
		abTestManager.disableABTest(abTestByName);
		abTestManager.enableABTest(abTestByName);
		abTestManager.printActiveTests();
	}

	private void createSomeABTest(String name) {
		
		ABTest createDummyABTest = randomizationService.getRandomBoolean() ? abTestManager.createDummyABTestRandom(name, new Date().getTime()) : abTestManager.createDummyABTest50(name, new Date().getTime());
		
		abTestManager.createABTest(createDummyABTest);
		abTestManager.enableABTest(createDummyABTest);
		abTestManager.printCurrentConfiguration();
	}

	


	
	



	public static void main( String[] args ){
        Injector injector = Guice.createInjector( new AppModule() );
        App app = injector.getInstance(App.class);
        app.run();
    }
}
