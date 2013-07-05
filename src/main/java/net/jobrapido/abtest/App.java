package net.jobrapido.abtest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.jobrapido.abtest.entities.ABTest;
import net.jobrapido.abtest.entities.ABTestUser;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

/**
 * Hello world!
 *
 */
public class App {
	
	@Inject private ABTestManager abTestManager;
	
	
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
		Map<String, Integer> result = new HashMap<String, Integer>();
		
		for(long userid = 0; userid < 1000000; userid++){
			ABTest abTestForUser = evaluateSomeUser(String.valueOf(userid));
			if (abTestForUser != null){
				if (result.containsKey(abTestForUser.getHashKey())){
					Integer count = result.get(abTestForUser.getHashKey());
					result.put(abTestForUser.getHashKey(), count + 1);
				}else{
					result.put(abTestForUser.getHashKey(), 1);
				}
			}
		}
		
		for (String item : result.keySet()) {
			System.out.println(item + ": " + result.get(item));
		}
		
	}

	private void createSomeABTests() {
		String[] abTestNames = {"link to inbox one", "link to inbox two","mailto light one","mailto light two"};
		for (String string : abTestNames) {
			createSomeABTest(string);	
		}
	}

	private ABTest evaluateSomeUser(String userId) {
		ABTestUser dummyABTestUser = abTestManager.createDummyABTestUser( userId );
//		System.out.println("dummyABTestUser.toDouble(): " + dummyABTestUser.toDouble());
//		System.out.println("dummyABTestUser.toLong(): " + dummyABTestUser.toLong());
		Map<String, Integer> result = new HashMap<String, Integer>();
		ABTest abTestForUser = abTestManager.getABTestForUser( dummyABTestUser );
		if ( abTestForUser != null ){
//			System.out.println("user " + dummyABTestUser.getUserId() + " assigned to test " + abTestForUser.getName());
			
			return abTestForUser;
			
//			ABTestCluster abTestClusterForUserAndABTest = abTestManager.getABTestClusterForUserAndABTest(abTestForUser, dummyABTestUser);
//			System.out.println( abTestClusterForUserAndABTest.toString() );
			
		} else {
			System.out.println("the user \"" + userId + "\" is not assigned to any test");
			return null;
		}
	}
	
	private void modifySomeABTest(String name){
		ABTest abTestByName = abTestManager.getABTestByName(name);
		abTestManager.disableABTest(abTestByName);
		abTestManager.enableABTest(abTestByName);
		abTestManager.printActiveTests();
	}

	private void createSomeABTest(String name) {
		ABTest createDummyABTest = abTestManager.createDummyABTest(name, new Date().getTime());
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
