package com.mycompany.a3.objects;
import com.codename1.charts.models.Point;
import com.codename1.ui.geom.Point2D;

/*
 * create an abstract Movable class that extends GameObject 
 */
public abstract class Movable extends GameObject
{
	//attributes of the class
	private int heading;
	private int speed;
	
	//constructor of the class 
	public Movable(int size, Point point,int color) 
	{
		super(size,point,color);
	}
	//setter method
	public void setHeading(int heading) 
	{
		this.heading = heading;
	}
	public void setSpeed(int speed)
	{
		this.speed = speed;
	}
	//getter method 
	public int getHeading() 
	{
		return heading;
	}
	
	public int getSpeed() 
	{
		return speed;
	}
	
	public void move(int time) 
	{
		float x = (float) (speed*(time/100)*Math.cos(Math.toRadians(90-getHeading()))) + getLocation().getX();
		float y = (float) (speed*(time/100)*Math.sin(Math.toRadians(90-getHeading()))) + getLocation().getY();
		// prevent Spider to go out of Game World
		if(this instanceof Spider) 
		{
			if(x <= 550 && x >=0 && y <=300 && y >=0)
			{
				setLocation(new Point(x,y));
			}
			else {
				setHeading(getHeading() - 90);
				move(time);
			}
		}
		else
			setLocation(new Point(x,y));
		
	}
	
	public String toString() 
	{
		return super.toString()+" heading="+getHeading()+
				" speed="+getSpeed()+" size="+getSize();
	}
}