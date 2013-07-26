package net.jobrapido.experiments.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;

import net.jobrapido.experiments.entities.Experiment;
import net.jobrapido.experiments.entities.ExperimentLog;
import net.jobrapido.experiments.entities.ExperimentOEC;
import net.jobrapido.experiments.entities.ExperimentResult;
import net.jobrapido.experiments.services.DataPathService;
import net.jobrapido.experiments.services.RandomizationService;

public class DataPathServiceDefault implements DataPathService {
	
	@Inject RandomizationService randomizationService;
	
	@Override
	public ExperimentResult collectLogsForExperiment(Experiment experiment) {
		ExperimentResult experimentResult = new ExperimentResult();
		experimentResult.setExperimentHashKey(experiment.getHashKey());
		
		// TODO get ExperimentLog list here and relative counter for each log, by counting log on remote server
		
		return experimentResult;
	}

	@Override
	public boolean fillExperimentResult(Experiment experiment) {
		ExperimentResult experimentResult = new ExperimentResult();
		List<ExperimentLog> results = new ArrayList<ExperimentLog>();
		
		// create some random results here
		ExperimentOEC overallEvaluationCriterion = experiment.getOverallEvaluationCriterion();
		List<ExperimentLog> metricsOfInterest = overallEvaluationCriterion.getMetricsOfInterest();
		for (ExperimentLog metric : metricsOfInterest) {
			
		}
		
		experiment.setExperimentResult(experimentResult);
		return true;
	}
	
}
