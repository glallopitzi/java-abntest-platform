package net.jobrapido.abtest.services.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.jobrapido.abtest.entities.ABTest;
import net.jobrapido.abtest.services.ConfigurationService;
import net.jobrapido.abtest.services.HashingService;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.inject.Inject;

public class ConfigurationServiceDefault implements ConfigurationService {

	
	@Inject private HashingService hashingService;
	
	private final static String CONFIGURATION_FILENAME = "C:/abtestConfiguration";
	
	private List<ABTest> allConfiguredABTests;
	
	
	@Override
	public List<ABTest> getAllConfiguredABTests() {
		if ( allConfiguredABTests == null ) { loadConfigurationFromFile(); }
		return this.allConfiguredABTests;
	}

	@Override
	public List<ABTest> getAllActiveABTests() {
		if ( allConfiguredABTests == null ) { loadConfigurationFromFile(); }
		List<ABTest> allActiveABTests = new ArrayList<ABTest>();
		for (ABTest abTest : this.allConfiguredABTests) {
			if (abTest.isActive()) allActiveABTests.add(abTest);
		}
		return allActiveABTests;
	}

	@Override
	public List<ABTest> getAllConfiguredABTestsFromFile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ABTest> getAllConfiguredABTestsFromDB() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ABTest> getAllActiveABTestsFromFile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ABTest> getAllActiveABTestsFromDB() {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public boolean loadConfigurationFromFile() {
		File configFile = new File(CONFIGURATION_FILENAME);
		try {
			Gson gson = new Gson();
			JsonParser parser = new JsonParser();
			
			String configString = FileUtils.readFileToString(configFile);
			JsonArray array = parser.parse(configString).getAsJsonArray();
			ArrayList<ABTest> abtests = new ArrayList<ABTest>();
			for(JsonElement obj : array){
				ABTest elem = gson.fromJson(obj, ABTest.class);
				abtests.add(elem);
			}
			setAllConfiguredABTests(abtests);
			
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Override
	public boolean flushConfigurationToFile() {
		System.out.println("flush configuration on file or over global on mongo db");
		
		StringBuilder sb = new StringBuilder(200);
		
		Gson gson = new Gson();
		sb.append(gson.toJson(getAllConfiguredABTests()));
		
		File configFile = new File(CONFIGURATION_FILENAME);
		try {
			FileUtils.touch(configFile);
			FileUtils.writeStringToFile(configFile, sb.toString());
			
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean addABTest(ABTest abtest) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeABTest(ABTest abtest) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateABTest(ABTest abtest) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean flushConfigurationToDB() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean loadConfigurationFromDB() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setAllConfiguredABTests(List<ABTest> allConfiguredABTests) {
		this.allConfiguredABTests = allConfiguredABTests;
	}

}
