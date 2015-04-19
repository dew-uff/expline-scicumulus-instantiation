package br.ufrj.cos.expline.scicumulus.conversion.controller;

import java.awt.Frame;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import br.ufrj.cos.expline.scicumulus.conversion.Conversion_reader;
import br.ufrj.cos.expline.scicumulus.conversion.Conversion_writer;
import br.ufrj.cos.expline.scicumulus.conversion.util.Util;
import br.ufrj.cos.expline.scicumulus.conversion.view.MainWindow;

public class ClassesController 
{
	private Conversion_reader reader;
	private Conversion_writer writer;
	private Map<String,String> properties;
	private File explineAbstractWorkflow;
	private File ScicumulusWorkflow;
	private MainWindow mainWindow;
	private final Frame owner;
	
	public ClassesController(Frame owner, File explineAbstractWorkflow, File ScicumulusWorkflow)
	{
		this.explineAbstractWorkflow = explineAbstractWorkflow;
		this.ScicumulusWorkflow = ScicumulusWorkflow;
		this.owner = owner;
		initProperties();
		initComponents();
		
	}
	
	private void initProperties()
	{
		this.properties = new HashMap<>();
		
		//Database node
		properties.put("DatabaseName", "");
		properties.put("DatabaseUsername", "");
		properties.put("DatabasePassword", "");
		properties.put("DatabasePort", "");
		properties.put("DatabaseServer", "");
		properties.put("DatabasePath", "");
		
		//Workspace node
		properties.put("WorkspaceUpload", "");
		properties.put("WorkspaceBucketName", "");
		properties.put("WorkspaceWorkflowDir", "");
		properties.put("WorkspaceCompressedWorkspace", "");
		properties.put("CompressedDir","");
		
		//Credentials node
		properties.put("AccessKey","");
		properties.put("SecretAccessKey","");
		
		//Binary node
		properties.put("Directory","");
		properties.put("ConceptualVersion","");
		properties.put("ExecutionVersion","");
		properties.put("StarterVersion","");
		properties.put("QueryVersion","");
		
		//Executionworkflow node
		properties.put("WorkflowExpdir","");
		
		//Relation (Executionworkflow child)
		properties.put("WorkflowRelationName","");
		properties.put("WorkflowRelationFilename","");
		
		//query Node
		properties.put("QuerySQL", "");
//		select ea.taskid, ea.actid, ea.machineid, ea.status from eactivation as ea;
	}
	
	private void initComponents()
	{
		writer = new Conversion_writer(this.ScicumulusWorkflow,this.properties);
		reader = new Conversion_reader(this.writer,this.explineAbstractWorkflow,this.properties);
		
		mainWindow = new MainWindow(owner, this.properties,this);
		
//		writer.saveDocumentToDisk(Util.getOnlyActivities(this.properties));
//		writer.saveDocumentToDisk();
	}
	
	public void finishXML(Map<String,String> propertiesDone,HashMap<String,HashMap<String,String>> workflowMap)
	{
		writer.insertAllActivations(Util.getOnlyActivities(propertiesDone));
		writer.insertAllUserInformation(propertiesDone);
		writer.insertWorkflowInformation(workflowMap);
		writer.saveDocumentToDisk();
	}
	
}
