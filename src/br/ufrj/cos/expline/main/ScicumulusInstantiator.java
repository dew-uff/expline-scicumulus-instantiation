package br.ufrj.cos.expline.main;

import java.io.File;
import java.util.Map;

import br.ufrj.cos.expline.scicumulus.conversion.Conversion_reader;
import br.ufrj.cos.expline.scicumulus.conversion.Conversion_writer;

public class ScicumulusInstantiator implements Instantiator{

	@Override
	public void instantiate(File explineAbstractWorkflow, File ScimulusWorkflow) {
		Conversion_writer writer = new Conversion_writer(ScimulusWorkflow);
		new Conversion_reader(writer,explineAbstractWorkflow);
		writer.saveDocumentToDisk();
		
	}
	
	@Override
	public void instantiate(File explineAbstractWorkflow, File ScimulusWorkflow, Map<String, String> properties) {
		
		//inserindo dados
		properties.put("login", "andymarinho");
		
		
		//resgatando dados
		String login = properties.get("login");
	}

	
	public static void main(String[] args){
		
		Instantiator scicumulusInstantiator = new ScicumulusInstantiator();
		File read = new File("src/othersource/AbstractWorkflow-ScicumulusExample.xml");
		File writer = new File("src/othersource/concrete.xml");
		scicumulusInstantiator.instantiate(read, writer);
	}
	
}
