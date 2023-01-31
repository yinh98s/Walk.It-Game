package com.mycompany.a3.commands;

/*
 * The "SoundCommand" will invoke the off/on sound of the game 
 */
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.model.GameWorld;

public class SoundCommand extends Command 
{
	private GameWorld gameWorld;
	//constructor of the SoundCommand
	public SoundCommand(GameWorld gw) 
	{
		super("Sound");
		this.gameWorld = gw;
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getKeyEvent() != -1)
		{
			gameWorld.changeSound();
		}
	}

}