
package com.mycompany.a3.objects;

import com.codename1.ui.plaf.Border;
import com.codename1.ui.Button;
import com.codename1.charts.util.ColorUtil;


//create a " GameButton" class that extends Button object 
public class GameButton extends Button
{
	//constructor of the GameButton class 
	public GameButton()
	{
		
		
		this.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.WHITE));
		this.getAllStyles().setBgTransparency(255);
		this.getAllStyles().setBgColor(ColorUtil.BLUE);
		this.getAllStyles().setFgColor(ColorUtil.WHITE);
		this.getAllStyles().setPadding(5,5,5,5);
		
	}

	//constructor of the GameButton class
	public GameButton(String title)
	{
		super(title);
		this.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.WHITE));
		this.getAllStyles().setBgTransparency(255);
		this.getAllStyles().setBgColor(ColorUtil.BLUE);
		this.getAllStyles().setFgColor(ColorUtil.WHITE);
		this.getAllStyles().setPadding(5,5,5,5);
	}
	
	
}