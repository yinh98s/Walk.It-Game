package com.mycompany.a3.objects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.charts.models.Point;
import com.codename1.ui.geom.Point2D;
import com.mycompany.a3.interfaces.IDrawable;

/*
 * create a Flag class that extends Fixed and implements IDrawable 
 */
public class Flag extends Fixed implements IDrawable
{
	private int x1 , x2 , x3 , y1 , y2 , y3;
	private int seqNum;
	private boolean reached;
	
	//constructor of the Flag class
	public Flag(Point grid, int num) 
	{
		super(60,grid,ColorUtil.rgb(0,0, 255));
		this.seqNum = num;
		//set reached to false 
		reached = false;
	}
	
	//setter methods
	public void setSequenceNumber(int sequenceNumber) 
	{
		this.seqNum = sequenceNumber;
	}
	
	public void setReached(boolean reached)
	{
		this.reached = reached;
	}
	
	@Override
	public void setSelected(boolean selected)
	{
		this.selected = selected;
	}
	//getter methods 
	public int getSequenceNumber() 
	{
		return seqNum;
	}
	
	public boolean isReached()
	{
		return reached;
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
		if(this.selected)
			gra.drawPolygon(pointsx, pointsy, 3);
		else
			gra.fillPolygon(pointsx, pointsy, 3);
		gra.setColor(ColorUtil.BLACK);
		gra.drawString(""+this.getSequenceNumber(),(int)(x1+this.getSize()/3) ,(int)(y1+this.getSize()/4));
	
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
	public boolean isSelected() 
	{
		return this.selected;
	}
	@Override
	public boolean contains(Point2D p1, Point2D p2) 
	{
		int x1,x2,y1,y2;
		x1 = (int) p1.getX();
		x2 = (int) (p2.getX() + this.getLocation().getX());
		y1 = (int) p1.getY();
		y2 = (int) (p2.getY() + this.getLocation().getY());
		
		if((x1 >= x2 - getSize()/2) && (x1 <= x2 + this.getSize()/2)
			&& (y1 >= y2 - getSize()/2) && (y1 <= y2 + this.getSize()/2))
			return true;
		else
			return false;
	}
	
	@Override
	public String toString() 
	{
		// Flag: location=200.0,200.0 color=[0,0,255] size=10 seqNum=1
		return "Flage: "+super.toString()+
				" size="+getSize()+" seqNum="+getSequenceNumber();
	}
	
	
}