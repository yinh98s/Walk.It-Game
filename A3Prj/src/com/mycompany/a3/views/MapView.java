package com.mycompany.a3.views;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point2D;
import com.codename1.ui.plaf.Border;
import com.mycompany.a3.model.GameWorld;
import com.mycompany.a3.objects.Ant;
import com.mycompany.a3.objects.Flag;
import com.mycompany.a3.objects.FoodStation;
import com.mycompany.a3.objects.GameObject;
import com.mycompany.a3.objects.GameWorldCollection;
import com.mycompany.a3.objects.Spider;
import com.mycompany.a3.interfaces.IDrawable;
import com.mycompany.a3.interfaces.IIterator;
import com.mycompany.a3.interfaces.ISelectable;

/*
 * create a MapView class that extends Container and implements Observer
 */
public class MapView extends Container implements Observer 
{
	//attributes of the MapView class
	
	private GameWorld game_world;
	private GameWorldCollection game_collection;
	private GameObject gameObject;
	private IIterator it;
	private Point2D location , point1 , point2;
	
	//constructor of the MapView
	public MapView() 
	{
		this.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.rgb(255, 0, 0)));
		this.getAllStyles().setBgColor(ColorUtil.WHITE);
		this.getAllStyles().setBgTransparency(255);
	}
	
	//create a paint() method
	public void paint(Graphics gra)
	{
		super.paint(gra);	
		location = new Point2D(getX(),getY());	
		// draw Game Objects
		gameObject = null;
		it = game_collection.getIterator();
		while(it.hasNext()) 
		{
			gameObject = (GameObject)it.getNext();
			if(gameObject instanceof IDrawable)
			{
				((IDrawable) gameObject).draw(gra, location);
			}
		}
	}
	
	public void pointer_press(int a, int b)
	{
		if(game_world.isPaused())
		{
			a = a - getParent().getAbsoluteX();
			b = b - getParent().getAbsoluteY();
			
			point1 = new Point2D(a,b);
			point2 = new Point2D(getX(), getY());
			
			game_collection = game_world.getGwc();
			IIterator iter = game_collection.getIterator();
			while(iter.hasNext())
			{
				gameObject = (GameObject)iter.getNext();
				if(gameObject instanceof ISelectable)
				{
					if( ((ISelectable) gameObject).contains(point1, point2))	
					{
						((ISelectable) gameObject).setSelected(true);
					}else
						((ISelectable) gameObject).setSelected(false);
				}
			}
		}
		game_world.notifyObservers();
		repaint();
	}
	
	public void update(Observable o, Object arg) 
	{
		if(! (arg instanceof GameWorld))
			return;
		game_world= (GameWorld)arg ;
		System.out.println("***************************MAP************************\n");
		game_world.map();
		System.out.println("\n***************************************************");
		game_collection = game_world.getGwc();
		repaint();
		
	}
	
	public Point2D getLocation() 
	{
		return location;
	}
	
	
}