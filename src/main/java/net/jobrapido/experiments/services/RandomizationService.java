package net.jobrapido.experiments.services;

public interface RandomizationService {
	public long getRandomLong();
	public long getRandomLong(long min, long max);
	public double getRandomDouble();
	public String getRandomString();
	public String getRandomString(int minLength, int maxLength);
	public boolean getRandomBoolean();
	public String getRandomEmailAddress();
}
