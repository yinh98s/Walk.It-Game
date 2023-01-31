package com.mycompany.a1;

import com.codename1.charts.util.ColorUtil;  
import com.codename1.charts.models.Point;

//create a "Ant" class that extends the " Movable" and implement "ISteerable"
public class Ant extends Movable implements ISteerable
{
	//attribute of the class
    private int steeringDirection;
    private int maximumSpeed;
    private int foodLevel;
    private int foodConsumptionRate;
    private int healthLevel;
    private int lastFlagReached;
    private int life;
    private int maxDamageLevel;
    private Flag flag;
    
    //constructor of the "Ant" class
	public Ant( ) 
	{
		super(ColorUtil.GRAY,30);
		super.setSpeed(0);
		super.setHeading(0);
		this.steeringDirection = 0;
		this.maximumSpeed= 30;
		this.foodConsumptionRate = 1;
		this.foodLevel = 20;
		this.healthLevel = 0;
		this.lastFlagReached = 1;
		life = 3;
		this.maxDamageLevel = 30;
	}
	
	
	public void turnLeft() 
	{
		int currentheading = this.getSteeringDirection();
		if (currentheading == 0) 
		{
			currentheading = currentheading - 5;
			this.setSteeringDirection(currentheading);
			if (this.getHeading() == 0 )
			{
				this.setHeading(360+currentheading);
			}
			else {
				this.setHeading(this.getHeading() + currentheading);
			}
			}
		else {
			if (currentheading > -40)
			{
				currentheading -=5;
				this.setSteeringDirection(currentheading);
				if (this.getHeading() == 0 )
				{
					this.setHeading(360+currentheading);
				}
				else {
					this.setHeading(this.getHeading() + currentheading);
				}
			}
			else {
				System.out.println("Can only turn left max 40");
			}
		}
	}
	
	@Override
	public void turnRight() 
	{
		int currentheading = this.getSteeringDirection();
		if (currentheading == 0) 
		{
			currentheading += + 5;
			this.setSteeringDirection(currentheading);
			if (this.getHeading() == 0 )
			{
				this.setHeading(currentheading);
			}
			else {
				this.setHeading(this.getHeading() + currentheading);
			}
		}
		else {
			if (currentheading < 40)
			{
				currentheading +=5;
				this.setSteeringDirection(currentheading);
				if (this.getHeading() == 0 )
				{
					this.setHeading(currentheading);
				}
				else {
					this.setHeading(this.getHeading() + currentheading);
				}
			}
			else {
				System.out.println("Can only turn left max 40");
			}
		}
		
	}
	
	public void increaseSpeed() 
	{
		int currentSpeed = getSpeed();
		if (!isMaximumSpeed()) 
		{
			this.setSpeed(++currentSpeed);
		}	
	}
	
	public void decreaseSpeed() 
	{
		int currentSpeed = getSpeed();
		if (currentSpeed > 0)
		{
			this.setSpeed(--currentSpeed);
		}
	}
	public boolean isMaxDamageLevel()
	{
		if (this.getDamageLevel() == maxDamageLevel)
		{
			return true;
		}
		else
		{
		return false;
		}
	}
	public boolean isMaximumSpeed() 
	{
		if (this.getSpeed() >= maximumSpeed)
		{
			System.out.println("You cannot cross maximum speed.");
			return true;
		}
		else
		{
			return false;
		}
	}

	public void increaseDamageLevel() 
	{
		this.healthLevel++;
	}
	public void checkDamageLevel() 
	{
		
		if(healthLevel > 0 && healthLevel < maxDamageLevel)
		{
			
			if (this.getSpeed() < this.getMaximumSpeed())
			{
				this.setMaximumSpeed(this.maxDamageLevel-this.healthLevel);
			}
			else {
				this.setSpeed(this.getMaximumSpeed());
			}
		}
		else if (healthLevel == maxDamageLevel)
		{
			this.setSpeed(0);	
		}
		
	}
	public void fooodLevelTick() 
	{
		this.setFoodLevel(this.getFoodLevel()- this.getFoodConsumptionRate());
	}
	 
	public void resetAnt(float x, float y) 
	{
		this.setX(x);
		this.setY(y);
		this.setHeading(0);
		this.maximumSpeed= 30;
		this.foodConsumptionRate = 1;
		this.foodLevel = 20;
		this.healthLevel = 0;
		this.lastFlagReached = flag.getSequenceNumber();
		this.maxDamageLevel = 50;
		life--;
	}
	
	//Below are all the setter methods
	
	public void setLife(int x)
	{
		life = x;
	}
	
	public void setSteeringDirection(int strDirection)
    {
    	this.steeringDirection = strDirection;
    }
	
	public void setMaximumSpeed(int maxSpeed) 
	{
		this.maximumSpeed = maxSpeed;	
	}
	
	public void setFoodLevel(int foodLevel) 
	{
		this.foodLevel = foodLevel;
	}
	public void setFoodConsumptionRate(int consumptionRate) 
	{
		this.foodConsumptionRate = consumptionRate;
	}
	public void setDamageLevel(int damageLevel) 
	{
		this.healthLevel = damageLevel;
	}
	public void setLastFlageached(int flagReached)
	{
		this.lastFlagReached = flagReached;
	}
	
	public void setSize(int size) {}	
    
	//Below are all the getter methods
	
	public int getMaxDamageLevel() 
	{
		return this.maxDamageLevel;
	}
	
	public int getLife() 
	{
		return life;
	}
	
    public int getSteeringDirection() 
    {
    	return steeringDirection;
    }
    
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
	
	public int getDamageLevel() 
	{
		return healthLevel;
	}
	
	public int getLastBaseReached() 
	{
		return lastFlagReached;
	}

	
	@Override
	public String toString() 
	{
		return ("Ant: loc=" + Math.round(this.getX()* 10.0)/ 10.0  +","+ Math.round(this.getY()*10.0)/10.0+
				"  color= " + this.getColortoString()+
				"  speed= " + this.getSpeed()+
				"  heading= " + this.getHeading()+
				"  size= " + this.getSize()+
				"  maxSpeed=" + this.getMaximumSpeed()+
				"  sterringDirection=" + this.getSteeringDirection()+
				"  foodLevel=" + this.getFoodLevel()+
				"  healthLevel=" + this.getDamageLevel());
	}
}
