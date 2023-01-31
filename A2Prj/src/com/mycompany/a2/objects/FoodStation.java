package com.mycompany.a2.objects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.models.Point;
import java.util.Random;

//create a "FoodStation" class that extends " Fixed" class
public class FoodStation extends Fixed 
{
	//attributes of the FoodStation class
	private int capacity; //define the amount of food in the food station 
	
	//constructor
	public FoodStation() 
	{
		super((10+ new Random().nextInt(41)),new Point(1000 * new Random().nextFloat(), 1000 * new Random().nextFloat()), ColorUtil.rgb(0, 255, 0));
		this.capacity = getSize();
	}

	//setter method
	public void setCapacity(int amount) 
	{
		this.capacity = amount;
	}
	//getter method 
	public int getCapacity() 
	{
		return capacity;
	}

	
	@Override
	public String toString() 
	{
		return "Food Station : "+ super.toString()+
				" size : "+getSize()+" Capacity="+getCapacity();
	}

}