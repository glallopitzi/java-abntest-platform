package net.jobrapido.experiments;

import org.junit.Before;

import net.jobrapido.experiments.manager.ExperimentManager;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class ExperimentManagerTestBase {

	protected Injector injector;
	protected ExperimentManager experimentManager;
	protected ExperimentManagerTestHelper helper;
	
	@Before
	public void testInit(){
		if(injector == null)
			injector = Guice.createInjector(new ExperimentManagerTestModule());
		
		if (experimentManager == null)
			experimentManager = injector.getInstance(ExperimentManager.class);
		
		if(helper == null)
			helper = injector.getInstance(ExperimentManagerTestHelper.class);
	}
	
}
