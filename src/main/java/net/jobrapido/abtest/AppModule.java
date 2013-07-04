package net.jobrapido.abtest;

import net.jobrapido.abtest.services.ConfigurationService;
import net.jobrapido.abtest.services.DataPathService;
import net.jobrapido.abtest.services.HashingService;
import net.jobrapido.abtest.services.RandomizationService;
import net.jobrapido.abtest.services.StatisticalService;
import net.jobrapido.abtest.services.UserAssignmentService;
import net.jobrapido.abtest.services.impl.ConfigurationServiceDefault;
import net.jobrapido.abtest.services.impl.DataPathServiceDefault;
import net.jobrapido.abtest.services.impl.HashingServiceMD5;
import net.jobrapido.abtest.services.impl.RandomizationServiceHashAndPartition;
import net.jobrapido.abtest.services.impl.StatisticalServiceTTest;
import net.jobrapido.abtest.services.impl.UserAssignmentServiceDefault;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class AppModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(RandomizationService.class).to(RandomizationServiceHashAndPartition.class);
		bind(UserAssignmentService.class).to(UserAssignmentServiceDefault.class);
		bind(DataPathService.class).to(DataPathServiceDefault.class);
		bind(StatisticalService.class).to(StatisticalServiceTTest.class);
		bind(ConfigurationService.class).to(ConfigurationServiceDefault.class).in(Singleton.class);
		bind(HashingService.class).to(HashingServiceMD5.class);
	}
	
	
	
}
