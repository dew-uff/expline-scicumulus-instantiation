package br.ufrj.cos.expline.scicumulus.conversion.view;

import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WorkspaceTab extends JPanel
{
	private JLabel lbUpload;
	private JTextField tfUpload;	
	private JLabel lbBucketName;
	private JTextField tfBucketName;
	private JLabel lbWorkflowDir;
	private JTextField tfWorkflowDir;
	private JLabel lbCompressedWorkspace;
	private JTextField tfCompressedWorkspace;
	private JLabel lbCompressedDir;
	private JTextField tfCompressedDir;
		
	public WorkspaceTab()
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
								.addComponent(lbUpload)
								.addComponent(lbBucketName)
								.addComponent(lbWorkflowDir)
								.addComponent(lbCompressedWorkspace)
								.addComponent(lbCompressedDir)
						)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(tfUpload)
								.addComponent(tfBucketName)
								.addComponent(tfWorkflowDir)
								.addComponent(tfCompressedWorkspace)
								.addComponent(tfCompressedDir)
						)
						.addContainerGap(100,100)
				)
		);
		
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lbUpload)
								.addComponent(tfUpload)
						)
						.addContainerGap(15,15)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lbBucketName)
								.addComponent(tfBucketName)
						)
						.addContainerGap(15,15)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lbWorkflowDir)
								.addComponent(tfWorkflowDir)
						)
						.addContainerGap(15,15)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lbCompressedWorkspace)
								.addComponent(tfCompressedWorkspace)
						)
						.addContainerGap(15,15)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lbCompressedDir)
								.addComponent(tfCompressedDir)
						)
						.addContainerGap()
				)
		);
		
		this.setLayout(layout);
	}

	private void initComponents() {
		// TODO Auto-generated method stub
//		this.setLayout(new GridLayout(5,2));
		this.setLayout(null);
		
		lbUpload = new JLabel("Upload: ");
//		lbUpload.setBounds(10,10,55,15);
//		this.add(lbUpload);
		
		tfUpload = new JTextField();
//		tfUpload.setBounds(lbUpload.getWidth(), 10, 425, 20);
//		this.add(tfUpload);
		
		lbBucketName = new JLabel("Bucket Name: ");
//		lbBucketName.setBounds(10,10+lbUpload.getHeight()+lbUpload.getY(),95,15);
//		this.add(lbBucketName);
		
		tfBucketName = new JTextField();
//		tfBucketName.setBounds(lbBucketName.getWidth(), lbBucketName.getY(), 385, 20);
//		this.add(tfBucketName);
		
		lbWorkflowDir = new JLabel("Workflow Dir: ");
//		lbWorkflowDir.setBounds(10,10+lbBucketName.getHeight()+lbBucketName.getY(),90,15);
//		this.add(lbWorkflowDir);
		
		tfWorkflowDir = new JTextField();
//		tfWorkflowDir.setBounds(lbWorkflowDir.getWidth(), lbWorkflowDir.getY(), 390, 20);
//		this.add(tfWorkflowDir);
		
		lbCompressedWorkspace = new JLabel("Compressed Workspace: ");
//		lbCompressedWorkspace.setBounds(10,10+lbWorkflowDir.getHeight()+lbWorkflowDir.getY(),160,15);
//		this.add(lbCompressedWorkspace);
		
		tfCompressedWorkspace = new JTextField();
//		tfCompressedWorkspace.setBounds(lbCompressedWorkspace.getWidth(), lbCompressedWorkspace.getY(), 320, 20);
//		this.add(tfCompressedWorkspace);
		
		lbCompressedDir = new JLabel("Compressed Dir: ");
//		lbCompressedDir.setBounds(10,10+lbCompressedWorkspace.getHeight()+lbCompressedWorkspace.getY(),110,15);
//		this.add(lbCompressedDir);
		
		tfCompressedDir = new JTextField();
//		tfCompressedDir.setBounds(lbCompressedDir.getWidth(), lbCompressedDir.getY(), 370, 20);
//		this.add(tfCompressedDir);
		
	}
	
	 
	public boolean checkFilledOut()
	{
			
		if(tfUpload.getText().equals(""))
		{
			System.out.println("Workspace");
			return false;	
		}
		else if(tfBucketName.getText().equals(""))
		{
			System.out.println("Workspace");
			return false;
		}
		else if(tfWorkflowDir.getText().equals(""))
		{
			System.out.println("Workspace");
			return false;
		}
		else if(tfCompressedWorkspace.getText().equals(""))
		{
			System.out.println("Workspace");
			return false;
		}
		else if(tfCompressedDir.getText().equals(""))
		{
			System.out.println("Workspace");
			return false;
		}
					
		return true;
	}
	
	public String getUpload()
	{
		return tfUpload.getText();
	}
	
	public String getBucketName()
	{
		return tfBucketName.getText();
	}
	
	public String getWorkflowDir()
	{
		return tfWorkflowDir.getText();
	}
	
	public String getCompressedWorkspace()
	{
		return tfCompressedWorkspace.getText();
	}
	
	public String getCompressedDir()
	{
		return tfCompressedDir.getText();
	}
}
