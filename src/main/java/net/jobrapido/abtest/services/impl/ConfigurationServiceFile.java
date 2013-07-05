package net.jobrapido.abtest.services.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import net.jobrapido.abtest.entities.ABTest;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class ConfigurationServiceFile extends ConfigurationServiceBase {

	
	private final static String CONFIGURATION_FILENAME = "C:/abtestConfiguration";
	
	
	@Override
	public boolean flushConfiguration() {
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
	public boolean loadConfiguration() {
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
	
}
