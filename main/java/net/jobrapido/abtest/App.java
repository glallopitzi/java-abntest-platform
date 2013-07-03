package net.jobrapido.abtest;

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
		
		abTestManager.init();
		
		abTestManager.printCurrentConfiguration();
		
		System.out.println( "Demo END" );
	}
	
	
	
	
	
	
	
	
    public static void main( String[] args ){
        Injector injector = Guice.createInjector( new AppModule() );
        App app = injector.getInstance(App.class);
        app.run();
    }
}
