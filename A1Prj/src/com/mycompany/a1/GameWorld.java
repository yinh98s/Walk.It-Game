package com.mycompany.a1;

import java.util.ArrayList;
import java.util.Random;
import com.codename1.charts.util.ColorUtil;

//create a "GameWorld" class for the game 
public class GameWorld 
{
	//attribute of the "GameWorld" class
	Random random = new Random();
	final private int gameWorldWidth = 1000, gameWorldHeight = 1000;
	private int timer;
	private int life;
	private int flagCount;
	private int foodStationCount;
	private int spiderCount;
	private int antCount;
	//array list of the GameObject 
	private ArrayList <GameObject> gameObject;
	//boolean for the game over or not
	private boolean gameOver;
	
	//constructor of the class
	public GameWorld(){}
	// Initialize the game
	public void init() 
	{
		gameObject = new ArrayList<GameObject>();
		//set the time to 0
		this.timer = 0;
		//set the number of flag to 0 
		this.flagCount = 0;
		//set the food station to 0
		this.foodStationCount = 0;
		//set the number of spider to 0
		this.spiderCount = 0;
		//set the number of ant to 0
		this.antCount = 0;
		//set the game to false at the start
		this.gameOver = false;
		
		//add the game object 
		addGameObjects();
	}
	 
	//Adding GameObjects to the GameWorld
	public void addGameObjects() 
	{
		//variables 
		int flagObject, foodStation;
		flagObject = 4 + random.nextInt(5);
		foodStation = 2+ random.nextInt(5);
		
		for (int i = 0; i < foodStation; i++) 
		{
			gameObject.add(new FoodStation());
			foodStationCount++;
		}
		for (int i = 0; i < foodStation ; i++) 
		{
			gameObject.add(new Spider());
			spiderCount++;
		}
		
		for (int i = 1; i < flagObject; i++) 
		{
			gameObject.add(new Flag(i));
			 flagCount++;
		}
		
		gameObject.add(new Ant());
		antCount++;
	}
	
	// Game Methods   
	//A " decrease" method will indicates when the ant slows down the speed
	public void decreaseSpeed() 
	{
		System.out.println("The ant decreases the speed.");
		for (int i = 0; i < gameObject.size(); i++)
		{
			if (gameObject.get(i) instanceof Ant)
			{
				((Ant) gameObject.get(i)).decreaseSpeed();
			}
		}
	}
	
	// A " increasingSpeed" method will indicates when the ant speeds up 
	public void increaseSpeed()
	{
		System.out.println("The Ant increases the speed.");
		for (int i = 0; i < gameObject.size(); i++)
		{
			if (gameObject.get(i) instanceof Ant)
			{
				((Ant) gameObject.get(i)).increaseSpeed();
			}
		}
	}
	
	//A " leftTurnAnt" method indicates when the ant makes a left turn 
	public void leftTurnAnt() 
	{
		System.out.println("The Ant makes a left turn.");
		for (int i = 0; i < gameObject.size(); i++)
		{
			if (gameObject.get(i) instanceof Ant)
			{
				((Ant) gameObject.get(i)).turnLeft();
			}

		}
	}
	//A " rightTurnAnt" method indicates when the ant makes a right turn
	public void rightTurnAnt() 
	{
		System.out.println("The Ant makes a right turn.");
		for (int i = 0; i < gameObject.size(); i++)
		{
			if (gameObject.get(i) instanceof Ant)
			{
				((Ant) gameObject.get(i)).turnRight();
			}
		}
	}

	
	//A "collisionFlag" method indicates when there is a collision between the ant and flag
	public void collisionFlag(int collision) 
	{
		System.out.println("The collision between Ant with Flag has occured.");
		for (int i = 0; i < gameObject.size(); i++)
		{
			if (gameObject.get(i) instanceof Ant)
			{
				int temp;
				temp = ((Ant) gameObject.get(i)).getLastBaseReached();
				temp++;
				if (temp == collision) 
				{
					System.out.println("The Ant has reached the next flag.");
					((Ant) gameObject.get(i)).setLastFlageached(collision);
				}
				else 
				{
					System.out.println("Had passed or haven't reached yet");
				}
				if (temp == 9)
				{
					System.out.println("Congratulation!You won the game.");
					System.exit(0);
				}
			}
		}
	}
	//A "collisonAnt' method indicates when there is a collision between ant and ant
	public void collsionAnt() 
	{
		System.out.println("The collision between Ant with Ant has occured!!");
		for (int i = 0; i < gameObject.size(); i++)
		{
			if (gameObject.get(i) instanceof Ant)
			{
				if (((Ant) gameObject.get(i)).isMaxDamageLevel() == true)
				{
					System.out.println("Your Ant is dead. ");
					if (((Ant) gameObject.get(i)).getLife() != 0) 
					{
						int temp_last_flag = ((Ant) gameObject.get(i)).getLastBaseReached();
						for (int j = 0; j < gameObject.size(); j++)
						{
							if (gameObject.get(j) instanceof Flag)
							{
								if (temp_last_flag == (((Flag) gameObject.get(j)).getSequenceNumber()))
								{
									float flag_x = (((Flag) gameObject.get(j)).getX());
									float flag_y = (((Flag) gameObject.get(j)).getY());
									((Ant) gameObject.get(i)).resetAnt(flag_x,flag_y);
								}
							}
						}
						
						}
					else {
						System.out.println("Game is over!!!");
						System.exit(0);
					}
				}
				
				else {
					((Ant) gameObject.get(i)).increaseDamageLevel();
					((Ant) gameObject.get(i)).checkDamageLevel();
					((Ant) gameObject.get(i)).setColor(ColorUtil.rgb(255, 102, 102));
					}
			}
			}
			
	}
	//
	public void collisionSpider() 
	{
		System.out.println("The collision between Ant and Spider has occured!!!");
		for (int i = 0; i < gameObject.size(); i++)
		{
			if (gameObject.get(i) instanceof Ant)
			{
				if (((Ant) gameObject.get(i)).isMaxDamageLevel() == true) {
					System.out.println("You ant is dead. ");
					if (((Ant) gameObject.get(i)).getLife() != 0) 
					{
						int temp_last_flag = ((Ant) gameObject.get(i)).getLastBaseReached();
						for (int j = 0; j < gameObject.size(); j++)
						{
							if (gameObject.get(j) instanceof Flag)
							{
								if (temp_last_flag == (((Flag) gameObject.get(j)).getSequenceNumber()))
								{
									float flag_x = (((Flag) gameObject.get(j)).getX());
									float flag_y = (((Flag) gameObject.get(j)).getY());
									((Ant) gameObject.get(i)).resetAnt(flag_x,flag_y);
								}
							}
						}
						
						}
					else {
						System.out.println("Game is over!!!");
						System.exit(0);
					}
				}
				
				else {
					((Ant) gameObject.get(i)).increaseDamageLevel();
					((Ant) gameObject.get(i)).checkDamageLevel();
					((Ant) gameObject.get(i)).setColor(ColorUtil.rgb(255, 102, 102));
					}
			}
			}
	}
	//A "collisionFoodStation" method indicates when there is a collision btw ant and the food station 
	public void collisionFoodStation() 
	{
		System.out.println("The collision between Ant and Food Stations has occured");
		for (int i = 0; i < gameObject.size(); i++)
		{
			
			if (gameObject.get(i) instanceof Ant)
			{
				for(int j = 0; j < gameObject.size(); j++)
				{
					if (gameObject.get(j) instanceof FoodStation) {
						if (((FoodStation) gameObject.get(j)).getCapacity() !=0)
						{
							int temp = ((FoodStation) gameObject.get(j)).getCapacity();
							((Ant) gameObject.get(i)).setFoodLevel(temp);  
							((FoodStation) gameObject.get(j)).setCapacity(0);
						break;
						}
					}
				}
			}
		}
	}
	//gameTick method 
	public void gameTick()
	{
		System.out.println("Game has been ticked!!");
		timer++;
		for (int i = 0; i < gameObject.size(); i++)
		{
			if (gameObject.get(i) instanceof Ant)
			{
				if (((Ant) gameObject.get(i)).getFoodLevel() != 0)
				{
					((Ant) gameObject.get(i)).move();
					((Ant) gameObject.get(i)).setSteeringDirection(0);
					((Ant) gameObject.get(i)).fooodLevelTick();
				}
				else
				{
					System.out.println("You has already lost 1 life");
					int temp_last_flag = ((Ant) gameObject.get(i)).getLastBaseReached();
					for (int j = 0; j < gameObject.size(); j++)
					{
						if (gameObject.get(i) instanceof Flag)
						{
							if (temp_last_flag == (((Flag) gameObject.get(i)).getSequenceNumber()))
							{
								float flag_x = (((Flag) gameObject.get(i)).getX());
								float flag_y = (((Flag) gameObject.get(i)).getY());
								((Ant) gameObject.get(i)).resetAnt(flag_x,flag_y);
							}
						}
					}

				}
			}
			if (gameObject.get(i) instanceof Spider)
			{
				((Spider) gameObject.get(i)).checkBoundary();
				((Spider) gameObject.get(i)).move();
			}
		}
	}
	
	
	
	// Press 'd' to print the map
	public void printMap()
	{
		System.out.println(" The Map is printed");
		for (int i = 0; i < gameObject.size(); i++) 
		{
			System.out.println(gameObject.get(i).toString());
		}
	}
	// Press 'd' the print out the current status of Ant
	public void printCurrent()
	{
		System.out.println(" Here are the current status!!!");
		for (int i = 0; i < gameObject.size(); i++)
		{
			if (gameObject.get(i) instanceof Ant)
			{
				int life = ((Ant) gameObject.get(i)).getLife();
				int timer = getTime();
				int lastFlagReach, currentFood, currentDamage;
				lastFlagReach  = ((Ant) gameObject.get(i)).getLastBaseReached();
				currentFood    = ((Ant) gameObject.get(i)).getFoodLevel();
				currentDamage  = ((Ant) gameObject.get(i)).getDamageLevel();
				System.out.println("The life left is: " + life);
				System.out.println("The timer has elapsed is: " + timer);
				System.out.println("The last Flag reached is: " + lastFlagReach);
				System.out.println("The current food level is: " + currentFood);
				System.out.println("The current damage is: " + currentDamage);
			}
		}
	}
	// Press 'x' to exit the game 
	public int getTime() 
	{
		return this.timer;
}
	public void exit() 
	{
		System.exit(0);
	}
}