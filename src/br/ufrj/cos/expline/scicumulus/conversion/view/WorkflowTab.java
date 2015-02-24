package br.ufrj.cos.expline.scicumulus.conversion.view;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WorkflowTab extends JPanel 
{
	private JLabel lbDirectory;
	private JTextField tfDirectory;	
	private JLabel lbRelationName;
	private JTextField tfRelationName;
	private JLabel lbRelationFileName;
	private JTextField tfRelationFileName;
	
		
	public WorkflowTab()
	{
		super();
		
		initComponents();
		
		this.setVisible(true);
	}

	private void initComponents() {
		// TODO Auto-generated method stub
		this.setLayout(new GridLayout(3,2));
		
		lbDirectory = new JLabel("Exp Dir: ");
//		lbDirectory.setBounds(10,10,60,15);
		this.add(lbDirectory);
		
		tfDirectory = new JTextField();
//		tfDirectory.setBounds(lbDirectory.getWidth(), 10, 420, 20);
		this.add(tfDirectory);
		
		lbRelationName = new JLabel("Relation Name: ");
//		lbRelationName.setBounds(10,10+lbDirectory.getHeight()+lbDirectory.getY(),100,15);
		this.add(lbRelationName);
		
		tfRelationName = new JTextField();
//		tfRelationName.setBounds(lbRelationName.getWidth(), lbRelationName.getY(), 380, 20);
		this.add(tfRelationName);
		
		lbRelationFileName = new JLabel("Relation File Name: ");
//		lbRelationFileName.setBounds(10,10+lbRelationName.getHeight()+lbRelationName.getY(),120,15);
		this.add(lbRelationFileName);
		
		tfRelationFileName = new JTextField();
//		tfRelationFileName.setBounds(lbRelationFileName.getWidth(), lbRelationFileName.getY(), 360, 20);
		this.add(tfRelationFileName);
		
	}
	
	public boolean checkFilledOut()
	{
			
		if(tfDirectory.getText().equals("")) return false;	
		else if(tfRelationName.getText().equals("")) return false;
		else if(tfRelationFileName.getText().equals("")) return false;
							
		return true;
	}
	
	public String getExpDir()
	{
		return tfDirectory.getText();
	}
	
	public String getRelationName()
	{
		return tfRelationName.getText();
	}
	
	public String getRelationFileName()
	{
		return tfRelationFileName.getText();
	}
}
