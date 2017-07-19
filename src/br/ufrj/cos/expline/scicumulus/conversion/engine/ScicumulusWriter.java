package br.ufrj.cos.expline.scicumulus.conversion.engine;

import java.io.File;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import br.ufrj.cos.expline.scicumulus.conversion.model.sciObjects.Activity;
import br.ufrj.cos.expline.scicumulus.conversion.model.sciObjects.Binary;
import br.ufrj.cos.expline.scicumulus.conversion.model.sciObjects.ConceptualWorkflow;
import br.ufrj.cos.expline.scicumulus.conversion.model.sciObjects.Credentials;
import br.ufrj.cos.expline.scicumulus.conversion.model.sciObjects.Database;
import br.ufrj.cos.expline.scicumulus.conversion.model.sciObjects.Environment;
import br.ufrj.cos.expline.scicumulus.conversion.model.sciObjects.ExecutionWorkflow;
import br.ufrj.cos.expline.scicumulus.conversion.model.sciObjects.ExecutionWorkflowRelation;
import br.ufrj.cos.expline.scicumulus.conversion.model.sciObjects.Field;
import br.ufrj.cos.expline.scicumulus.conversion.model.sciObjects.Query;
import br.ufrj.cos.expline.scicumulus.conversion.model.sciObjects.Relation;
import br.ufrj.cos.expline.scicumulus.conversion.model.sciObjects.ScicumulusDocument;
import br.ufrj.cos.expline.scicumulus.conversion.model.sciObjects.Workspace;

public class ScicumulusWriter {
	
	private File outputFile;
	private Document doc;
	private Element root;
	
	public ScicumulusWriter (File outputFile) {
		this.outputFile = outputFile;
		initComponent ();
	}

	private void initComponent() {
		// Init Document to be Written.
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			this.doc = builder.newDocument();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		
		Element root = this.doc.createElement("SciCumulus");
		this.doc.appendChild(root);
		this.root = root;
		
	}
	
	public void run (ScicumulusDocument sciDoc) {
		writeBinary(sciDoc.getBinary());
		writeCredentials(sciDoc.getCred());
		writeDatabase(sciDoc.getDatabase());
		writeEnvironment(sciDoc.getEnviron());
		writeWorkspace(sciDoc.getWorkspace());
		writeQuery(sciDoc.getQuery());
		writeExecWorkflow(sciDoc.getExecWorkflow());
		writeConceptualWorkflow(sciDoc.getConceptualWorkflow());
		
		saveWorkFlow ();
	}

	private void saveWorkFlow() {
		try{
			TransformerFactory transformerfactory = TransformerFactory.newInstance();
			Transformer transformer = transformerfactory.newTransformer();
			DOMSource documentSource = new DOMSource(this.doc);
			StreamResult result = new StreamResult(this.outputFile);
			transformer.transform(documentSource, result);
			// System.out.println("File Saved");
			JOptionPane.showMessageDialog(null, "File Saved");
		} catch (Exception e) {
			System.out.println("Error while saving the XML file");
		}
	}

	private void writeExecWorkflow(ExecutionWorkflow execWorkflow) {
		Element sciCumulusExecutionWorkflow = this.doc.createElement("executionWorkflow");
		
		Attr sciCumulusExecutionWorkflowTag = this.doc.createAttribute("tag");
		sciCumulusExecutionWorkflowTag.setNodeValue(execWorkflow.getTag());
		
		Attr sciCumulusExecutionWorkflowExecmodel = this.doc.createAttribute("execmodel");
		sciCumulusExecutionWorkflowExecmodel.setNodeValue(execWorkflow.getExecutionModel());
		
		Attr sciCumulusExecutionWorkflowExpdir = this.doc.createAttribute("expdir");
		sciCumulusExecutionWorkflowExpdir.setNodeValue(execWorkflow.getExpDir());
		
		Attr sciCumulusExecutionWorkflowMaxFailure = this.doc.createAttribute("max_failure");
		sciCumulusExecutionWorkflowMaxFailure.setNodeValue(execWorkflow.getMaxFailure());
		
		Attr sciCumulusExecutionWorkflowUserInteraction = this.doc.createAttribute("user_interaction");
		sciCumulusExecutionWorkflowUserInteraction.setNodeValue(execWorkflow.getUserInteraction());
		
		Attr sciCumulusExecutionWorkflowRedundancy = this.doc.createAttribute("redundancy");
		sciCumulusExecutionWorkflowRedundancy.setNodeValue(execWorkflow.getRedundancy());
		
		Attr sciCumulusExecutionWorkflowReliability = this.doc.createAttribute("reliability");
		sciCumulusExecutionWorkflowReliability.setNodeValue(execWorkflow.getReliability());
		
		sciCumulusExecutionWorkflow.setAttributeNode(sciCumulusExecutionWorkflowTag);
		sciCumulusExecutionWorkflow.setAttributeNode(sciCumulusExecutionWorkflowExecmodel);
		sciCumulusExecutionWorkflow.setAttributeNode(sciCumulusExecutionWorkflowExpdir);
		sciCumulusExecutionWorkflow.setAttributeNode(sciCumulusExecutionWorkflowMaxFailure);
		sciCumulusExecutionWorkflow.setAttributeNode(sciCumulusExecutionWorkflowUserInteraction);
		sciCumulusExecutionWorkflow.setAttributeNode(sciCumulusExecutionWorkflowRedundancy);
		sciCumulusExecutionWorkflow.setAttributeNode(sciCumulusExecutionWorkflowReliability);
		
		for (ExecutionWorkflowRelation workRel : execWorkflow.getExecWorkRelList()) {
			Element rel = this.doc.createElement("relation");
			
			Attr sciCumulusExecutionWorkflowRelationFileName = this.doc.createAttribute("filename");
			sciCumulusExecutionWorkflowRelationFileName.setNodeValue(workRel.getFileName());
			
			Attr sciCumulusExecutionWorkflowRelationName = this.doc.createAttribute("name");
			sciCumulusExecutionWorkflowRelationName.setNodeValue(workRel.getName());
			
			rel.setAttributeNode(sciCumulusExecutionWorkflowRelationFileName);
			rel.setAttributeNode(sciCumulusExecutionWorkflowRelationName);
			
			sciCumulusExecutionWorkflow.appendChild(rel);
		}
		
		this.root.appendChild(sciCumulusExecutionWorkflow);
	}

	private void writeConceptualWorkflow(ConceptualWorkflow conceptualWorkflow) {
		Element scicumulusConceptuaWorkflow = this.doc.createElement("conceptualWorkflow");
		
		Attr scicumulusConceptualWorkflowDescrip = this.doc.createAttribute("description");
		scicumulusConceptualWorkflowDescrip.setNodeValue(conceptualWorkflow.getDescription());
		
		Attr scicumulusConceptualWorkflowTag = this.doc.createAttribute("tag");
		scicumulusConceptualWorkflowTag.setValue(conceptualWorkflow.getTag());
		
		scicumulusConceptuaWorkflow.setAttributeNode(scicumulusConceptualWorkflowDescrip);
		scicumulusConceptuaWorkflow.setAttributeNode(scicumulusConceptualWorkflowTag);
		
		// Insert Activity
		for (Activity act : conceptualWorkflow.getActivities()) {
			Element activity = this.doc.createElement("activity");
			
			Attr activityActivation = this.doc.createAttribute("activation");
			activityActivation.setValue(act.getActivation());
			
			Attr activityDescription = this.doc.createAttribute("description");
			activityDescription.setValue(act.getDescription());
			
			Attr activityTag = this.doc.createAttribute("tag");
			activityTag.setValue(act.getTag());
			
			Attr activityType = this.doc.createAttribute("type");
			activityType.setValue(act.getType());
			
			activity.setAttributeNode(activityActivation);
			activity.setAttributeNode(activityDescription);
			activity.setAttributeNode(activityTag);
			activity.setAttributeNode(activityType);
			
			for (Relation rel : act.getRelations()) {
				Element relation = this.doc.createElement("relation");
				
				Attr relationName = this.doc.createAttribute("name");
				relationName.setValue(rel.getName());
				
				Attr relationRelType = this.doc.createAttribute("relatype");
				relationRelType.setValue(rel.getRelType());
				
				relation.setAttributeNode(relationName);
				relation.setAttributeNode(relationRelType);
				
				String actName = rel.getDependencyActivity().getTag();
				if (!actName.isEmpty()) {
					Attr actDependencyName = this.doc.createAttribute("dependency");
					actDependencyName.setValue(actName);
					relation.setAttributeNode(actDependencyName);
				}
				
				activity.appendChild(relation);
			}
			
			for (Field field : act.getFields()) {
				Element fields = this.doc.createElement("field");
				
				Attr fieldDecimalPlaces = this.doc.createAttribute("decimalplaces");
				fieldDecimalPlaces.setValue(field.getDecimalPlaces());
				
				Attr fieldName = this.doc.createAttribute("name");
				fieldName.setValue(field.getName());
				
				Attr fieldType = this.doc.createAttribute("type");
				fieldType.setValue(field.getType());
				
				fields.appendChild(fieldDecimalPlaces);
				fields.appendChild(fieldName);
				fields.appendChild(fieldType);
				
				if (!field.getInput().getName().isEmpty()) {
					Attr fieldInputName = this.doc.createAttribute("input");
					fieldInputName.setValue(field.getInput().getName());
					fields.appendChild(fieldInputName);
				}
				
				if (!field.getOutput().getName().isEmpty()) {
					Attr fieldOutputName = this.doc.createAttribute("output");
					fieldOutputName.setValue(field.getOutput().getName());
					fields.appendChild(fieldOutputName);
				}
				
				activity.appendChild(fields);
			}
			
			
			scicumulusConceptuaWorkflow.appendChild(activity);
		}
		
		this.root.appendChild(scicumulusConceptuaWorkflow);
	}

	private void writeQuery(Query query) {
		Element sciCumulusQuery = this.doc.createElement("query");
		
		Attr sciCumulusQuerySQL = this.doc.createAttribute("sql");
		sciCumulusQuerySQL.setNodeValue(query.getQuery());
		
		sciCumulusQuery.setAttributeNode(sciCumulusQuerySQL);
		
		this.root.appendChild(sciCumulusQuery);
	}

	private void writeWorkspace(Workspace workspace) {
		Element sciCumulusWorkspace = this.doc.createElement("workspace");
		
		Attr sciCumulusWorkspaceUpload = this.doc.createAttribute("upload");
		sciCumulusWorkspaceUpload.setNodeValue(workspace.getUpload());
		
		Attr sciCumulusWorkspaceBucketName = this.doc.createAttribute("bucket_name");
		sciCumulusWorkspaceBucketName.setNodeValue(workspace.getBucketName());
		
		Attr sciCumulusWorkspaceWorkflowDir = this.doc.createAttribute("workflow_dir");
		sciCumulusWorkspaceWorkflowDir.setNodeValue(workspace.getWorkflowDir());
		
		Attr sciCumulusWorkspaceCompressedWorkspace = this.doc.createAttribute("compressed_workspace");
		sciCumulusWorkspaceCompressedWorkspace.setNodeValue(workspace.getCompressedWorkspace());
		
		Attr sciCumulusWorkspaceCompressedDir = this.doc.createAttribute("compressed_dir");
		sciCumulusWorkspaceCompressedDir.setNodeValue(workspace.getCompressedDir());
		
		sciCumulusWorkspace.setAttributeNode(sciCumulusWorkspaceUpload);
		sciCumulusWorkspace.setAttributeNode(sciCumulusWorkspaceBucketName);
		sciCumulusWorkspace.setAttributeNode(sciCumulusWorkspaceWorkflowDir);
		sciCumulusWorkspace.setAttributeNode(sciCumulusWorkspaceCompressedWorkspace);
		sciCumulusWorkspace.setAttributeNode(sciCumulusWorkspaceCompressedDir);
		
		this.root.appendChild(sciCumulusWorkspace);
	}

	private void writeEnvironment(Environment environ) {
		Element sciCumulusenvironment = this.doc.createElement("environment");
		
		Attr sciCumulusEnvironmentverbose = this.doc.createAttribute("verbose");
		sciCumulusEnvironmentverbose.setNodeValue(environ.getVerbose());
		
		Attr sciCumulusEnvironmenttype = this.doc.createAttribute("type");
		sciCumulusEnvironmenttype.setNodeValue(environ.getType());
		
		Attr sciCumulusEnvironmentClusterName = this.doc.createAttribute("cluster_name");
		sciCumulusEnvironmentClusterName.setNodeValue(environ.getClusterName());
		
		sciCumulusenvironment.setAttributeNode(sciCumulusEnvironmentverbose);
		sciCumulusenvironment.setAttributeNode(sciCumulusEnvironmenttype);
		sciCumulusenvironment.setAttributeNode(sciCumulusEnvironmentClusterName);
		
		this.root.appendChild(sciCumulusenvironment);
	}

	private void writeDatabase(Database database) {
		Element sciCumulusDatabase = this.doc.createElement("database");
		
		Attr sciCumulusDatabaseName = this.doc.createAttribute("name");
		sciCumulusDatabaseName.setNodeValue(database.getName());
		
		Attr sciCumulusDatabaseUsername = this.doc.createAttribute("Username");
		sciCumulusDatabaseUsername.setNodeValue(database.getUserName());
		
		Attr sciCumulusDatabasePassword = this.doc.createAttribute("password");
		sciCumulusDatabasePassword.setNodeValue(database.getPassword());
		
		Attr sciCumulusDatabasePort = this.doc.createAttribute("port");
		sciCumulusDatabasePort.setNodeValue(database.getPort());
		
		Attr sciCumulusDatabaseServer = this.doc.createAttribute("server");
		sciCumulusDatabase.setNodeValue(database.getServer());
		
		Attr sciCumulusDatabasePath = this.doc.createAttribute("path");
		sciCumulusDatabase.setNodeValue(database.getPath());
		
		sciCumulusDatabase.setAttributeNode(sciCumulusDatabaseName);
		sciCumulusDatabase.setAttributeNode(sciCumulusDatabaseUsername);
		sciCumulusDatabase.setAttributeNode(sciCumulusDatabasePassword);
		sciCumulusDatabase.setAttributeNode(sciCumulusDatabasePort);
		sciCumulusDatabase.setAttributeNode(sciCumulusDatabaseServer);
		sciCumulusDatabase.setAttributeNode(sciCumulusDatabasePath);
		
		
		
		
		root.appendChild(sciCumulusDatabase);
		
	}

	private void writeCredentials(Credentials cred) {
		Element sciCumulusCredentials = this.doc.createElement("credentials");
		
		
		Attr sciCumulusBinaryAccessKey = this.doc.createAttribute("access_key");
		sciCumulusBinaryAccessKey.setNodeValue(cred.getAccessKey());
		
		Attr sciCumulusBinarySecretAccessKey = this.doc.createAttribute("secret_access_key");
		sciCumulusBinarySecretAccessKey.setNodeValue(cred.getSecretAccessKey());		
		
		sciCumulusCredentials.setAttributeNode(sciCumulusBinaryAccessKey);
		sciCumulusCredentials.setAttributeNode(sciCumulusBinarySecretAccessKey);
		
		
		
		this.root.appendChild(sciCumulusCredentials);
		
	}

	private void writeBinary(Binary binary) {
		Element sciCumulusBinary = this.doc.createElement("binary");
		
		Attr sciCumulusBinaryDirectory = this.doc.createAttribute("directory");
		sciCumulusBinaryDirectory.setNodeValue(binary.getDirectory()); 
		
		Attr sciCumulusBinaryConceptualVersion = this.doc.createAttribute("conceptual_version");
		sciCumulusBinaryConceptualVersion.setNodeValue(binary.getConceptualVersion());
		
		Attr sciCumulusBinaryExecutionVersion = this.doc.createAttribute("execution_version");
		sciCumulusBinaryExecutionVersion.setNodeValue(binary.getExecutionVersion());
		
		Attr sciCumulusBinaryStarterVersion = this.doc.createAttribute("starter_version");
		sciCumulusBinaryStarterVersion.setNodeValue(binary.getStarterVersion());
		
		Attr sciCumulusBinaryQueryVersion = this.doc.createAttribute("query_version");
		sciCumulusBinaryQueryVersion.setNodeValue(binary.getQueryVersion());
		
		sciCumulusBinary.setAttributeNode(sciCumulusBinaryDirectory);
		sciCumulusBinary.setAttributeNode(sciCumulusBinaryConceptualVersion);
		sciCumulusBinary.setAttributeNode(sciCumulusBinaryExecutionVersion);
		sciCumulusBinary.setAttributeNode(sciCumulusBinaryStarterVersion);
		sciCumulusBinary.setAttributeNode(sciCumulusBinaryQueryVersion);
		
		
		
		this.root.appendChild(sciCumulusBinary);
	}
}
