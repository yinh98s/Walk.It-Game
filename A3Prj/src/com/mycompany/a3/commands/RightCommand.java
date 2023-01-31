package com.mycompany.a3.commands;

/*
 *  The "RightCommand" class will invoke the right command of the game object
 */
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.model.GameWorld;

public class RightCommand extends Command
{

	private GameWorld gameWorld;
	//constructor  of the "RightCommand"
	public RightCommand(GameWorld gw) 
	{
		super("Right");
		this.gameWorld = gw;
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getKeyEvent() != -1) 
		{
			gameWorld.right();
		}
	}
}