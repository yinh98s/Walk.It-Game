package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.model.GameWorld;

//create a "BrakeCommand" class
public class BrakeCommand extends Command
{
	//attribute
	private GameWorld gw;
	//constructor
	public BrakeCommand(GameWorld gw) 
	{
		super("Brake");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent event)
	{
		if (event.getKeyEvent() != -1) {
			gw.brake();
		}
	}


}