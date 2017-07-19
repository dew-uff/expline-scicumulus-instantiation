package br.ufrj.cos.expline.scicumulus.conversion.view;

import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import br.ufrj.cos.expline.scicumulus.conversion.model.sciObjects.ExecutionWorkflowRelation;
import br.ufrj.cos.expline.scicumulus.conversion.util.Util;

public class WorkflowMember extends JPanel
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TitledBorder title;
	private String keyInTheMap;
	
	private int id;
		
//	private JLabel lbRelationName;
//	private JTextField tfRelationName;
	private JLabel lbRelationFileName;
	private JTextField tfRelationFileName;
	private ExecutionWorkflowRelation rel;
	
	public WorkflowMember(String key,int id)
	{
		super();
		this.id = id;
		initComponent();
		
		this.setBounds(0, 0, 560, 20);
		
		this.keyInTheMap = key;
		
		String actName = Util.getActivityTag(keyInTheMap);
		
		String strTitle = getProperlyTitle(actName);
		
		title = BorderFactory.createTitledBorder(strTitle);//"Relation_"+Util.getActivityTag(keyInTheMap));
		this.setBorder(title);
		
		initLayout();
		
	}
	
	public WorkflowMember(ExecutionWorkflowRelation execRel)
	{
		super();
		this.rel = execRel;
		initComponent();
		
		this.setBounds(0, 0, 560, 20);
		
		this.keyInTheMap = execRel.getName();
		
		String actName = Util.getActivityTag(keyInTheMap);
		
		String strTitle = getProperlyTitle(actName);
		
		title = BorderFactory.createTitledBorder(strTitle);//"Relation_"+Util.getActivityTag(keyInTheMap));
		this.setBorder(title);
		
		initLayout();
		
	}
	
	private String getProperlyTitle(String keyInTheMap2) {
//		String id = Util.getId(keyInTheMap2);
		String actName = Util.getLastActName(keyInTheMap2);
		
		String properlyTitle = "ACTIVITY "+actName+" ->  INPUT PORT "+ this.id+"  -> RELATION";
		return properlyTitle;
	}

	private void initLayout() {
		GroupLayout layout = new GroupLayout(this);
		
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
//								.addComponent(lbExpDir)
//								.addComponent(lbRelationName)
								.addComponent(lbRelationFileName)
						)
						.addContainerGap(10,10)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//								.addComponent(tfExpDir)
//								.addComponent(tfRelationName)
								.addComponent(tfRelationFileName)
						)
				)
		);
		
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addGroup(layout.createSequentialGroup()
//						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//								.addComponent(lbExpDir)
//								.addComponent(tfExpDir)
//						)
//						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//								.addComponent(lbRelationName)
//								.addComponent(tfRelationName)
//						)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lbRelationFileName)
								.addComponent(tfRelationFileName)
						)
				)
		);
		
		this.setLayout(layout);
	}

	private void initComponent() {
//		lbExpDir = new JLabel("Exp Dir: ");
//		
//		tfExpDir = new JTextField();
		
//		lbRelationName = new JLabel("Relation Name: ");

//		tfRelationName = new JTextField();
		
		lbRelationFileName = new JLabel("File Name: ");
		
		tfRelationFileName = new JTextField();
		
	}
	
	public String getKeyInTheMap()
	{
		return keyInTheMap;
	}
	
	public boolean fieldIsEmpty()
	{
//		if(tfExpDir.getText().equals(""))
//		{
////			System.out.println("Workflow");
//			return false;	
//		}else
//		if(tfRelationName.getText().equals(""))
//		{
//			System.out.println("Workflow");
//			return true;
//		}
//		else 
		if(tfRelationFileName.getText().equals(""))
		{
//			System.out.println("Workflow");
			return true;
		}
							
		return false;
	}
	
	public HashMap<String,String> getMapContainsRels()
	{
		HashMap<String,String> temp = new HashMap<>();
		temp.put(Util.getActivityTag(keyInTheMap), tfRelationFileName.getText());
		
		return temp;
	}
	
	public String getTitleofWorkflow()
	{
		return this.keyInTheMap;
	}
	
	public String getRelationFileName () {
		return this.tfRelationFileName.getText();
	}
	
	public void fillOut() {
		this.rel.setFileName(getRelationFileName());
	}

}
