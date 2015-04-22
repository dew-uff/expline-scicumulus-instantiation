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
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class WorkflowTab extends JPanel 
{
	private Map<String,String> onlyActivation;
	private ArrayList<WorkflowMember> workflows;
	private JPanel panelScrolled;
	private JScrollPane jsp;
	
	private JLabel lbExpDir;
	private JTextField tfExpDir;
	
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
		
		JSeparator separator = new JSeparator();
		
		ParallelGroup ppldk = layout.createParallelGroup(GroupLayout.Alignment.CENTER)
									.addGroup(layout.createSequentialGroup()
											.addGap(10)
											.addComponent(lbExpDir)
											.addComponent(tfExpDir)
											.addGap(5)
									);
		pg.addGap(10);
		pg.addGroup(ppldk);
//		pg.addComponent(lbExpDir).addComponent(tfExpDir); 
		pg.addGap(10);
		
		sg.addGap(10);
		sg.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(lbExpDir)
								.addComponent(tfExpDir));
		sg.addGap(10);
		
		
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
		
		lbExpDir = new JLabel("Exp Dir: ");
		
		tfExpDir = new JTextField();
		
		for(String p:onlyActivation.keySet())
		{
			WorkflowMember tempAM = new WorkflowMember(p);
			workflows.add(tempAM);
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
		//TODO
		HashMap<String,HashMap<String,String>> map = new HashMap<>();
		
		for(WorkflowMember am:workflows)
		{
			HashMap<String,String> tempMap = am.getMapContainsRels();
			map.put(am.getKeyInTheMap(), tempMap);
		}
		
		return map;
	}
}
