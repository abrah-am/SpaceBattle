package com.asc.game.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import com.asc.game.components.command.SpaceShipCommand;
import com.asc.game.components.mediator.GameController;
import com.asc.game.components.mediator.Mediator;
import com.asc.game.components.objects.Alien;
import com.asc.game.components.objects.ShipCounter;
import com.asc.game.components.objects.Shot;
import com.asc.game.components.objects.Space;
import com.asc.game.components.objects.SpaceObject;
import com.asc.game.components.objects.Spaceship;
import com.asc.game.components.state.Context;
import com.asc.game.components.state.Context.Event;
import com.asc.game.utils.GameResources.GameImages;
import com.asc.game.utils.GameResources.GameSounds;
import com.asc.game.utils.GameUtils;

@SuppressWarnings("serial")
public class AlienKillerGame extends Canvas implements Runnable, Mediator{
	private static final int DEFAULT_LIVES = 3;
	private static final int DEFAULT_NUMBER_OF_ALIENS = 10;
	private final GameController control;
	private Context gameState;
	private boolean soundOn = true;
	
	private final Space theSpace = new Space();
	private final List<ShipCounter> shipCounterList = new ArrayList<>();

	
	public AlienKillerGame(int width, int height){
		gameState = new Context(this);
		//Add this to the play area.
		setBounds(0, 0, width, height);
		//Not use the repaint method.
		setIgnoreRepaint(true);
		control = new GameController(this);
		addKeyListener(control);
		setFocusable(true);
		requestFocus();
	}
	@Override
	public void run(){
		createBufferStrategy(2);
		BufferStrategy strategy = getBufferStrategy();
		while(true){
			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(Color.white);
			//Shows the objects depending on the the state.
			gameState.show(g);
			//Listens to the reactions of the player.
			control.listens();
			g.dispose();
			strategy.show();
			//Sleep the screen for a little time before repainting it.
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) { e.printStackTrace(); }
		}
	}
	/**
	 * Starts a new game.
	 */
	public void newGame(){
		gameState.handleEvent(Event.NEW_GAME);
	}
	/**
	 * @return <b>true</b> if the sound is active, <b>false<b> otherwise.
	 */
	public boolean isSoundOn(){
		return soundOn;
	}
	/**
	 * Enables or disables the sound.
	 * @param soundOn The flag to indicate if the sound is on/off.
	 */
	public void setSoundOn(boolean soundOn){
		this.soundOn = soundOn;
	}
	@Override
	public void notifyShipHitByAlien(Spaceship ship, Alien thatAlien) {
		if(soundOn)GameSounds.EXPLOTION.playSound();
		//if the spaceship was hit, remove all possible shots.
		theSpace.removeAll(Shot.class);
		if(lives(shipCounterList) > 1){
			//restart the position of the ship, and move the alien somewhere else
			ship.restartPosition();
			//Remove the alien that hit the spaceship and place it somewhere else to not cause another unexpected hit.
			theSpace.remove(thatAlien);
			theSpace.add(GameUtils.createAlien(this, getWidth(), getHeight()));
			//Take off one of the lives.
			shipCounterList.get(lives(shipCounterList) - 1).setAlive(false);
			gameState.handleEvent(Event.SHIP_HIT);
		}
		else{
			gameState.handleEvent(Event.LOST);
			notifyGameRestarted();
		}
	}
	@Override
	public void notifyAlienHitByShot(Shot shot, Alien thatAlien) {
		if(soundOn)GameSounds.EXPLOTION.playSound();
		theSpace.remove(shot);
		theSpace.remove(thatAlien);
		if(theSpace.count(Alien.class) == 0){
			gameState.handleEvent(Event.WIN);
		}
	}
	@Override
	public void notifyShotGone(Shot shot) {
		theSpace.remove(shot);
	}
	@Override
	public void notifyTwoAliensCollided(Alien thisAlien, Alien thatAlien) {
		thisAlien.changeDirectionWith(thatAlien);
	}
	@Override
	public void notifyGameRestarted() {
		theSpace.clear();
		theSpace.add(new Spaceship(this));
		for(int i = 0; i < DEFAULT_NUMBER_OF_ALIENS; i++){
			theSpace.add(GameUtils.createAlien(this, getWidth(), getHeight()));
		}
		resetLiveCounter();
	}
	@Override
	public void showSpaceObjects(Graphics2D g) {
		for(SpaceObject thisObj : theSpace){
			for(SpaceObject thatObj : theSpace){
				if(thisObj != thatObj && thisObj.intersectsWith(thatObj)){
					//Handle what happens when two space objects collapse.
					thisObj.collidedWith(thatObj);
				}
			}
			thisObj.draw(g);
			thisObj.move();
		}
		for(ShipCounter shipCount : shipCounterList) 
			shipCount.draw(g);
	}
	@Override
	public void notifyShipEvent(SpaceShipCommand command) {
		Spaceship ship = theSpace.findShip();
		command.execute(ship);
		if(soundOn) command.playSound();
		if(command == SpaceShipCommand.FIRE){
			theSpace.add(new Shot(this, ship.getXNose(), ship.getYNose(), (double)ship.getCurrentAngle()));
		}
	}
	/**
	 * Returns how many lives it has
	 * @param ships
	 * @return
	 */
	private static final int lives(List<ShipCounter> ships){
		int count = 0;
		for(ShipCounter ship : ships){
			if(ship.isAlive()) count++;
		}
		return count;
	}
	/**
	 * Shows the user how many lives it has.
	 */
	private void resetLiveCounter(){
		shipCounterList.clear();
		for(int i = 0; i < DEFAULT_LIVES; i++){
			shipCounterList.add(new ShipCounter(
					(10 + (GameImages.SHIPALIVE.getImage().getWidth() * i)), 
					(5 + GameImages.SHIPALIVE.getImage().getHeight()))
			);
		}
	}
	@Override
	public void notifyPause() {
		gameState.handleEvent(Event.GAME_PAUSED);
	}
	@Override
	public void notifyKeyPressed(){
		gameState.handleEvent(Event.KEY_PRESSED);
	}
}
