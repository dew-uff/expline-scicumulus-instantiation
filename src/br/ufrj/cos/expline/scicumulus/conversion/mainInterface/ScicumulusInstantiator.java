package br.ufrj.cos.expline.scicumulus.conversion.mainInterface;

import java.awt.Frame;
import java.io.File;


import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import br.ufrj.cos.expline.scicumulus.conversion.Conversion_reader;
import br.ufrj.cos.expline.scicumulus.conversion.Conversion_writer;
import br.ufrj.cos.expline.scicumulus.conversion.controller.ClassesController;
import br.ufrj.cos.expline.scicumulus.conversion.util.Util;

public class ScicumulusInstantiator implements Instantiator{

	@Override
	public void instantiate(Frame owner, File explineAbstractWorkflow, File ScimulusWorkflow) {
		
		new ClassesController(owner, explineAbstractWorkflow,ScimulusWorkflow);
	}
	
////	@Override
//	public void instantiateTest(File explineAbstractWorkflow, File ScimulusWorkflow, Map<String, String> properties) {
//		
//		Conversion_writer writer = new Conversion_writer(ScimulusWorkflow,properties);
//		new Conversion_reader(writer,explineAbstractWorkflow,properties);
//		Map<String,String> temp = Util.getOnlyActivities(properties);
//		
//		/*
//		for(String p:temp.keySet())
//		{
//		System.out.println("tag:");
//		System.out.println(p);
//		//temp.put(p, "java -jar /root/programs/Sleep.jar ID=%=ID% T2=%=T2%");
//		}
//		*/
//		
//		
//		
//		writer.insertAllActivations(temp);
//		writer.insertAllUserInformation(properties);
//		writer.saveDocumentToDisk();
//		
//		
//	}
//
//	
	public static void main(String[] args){
		
		Instantiator scicumulusInstantiator = new ScicumulusInstantiator();
	
//		File read = new File("othersource/abstractWorkflowGeradoPeloExpLine.xml");
		File read = new File("othersource/teste_Join_Maluco.xml");
		File writer = new File("othersource/SciCumulus-wp_TESTE_Join.xml");
		Map<String,String> properties = new HashMap<String, String>();
		scicumulusInstantiator.instantiate(new JFrame(), read, writer);
		
		
//		//activations
//		properties.put("activity_act1", "java -jar /root/programs/Sleep.jar ID=%=ID% T2=%=T2%");
//		properties.put("activity_act2", "teste");
//		properties.put("activity_act3", "teste1");
//		
//		
//		
//		//Database node
//		properties.put("DatabaseName", "scc2");
//		properties.put("DatabaseUsername", "postgres");
//		properties.put("DatabasePassword", "pass");
//		properties.put("DatabasePort", "5432");
//		properties.put("DatabaseServer", "servidor bd ec2");
//		properties.put("DatabasePath", "/var/lib/pgsql");
//		
//		//Workspace node
//		properties.put("WorkspaceUpload", "true");
//		properties.put("WorkspaceBucketName", "vitor-starter");
//		properties.put("WorkspaceWorkflowDir", "/root/workflow_1");
//		properties.put("WorkspaceCompressedWorkspace", "temp_workflow_1.zip");
//		properties.put("CompressedDir","temp_workflow_1");
//		
//		//Credentials node
//		properties.put("AccessKey","chave de acesso");
//		properties.put("SecretAccessKey","chave secreta de acesso");
//		
//		//Binary node
//		properties.put("Directory","/root/programs");
//		properties.put("ConceptualVersion","SciCumulusSetup.jar");
//		properties.put("ExecutionVersion","SciCumulusCore.jar");
//		properties.put("StarterVersion","SciCumulusStarter.jar");
//		properties.put("QueryVersion","SciCumulusStarter.jar");
//		
//		//Executionworkflow node
//		properties.put("WorkflowExpdir","%=WFDIR%/exp");
//		properties.put("WorkflowRelationName","IMod1Act1");
//		properties.put("WorkflowRelationFilename","Input.dataset");
//		
//		//query Node
//		properties.put("QuerySQL", "select ea.taskid, ea.actid, ea.machineid, ea.status from eactivation as ea;");
//		
//		//Activity dentro do metodo Instantiate
//		
////		(ScicumulusInstantiator)scicumulusInstantiator.(read, writer,properties); //forma usando o properties criado aqui, sem interface grafica
//		
//		//scicumulusInstantiator.instantiate(read, writer);
	}
	
}

