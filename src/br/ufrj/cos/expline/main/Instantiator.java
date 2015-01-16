package br.ufrj.cos.expline.main;

import java.awt.Frame;
import java.io.File;
import java.util.Map;

public interface Instantiator {

	public void instantiate(Frame owner, File explineAbstractWorkflow, File SWfMSworkflow);
	
	public void instantiate(File explineAbstractWorkflow, File SWfMSworkflow, Map<String, String> properties);
	
}
