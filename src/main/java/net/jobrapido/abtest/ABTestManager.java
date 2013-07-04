package net.jobrapido.abtest;

import java.util.List;

import com.google.inject.Inject;

import net.jobrapido.abtest.entities.ABTest;
import net.jobrapido.abtest.entities.ABTestCluster;
import net.jobrapido.abtest.entities.ABTestUser;
import net.jobrapido.abtest.services.ConfigurationService;
import net.jobrapido.abtest.services.DataPathService;
import net.jobrapido.abtest.services.RandomizationService;
import net.jobrapido.abtest.services.StatisticalService;
import net.jobrapido.abtest.services.UserAssignmentService;

public class ABTestManager {
	
	@Inject private RandomizationService randomizationService;
	@Inject private StatisticalService statisticalService;
	@Inject private UserAssignmentService assignmentService;
	@Inject private DataPathService dataPathService;
	@Inject private ConfigurationService configurationService;
	
	private List<ABTest> allConfiguredABTests;
	private List<ABTest> allActiveABTests;
	
	public void init(){
		setAllConfiguredABTests(getAllConfiguredABTests());
		setAllActiveABTests(getAllActiveABTests());
	}
	
	public void reloadConfiguration(){
		init();
	}
	
	public void flushConfiguration(){
		configurationService.flushConfigurationToFile();
	}
	
	public List<ABTest> getAllConfiguredABTests(){
		return configurationService.getAllConfiguredABTests();
	}
	
	public List<ABTest> getAllActiveABTests(){
		return configurationService.getAllActiveABTests();
	}
	
	public void printCurrentConfiguration(){
		System.out.println("-- BEGIN");
		for (ABTest configuredABTest : allConfiguredABTests) {
			System.out.println(configuredABTest.toString());
			System.out.println("--------");
		}
		System.out.println("---- END");
	}
	
	
	
	public boolean createABTest(String name, long id){
		
		return false;
	}
	
	public boolean createABTest(ABTest abtest){
		
		return false;
	}
	
	public boolean removeABTest(ABTest abtest){
		return false;
	}
	
	public boolean updateABTest(ABTest abtest){
		return false;
	}
	
	public boolean enableABTest(ABTest abtest){
		return false;
	}
	
	public boolean disableABTest(ABTest abtest){
		return false;
	}
	
	
	
	public ABTestCluster getABTestClusterForUser(ABTestUser abtestUser){
		
		return null;
	}

	public void setAllConfiguredABTests(List<ABTest> allConfiguredABTests) {
		this.allConfiguredABTests = allConfiguredABTests;
	}

	public void setAllActiveABTests(List<ABTest> allActiveABTests) {
		this.allActiveABTests = allActiveABTests;
	}

}