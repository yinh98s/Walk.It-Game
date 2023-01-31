package com.mycompany.a3.commands;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.controller.Game;

/*
 * The "PausedCommand" will invoke the paused button to stop the game object 
 */
public class PausedCommand extends Command
{
	//attributes of the PausedCommand 
	private Game gameWorld;
	//constructor of the command 
	public PausedCommand(Game gw)
	{
		super("Pause");
		this.gameWorld = gw;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getKeyEvent() !=-1)
		{
			gameWorld.pauseHandler();
		}
	}
}
