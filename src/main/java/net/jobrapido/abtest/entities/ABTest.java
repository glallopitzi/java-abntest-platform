package net.jobrapido.abtest.entities;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;



public class ABTest {
	private long id;
	private boolean isActive;
	
	private String name;
	private String hashKey;
	
	private List<ABTestCluster> clusters;
	private long clustersWeigth;
	
	private boolean hasWinner;

	private Date createdAt;
	private Date startedAt;
	private Date finishedAt;
	
	
	public ABTest(String name, long id, String hashKey) {
		setId( id );
		setActive( false );
		setName( name );
		setHashKey( hashKey );
		setCreatedAt( new Date() );
		setHasWinner( false );
		
	}
	
	public ABTest(String name, long id) {
		setId( id );
		setActive( false );
		setName( name );
		setHashKey( calculateHashKey() );
		setCreatedAt( new Date() );
		setHasWinner( false );
		
	}
	
	public ABTest() {
		setCreatedAt( new Date() );
		setHasWinner( false );
		setActive( false );
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
	
	private String calculateHashKey(){
		// TODO make hash here
		
		String clearName = getName() + String.valueOf( getId() );
		String result = "";
		try {
			byte[] bytesOfMessage = clearName.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] thedigest = md.digest(bytesOfMessage);
			result = thedigest.toString();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(200);
		
		String createdAt = getCreatedAt().toString();
		String startedAt = isActive() ? getStartedAt().toString() : "";
		String isActive = isActive() ? "Y" : "N";
		String hasWinner = isHasWinner() ? "Y" : "N";
		String finishedAt = isHasWinner() ? getFinishedAt().toString() : "";
		
		sb.append("ABTest[name("+getName()+"), ");
		sb.append("hashKey("+getHashKey()+"), ");
		sb.append("createdAt("+createdAt+"), ");
		sb.append("isActive("+isActive+"), ");
		sb.append("startedAt("+startedAt+"), ");
		sb.append("hasWinner("+hasWinner+"), ");
		sb.append("finishedAt("+finishedAt+")]" + "\n");
		
		int count = 0;
		sb.append("--      ");
		for (ABTestCluster cluster : getClusters()) {
			count++;
			if (count == getClusters().size())
				sb.append(cluster.toString() + "\n");
			else
				sb.append(cluster.toString() + "||");
		}
		
		return sb.toString();
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

	public List<ABTestCluster> getClusters() {
		return clusters;
	}

	public void setClusters(List<ABTestCluster> clusters) {
		long clustersWeigth = 0l;
		for (ABTestCluster abTestCluster : clusters) {
			clustersWeigth += abTestCluster.getWeight();
		}
		this.clustersWeigth = clustersWeigth;
		this.clusters = clusters;
	}

	public long getClustersWeigth() {
		return clustersWeigth;
	}

	public void setClustersWeigth(long clustersWeigth) {
		this.clustersWeigth = clustersWeigth;
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

