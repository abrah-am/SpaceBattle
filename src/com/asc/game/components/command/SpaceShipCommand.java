package com.asc.game.components.command;

import com.asc.game.components.objects.Spaceship;
import com.asc.game.utils.GameResources.GameSounds;

/**
 * COMMAND PATTERN: Usage of the command pattern. 
 * The command indicates the operation the ship will execute.
 * Also indicates the sound to be played when the state of the ship changes.
 * @author Abraham Soto
 *
 */
public enum SpaceShipCommand {
	
	SPEED_UP{
		@Override public void execute(Spaceship ship) {
			ship.increaseVelocity();
		}
		@Override public void playSound() {
			GameSounds.SPEED.playSound();
		}
	},
	SPEED_DOWN{
		@Override public void execute(Spaceship ship) {
			ship.decreaseVelocity();
		}
		@Override public void playSound() {
			GameSounds.SPEED.playSound();
		}
	},
	INCREASE_ANGLE{
		@Override public void execute(Spaceship ship) {
			ship.increaseAngle();
		}
		@Override public void playSound() {
			GameSounds.SPEED.playSound();
		}
	},
	DECREASE_ANGLE{
		@Override public void execute(Spaceship ship) {
			ship.decreaseAngle();
		}
		@Override public void playSound() {
			GameSounds.SPEED.playSound();
		}
	},
	FIRE{
		@Override public void execute(Spaceship ship) {
		}
		@Override public void playSound() {
			GameSounds.LASER.playSound();
		}
	};
	
	/**
	 * This executes an operation on the ship.
	 * @param ship The instance of the {@link Spaceship} on which the command will be executed.
	 */
	public abstract void execute(Spaceship ship);
	
	/**
	 * This method invokes the corresponding sound for the operation. 
	 */
	public abstract void playSound();
}
