package br.ufrj.cos.expline.scicumulus.conversion.main;

import java.awt.Frame;
import java.io.File;
import java.util.Map;

public interface Instantiator {

	public void instantiate(Frame owner, File explineAbstractWorkflow, File SWfMSworkflow);
	
}
