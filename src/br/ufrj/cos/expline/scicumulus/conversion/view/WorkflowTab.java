package br.ufrj.cos.expline.scicumulus.conversion.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import br.ufrj.cos.expline.scicumulus.conversion.model.sciObjects.ExecutionWorkflow;
import br.ufrj.cos.expline.scicumulus.conversion.model.sciObjects.ExecutionWorkflowRelation;

public class WorkflowTab extends JPanel 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String,String> onlyActivation;
	private ArrayList<WorkflowMember> workflows;
	private JPanel panelScrolled;
	private JScrollPane jsp;
	
	private JLabel lbExpDir;
	private JTextField tfExpDir;	
	
	private ExecutionWorkflow execWorkflow;
	
	public WorkflowTab(ExecutionWorkflow execWorkflow)
	{
		super();
		
		this.execWorkflow = execWorkflow;
		
		this.onlyActivation = new HashMap<>();
		initComponents();
		
		initLayout();
	}

	private void initLayout() {
		GroupLayout layout = new GroupLayout(panelScrolled);
		
		ParallelGroup pg = layout.createParallelGroup(GroupLayout.Alignment.CENTER);
		
		SequentialGroup sg = layout.createSequentialGroup();
		
		ParallelGroup ppldk = layout.createParallelGroup(GroupLayout.Alignment.CENTER)
									.addGroup(layout.createSequentialGroup()
											.addGap(20)
											.addComponent(lbExpDir)
											.addGap(10)
											.addComponent(tfExpDir)
											.addGap(5)
									);
		pg.addGap(10);
		pg.addGroup(ppldk);
		pg.addGap(10);
		
		sg.addGap(10);
		sg.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(lbExpDir,20,20,20)
								.addComponent(tfExpDir,20,20,20));
		sg.addGap(10);
		
		
		for(WorkflowMember am:workflows)
		{
			pg.addGap(10);
			pg.addComponent(am);
			pg.addGap(10);
			
			ParallelGroup temp = layout.createParallelGroup(GroupLayout.Alignment.BASELINE);
			temp.addComponent(am,50,50,50);
			sg.addGroup(temp);
			sg.addContainerGap(10, 10);
		}
		
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
						.addGroup(pg)
				)
		);
		
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(sg)
		);
		
		panelScrolled.setLayout(layout);
		
		jsp.revalidate();
		
	}

	private void initComponents() {
		workflows = new ArrayList<>();
		
		panelScrolled = new JPanel();
		panelScrolled.setBounds(0, 0, 570, 200);
		panelScrolled.setLayout(null);
		
		jsp = new JScrollPane(panelScrolled);
		panelScrolled.setAutoscrolls(true);
		jsp.setBounds(0, 0, 590, 200);
		
		this.add(jsp);
		
		lbExpDir = new JLabel("Exp Dir: ");
		
		tfExpDir = new JTextField();
		
		for(ExecutionWorkflowRelation rel : this.execWorkflow.getExecWorkRelList())
		{
			WorkflowMember tempAM = new WorkflowMember(rel);
			workflows.add(tempAM);
//			this.id++;
		}
		
		
		this.setLayout(null);
		
	}
	
	public boolean checkFilledOut()
	{
		if(tfExpDir.getText().equals(""))
		{
//			System.out.println("Entrou Aqui");
			return false;
		}
		for(WorkflowMember am:workflows)
		{
			if(am.fieldIsEmpty())
			{
//				System.out.println("Workflow");
				return false;
			}
		}
		return true;
	}
	
	public String getExpDir()
	{
//		System.out.println("HERREEE ---- "+tfExpDir);
		return tfExpDir.getText();
	}
	
	public String getRelationName()
	{
		return null;//tfRelationName.getText();
	}
	
	public String getRelationFileName()
	{
		return null;//tfRelationFileName.getText();
	}
	
	public HashMap<String,HashMap<String,String>> getMapOnlyWorkflowsDone()
	{
		HashMap<String,HashMap<String,String>> map = new HashMap<>();
		
		for(WorkflowMember am:workflows)
		{
			HashMap<String,String> tempMap = am.getMapContainsRels();
			map.put(am.getKeyInTheMap(), tempMap);
		}
		
		return map;
	}

	public void fillOut() {
		for (WorkflowMember wMember : this.workflows) {
			wMember.fillOut();
		}
	}
}
