package br.ufrj.cos.expline.scicumulus.conversion.instantiator;

import java.awt.Frame;
import java.io.File;

public interface Instantiator {

	public void instantiate(Frame owner, File explineAbstractWorkflow, File SWfMSworkflow);
	
}
