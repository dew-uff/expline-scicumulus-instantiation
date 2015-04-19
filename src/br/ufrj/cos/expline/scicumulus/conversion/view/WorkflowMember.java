package br.ufrj.cos.expline.scicumulus.conversion.view;

import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import br.ufrj.cos.expline.scicumulus.conversion.util.Util;

public class WorkflowMember extends JPanel
{
	
	private TitledBorder title;
	private String keyInTheMap;
		
	private JLabel lbRelationName;
	private JTextField tfRelationName;
	private JLabel lbRelationFileName;
	private JTextField tfRelationFileName;
	
	public WorkflowMember(String key)
	{
		super();
		
		initComponent();
		
		this.setBounds(0, 0, 560, 40);
		
		this.keyInTheMap = key;
		
		
		
		title = BorderFactory.createTitledBorder(Util.getActivityTag(keyInTheMap));
		this.setBorder(title);
		
		initLayout();
		
	}

	private void initLayout() {
		// TODO Auto-generated method stub
		GroupLayout layout = new GroupLayout(this);
		
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
//								.addComponent(lbExpDir)
								.addComponent(lbRelationName)
								.addComponent(lbRelationFileName)
						)
						.addContainerGap(10,10)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//								.addComponent(tfExpDir)
								.addComponent(tfRelationName)
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
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lbRelationName)
								.addComponent(tfRelationName)
						)
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
		
		lbRelationName = new JLabel("Relation Name: ");
		
		tfRelationName = new JTextField();
		
		lbRelationFileName = new JLabel("Relation File Name: ");
		
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
		if(tfRelationName.getText().equals(""))
		{
//			System.out.println("Workflow");
			return true;
		}
		else if(tfRelationFileName.getText().equals(""))
		{
//			System.out.println("Workflow");
			return true;
		}
							
		return false;
	}
	
	public HashMap<String,String> getMapContainsRels()
	{
		HashMap<String,String> temp = new HashMap<>();
		temp.put(tfRelationName.getText(), tfRelationFileName.getText());
		
		return temp;
	}
	
	public String getTitleofWorkflow()
	{
		return this.keyInTheMap;
	}

}
