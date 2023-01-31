package com.mycompany.a3.commands;

/*
 * The "ExitCommand" will invoke exit command in the game 
 */

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.model.GameWorld;

//create an "ExitCommand" class that extends Command
public class ExitCommand extends Command
{
	private GameWorld gameWorld;
	//constructor of the "ExitCommand"
	public ExitCommand(GameWorld gw)
	{
		super("Exit");
		this.gameWorld = gw;
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getKeyEvent()!=-1)
		{
			gameWorld.exit();
		}
	}
}
