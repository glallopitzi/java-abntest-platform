package net.jobrapido.experiments.services;

import net.jobrapido.experiments.entities.Experiment;
import net.jobrapido.experiments.entities.ExperimentUser;
import net.jobrapido.experiments.entities.ExperimentVariant;

public interface UserAssignmentService {
	public Experiment getABTestForUser(ExperimentUser abTestUser);
	public ExperimentVariant getABTestClusterForUserAndABTest(Experiment abTest, ExperimentUser abTestUser);
}
