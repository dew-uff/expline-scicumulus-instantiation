package br.ufrj.cos.expline.scicumulus.conversion.view;

import java.io.File;

import br.ufrj.cos.expline.scicumulus.conversion.mainInterface.Instantiator;
import br.ufrj.cos.expline.scicumulus.conversion.mainInterface.ScicumulusInstantiator;

public class Main 
{
	public static void main(String[] args)
	{
//		File read = new File("othersource/Example/example1.xml");
//		File read = new File("othersource/Example/example2.xml");
//		File read = new File("othersource/Example/example3.xml");
//		File read = new File("othersource/Example/example4.xml");
//		File read = new File("othersource/Example/example5.xml");
		File read = new File("othersource/Example/example6.xml");
//		File read = new File("othersource/AbstractWorkflow-ScicumulusExample.xml");
//		File read = new File("othersource/Join_test2.xml");		
//		File read = new File("othersource/teste_Join_Maluco.xml");
		File writer = new File("othersource/concrete_teste_Join_Maluco.xml");
		
		Instantiator instantiator = new ScicumulusInstantiator();
		instantiator.instantiate(null, read, writer);
	}
}
