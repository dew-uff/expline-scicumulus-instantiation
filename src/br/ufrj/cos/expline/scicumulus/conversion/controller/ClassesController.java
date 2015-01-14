package br.ufrj.cos.expline.scicumulus.conversion.controller;

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
	
	public ClassesController(File explineAbstractWorkflow, File ScicumulusWorkflow)
	{
		this.explineAbstractWorkflow = explineAbstractWorkflow;
		this.ScicumulusWorkflow = ScicumulusWorkflow;
		initProperties();
		initComponents();
		
	}
	
	private void initProperties()
	{
		this.properties = new HashMap<>();
		
		//Database node
		properties.put("DatabaseName", "scc2");
		properties.put("DatabaseUsername", "postgres");
		properties.put("DatabasePassword", "pass");
		properties.put("DatabasePort", "5432");
		properties.put("DatabaseServer", "servidor bd ec2");
		properties.put("DatabasePath", "/var/lib/pgsql");
		
		//Workspace node
		properties.put("WorkspaceUpload", "true");
		properties.put("WorkspaceBucketName", "vitor-starter");
		properties.put("WorkspaceWorkflowDir", "/root/workflow_1");
		properties.put("WorkspaceCompressedWorkspace", "temp_workflow_1.zip");
		properties.put("CompressedDir","temp_workflow_1");
		
		//Credentials node
		properties.put("AccessKey","chave de acesso");
		properties.put("SecretAccessKey","chave secreta de acesso");
		
		//Binary node
		properties.put("Directory","/root/programs");
		properties.put("ConceptualVersion","SciCumulusSetup.jar");
		properties.put("ExecutionVersion","SciCumulusCore.jar");
		properties.put("StarterVersion","SciCumulusStarter.jar");
		properties.put("QueryVersion","SciCumulusStarter.jar");
		
		//Executionworkflow node
		properties.put("WorkflowExpdir","%=WFDIR%/exp");
		
		properties.put("WorkflowRelationName","IMod1Act1");
		properties.put("WorkflowRelationFilename","Input.dataset");
		
		//query Node
		properties.put("QuerySQL", "select ea.taskid, ea.actid, ea.machineid, ea.status from eactivation as ea;");
	}
	
	private void initComponents()
	{
		writer = new Conversion_writer(this.ScicumulusWorkflow,this.properties);
		reader = new Conversion_reader(this.writer,this.explineAbstractWorkflow,this.properties);
		
		mainWindow = new MainWindow(this.properties);
		
		writer.saveDocumentToDisk(Util.getOnlyActivities(this.properties));
	}
	
}
