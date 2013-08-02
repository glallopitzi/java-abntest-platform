package net.jobrapido.experiments;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import net.jobrapido.experiments.entities.Experiment;
import net.jobrapido.experiments.entities.ExperimentUser;
import net.jobrapido.experiments.entities.ExperimentVariant;

import org.junit.Test;

public class TestUserAssignment extends ExperimentManagerTestBase {
	
	
	@Test
	public void testRandomExperimentUserAssignment(){
		List<String> generateRandomUserIds = helper.generateRandomUserIds();
		assertTrue(generateRandomUserIds.size() > 0);

		for (String string : generateRandomUserIds) {
			ExperimentUser experimentUser = helper.createDummyExperimentUser(string);
			Experiment experimentForUser = experimentManager.getExperimentForUser( experimentUser );
			assertNotNull(experimentForUser);
		}
	}
	
	@Test
	public void testRandomExperimentVariantUserAssignment(){
		List<String> generateRandomUserIds = helper.generateRandomUserIds();
		assertTrue(generateRandomUserIds.size() > 0);

		for (String string : generateRandomUserIds) {
			ExperimentUser experimentUser = helper.createDummyExperimentUser(string);
			Experiment experimentForUser = experimentManager.getExperimentForUser( experimentUser );
			assertNotNull(experimentForUser);
			
			ExperimentVariant experimentVariantForUserAndExperiment = experimentManager.getExperimentVariantForUserAndExperiment(experimentForUser, experimentUser);
			assertNotNull(experimentVariantForUserAndExperiment);
		}
	}
	
	
	@Test
	public void testRandomExperimentCreation(){
		Experiment experimentByName = experimentManager.getExperimentByName("link to inbox one");
		assertNotNull(experimentByName);
	}
	
	@Test
	public void testForceUserAssignment(){
		// TODO
	}
	
}
