package net.jobrapido.experiments.manager;

import net.jobrapido.experiments.entities.Experiment;
import net.jobrapido.experiments.entities.ExperimentUser;
import net.jobrapido.experiments.entities.ExperimentVariant;

public class ExperimentManagerGA implements ExperimentManager {

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void reloadConfiguration() {
		// TODO Auto-generated method stub

	}

	@Override
	public void flushConfiguration() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean createExperiment(Experiment experiment) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeExperiment(Experiment experiment) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateExperiment(Experiment experiment) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean enableExperiment(Experiment experiment) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean disableExperiment(Experiment experiment) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ExperimentUser getExperimentUser(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Experiment getExperimentByName(String experimentName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Experiment getExperimentForUser(ExperimentUser experimentUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExperimentVariant getExperimentVariantForUserAndExperiment(
			Experiment experiment, ExperimentUser experimentUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void forceExperimentForUser(Experiment experiment,
			ExperimentUser experimentUser) {
		// TODO Auto-generated method stub

	}

	@Override
	public void forceExperimentVariantForUser(Experiment experiment,
			ExperimentUser experimentUser, ExperimentVariant experimentCluster) {
		// TODO Auto-generated method stub

	}

}
