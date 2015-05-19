package br.ufrj.cos.expline.scicumulus.conversion.view;

import java.io.File;

import br.ufrj.cos.expline.scicumulus.conversion.mainInterface.Instantiator;
import br.ufrj.cos.expline.scicumulus.conversion.mainInterface.ScicumulusInstantiator;

public class Main 
{
	public static void main(String[] args)
	{
		File read = new File("othersource/Join.xml");
		File writer = new File("othersource/concrete_teste_Join_Maluco.xml");
		
		Instantiator instantiator = new ScicumulusInstantiator();
		instantiator.instantiate(null, read, writer);
	}
}
