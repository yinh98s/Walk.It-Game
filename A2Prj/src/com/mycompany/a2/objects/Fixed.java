package com.mycompany.a2.objects;

import com.codename1.charts.models.Point;

//create a "Fixed" class that extends " GameObject" class
public abstract class Fixed extends GameObject
{

	public Fixed(int size, Point location,int color)
	{
		super(size,location,color);
	}
	
	public void setLocation(Point newLocation) 
	{
	}
	public void setColor(int color) 
	{
		
	}
	
	
	public String toString() 
	{
		return super.toString();
	}
}