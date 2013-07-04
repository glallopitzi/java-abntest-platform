package net.jobrapido.abtest;

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
		System.out.println( "Demo begin.." );
		
		// call this at very beginning of your app
		abTestManager.init();
		
		
		ABTestUser dummyABTestUser = abTestManager.createDummyABTestUser("giancarlolallopizzi@gmail.com");
		ABTest abTestForUser = abTestManager.getABTestForUser(dummyABTestUser);
		ABTestCluster abTestClusterForUserAndABTest = abTestManager.getABTestClusterForUserAndABTest(abTestForUser, dummyABTestUser);
		
		
		
//		ABTest abTestByName = abTestManager.getABTestByName("dummy perfect final");
//		abTestManager.disableABTest(abTestByName);
//		abTestManager.enableABTest(abTestByName);
//		abTestManager.printActiveTests();
		
//		List<ABTest> allConfiguredABTests = abTestManager.getAllConfiguredABTests();
//		abTestManager.printActiveTests();
//		ABTest createDummyABTest = abTestManager.createDummyABTest("dummy perfect final", new Date().getTime());
//		abTestManager.createABTest(createDummyABTest);
//		abTestManager.enableABTest(createDummyABTest);
//		abTestManager.printActiveTests();
//		abTestManager.printCurrentConfiguration();
		
//		abTestManager.flushConfiguration();
		
		System.out.println( "Demo END" );
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    

















	public static void main( String[] args ){
        Injector injector = Guice.createInjector( new AppModule() );
        App app = injector.getInstance(App.class);
        app.run();
    }
}
