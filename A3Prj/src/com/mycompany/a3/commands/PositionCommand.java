package com.mycompany.a3.commands;

/*
 * The "PositionCommand" will invoke the position of the game object
 */
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.controller.Game;
import com.mycompany.a3.model.GameWorld;

public class PositionCommand extends Command
{

	private Game gameWorld;
	
	//constructor of the "PositionCommand" in the game
	public PositionCommand(Game gw) 
	{
		super("Position");
		this.gameWorld = gw;
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getKeyEvent() != -1 && gameWorld.isPaused())
		{
			
		}
	}
}