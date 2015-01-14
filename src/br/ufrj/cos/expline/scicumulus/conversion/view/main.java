package br.ufrj.cos.expline.scicumulus.conversion.view;

import java.io.File;

import br.ufrj.cos.expline.scicumulus.conversion.controller.ClassesController;

public class main 
{
	public static void main(String[] args)
	{
		File read = new File("src/othersource/AbstractWorkflow-ScicumulusExample.xml");
		File writer = new File("src/othersource/concrete.xml");
		new ClassesController(read,writer);
	}
}
