package br.ufrj.cos.expline.instantiation.scicumulus;

import java.io.File;

public class ScicumulusInstantiator implements Instantiator{

	@Override
	public void instantiate(File explineAbstractWorkflow, File ScimulusWorkflow) {
		
		
	}

	
	public static void main(String[] args){
		
		Instantiator scicumulusInstantiator = new ScicumulusInstantiator();
		
		scicumulusInstantiator.instantiate(null, null);
	}
	
}
