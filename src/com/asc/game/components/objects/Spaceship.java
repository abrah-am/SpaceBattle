package com.asc.game.components.objects;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import com.asc.game.components.mediator.Mediator;
import com.asc.game.utils.GameUtils;
import com.asc.game.utils.GameResources.GameImages;

/**
 * This class is the representation of the Spaceship.
 * This is a subclass of SpaceObject, and implements methods move(), draw(), collidedWith()
 * @author Abraham Soto
 *
 */
public class Spaceship extends SpaceObject{
	/** The increment or decrement ratio of the speed */
	private static final double VELOCITY_INCREMENT = 0.1;
	/** The increment or decrement ratio of the angle */
	private static final int ANGLE_INCREMENT = 10;
	
	/** The area where the spaceship will be placed */
	private final Mediator mediator;
	
	/** The velocity and angle  */
	private int velocityLevel, currentAngle;
	
	/** The speed for x and y */
	private double xSpeed, ySpeed;
	
	public Spaceship(Mediator mediator){
            super(GameImages.SHIP.getImage(), mediator.getWidth()/2, mediator.getHeight()/2);
            this.mediator = mediator;
	}
	
        /**
         * Increases the angle, it actually rotates the ship downwards.
         */
        public final void decreaseAngle(){
            currentAngle = (currentAngle >= 365) ? 0 : currentAngle + ANGLE_INCREMENT;
            adjustVelocity();
        }
        /**
         * Increases the angle, it actually rotates the ship upwards.
         */
        public final void increaseAngle(){
            currentAngle = (currentAngle < 0) ? 365 : currentAngle - ANGLE_INCREMENT; 
            adjustVelocity();
        }
	/**
	 * Increases the speed of the ship.
	 */
	public void increaseVelocity(){
		velocityLevel++;
		adjustVelocity();
	}
	/**
	 * Increases the speed of the ship.
	 */
	public void decreaseVelocity(){
		if(velocityLevel > 0){
			velocityLevel--;
			adjustVelocity();
		}
	}
	@Override
	public final void move(){
		x += xSpeed;
	    if(x < 0) 
			x = mediator.getWidth();
		if(x > mediator.getWidth())  
			x = 0;

	    y += ySpeed;
		if(y < 0)
			y = mediator.getHeight();
		if(y > mediator.getHeight())
			y = 0;
	}
	
	@Override
	public void draw(Graphics2D g) {
		AffineTransform at = new AffineTransform();
		g.setTransform(at);
		g.rotate(Math.toRadians(currentAngle), x + image.getWidth()/2, y + image.getHeight()/2);
		g.drawImage(image, (int)x, (int)y, null);
	}
	
	@Override
	public void collidedWith(SpaceObject that) {
		if(that instanceof Alien){
			mediator.notifyShipHitByAlien(this, (Alien)that);
		}
	}
	/**
	 * Sends the ship to the middle of the screen.
	 */
	public void restartPosition(){
		currentAngle = velocityLevel = 0;
		xSpeed = ySpeed = 0;
		x = mediator.getWidth()/2;
		y = mediator.getHeight()/2;
	}
	/**
	 * This method must be invoked every time the spaceship changes its direction or speed.
	 */
	private void adjustVelocity(){
		//restart the values.
		xSpeed = ySpeed = 0;
		for(int i=0; i < this.velocityLevel; i++){
			xSpeed += GameUtils.calculateMovingAngleX((double)currentAngle) * VELOCITY_INCREMENT;
			ySpeed += GameUtils.calculateMovingAngleY((double)currentAngle) * VELOCITY_INCREMENT;
		}
	}
	/**
	 * @return The current angle of the trajectory of the ship.
	 */
	public final int getCurrentAngle(){
		return this.currentAngle;
	}
	
	/**
	 * @return The x position of the nose of the ship.
	 */
	public final int getXNose(){
		return (int)(x + image.getWidth()/2 + Math.cos(currentAngle));
	}
	
	/**
	 * @return The y position of the nose of the ship.
	 */
	public final int getYNose(){
		return (int)(y + image.getHeight()/2 + Math.sin(currentAngle));
	}
}
