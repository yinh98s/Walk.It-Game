package com.mycompany.a1;

import java.lang.Math;

//create a class called "Movable" that extends " GameObject" class
public  class Movable extends GameObject
{
	//attribute of the "Movable" class
	private int heading, speed;
	//constructor of the "Movable" class
	public Movable(int color) 
	{
		super(color);
	}
	public Movable( int color, int size) 
	{
		super(color, size);
	}
	
	//below are setter methods 
	public void setSpeed(int speed) 
	{
		this.speed = speed;
	}
	public void setHeading(int heading) 
	{
		this.heading = heading;
	}
	
	//below are the getter methods
	public int getSpeed() 
	{
		return speed;
	}
	public int getHeading() 
	{
		return heading;
	}
	
	//create a method called "move()" that represent each movement of the spider
	public void move() 
	{
		//variable 
		float radian, newX, newY;
		radian = (90 - this.getHeading()) * (float)Math.PI / 180 ;
		newX = this.getX() + (float)Math.cos(radian) * this.getSpeed();
		newY = this.getY() + (float)Math.sin(radian) * this.getSpeed();
		this.setLocation(newX, newY);
	}
	//create a method that will check the boundary between ant and spider 
	public void checkBoundary() 
	{	
	}
}