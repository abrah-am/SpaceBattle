package com.asc.projects.spacebattle.main;

import com.asc.projects.spacebattle.objects.Spaceship;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Abraham Soto
 */
public class Main extends Application {
    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;
    
    
    /**
     * Main method.
     * @param args 
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group group = new Group();
        primaryStage.setWidth(WIDTH);
        primaryStage.setHeight(HEIGHT);
        final Scene scene = new Scene(group, WIDTH, HEIGHT, Color.BLACK);
        Spaceship spaceship = new Spaceship(group, scene);
        scene.setOnKeyPressed((KeyEvent event) -> {
            switch(event.getCode()) {
                case RIGHT: spaceship.increaseVelocity(); break;
                case LEFT: spaceship.decreaseVelocity(); break;
                case DOWN: spaceship.increaseAngle(); break;
                case UP: spaceship.decreaseAngle(); break;
            
            }
        });
        primaryStage.setScene(scene);
        primaryStage.show();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                spaceship.move();
                
            }
        };
        gameLoop.start();
        
        
    }
    
}
