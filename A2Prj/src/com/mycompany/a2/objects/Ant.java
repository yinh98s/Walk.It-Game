
package com.mycompany.a2.objects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.models.Point;
import com.mycompany.a2.interfaces.Isteerable;

////create a "Ant" class that extends the " Movable" and implement "ISteerable"
public class Ant extends Movable implements Isteerable
{
	//attribute of "Ant" class
	private int foodLevel; //level food of the ant object
	private int foodConsumptionRate; //rate of the food consumption for the ant object
	private int maximumSpeed; //a variable to define the maximum speed of the ant 
	//also, set the ant's speed to 120 
	private final int MAXSPEED = 120;
	private int healthLevel; //level health of the ant object 
	private int lastFlagReached;//a variable  to define where the ant lastly reached in the game 
	private int redColor; //ant is red 
	private static Ant antObject = new Ant(new Point(50,50));//starting point of the ant object 
	
	//constructor of "Ant" class
	public Ant(Point location)
	{
		
		super(20, location , ColorUtil.rgb(255,0,0));
		setMaximumSpeed(MAXSPEED);
		setFoodLevel(75);
		setFoodConsumptionRate(2);
		setHealthLevel(10);
		setHeading(0);
		setLastFlagReached(1);
		setSpeed(20);
		redColor = 255;
	
		
	}
	//moveRight() method of the Ant
	public void moveRight()
	{
		setHeading(getHeading() + 5);
		if(getHeading() >= 360)
		{
			setHeading(getHeading() - 360);
		}

	}
	//moveLeft() method of the Ant
	public void moveLeft()
	{
		setHeading(getHeading()- 5);
		if(getHeading() < 0)
		{
			setHeading(360 - getHeading());	
		}
	}
	
	//setSpeed() method of the Ant
	public void setSpeed(int speed)
	{
		if(getHealthLevel() == 0 || getFoodLevel()== 0 || speed < 0)
		{
			//the ant wont be able work since health,speed,food level is 0.
			super.setSpeed(0);
		}
		//if not 0 or less,
		else if(speed >= 0 && speed <= getMaximumSpeed())
		{
			//the ant will be able to work with its specified speeds
			super.setSpeed(speed);
		}
	}
	
	//flag-ant collision method
	public void collisionWithFlag(int sequenceNumber)
	{
		if(sequenceNumber == getLastFlagReached()+1) 
		{
			setLastFlagReached(sequenceNumber);
		}
	}
	
	//spider-ant collision method
	
	public void collisionWithSpider() 
	{
		setHealthLevel(getHealthLevel()-1);
		
		if(getHealthLevel() < 0 )
			setHealthLevel(0);
		maximumSpeed = (int) (MAXSPEED * (getHealthLevel()/ 10.0));
		
		// if the ant's speed accelerate more than the max speed,
		if (getSpeed() > maximumSpeed)
			//the ant's speed is only at their max speed
			setSpeed(maximumSpeed);
		redColor -= 25;
		//color of the ant is always red since it was created 
		setColor(ColorUtil.rgb(redColor, 0, 0));
	}
	
	
	//setter methods 
	public void setMaximumSpeed(int maxSpeed) 
	{
		this.maximumSpeed = maxSpeed;
	}
	public void setFoodLevel(int foodLevel)
	{
		this.foodLevel = foodLevel;
		if(getFoodLevel() == 0)
			setSpeed(0);
	}
	public void setFoodConsumptionRate(int rate) 
	{
		this.foodConsumptionRate = rate;
	}
	public void setHealthLevel(int healthLevel) 
	{
		this.healthLevel = healthLevel;
		if(getHealthLevel() == 0)
			setSpeed(0);
	}
	
	//getter methods
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

	public void setLastFlagReached(int lastFlag) 
	{
		this.lastFlagReached = lastFlag;
	}

	public int getRedValue() 
	{
		return redColor;
	}
	
	public static Ant getAntObj() 
	{
		return antObject;
	}

	
	@Override
	//toString() method representing the attributes the Ant object in the game
	public String toString()
	{
		
		//create a string variable "str"
		String str;
		str = "Ant : "+ super.toString() + "\n"
				+ "MAX SPEED : "+ getMaximumSpeed() + "  " + "," +"  "
				+ "Food Consumption Rate : " + getFoodConsumptionRate();
		return str;
		
	}
	
}


