package br.ufrj.cos.expline.scicumulus.conversion.instantiator;

import java.awt.Frame;
import java.io.File;

import br.ufrj.cos.expline.scicumulus.conversion.engine.ScicumulusReader;
import br.ufrj.cos.expline.scicumulus.conversion.model.sciObjects.ScicumulusDocument;
import br.ufrj.cos.expline.scicumulus.conversion.view.MainWindow;

public class ScicumulusInstantiator implements Instantiator{

	@Override
	public void instantiate(Frame owner, File explineAbstractWorkflow, File SWfMSworkflow) {
		ScicumulusReader reader = new ScicumulusReader (explineAbstractWorkflow);
		ScicumulusDocument sciDoc = reader.Run();
		new MainWindow(owner, sciDoc, SWfMSworkflow);
	}

}
