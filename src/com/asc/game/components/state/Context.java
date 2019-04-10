package com.asc.game.components.state;

import java.awt.Graphics2D;

import com.asc.game.components.mediator.Mediator;

/**
 * Context: This context contains the current state of the application.
 * Also has the methods to change the state.
 * @author Abraham Soto
 *
 */
public class Context {
	private static final String MSG_WIN = "Congrats, you won!";
	private static final String MSG_LOST = "You've lost, try again.";
	private static final String MSG_PRESS_ANY_KEY_TO_CONTINUE = "Press any key to continue...";
	
	//Operations that can change the state of the application.
	public enum Event{ NEW_GAME, LOST, WIN, SHIP_HIT, KEY_PRESSED, GAME_PAUSED };
	
	//This variable keeps the state of the game.
	private State currentState = State.STOPPED;
	private final Mediator mgr;
	private String message;
	
	/**
	 * Public constructor.
	 * @param mgr The manager to execute the operations.
	 */
	public Context(Mediator mgr){
		this.mgr = mgr;
		this.message = "";
	}
	
	/**
	 * @return Returns the current state.
	 */
	public final State getCurrentState() {
		return currentState;
	}
	
	/**
	 * Sets the current state.
	 * @param currentState
	 */
	public final void setCurrentState(State currentState) {
		this.currentState = currentState;
	}
	/**
	 * Changes the state depending on the event.
	 * @param event
	 */
	public void handleEvent(Event event){
		if(event == Event.NEW_GAME){
			if(currentState.start() != null){
				currentState = currentState.start();
				mgr.notifyGameRestarted();
			}
		}
		if(event == Event.SHIP_HIT || event == Event.GAME_PAUSED){
			if(currentState.pause() != null){
				currentState = currentState.pause();
				message = MSG_PRESS_ANY_KEY_TO_CONTINUE;
			}
		}
		if(event == Event.KEY_PRESSED){
			if(currentState.resume() != null)
				currentState = currentState.resume();
		}
		if(event == Event.LOST){
			if(currentState.lost() != null){
				currentState = currentState.lost();
				message = MSG_LOST;
			}	
		}
		if(event == Event.WIN){
			if(currentState.win() != null){
				currentState = currentState.win();
				message = MSG_WIN;
			}
		}
	}
	/**
	 * Choose what to print depending on the state of the application.
	 */
	public void show(Graphics2D g){
		if(currentState == State.PAUSED || currentState == State.LOST || currentState == State.WIN)
			g.drawString(message,(mgr.getWidth()-g.getFontMetrics().stringWidth(message))/2, mgr.getHeight()/2);

		if(currentState == State.STARTED || currentState == State.RESUMED || currentState == State.WIN)
			mgr.showSpaceObjects(g);
	}
}
