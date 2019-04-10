package com.asc.game.components.objects;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * ADAPTER PATTERN: This class is an adapter for the class List.
 * This class contains methods that allow to interact with the space.
 * @author Abraham Soto
 *
 */
public class Space implements Iterable<SpaceObject>{
	/** This List contains all objects existing in space.	 */
	private final List<SpaceObject> objects = new CopyOnWriteArrayList<>();
	
	/**
	 * Removes all objects from the list.
	 */
	public void clear(){
		objects.clear();
	}
	/**
	 * Removes the instance from the space. 
	 * @param obj The {@link SpaceObject} to remove.
	 */
	public void remove(SpaceObject obj){
		objects.remove(obj);
	}
	/**
	 * Adds the object to the space.
	 * @param obj The {@link SpaceObject} to add.
	 */
	public void add(SpaceObject obj){
		objects.add(obj);
	}
	/**
	 * @return The instance of the ship.
	 */
	public Spaceship findShip(){
		for(SpaceObject obj : objects) 
			if(obj instanceof Spaceship) return (Spaceship)obj;
		return null;
	}
	/**
	 * Returns the count of the objects of the given class in space.
	 * @param theClass
	 * @return The number of objects of that instance.
	 */
	public <S extends SpaceObject> int count(Class<S> theClass){
		int count = 0;
		for(SpaceObject obj : objects)
			if(obj.getClass().equals(theClass)) count++;
		return count;
			
	}
	/**
	 * Removes from the space all instances of a given class.
	 * @param theClass
	 */
	public <S extends SpaceObject> void removeAll(Class<S> theClass){
		for(SpaceObject obj : objects)
			if(obj.getClass().equals(theClass)) 
				objects.remove(obj);
	}
	@Override
	public Iterator<SpaceObject> iterator() {
		return objects.iterator();
	}
}
