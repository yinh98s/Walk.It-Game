package com.mycompany.a3.commands;

/*
 * The "BrakeCommand" will invoke the Brake of the game object 
 */

import  com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.model.GameWorld;

//create a "BrakeCommand" class that extends "Command"
public class BrakeCommand extends Command
{
	private GameWorld gameWorld;
	
	//constructor of the "BrakeCommand" class
	public BrakeCommand(GameWorld gw)
	{
		super("Brake");
		this.gameWorld = gw;
	
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getKeyEvent() !=-1)
		{
			gameWorld.brake();
		}
	}
}
