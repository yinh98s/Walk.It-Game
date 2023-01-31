package com.mycompany.a1;

import java.util.Random;
import com.codename1.charts.util.ColorUtil;

//create a "FoodStation" class that extends the "Fixed" class
public class FoodStation extends Fixed
{
	//attribute of the class
    private int capacity;
    Random random = new Random();
    //constructor of the class
    public FoodStation() 
    {
    	super(ColorUtil.GREEN);
    	final int MIN_SIZE = 10, MAX_SIZE = 50;
		super.setSize(new Random().nextInt(MAX_SIZE - MIN_SIZE) + MIN_SIZE); 
    	this.capacity = this.getSize();
    }
    
    
    //below are the setter methods
    public void setCapacity(int capacity)
    {
        this.capacity = capacity;
    }
    public void setSize(int size){}
    public void setColor(int color){}
    
    //getter method 
    public int getCapacity()
    {
        return capacity;
    }
    
    //this method check for the food 
    public boolean checkEnergyStation()
    {
        if (capacity == 0)
            return false;
        else
            return true;
    }
    //toString method 
    public String toString() 
    {
    	String string;
    	double x, y;
    	x = Math.round(this.getX()* 10.0)/ 10.0;
    	y = Math.round(this.getY()*10.0)/10.0;
    	
    	string = "EnergyStation: loc= " +x  +","+ y+
    			"  color= " + this.getColortoString() + 
    			"  size= " + this.getSize()+
    			"  capacity="+ this.getCapacity();
    	return string;
    }  
}
