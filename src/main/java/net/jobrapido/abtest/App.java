package net.jobrapido.abtest;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Hello world!
 *
 */
public class App {
	
	public static void main( String[] args ){
        Injector injector = Guice.createInjector( new AppModule() );
        ExperimentManagerFacade app = injector.getInstance(ExperimentManagerFacade.class);
        app.run();
    }
}
