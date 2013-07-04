package net.jobrapido.abtest.services;

import java.util.List;

import net.jobrapido.abtest.entities.ABTest;

public interface ConfigurationService {
	public List<ABTest> getAllConfiguredABTests();
	public List<ABTest> getAllActiveABTests();
	public long getTotalActiveTestsWeight();
	
	public boolean flushConfiguration();
	public boolean flushAndReloadConfiguration();
	public boolean loadConfiguration();
	
	public boolean loadConfigurationFromFile();
	public boolean flushConfigurationToFile();
	public boolean loadConfigurationFromDB();
	public boolean flushConfigurationToDB();
	
	public boolean addABTest(ABTest abtest);
	public boolean removeABTest(ABTest abtest);
	public boolean updateABTest(ABTest abtest);
}
