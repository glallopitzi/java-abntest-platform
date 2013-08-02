package net.jobrapido.experiments;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import net.jobrapido.experiments.entities.Experiment;

import org.junit.Test;

public class TestConfigurationManagement extends ExperimentManagerTestBase {
	
	@Test
	public void testGetWrongExperimentName(){
		Experiment experimentToEvaluate = experimentManager.getExperimentByName(helper.WRONG_EXPERIMENT_NAME);
		assertNull(experimentToEvaluate);
	}
	
	@Test
	public void testGetExperiment(){
		Experiment experimentToEvaluate = experimentManager.getExperimentByName(helper.EXPERIMENT_NAME_TO_EVALUATE);
		assertNotNull(experimentToEvaluate);
	}
}
