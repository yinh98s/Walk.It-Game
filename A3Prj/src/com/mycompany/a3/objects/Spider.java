package com.mycompany.a3.objects;

import java.util.Random;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point2D;
import com.mycompany.a3.interfaces.IDrawable;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

/*
 * Create a Spider class that extends implements IDrawable 
 */
public class Spider extends Movable implements IDrawable
{
	//attributes of the class
	private int x1,x2,x3 ;
	private int y1,y2,y3;
	//constructor of the class
	public Spider() 
	{
		super((60 + new Random().nextInt(10)),new Point(550 * new Random().nextFloat(), 300 * new Random().nextFloat()), ColorUtil.rgb(0, 0, 0));
		//set speed 
		setSpeed(5 + new Random().nextInt(6));
		//set heading 
		setHeading(new Random().nextInt(360));
	}
	
	public void setColor(int color) 
	{
	}

	public void updateHeading()
	{
		setHeading(getHeading() - 5);
		if (getHeading() < 0) 
		{
			setHeading(360 - -getHeading());
		}
	}
	@Override
	public void draw(Graphics gra, Point2D pCmpRelPrnt) 
	{
		gra.setColor(this.getColor());
		x1 = (int)(this.getLocation().getX() + pCmpRelPrnt.getX() - getSize()/2);
		x2 = x1 + this.getSize();
		x3 = (int)(x1+this.getSize()/2);
		y1 = (int)( this.getLocation().getY() + pCmpRelPrnt.getY()- getSize()/2);
		y2 = y1;

		y3 = y1+this.getSize();
		
		int pointsx[] = {x1 , x2 , x3};
		int pointsy[] = {y1 , y2 , y3};
		gra.drawPolygon(pointsx, pointsy, 3);
	}
	@Override
	public boolean collidesWith(GameObject otherObject)
	{
		return false;
	}
	@Override
	public void handleCollision(GameObject otherObject)
	{
	}
	
	@Override
	public String toString()
	{
		
		return "Spider: "+super.toString();
	}
}