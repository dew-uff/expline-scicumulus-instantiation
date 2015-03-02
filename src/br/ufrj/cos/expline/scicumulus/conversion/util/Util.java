package br.ufrj.cos.expline.scicumulus.conversion.util;

import java.util.HashMap;
import java.util.Map;

public class Util 
{
	public static String getActivityTag(String value)
	{
		String temp = null;
		temp = value;
		
		int underlineIndex = temp.indexOf("_");
		if(underlineIndex != -1)
		{
			temp = temp.substring(underlineIndex+1);
		}
		
		return temp;
	}
	
	public static Map<String,String> getOnlyActivities(Map<String,String> properties)
	{
		Map<String,String> onlyActivities = new HashMap<>();
		
		for(String p:properties.keySet())
		{
			int temp = p.indexOf("_");
			if(temp != -1)
			{
				onlyActivities.put(p, properties.get(p));
			}
		}
		
		return onlyActivities;
	}
	
	public static Map<String,String> getFakeActivities()
	{
		Map<String,String> temp = new HashMap<>();
		
		for(int i =0;i<20;i++)
		{
			temp.put("activity_"+i, "-");
		}
					
		return temp;
	}
	
	public static String getValue(String aux)
	{
		String iMod = aux.substring(0,aux.indexOf("_"));
		
		String temp = aux.substring(aux.indexOf("_")+1);
		
		String value = temp.substring(0,temp.indexOf("_"));
		
		String id = temp.substring(temp.indexOf("_")+1);
		
		return value;
	}
	
	public static String getId(String aux)
	{
		String iMod = aux.substring(0,aux.indexOf("_"));
		
		String temp = aux.substring(aux.indexOf("_")+1);
		
		String value = temp.substring(0,temp.indexOf("_"));
		
		String id = temp.substring(temp.indexOf("_")+1);
		
		return id;
	}
}
