package com.mycompany.a2.views;

import java.util.Observer;
import java.util.Observable;
import com.mycompany.a2.model.GameWorld;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.plaf.Border;


//create a " mapView" class that extends "Containers" and implement Obersever
public class MapView extends Container implements Observer {

	//constructor
	public MapView() 
	{
		this.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.rgb(255, 0, 0)));
		this.getAllStyles().setBgColor(ColorUtil.WHITE);
		this.getAllStyles().setBgTransparency(255);
		
	}
		
	//update() method to update the map every time the game invokes
	public void update(Observable o, Object arg) 
	{
		System.out.println("*************************** MAP VIEW OF GAME WORLD ************************\n");
		((GameWorld)arg).map();
		System.out.println("\n************************************************************************************");
		
	}
}