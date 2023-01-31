package com.mycompany.a3.objects;

import com.mycompany.a3.interfaces.ISelectable;
import com.codename1.charts.models.Point;

//create a Fixed class that extends GameObject and implements ISelectable 
public abstract class Fixed extends GameObject implements ISelectable
{
	//attributes of the Fixed class
	protected boolean selected;
	//constructor of the class 
	public Fixed(int size, Point grid ,int color)
	{
		super(size,grid,color);
		selected = false;
	}
	
	public void setLocation(Point newLocation) 
	{
	}
	public void setColor(int color) 
	{
		
	}
	
	@Override 
	public String toString()
	{
		return super.toString();
	}
}