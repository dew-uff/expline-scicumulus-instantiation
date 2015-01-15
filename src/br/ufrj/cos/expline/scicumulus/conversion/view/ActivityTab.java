package br.ufrj.cos.expline.scicumulus.conversion.view;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ActivityTab extends JPanel 
{
	private Map<String,String> onlyActivation;
	private ArrayList<ActivityMember> activities;
	private JPanel panelScrolled;
	private JScrollPane jsp;
	
	public ActivityTab(Map<String,String> onlyActivation)
	{
		super();
		
		this.onlyActivation = onlyActivation;
		this.setLayout(null);
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
		
		for(String p:onlyActivation.keySet())
		{
			ActivityMember tempAM = new ActivityMember(p);
			activities.add(tempAM);
		}
		
	}
	
	private void initActivityMembersLayout() 
	{
		int x = 10;
		int y = 10;
		int height = 0;
		for(ActivityMember am:activities)
		{
			am.setLocation(x, y);
			
			panelScrolled.add(am);
			
			x = am.getX();
			y = am.getY() + am.getHeight() + 10;
			height+= (10+am.getHeight());
		}
		height += 10;
		panelScrolled.setPreferredSize(new Dimension(570,height));
		
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
}
