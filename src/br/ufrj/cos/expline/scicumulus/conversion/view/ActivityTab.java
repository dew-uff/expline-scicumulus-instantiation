package br.ufrj.cos.expline.scicumulus.conversion.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import br.ufrj.cos.expline.scicumulus.conversion.Conversion_reader;
import br.ufrj.cos.expline.scicumulus.conversion.util.Util;

public class ActivityTab extends JPanel 
{
	private Map<String,String> onlyActivation;
	private ArrayList<ActivityMember> activities;
	private JPanel panelScrolled;
	private JScrollPane jsp;
	private HashMap<String,HashMap<String,String>> activityMap;
	
	public ActivityTab(Map<String,String> onlyActivation)
	{
		super();
		
		this.onlyActivation = onlyActivation;
		
		this.activityMap = Conversion_reader.getActivityMap();
		System.out.println("------ "+activityMap.size());
		for(String p : activityMap.keySet())
		{
			System.out.println(p);
			HashMap<String,String> temp = activityMap.get(p);
			System.out.println("sdas"+temp.size());
			for(String pw : temp.keySet())
			{
				System.out.println(pw);
			}
		}
		
		initComponents();
		initActivityMembersLayout();
		
		initLayout();

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
			ActivityMember tempAM = new ActivityMember( p, this.activityMap.get(Util.getActivityTag(p)) );
			activities.add(tempAM);
		}
		
		
		this.setLayout(null);
				
	}
	
	private void initLayout()
	{
//		constraints.fill = GridBagConstraints.BOTH;
//		addComponents(panelScrolled, 0, 0, 1, 1);
	}
	
	private void initActivityMembersLayout() 
	{
//		int x = 10;
//		int y = 10;
//		int height = 0;
//		for(ActivityMember am:activities)
//		{
//			am.setLocation(x, y);
//			
//			panelScrolled.add(am);
//			
//			x = am.getX();
//			y = am.getY() + am.getHeight() + 10;
//			height+= (10+am.getHeight());
//		}
//		height += 10;
//		panelScrolled.setPreferredSize(new Dimension(570,height));
//		

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
				System.out.println("Activity");
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
