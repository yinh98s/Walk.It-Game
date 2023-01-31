package com.mycompany.a2.commands;

import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.Command;
import com.mycompany.a2.model.GameWorld;
//create a "CollideFlagCommand" 

public class CollideFlagCommand extends Command 
{
	//attribute
	private GameWorld gw;
	public CollideFlagCommand(GameWorld gw) 
	{
		super("Collisoin With Flag");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent event)
	{
		
		if (event.getKeyEvent() != -1) {
		}
		
	}

}