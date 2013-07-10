package net.jobrapido.abtest.services;

import net.jobrapido.abtest.entities.Experiment;
import net.jobrapido.abtest.entities.ExperimentVariant;
import net.jobrapido.abtest.entities.ExperimentUser;

public interface UserAssignmentService {
	public Experiment getABTestForUser(ExperimentUser abTestUser);
	public ExperimentVariant getABTestClusterForUserAndABTest(Experiment abTest, ExperimentUser abTestUser);
}
