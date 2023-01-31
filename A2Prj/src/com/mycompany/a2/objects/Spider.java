package com.mycompany.a2.objects;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import java.util.Random;

//create a "Spider" class that extends "Movable" class
public class Spider extends Movable 
{
	//constructor
	public Spider()
	{
		super((10 + new Random().nextInt(41)),new Point(1000 * new Random().nextFloat(), 1000 * new Random().nextFloat()),ColorUtil.rgb(0,0,0));
		setHeading(new Random().nextInt(360));
		setSpeed(5 + new Random().nextInt(6));
		
	}

	public void updateHeading()
	{
		setHeading(getHeading() - 5);
		if (getHeading() < 0) 
		{
			setHeading(360 - -getHeading());
		}
	}
	
	public void setColor(int color) 
	{
	}
	
	@Override
	public String toString() 
	{
		String str;
		str = "Spider : "+super.toString();
		return str;
	}
}