package net.jobrapido.abtest.services;

import java.util.List;

import net.jobrapido.abtest.entities.ABTest;

public interface ConfigurationService {
	public List<ABTest> getAllConfiguredABTests();
	public List<ABTest> getAllConfiguredABTestsFromFile();
	public List<ABTest> getAllConfiguredABTestsFromDB();
	public List<ABTest> getAllActiveABTests();
	public List<ABTest> getAllActiveABTestsFromFile();
	public List<ABTest> getAllActiveABTestsFromDB();
	
	public boolean flushConfigurationToFile();
	
	public boolean addABTest(ABTest abtest);
	public boolean removeABTest(ABTest abtest);
	public boolean updateABTest(ABTest abtest);
}
