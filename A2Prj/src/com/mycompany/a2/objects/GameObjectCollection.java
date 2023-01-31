package com.mycompany.a2.objects;


import com.mycompany.a2.interfaces.ICollection;
import com.mycompany.a2.interfaces.IIterator;
import java.util.ArrayList;

//create a "GameObjectCollection" that implements " ICollection"
public class GameObjectCollection implements ICollection{

	//create an arrayList 
	private ArrayList<GameObject> gameObjects;
	
	//constructor
	public GameObjectCollection() 
	{
		//instantiation 
		gameObjects = new ArrayList<GameObject>();
	}
	
	public GameObject get(int index) 
	{
		if(index < 0 || index >= gameObjects.size())
		{
			return null;
		}
		else
		{
		return gameObjects.get(index);
		}
	}
	
	//add() method of the game object
	public void add(Object object) 
	{
		
		gameObjects.add((GameObject) object);
	}
	//size() method of the game object
	public int size() 
	{
		return gameObjects.size();
	}
	
	
	public IIterator getIterator()
	{
		
		return new GameObjectCollectionIterator();
	}
	
	private class GameObjectCollectionIterator implements IIterator
	{
		private int index;
		
		public GameObjectCollectionIterator()
		{
			index = -1;	
		}
		
		public boolean hasNext()
		{
			if(gameObjects.size() <= 0 || index == gameObjects.size() - 1)
			{
				return false;
			}
			else
			{
			return true;
			}
			
		}

		public Object getNext() 
		{
			return(gameObjects.get(++index));
		}
		
		public void remove()
		{
			if(!hasNext())
				return ;
			gameObjects.remove(index--);
		}			
	}

}