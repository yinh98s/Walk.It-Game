package com.mycompany.a3.commands;

/*
 * The "LeftCommand" class will invoke the left button of the game object
 */
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.model.GameWorld;


public class LeftCommand extends Command
{
	private GameWorld gameWorld;
	//constructor of the "LeftCommand" class
	public LeftCommand(GameWorld gw)
	{
		super("Left");
		this.gameWorld = gw;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getKeyEvent() !=1)
		{
			gameWorld.left();
		}
	}
}
