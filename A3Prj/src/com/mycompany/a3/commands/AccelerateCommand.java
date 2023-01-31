package com.mycompany.a3.commands;

/*
 * The "AccelerateCommand" will invoke the speed of the GAME object
 */

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.model.GameWorld;

//create an " AccelerateCommand" that extends the "Command"
public class AccelerateCommand extends Command
{
	private GameWorld gameWorld;
	
	//constructor of the "AccerlerateCommand" 
	public AccelerateCommand(GameWorld gw)
	{
		super("Accelerate");
		this.gameWorld = gw;
		
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getKeyEvent() !=-1)
		{
			gameWorld.accelerate();
		}
	}
}
