package br.ufrj.cos.expline.scicumulus.conversion.view;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import br.ufrj.cos.expline.scicumulus.conversion.Conversion_writer;
import br.ufrj.cos.expline.scicumulus.conversion.controller.ClassesController;
import br.ufrj.cos.expline.scicumulus.conversion.util.Util;

public class MainWindow extends JDialog 
{
	private Map<String,String> properties;
	private ClassesController classesController;
	private ButtonListener bl;
	
	private DataBaseTab databaseTab;
	private WorkspaceTab workspaceTab;
	private CredentialsTab credentialsTab;
	private BinaryTab binaryTab;
	private WorkflowTab workflowTab;	
	private ActivityTab activityTab;
	private QueryTab queryTab;
	
	public MainWindow(Frame owner, Map<String,String> properties, ClassesController classesController)
	{
		super(owner);
		
		this.classesController = classesController;
		
		this.properties = properties;
		
		this.setSize(600, 300);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setTitle("Scicumulus Instantiation");
				
		JTabbedPane jtp = new JTabbedPane();
		jtp.setBounds(0,0,this.getWidth()-5, this.getHeight()-70);
		//jtp.setSize(width, height);
		this.add(jtp);
		
		bl = new ButtonListener();
		
		JButton finishButton = new JButton("Finish");
		finishButton.addActionListener(bl);
		int diff = this.getHeight() - jtp.getHeight();
		finishButton.setBounds(this.getWidth() - 10 - 100,jtp.getHeight()+((diff /6)),100,25);
		this.add(finishButton);
		
		this.databaseTab 		= new DataBaseTab();
		this.workspaceTab 		= new WorkspaceTab();
		this.credentialsTab 	= new CredentialsTab();
		this.binaryTab			= new BinaryTab();
		this.workflowTab		= new WorkflowTab();
		this.activityTab		= new ActivityTab(Util.getOnlyActivities(this.properties));
//		this.activityTab		= new ActivityTab(Util.getFakeActivities());
		this.queryTab			= new QueryTab();

		
		jtp.addTab("Database", this.databaseTab );
		
		jtp.addTab("Workspace", this.workspaceTab );
		
		jtp.addTab("Credentials", this.credentialsTab );
		
		jtp.addTab("Binary", this.binaryTab );
		
		jtp.addTab("Workflow", this.workflowTab );
		
		jtp.addTab("Query", this.queryTab);
		
		jtp.addTab("Activities", this.activityTab );
						
		this.setResizable(false);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.setVisible(true);
		
		
		
		
	}
	
	public ClassesController getClassesController()
	{
		return this.classesController;
	}
	
	public Map<String,String> getProperties()
	{
		return this.properties;
	}
	
	public boolean hasEmptyField()
	{
		return !(databaseTab.checkFilledOut() &&
			   workspaceTab.checkFilledOut() &&
			   credentialsTab.checkFilledOut() &&
			   binaryTab.checkFilledOut() &&
			   workflowTab.checkFilledOut() &&
			   queryTab.checkFilledOut() &&
			   activityTab.checkFilledOut());
	}
	
	public Map<String,String> fillOutTheMap()
	{
		//Database node
		properties.put("DatabaseName", databaseTab.getName());
		properties.put("DatabaseUsername", databaseTab.getUserName());
		properties.put("DatabasePassword",databaseTab.getPassword());
		properties.put("DatabasePort", databaseTab.getPort());
		properties.put("DatabaseServer", databaseTab.getServer());
		properties.put("DatabasePath", databaseTab.getPath());
		
		//Workspace node
		properties.put("WorkspaceUpload", workspaceTab.getUpload());
		properties.put("WorkspaceBucketName", workspaceTab.getBucketName());
		properties.put("WorkspaceWorkflowDir", workspaceTab.getWorkflowDir());
		properties.put("WorkspaceCompressedWorkspace", workspaceTab.getCompressedWorkspace());
		properties.put("CompressedDir",workspaceTab.getCompressedDir());
		
		//Credentials node
		properties.put("AccessKey",credentialsTab.getAccessKey());
		properties.put("SecretAccessKey",credentialsTab.getSecretAccessKey());
		
		//Binary node
		properties.put("Directory",binaryTab.getDirectory());
		properties.put("ConceptualVersion",binaryTab.getConceptualVersion());
		properties.put("ExecutionVersion",binaryTab.getExecutionVersion());
		properties.put("StarterVersion",binaryTab.getStarterVersion());
		properties.put("QueryVersion",binaryTab.getQueryVersion());
		
		//Executionworkflow node
		properties.put("WorkflowExpdir",workflowTab.getExpDir());
		properties.put("WorkflowRelationName",workflowTab.getRelationName());
		properties.put("WorkflowRelationFilename",workflowTab.getRelationFileName());
		
		//query Node
		properties.put("QuerySQL",queryTab.getSql());
		
//		select ea.taskid, ea.actid, ea.machineid, ea.status from eactivation as ea;
		
		//loopParaPreencherActivity
		Map<String,String> activities = activityTab.getMapOnlyActivitiesDone();
		for(String key: activities.keySet())
		{
			properties.put(key, activities.get(key));
		}
		
		return properties;
	}
	
	private class ButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JButton finishButton = (JButton)e.getSource();
			MainWindow mainFrame = (MainWindow)SwingUtilities.getWindowAncestor(finishButton);
			
			ClassesController cc = mainFrame.getClassesController();
			
			boolean hasEmptyField =  mainFrame.hasEmptyField();
			
			if(hasEmptyField)
			{
				JOptionPane.showMessageDialog(null, "Has Empty Field", "Error", JOptionPane.ERROR_MESSAGE);
			}else
			{
				
				cc.finishXML(mainFrame.fillOutTheMap());
				mainFrame.dispose();
			}
						
		}

	}
}
