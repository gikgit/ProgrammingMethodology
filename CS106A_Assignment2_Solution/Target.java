/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	public void run() {
		/* You fill this in. */
		addCircle1();
		addCircle2();
		addCircle3();
			
	}
	
	private void addCircle1(){
		GOval circle = new GOval(72, 72);
		circle.setFilled(true);
		circle.setColor(Color.RED);
		double coordinateX = getWidth()/2.0 - circle.getWidth()/2.0;
		double coordinateY = getHeight()/2.0 - circle.getHeight()/2.0;
		add(circle,coordinateX,coordinateY);
	}
	
	private void addCircle2() {
		GOval circle = new GOval(72*0.65, 72*0.65);
		circle.setFilled(true);
		circle.setColor(Color.WHITE);
		double coordinateX = getWidth()/2.0 - circle.getWidth()/2.0;
		double coordinateY = getHeight()/2.0 - circle.getHeight()/2.0;
		add(circle,coordinateX,coordinateY);
	}
	
	private void addCircle3() {
		GOval circle = new GOval(72*0.3, 72*0.3);
		circle.setFilled(true);
		circle.setColor(Color.RED);
		double coordinateX = getWidth()/2.0 - circle.getWidth()/2.0;
		double coordinateY = getHeight()/2.0 - circle.getHeight()/2.0;
		add(circle,coordinateX,coordinateY);
	}
}
