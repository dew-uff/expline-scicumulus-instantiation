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
		
		//inserindo dados
		properties.put("login", "andymarinho");
		properties.put("senha", "1234");
		
		
		
		
		properties.put("CompressedDir","-");
		properties.put("AccessKey","-");
		properties.put("SecretAccessKey","-");
		properties.put("Directory","-");
		properties.put("ConceptualVersion","-");
		properties.put("ExecutionVersion","-");
		properties.put("StarterVersion","-");
		properties.put("QueryVersion","-");
		properties.put("WorkflowExpdir","-");
		properties.put("WorkflowRelationName","-");
		properties.put("WorkflowRelationFilename","-");
		
		Conversion_writer writer = new Conversion_writer(ScimulusWorkflow,properties);
		new Conversion_reader(writer,explineAbstractWorkflow);
		writer.saveDocumentToDisk();
		
		
		//resgatando dados
				String login = properties.get("login");
	}

	
	public static void main(String[] args){
		
		Instantiator scicumulusInstantiator = new ScicumulusInstantiator();
		File read = new File("src/othersource/AbstractWorkflow-ScicumulusExample.xml");
		File writer = new File("src/othersource/concrete.xml");
		HashMap<String,String> auxTempMap = new HashMap<String, String>();
		scicumulusInstantiator.instantiate(read, writer, auxTempMap );
	}
	
}
