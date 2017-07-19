package br.ufrj.cos.expline.scicumulus.conversion.model.expLineObjects;

import java.util.ArrayList;
import java.util.List;

public class Activity {
	private String algebraicOperator;
	private String id;
	private String value;
	private List<Port> inputPorts;
	private List<Port> outputPorts;
	
	public Activity() {
		this.algebraicOperator = "";
		this.id = "";
		this.value = "";
		this.inputPorts = new ArrayList<>();
		this.outputPorts = new ArrayList<>();
	}
	
	public Activity(String algebraicOperator, String id, String value, List<Port> inputPorts, List<Port> outputPorts) {
		super();
		this.algebraicOperator = algebraicOperator;
		this.id = id;
		this.value = value;
		this.inputPorts = inputPorts;
		this.outputPorts = outputPorts;
	}

	public String getAlgebraicOperator() {
		return algebraicOperator;
	}

	public void setAlgebraicOperator(String algebraicOperator) {
		this.algebraicOperator = algebraicOperator;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public List<Port> getInputPorts() {
		return inputPorts;
	}

	public void setInputPorts(List<Port> inputPorts) {
		this.inputPorts = inputPorts;
	}

	public List<Port> getOutputPorts() {
		return outputPorts;
	}

	public void setOutputPorts(List<Port> outputPorts) {
		this.outputPorts = outputPorts;
	}

	@Override
	public String toString() {
		String ActivityInfo = algebraicOperator + " - " + id + " - " + value + "\n";
		
		ActivityInfo += "InputPorts\n";
		
		for (Port p : inputPorts) {
			ActivityInfo += p.toString();
		}
		
		ActivityInfo += "OutputPorts\n";
		
		for (Port p : outputPorts) {
			ActivityInfo += p.toString();
		}
		
		return ActivityInfo;
	}

	/**
	 * Find a port with a specific id. It depends on which type of array you'd  like to look at.
	 * @param type
	 * @param id
	 * @return port
	 */
	public Port getPortById (int type, String id) {
		List<Port> searchArray = (type == Activity.SEARCHINPUTPORT)? this.inputPorts : 
								 (type == Activity.SEARCHOUTPUTPORT)? this.outputPorts: null;
		
		if (searchArray != null) {
			for (Port port : searchArray) {
				if (port.getId().equalsIgnoreCase(id)) {
					return port;
				}
			}
		}		
		
		return null;
	}
	
	/**
	 * They are constants that describe where you'd like to search a port.
	 */
	public static int SEARCHINPUTPORT = 1;
	public static int SEARCHOUTPUTPORT = 2; 
}
