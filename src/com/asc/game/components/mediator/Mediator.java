package com.asc.game.components.mediator;

import java.awt.Graphics2D;

import com.asc.game.components.command.SpaceShipCommand;
import com.asc.game.components.objects.Alien;
import com.asc.game.components.objects.Shot;
import com.asc.game.components.objects.Spaceship;

/**
 * MEDIATOR PATTERN: This is the mediator pattern, all the logic is placed in the implementation of this class.
 * This interface was created to use the Mediator pattern.
 * The class implementing this interface becomes the intermediate between SpaceObjects.
 * @author Abraham Soto
 *
 */
public interface Mediator {
	/**
	 * @return The width of the component where the object will be placed.
	 */
	public int getWidth();
	/**
	 * @return The height of the component where the object will be placed.
	 */
	public int getHeight();
	/**
	 * This method notifies the manager that the ship was hit by an Alien.
	 * @param ship The ship that was hit.
	 * @param thatAlien The Alien that hit it.
	 */
	public void notifyShipHitByAlien(Spaceship ship, Alien thatAlien);
	/**
	 * Notifies the manager that an Alien has been killed.
	 * @param thatAlien The Shot that killed the alien.
	 * @param shot The Alien hit by the shot.
	 */
	public void notifyAlienHitByShot(Shot shot, Alien thatAlien);
	/**
	 * Notifies the manager that the shot is out of the screen.
	 */
	public void notifyShotGone(Shot shot);
	/**
	 * Notifies the manager that two aliens collapsed.
	 * @param thisAlien The first Alien
	 * @param thatAlien The second Alien
	 */
	public void notifyTwoAliensCollided(Alien thisAlien, Alien thatAlien);
	/**
	 * Notifies the manager that the game was restarted.
	 */
	public void notifyGameRestarted();
	/**
	 * Shows the objects and moves them according to their logic.
	 * @param g The Graphics where the objects will be paint.
	 */
	public void showSpaceObjects(Graphics2D g);
	/**
	 * Notifies the orders given to the ship.
	 * @param event The event to handle.
	 */
	public void notifyShipEvent(SpaceShipCommand command);
	/**
	 * Notifies the manager that the game has been paused.
	 */
	public void notifyPause();
	/**
	 * Notifies the manager that a key was pressed.
	 */
	void notifyKeyPressed();
}
