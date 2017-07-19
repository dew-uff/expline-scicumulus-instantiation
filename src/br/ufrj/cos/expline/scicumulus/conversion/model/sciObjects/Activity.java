package br.ufrj.cos.expline.scicumulus.conversion.model.sciObjects;

import java.util.ArrayList;
import java.util.List;

public class Activity {
	private String activation;
	private String description;
	private String tag;
	private String type;
	private List<Relation> relations;
	private List<Field> fields;
	
	public Activity() {
		this.activation = "";
		this.description = "";
		this.tag = "";
		this.type = "";
		this.relations = new ArrayList<>();
		this.fields = new ArrayList<>();
	}
	
	public Activity(String activation, String description, String tag, String type) {
		super();
		this.activation = activation;
		this.description = description;
		this.tag = tag;
		this.type = type;
		this.relations = new ArrayList<>();;
		this.fields = new ArrayList<>();;
	}

	public String getActivation() {
		return activation;
	}

	public void setActivation(String activation) {
		this.activation = activation;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public void addRelation (Relation rel)
	{
		this.relations.add(rel);
	}
	
	public boolean removeRelation (Relation r)
	{
		for (Relation rel : this.relations ) {
			if (rel.getName().equalsIgnoreCase(r.getName())) {
				this.relations.remove(rel);
				return true;
			}
		}
		
		return false;
	}
	
	public void addField (Field field)
	{
		this.fields.add(field);
	}
	
	public boolean removeRelation (Field f)
	{
		for (Field field : this.fields ) {
			if (field.getName().equalsIgnoreCase(f.getName())) {
				this.fields.remove(field);
				return true;
			}
		}
		
		return false;
	}

	public List<Relation> getRelations() {
		return relations;
	}

	public void setRelations(List<Relation> relations) {
		this.relations = relations;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
	
	
	
}
