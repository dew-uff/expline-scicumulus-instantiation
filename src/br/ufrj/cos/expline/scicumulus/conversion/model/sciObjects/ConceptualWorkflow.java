package br.ufrj.cos.expline.scicumulus.conversion.model.sciObjects;

import java.util.ArrayList;
import java.util.List;

public class ConceptualWorkflow {
	private String description;
	private String tag;
	private List<Activity> activities;
	
	public ConceptualWorkflow() {
		this.description = "";
		this.tag = "workflow-1";
		this.activities = new ArrayList<>();
	}
	
	public ConceptualWorkflow(String description, String tag) {
		super();
		this.description = description;
		this.tag = tag;
		this.activities = new ArrayList<>();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}
	
	
	
}
