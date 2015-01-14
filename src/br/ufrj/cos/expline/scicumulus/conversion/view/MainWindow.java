package br.ufrj.cos.expline.scicumulus.conversion.view;

import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import br.ufrj.cos.expline.scicumulus.conversion.controller.ClassesController;
import br.ufrj.cos.expline.scicumulus.conversion.util.Util;

public class MainWindow extends JFrame 
{
	private Map<String,String> properties;
	private ClassesController classesController;
	
	public MainWindow(Map<String,String> properties)
	{
		super();
		
		this.properties = properties;
		
		this.setSize(500, 300);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setTitle("Scicumulus Instantiation");
				
		JTabbedPane jtp = new JTabbedPane();
		jtp.setBounds(0,0,this.getWidth(), this.getHeight()-70);
		//jtp.setSize(width, height);
		this.add(jtp);
		
		JButton finishButton = new JButton("Finish");
		int diff = this.getHeight() - jtp.getHeight();
		finishButton.setBounds(this.getWidth() - 10 - 100,jtp.getHeight()+((diff /6)),100,25);
		this.add(finishButton);
		
		JPanel databaseTab 		= new DataBaseTab();
		JPanel workspaceTab 	= new WorkspaceTab();
		JPanel credentialsTab 	= new CredentialsTab();
		JPanel binaryTab		= new BinaryTab();
		JPanel workflowTab		= new WorkflowTab();
		JPanel queryTab			= new QueryTab();
		JPanel activityTab		= new ActivityTab(Util.getOnlyActivities(this.properties));
		
		jtp.addTab("Database", databaseTab );
		
		jtp.addTab("Workspace", workspaceTab );
		
		jtp.addTab("Credentials", credentialsTab );
		
		jtp.addTab("Binary", binaryTab );
		
		jtp.addTab("Workflow", workflowTab );
		
		jtp.addTab("Activities", activityTab );
		
		jtp.addTab("Query", queryTab);
				
		this.setResizable(false);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.setVisible(true);
		
		
		
		
	}
}
