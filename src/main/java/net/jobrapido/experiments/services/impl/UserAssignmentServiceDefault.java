package net.jobrapido.experiments.services.impl;

import java.math.BigInteger;
import java.util.List;

import net.jobrapido.experiments.entities.Experiment;
import net.jobrapido.experiments.entities.ExperimentUser;
import net.jobrapido.experiments.entities.ExperimentVariant;
import net.jobrapido.experiments.services.ConfigurationService;
import net.jobrapido.experiments.services.HashingService;
import net.jobrapido.experiments.services.RandomizationService;
import net.jobrapido.experiments.services.UserAssignmentService;

import com.google.inject.Inject;

public class UserAssignmentServiceDefault implements UserAssignmentService {

	@Inject private ConfigurationService configurationService;
	@Inject private RandomizationService randomizationService;
	@Inject private HashingService hashingService;
	
	@Override
	public Experiment getExperimentForUser(ExperimentUser experimentUser) {
		List<Experiment> allActiveExperiments = configurationService.getAllActiveExperiments();
		long totalActiveExperimentsWeight = configurationService.getTotalActiveExperimentsWeight();
		long experimentUserLongValue = Math.abs(hashingService.toBigInteger(experimentUser.getHashKey()).longValue());
		long experimentSlotAssignment =  experimentUserLongValue % totalActiveExperimentsWeight; 
		long threshold = 0;
		for (Experiment experiment : allActiveExperiments) {
			long nextThreshold = threshold + experiment.getExperimentWeight();
			if((threshold <= experimentSlotAssignment) && (experimentSlotAssignment < nextThreshold)) return experiment;
			threshold = nextThreshold;
		}
		return null;
	}

	@Override
	public ExperimentVariant getExperimentVariantForUserAndExperiment(Experiment experiment,
			ExperimentUser experimentUser) {
		List<ExperimentVariant> experimentVariants = experiment.getVariants();
		long totalVariantsWeight = configurationService.getTotalExperimentVariantsWeight(experiment);
		
		BigInteger experimentBigIntegerValue = hashingService.toBigInteger(experiment.getHashKey());
		BigInteger experimentUserBigIntegerValue = hashingService.toBigInteger(experimentUser.getHashKey());
		BigInteger experimentUserXORExperimentBigIntegerValue = experimentBigIntegerValue.xor(experimentUserBigIntegerValue).abs();
		long variantSlotAssignment = Math.abs(experimentUserXORExperimentBigIntegerValue.longValue()) % totalVariantsWeight;
		
		long threshold = 0;
		for (ExperimentVariant experimentVariant : experimentVariants) {
			long nextThreshold = threshold + experimentVariant.getWeight();
			if((threshold <= variantSlotAssignment) && (variantSlotAssignment < nextThreshold)) return experimentVariant;
			threshold = nextThreshold;
		}
		return null;
	}

}
