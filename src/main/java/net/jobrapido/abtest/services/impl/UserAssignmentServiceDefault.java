package net.jobrapido.abtest.services.impl;

import com.google.inject.Inject;

import net.jobrapido.abtest.entities.ABTest;
import net.jobrapido.abtest.entities.ABTestCluster;
import net.jobrapido.abtest.entities.ABTestUser;
import net.jobrapido.abtest.services.ConfigurationService;
import net.jobrapido.abtest.services.RandomizationService;
import net.jobrapido.abtest.services.UserAssignmentService;

public class UserAssignmentServiceDefault implements UserAssignmentService {

	@Inject private ConfigurationService configurationService;
	@Inject private RandomizationService randomizationService;
	
	@Override
	public ABTest getABTestForUser(ABTestUser abTestUser) {
		// TODO check if robot here? in that case return null
		return null;
	}

	@Override
	public ABTestCluster getABTestClusterForUserAndABTest(ABTest abTest,
			ABTestUser abTestUser) {
		// TODO Auto-generated method stub
		return null;
	}

}
