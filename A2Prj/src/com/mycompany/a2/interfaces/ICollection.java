package com.mycompany.a2.interfaces;

public interface ICollection 
{

	public void add(Object o);
	public IIterator getIterator();
	public int size();
}