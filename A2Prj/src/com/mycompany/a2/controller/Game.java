package com.mycompany.a2.controller;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.mycompany.a2.commands.AboutCommand;
import com.mycompany.a2.commands.AccelerateCommand;
import com.mycompany.a2.commands.BrakeCommand;
import com.mycompany.a2.commands.CollideFlagCommand;
import com.mycompany.a2.commands.CollideFoodCommand;
import com.mycompany.a2.commands.CollideSpiderCommand;
import com.mycompany.a2.commands.ExitCommand;
import com.mycompany.a2.commands.HelpCommand;
import com.mycompany.a2.commands.LeftCommand;
import com.mycompany.a2.commands.RightCommand;
import com.mycompany.a2.commands.SoundCommand;
import com.mycompany.a2.commands.TickCommand;
import com.mycompany.a2.model.GameWorld;
import com.mycompany.a2.objects.GameButton;
import com.mycompany.a2.views.MapView;
import com.mycompany.a2.views.ScoreView;

//create a "Game class" that extends "Form" class
public class Game extends Form 
{
	//attributes of the Game class
	private GameWorld gw;
	private MapView mv; 
	private ScoreView sv; 
	//containers 
	private Container west , east , south;
	//various command of the Game 
	private Command acceC, brakeC, leftC, rightC, collideFoodC , collideSpiderC , tickC,
			collideFlagC, soundC, aboutC , helpC,exitC;
	//various button of the Game
	private GameButton acceB , leftB , brakeB , rightB ,collideFoodB , collideSpiderB, tickB , collideFlagB,
	accelerateButtonbar , aboutB , helpB, exitB;
	//toolbar of the Game
	private Toolbar toolBar;
	//sound check of the Game "OFF/ON"
	private CheckBox soundCheckBox;
	
	//constructor
	public Game()
	
	{	sv = new ScoreView();
		mv = new MapView(); 
		gw = new GameWorld(); 
		gw.addObserver(mv); 
		gw.addObserver(sv); 
		this.show();
		gw.init(); 
		
		//commands of the Game 
		acceC = new AccelerateCommand(gw);
		brakeC = new BrakeCommand(gw);
		rightC= new RightCommand(gw);
		leftC = new LeftCommand(gw);
		collideFoodC = new CollideFoodCommand(gw);
		collideSpiderC = new CollideSpiderCommand(gw);
		collideFlagC= new CollideFlagCommand(gw);
		tickC = new TickCommand(gw);
		soundC = new SoundCommand(gw);
		aboutC = new AboutCommand(gw);
		helpC = new HelpCommand(gw);
		exitC = new ExitCommand(gw);
		
		//Buttons
		createButtons();
		addKeyBindings();
		createContainers();
		
		toolBar = this.getToolbar();
		Toolbar.setGlobalToolbar(true);
		this.getToolbar().getAllStyles().setBgColor(ColorUtil.BLACK);
		this.getToolbar().getAllStyles().setBgTransparency(255);
		toolBar.addComponentToLeftSideMenu(accelerateButtonbar);
		toolBar.addComponentToLeftSideMenu(soundCheckBox);
		toolBar.addComponentToLeftSideMenu(aboutB);
		toolBar.addComponentToLeftSideMenu(exitB);
		toolBar.addComponentToRightSideMenu(helpB);
		this.setLayout(new BorderLayout());
		this.add(BorderLayout.NORTH, sv);
		this.add(BorderLayout.SOUTH, south);
		this.add(BorderLayout.CENTER, mv);
		this.add(BorderLayout.WEST, west);
		this.add(BorderLayout.EAST, east);
		this.setTitle("On My Walkit Game :) ");
		this.repaint();
		this.show();
	}
	
	public void createButtons() 
	{
		acceB  = new GameButton("Accelerate");
		acceB.setCommand(acceC);

		leftB  = new GameButton("Left");
		leftB.setCommand(leftC);

		rightB  = new GameButton("Right");
		rightB.setCommand(rightC);
		
		brakeB  = new GameButton("Break");
		brakeB.setCommand(brakeC);
		
		accelerateButtonbar  = new GameButton("Accelerate");
		accelerateButtonbar.setCommand(acceC);

		collideFoodB = new GameButton("Collide with Food Stations");
		collideFoodB.setCommand(collideFoodC);

		collideSpiderB  = new GameButton("Collide with Spider");
		collideSpiderB.setCommand(collideSpiderC);

		tickB  = new GameButton("Tick");
		tickB.setCommand(tickC);

		collideFlagB  = new GameButton("Collide with Flag");
		collideFlagB.setCommand(collideFlagC);

		aboutB  = new GameButton("About");
		aboutB.setCommand(aboutC);
		
		helpB = new GameButton("Help");
		helpB.setCommand(helpC);
		
		exitB  = new GameButton("Exit");
		exitB.setCommand(exitC);
		
		soundCheckBox  = new CheckBox("Sound");
		soundCheckBox.setSelected(true);
		soundCheckBox.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.BLACK));
		soundCheckBox.getAllStyles().setBgTransparency(255);
		soundCheckBox.getAllStyles().setBgColor(ColorUtil.GRAY);
		soundCheckBox.getAllStyles().setFgColor(ColorUtil.WHITE);
		soundCheckBox.getAllStyles().setPadding(2,2,2,2);
		soundCheckBox.setCommand(soundC);

	}
	
	public void addKeyBindings() 
	{
		this.addKeyListener('a', acceC);
		this.addKeyListener('A', acceC);
		
		this.addKeyListener('b', brakeC);
		this.addKeyListener('B', brakeC);

		
		this.addKeyListener('l', brakeC);
		this.addKeyListener('L', brakeC);
		
		this.addKeyListener('r', rightC);
		this.addKeyListener('R', rightC);
		
		this.addKeyListener('f', collideFoodC);
		this.addKeyListener('F', collideFoodC);
		
		this.addKeyListener('g', collideSpiderC);
		this.addKeyListener('G', collideSpiderC);
		
		this.addKeyListener('t', tickC);
		this.addKeyListener('T', tickC);
	}
	
	public void createContainers() 
	{

		east = new Container();
		east.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		east.getAllStyles().setBgColor(ColorUtil.WHITE);
		east.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		east.getAllStyles().setBgTransparency(255);
		east.getAllStyles().setPadding(50,2,2,2);
		east.addAll(brakeB , rightB);
		
		west = new Container();
		west.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		west.getAllStyles().setBgColor(ColorUtil.WHITE);
		west.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		west.getAllStyles().setBgTransparency(255);
		west.getAllStyles().setPadding(50,2,2,2);
		west.addAll(acceB , leftB);

		south = new Container();
		south.setLayout(new BoxLayout(BoxLayout.X_AXIS));
		south.getAllStyles().setBgColor(ColorUtil.WHITE);
		south.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		south.getAllStyles().setBgTransparency(255);
		south.getAllStyles().setPadding(2,2,50,2);
		south.addAll(collideSpiderB, collideFlagB, collideFoodB, tickB);
		
	}

	private void play()
	{

	}
	

}