/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 404;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

	/** Separation between bricks */
	private static final int BRICK_SEP = 4;

	/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	private static final int NTURNS = 3;
	

	/** instance variable paddle*/
	private GRect paddle;
	private GOval ball;
	private double vx, vy;
	
	/**  
	 * setup the game and wait for mouse click
	 * */
	public void init() {
		setBricks();
		setPaddle();
		setBall();
		waitForClick();
		addMouseListeners( ); 
	}
	
	/**
	 * initialize bricks
	 * */
	private void setBricks() {
		double brickPositionY = BRICK_Y_OFFSET;
		double brickPositionX = BRICK_SEP;
		int num = NBRICK_ROWS/2;
		Color[] color = {Color.RED,Color.ORANGE,Color.YELLOW,Color.GREEN,Color.CYAN};
		
		while(num!=0){
			for(int i=0; i<NBRICKS_PER_ROW; i++) {
				GRect brick = new GRect(brickPositionX,brickPositionY,BRICK_WIDTH,BRICK_HEIGHT);
				brick.setFilled(true);
				brick.setColor(color[5-num]);
				add(brick);
				brickPositionX = brickPositionX + BRICK_WIDTH + BRICK_SEP;
			}
			brickPositionX = BRICK_SEP;
			brickPositionY = brickPositionY + BRICK_HEIGHT + BRICK_SEP;
			for(int i=0; i<NBRICKS_PER_ROW; i++) {
				GRect brick = new GRect(brickPositionX,brickPositionY,BRICK_WIDTH,BRICK_HEIGHT);
				brick.setFilled(true);
				brick.setColor(color[5-num]);
				add(brick);
				brickPositionX = brickPositionX + BRICK_WIDTH + BRICK_SEP;
			}
			brickPositionX = BRICK_SEP;
			brickPositionY = brickPositionY + BRICK_HEIGHT + BRICK_SEP;
			num--;
		}
	}
	
	/**
	 * initialize paddle
	 * */
	public void setPaddle() {
		double paddlePositionX = WIDTH/2.0 - PADDLE_WIDTH/2.0;
		double paddlePositionY = HEIGHT - PADDLE_Y_OFFSET;
		paddle = new GRect(paddlePositionX,paddlePositionY,PADDLE_WIDTH,PADDLE_HEIGHT);
		paddle.setFilled(true);
		paddle.setColor(Color.BLACK);
		add(paddle);
	}
	
	/**
	 * initialize ball
	 * */
	public void setBall() {
		double ballPositionX = paddle.getX() + PADDLE_WIDTH/2.0 - BALL_RADIUS;
		double ballPositionY = paddle.getY() - BALL_RADIUS;
		ball = new GOval(ballPositionX,ballPositionY,BALL_RADIUS,BALL_RADIUS);
		ball.setFilled(true);
		ball.setColor(Color.BLACK);
		add(ball);
	}
	
	/**
	 * launch ball
	 * */
	public void play() {
		RandomGenerator rgen = RandomGenerator.getInstance();
		vx = rgen.nextDouble(1.0, 3.0);
		if (rgen.nextBoolean(0.5)) vx = -vx;
		vy = -3.0;
		
		ball.sendToBack();
		
		while(true) {
			ball.move(vx, vy);
			pause(15);
			if(ball.getX()<=0 || ball.getX()>=WIDTH - BALL_RADIUS){
				AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au");
				bounceClip.play();
				vx = -vx;
			}
			else if(ball.getY()<=0 || hasHitBricks(ball)) {
				AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au");
				bounceClip.play();
				vy = -vy;
			}
			else if(hasHitPaddle(ball)) {
				AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au");
				bounceClip.play();
				vy = -vy;
			}
			else if(ball.getX()<=0 && ball.getY()<=0){
				AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au");
				bounceClip.play();
				vy = -vy;
				vx = -vx;
			}
			else if(ball.getX()>=WIDTH-2*BALL_RADIUS && ball.getY()<=0){
				AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au");
				bounceClip.play();
				vy = -vy;
				vx = -vx;
			}
		}
		
		
	}
	
	private boolean hasHitBricks(GOval ball) {
		GObject hit = getElementAt(ball.getX()+BALL_RADIUS, ball.getY()-0.01);
		if(hit!=null&&hit!=paddle&&hit!=ball) remove(hit);
		return hit != null&&hit!=ball&&hit!=paddle;
	}
	
	/**
	 * check if the ball hits the paddle
	 * */
	private boolean hasHitPaddle(GOval ball) {
		GObject hit = getElementAt(ball.getX()+BALL_RADIUS, ball.getY()+BALL_RADIUS);		
		return hit == paddle; 
	}
	
	/**
	 * make paddle following mouse's move
	 * */
	public void mouseMoved(MouseEvent e) {
		double paddlePositionX = e.getX() - paddle.getWidth()/2.0;
		if(paddlePositionX>=0 && paddlePositionX<=getWidth()-PADDLE_WIDTH) {
			paddle.setLocation(paddlePositionX, paddle.getY());
		}
	}
	
	/**
	 * the main function of the program
	 * */
	public void run() {
		/* You fill this in, along with any subsidiary methods */
		play();
		
	}
}
