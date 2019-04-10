package com.asc.game.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class GameResources{
	
	/**
	 * SINGLETON PATTERN: This enum uses the singleton pattern to create one instance of each image.
	 * @author Abraham Soto
	 *
	 */
	public enum GameImages{
		SHIP("ship.gif"), 
		ALIEN("alien.gif"), 
		SHOT("shot.gif"),
		SHIPALIVE("shipalive.gif"),
		SHIPDEAD("shipdead.gif");
		
		
		private static final String IMAGES_PATH = "/images/";
		private BufferedImage theImage;

		private GameImages(String fileName){
			try {
				this.theImage = ImageIO.read(getClass().getResource(IMAGES_PATH + fileName));
			} catch (Exception e) {
				System.out.println("Image not found: " + fileName);
				//e.printStackTrace();
			}
		}

		public BufferedImage getImage(){ return this.theImage; }
	}
	
	//Loads the sound effects for the game
	public enum GameSounds{
		LASER("laser.aiff"), 
		EXPLOTION("explode.wav"), 
		SPEED("thrust.au");
		
		private static final String SOUNDS_PATH = "/sounds/";
		private URL soundResource;
		
		private GameSounds(String fileName){
			soundResource = getClass().getResource(SOUNDS_PATH + fileName);
		}
		
		public void playSound(){
			try {
				AudioInputStream ais = AudioSystem.getAudioInputStream(soundResource);
				Clip clip = AudioSystem.getClip();
				clip.open(ais);
				clip.loop(0);
				clip.start();
			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
				e.printStackTrace();
			}
		}
	}
}
