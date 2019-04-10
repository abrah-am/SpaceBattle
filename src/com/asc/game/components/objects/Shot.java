package com.asc.game.components.objects;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import com.asc.game.components.mediator.Mediator;
import com.asc.game.utils.GameUtils;
import com.asc.game.utils.GameResources.GameImages;

public class Shot extends SpaceObject {
	private static final int TORPEDO_DEFAULT_SPEED = 10;
	private final Mediator mediator;

	//The speed that the shot moves and the angle of the trajectory.
	private double xSpeed, ySpeed, currentAngle;
	
	/**
	 * Creates a shot and paints it in the indicated position.
	 * The coalition events are handled by the manager received.
	 * @param mgr The manager that will handle the events.
	 * @param x  The position in x where the Shot will be first placed.
	 * @param y The position in x where the Shot will be first placed.
	 * @param angle The inclination angle of the Shot, this depends on the Spaceship position.
	 */
	public Shot(Mediator mgr, int x, int y, Double angle){
		super(GameImages.SHOT.getImage(), x, y);
		this.mediator = mgr;
		currentAngle = angle;
		xSpeed = GameUtils.calculateMovingAngleX(currentAngle) * TORPEDO_DEFAULT_SPEED;
		ySpeed = GameUtils.calculateMovingAngleY(currentAngle) * TORPEDO_DEFAULT_SPEED;
	}
	
	@Override
	public void move(){
		x += xSpeed;
		y += ySpeed;
		if((x < 0 || x > mediator.getWidth()) || (y < 0 || y > mediator.getHeight()))
			mediator.notifyShotGone(this);
	}
	
	@Override
	public void draw(Graphics2D g) {
		AffineTransform at = new AffineTransform();
		g.setTransform(at);
		g.rotate(Math.toRadians(currentAngle), x + image.getWidth()/2, y + image.getHeight()/2);
		g.drawImage(image, (int)x, (int)y, null);
	}
	
	@Override
	public void collidedWith(SpaceObject thatObj) {
		if(thatObj instanceof Alien){
			this.mediator.notifyAlienHitByShot((Shot)this, (Alien)thatObj);
		}
	}
}
