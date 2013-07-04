package net.jobrapido.abtest;

import java.util.Date;
import java.util.List;

import net.jobrapido.abtest.entities.ABTest;
import net.jobrapido.abtest.entities.ABTestCluster;
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
		System.out.println( "------------ Demo begin.." );
		
		// call this at very beginning of your app
		abTestManager.init();
		
//		createSomeABTest("change me");
		
//		modifySomeABTest("change me");
		
//		evaluateSomeUser("giancarlolallopizzi@gmail.com");
		
//		evaluateSomeUser("giancarlo.lallopizzi@jobrapido.com");
//		
//		evaluateSomeUser("123322432");
//		evaluateSomeUser("123322433");
//		evaluateSomeUser("123322434");
		
		abTestManager.printCurrentConfiguration();
		
		System.out.println( "------------ Demo END" );
	}



	
	
	
	private void evaluateSomeUser(String userId) {
		ABTestUser dummyABTestUser = abTestManager.createDummyABTestUser( userId );
		ABTest abTestForUser = abTestManager.getABTestForUser( dummyABTestUser );
		if ( abTestForUser != null ){
			ABTestCluster abTestClusterForUserAndABTest = abTestManager.getABTestClusterForUserAndABTest(abTestForUser, dummyABTestUser);
			System.out.println( abTestClusterForUserAndABTest.toString() );
			
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
