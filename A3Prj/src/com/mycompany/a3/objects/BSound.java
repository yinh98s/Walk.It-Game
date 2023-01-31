package com.mycompany.a3.objects;

import java.io.InputStream;
import com.codename1.ui.Display;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;

/*
 * create a BSound class that implements runnable interface 
 */
public class BSound implements Runnable
{
	//attributes of the BSound 
	 private Media media;
	 //constructor of the BSound class
	 public BSound(String soundName) 
	 {
	   try {
		    InputStream inputStream = Display.getInstance().getResourceAsStream(getClass(), "/" + soundName); 
		    media =MediaManager.createMedia(inputStream, "audio/wav", this);
		    
	    } catch(Exception ex)
	    {
	    	ex.printStackTrace();
	    }
	 }
	 
	
 	public void pause()
 	{ 
 		if(media == null)
 		{
			 return;
 		}
 		else
 		{
 		media.pause();
 		}
 	} 
 	
 	 public void play()
	 {
		 if(media == null)
			 return;
		 media.setTime(0);
		 media.setVolume(10);
		 media.play();
	 }
 	 
 	public void run()
 	{
 		if(media == null)
			 return;
 		
 		media.setTime(0);
 		media.play();
 	}
}
