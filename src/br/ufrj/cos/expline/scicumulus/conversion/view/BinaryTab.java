package br.ufrj.cos.expline.scicumulus.conversion.view;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BinaryTab extends JPanel
{
	private JLabel lbDirectory;
	private JTextField tfDirectory;	
	private JLabel lbConceptualVersion;
	private JTextField tfConceptualVersion;
	private JLabel lbExecutionVersion;
	private JTextField tfExecutionVersion;
	private JLabel lbStarterVersion;
	private JTextField tfStarterVersion;
	private JLabel lbQueryVersion;
	private JTextField tfQueryVersion;
		
	public BinaryTab()
	{
		super();
		
		initComponents();
		
		this.setVisible(true);
	}

	private void initComponents() {
		// TODO Auto-generated method stub
//		this.setLayout(new GridLayout(5,2));
		this.setLayout(null);
		
		lbDirectory = new JLabel("Directory: ");
		lbDirectory.setBounds(10,10,70,15);
		this.add(lbDirectory);
		
		tfDirectory = new JTextField();
		tfDirectory.setBounds(lbDirectory.getWidth(), 10, 410, 20);
		this.add(tfDirectory);
		
		lbConceptualVersion = new JLabel("Conceptual Version: ");
		lbConceptualVersion.setBounds(10,10+lbDirectory.getHeight()+lbDirectory.getY(),130,15);
		this.add(lbConceptualVersion);
		
		tfConceptualVersion = new JTextField();
		tfConceptualVersion.setBounds(lbConceptualVersion.getWidth(), lbConceptualVersion.getY(), 350, 20);
		this.add(tfConceptualVersion);
		
		lbExecutionVersion = new JLabel("Execution Version: ");
		lbExecutionVersion.setBounds(10,10+lbConceptualVersion.getHeight()+lbConceptualVersion.getY(),120,15);
		this.add(lbExecutionVersion);
		
		tfExecutionVersion = new JTextField();
		tfExecutionVersion.setBounds(lbExecutionVersion.getWidth(), lbExecutionVersion.getY(), 360, 20);
		this.add(tfExecutionVersion);
		
		lbStarterVersion = new JLabel("Starter Version: ");
		lbStarterVersion.setBounds(10,10+lbExecutionVersion.getHeight()+lbExecutionVersion.getY(),105,15);
		this.add(lbStarterVersion);
		
		tfStarterVersion = new JTextField();
		tfStarterVersion.setBounds(lbStarterVersion.getWidth(), lbStarterVersion.getY(), 375, 20);
		this.add(tfStarterVersion);
		
		lbQueryVersion = new JLabel("Query Version: ");
		lbQueryVersion.setBounds(10,10+lbStarterVersion.getHeight()+lbStarterVersion.getY(),100,15);
		this.add(lbQueryVersion);
		
		tfQueryVersion = new JTextField();
		tfQueryVersion.setBounds(lbQueryVersion.getWidth(), lbQueryVersion.getY(), 380, 20);
		this.add(tfQueryVersion);
		
	}
	
	public boolean checkFilledOut()
	{
		if(tfDirectory.getText().equals(""))
		{
			System.out.println("Binary");
			return false;	
		}
		else if(tfConceptualVersion.getText().equals(""))
		{
			System.out.println("Binary");
			return false;
		}
		else if(tfExecutionVersion.getText().equals(""))
		{
			System.out.println("Binary");
			return false;
		}
		else if(tfStarterVersion.getText().equals(""))
		{
			System.out.println("Binary");
			return false;
		}
		else if(tfQueryVersion.getText().equals(""))
		{
			System.out.println("Binary");
			return false;
		}
		
		return true;
	}
	
	public String getDirectory()
	{
		return tfDirectory.getText();
	}
	
	public String getConceptualVersion()
	{
		return tfConceptualVersion.getText();
	}
	
	public String getExecutionVersion()
	{
		return tfExecutionVersion.getText();
	}
	
	public String getStarterVersion()
	{
		return tfStarterVersion.getText();
	}
	
	public String getQueryVersion()
	{
		return tfQueryVersion.getText();
	}
}
