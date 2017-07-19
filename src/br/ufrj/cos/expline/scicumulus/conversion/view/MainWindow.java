package br.ufrj.cos.expline.scicumulus.conversion.view;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import br.ufrj.cos.expline.scicumulus.conversion.engine.ScicumulusWriter;
import br.ufrj.cos.expline.scicumulus.conversion.model.sciObjects.ScicumulusDocument;

public class MainWindow extends JDialog 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Map<String,String> properties;
	private ScicumulusDocument sciDoc;
	
	private ButtonListener bl;
	
	private DataBaseTab databaseTab;
	private WorkspaceTab workspaceTab;
	private EnvironmentTab enviromentTab;
	private CredentialsTab credentialsTab;
	private BinaryTab binaryTab;
	private WorkflowTab workflowTab;	
	private ActivityTab activityTab;
	private QueryTab queryTab;
	private File outputFile;
	
	public Frame frame;
	
	public MainWindow(Frame owner, ScicumulusDocument sciDoc, File outputFile)
	{
		super(owner);
		this.outputFile = outputFile;
		this.properties = new HashMap<>();
		this.sciDoc = sciDoc;
		
		this.frame = owner;
		
		this.setSize(600, 300);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setTitle("Scicumulus Instantiation");
				
		JTabbedPane jtp = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT);
		jtp.setBounds(0,0,this.getWidth()-5, this.getHeight()-70);
		//jtp.setSize(width, height);
		this.add(jtp);
		
		bl = new ButtonListener();
		
		JButton finishButton = new JButton("Finish");
		finishButton.addActionListener(bl);
		int diff = this.getHeight() - jtp.getHeight();
		finishButton.setBounds(this.getWidth() - 10 - 100,jtp.getHeight()+((diff /6)),100,25);
		this.add(finishButton);
		
		this.databaseTab 		= new DataBaseTab(this.sciDoc.getDatabase());
		this.workspaceTab 		= new WorkspaceTab(this.sciDoc.getWorkspace());
		this.enviromentTab		= new EnvironmentTab(this.sciDoc.getEnviron());
		this.credentialsTab 	= new CredentialsTab(this.sciDoc.getCred());
		this.binaryTab			= new BinaryTab(this.sciDoc.getBinary());
		this.workflowTab		= new WorkflowTab(this.sciDoc.getExecWorkflow());
		this.activityTab		= new ActivityTab(this.sciDoc.getConceptualWorkflow());
		this.queryTab			= new QueryTab(this.sciDoc.getQuery());
		
		JPanel panel = new JPanel();
		
		String without = "InPut Ports Without using: ";
		
		JLabel label = new JLabel(without);
		panel.add(label);
		panel.setVisible(true);
		
		//jtp.addTab("Input Ports Info", panel);
		
		jtp.addTab("Database", this.databaseTab );
		
		jtp.addTab("Workspace", this.workspaceTab );
		
		jtp.addTab("Environment", this.enviromentTab);
		
		jtp.addTab("Credentials", this.credentialsTab );
		
		jtp.addTab("Binary", this.binaryTab );
		
		jtp.addTab("Workflow", this.workflowTab );
		
		jtp.addTab("Query", this.queryTab);
		
		jtp.addTab("Activities", this.activityTab );
						
		this.setResizable(false);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.setVisible(true);
				
	}
	
	public Map<String,String> getProperties()
	{
		return properties;
	}
	
	public WorkflowTab getWorkflowTab()
	{
		return this.workflowTab;
	}
	
	public boolean hasEmptyField()
	{
		return !(databaseTab.checkFilledOut() &&
			   workspaceTab.checkFilledOut() &&
			   credentialsTab.checkFilledOut() &&
			   binaryTab.checkFilledOut() &&
			   queryTab.checkFilledOut()
			   && workflowTab.checkFilledOut() 
			   && activityTab.checkFilledOut()
				);
	}
	
//	public Map<String,String> fillOutTheMap()
//	{
//		//Database node
//		properties.put("DatabaseName", databaseTab.getName());
//		properties.put("DatabaseUsername", databaseTab.getUserName());
//		properties.put("DatabasePassword",databaseTab.getPassword());
//		properties.put("DatabasePort", databaseTab.getPort());
//		properties.put("DatabaseServer", databaseTab.getServer());
//		properties.put("DatabasePath", databaseTab.getPath());
//		
//		//Workspace node
//		properties.put("WorkspaceUpload", workspaceTab.getUpload());
//		properties.put("WorkspaceBucketName", workspaceTab.getBucketName());
//		properties.put("WorkspaceWorkflowDir", workspaceTab.getWorkflowDir());
//		properties.put("WorkspaceCompressedWorkspace", workspaceTab.getCompressedWorkspace());
//		properties.put("CompressedDir",workspaceTab.getCompressedDir());
//		
//		//Environment node
//		properties.put("EnvironmentClusterName", enviromentTab.getClusterName());
//		properties.put("EnvironmentType", enviromentTab.getType());
//		properties.put("EnvironmentVerbose", enviromentTab.getVerbose());
//		
//		//Credentials node
//		properties.put("AccessKey",credentialsTab.getAccessKey());
//		properties.put("SecretAccessKey",credentialsTab.getSecretAccessKey());
//		
//		//Binary node
//		properties.put("Directory",binaryTab.getDirectory());
//		properties.put("ConceptualVersion",binaryTab.getConceptualVersion());
//		properties.put("ExecutionVersion",binaryTab.getExecutionVersion());
//		properties.put("StarterVersion",binaryTab.getStarterVersion());
//		properties.put("QueryVersion",binaryTab.getQueryVersion());
//		
//		//Executionworkflow node
////		System.out.println("workflow info");
//		properties.put("WorkflowExpdir",workflowTab.getExpDir());
////		properties.put("WorkflowRelationName",workflowTab.getRelationName());
////		properties.put("WorkflowRelationFilename",workflowTab.getRelationFileName());
//		
//		//query Node
//		properties.put("QuerySQL",queryTab.getSql());
//		
////		select ea.taskid, ea.actid, ea.machineid, ea.status from eactivation as ea;
//		
//		//loopParaPreencherActivity
//		Map<String,String> activities = activityTab.getMapOnlyActivitiesDone();
//		for(String key: activities.keySet())
//		{
//			properties.put(key, activities.get(key));
//		}
//		
//		return properties;
//	}
	
	public void fillOutSciCumulusDoc() {
		this.databaseTab.fillOut ();
		this.workspaceTab.fillOut ();
		this.enviromentTab.fillOut ();
		this.credentialsTab.fillOut ();
		this.binaryTab.fillOut ();
		this.queryTab.fillOut ();
		// Another way to fillOut
		this.workflowTab.fillOut ();
		this.activityTab.fillOut ();
	}
	
	private class ButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton finishButton = (JButton)e.getSource();
			MainWindow mainFrame = (MainWindow)SwingUtilities.getWindowAncestor(finishButton);
			
			boolean hasEmptyField =  mainFrame.hasEmptyField();
			
			if(hasEmptyField)
			{
				JOptionPane.showMessageDialog(null, "Has Empty Field", "Error", JOptionPane.ERROR_MESSAGE);
			}else
			{
				mainFrame.fillOutSciCumulusDoc();
				mainFrame.WriteXML ();
				mainFrame.dispose();
			}
						
		}

	}

	public void WriteXML() {
		ScicumulusWriter writer = new ScicumulusWriter (this.outputFile);
		writer.run(this.sciDoc);
	}
}
