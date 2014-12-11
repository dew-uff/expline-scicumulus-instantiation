package br.ufrj.cos.expline.scicumulus.convertion;

import java.io.File;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public class Convertion_writer implements IWriter{

	
	
	Document scicumulusXML;
	Element root;
	
	
	public Convertion_writer(){
		
		try{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		this.scicumulusXML = builder.newDocument();
		}catch(Exception e){
			System.out.println("Error while creating the XML document");
		}
		
	}
	
	
	public void scicumulusMainNodeCreation(){
		Element root = this.scicumulusXML.createElement("SciCumulus");
		this.scicumulusXML.appendChild(root);
		this.root = root;
	}
	
	public void scicumulusConceptualWorkflowCreation(){
		
		Element conceptualWorkflow = this.scicumulusXML.createElement("conceptualWorkflow");
		
		Attr tag = this.scicumulusXML.createAttribute("tag");
		tag.setValue("workflow-1");
		conceptualWorkflow.setAttributeNode(tag);
		
		Attr description = this.scicumulusXML.createAttribute("description");
		description.setValue("");
		conceptualWorkflow.setAttributeNode(description);
		
		
		root.appendChild(conceptualWorkflow);
	}
	
	
	public void saveDocumentToDisk(){
		
		try{
		TransformerFactory transformerfactory = TransformerFactory.newInstance();
		Transformer transformer = transformerfactory.newTransformer();
		DOMSource documentSource = new DOMSource(this.scicumulusXML);
		StreamResult result = new StreamResult(new File("SciCumulus-wp_novo.xml"));
		transformer.transform(documentSource, result);
		System.out.println("File Saved");
		}catch(Exception e){
			System.out.println("Error while saving the XML file");
		}
	}



	public void insertActivity(String tag, String type) {
		
		Scanner entry = new Scanner(System.in);
		
		
		Element activity = this.scicumulusXML.createElement("activity");
		
		Attr sciCumulusActivityTag = this.scicumulusXML.createAttribute("tag");
		sciCumulusActivityTag.setValue(tag);
		
		Attr sciCumulusActivityDescription = this.scicumulusXML.createAttribute("description");
		sciCumulusActivityDescription.setValue("");
		
		Attr sciCumulusActivityType = this.scicumulusXML.createAttribute("type");
		sciCumulusActivityType.setValue(type);
		
		Attr sciCumulusActivityActivation = this.scicumulusXML.createAttribute("activation");
		System.out.println("Activation:");
		sciCumulusActivityActivation.setValue(entry.nextLine());
		
		
		activity.setAttributeNode(sciCumulusActivityTag);
		activity.setAttributeNode(sciCumulusActivityType);
		activity.setAttributeNode(sciCumulusActivityDescription);
		activity.setAttributeNode(sciCumulusActivityActivation);
		
		appendConceptualWorkflowChild(activity);
		
	}
	
	private void appendConceptualWorkflowChild(Element activity){
		
		NodeList sciCumulusChildren = this.root.getElementsByTagName("conceptualWorkflow");
		Node currentChild = sciCumulusChildren.item(0);
		
		currentChild.appendChild(activity);
	}


	@Override
	public void insertInputRelation(String name, String dependency, String activityTag) {
		Element sciCumulusRelation = this.scicumulusXML.createElement("relation");
		
		Attr sciCumulusRelationReltype = this.scicumulusXML.createAttribute("reltype");
		sciCumulusRelationReltype.setNodeValue("Input");
		
		
		Attr sciCumulusRelationName = this.scicumulusXML.createAttribute("name");
		sciCumulusRelationName.setNodeValue(name);
		
		sciCumulusRelation.setAttributeNode(sciCumulusRelationReltype);
		sciCumulusRelation.setAttributeNode(sciCumulusRelationName);
		
		
		if (dependency != null) {
			Attr sciCumulusRelationDependency = this.scicumulusXML.createAttribute("dependency");
			sciCumulusRelationDependency.setNodeValue(dependency);
			sciCumulusRelation.setAttributeNode(sciCumulusRelationDependency);
			
		}
		
		
		
		appendActivityChild(sciCumulusRelation, activityTag);
		
	}
	
	private void appendActivityChild (Element relation, String activityTag){
		
		
		NodeList sciCumulusChildren = this.root.getElementsByTagName("conceptualWorkflow");
		
		Element currentChild = (Element)sciCumulusChildren.item(0);
		
		NodeList conceptualWorkflowChildren = currentChild.getElementsByTagName("activity");
		
		for (int i = 0; i < conceptualWorkflowChildren.getLength(); i++) {
			
			Element currentActivity = (Element)conceptualWorkflowChildren.item(i);
			String currentActivityTag = currentActivity.getAttribute("tag");
			
			if (currentActivityTag.equals(activityTag)) {
				currentActivity.appendChild(relation);
			}

		}
		
		
		
		
	}
	
	public void insertOutputRelation(String name, String activityTag) {
		
		Element sciCumulusRelation = this.scicumulusXML.createElement("relation");
		
		Attr sciCumulusRelationReltype = this.scicumulusXML.createAttribute("reltype");
		sciCumulusRelationReltype.setNodeValue("Output");
		
		
		Attr sciCumulusRelationName = this.scicumulusXML.createAttribute("name");
		sciCumulusRelationName.setNodeValue(name);
		
		sciCumulusRelation.setAttributeNode(sciCumulusRelationReltype);
		sciCumulusRelation.setAttributeNode(sciCumulusRelationName);
		
		
		appendActivityChild(sciCumulusRelation, activityTag);
		
		
	}

	@Override
	public void insertField(String name, String type, String input, String output, String activityTag) {

		
		Element sciCumulusField = this.scicumulusXML.createElement("field");
		
		Attr sciCumulusFieldName = this.scicumulusXML.createAttribute("name");
		sciCumulusFieldName.setValue(name);
		
		Attr sciCumulusFieldType = this.scicumulusXML.createAttribute("type");
		sciCumulusFieldType.setNodeValue(type);
		
		Attr sciCumulusFieldInput = this.scicumulusXML.createAttribute("input");
		sciCumulusFieldInput.setNodeValue(input);
		
		if (output != null) {
			Attr sciCumulusFieldOutput = this.scicumulusXML.createAttribute("output");
			sciCumulusFieldOutput.setNodeValue(output);
			sciCumulusField.setAttributeNode(sciCumulusFieldOutput);
		}
		
		Attr sciCumulusFieldDecimalplaces = this.scicumulusXML.createAttribute("decimalplaces");
		sciCumulusFieldDecimalplaces.setNodeValue("0");
		
		
		sciCumulusField.setAttributeNode(sciCumulusFieldName);
		sciCumulusField.setAttributeNode(sciCumulusFieldType);
		sciCumulusField.setAttributeNode(sciCumulusFieldInput);
		sciCumulusField.setAttributeNode(sciCumulusFieldDecimalplaces);
		
		appendActivityChild(sciCumulusField, activityTag);
		
		
	}
	
	private void insertEnvironment(){

		Element sciCumulusenvironment = this.scicumulusXML.createElement("environment");
		
		Attr sciCumulusEnvironmentverbose = this.scicumulusXML.createAttribute("verbose");
		sciCumulusEnvironmentverbose.setNodeValue("false");
		
		Attr sciCumulusEnvironmenttype = this.scicumulusXML.createAttribute("type");
		sciCumulusEnvironmenttype.setNodeValue("CLOUD");
		
		Attr sciCumulusEnvironmentClusterName = this.scicumulusXML.createAttribute("cluster_name");
		sciCumulusEnvironmentClusterName.setNodeValue("vitor");
		
		sciCumulusenvironment.setAttributeNode(sciCumulusEnvironmentverbose);
		sciCumulusenvironment.setAttributeNode(sciCumulusEnvironmenttype);
		sciCumulusenvironment.setAttributeNode(sciCumulusEnvironmentClusterName);
		
		this.root.appendChild(sciCumulusenvironment);
		
		
	}
	
	private void insertQuery(){
		
		
		Scanner entry = new Scanner(System.in);
		
		
		Element sciCumulusQuery = this.scicumulusXML.createElement("query");
		
		Attr sciCumulusQuerySQL = this.scicumulusXML.createAttribute("sql");
		System.out.print("sql: ");
		sciCumulusQuerySQL.setNodeValue(entry.nextLine());
		
		
		
		this.root.appendChild(sciCumulusQuery);
		
	}
	
	private void insertDatabase(){
		
		Scanner entry = new Scanner(System.in);
		
		Element sciCumulusDatabase = this.scicumulusXML.createElement("database");
		
		Attr sciCumulusDatabaseName = this.scicumulusXML.createAttribute("name");
		System.out.println("Database name: ");
		sciCumulusDatabaseName.setNodeValue(entry.nextLine());
		
		Attr sciCumulusDatabaseUsername = this.scicumulusXML.createAttribute("Username");
		System.out.println("Username: ");
		sciCumulusDatabaseUsername.setNodeValue(entry.nextLine());
		
		Attr sciCumulusDatabasePassword = this.scicumulusXML.createAttribute("password");
		System.out.println("Database password");
		sciCumulusDatabasePassword.setNodeValue(entry.nextLine());
		
		Attr sciCumulusDatabasePort = this.scicumulusXML.createAttribute("port");
		System.out.println("Database port:");
		sciCumulusDatabasePort.setNodeValue(entry.nextLine());
		
		Attr sciCumulusDatabaseServer = this.scicumulusXML.createAttribute("server");
		System.out.println("Database Server: ");
		sciCumulusDatabase.setNodeValue(entry.nextLine());
		
		Attr sciCumulusDatabasePath = this.scicumulusXML.createAttribute("path");
		System.out.println("Database path:");
		sciCumulusDatabase.setNodeValue(entry.nextLine());
		
		
		
		root.appendChild(sciCumulusDatabase);
		
		
		
		
	}
	
	private void insertWorkspace(){
		
		
		
		
	}
	
	private void insertCredentials(){
		
		Scanner entry = new Scanner(System.in);
		
		Element sciCumulusCredentials = this.scicumulusXML.createElement("credentials");
		
		
		Attr sciCumulusBinaryAccessKey = this.scicumulusXML.createAttribute("access_key");
		System.out.println("Binary Access Key");
		sciCumulusBinaryAccessKey.setNodeValue(entry.nextLine());
		
		Attr sciCumulusBinarySecretAccessKey = this.scicumulusXML.createAttribute("secret_access_key");
		System.out.println("Binary Secret Access Key");
		sciCumulusBinarySecretAccessKey.setNodeValue(entry.nextLine());
		
		
		sciCumulusCredentials.setAttributeNode(sciCumulusBinaryAccessKey);
		sciCumulusCredentials.setAttributeNode(sciCumulusBinarySecretAccessKey);
		
		
		
		this.root.appendChild(sciCumulusCredentials);

		
		
		
	}
	
	@Override
	public void setDependency(String activityTag, String relationDependency){
		
		NodeList conceptualWorkflow = this.root.getElementsByTagName("conceptualWorkflow");
		if(conceptualWorkflow.item(0).getNodeType() == Node.ELEMENT_NODE)
		{
			Element conceptualElement = (Element)conceptualWorkflow.item(0);
			
			NodeList activities = conceptualElement.getElementsByTagName("activity");
			
			for (int i = 0; i < activities.getLength(); i++) {
				
				if(activities.item(i).getNodeType() == Node.ELEMENT_NODE)
				{
					Element currentActivity = (Element)activities.item(i);
					
					if(currentActivity.getAttribute("tag").equals(activityTag)){
						
						NodeList relationList = currentActivity.getElementsByTagName("relation");
						
						for (int j = 0; j < relationList.getLength(); j++) {
							
							if(relationList.item(j).getNodeType() == Node.ELEMENT_NODE)
							{
								Element currentRelation = (Element) relationList.item(j);
								if(currentRelation.getAttribute("reltype").equals("Input")){
									
									Attr sciCumulusRelationDependency = this.scicumulusXML.createAttribute("dependency");
									sciCumulusRelationDependency.setNodeValue(relationDependency);
									currentRelation.setAttributeNode(sciCumulusRelationDependency);
									
								}
							}
						}
					}
				}
			}
		}
	}

	private void insertBinary(){
		
		Scanner entry = new Scanner(System.in);
		
		Element sciCumulusBinary = this.scicumulusXML.createElement("binary");
		
		Attr sciCumulusBinaryDirectory = this.scicumulusXML.createAttribute("directory");
		System.out.println("Binary directory");
		sciCumulusBinaryDirectory.setNodeValue(entry.nextLine());
		
		Attr sciCumulusBinaryConceptualVersion = this.scicumulusXML.createAttribute("conceptual_version");
		System.out.println("Binary conceptual version");
		sciCumulusBinaryConceptualVersion.setNodeValue(entry.nextLine());
		
		Attr sciCumulusBinaryExecutionVersion = this.scicumulusXML.createAttribute("execution_version");
		System.out.println("Binary execution version");
		sciCumulusBinaryExecutionVersion.setNodeValue(entry.nextLine());
		
		Attr sciCumulusBinaryStarterVersion = this.scicumulusXML.createAttribute("starter_version");
		System.out.println("Binary starter version");
		sciCumulusBinaryStarterVersion.setNodeValue(entry.nextLine());
		
		Attr sciCumulusBinaryQueryVersion = this.scicumulusXML.createAttribute("query_version");
		System.out.println("Binary Query version");
		sciCumulusBinaryQueryVersion.setNodeValue(entry.nextLine());
		
		sciCumulusBinary.setAttributeNode(sciCumulusBinaryDirectory);
		sciCumulusBinary.setAttributeNode(sciCumulusBinaryConceptualVersion);
		sciCumulusBinary.setAttributeNode(sciCumulusBinaryExecutionVersion);
		sciCumulusBinary.setAttributeNode(sciCumulusBinaryStarterVersion);
		sciCumulusBinary.setAttributeNode(sciCumulusBinaryQueryVersion);
		
		
		
		this.root.appendChild(sciCumulusBinary);
		
	}

	public void insertUserInformation(){
		
		this.insertCredentials();
		this.insertEnvironment();
		this.insertBinary();
		this.insertWorkspace();
		this.insertDatabase();
		this.insertQuery();
		
		
		
	}
	
}
	
