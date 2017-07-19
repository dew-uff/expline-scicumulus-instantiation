package br.ufrj.cos.expline.scicumulus.conversion.model.expLineObjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbstractWorkflow {
	private List<Activity> activities;
	private List<Edge> edges;
	private Map<String, Activity> mapNamePortActivity;
	private Map<String, Port> mapPorts;
	private List<Port> portsWithoutData;
	
	public AbstractWorkflow () {
		this.activities = new ArrayList<>();
		this.edges = new ArrayList<>();
		this.mapNamePortActivity = new HashMap<>();
		this.mapPorts = new HashMap<>();
		this.portsWithoutData = new ArrayList<>();
	}
	
	public AbstractWorkflow (List<Activity> activities, List<Edge> edges, Map<String, Activity> mapNamePortActivity, Map<String, Port> mapPorts, List<Port> portsWithoutData) {
		this.activities = activities;
		this.edges = edges;
		this.mapNamePortActivity = mapNamePortActivity;
		this.mapPorts = mapPorts;
		this.portsWithoutData = portsWithoutData;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}

	@Override
	public String toString() {
		return activities.toString() + "\n\nEdges:\n\n" +edges.toString();		
	}

	public Map<String, Activity> getMapNamePortActivity() {
		return mapNamePortActivity;
	}

	public void setMapNamePortActivity(Map<String, Activity> mapNamePortActivity) {
		this.mapNamePortActivity = mapNamePortActivity;
	}

	public Map<String, Port> getMapPorts() {
		return mapPorts;
	}

	public void setMapPorts(Map<String, Port> mapPorts) {
		this.mapPorts = mapPorts;
	}

	public List<Port> getPortsWithoutData() {
		return portsWithoutData;
	}

	public void setPortsWithoutData(List<Port> portsWithoutData) {
		this.portsWithoutData = portsWithoutData;
	}
	
	
	
}
