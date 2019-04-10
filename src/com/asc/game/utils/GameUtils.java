package com.asc.game.utils;



import java.util.Random;

import com.asc.game.components.objects.Alien;
import com.asc.game.components.mediator.Mediator;
import com.asc.game.utils.GameResources.GameImages;

public class GameUtils {
	private static final int DEFAULT_DISTANCE_FROM_SHIP = 250;
	private static final Random _random = new Random();
	
	/**
	 * Calculates the moving angle for Y axis
	 * @param yAngle The moving angle of Y axis.
	 * @return The moving angle for Y
	 */
	public static final Double calculateMovingAngleY(double yAngle){
		return Math.sin(yAngle * Math.PI  / 180);
	}
	/**
	 * Calculates the moving angle for X axis
	 * @param yMoveAngle The moving angle of X axis.
	 * @return The moving angle for X
	 */
	public static final Double calculateMovingAngleX(double xAngle){
		return Math.cos(xAngle * Math.PI  / 180);
	}
	/**
	 * Factory method that creates an alien. Aliens must be creates with a certain distance from the ship.
	 * @param width The width of the surface where the alien will be created.
	 * @param height The height of the surface where the alien will be created.
	 * @return A new instance of {@link Alien}.
	 */
	public static final Alien createAlien(Mediator mgr, int width, int height){
		int x = _random.nextInt(width - GameImages.ALIEN.getImage().getWidth());
		int y = _random.nextInt(height - GameImages.ALIEN.getImage().getHeight());
		int centerX = mgr.getWidth()/2;
	    //Place aliens certain distance from the center to avoid crash the ship when starting.
		if(x > (centerX - DEFAULT_DISTANCE_FROM_SHIP) && x <= centerX){
			x = centerX - DEFAULT_DISTANCE_FROM_SHIP;
		}
		if(x < (centerX + DEFAULT_DISTANCE_FROM_SHIP) && x >= centerX){
			x = centerX + DEFAULT_DISTANCE_FROM_SHIP;
		}
		return new Alien(mgr, x, y);
	}
}
