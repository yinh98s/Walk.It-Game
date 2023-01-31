package com.mycompany.a2.objects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.models.Point;

//create an abstract class of the "GameObject"
public abstract class GameObject 
{
	//attribute of the class "GameObject"
	private int color;
	private int size;
	private Point location;
	
	//constructor
	public GameObject(int size, Point location, int color)
	{
		//instantiation 
		this.color = color;
		this.size = size;
		this.location = location;
	}
	
	//setter methods
	public void setLocation(Point point) 
	{
		this.location = point;
	}

	public void setColor(int c) 
	{
		this.color = c;
	}
	
	//getter methods
	public Point getLocation() 
	{
		return location;
	}
	
	public int getColor()
	{
		return this.color;
	}
	
	public int getSize() 
	{
		return size;
	}
	
	@Override
	public String toString() 
	{
		String str;
		str = "location : " + Math.round(getLocation().getX()*10.0)/10.0 + "," +
				Math.round(getLocation().getY()*10.0)/10.0+
				" color=["+ColorUtil.red(getColor())+","+
				ColorUtil.green(getColor())+","+
				ColorUtil.blue(getColor())+","+"]";
		return str;
	}
}

