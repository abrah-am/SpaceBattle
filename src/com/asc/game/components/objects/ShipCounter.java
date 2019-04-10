package com.asc.game.components.objects;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import com.asc.game.utils.GameResources.GameImages;

/**
 * A representation of the ship.
 * @author Abraham Soto
 *
 */
public class ShipCounter{
	private int x, y;
	private boolean alive;
	
	public ShipCounter(int x, int y) {
		this.x = x;
		this.y = y;
		this.alive = true;
	}
	/**
	 * Sets the status of the ship. 
	 * Alive shows a red ship. Dead shows a gray ship.
	 * @param alive The state of the ship.
	 */
	public void setAlive(boolean alive){
		this.alive = alive;
	}
	/**
	 * @return <b>true</b> if the ship is alive, <b>false</b> otherwise.
	 */
	public boolean isAlive(){
		return this.alive;
	}
	/**
	 * Draws the ship depending on the state.
	 * @param g
	 */
	public void draw(Graphics2D g){
		AffineTransform at = new AffineTransform();
		g.setTransform(at);
		if(alive)
			g.drawImage(GameImages.SHIPALIVE.getImage(), (int)x, (int)y, null);
		else
			g.drawImage(GameImages.SHIPDEAD.getImage(), (int)x, (int)y, null);
	}
}
