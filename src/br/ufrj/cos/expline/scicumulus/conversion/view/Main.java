package br.ufrj.cos.expline.scicumulus.conversion.view;

import java.io.File;

import br.ufrj.cos.expline.scicumulus.conversion.controller.ClassesController;
import br.ufrj.cos.expline.scicumulus.conversion.main.Instantiator;
import br.ufrj.cos.expline.scicumulus.conversion.main.ScicumulusInstantiator;

public class Main 
{
	public static void main(String[] args)
	{
		File read = new File("othersource/AbstractWorkflow-ScicumulusExample.xml");
		File writer = new File("othersource/concrete.xml");
		
		Instantiator instantiator = new ScicumulusInstantiator();
		instantiator.instantiate(null, read, writer);
	}
}
