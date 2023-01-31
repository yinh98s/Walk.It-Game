 package com.mycompany.a3.views;
 
import java.util.Observer;
import java.util.Observable;

import com.mycompany.a3.model.GameWorld;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.plaf.Border;


//create a "ScoreView" class that extends "Container" and implements " Observer"
public class ScoreView extends Container implements Observer 
{
	//attributes of the ScoreView
	private  Label time,lives,flag;
	private Label foodLevel, healthLevel, sound;
	private Label timeValue, livesValue, flagValue; 
	private Label foodLevelValue, healthLevelVaue, soundValue;
	
	//constructor of the class
	public ScoreView() 
	{	//set some design for the score view 
		this.setLayout(new BoxLayout(BoxLayout.X_AXIS));
		this.getAllStyles().setBgColor(ColorUtil.rgb(153,204,255));
		this.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.WHITE));
		this.getAllStyles().setBgTransparency(255);
		
		// Lives Labels
		lives = new Label("Lives Remaining: ");
		lives.getAllStyles().setFgColor(ColorUtil.WHITE);
		livesValue = new Label("");
		livesValue.getAllStyles().setFgColor(ColorUtil.WHITE);
		livesValue.getAllStyles().setPaddingRight(5);

		// Time Labels
		time = new Label("Time: ");
		time.getAllStyles().setFgColor(ColorUtil.WHITE);
		timeValue = new Label("");
		timeValue.getAllStyles().setFgColor(ColorUtil.WHITE);
		timeValue.getAllStyles().setPaddingRight(5);
		
		// FoodLevel Labels
		foodLevel = new Label("Food Level: ");
		foodLevel.getAllStyles().setFgColor(ColorUtil.WHITE);
		foodLevelValue = new Label("");
		foodLevelValue.getAllStyles().setFgColor(ColorUtil.WHITE);
		foodLevelValue.getAllStyles().setPaddingRight(5);


		// Flag Labels
		flag = new Label("Last Flag Reached: ");
		flag.getAllStyles().setFgColor(ColorUtil.WHITE);
		flagValue = new Label("");
		flagValue.getAllStyles().setFgColor(ColorUtil.WHITE);
		flagValue.getAllStyles().setPaddingRight(5);

		
		// HealthLevel Labels
		healthLevel = new Label("Health Level: ");
		healthLevel.getAllStyles().setFgColor(ColorUtil.WHITE);
		healthLevelVaue = new Label("");
		healthLevelVaue.getAllStyles().setFgColor(ColorUtil.WHITE);
		healthLevelVaue.getAllStyles().setPaddingRight(5);

		// Sound Labels
		sound = new Label("Sound: ");
		sound.getAllStyles().setFgColor(ColorUtil.WHITE);
		soundValue = new Label("");
		soundValue.getAllStyles().setFgColor(ColorUtil.WHITE);
		soundValue.getAllStyles().setPaddingRight(5);
		//add all attributes to the Score view 
		this.addAll(time,timeValue,lives,livesValue,flag,flagValue,foodLevel,foodLevelValue,
				healthLevel,healthLevelVaue,sound,soundValue);
	}

	public void update(Observable o, Object arg)
	{
		
		if(arg == null)
			return;
		timeValue.setText(Integer.toString(((GameWorld)arg).getTime()));
		livesValue.setText(Integer.toString(((GameWorld)arg).getLives()));
		flagValue.setText (Integer.toString(((GameWorld)arg).getLastFlagReached()));
		foodLevelValue.setText (Integer.toString(((GameWorld)arg).getFoodLevel()));
		healthLevelVaue.setText (Integer.toString(((GameWorld)arg).getHealthLevel()));
		
		//determine whether the sound is off/on
		if(((GameWorld)arg).isSound())
		{
			soundValue.setText("ON");
		}
		else
		{
			soundValue.setText("OFF");
		}
		repaint();
	}
}