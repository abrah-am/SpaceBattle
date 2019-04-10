package com.asc.game.components.objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * Super class of all space objects.
 * @author Abraham Soto
 *
 */
public abstract class SpaceObject {
	protected final BufferedImage image;
	protected double x;
	protected double y;
	
	/**
	 * Default constructor.
	 * @param image The image for the object.
	 * @param position The start position of the object in space.
	 */
	public SpaceObject(BufferedImage image, int x, int y){
		this.image = image;
		this.x = x;
		this.y = y;
	}
	/**
	 * This method will be implemented by each subclass to move 
	 * the object according to its rules.
	 */
	public abstract void move();

	/**
	 * Draws the object in the g plane passed.
	 * @param g The g that will be used to draw the image.
	 */
	public abstract void draw(Graphics2D g);

	/**
	 * This method handles what happens when this object intersects another one.
	 * @param thatObj
	 */
	public abstract void collidedWith(SpaceObject thatObj);

	/**
	 * @return The rectangle that surrenders the image to validate when this object has crashed another one.
	 */
	public final Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, image.getWidth(), image.getHeight());
	}
	
	/**
	 * @param anotherObject Another object in space.
	 * @return Returns <b>true</b> if this object intersects the other object. <b>false</b> when they are not at the same point.
	 */
	public final boolean intersectsWith(SpaceObject thatObj){
		return this != thatObj && this.getBounds().intersects(thatObj.getBounds());
	}
}
