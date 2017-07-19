package br.ufrj.cos.expline.scicumulus.conversion.model.expLineObjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ExpLineParser {
	private final AbstractDocumentReaderUtils absUtils = new AbstractDocumentReaderUtils(); 
	private final Logger logger = Logger.getLogger("ExpLineParser");
	private Map<String, Port> portsMap;
	private Map<String, Port> portsMapInput;
	
	public ExpLineParser () {
		this.portsMap = new HashMap<>();
		this.portsMapInput = new HashMap<>();
	}
	
	public AbstractWorkflow parseDocument (Element root) {
		
		NodeList activitiesNodes = getNodeListFromXPath (root, absUtils.getxPathActivities());
		
		Map<String, Activity> mapNamePortActivity = new HashMap<>();
		List<Activity> activities = new ArrayList<>();
		
		for (int i = 0; i < activitiesNodes.getLength(); i ++) {
			Element activityElem = (Element) activitiesNodes.item(i);
			
			String algebraicOperator = activityElem.getAttribute("algebraicOperator");
			String id = activityElem.getAttribute("id");
			String value = activityElem.getAttribute("value");

			Activity activity = new Activity();
			activity.setAlgebraicOperator(algebraicOperator);
			activity.setId(id);
			activity.setValue(value);
			
			NodeList inputPorts = (NodeList) getNodeListFromXPath (activityElem, absUtils.getxPathInputPorts());
			List<Port> inputPortsList = getPortList (inputPorts, "Input", activity);
			
			//for (Port p : inputPortsList) System.out.println(p.toString()); 
			
			NodeList outputPorts = (NodeList) getNodeListFromXPath (activityElem, absUtils.getxPathOutpuPorts());
			List<Port> outputPortsList = getPortList (outputPorts, "Output", activity);
			
			List<Port> generalPorts = new ArrayList<>();
			generalPorts.addAll(inputPortsList);
			generalPorts.addAll(outputPortsList);
			
			for (Port p : generalPorts) {
				mapNamePortActivity.put(p.getName(), activity);
				
			}
			
			activity.setInputPorts(inputPortsList);
			activity.setOutputPorts(outputPortsList);
			
			activities.add(activity);
		}
		
		NodeList edgesNodes = getNodeListFromXPath (root, absUtils.getxPathEdges());
		List<Edge> edges = new ArrayList<>();
		
		for (int i = 0; i < edgesNodes.getLength(); i ++) {
			Element edgeElem = (Element) edgesNodes.item(i);
			Edge edge = getEdge (edgeElem);
			edges.add(edge);
		}
		
		List<Port> inputWithoutData = getPortsListWithoutData ();
		
		AbstractWorkflow absWorkflow = new AbstractWorkflow(activities, edges, mapNamePortActivity, this.portsMap, inputWithoutData);
		
		return absWorkflow;
	}
	
	private List<Port> getPortsListWithoutData() {
		List<Port> portsWithoutData = new ArrayList<>();
		for (String portId : this.portsMap.keySet()) {
			Port p = this.portsMap.get(portId);
			if (p.getType().equalsIgnoreCase("Input")) {
				if (!this.portsMapInput.containsKey(portId)) {
					portsWithoutData.add(p);
				}
			}
		}
		return portsWithoutData;
	}

	private Edge getEdge(Element edgeElem) {
		String idProperty = edgeElem.getAttribute("id");
		String source = edgeElem.getAttribute("source");
		String target = edgeElem.getAttribute("target");
		
		Port ps = this.portsMap.get(source);
		Port pt = this.portsMap.get(target);
		
		this.portsMapInput.put(pt.getId(), pt);
		
		Edge edge = new Edge (idProperty, ps, pt);
		return edge;
	}

	public List<Port> getPortList (NodeList portNodeList, String type, Activity act) {
		List<Port> ports = new ArrayList<>();
		
		for (int i = 0; i < portNodeList.getLength(); i++) {
			Element portNode = (Element) portNodeList.item(i);
			
			NodeList attrList = (NodeList) getNodeListFromXPath(portNode, absUtils.getxPathRelationSchemaAttributes());
			
			String id = portNode.getAttribute("id");
			String portName = getPortName (id, type, act.getValue());
			List<Attribute> portAttrs = getAttributesList (attrList);
			
			Port port = new Port (id, portName, type, portAttrs);
			
			this.portsMap.put(id, port);
			
			ports.add(port);
			
		}
		
		return ports;
	}
	
	private String getPortName(String id, String type, String actName) {
		String opMode = (type.equalsIgnoreCase("Input")) ? "IMod" : "OMod";
		String portName = id + "_" + opMode + "_" + actName;
		return portName;
	}

	private List<Attribute> getAttributesList (NodeList attrList) {
		List<Attribute> attrArrayList = new ArrayList<>();
		
		for (int i = 0; i < attrList.getLength(); i ++) {
			Element attrElem = (Element) attrList.item(i);
			
			String name = attrElem.getAttribute("name");
			String type = attrElem.getAttribute("type");
			
			Attribute attr = new Attribute (name, type);
			
			attrArrayList.add(attr);
		}
		
		return attrArrayList;
	}
	
	private NodeList getNodeListFromXPath (Element elem, String xpath) {
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xPath = xpathFactory.newXPath();
		XPathExpression xPathExpression = null;
		
		try {
			xPathExpression = xPath.compile(xpath);
		} catch (XPathExpressionException e1) {
			this.logger.log(Level.WARNING, "Can't compile XPath. - " + xpath);
		}
		
		NodeList nodeList = null;
		try {
			if (xPathExpression != null) {
				nodeList = (NodeList) xPathExpression.evaluate(elem, XPathConstants.NODESET);
			}
		} catch (XPathExpressionException e) {
			this.logger.log(Level.WARNING, "Can't apply XPath. - " + xpath);
		}
		
		if (nodeList != null)
		{
			return nodeList;
		}
		
		return null;
	}
	
	private class AbstractDocumentReaderUtils {
		private final String xPathActivities = "//Activity";
		private final String xPathInputPorts = ".//InputPorts//Port";
		private final String xPathOutpuPorts = ".//OutputPorts//Port";
		private final String xPathRelationSchemaAttributes = ".//RelationSchemaAttribute";
		private final String xPathEdges = "//Edge";
		
		public String getxPathActivities() {
			return this.xPathActivities;
		}
		
		public String getxPathInputPorts() {
			return this.xPathInputPorts;
		}
		
		public String getxPathOutpuPorts() {
			return this.xPathOutpuPorts;
		}
		
		public String getxPathEdges() {
			return this.xPathEdges;
		}
		
		public String getxPathRelationSchemaAttributes () {
			return this.xPathRelationSchemaAttributes;
		}
		
	}
}
