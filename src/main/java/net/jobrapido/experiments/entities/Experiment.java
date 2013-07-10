package net.jobrapido.experiments.entities;

import java.util.Date;
import java.util.List;



public class Experiment {
	private long id;
	private boolean isActive;
	private long experimentWeight;
	
	private String name;
	private String hashKey;
	
	private List<ExperimentVariant> variants;
	private long variantsWeigth;
	
	private boolean hasWinner;
	
	private Date createdAt;
	private Date startedAt;
	private Date finishedAt;
	
	private ExperimentOEC overallEvaluationCriterion;
	
	public Experiment(String experimentName, long id, String experimentHashKey) {
		setId( id );
		setActive( false );
		setName( experimentName );
		setHashKey( experimentHashKey );
		setCreatedAt( new Date() );
		setHasWinner( false );
		setExperimentWeight(1l);
	}
	
	
	
	
	
	
	public boolean disable(){
		setActive( false );
		setFinishedAt(new Date());
		return isActive();
	}
	
	public boolean activate(){
		setActive( true );
		setStartedAt( new Date() );
		return isActive();
	}
	
	public boolean close(){
		setHasWinner(true);
		setFinishedAt(new Date());
		return true;
	}
	
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(200);
		
		String createdAt = getCreatedAt().toString();
		String startedAt = isActive() ? getStartedAt().toString() : "";
		String isActive = isActive() ? "Y" : "N";
		String hasWinner = isHasWinner() ? "Y" : "N";
		String finishedAt = isHasWinner() ? getFinishedAt().toString() : "";
		
		sb.append("Experiment[name("+getName()+"), ");
		sb.append("weigth("+getExperimentWeight()+"), ");
		sb.append("hashKey("+getHashKey()+"), ");
		sb.append("createdAt("+createdAt+"), ");
		sb.append("isActive("+isActive+"), ");
		sb.append("startedAt("+startedAt+"), ");
		sb.append("hasWinner("+hasWinner+"), ");
		sb.append("finishedAt("+finishedAt+")]" + "\n");
		
		int count = 0;
		sb.append("--      ");
		for (ExperimentVariant cluster : getVariants()) {
			count++;
			if (count == getVariants().size())
				sb.append(cluster.toString() + "\n");
			else
				sb.append(cluster.toString() + "||");
		}
		
		return sb.toString();
	}
	
	
	@Override
	public boolean equals(Object obj) {
		return ((Experiment)obj).getHashKey().equals(getHashKey());
	}
	
	
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public long getExperimentWeight() {
		return experimentWeight;
	}

	public void setExperimentWeight(long experimentWeight) {
		this.experimentWeight = experimentWeight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHashKey() {
		return hashKey;
	}

	public void setHashKey(String hashKey) {
		this.hashKey = hashKey;
	}

	public List<ExperimentVariant> getVariants() {
		return variants;
	}

	public void setVariants(List<ExperimentVariant> variants) {
		long variantsWeigth = 0l;
		for (ExperimentVariant experimentVariant : variants) {
			variantsWeigth += experimentVariant.getWeight();
		}
		this.variantsWeigth = variantsWeigth;
		this.variants = variants;
	}

	public long getVariantsWeigth() {
		return variantsWeigth;
	}

	public void setVariantsWeigth(long variantsWeigth) {
		this.variantsWeigth = variantsWeigth;
	}

	public boolean isHasWinner() {
		return hasWinner;
	}

	public void setHasWinner(boolean hasWinner) {
		this.hasWinner = hasWinner;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getStartedAt() {
		return startedAt;
	}

	public void setStartedAt(Date startedAt) {
		this.startedAt = startedAt;
	}

	public Date getFinishedAt() {
		return finishedAt;
	}

	public void setFinishedAt(Date finishedAt) {
		this.finishedAt = finishedAt;
	}
	
}

