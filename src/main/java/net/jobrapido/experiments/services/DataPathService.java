package net.jobrapido.experiments.services;

import net.jobrapido.experiments.entities.Experiment;
import net.jobrapido.experiments.entities.ExperimentResult;

public interface DataPathService {
	public ExperimentResult collectLogsForExperiment(Experiment experiment);
	public boolean fillExperimentResult(Experiment experiment);
}
