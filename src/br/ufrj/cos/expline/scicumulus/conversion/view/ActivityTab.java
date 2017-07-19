package br.ufrj.cos.expline.scicumulus.conversion.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import br.ufrj.cos.expline.scicumulus.conversion.model.sciObjects.Activity;
import br.ufrj.cos.expline.scicumulus.conversion.model.sciObjects.ConceptualWorkflow;
import br.ufrj.cos.expline.scicumulus.conversion.util.Util;

public class ActivityTab extends JPanel 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String,String> onlyActivation;
	private ArrayList<ActivityMember> activities;
	private JPanel panelScrolled;
	private JScrollPane jsp;
	private HashMap<String,HashMap<String,List<String>>> activityMap;
	private ConceptualWorkflow concepWorkflow;
	
	public ActivityTab(ConceptualWorkflow concepWorkflow)
	{
		super();
		
		this.concepWorkflow = concepWorkflow;
		
		this.onlyActivation = new HashMap<>();
		
		initComponents();
		initActivityMembersLayout();
	

	}
	
	private void initComponents()
	{
		activities = new ArrayList<>();
		
		panelScrolled = new JPanel();
		panelScrolled.setBounds(0, 0, 570, 200);
		panelScrolled.setLayout(null);
		
		jsp = new JScrollPane(panelScrolled);
		panelScrolled.setAutoscrolls(true);
		jsp.setBounds(0, 0, 590, 200);
		
		this.add(jsp);
		
		for(Activity act : this.concepWorkflow.getActivities())
		{
//			ActivityMember tempAM = new ActivityMember( p, this.activityMap.get(Util.getActivityTag(p)) );
			ActivityMember tempAM = new ActivityMember( act );
			activities.add(tempAM);
		}
		
		
		this.setLayout(null);
				
	}
	
	private void initActivityMembersLayout() {
		GroupLayout layout = new GroupLayout(panelScrolled);
		
		ParallelGroup pg = layout.createParallelGroup(GroupLayout.Alignment.CENTER);
		
		SequentialGroup sg = layout.createSequentialGroup();
		
		for(ActivityMember am:activities)
		{
			pg.addGap(10);
			pg.addComponent(am);
			pg.addGap(10);
			
			ParallelGroup temp = layout.createParallelGroup(GroupLayout.Alignment.BASELINE);
			temp.addComponent(am,50,50,50);
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
	
	public boolean checkFilledOut()
	{
		for(ActivityMember am:activities)
		{
			if(am.fieldIsEmpty())
			{
				return false;
			}
		}
		return true;
	}
	
	public Map<String,String> getMapOnlyActivitiesDone()
	{
		Map<String,String> map = new HashMap<>();
		
		for(ActivityMember am:activities)
		{
			map.put(am.getBorderTitle(), am.getActivation());
		}
		
		return map;
	}

	public void fillOut() {
		for(ActivityMember am:activities)
		{
			am.fillOut();
		}
	}
}
