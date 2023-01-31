package com.mycompany.a3.model;

import java.util.Observable;
import java.util.Observer;
import java.util.ArrayList;
import java.util.Random;
import com.codename1.ui.Dialog;
import com.mycompany.a3.interfaces.IIterator;
import com.mycompany.a3.interfaces.ISelectable;
//import the objects into the GameWorld 
import com.mycompany.a3.objects.Ant;
import com.mycompany.a3.objects.BSound;
import com.mycompany.a3.objects.Fixed;
import com.mycompany.a3.objects.Flag;
import com.mycompany.a3.objects.FoodStation;
import com.mycompany.a3.objects.GameObject;
import com.mycompany.a3.objects.GameWorldCollection;
import com.mycompany.a3.objects.Movable;
import com.mycompany.a3.objects.Sound;
import com.mycompany.a3.objects.Spider;
//import the views class into the GameWorld
import com.mycompany.a3.views.MapView;
import com.mycompany.a3.views.ScoreView;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

/**
 *create a GameWorld class that extends Observable 
 */
public class GameWorld extends Observable
{
	//Game's attributes
	private int lives,time;
	private GameWorldCollection game_collection;
	private boolean soundON, isPaused;

	private int height,width;
	private int timerTickRate;
	private GameObject ant;
	private int lastNumber;
	//object sounds
	private Sound stationSound,spiderSound,flagSound ;
	private BSound gameSound;
	
	//Constructor of the Game
	public GameWorld(int tickRate)
	{
		// create all Game Objects
		this.timerTickRate  = tickRate;
		game_collection = new GameWorldCollection();
		lives = 4;
		time = 0;
		isPaused = false;
		soundON = false;
		setHeight(1000);
		setWidth(1000);
		init();
		create_sounds();
	}
	
	//create             method that will create of each object in the game 
	public void create_sounds()
	{
		//sound for the game objects
		flagSound    = new Sound("hitFlag.wav");
		stationSound = new Sound("hitStation.wav");
		spiderSound  = new Sound("hitSpider.wav");
		gameSound    = new BSound("gameSound.wav");
		//if the sound is on, 
		if(soundON)
		{
			//call the playGameSound() method 
			playGameSound();
		}
	}
	
	//create a pause () method 
	public void pause() 
	{
		this.isPaused = true;
		stopGameSound();
		notifyObservers();
		
	}
	
	//create a resume() method
	public void resume() 
	{
		IIterator it = game_collection.getIterator();
		GameObject gameObject;
		while(it.hasNext())
		{
			gameObject = (GameObject)it.getNext();
			if(gameObject instanceof ISelectable) 
			{
				((ISelectable)gameObject).setSelected(false);
			}
		}
		this.isPaused = false;
		playGameSound();
		notifyObservers();
		
	}
	
	//create an addObserver() method that take a parameter "ob"
	public void addObserver(Observer ob) 
	{
		super.addObserver(ob);
		if(ob instanceof MapView)
		{
			setWidth(((MapView) ob).getHeight());
			setHeight(((MapView) ob).getWidth());		
		}
		notifyObservers();
	}
	
	//create a changeSound method of the game 
	public void changeSound() 
	{
		this.soundON = !this.soundON;
		if(soundON == true)
			playGameSound();
		else
			stopGameSound();
		notifyObservers();
	}
	
	public void notifyObservers() 
	{
		setChanged();
		notifyObservers(this);
	}
	
	public void init()
	{
		// create an object of Ant Game Object
		lastNumber = 1;
		game_collection.add(Ant.getAntObj());
		game_collection.add(new Flag(new Point(99,51), lastNumber));
		game_collection.add(new Flag(new Point(401,51), ++lastNumber));
		game_collection.add(new Flag(new Point(210,260), ++lastNumber));
		game_collection.add(new Flag(new Point(560,200), ++lastNumber));
		game_collection.add(new FoodStation());
		game_collection.add(new FoodStation());
		// add 3 spiders
		game_collection.add(new Spider());
		game_collection.add(new Spider());
		game_collection.add(new Spider());
	}
	
	public void tick () 
	{
		IIterator it = game_collection.getIterator();
		GameObject gameObject;
		while(it.hasNext()) 
		{
			gameObject = (GameObject)it.getNext();
			if(gameObject instanceof Spider) 
			{
				((Spider)gameObject).updateHeading();
			}
		}
		
		it = game_collection.getIterator();
		while(it.hasNext()) 
		{
			gameObject = (GameObject)it.getNext();
			if(gameObject instanceof Movable) {
				((Movable)gameObject).move(this.timerTickRate);
			}
		}
	
		ant = null;
		it = game_collection.getIterator();
		while(it.hasNext())
		{
			gameObject = (GameObject)it.getNext();
			if(gameObject instanceof Ant) 
			{
				ant = gameObject;
				((Ant)gameObject).setFoodLevel(((Ant)gameObject).getFoodLevel() - ((Ant)gameObject).getFoodConsumptionRate());
				break;
			}
		}
		
		if(ant != null) 
		{
			it = game_collection.getIterator();
			while(it.hasNext()) 
			{
				gameObject = (GameObject)it.getNext();
				if(gameObject instanceof Ant)
					continue;
				
				if(ant.collidesWith(gameObject)) 
				{
					if( gameObject instanceof Spider)
					{
						playHitSpiderSound();
					}
					else if( gameObject instanceof Flag) 
					{
						playHitFlagSound();
					}
					else if( gameObject instanceof FoodStation)
					{
						playHitFoodStationSound();
					}
					ant.handleCollision(gameObject);
					break;
				}
			}
		}
		time++;
		checkForWin();
		notifyObservers();
	}
	
	
	public void playHitFoodStationSound() 
	{
		if(soundON == false || isPaused)
			return;
		stationSound.play();
	}
	
	public void playHitFlagSound() 
	{
		if(soundON == false || isPaused)
			return;
		flagSound.play();
		
	}
	
	public void playHitSpiderSound() 
	{
		if(soundON == false || isPaused)
			return;
		spiderSound.play();
	}
	
	public void playGameSound() 
	{
		if(soundON == false || isPaused)
			return;
		gameSound.play();
	}
	
	public void stopGameSound() 
	{
		gameSound.pause();
	}
	
	
	/**
	 * check to see if the player win or not 
	 */
	public void checkForWin() 
	{
		IIterator it = game_collection.getIterator();
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
		if(((Ant)gameObject).getLastFlagReached() == 4) 
		{
			System.out.print("Walk It Game is over, you win! Total time: "+time);
			exit();
		}
		else if(lives == 0 ) 
		{
			System.out.println("Game over, you failed!");
			
			exit();
		}
		else if(((Ant)gameObject).getSpeed() == 0) 
		{
			System.out.println("Re-initializ Game");
			lives--;
			if(lives < 0 )
			{
				lives = 0;
			}
		}
		notifyObservers();
	}
	
	/**
	 * accelerate (increase the speed of) the ant by a small amount
	 */
	public void accelerate()
	{
		IIterator it = game_collection.getIterator();
		GameObject gameObject = null;
		while(it.hasNext()) {
			gameObject = (GameObject)it.getNext();
			if(gameObject instanceof Ant) {
				break;
			}
		}
		if(gameObject == null)
			return;
		((Ant)gameObject).setSpeed(((Ant)gameObject).getSpeed()+5);
		checkForWin();
		notifyObservers();
	}

	//create a brake() method 
	public void brake() 
	{
		IIterator it = game_collection.getIterator();
		GameObject gameObject = null;
		while(it.hasNext()) {
			gameObject = (GameObject)it.getNext();
			if(gameObject instanceof Ant) {
				break;
			}
		}
		if(gameObject == null)
			return;
		((Ant)gameObject).setSpeed(((Ant)gameObject).getSpeed()-5);
		checkForWin();
		notifyObservers();
	}
	
	//create a left() method 
	public void left()
	{
		IIterator it = game_collection.getIterator();
		GameObject gameObject = null;
		while(it.hasNext()) {
			gameObject = (GameObject)it.getNext();
			if(gameObject instanceof Ant) {
				break;
			}
		}
		if(gameObject == null)
			return;
		((Ant)gameObject).turnLeft();
		checkForWin();
		notifyObservers();
	}
	
	//create a right() method 
	public void right() 
	{
		IIterator it = game_collection.getIterator();
		GameObject gameObject = null;
		while(it.hasNext()) {
			gameObject = (GameObject)it.getNext();
			if(gameObject instanceof Ant) {
				break;
			}
		}
		if(gameObject == null)
			return;
		((Ant)gameObject).turnRight();
		notifyObservers();
	}
		
	public int getLastFlagReached()
	{
		IIterator it = game_collection.getIterator();
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
		return ((Ant)gameObject).getLastFlagReached();
	}
	
	//create a getFoodLevel() method 
	public int getFoodLevel() 
	{
		IIterator it = game_collection.getIterator();
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
	
	/*
	 * create a getHealthLevel() method
	 */
	public int getHealthLevel() 
	{
		IIterator it = game_collection.getIterator();
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
		{
			return -1;
		}
		else
		{
		return ((Ant)gameObject).getHealthLevel();
		}
	}
	
	/**
	 * create a map() method to show on the console 
	 */
	public void map()
	{
		GameObject gameObject = null;
		IIterator it = game_collection.getIterator();
		//GameObject gameObject = null;
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
		
		it = game_collection.getIterator();
		while(it.hasNext())
		{
			gameObject = (GameObject)it.getNext();
			if(gameObject instanceof FoodStation)
			{
				System.out.println(((FoodStation)gameObject).toString());
			}
			else if(gameObject instanceof Spider)
			{
				System.out.println(((Spider)gameObject).toString());
			}
			else if( gameObject instanceof Flag)
			{
				System.out.println(((Flag)gameObject).toString());
			}
		}
	}
	
	//below are setter methods
	public void setLives(int l) 
	{
		this.lives = l;
		checkForWin();
		notifyObservers();
	}
	public void setTime(int t) 
	{
		this.time = t;
		checkForWin();
		notifyObservers();
	}
	public void setHeight(int h) 
	{
		this.height = h;
	}
	public void setWidth(int w) 
	{
		this.width = w;
	}

	//Below are the getter method 
	public int getLives()
	{
		return lives;
	}
	public int getTime() 
	{
		return time;
	}
	public int getHeight() 
	{
		return height;
	}
	public int getWidth() 
	{
		return width;
	}
	public boolean isSound() 
	{
		return soundON;
	}

	public void exit() 
	{
		if(Dialog.show("Quit","Do you Want Exit our Walk It Game ?!!!!","Yes", "No")) 
		{
			System.exit(0);
		}
	}
	
	public GameWorldCollection getGwc() 
	{
		return game_collection;
	}

	public boolean isPaused()
	{
		return isPaused;
	}
	
}