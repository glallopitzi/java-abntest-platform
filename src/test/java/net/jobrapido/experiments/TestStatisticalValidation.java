package net.jobrapido.experiments;

import static org.junit.Assert.assertNotNull;
import net.jobrapido.experiments.entities.Experiment;

import org.junit.Test;

public class TestStatisticalValidation extends ExperimentManagerTestBase {
	
	@Test
	public void testExperimentEvaluation(){
		Experiment experimentToEvaluate = experimentManager.getExperimentByName(helper.EXPERIMENT_NAME_TO_EVALUATE);
		assertNotNull(experimentToEvaluate);
		// TODO
		
	}
}
