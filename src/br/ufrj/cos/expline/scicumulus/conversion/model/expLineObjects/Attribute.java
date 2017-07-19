package br.ufrj.cos.expline.scicumulus.conversion.model.expLineObjects;

public class Attribute {
	private String name;
	private String type;
	
	public Attribute() {
		this.name = "";
		this.type = "";
	}
	
	public Attribute(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "\t" + this.name + " - " + this.type + "\n";
	}
	
	
	
}
