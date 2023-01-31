package com.mycompany.a1;

import java.lang.String;
import com.codename1.ui.TextField;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionListener; 
import com.codename1.ui.events.ActionEvent; 
import com.codename1.ui.Form;

// create a "Game" class that extends "Form
public class Game extends Form 
{
	private GameWorld gameworld;
	private char optionKey;
	
	//constructor of the "Game" class
	public Game() 
	{
		gameworld = new GameWorld();
		gameworld.init();
		play();
	}	
	private void setKey(char key)
	{
		this.optionKey=key;
	}
	private char getKey() 
	{
		return this.optionKey;
	}

	//Play() method will determine how the game is playing 
	private void play() 
	{
		Label myLabel=new Label("Please enter your command : "); 
		this.addComponent(myLabel);
		final TextField textField = new TextField(); 
		this.addComponent(textField); 
		this.show();
		
		textField.addActionListener(new ActionListener()
		{ 
			public void actionPerformed(ActionEvent evt) 
			{
			String sCommand = textField.getText().toString(); 
			textField.clear();
			if(sCommand.length() != 0)
				switch (sCommand.charAt(0)) 
				{ 

					case 'a':
						gameworld.increaseSpeed();
						break;
					case 'b':
						gameworld.decreaseSpeed();
						break;
					case 'l':
						gameworld.leftTurnAnt();
						break;
					case 'r':
						gameworld.rightTurnAnt();
						break;
					case 'c':
						gameworld.collsionAnt();
						break;
					// Case 1 -9
					case '1':
						gameworld.collisionFlag(1);
						break;
					case '2':
						gameworld.collisionFlag(2);
						break;
					case '3':
						gameworld.collisionFlag(3);
						break;
					case '4':
						gameworld.collisionFlag(4);
						break;
					case '5':
						gameworld.collisionFlag(5);
						break;
					case '6':
						gameworld.collisionFlag(6);
						break;
					case '7':
						gameworld.collisionFlag(7);
						break;
					case '8':
						gameworld.collisionFlag(8);
						break;
					case '9':
						gameworld.collisionFlag(9);
						break;
					case 'e':
						gameworld.collisionFoodStation();
						break;
					case 'g':
						gameworld.collisionSpider();
						break;
					case 't':
						gameworld.gameTick();
						break;
					case 'd':
						gameworld.printCurrent();;
						break;
					case 'm':
						gameworld.printMap();
						break;
					case 'x':
						setKey(sCommand.charAt(0));
						System.out.println("Are you sure to exit game?");
						break;
					 case 'N':
					 case 'n':
						if(getKey() == 'x') 
							System.out.println("Game continue");
						else {
							System.out.println("Invalid input");
						}
							break;
					 case 'Y':
					 case 'y':
						 if(getKey() == 'x')
							 gameworld.exit();
						 else {
							 System.out.println("Invalid input");
						 }
						 break;
					default:
						System.out.println("\nYour Input is invalid.Please enter valid command!!!\n");
						break;
				}
				}
			 
			}
		);
		
	}
	}
	