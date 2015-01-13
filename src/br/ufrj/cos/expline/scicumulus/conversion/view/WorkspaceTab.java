package br.ufrj.cos.expline.scicumulus.conversion.view;

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
	}

	private void initComponents() {
		// TODO Auto-generated method stub
		this.setLayout(null);
		
		lbUpload = new JLabel("Upload: ");
		lbUpload.setBounds(10,10,55,15);
		this.add(lbUpload);
		
		tfUpload = new JTextField();
		tfUpload.setBounds(lbUpload.getWidth(), 10, 425, 20);
		this.add(tfUpload);
		
		lbBucketName = new JLabel("Bucket Name: ");
		lbBucketName.setBounds(10,10+lbUpload.getHeight()+lbUpload.getY(),95,15);
		this.add(lbBucketName);
		
		tfBucketName = new JTextField();
		tfBucketName.setBounds(lbBucketName.getWidth(), lbBucketName.getY(), 385, 20);
		this.add(tfBucketName);
		
		lbWorkflowDir = new JLabel("Workflow Dir: ");
		lbWorkflowDir.setBounds(10,10+lbBucketName.getHeight()+lbBucketName.getY(),90,15);
		this.add(lbWorkflowDir);
		
		tfWorkflowDir = new JTextField();
		tfWorkflowDir.setBounds(lbWorkflowDir.getWidth(), lbWorkflowDir.getY(), 390, 20);
		this.add(tfWorkflowDir);
		
		lbCompressedWorkspace = new JLabel("Compressed Workspace: ");
		lbCompressedWorkspace.setBounds(10,10+lbWorkflowDir.getHeight()+lbWorkflowDir.getY(),160,15);
		this.add(lbCompressedWorkspace);
		
		tfCompressedWorkspace = new JTextField();
		tfCompressedWorkspace.setBounds(lbCompressedWorkspace.getWidth(), lbCompressedWorkspace.getY(), 320, 20);
		this.add(tfCompressedWorkspace);
		
		lbCompressedDir = new JLabel("Compressed Dir: ");
		lbCompressedDir.setBounds(10,10+lbCompressedWorkspace.getHeight()+lbCompressedWorkspace.getY(),110,15);
		this.add(lbCompressedDir);
		
		tfCompressedDir = new JTextField();
		tfCompressedDir.setBounds(lbCompressedDir.getWidth(), lbCompressedDir.getY(), 370, 20);
		this.add(tfCompressedDir);
		
	}
}
