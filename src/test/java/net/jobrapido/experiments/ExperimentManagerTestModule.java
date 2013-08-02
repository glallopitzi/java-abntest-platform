package net.jobrapido.experiments;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import net.jobrapido.experiments.manager.ExperimentManager;
import net.jobrapido.experiments.manager.ExperimentManagerLocal;
import net.jobrapido.experiments.services.ConfigurationService;
import net.jobrapido.experiments.services.DataPathService;
import net.jobrapido.experiments.services.HashingService;
import net.jobrapido.experiments.services.RandomizationService;
import net.jobrapido.experiments.services.StatisticalService;
import net.jobrapido.experiments.services.UserAssignmentService;
import net.jobrapido.experiments.services.impl.ConfigurationServiceFile;
import net.jobrapido.experiments.services.impl.DataPathServiceDefault;
import net.jobrapido.experiments.services.impl.HashingServiceMD5;
import net.jobrapido.experiments.services.impl.RandomizationServiceHashAndPartition;
import net.jobrapido.experiments.services.impl.StatisticalServiceTTest;
import net.jobrapido.experiments.services.impl.UserAssignmentServiceDefault;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

public class ExperimentManagerTestModule extends AbstractModule {

	@Override
	protected void configure() {
		try {
	        Properties properties = new Properties();
	        String osName = System.getProperty("os.name");
			
			if (osName.contains("Windows")){
				properties.load(new FileReader("config/win.app.properties"));
			
			} else if (osName.contains("OS X")) {
				properties.load(new FileReader("config/osx.app.properties"));
			
			} else {
				throw new RuntimeException("OS not recognized");
			}
			
	        Names.bindProperties(binder(), properties);
	    } catch (IOException ex) {
	    	throw new RuntimeException(ex);
	    }
		
		bind(ExperimentManager.class).to(ExperimentManagerLocal.class);
		
		bind(RandomizationService.class).to(RandomizationServiceHashAndPartition.class);
		bind(UserAssignmentService.class).to(UserAssignmentServiceDefault.class);
		bind(DataPathService.class).to(DataPathServiceDefault.class);
		bind(StatisticalService.class).to(StatisticalServiceTTest.class);
		bind(ConfigurationService.class).to(ConfigurationServiceFile.class).in(Singleton.class);
		bind(HashingService.class).to(HashingServiceMD5.class);
		
		bind(ExperimentManagerTestHelper.class);
	}

}
