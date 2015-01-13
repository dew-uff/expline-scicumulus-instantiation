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
}
