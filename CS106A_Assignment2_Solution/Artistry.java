/*
 * File: Artistry.java
 * Name:
 * Section Leader:
 * ==========================================================
 * Replace these comments with a description of your program.
 * Since this program is more freeform than the rest, tell us
 * a bit about it in these comments!
 */
import java.awt.Color;

import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.*;

public class Artistry extends GraphicsProgram {
	
	private static final double RECT_X = 10;
	private static final double RECT_Y = 0;
	private static final double RECT_WIDTH = 100;
	private static final double RECT_HEIGHT = 200;
		
	public void run() {
		/* You fill this in.  Remember that you must have
		 * 
		 * 1. At least one filled object,
		 * 2. At least two different colors of objects, and
		 * 3. At least three different types of objects (not
		 *    counting the GLabel you use to sign your name).
		 * 
		 * Also, be sure to sign your name in the bottom-right
		 * corner of the canvas. Your name should be flush up
		 * against the bottom-right corner of the label. We
		 * recommend looking at the getDescent() method as a
		 * means for calculating where to position the label.
		 */
		GRect rect = new GRect(RECT_X, RECT_Y, RECT_WIDTH, RECT_HEIGHT);
		rect.setFilled(true);
		rect.setColor(Color.GREEN);
		add(rect);
		
		
		GOval oval = new GOval(50, 50);
		oval.setFilled(true);
		oval.setColor(Color.RED);
		double coordinateX = getWidth()/2.0 - oval.getWidth()/2.0;
		double coordinateY = getHeight()/2.0 - oval.getHeight()/2.0;
		add(oval,coordinateX,coordinateY);
		
		GLabel helloLabel = new GLabel("Hello, world!", 150, 150);
		helloLabel.setFont("CenturySchoolbookL-36");
		helloLabel.setColor(Color.BLUE);
		add(helloLabel);
		
	}
}
