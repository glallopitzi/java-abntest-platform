package net.jobrapido.experiments;

import java.util.List;

import junit.framework.Assert;
import net.jobrapido.experiments.entities.Experiment;
import net.jobrapido.experiments.manager.ExperimentManager;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

@SuppressWarnings("deprecation")
public class TestUserPartition {
	
	private Injector injector;
	private ExperimentManager experimentManager;
	private TestHelper helper;
	
	@Before
	public void testInit(){
		if(injector == null)
			injector = Guice.createInjector(new AppTestModule());
		
		if (experimentManager == null)
			experimentManager = injector.getInstance(ExperimentManager.class);
		
		if(helper == null)
			helper = injector.getInstance(TestHelper.class);
	}
	
	
	
	@Test
	public void testRandomUserCreation(){
		List<String> generateRandomUserIds = helper.generateRandomUserIds();
		Assert.assertTrue(generateRandomUserIds.size() > 0);
	}
	
	@Test
	public void testRandomUserAssignment(){
		List<String> generateRandomUserIds = helper.generateRandomUserIds();
		Assert.assertTrue(generateRandomUserIds.size() > 0);

		// TODO
		
	}
	
	
	@Test
	public void testRandomExperimentCreation(){
		experimentManager.init();
		Experiment experimentByName = experimentManager.getExperimentByName("link to inbox one");
		Assert.assertNotNull(experimentByName);
	}
	
}
