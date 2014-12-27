package br.ufrj.cos.expline.main;

import java.io.File;

import br.ufrj.cos.expline.scicumulus.conversion.Conversion_reader;
import br.ufrj.cos.expline.scicumulus.conversion.Conversion_writer;

public class ScicumulusInstantiator implements Instantiator{

	@Override
	public void instantiate(File explineAbstractWorkflow, File ScimulusWorkflow) {
		Conversion_writer writer = new Conversion_writer(ScimulusWorkflow);
		new Conversion_reader(writer,explineAbstractWorkflow);
		writer.saveDocumentToDisk();
		
	}

	
	public static void main(String[] args){
		
		Instantiator scicumulusInstantiator = new ScicumulusInstantiator();
		File read = new File("C:\\Users\\claudio\\Desktop\\Repositories\\ProjetoFinal\\src\\othersource\\AbstractWorkflow-ScicumulusExample.xml");
		File writer = new File("C:\\Users\\claudio\\Desktop\\Repositories\\ProjetoFinal\\src\\othersource\\concrete.xml");
		scicumulusInstantiator.instantiate(read, writer);
	}
	
}
