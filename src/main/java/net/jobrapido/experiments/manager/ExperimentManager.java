package net.jobrapido.experiments.manager;

import net.jobrapido.experiments.entities.Experiment;
import net.jobrapido.experiments.entities.ExperimentUser;
import net.jobrapido.experiments.entities.ExperimentVariant;

public interface ExperimentManager {
	
	public void init();
	
	public void reloadConfiguration();
	
	public void flushConfiguration();
	
	public boolean createExperiment(Experiment experiment);
	
	public boolean removeExperiment(Experiment experiment);
	
	public boolean updateExperiment(Experiment experiment);
	
	public boolean enableExperiment(Experiment experiment);
	
	public boolean disableExperiment(Experiment experiment);
	
	public ExperimentUser getExperimentUser(String name);
	
	public Experiment getExperimentByName(String experimentName);
	
	public Experiment getExperimentForUser(ExperimentUser experimentUser);
	
	public ExperimentVariant getExperimentVariantForUserAndExperiment(Experiment experiment, ExperimentUser experimentUser);
	
	public void forceExperimentForUser(Experiment experiment, ExperimentUser experimentUser);
	
	public void forceExperimentVariantForUser(Experiment experiment, ExperimentUser experimentUser, ExperimentVariant experimentCluster);
	
}
