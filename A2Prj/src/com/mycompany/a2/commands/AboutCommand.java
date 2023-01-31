package com.mycompany.a2.commands;


import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.mycompany.a2.model.GameWorld;

//create an "AboutCommand" class
public class AboutCommand extends Command 
{
	//attribute of the class
	private GameWorld gw;
	//constructor
	public AboutCommand(GameWorld gw) 
	{
		super("About");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent event)
	{
		if (event.getKeyEvent() != -1) 
		{
			Dialog.show("About","WalkIt Game\n" + 
						"A2 GUI Project: Yingying Sroy\n" + "CSC 133 \n" + 
						"FALL 2022\n" + 
						"I hope you enjoy my little creative Game!", "OK", null);
		}
	}

}
