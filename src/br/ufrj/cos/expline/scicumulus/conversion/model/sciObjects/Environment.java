package br.ufrj.cos.expline.scicumulus.conversion.model.sciObjects;

public class Environment {
	private String clusterName;
	private String type;
	private String verbose;
	
	public Environment() {
		this.clusterName = "";
		this.type = "";
		this.verbose = "";
	}
	
	public Environment(String clusterName, String type, String verbose) {
		super();
		this.clusterName = clusterName;
		this.type = type;
		this.verbose = verbose;
	}

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVerbose() {
		return verbose;
	}

	public void setVerbose(String verbose) {
		this.verbose = verbose;
	}
	
}
