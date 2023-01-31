package com.mycompany.a2.model;

import java.util.Random;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Dialog;
import com.mycompany.a2.interfaces.IIterator;
import com.mycompany.a2.objects.Ant;
import com.mycompany.a2.objects.Flag;
import com.mycompany.a2.objects.FoodStation;
import com.mycompany.a2.objects.GameObject;
import com.mycompany.a2.objects.GameObjectCollection;
import com.mycompany.a2.objects.Movable;
import com.mycompany.a2.objects.Spider;
import com.mycompany.a2.views.MapView;
import com.mycompany.a2.views.ScoreView;

//create a "GameWorld" that extends "Observable"
public class GameWorld extends Observable
{
	//attributes of the class
	private int lives, time, lastNumber;
	private GameObjectCollection goc;//"goc" defines as game object
	private boolean sound;
	private boolean antAdded;
	
	//constructor of the game 
	public GameWorld() 
	{
		goc = new GameObjectCollection();
		time = 0;
		lives = 3;
		sound = true;
		init();
		notifyObservers();
		antAdded = false;
	}
	
	//below are all the methods in my Game world
	
	//changeSoundStatus() method
	public void SoundStatus() 
	{
		this.sound = !this.sound;
		notifyObservers();
	}
	//addOberserver() method
	public void addObserver(Observer observer) 
	{
		super.addObserver(observer);
		notifyObservers();
	}
	//notifyObservers()method
	public void notifyObservers() 
	{
		setChanged();
		notifyObservers(this);
	}
	
	public void init()
	{

		goc.add(new Ant(new Point(50,50)));
		lastNumber = 1;
		antAdded = true;
		
		//operation on init() methods
		goc.add(new Flag(new Point(50,50), lastNumber));
		goc.add(new Flag(new Point(70,70), ++lastNumber));
		goc.add(new Flag(new Point(450,350), ++lastNumber));
		goc.add(new Flag(new Point(600,600), ++lastNumber));
		goc.add(new FoodStation());
		goc.add(new FoodStation());
		goc.add(new FoodStation());
		goc.add(new Spider());
		goc.add(new Spider());
		goc.add(new Spider());
		
	}
	
	

	public void checkWin() 
	{
		IIterator it ;
		it = goc.getIterator();
		GameObject gameObject = null;
		while(it.hasNext()) 
		{
			gameObject = (GameObject)it.getNext();
			if(gameObject instanceof Ant) 
			{
				break;
			}
		}
		if(gameObject == null)
			return;
		if(((Ant)gameObject).getLastFlagReached() == 4) {
			System.out.print("Game is over! you won!The Total time is: " +time);
			exit();
		}
		else if(lives == 0 ) 
		{
			System.out.println("Game over! You failed!");
			exit();
		}
		else if(((Ant)gameObject).getSpeed() == 0)
		{
			System.out.println("Restart the Game");
			lives--;
			if(lives < 0 )
				lives = 0;
		}
		notifyObservers();
	}
	
	public void tick () 
	{
		//iterator
		IIterator it ;
		it = goc.getIterator();
		GameObject gameObject;
		//use while loop for the has.next()
		while(it.hasNext()) 
		{
			gameObject = (GameObject)it.getNext();
			if(gameObject instanceof Spider) 
			{
				((Spider)gameObject).updateHeading();
			}
		}

		it = goc.getIterator();
		while(it.hasNext()) 
		{
			gameObject = (GameObject)it.getNext();
			if(gameObject instanceof Movable) 
			{
				((Movable)gameObject).move();
			}
		}

		it = goc.getIterator();
		while(it.hasNext()) 
		{
			gameObject = (GameObject)it.getNext();
			if(gameObject instanceof Ant) 
			{
				((Ant)gameObject).setFoodLevel(((Ant)gameObject).getFoodLevel() - ((Ant)gameObject).getFoodConsumptionRate());
				break;
			}
		}
		time++;
		checkWin();
		notifyObservers();
	}

	
	public void accelerate() 
	{
		IIterator it = goc.getIterator();
		GameObject gameObject = null;
		while(it.hasNext()) 
		{
			gameObject = (GameObject)it.getNext();
			if(gameObject instanceof Ant) {
				break;
			}
		}
		if(gameObject == null)
			return;
		((Ant)gameObject).setSpeed(((Ant)gameObject).getSpeed()+5);
		checkWin();
		notifyObservers();
	}
	
	
	public void brake() 
	{
		IIterator it = goc.getIterator();
		GameObject gameObject = null;
		while(it.hasNext()) 
		{
			gameObject = (GameObject)it.getNext();
			if(gameObject instanceof Ant) 
			{
				break;
			}
		}
		if(gameObject == null)
			return;
		((Ant)gameObject).setSpeed(((Ant)gameObject).getSpeed()-5);
		checkWin();
		notifyObservers();
	}
	

	public void left() 
	{
		IIterator it = goc.getIterator();
		GameObject gameObject = null;
		while(it.hasNext()) 
		{
			gameObject = (GameObject)it.getNext();
			if(gameObject instanceof Ant) {
				break;
			}
		}
		if(gameObject == null)
			return;
		((Ant)gameObject).moveLeft();
		checkWin();
		notifyObservers();
	}


	public void right() 
	{
		IIterator it = goc.getIterator();
		GameObject gameObject = null;
		while(it.hasNext())
		{
			gameObject = (GameObject)it.getNext();
			if(gameObject instanceof Ant) 
			{
				break;
			}
		}
		if(gameObject == null)
			return;
		((Ant)gameObject).moveRight();
		notifyObservers();
	}
	
	public void collisionWithFlag(int num)
	{
		IIterator it = goc.getIterator();
		GameObject gameObject = null;
		while(it.hasNext()) {
			gameObject = (GameObject)it.getNext();
			if(gameObject instanceof Ant) 
			{
				break;
			}
		}
		if(gameObject == null)
			return;
		((Ant)gameObject).collisionWithFlag(num);
		notifyObservers();
	}
	

	public void collisionWithFoodStation() 
	{
		Random rand = new Random();
		IIterator it;
		GameObject gameObject;
		int index;
		while(true) 
		{
			index = rand.nextInt(goc.size());
			
			if(goc.get(index) instanceof FoodStation
					&& ((FoodStation)goc.get(index)).getCapacity() != 0) 
			{
				it = goc.getIterator();
				gameObject = null;
				while(it.hasNext()) {
					gameObject = (GameObject)it.getNext();
					if(gameObject instanceof Ant) {
						break;
					}
				}
				if(gameObject != null)
					((Ant)gameObject).setFoodLevel(((Ant)gameObject).getFoodLevel() + ((FoodStation)goc.get(index)).getCapacity());
				((FoodStation)goc.get(index)).setCapacity(0);
				goc.get(index).setColor(ColorUtil.rgb(0, 200, 0) );
				goc.add(new FoodStation());
				break;
			}
		}
		checkWin();
		notifyObservers();
	}
	
	public void collisionWithSpider() 
	{
		IIterator it = goc.getIterator();
		GameObject gameObject = null;
		while(it.hasNext()) 
		{
			gameObject = (GameObject)it.getNext();
			if(gameObject instanceof Ant) {
				break;
			}
		}
		if(gameObject == null)
			return;
		((Ant)gameObject).collisionWithSpider();
		checkWin();
		notifyObservers();
	}
	

	
	
	public int getLastFlagReached() 
	{
		IIterator it = goc.getIterator();
		GameObject gameObject = null;
		while(it.hasNext()) {
			gameObject = (GameObject)it.getNext();
			if(gameObject instanceof Ant) {
				break;
			}
		}
		if(gameObject == null)
			return -1;
		return ((Ant)gameObject).getLastFlagReached();
	}
	
	public int getFoodLevel() 
	{
		IIterator it = goc.getIterator();
		GameObject gameObject = null;
		while(it.hasNext()) {
			gameObject = (GameObject)it.getNext();
			if(gameObject instanceof Ant) 
			{
				break;
			}
		}
		if(gameObject == null)
			return -1;
		return ((Ant)gameObject).getFoodLevel();
	}
	
	public int getHealthLevel() 
	{
		IIterator it = goc.getIterator();
		GameObject gameObject = null;
		while(it.hasNext()) {
			gameObject = (GameObject)it.getNext();
			if(gameObject instanceof Ant) {
				break;
			}
		}
		if(gameObject == null)
			return -1;
		return ((Ant)gameObject).getHealthLevel();
	}
	
	public void map() 
	{
		IIterator it = goc.getIterator();
		GameObject gameObject = null;
		while(it.hasNext()) 
		{
			gameObject = (GameObject)it.getNext();
			if(gameObject instanceof Ant)
			{
				break;
			}
		}
		if(gameObject != null)
		{
			System.out.println((Ant)gameObject);
		}
		it = goc.getIterator();
		while(it.hasNext())
		{
			gameObject = (GameObject)it.getNext();
			if( gameObject instanceof Spider)
			{
				System.out.println(((Spider)gameObject).toString());
			}
			else if( gameObject instanceof FoodStation)
			{
				System.out.println(((FoodStation)gameObject).toString());
			}
			else if( gameObject instanceof Flag)
			{
				System.out.println(((Flag)gameObject).toString());
			}
		}
	}
	
	
	public void display() 
	{

	}
	
	
	//setter methods
	public void setLives(int lives) 
	{
		this.lives = lives;
		checkWin();
		notifyObservers();
	}
	public void setTime(int time) 
	{
		this.time = time;
		checkWin();
		notifyObservers();
	}
	
	public void setAntIsAdded(boolean antIsAdded) 
	{
		this.antAdded = antIsAdded;
		notifyObservers();
	}

	//getter methods
	public int getLives() 
	{
		return lives;
	}
	public int getTime() 
	{
		return time;
	}

	public boolean isSound() 
	{
		return sound;
	}

	public boolean isAntIsAdded() {
		return antAdded;
	}

	

	public void exit() 
	{
		if(Dialog.show("Quit", "Would you like to Quit my Game ? ","Yes", "No")) 
		{
			System.exit(0);
		}
	}
}