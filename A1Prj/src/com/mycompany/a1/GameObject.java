package com.mycompany.a1;
 
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.models.Point;
import java.util.Random;

//create an abstract "GameObject class"
public abstract class GameObject 
{
	//attribute of the GameObject
	private int size,color;
	private Point grid;
	
	//constructor of the GameObject class
	public GameObject(int color) 
	{
		float x,y;
		Random rnd = new Random();
		x = (float)rnd.nextDouble() *  1000;
		y = (float)rnd.nextDouble() * 1000;
		this.grid  = new Point(x,y);
		this.color = color;
	}
	//another constructors of GameObject that accept color and size
	public GameObject(int color, int size) 
	{	float x,y;
		Random rnd = new Random();
		this.size = size;
		this.color = color;
		x = (float)rnd.nextDouble() *  1000;
		y = (float)rnd.nextDouble() * 1000;
		this.grid  = new Point(x,y);
		
	}
	
	
	//below are the setter method
	public void setX(float x) 
	{
		grid.setX(x);
	}
	public void setY(float y)
	{
		grid.setY(y);
	}
	public void setColor(int color) 
	{
		this.color = color;
	}
	public void setSize(int size)
	{
		this.size = size;
	}
	public void setLocation(float x, float y)
	{
		this.grid = new Point(x,y);
	}
	
	//below are the getter methods
	public float getX() 
	{
		return grid.getX();
	}

	public float getY()
	{
		return grid.getY();
	}
	
	public int getColor() 
	{
		return color;
	}
	public int getSize() 
	{
		return size;
	}
	//toString method
	public String getColortoString()
	{	String string;
		string = "["+ ColorUtil.red(this.color) + "," + ColorUtil.green(this.color) + "," + ColorUtil.blue(this.color)+"]";
		return string;
	}

}