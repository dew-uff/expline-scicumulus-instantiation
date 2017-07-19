package br.ufrj.cos.expline.scicumulus.conversion.mainTest;

import java.io.File;

import br.ufrj.cos.expline.scicumulus.conversion.instantiator.ScicumulusInstantiator;

public class ExecutionTest {

	public static void main(String[] args) {
//		File inputFile = new File ("D://Projects//Expline-Scicumulus-Instantiation//othersource//Tests//test21.xml");
		File inputFile = new File ("D://Projects//Expline-Scicumulus-Instantiation//othersource//AbstractWorkflow-ScicumulusExample.xml");
		File outputFile = new File ("D://Projects//Expline-Scicumulus-Instantiation//othersource//Tests//test21_exit.xml");
		ScicumulusInstantiator instantiator = new ScicumulusInstantiator();
		instantiator.instantiate(null, inputFile, outputFile);
	}

}
