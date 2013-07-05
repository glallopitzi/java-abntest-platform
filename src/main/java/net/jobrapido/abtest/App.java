package net.jobrapido.abtest;

import java.util.Date;

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
		
//		createSomeABTest("link to inbox one");
//		createSomeABTest("link to inbox two");
//		createSomeABTest("mailto light one");
//		createSomeABTest("mailto light two");
//		createSomeABTest("subscription div one");
		
		
//		modifySomeABTest("change me");
//		evaluateSomeUser("giancarlolallopizzi@gmail.com");
//		evaluateSomeUser("giancarlo.lallopizzi@jobrapido.com");
		
		for(long userid = 0; userid < 1000; userid++){
			evaluateSomeUser(String.valueOf(userid));
		}
		
		
//		abTestManager.printCurrentConfiguration();
		
		System.out.println( "------------ Demo END" );
	}



	
	
	
	private void evaluateSomeUser(String userId) {
		ABTestUser dummyABTestUser = abTestManager.createDummyABTestUser( userId );
//		System.out.println("dummyABTestUser.toDouble(): " + dummyABTestUser.toDouble());
//		System.out.println("dummyABTestUser.toLong(): " + dummyABTestUser.toLong());
		
		ABTest abTestForUser = abTestManager.getABTestForUser( dummyABTestUser );
		if ( abTestForUser != null ){
			System.out.println("user " + dummyABTestUser.getUserId() + " assigned to test " + abTestForUser.getName());
//			ABTestCluster abTestClusterForUserAndABTest = abTestManager.getABTestClusterForUserAndABTest(abTestForUser, dummyABTestUser);
//			System.out.println( abTestClusterForUserAndABTest.toString() );
			
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
