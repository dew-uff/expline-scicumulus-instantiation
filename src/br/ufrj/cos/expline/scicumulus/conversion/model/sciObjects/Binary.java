package br.ufrj.cos.expline.scicumulus.conversion.model.sciObjects;

public class Binary {
	private String conceptualVersion;
	private String directory;
	private String executionVersion;
	private String queryVersion;
	private String starterVersion;

	public Binary() {
		this.conceptualVersion = "";
		this.directory = "";
		this.executionVersion = "";
		this.queryVersion = "";
		this.starterVersion = "";
	}
	
	public Binary(String conceptualVersion, String directory, String executionVersion, String queryVersion,
			String starterVersion) {
		super();
		this.conceptualVersion = conceptualVersion;
		this.directory = directory;
		this.executionVersion = executionVersion;
		this.queryVersion = queryVersion;
		this.starterVersion = starterVersion;
	}

	public String getConceptualVersion() {
		return conceptualVersion;
	}

	public void setConceptualVersion(String conceptualVersion) {
		this.conceptualVersion = conceptualVersion;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public String getExecutionVersion() {
		return executionVersion;
	}

	public void setExecutionVersion(String executionVersion) {
		this.executionVersion = executionVersion;
	}

	public String getQueryVersion() {
		return queryVersion;
	}

	public void setQueryVersion(String queryVersion) {
		this.queryVersion = queryVersion;
	}

	public String getStarterVersion() {
		return starterVersion;
	}

	public void setStarterVersion(String starterVersion) {
		this.starterVersion = starterVersion;
	}
	
}
