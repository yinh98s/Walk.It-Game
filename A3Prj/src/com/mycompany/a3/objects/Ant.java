package com.mycompany.a3.objects;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point2D;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.mycompany.a3.interfaces.IDrawable;
import com.mycompany.a3.interfaces.Isteerable;

/*
 * create an Ant class that extends Movable and implements both Isteerable & IDrawable 
 */
public class Ant extends Movable implements Isteerable ,IDrawable
{
	//attributes of the Ant class
	private static Ant antObj =new Ant(new Point(50,50));
	private final int MAX_SPEED = 100;
	private int maximumSpeed;
	private int foodLevel,foodConsumptionRate,healthLevel;
	private int redValue;
	private int lastFlagReached;
	
	//constructor of the Ant 
	private Ant(Point grid) 
	{
		super(20, grid, ColorUtil.rgb(255, 0, 0));
		setMaximumSpeed(MAX_SPEED);
		redValue = 255;
		setFoodLevel(100);
		setFoodConsumptionRate(2);
		setHealthLevel(10);
		setHeading(0);
		setLastFlagReached(1);
		setSpeed(10);
		
	}

	//create a turnRight() method 
	public void turnRight() 
	{
		setHeading(getHeading() + 5);
		if (getHeading() >= 360)
		{
			setHeading(getHeading() - 360);
		}
	}
	//create a turnLeft() method 
	public void turnLeft() 
	{
		setHeading(getHeading() - 5);
		if (getHeading() < 0) 
		{
			setHeading(360 - -getHeading());
		}
	}
	//create setSpeed() method 
	public void setSpeed(int s) 
	{
		if(getFoodLevel() == 0 || getHealthLevel() == 0 || s < 0 )
		{
			super.setSpeed(0);
		}
		else if (s <= getMaximumSpeed() && s >= 0)
		{
			super.setSpeed(s);
		}
	}
	/**
	 * 
	 * create a collison_handle that will handle all the collision between ant and all other object 
	 * @param o
	 */
	public void handleCollision(GameObject o) 
	{ 
		// if collision with Spider occurs
		if( o instanceof Spider) 
		{
			setHealthLevel(getHealthLevel()-1);
			if(getHealthLevel() < 0 )
			{
				setHealthLevel(0);
			}
			//calculate the maximum speed of the ant that can be 
			maximumSpeed = (int) (MAX_SPEED * (getHealthLevel()/ 10.0));
			//if the ant speed is greater than the MAX_SPEED = 100
			if (getSpeed() > maximumSpeed)
				//its speed is the max speed
				setSpeed(maximumSpeed);
			redValue -= 25;
			setColor(ColorUtil.rgb(redValue, 0, 0));
			
		}
		// if collision with FoodStation occurs
		else if(o instanceof FoodStation && ((FoodStation)o).getCapacity() != 0 ) 
		{
			setFoodLevel(getFoodLevel() + ((FoodStation)o).getCapacity());
			((FoodStation)o).setCapacity(0);
		}
		// if collision with Flag occurs
		else if(o instanceof Flag && ((Flag)o).getSequenceNumber() == getLastFlagReached()+1) 
		{
			setLastFlagReached(((Flag)o).getSequenceNumber());
			((Flag)o).setReached(true);
		}
	}
	
	//create a collisionFlag method  that is the collision between flag with other object 
	public void collisionFlag(int num)
	{
		if(num == getLastFlagReached()+1)
		{
			setLastFlagReached(num);
		}
	}
	//create a collisionSpider() method 
	public void collisionSpider() 
	{
		setHealthLevel(getHealthLevel()-1);
		if(getHealthLevel() < 0 )
			setHealthLevel(0);
		maximumSpeed = (int) (MAX_SPEED * (getHealthLevel()/ 10.0));
		if (getSpeed() > maximumSpeed)
			setSpeed(maximumSpeed);
		redValue -= 25;
		setColor(ColorUtil.rgb(redValue, 0, 0));
	}

	//below are setter method 
	
	public void setMaximumSpeed(int max)
	{
		this.maximumSpeed = max;
	}
	public void setFoodLevel(int foodLevel)
	{
		this.foodLevel = foodLevel;
		if(getFoodLevel() == 0)
		{
			setSpeed(0);
		}
	}
	public void setFoodConsumptionRate(int foodConsumptionRate) 
	{
		this.foodConsumptionRate = foodConsumptionRate;
	}
	
	public void setHealthLevel(int healthLevel) 
	{
		this.healthLevel = healthLevel;
		if(getHealthLevel() == 0)
			setSpeed(0);
	}
	public void setLastFlagReached(int lastFlagReached) 
	{
		this.lastFlagReached = lastFlagReached;
	}
	//end of the setter methods

	//below are the getter methods 
	public int getMaximumSpeed() 
	{
		return maximumSpeed;
	}
	public int getFoodLevel()
	{
		return foodLevel;
	}
	public int getFoodConsumptionRate() 
	{
		return foodConsumptionRate;
	}

	public int getHealthLevel() 
	{
		return healthLevel;
	}
	public int getLastFlagReached() 
	{
		return lastFlagReached;
	}
	public int getRedValue() 
	{
		return redValue;
	}
	public static Ant getAntObj() 
	{
		return antObj;
	}
	//end of the getter methods
	
	
	
	//create a draw() method for the ant 
	public void draw(Graphics gra, Point2D pCmpRelPrnt)
	{
		//draw the ant object of the game 
		gra.setColor(this.getColor());
		gra.fillArc((int)(this.getLocation().getX() + pCmpRelPrnt.getX() - this.getSize()/2),
				(int)(this.getLocation().getY() + pCmpRelPrnt.getY()- this.getSize()/2), 
				getSize(),getSize(), 0, 360);
	}

	//create a collidesWith() method that other is its parameter
	public boolean collidesWith(GameObject other) 
	{
		//variables 
		int centerX, centerY;
		centerX = (int)this.getLocation().getX() + this.getSize()/2;
		centerY = (int)this.getLocation().getY() + this.getSize()/2;
		int otherX, otherY;//CENTER POINT TOO
		otherX = (int)(other.getLocation().getX() + other.getSize()/2);
		otherY = (int)(other.getLocation().getY() + other.getSize()/2);
		int dx,dy,dxy,r,otherR,dr;
		dx = centerX - otherX;
		dy = centerY - otherY;
		dxy = (dx*dx + dy*dy);
		r = this.getSize()/2;
		otherR = other.getSize()/2;	
		dr = (r * r+ 2*r*otherR+ otherR*otherR);
		if(dxy <= dr)
			return true;
		return false;
	}
	
	@Override
	public String toString()
	{
		return "Ant: "+super.toString()+"\n"
				+"maxSpeed="+getMaximumSpeed()+" foodConsumptionRate="+
				getFoodConsumptionRate();
	}


}