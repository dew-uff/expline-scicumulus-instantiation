package br.ufrj.cos.expline.main;

import br.ufrj.cos.expline.scicumulus.convertion.Convertion_reader;
import br.ufrj.cos.expline.scicumulus.convertion.Convertion_writer;
import br.ufrj.cos.expline.scicumulus.convertion.IWriter;

public class Main 
{
	public static void main(String [] args)
	{
		Convertion_writer writer = new Convertion_writer();
		writer.scicumulusMainNodeCreation();
		writer.scicumulusConceptualWorkflowCreation();
		new Convertion_reader(writer);
		writer.saveDocumentToDisk();
	}
}
