package br.ufrj.cos.expline.scicumulus.conversion;

import java.io.File;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JOptionPane;
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

import br.ufrj.cos.expline.scicumulus.conversion.util.Util;



public class Conversion_writer implements IWriter{

	
	
	Document scicumulusXML;
	Element root;
	File fileToWrite;
	Map<String, String> properties;
	
	
	public Conversion_writer(File file, Map<String, String> properties){
		this.properties = properties;
		this.fileToWrite = file;
		try{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		this.scicumulusXML = builder.newDocument();
		}catch(Exception e){
			System.out.println("Error while creating the XML document");
		}
		this.scicumulusMainNodeCreation();
		//this.insertUserInformation();
		this.scicumulusConceptualWorkflowCreation();
		
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
		StreamResult result = new StreamResult(fileToWrite);
		transformer.transform(documentSource, result);
//		System.out.println("File Saved");
		JOptionPane.showMessageDialog(null, "File Saved");
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
		//System.out.println("Activation:");
		//sciCumulusActivityActivation.setValue(properties.get("ActivityActivation"));
		
		
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
	
	private void insertQuery(String SQL){
		
		
		Scanner entry = new Scanner(System.in);
		
		
		Element sciCumulusQuery = this.scicumulusXML.createElement("query");
		
		Attr sciCumulusQuerySQL = this.scicumulusXML.createAttribute("sql");
		System.out.print("sql: ");
		sciCumulusQuerySQL.setNodeValue(SQL);
		
		sciCumulusQuery.setAttributeNode(sciCumulusQuerySQL);
		
		
		
		this.root.appendChild(sciCumulusQuery);
		
	}
	
	private void insertDatabase(String DatabaseName, String DatabaseUsername, String DatabasePassword, String DatabasePort, String DatabaseServer, String DatabasePath){
		
		Scanner entry = new Scanner(System.in);
		
		Element sciCumulusDatabase = this.scicumulusXML.createElement("database");
		
		Attr sciCumulusDatabaseName = this.scicumulusXML.createAttribute("name");
//		System.out.println("Database name: ");
		sciCumulusDatabaseName.setNodeValue(DatabaseName);
		
		Attr sciCumulusDatabaseUsername = this.scicumulusXML.createAttribute("Username");
//		System.out.println("Username: ");
		sciCumulusDatabaseUsername.setNodeValue(DatabaseUsername);
		
		Attr sciCumulusDatabasePassword = this.scicumulusXML.createAttribute("password");
//		System.out.println("Database password");
		sciCumulusDatabasePassword.setNodeValue(DatabasePassword);
		
		Attr sciCumulusDatabasePort = this.scicumulusXML.createAttribute("port");
//		System.out.println("Database port:");
		sciCumulusDatabasePort.setNodeValue(DatabasePort);
		
		Attr sciCumulusDatabaseServer = this.scicumulusXML.createAttribute("server");
//		System.out.println("Database Server: ");
		sciCumulusDatabase.setNodeValue(DatabaseServer);
		
		Attr sciCumulusDatabasePath = this.scicumulusXML.createAttribute("path");
//		System.out.println("Database path:");
		sciCumulusDatabase.setNodeValue(DatabasePath);
		
		sciCumulusDatabase.setAttributeNode(sciCumulusDatabaseName);
		sciCumulusDatabase.setAttributeNode(sciCumulusDatabaseUsername);
		sciCumulusDatabase.setAttributeNode(sciCumulusDatabasePassword);
		sciCumulusDatabase.setAttributeNode(sciCumulusDatabasePort);
		sciCumulusDatabase.setAttributeNode(sciCumulusDatabaseServer);
		sciCumulusDatabase.setAttributeNode(sciCumulusDatabasePath);
		
		
		
		
		root.appendChild(sciCumulusDatabase);
		
		
		
		
	}
	
	private void insertWorkspace(String WorkspaceUpload, String WorkspaceBucketName, String WorkspaceWorkflowDir, String WorkspaceCompressedWorkspace, String WorkspaceCompressedDir){
		
		Scanner entry = new Scanner(System.in);
		
		
		Element sciCumulusWorkspace = this.scicumulusXML.createElement("workspace");
		
		Attr sciCumulusWorkspaceUpload = this.scicumulusXML.createAttribute("upload");
//		System.out.println("Workspace upload");
		sciCumulusWorkspaceUpload.setNodeValue(WorkspaceUpload);
		
		Attr sciCumulusWorkspaceBucketName = this.scicumulusXML.createAttribute("bucket_name");
//		System.out.println("Workspace bucket name");
		sciCumulusWorkspaceBucketName.setNodeValue(WorkspaceBucketName);
		
		Attr sciCumulusWorkspaceWorkflowDir = this.scicumulusXML.createAttribute("workflow_dir");
//		System.out.println("Workspace workflow dir");
		sciCumulusWorkspaceWorkflowDir.setNodeValue(WorkspaceWorkflowDir);
		
		Attr sciCumulusWorkspaceCompressedWorkspace = this.scicumulusXML.createAttribute("compressed_workspace");
//		System.out.println("Workspace compressed workspace");
		sciCumulusWorkspaceCompressedWorkspace.setNodeValue(WorkspaceCompressedWorkspace);
		
		Attr sciCumulusWorkspaceCompressedDir = this.scicumulusXML.createAttribute("compressed_dir");
//		System.out.println("Workspace compressed dir");
		//sciCumulusWorkspaceCompressedDir.setNodeValue(entry.nextLine());
		sciCumulusWorkspaceCompressedDir.setNodeValue(WorkspaceCompressedDir);
		
		sciCumulusWorkspace.setAttributeNode(sciCumulusWorkspaceUpload);
		sciCumulusWorkspace.setAttributeNode(sciCumulusWorkspaceBucketName);
		sciCumulusWorkspace.setAttributeNode(sciCumulusWorkspaceWorkflowDir);
		sciCumulusWorkspace.setAttributeNode(sciCumulusWorkspaceCompressedWorkspace);
		sciCumulusWorkspace.setAttributeNode(sciCumulusWorkspaceCompressedDir);
		
		this.root.appendChild(sciCumulusWorkspace);
		
		
		
		
		
		
		
		
	}
	
	private void insertCredentials(String AccessKey, String SecretAccessKey){
		
		Scanner entry = new Scanner(System.in);
		
		Element sciCumulusCredentials = this.scicumulusXML.createElement("credentials");
		
		
		Attr sciCumulusBinaryAccessKey = this.scicumulusXML.createAttribute("access_key");
//		System.out.println("Binary Access Key");
		//sciCumulusBinaryAccessKey.setNodeValue(entry.nextLine());
		sciCumulusBinaryAccessKey.setNodeValue(AccessKey);
		
		Attr sciCumulusBinarySecretAccessKey = this.scicumulusXML.createAttribute("secret_access_key");
//		System.out.println("Binary Secret Access Key");
		//sciCumulusBinarySecretAccessKey.setNodeValue(entry.nextLine());
		sciCumulusBinarySecretAccessKey.setNodeValue(SecretAccessKey);		
		
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
	
	@Override
	public void setDependency(String activityTag, String relationDependency,String relationUsed)
	{
		// TODO this method to insert the dependency correctly
	}

	private void insertBinary(String BinaryDirectory, String BinaryConceptualVersion, String BinaryExecutionVersion, String BinaryStarterVersion, String BinaryQueryVersion){
		
		Scanner entry = new Scanner(System.in);
		
		Element sciCumulusBinary = this.scicumulusXML.createElement("binary");
		
		Attr sciCumulusBinaryDirectory = this.scicumulusXML.createAttribute("directory");
//		System.out.println("Binary directory");
		//sciCumulusBinaryDirectory.setNodeValue(entry.nextLine());
		sciCumulusBinaryDirectory.setNodeValue(BinaryDirectory); 
		
		Attr sciCumulusBinaryConceptualVersion = this.scicumulusXML.createAttribute("conceptual_version");
//		System.out.println("Binary conceptual version");
		//sciCumulusBinaryConceptualVersion.setNodeValue(entry.nextLine());
		sciCumulusBinaryConceptualVersion.setNodeValue(BinaryConceptualVersion);
		
		Attr sciCumulusBinaryExecutionVersion = this.scicumulusXML.createAttribute("execution_version");
//		System.out.println("Binary execution version");
		//sciCumulusBinaryExecutionVersion.setNodeValue(entry.nextLine());
		sciCumulusBinaryExecutionVersion.setNodeValue(BinaryExecutionVersion);
		
		Attr sciCumulusBinaryStarterVersion = this.scicumulusXML.createAttribute("starter_version");
//		System.out.println("Binary starter version");
		//sciCumulusBinaryStarterVersion.setNodeValue(entry.nextLine());
		sciCumulusBinaryStarterVersion.setNodeValue(BinaryStarterVersion);
		
		Attr sciCumulusBinaryQueryVersion = this.scicumulusXML.createAttribute("query_version");
//		System.out.println("Binary Query version");
		//sciCumulusBinaryQueryVersion.setNodeValue(entry.nextLine());
		sciCumulusBinaryQueryVersion.setNodeValue(BinaryQueryVersion);
		
		sciCumulusBinary.setAttributeNode(sciCumulusBinaryDirectory);
		sciCumulusBinary.setAttributeNode(sciCumulusBinaryConceptualVersion);
		sciCumulusBinary.setAttributeNode(sciCumulusBinaryExecutionVersion);
		sciCumulusBinary.setAttributeNode(sciCumulusBinaryStarterVersion);
		sciCumulusBinary.setAttributeNode(sciCumulusBinaryQueryVersion);
		
		
		
		this.root.appendChild(sciCumulusBinary);
		
	}

	public void insertUserInformation(){
		
		//this.insertCredentials();
		//this.insertEnvironment();
		//this.insertBinary();
		//this.insertWorkspace();
		//this.insertDatabase();
		//this.insertQuery();
		//this.insertExecutionWorkflow();
		
		
		
	}
	

	private void insertExecutionWorkflow(String ExecutionWorkflowExpdir){
		
		Scanner entry = new Scanner(System.in);
		
		Element sciCumulusExecutionWorkflow = this.scicumulusXML.createElement("executionWorkflow");
		
		Attr sciCumulusExecutionWorkflowTag = this.scicumulusXML.createAttribute("tag");
		sciCumulusExecutionWorkflowTag.setNodeValue("workflow-1");
		
		Attr sciCumulusExecutionWorkflowExecmodel = this.scicumulusXML.createAttribute("execmodel");
		sciCumulusExecutionWorkflowExecmodel.setNodeValue("DYN_FAF");
		
		Attr sciCumulusExecutionWorkflowExpdir = this.scicumulusXML.createAttribute("expdir");
//		System.out.println("Execution workflow expdir");
		//sciCumulusExecutionWorkflowExpdir.setNodeValue(entry.nextLine());
		sciCumulusExecutionWorkflowExpdir.setNodeValue(ExecutionWorkflowExpdir);
		
		Attr sciCumulusExecutionWorkflowMaxFailure = this.scicumulusXML.createAttribute("max_failure");
		sciCumulusExecutionWorkflowMaxFailure.setNodeValue("5");
		
		Attr sciCumulusExecutionWorkflowUserInteraction = this.scicumulusXML.createAttribute("user_interaction");
		sciCumulusExecutionWorkflowUserInteraction.setNodeValue("false");
		
		Attr sciCumulusExecutionWorkflowRedundancy = this.scicumulusXML.createAttribute("redundancy");
		sciCumulusExecutionWorkflowRedundancy.setNodeValue("true");
		
		Attr sciCumulusExecutionWorkflowReliability = this.scicumulusXML.createAttribute("reliability");
		sciCumulusExecutionWorkflowReliability.setNodeValue("0.9");
		
		sciCumulusExecutionWorkflow.setAttributeNode(sciCumulusExecutionWorkflowTag);
		sciCumulusExecutionWorkflow.setAttributeNode(sciCumulusExecutionWorkflowExecmodel);
		sciCumulusExecutionWorkflow.setAttributeNode(sciCumulusExecutionWorkflowExpdir);
		sciCumulusExecutionWorkflow.setAttributeNode(sciCumulusExecutionWorkflowMaxFailure);
		sciCumulusExecutionWorkflow.setAttributeNode(sciCumulusExecutionWorkflowUserInteraction);
		sciCumulusExecutionWorkflow.setAttributeNode(sciCumulusExecutionWorkflowRedundancy);
		sciCumulusExecutionWorkflow.setAttributeNode(sciCumulusExecutionWorkflowReliability);
		
		this.root.appendChild(sciCumulusExecutionWorkflow);
		
		
		//this.insertsciCumulusExecutionWorkflowRelation();
		
		
	}

	private void insertsciCumulusExecutionWorkflowRelation(String WorkflowRelationName, String WorkflowRelationFilename){
		
		Scanner entry = new Scanner(System.in);
		
		Element sciCumulusExecutionWorkflowRelation = this.scicumulusXML.createElement("relation");
		
		
		Attr sciCumulusExecutionWorkflowRelationName = this.scicumulusXML.createAttribute("name");
//		System.out.println("Execution workflow relation name");
		//sciCumulusExecutionWorkflowRelationName.setNodeValue(entry.nextLine());
		sciCumulusExecutionWorkflowRelationName.setNodeValue(WorkflowRelationName);
		
		Attr sciCumulusExecutionWorkflowRelationFilename = this.scicumulusXML.createAttribute("filename");
//		System.out.println("Execution workflow relation filename");
		//sciCumulusExecutionWorkflowRelationFilename.setNodeValue(entry.nextLine());
		sciCumulusExecutionWorkflowRelationFilename.setNodeValue(WorkflowRelationFilename);
		
		sciCumulusExecutionWorkflowRelation.setAttributeNode(sciCumulusExecutionWorkflowRelationName);
		sciCumulusExecutionWorkflowRelation.setAttributeNode(sciCumulusExecutionWorkflowRelationFilename);
		
		appendExecutionWorkflowChild(sciCumulusExecutionWorkflowRelation);
		
		
		
		
		
	}
	
	private void appendExecutionWorkflowChild(Element relation){
		
		NodeList executionWorkflow = this.root.getElementsByTagName("executionWorkflow");
		
		Element executionWorkflowElement = (Element)executionWorkflow.item(0);
		
		executionWorkflowElement.appendChild(relation);
		
		
	}
	
	public void insertAllActivations(Map<String,String> onlyActivations){
		
		for(String activation : onlyActivations.keySet()){
			
			String tag = Util.getActivityTag(activation);
			this.insertSpecificActivation(tag, onlyActivations.get(activation));
		}
		
		
	}
	
	private void insertSpecificActivation(String activityTag, String activation){
		
		NodeList sciCumulusChildren = this.root.getElementsByTagName("conceptualWorkflow");
		
		Element currentChild = (Element)sciCumulusChildren.item(0);
		
		NodeList conceptualWorkflowChildren = currentChild.getElementsByTagName("activity");
		
		for (int i = 0; i < conceptualWorkflowChildren.getLength(); i++) {
			
			Element currentActivity = (Element)conceptualWorkflowChildren.item(i);
			String currentActivityTag = currentActivity.getAttribute("tag");
			
			if (currentActivityTag.equals(activityTag)) {
				currentActivity.setAttribute("activation", activation);
			}

		}
		
	}
	
	public void insertAllUserInformation(Map<String, String> properties){
		
		this.insertCredentials(properties.get("AccessKey"), properties.get("SecretAccessKey"));
		
		//defaul values. If it has to be set by the user, pass the properties.get() of attributes of the node
		this.insertEnvironment();
		
		this.insertBinary(properties.get("Directory"), properties.get("ConceptualVersion"), properties.get("ExecutionVersion"), properties.get("StarterVersion"), 
				properties.get("QueryVersion"));
		
		
		this.insertWorkspace(properties.get("WorkspaceUpload"), properties.get("WorkspaceBucketName"), properties.get("WorkspaceWorkflowDir"), properties.get("WorkspaceCompressedWorkspace"), properties.get("CompressedDir"));
		this.insertDatabase(properties.get("DatabaseName"), properties.get("DatabaseUsername"), properties.get("DatabasePassword"), properties.get("DatabasePort"), properties.get("DatabaseServer"), properties.get("DatabasePath"));
		
		this.insertQuery(properties.get("QuerySQL"));
		
		this.insertExecutionWorkflow(properties.get("WorkflowExpdir"));
		
		this.insertsciCumulusExecutionWorkflowRelation(properties.get("WorkflowRelationName"), properties.get("WorkflowRelationFilename"));
	}

}
	
