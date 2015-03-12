package br.ufrj.cos.expline.scicumulus.conversion.view;

import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WorkflowTab extends JPanel 
{
	private JLabel lbExpDir;
	private JTextField tfExpDir;	
	private JLabel lbRelationName;
	private JTextField tfRelationName;
	private JLabel lbRelationFileName;
	private JTextField tfRelationFileName;
	
		
	public WorkflowTab()
	{
		super();
		
		initComponents();
		
		this.setVisible(true);
		
		initLayout();
	}

	private void initLayout() {
		// TODO Auto-generated method stub
		GroupLayout layout = new GroupLayout(this);
		
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(100,100)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addComponent(lbExpDir)
								.addComponent(lbRelationName)
								.addComponent(lbRelationFileName)
						)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(tfExpDir)
								.addComponent(tfRelationName)
								.addComponent(tfRelationFileName)
						)
						.addContainerGap(100,100)
				)
		);
		
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lbExpDir)
								.addComponent(tfExpDir)
						)
						.addContainerGap(15,15)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lbRelationName)
								.addComponent(tfRelationName)
						)
						.addContainerGap(15,15)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lbRelationFileName)
								.addComponent(tfRelationFileName)
						)
						.addContainerGap()
				)
		);
		
		this.setLayout(layout);
	}

	private void initComponents() {
		// TODO Auto-generated method stub
//		this.setLayout(new GridLayout(3,2));
//		this.setLayout(null);
		
		lbExpDir = new JLabel("Exp Dir: ");
//		lbDirectory.setBounds(10,10,60,15);
//		this.add(lbDirectory);
		
		tfExpDir = new JTextField();
//		tfDirectory.setBounds(lbDirectory.getWidth(), 10, 420, 20);
//		this.add(tfDirectory);
		
		lbRelationName = new JLabel("Relation Name: ");
//		lbRelationName.setBounds(10,10+lbDirectory.getHeight()+lbDirectory.getY(),100,15);
//		this.add(lbRelationName);
		
		tfRelationName = new JTextField();
//		tfRelationName.setBounds(lbRelationName.getWidth(), lbRelationName.getY(), 380, 20);
//		this.add(tfRelationName);
		
		lbRelationFileName = new JLabel("Relation File Name: ");
//		lbRelationFileName.setBounds(10,10+lbRelationName.getHeight()+lbRelationName.getY(),120,15);
//		this.add(lbRelationFileName);
		
		tfRelationFileName = new JTextField();
//		tfRelationFileName.setBounds(lbRelationFileName.getWidth(), lbRelationFileName.getY(), 360, 20);
//		this.add(tfRelationFileName);
		
	}
	
	public boolean checkFilledOut()
	{
			
		if(tfExpDir.getText().equals(""))
		{
			System.out.println("Workflow");
			return false;	
		}
		else if(tfRelationName.getText().equals(""))
		{
			System.out.println("Workflow");
			return false;
		}
		else if(tfRelationFileName.getText().equals(""))
		{
			System.out.println("Workflow");
			return false;
		}
							
		return true;
	}
	
	public String getExpDir()
	{
		return tfExpDir.getText();
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
