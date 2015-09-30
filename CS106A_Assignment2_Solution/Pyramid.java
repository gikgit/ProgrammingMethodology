/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;


public class Pyramid extends GraphicsProgram {

	/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

	/** Height of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

	/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 22;
	
	public void run() {
		/* You fill this in. */
		int num = BRICKS_IN_BASE;
		double coordinateX = getWidth()/2.0 - BRICKS_IN_BASE*BRICK_WIDTH/2.0;
		double coordinateY = getHeight() - BRICK_HEIGHT;
		
		while(num!=0){
			putOneRow(num,coordinateX,coordinateY);
			coordinateX += BRICK_WIDTH/2.0;
			coordinateY -= BRICK_HEIGHT;
			num--;
		}
	}
	
	private void putOneRow(int num, double positionX, double positionY){
		while(num!=0){
			GRect brick = new GRect(positionX, positionY, BRICK_WIDTH, BRICK_HEIGHT);
			brick.setFilled(false);
			brick.setColor(Color.BLACK);
			add(brick);
			positionX += BRICK_WIDTH;
			num--;	
		}
	}
}

