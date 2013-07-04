package net.jobrapido.abtest.services;

import net.jobrapido.abtest.entities.ABTest;
import net.jobrapido.abtest.entities.ABTestCluster;
import net.jobrapido.abtest.entities.ABTestUser;

public interface UserAssignmentService {
	public ABTest getABTestForUser(ABTestUser abTestUser);
	public ABTestCluster getABTestClusterForUserAndABTest(ABTest abTest, ABTestUser abTestUser);
}
