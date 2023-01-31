package com.mycompany.a1;

import com.codename1.charts.util.ColorUtil;
import java.util.Random;

//create a class Spider that has extension to Movable class
public class Spider extends Movable 
{
	Random random = new Random();
	//constructor of the class "Spider"
	public Spider() 
	{
		super(ColorUtil.BLUE);
		//set the minimum and maximum size to 10 and 50 respectively 
		final int MIN_SIZE = 10, MAX_SIZE = 50;
		super.setSize(new Random().nextInt(MAX_SIZE - MIN_SIZE) + MIN_SIZE); 
		//set the speed of the spider range from 5-10
		super.setSpeed(random.nextInt(10));
		//set the heading of spider range from 0 -359
		super.setHeading(random.nextInt(360));
		
	}
	
	//setter method to set the size 
	public void setSize(int size) 
	{}
	//setter method to set the color of the spider 
	public void setColor(int color)
	{}
	//
	public void move() 
	{
		this.setHeading(super.getHeading()+random.nextInt(5));
	}
	public void boundarySpider() 
	{
		//condition for the spider's boundary 
		if ((this.getX()+ this.getSize()) > 0 || (this.getSize()+ this.getX())> 1000)
		{
			this.setHeading(random.nextInt(180));
		}
		if ((this.getSize() + this.getY()) < 0 || (this.getSize() + this.getY()) > 1000 )
		{
			this.setHeading(random.nextInt(180));
		}			
	}
	
	//Overriding string representation for the Spider 
	public String toString()
	{
		String string;
		double x = Math.round(this.getX()* 10.0)/ 10.0 ;
		double y = Math.round(this.getY()*10.0)/10.0;
		
		//below are the string representing the Spider class
		string ="Spider : loc= " + x +","+ y+
    			"color= " + this.getColortoString() + 
    			"  size = " + this.getSize()+
    			"  heading ="+ this.getHeading()+
    			"  speed = " + this.getSpeed();
		
		return string;
		
	}
}