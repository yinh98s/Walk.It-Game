/*
 * The "AboutCommand" will describe the description of the game 
 */
package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.Dialog;
import com.mycompany.a3.model.GameWorld;


//create an "AboutCommand" that extends " Command"
public class AboutCommand extends Command
{
	private GameWorld gameWorld;
	
	//constructor of the "AboutCommand" class
	public AboutCommand(GameWorld gw)
	{
		super("About");
		this.gameWorld = gw;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getKeyEvent() != -1)
		{
			Dialog.show("About"," WalkIt Game\n" + 
					"A3 GUI Project: Yingying Sroy\n" + "CSC 133 \n" + 
					"FALL 2022\n" + 
					"I hope you enjoy my little creative Game!", "OK", null);
		}
	}
}
