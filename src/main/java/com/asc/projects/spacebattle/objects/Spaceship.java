package com.asc.projects.spacebattle.objects;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;

/**
 * Represents an spaceship.
 *
 * @author Abraham Soto
 */
public class Spaceship {
    /** The increment or decrement ratio of the speed */
    private static final double VELOCITY_INCREMENT = 0.5;
    /** The increment or decrement ratio of the angle */
    private static final int ANGLE_INCREMENT = 15;
    
    private final ImageView image;
    private double xSpeed = 0;
    private double ySpeed = 0;
    /** The velocity and angle  */
    private int velocityLevel = 0, currentAngle = 0;    
    private final Scene scene;

    public Spaceship(Group group, Scene scene) {
        this.image = new ImageView("/images/ship.gif");
        this.scene = scene;
        this.image.setX(scene.getWidth()/2);
        this.image.setY(scene.getHeight()/2);
        group.getChildren().add(this.image);
    }

    /**
     * Increases the angle, it actually rotates the ship downwards.
     */
    public final void decreaseAngle(){
        currentAngle = (currentAngle >= 365) ? 0 : currentAngle + ANGLE_INCREMENT;
        image.setRotate(currentAngle);
        adjustVelocity();
    }
    /**
     * Increases the angle, it actually rotates the ship upwards.
     */
    public final void increaseAngle(){
        currentAngle = (currentAngle <= 0) ? 365 : currentAngle - ANGLE_INCREMENT; 
        image.setRotate(currentAngle);
        adjustVelocity();
    }    
    
    /**
     * Increases the velocity of the spaceship.
     */
    public void increaseVelocity() {
        velocityLevel++;
        adjustVelocity();
    }

    /**
     * Decreases the velocity of the spaceship.
     */
    public void decreaseVelocity() {
        if(velocityLevel > 0){
            velocityLevel--;
            adjustVelocity();
        }
    }

    /**
     * This method changes the x and y position of the spaceship every time it is called.
     */
    public void move() {
        image.setX(image.getX() + xSpeed);
        if(image.getX() < 0) {
            image.setX(scene.getWidth());
        } 
        if(image.getX() > scene.getWidth()){
            image.setX(0);
        }  
        
        image.setY(image.getY() + ySpeed);
        if(image.getY() < 0){
            image.setY(scene.getHeight());
        }
        if(image.getY() > scene.getHeight()){
            image.setY(0);
        }
    }
    
    
    /**
     * This method must be invoked every time the spaceship changes its direction or speed.
     */
    private void adjustVelocity(){
        //restart the values.
        xSpeed = ySpeed = 0;
        for(int i=0; i < this.velocityLevel; i++){
            xSpeed += calculateMovingAngleX((double)currentAngle) * VELOCITY_INCREMENT;
            ySpeed += calculateMovingAngleY((double)currentAngle) * VELOCITY_INCREMENT;
        }
    }
    /**
     * Calculates the moving angle for Y axis
     * @param yAngle The moving angle of Y axis.
     * @return The moving angle for Y
     */
    private static Double calculateMovingAngleY(double yAngle){
            return Math.sin(yAngle * Math.PI  / 180);
    }
    /**
     * Calculates the moving angle for X axis
     * @param xAngle The moving angle of X axis.
     * @return The moving angle for X
     */
    private static Double calculateMovingAngleX(double xAngle){
            return Math.cos(xAngle * Math.PI  / 180);
    }            
}
