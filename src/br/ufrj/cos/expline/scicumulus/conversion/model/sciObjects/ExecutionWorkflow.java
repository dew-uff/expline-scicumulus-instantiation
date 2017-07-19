package br.ufrj.cos.expline.scicumulus.conversion.model.sciObjects;

import java.util.ArrayList;
import java.util.List;

public class ExecutionWorkflow {
	private String executionModel;
	private String expDir;
	private String maxFailure;
	private String redundancy;
	private String reliability;
	private String tag;
	private String userInteraction;
	private List<ExecutionWorkflowRelation> execWorkRelList;
	
	public ExecutionWorkflow() {
		this.executionModel = "DYN_FAF";
		this.expDir = "";
		this.maxFailure = "5";
		this.redundancy = "true";
		this.reliability = "0.9";
		this.tag = "workflow-1";
		this.userInteraction = "false";
		this.execWorkRelList = new ArrayList<>();
	}
	
	public ExecutionWorkflow(String executionModel, String expDir, String maxFailure, String redundancy,
			String reliability, String tag, String userInteraction) {
		super();
		this.executionModel = executionModel;
		this.expDir = expDir;
		this.maxFailure = maxFailure;
		this.redundancy = redundancy;
		this.reliability = reliability;
		this.tag = tag;
		this.userInteraction = userInteraction;
		this.execWorkRelList = new ArrayList<>();
	}
	
	public String getExecutionModel() {
		return executionModel;
	}
	
	public void setExecutionModel(String executionModel) {
		this.executionModel = executionModel;
	}
	
	public String getExpDir() {
		return expDir;
	}
	
	public void setExpDir(String expDir) {
		this.expDir = expDir;
	}
	
	public String getMaxFailure() {
		return maxFailure;
	}
	
	public void setMaxFailure(String maxFailure) {
		this.maxFailure = maxFailure;
	}
	
	public String getRedundancy() {
		return redundancy;
	}
	
	public void setRedundancy(String redundancy) {
		this.redundancy = redundancy;
	}
	
	public String getReliability() {
		return reliability;
	}
	
	public void setReliability(String reliability) {
		this.reliability = reliability;
	}
	
	public String getTag() {
		return tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public String getUserInteraction() {
		return userInteraction;
	}
	
	public void setUserInteraction(String userInteraction) {
		this.userInteraction = userInteraction;
	}
	
	public void addRelation (ExecutionWorkflowRelation rel)
	{
		this.execWorkRelList.add(rel);
	}
	
	public boolean removeRelation (ExecutionWorkflowRelation rel)
	{
		for (ExecutionWorkflowRelation execRel : this.execWorkRelList ) {
			if (execRel.getName().equalsIgnoreCase(rel.getName())) {
				this.execWorkRelList.remove(execRel);
				return true;
			}
		}
		
		return false;
	}

	public List<ExecutionWorkflowRelation> getExecWorkRelList() {
		return execWorkRelList;
	}

	public void setExecWorkRelList(List<ExecutionWorkflowRelation> execWorkRelList) {
		this.execWorkRelList = execWorkRelList;
	}
	
	
}
