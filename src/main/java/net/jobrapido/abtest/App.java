package net.jobrapido.abtest;

import java.util.ArrayList;
import java.util.List;

import net.jobrapido.abtest.entities.ABTest;
import net.jobrapido.abtest.entities.ABTestCluster;

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
		
		
		
//		List<ABTest> allConfiguredABTests = abTestManager.getAllConfiguredABTests();
		abTestManager.printActiveTests();
		ABTest createDummyABTest = abTestManager.createDummyABTest("dummy perfect 2", 19l);
		abTestManager.createABTest(createDummyABTest);
		abTestManager.enableABTest(createDummyABTest);
		abTestManager.printActiveTests();
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
