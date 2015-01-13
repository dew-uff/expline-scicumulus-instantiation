package br.ufrj.cos.expline.scicumulus.conversion.view;

import java.util.Map;

import javax.swing.JPanel;

public class ActivityTab extends JPanel 
{
	private Map<String,String> onlyActivation;
	
	public ActivityTab(Map<String,String> onlyActivation)
	{
		super();
		this.onlyActivation = onlyActivation;
	}
}
