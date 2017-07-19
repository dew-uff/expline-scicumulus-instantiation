package br.ufrj.cos.expline.scicumulus.conversion.model.sciObjects;

public class Workspace {
	private String bucketName;
	private String compressedDir;
	private String compressedWorkspace;
	private String upload;
	private String workflowDir;
	
	public Workspace() {
		this.bucketName = "";
		this.compressedDir = "";
		this.compressedWorkspace = "";
		this.upload = "";
		this.workflowDir = "";
	}
	
	public Workspace(String bucketName, String compressedDir, String compressedWorkspace, String upload,
			String workflowDir) {
		super();
		this.bucketName = bucketName;
		this.compressedDir = compressedDir;
		this.compressedWorkspace = compressedWorkspace;
		this.upload = upload;
		this.workflowDir = workflowDir;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getCompressedDir() {
		return compressedDir;
	}

	public void setCompressedDir(String compressedDir) {
		this.compressedDir = compressedDir;
	}

	public String getCompressedWorkspace() {
		return compressedWorkspace;
	}

	public void setCompressedWorkspace(String compressedWorkspace) {
		this.compressedWorkspace = compressedWorkspace;
	}

	public String getUpload() {
		return upload;
	}

	public void setUpload(String upload) {
		this.upload = upload;
	}

	public String getWorkflowDir() {
		return workflowDir;
	}

	public void setWorkflowDir(String workflowDir) {
		this.workflowDir = workflowDir;
	}
	
	
	
}
