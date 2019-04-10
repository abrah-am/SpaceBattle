package com.asc.game.components.objects;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Random;

import com.asc.game.components.mediator.Mediator;
import com.asc.game.utils.GameResources.GameImages;


public class Alien extends SpaceObject{
	/** Generator of the asteroid speed. */
	private static final Random s_random = new Random();
	/** The play area where the asteroids will be placed */
	private final Mediator mediator;
	
	/** The direction the alien moves */
	private double xDirection, yDirection;
	
	/**
	 * Creates an asteroid object. The object is not visible until method draw is invoked.
         * @param mediator
	 * @param x The start position coordinate for x.
	 * @param y The start position coordinate for y.
	 */
	public Alien(Mediator mediator, int x, int y) {
		super(GameImages.ALIEN.getImage(), x, y);
		//Set a random speed for each asteroid add 1 to avoid 0.
		xDirection = s_random.nextInt(4) + 1;
		yDirection = s_random.nextInt(4) + 1;
		this.mediator = mediator;
	}
	
	@Override
	public void move(){
		x += xDirection;
		y += yDirection;
		if(x < 0 || x + image.getWidth() > mediator.getWidth()){
			xDirection *= -1.0;
		}
		if(y <0 || y + image.getHeight() > mediator.getHeight()){
			yDirection *= -1.0;
		}
	}
	
	@Override
	public void draw(Graphics2D g) {
		AffineTransform at = new AffineTransform();
		g.setTransform(at);
		g.drawImage(image, (int)x, (int)y, null);
	}
	
	@Override
	public void collidedWith(SpaceObject thatObj) {
		if(thatObj instanceof Alien){
			mediator.notifyTwoAliensCollided(this, (Alien)thatObj);
		}
	}
	/**
	 * This method inverts the direction when this alien impacts another alien.
	 * @param alien
	 */
	public void changeDirectionWith(Alien thatAlien){
		double thisXDir = xDirection, thisYDir = yDirection;
		xDirection = thatAlien.xDirection;
		yDirection = thatAlien.yDirection;
		thatAlien.xDirection = thisXDir;
		thatAlien.yDirection = thisYDir;
	}
}
