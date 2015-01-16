package br.ufrj.cos.expline.scicumulus.conversion.view;

import java.io.File;

import br.ufrj.cos.expline.scicumulus.conversion.controller.ClassesController;

public class Main 
{
	public static void main(String[] args)
	{
		File read = new File("C:\\Users\\t3gq\\AppData\\Local\\Temp\\abstractWorkflow930323260384909305.xml");
		File writer = new File("src/othersource/concrete.xml");
		
		
		new ClassesController(null, read,writer);
	}
}
