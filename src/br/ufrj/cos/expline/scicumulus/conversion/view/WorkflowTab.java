package br.ufrj.cos.expline.scicumulus.conversion.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class WorkflowTab extends JPanel 
{
	private Map<String,String> onlyActivation;
	private ArrayList<WorkflowMember> workflows;
	private JPanel panelScrolled;
	private JScrollPane jsp;
	
		
	public WorkflowTab(Map<String,String> onlyActivation)
	{
		super();
		
		this.onlyActivation = onlyActivation;
		initComponents();
		
//		this.setVisible(true);
		
		initLayout();
	}

	private void initLayout() {
		// TODO Auto-generated method stub
//		GroupLayout layout = new GroupLayout(this);
//		
//		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
//				.addGroup(layout.createSequentialGroup()
//						.addContainerGap(100,100)
//						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
//								.addComponent(lbExpDir)
//								.addComponent(lbRelationName)
//								.addComponent(lbRelationFileName)
//						)
//						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//								.addComponent(tfExpDir)
//								.addComponent(tfRelationName)
//								.addComponent(tfRelationFileName)
//						)
//						.addContainerGap(100,100)
//				)
//		);
//		
//		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
//				.addGroup(layout.createSequentialGroup()
//						.addContainerGap()
//						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//								.addComponent(lbExpDir)
//								.addComponent(tfExpDir)
//						)
//						.addContainerGap(15,15)
//						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//								.addComponent(lbRelationName)
//								.addComponent(tfRelationName)
//						)
//						.addContainerGap(15,15)
//						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//								.addComponent(lbRelationFileName)
//								.addComponent(tfRelationFileName)
//						)
//						.addContainerGap()
//				)
//		);
		GroupLayout layout = new GroupLayout(panelScrolled);
		
		ParallelGroup pg = layout.createParallelGroup(GroupLayout.Alignment.CENTER);
		
		SequentialGroup sg = layout.createSequentialGroup();
		
		for(WorkflowMember am:workflows)
		{
			pg.addGap(10);
			pg.addComponent(am);
			pg.addGap(10);
			
			ParallelGroup temp = layout.createParallelGroup(GroupLayout.Alignment.BASELINE);
			temp.addComponent(am,80,80,80);
			sg.addGroup(temp);
			sg.addContainerGap(20, 20);
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
		// TODO Auto-generated method stub
		workflows = new ArrayList<>();
		
		panelScrolled = new JPanel();
		panelScrolled.setBounds(0, 0, 570, 200);
		panelScrolled.setLayout(null);
		
		jsp = new JScrollPane(panelScrolled);
		panelScrolled.setAutoscrolls(true);
		jsp.setBounds(0, 0, 590, 200);
		
		this.add(jsp);
		
		for(String p:onlyActivation.keySet())
		{
			WorkflowMember tempAM = new WorkflowMember(p);
			workflows.add(tempAM);
		}
		
		
		this.setLayout(null);
		
	}
	
	public boolean checkFilledOut()
	{
			
//		for(WorkflowMember am:workflows)
//		{
//			if(am.fieldIsEmpty())
//			{
//				System.out.println("Workflow");
//				return false;
//			}
//		}
		return true;
	}
	
	public String getExpDir()
	{
		return null;//tfExpDir.getText();
	}
	
	public String getRelationName()
	{
		return null;//tfRelationName.getText();
	}
	
	public String getRelationFileName()
	{
		return null;//tfRelationFileName.getText();
	}
	
	public Map<String,String> getMapOnlyWorkflowsDone()
	{
		//TODO
		Map<String,String> map = new HashMap<>();
		
		for(WorkflowMember am:workflows)
		{
//			map.put(am.getBorderTitle(), am.getActivation());
		}
		
		return map;
	}
}
