package com.asc.game.components.mediator;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.asc.game.components.command.SpaceShipCommand;

/**
 * This class controls the behavior of the game.
 * @author Abraham Soto
 *
 */
public class GameController implements KeyListener{
	/** The interval between shots. Time measured in milliseconds. */
	private static final int SHOT_FIRING_INTERVAL = 500;
	private long lastShot = 0;
	private boolean keyPressed = false;
	private int keyCode;
	private final Mediator manager;
	
	/**
	 * Constructor. This constructor is used to link the key listener with the manager.
	 * @param manager The manager where the instructions will be sent to.
	 */
	public GameController(Mediator manager) {
		this.manager = manager;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		keyPressed = true;
		keyCode = e.getKeyCode();
	}
	@Override
	public void keyReleased(KeyEvent e) {
		keyPressed = false;
	}
	@Override public void keyTyped(KeyEvent e) { }
	
	public void listens(){
		if(!keyPressed) return;
		
		//The key ESC pauses the game.
		if(keyCode == KeyEvent.VK_ESCAPE){
			manager.notifyPause();
		}
		else{
			manager.notifyKeyPressed();
		}
		
		if(keyCode == KeyEvent.VK_DOWN){
			manager.notifyShipEvent(SpaceShipCommand.INCREASE_ANGLE);
		}
		if(keyCode == KeyEvent.VK_UP){
			manager.notifyShipEvent(SpaceShipCommand.DECREASE_ANGLE);
		}
		if(keyCode == KeyEvent.VK_RIGHT){
			manager.notifyShipEvent(SpaceShipCommand.SPEED_UP);
		}
		if(keyCode == KeyEvent.VK_LEFT){
			manager.notifyShipEvent(SpaceShipCommand.SPEED_DOWN);
		}
		if(keyCode == KeyEvent.VK_SPACE){
			if(System.currentTimeMillis() - lastShot >SHOT_FIRING_INTERVAL){
				manager.notifyShipEvent(SpaceShipCommand.FIRE);
				lastShot = System.currentTimeMillis();
			}	
		}
	}
}