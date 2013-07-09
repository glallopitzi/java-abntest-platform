package net.jobrapido.abtest.services.impl;

import java.math.BigInteger;
import java.util.List;

import net.jobrapido.abtest.entities.ABTest;
import net.jobrapido.abtest.entities.ABTestCluster;
import net.jobrapido.abtest.entities.ABTestUser;
import net.jobrapido.abtest.services.ConfigurationService;
import net.jobrapido.abtest.services.HashingService;
import net.jobrapido.abtest.services.RandomizationService;
import net.jobrapido.abtest.services.UserAssignmentService;

import com.google.inject.Inject;

public class UserAssignmentServiceDefault implements UserAssignmentService {

	@Inject private ConfigurationService configurationService;
	@Inject private RandomizationService randomizationService;
	@Inject private HashingService hashingService;
	
	@Override
	public ABTest getABTestForUser(ABTestUser abTestUser) {
		List<ABTest> allActiveABTests = configurationService.getAllActiveABTests();
		long totalActiveTestsWeight = configurationService.getTotalActiveTestsWeight();
		long abTestUserLong = Math.abs(hashingService.getLongFromString(abTestUser.getHashKey()));
		long res =  abTestUserLong % totalActiveTestsWeight; 
		long aux = 0;
		for (ABTest abTest : allActiveABTests) {
			long nextAux = aux + abTest.getTestWeight();
			if((aux <= res) && (res < nextAux)) return abTest;
			aux = nextAux;
		}
		return null;
	}

	@Override
	public ABTestCluster getABTestClusterForUserAndABTest(ABTest abTest,
			ABTestUser abTestUser) {
		List<ABTestCluster> abTestClusters = abTest.getClusters();
		long totalClusterWeight = configurationService.getTotalTestClustersWeight(abTest);
		BigInteger abTestBigInteger = hashingService.toBigInteger(abTest.getHashKey());
		BigInteger abTestUserBigInteger = hashingService.toBigInteger(abTestUser.getHashKey());
		BigInteger result = abTestBigInteger.xor(abTestUserBigInteger).abs();
		
		
		long res = Math.abs(result.longValue()) % totalClusterWeight;
		
		long aux = 0;
		for (ABTestCluster abTestCluster : abTestClusters) {
			long nextAux = aux + abTestCluster.getWeight();
			if((aux <= res) && (res < nextAux)) return abTestCluster;
			aux = nextAux;
		}
		return null;
	}

}
