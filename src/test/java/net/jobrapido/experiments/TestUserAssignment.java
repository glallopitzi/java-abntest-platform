package net.jobrapido.experiments;

import static org.junit.Assert.*;

import java.util.List;

import net.jobrapido.experiments.entities.Experiment;
import net.jobrapido.experiments.entities.ExperimentUser;
import net.jobrapido.experiments.manager.ExperimentManager;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class TestUserAssignment {
	
	private Injector injector;
	private ExperimentManager experimentManager;
	private ExperimentManagerTestHelper helper;
	
	@Before
	public void testInit(){
		if(injector == null)
			injector = Guice.createInjector(new ExperimentManagerTestModule());
		
		if (experimentManager == null)
			experimentManager = injector.getInstance(ExperimentManager.class);
		
		if(helper == null)
			helper = injector.getInstance(ExperimentManagerTestHelper.class);
	}
	
	
	
	@Test
	public void testRandomUserCreation(){
		List<String> generateRandomUserIds = helper.generateRandomUserIds();
		assertTrue(generateRandomUserIds.size() > 0);
	}
	
	@Test
	public void testRandomUserAssignment(){
		List<String> generateRandomUserIds = helper.generateRandomUserIds();
		assertTrue(generateRandomUserIds.size() > 0);

		for (String string : generateRandomUserIds) {
			ExperimentUser experimentUser = helper.createDummyExperimentUser(string);
			Experiment experimentForUser = experimentManager.getExperimentForUser( experimentUser );
			assertNotNull(experimentForUser);
		}
		// TODO
		
	}
	
	
	@Test
	public void testRandomExperimentCreation(){
		experimentManager.init();
		Experiment experimentByName = experimentManager.getExperimentByName("link to inbox one");
		assertNotNull(experimentByName);
	}
	
}
