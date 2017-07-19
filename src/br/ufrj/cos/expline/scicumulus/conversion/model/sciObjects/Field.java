package br.ufrj.cos.expline.scicumulus.conversion.model.sciObjects;

public class Field {
	private String decimalplaces;
	private String name;
	private String type;
	private Relation input;
	private Relation output;
	
	public Field() {
		this.decimalplaces = "0";
		this.name = "";
		this.type = "";
		this.input = new Relation();
		this.output = new Relation();
	}
	
	public Field(String decimalplaces, String name, String type, Relation input, Relation output) {
		super();
		this.decimalplaces = "0";
		this.name = name;
		this.type = type;
		this.input = input;
		this.output = output;
	}

	public String getDecimalPlaces() {
		return decimalplaces;
	}

	public void setDecimalPlaces(String decimalplaces) {
		this.decimalplaces = decimalplaces;
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

	public Relation getInput() {
		return input;
	}

	public void setInput(Relation input) {
		this.input = input;
	}

	public Relation getOutput() {
		return output;
	}

	public void setOutput(Relation output) {
		this.output = output;
	}
	
	
	
}
