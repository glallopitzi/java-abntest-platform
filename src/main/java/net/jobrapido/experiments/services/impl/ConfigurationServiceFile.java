package net.jobrapido.experiments.services.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import net.jobrapido.experiments.entities.Experiment;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public class ConfigurationServiceFile extends ConfigurationServiceBase {

	
	@Inject @Named("configuration.file") public String configurationFileName;
	
	
	@Override
	public boolean flushConfiguration() {
		System.out.println("flush configuration on file or over global on mongo db");
		
		StringBuilder sb = new StringBuilder(200);
		
		Gson gson = new Gson();
		sb.append(gson.toJson(getAllConfiguredExperiments()));
		
		File configFile = new File(configurationFileName);
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
		File configFile = new File(configurationFileName);
		try {
			ArrayList<Experiment> experiments = new ArrayList<Experiment>();
			Gson gson = new Gson();
			JsonParser parser = new JsonParser();
			String configString = FileUtils.readFileToString(configFile);
			if ( ! "".equals(configString) ){
				JsonArray array = parser.parse(configString).getAsJsonArray();
				for(JsonElement obj : array){
					Experiment experiment = gson.fromJson(obj, Experiment.class);
					experiments.add(experiment);
				}
			}
			setAllConfiguredExperiments(experiments);
			
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}



	@Override
	public boolean deleteConfiguration() {
		// TODO Auto-generated method stub
		FileUtils.forceDelete(arg0);
		return false;
	}
	
	
	
	
}
