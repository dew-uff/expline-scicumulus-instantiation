package br.ufrj.cos.expline.scicumulus.conversion.view;

import java.util.ArrayList;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class ActivityTab extends JPanel 
{
	private Map<String,String> onlyActivation;
	private ArrayList<ActivityMember> activities;
	private JPanel panelScrolled;
	
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
		
		for(String p:onlyActivation.keySet())
		{
			ActivityMember tempAM = new ActivityMember(p);
			activities.add(tempAM);
		}
		
		ArrayList<ActivityMember> temp = new ArrayList<>();
		
		for(int i = activities.size()-1; i>=0 ;i--)
		{
			temp.add(activities.get(i));
		}
		
		activities = temp;
		System.out.println("_________");
		for(ActivityMember am : activities)
		{
			System.out.println(am.getBorderTitle());
		}
	}
	
	private void initActivityMembersLayout() 
	{
		int x = 10;
		int y = 10;
		for(ActivityMember am:activities)
		{
			am.setLocation(x, y);
			
			this.add(am);
			
			x = am.getX();
			y = am.getY() + am.getHeight() + 10;
		}
	}
}
