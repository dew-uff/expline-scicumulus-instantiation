package br.ufrj.cos.expline.scicumulus.conversion.model.expLineObjects;

public class Edge {
	private String id;
	private Port source;
	private Port target;
	
	public Edge() {
		this.id = "";
		this.source = new Port();
		this.target = new Port();
	}
	
	public Edge(String id, Port source, Port target) {
		super();
		this.id = id;
		this.source = source;
		this.target = target;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Port getSource() {
		return source;
	}

	public void setSource(Port source) {
		this.source = source;
	}

	public Port getTarget() {
		return target;
	}

	public void setTarget(Port target) {
		this.target = target;
	}

	@Override
	public String toString() {
		String info = id + "\n\tSource:\n\t" + source.toString() + "\n\tTarget:\n\t" + target.toString() ;
		return info;
	}
	
	
}
