package br.ufrj.cos.expline.scicumulus.conversion.model.sciObjects;

public class Credentials {
	private String accessKey;
	private String secretAccessKey;
	
	public Credentials() {
		this.accessKey = "";
		this.secretAccessKey = "";
	}
	
	public Credentials(String accessKey, String secretAccessKey) {
		super();
		this.accessKey = accessKey;
		this.secretAccessKey = secretAccessKey;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getSecretAccessKey() {
		return secretAccessKey;
	}

	public void setSecretAccessKey(String secretAccessKey) {
		this.secretAccessKey = secretAccessKey;
	}
}
