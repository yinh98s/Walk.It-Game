package com.mycompany.a3.commands;

/*
 * The "HelpCommand" will invoke to help command to show the guideline of the game 
 */

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.model.GameWorld;
//create a "HelpCommand" class that extends the command
public class HelpCommand extends Command
{
	private GameWorld gameWorld;
	//constructor of the "HelpCommand"
	public HelpCommand(GameWorld gw)
	{
		super("Help");
		this.gameWorld = gw;
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getKeyEvent() !=-1)
		{
			Dialog.show("Help","Press 'a' to Accelerate\n"
					+ "Press 'b' for Break\n"
					+ "Press 'l' to move Left\n"
					+ "Press 'r' to move Right\n"
					+ "Press 'f' to collide with Food Stations\n"
					+ "Press 'g' to collide with Spider\n"
					+ "Press 't' to Tick", "OK", null);
		}
	}
}
