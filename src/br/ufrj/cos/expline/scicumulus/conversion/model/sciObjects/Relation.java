package br.ufrj.cos.expline.scicumulus.conversion.model.sciObjects;

public class Relation {
	private String name;
	private String relType;
	private Activity dependencyActivity;
	
	public Relation() {
		this.name = "";
		this.relType = "";
		this.dependencyActivity = new Activity();
	}
	
	public Relation(String name, String relType, Activity dependencyActivity) {
		super();
		this.name = name;
		this.relType = relType;
		this.dependencyActivity = dependencyActivity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRelType() {
		return relType;
	}

	public void setRelType(String relType) {
		this.relType = relType;
	}

	public Activity getDependencyActivity() {
		return dependencyActivity;
	}

	public void setDependencyActivity(Activity dependencyActivity) {
		this.dependencyActivity = dependencyActivity;
	}
	
		
	
}
