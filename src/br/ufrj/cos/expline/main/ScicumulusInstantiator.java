package br.ufrj.cos.expline.main;

import java.io.File;


import java.util.HashMap;
import java.util.Map;

import br.ufrj.cos.expline.scicumulus.conversion.Conversion_reader;
import br.ufrj.cos.expline.scicumulus.conversion.Conversion_writer;

public class ScicumulusInstantiator implements Instantiator{

	@Override
	public void instantiate(File explineAbstractWorkflow, File ScimulusWorkflow) {
		Conversion_writer writer = new Conversion_writer(ScimulusWorkflow,null);
		new Conversion_reader(writer,explineAbstractWorkflow);
		writer.saveDocumentToDisk();
		
	}
	
	@Override
	public void instantiate(File explineAbstractWorkflow, File ScimulusWorkflow, Map<String, String> properties) {
		
		Conversion_writer writer = new Conversion_writer(ScimulusWorkflow,properties);
		new Conversion_reader(writer,explineAbstractWorkflow);
		writer.saveDocumentToDisk();
		
	}

	
	public static void main(String[] args){
		
		Instantiator scicumulusInstantiator = new ScicumulusInstantiator();
		File read = new File("src/othersource/AbstractWorkflow-ScicumulusExample.xml");
		File writer = new File("src/othersource/concrete.xml");
		Map<String,String> properties = new HashMap<String, String>();
		
		
		properties.put("ActivityActivation", "-");
		
		//Database node
		properties.put("DatabaseName", "-");
		properties.put("DatabaseUsername", "-");
		properties.put("DatabasePassword", "-");
		properties.put("DatabasePort", "-");
		properties.put("DatabaseServer", "-");
		properties.put("DatabasePath", "-");
		
		//Workspace node
		properties.put("WorkspaceUpload", "-");
		properties.put("WorkspaceBucketName", "-");
		properties.put("WorkspaceWorkflowDir", "-");
		properties.put("WorkspaceCompressedWorkspace", "-");
		properties.put("CompressedDir","-");
		
		//Credentials node
		properties.put("AccessKey","-");
		properties.put("SecretAccessKey","-");
		
		//Binary node
		properties.put("Directory","-");
		properties.put("ConceptualVersion","-");
		properties.put("ExecutionVersion","-");
		properties.put("StarterVersion","-");
		properties.put("QueryVersion","-");
		
		//Executionworkflow node
		properties.put("WorkflowExpdir","-");
		properties.put("WorkflowRelationName","-");
		properties.put("WorkflowRelationFilename","-");
		
		
		scicumulusInstantiator.instantiate(read, writer, properties );
	}
	
}

