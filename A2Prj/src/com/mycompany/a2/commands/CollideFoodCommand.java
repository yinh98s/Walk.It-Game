package com.mycompany.a2.commands;

import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.Command;
import com.mycompany.a2.model.GameWorld;

//create a "CollideFoodCommand" class
public class CollideFoodCommand extends Command{

	private GameWorld gw;
	public CollideFoodCommand(GameWorld gw) 
	{
		super("Collision With Food Stations");
		this.gw = gw;

	}
	
	public void actionPerformed(ActionEvent event)
	{
		if (event.getKeyEvent() != -1) {
			gw.collisionWithFoodStation();
		}
	}
}