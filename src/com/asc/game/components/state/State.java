package com.asc.game.components.state;

/**
 * State: The different states of the application.
 * @author Abraham Soto
 *
 */
public enum State {
	STOPPED{
		@Override public State start() { return STARTED; }
	}, 
	STARTED{
		@Override public State start() { return STARTED; }
		@Override public State pause() { return PAUSED; }
		@Override public State lost() { return LOST; }
		@Override public State win() { return WIN; }
	}, 
	PAUSED{
		@Override public State start() { return STARTED; }
		@Override public State resume() { return RESUMED; }
	}, 
	WIN{
		@Override public State start() { return STARTED; }
	}, 
	LOST{
		@Override public State start() { return STARTED; }
	}, 
	RESUMED{
		@Override public State start() { return STARTED; }
		@Override public State pause() { return PAUSED; }
		@Override public State lost() { return LOST; }
		@Override public State win() { return WIN; }
	};
	
	public State start()  { return null; }
	public State pause()  { return null; }
	public State resume() { return null; }
	public State lost()   { return null; }
	public State win()    { return null; }
}
