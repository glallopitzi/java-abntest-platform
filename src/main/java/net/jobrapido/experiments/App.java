package net.jobrapido.experiments;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Hello world!
 *
 */
public class App {
	
	public static void main( String[] args ){
        Injector injector = Guice.createInjector( new AppModule() );
        ExperimentManagerApplication app = injector.getInstance(ExperimentManagerApplication.class);
        app.run();
    }
}
