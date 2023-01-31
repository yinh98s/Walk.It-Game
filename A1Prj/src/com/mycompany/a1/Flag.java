package com.mycompany.a1;

import com.codename1.charts.util.ColorUtil;

//create a class Flag that is the extension of Fixed class
public class Flag extends Fixed 
{	
    //attribute of the class
	private int sequenceNumber;
	
	//Instantiation of the class
    public Flag(int sequenceNumber) 
    {
    	super(ColorUtil.GREEN,10);
    	this.sequenceNumber = sequenceNumber;
    }
   
    //below are the setter method
    public void setColor(int color)
    {
    	System.out.println("Cannot be changed");
    }
    
    public void setSequenceNumber(int sequenceNumber) 
    {
    	this.sequenceNumber = sequenceNumber;
    }
    public void setSize(int size) 
    {}
    
    //below is a getter method
    public int getSequenceNumber()
    {
        return this.sequenceNumber;
    }
    
   @Override
   public String toString() 
   {
	String string;
	double x,y;
	x =  Math.round(this.getX()* 10.0)/ 10.0 ;
	y =  Math.round(this.getY()*10.0)/10.0;
	
	string = "Flag: loc= " + x +","+ y+
   			"  color= " + this.getColortoString() + 
   			"  size= " + this.getSize()+
   			"  seqNumber= " + this.getSequenceNumber();
	return string;
   
   }
}