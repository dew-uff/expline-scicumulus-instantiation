package br.ufrj.cos.expline.scicumulus.conversion.model.expLineObjects;

import java.util.ArrayList;
import java.util.List;

public class Port {
	private String id;
	private String name;
	private String type;
	private List<Attribute> attributes;
	
	public Port() {
		this.id = "";
		this.name = "";
		this.type = "";
		this.attributes = new ArrayList<>();
	}
	
	public Port(String id, String name, String type, List<Attribute> attributes) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.attributes = attributes;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void addAttribute (Attribute attr){
		this.attributes.add(attr);
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

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

	public boolean removeAttribute (Attribute attr) {
		
		for (Attribute attribute : this.attributes) {
			if (attribute.getName().equalsIgnoreCase(attr.getName())) {
				this.attributes.remove(attribute);
				return true;
			}
		}
		
		return false;
	}

	@Override
	public String toString() {
		String portInfo = "";
		for (Attribute attr : this.attributes) {
			portInfo += attr.toString();
		}
		return this.id + " - " + this.name + " - " + this.type + "\n" + portInfo;
	}
	
	
	
}
