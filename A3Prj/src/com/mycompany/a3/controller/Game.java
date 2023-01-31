
package com.mycompany.a3.controller;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
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
import com.codename1.ui.util.UITimer;
import com.mycompany.a3.commands.AboutCommand;
import com.mycompany.a3.commands.AccelerateCommand;
import com.mycompany.a3.commands.BrakeCommand;
import com.mycompany.a3.commands.ExitCommand;
import com.mycompany.a3.commands.HelpCommand;
import com.mycompany.a3.commands.LeftCommand;
import com.mycompany.a3.commands.PausedCommand;
import com.mycompany.a3.commands.PositionCommand;
import com.mycompany.a3.commands.RightCommand;
import com.mycompany.a3.commands.SoundCommand;
import com.mycompany.a3.model.GameWorld;
import com.mycompany.a3.objects.GameButton;
import com.mycompany.a3.views.MapView;
import com.mycompany.a3.views.ScoreView;

/*
 * Create a Game class that extends Form and implements Runnable
 */
public class Game extends Form implements Runnable
{
	//attributes of the Game class
	private UITimer timer;
	private GameWorld gw;
	private MapView mv; 
	private ScoreView sv; 
	private Container west , east, south;
	//various command variables
	private Command accelerateC, brakeC, leftC, rightC;
	private Command  collideFoodC , collideSpiderC , tikeC;
	private Command collideFlagC , soundC, aboutC , helpC;
	private Command  exitC , positionC ,pauseC ;
	
	//various button variables
	private GameButton accelerateB , leftB , brakeB, rightB;
	private GameButton positionB , pauseB,accelerateButtonbar , aboutB, helpB, exitB;
	
	//tool bar variable 
	private Toolbar tb;
	//sound check variable 
	private CheckBox soundCheckBox;
	//other variables 
	private boolean isPaused;
	private int speed;
	
	
	//Game constructor 
	public Game() 
	{
		isPaused  = false;
		speed = 1600;
		
		mv = new MapView(); 
		sv = new ScoreView();
		gw = new GameWorld(speed);
		gw.addObserver(mv); 
		gw.addObserver(sv);

		//various command game 
		accelerateC = new AccelerateCommand(gw);
		brakeC = new BrakeCommand(gw);
		leftC = new LeftCommand(gw);
		rightC = new RightCommand(gw);
		soundC= new SoundCommand(gw);
		aboutC = new AboutCommand(gw);
		helpC = new HelpCommand(gw);
		exitC = new ExitCommand(gw);
		positionC= new PositionCommand(this);
		pauseC = new PausedCommand(this);
		
		// create Buttons
		Button_creation();
		Container_creation();
		addKeyBindings();
		
		//tool bar and design 
		tb= this.getToolbar();
		Toolbar.setGlobalToolbar(true);
		this.getToolbar().getAllStyles().setBgColor(ColorUtil.BLUE);
		this.getToolbar().getAllStyles().setBgTransparency(255);
		tb.addComponentToLeftSideMenu(accelerateButtonbar);
		tb.addComponentToLeftSideMenu(aboutB);
		tb.addComponentToLeftSideMenu(exitB);
		tb.addComponentToRightSideMenu(helpB);
		tb.addComponentToLeftSideMenu(soundCheckBox);
		
		this.setLayout(new BorderLayout());
		this.add(BorderLayout.CENTER, mv);
		this.add(BorderLayout.WEST, west);
		this.add(BorderLayout.EAST, east);
		this.add(BorderLayout.NORTH, sv);
		this.add(BorderLayout.SOUTH, south);
		
		//set the tittle of the Game 
		this.setTitle("On My Walk It Game :)");
		this.repaint();
		this.show();
		timer = new UITimer(this);
		timer.schedule(speed, true, this);
		positionB.setEnabled(false);
	}
	
	//create a button creation method of the Game 
	public void Button_creation()
	{
		//Below are buttons of the game 
		
		//accelerate 
		accelerateB = new GameButton("Accelerate");
		accelerateB.setCommand(accelerateC);
	
		//brake 
		brakeB  = new GameButton("Break");
		brakeB.setCommand(brakeC);
		
		//turn left 
		leftB  = new GameButton("Left");
		leftB.setCommand(leftC);
		
		//turn right 
		rightB  = new GameButton("Right");
		rightB.setCommand(rightC);
		
		//pause 
		pauseB  = new GameButton("Pause");
		pauseB.setCommand(pauseC);
		
		//position 
		positionB = new GameButton("Position");
		positionB.setCommand(positionC);
	
		//accelerate button bar 
		accelerateButtonbar  = new GameButton("Accelerate");
		accelerateButtonbar.setCommand(accelerateC);
		
		//about the game 
		aboutB = new GameButton("About");
		aboutB.setCommand(aboutC);
		
		//help center of the game 
		helpB  = new GameButton("Help");
		helpB.setCommand(helpC);
	
		//exit the game 
		exitB = new GameButton("Exit");
		exitB.setCommand(exitC);
		
	
		//sound check in the game 
		soundCheckBox  = new CheckBox("Sound");
		soundCheckBox.setSelected(false);
		//design and color 
		soundCheckBox.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.WHITE));
		soundCheckBox.getAllStyles().setBgColor(ColorUtil.YELLOW);
		soundCheckBox.getAllStyles().setFgColor(ColorUtil.BLACK);
		soundCheckBox.getAllStyles().setBgTransparency(255);
		soundCheckBox.getAllStyles().setPadding(2,2,2,2);
		soundCheckBox.setCommand(soundC);
		
		
	}
	//create a container_creation method
	public void Container_creation()
	{
		
		east = new Container();
		east.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		east.getAllStyles().setBgColor(ColorUtil.WHITE);
		east.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		east.getAllStyles().setBgTransparency(255);
		east.getAllStyles().setPadding(50,2,2,2);
		east.addAll(brakeB , rightB);
		
		south = new Container();
		south.setLayout(new BoxLayout(BoxLayout.X_AXIS));
		south.getAllStyles().setBgColor(ColorUtil.WHITE);
		south.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		south.getAllStyles().setBgTransparency(255);
		south.getAllStyles().setPadding(2,2,200,2);
		south.addAll(positionB , pauseB);
		
		west= new Container();
		west.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		west.getAllStyles().setBgColor(ColorUtil.WHITE);
		west.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		west.getAllStyles().setBgTransparency(255);
		west.getAllStyles().setPadding(50,2,2,2);
		west.addAll(accelerateB , leftB);
	}
	
	//create a remove key bindings method
	public void removeKeyBindings()
	{
		this.removeKeyListener('a', accelerateC);
		this.removeKeyListener('A', accelerateC);
		
		this.removeKeyListener('r', rightC);
		this.removeKeyListener('R', rightC);
		
		this.removeKeyListener('l', brakeC);
		this.removeKeyListener('L', brakeC);
		
		
		this.removeKeyListener('b', brakeC);
		this.removeKeyListener('B', brakeC);
		
	}
	
	//create an addKeyBindings method
	public void addKeyBindings() 
	{
		this.addKeyListener('a', accelerateC);
		this.addKeyListener('A', accelerateC);
		
		this.addKeyListener('l', brakeC);
		this.addKeyListener('L', brakeC);
		
		this.addKeyListener('r', rightC);
		this.addKeyListener('R', rightC);
		
		this.addKeyListener('b', brakeC);
		this.addKeyListener('B', brakeC);
		
	}
	
	public void pauseHandler() 
	{
		this.isPaused = !this.isPaused;
		if(isPaused == true)
			pause();
		else
			resume();
	}
	
	public void pause() 
	{
		gw.pause();
		timer.cancel();
		pauseB.setText("Play");
		
		removeKeyBindings();
		accelerateB.setEnabled(false);
		leftB.setEnabled(false);
		brakeB.setEnabled(false);
		rightB.setEnabled(false);
		accelerateButtonbar.setEnabled(false);
		soundCheckBox.setEnabled(false);
		positionB.setEnabled(true);
	}
	
	public void resume() 
	{
		
		gw.resume();
		timer.schedule(speed, true, this);
		pauseB.setText("Pause");
		addKeyBindings();
		accelerateB.setEnabled(true);
		leftB.setEnabled(true);
		brakeB.setEnabled(true);
		rightB.setEnabled(true);
		accelerateButtonbar.setEnabled(true);
		soundCheckBox.setEnabled(true);
		positionB.setEnabled(false);
	}
	
	public void run()
	{
		gw.tick();	
	}

	public boolean isPaused() 
	{
		return isPaused;
	}
}
