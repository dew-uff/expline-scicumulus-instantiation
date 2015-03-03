package br.ufrj.cos.expline.scicumulus.conversion.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EnvironmentTab extends JPanel{
	
	private JLabel lbClusterName;
	private JTextField tfClusterName;	
	private JLabel lbType;
	private JTextField tfType;
	private JLabel lbVerbose;
	private JTextField tfVerbose;
	
	
	private GridBagLayout layout;

	private GridBagConstraints constraints;
	
	public EnvironmentTab()
	{
		super();
		
		initComponents();
		
		this.setVisible(true);
		
//		initLayout();
	}

	private void initComponents() {
		// TODO Auto-generated method stub

		this.setLayout(null);
//		this.setLayout(layout);

		
		lbClusterName = new JLabel("Cluster Name: ");
		lbClusterName.setBounds(10,10,95,15);
		this.add(lbClusterName);
		
		tfClusterName = new JTextField();
		tfClusterName.setText("vitor");
		tfClusterName.setEnabled(false);
		tfClusterName.setBounds(lbClusterName.getWidth(), 10, 385, 20);
		this.add(tfClusterName);
		
		lbType = new JLabel("Type: ");
		lbType.setBounds(10,10+lbClusterName.getHeight()+lbClusterName.getY(),45,15);
		this.add(lbType);
		
		tfType = new JTextField();
		tfType.setText("CLOUD");
		tfType.setEnabled(false);
		tfType.setBounds(lbType.getWidth(), lbType.getY(), 435, 20);
		this.add(tfType);
		
		lbVerbose = new JLabel("Verbose: ");
		lbVerbose.setBounds(10,10+lbType.getHeight()+lbType.getY(),65,15);
		this.add(lbVerbose);
		
		tfVerbose = new JTextField();
		tfVerbose.setText("false");
		tfVerbose.setEnabled(false);
		tfVerbose.setBounds(lbVerbose.getWidth(), lbVerbose.getY(), 415, 20);
		this.add(tfVerbose);
		
		

	}
	
	private void initLayout()
	{

		constraints.fill = GridBagConstraints.BOTH;
//		addComponents(lbName, 0, 0, 2, 1);
		
		constraints.fill = GridBagConstraints.BOTH;
//		addComponents(tfName, 0, 2, 3, 1);

	}
	
	public boolean checkFilledOut()
	{
		
		if(tfClusterName.getText().equals(""))
		{
			System.out.println("Enviroment");
			return false;	
		}
		else if(tfType.getText().equals(""))
		{
			System.out.println("Enviroment");
			return false;
		}
		else if(tfVerbose.getText().equals(""))
		{
			System.out.println("Enviroment");
			return false;
		}
		
		return true;
	}
	
	public String getClusterName()
	{
		return tfClusterName.getText();
	}
	
	public String getType()
	{
		return tfType.getText();
	}
	
	public String getVerbose()
	{
		return tfVerbose.getText();
	}
}
