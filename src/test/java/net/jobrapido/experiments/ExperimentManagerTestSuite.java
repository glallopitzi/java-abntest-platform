package net.jobrapido.experiments;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.google.inject.Guice;
import com.google.inject.Injector;

@RunWith(Suite.class)
@SuiteClasses({ 
	TestUserAssignment.class,
	TestStatisticalValidation.class,
	TestConfigurationManagement.class
	})
public class ExperimentManagerTestSuite {

	@BeforeClass 
    public static void setUpClass() {      
        System.out.println("setup");
        Injector injector = Guice.createInjector(new ExperimentManagerTestModule());
        ExperimentManagerTestHelper helper = injector.getInstance(ExperimentManagerTestHelper.class);
        helper.createConfiguration();
    }

    @AfterClass public static void tearDownClass() { 
        System.out.println("tearDown");
        Injector injector = Guice.createInjector(new ExperimentManagerTestModule());
        ExperimentManagerTestHelper helper = injector.getInstance(ExperimentManagerTestHelper.class);
        helper.deleteConfiguration();
    }
}
