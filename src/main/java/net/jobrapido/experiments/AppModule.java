package net.jobrapido.experiments;

import net.jobrapido.experiments.manager.ExperimentManager;
import net.jobrapido.experiments.manager.ExperimentManagerLocalImpl;
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

public class AppModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ExperimentManager.class).to(ExperimentManagerLocalImpl.class);
		
		bind(RandomizationService.class).to(RandomizationServiceHashAndPartition.class);
		bind(UserAssignmentService.class).to(UserAssignmentServiceDefault.class);
		bind(DataPathService.class).to(DataPathServiceDefault.class);
		bind(StatisticalService.class).to(StatisticalServiceTTest.class);
		bind(ConfigurationService.class).to(ConfigurationServiceFile.class).in(Singleton.class);
		bind(HashingService.class).to(HashingServiceMD5.class);
	}
	
	
	
}
