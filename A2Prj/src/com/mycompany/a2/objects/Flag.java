package com.mycompany.a2.objects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.models.Point;

//create a "Flag" class that extends "Fixed" class
public class Flag extends Fixed 
{
	//attributes of the "flag" class
	
	private boolean reach;//define which flag the ant will stop by 
	private int sequenceNumber;//flag as in sequence number 
	
	//constructor of the "Flag" class
	public Flag(Point location, int sequence)
	{
		super(10,location,ColorUtil.rgb(0,0, 255));
		this.sequenceNumber = sequence;
		//set the reach to false 
		reach = false;
	}
	
	//getter methods
	public int getSequenceNumber() 
	{
		return sequenceNumber;
	}
	//setter methods
	public void setSequenceNumber(int sequenceNumber)
	{
		this.sequenceNumber = sequenceNumber;
	}
	public void setReached(boolean reached)
	{
		this.reach = reached;
	}
	public boolean isReached() 
	{
		return reach;
	}
	
	@Override
	public String toString() 
	{
	
		String str;
		str ="Flag : " + super.toString()+" size="+ getSize()+" sequence number : "+ getSequenceNumber();
		return str;
	}
	
	
}