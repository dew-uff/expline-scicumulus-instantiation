package br.ufrj.cos.expline.scicumulus.conversion.model.sciObjects;

public class ExecutionWorkflowRelation {
	private String name;
	private String fileName;
	private Activity act;
	private Relation rel;
	
	public ExecutionWorkflowRelation() {
		this.name = "";
		this.fileName = "";
		this.act = new Activity ();
		this.rel = new Relation();
	}
	
	public ExecutionWorkflowRelation(String name, String fileName, Activity act, Relation rel) {
		super();
		this.name = name;
		this.fileName = fileName;
		this.act = act;
		this.rel = rel;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Activity getAct() {
		return act;
	}

	public void setAct(Activity act) {
		this.act = act;
	}

	public Relation getRelation() {
		return rel;
	}

	public void setRelation(Relation rel) {
		this.rel = rel;
	}
	
}
