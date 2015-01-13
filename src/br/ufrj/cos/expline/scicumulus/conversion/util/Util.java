package br.ufrj.cos.expline.scicumulus.conversion.util;

public class Util 
{
	public static String getActivityId(String value)
	{
		String temp = null;
		temp = value;
		
		int underlineIndex = temp.indexOf("_");
		
		temp = temp.substring(underlineIndex+1);
		
		return temp;
	}
}
