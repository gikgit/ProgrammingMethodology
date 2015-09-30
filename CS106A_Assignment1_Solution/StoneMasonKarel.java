/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {
	
	// You fill in this part
	public void run() {
		repairMason();
		while(frontIsClear()) {
			move4steps();
			turnAround();
			move4steps();
			if(beepersPresent()) {
				turnAround();
				move4steps();
				repairMason();	
			}else{
				turnAround();
				move4steps();
			}
		}
	}
	
	private void repairMason() {
		ascendMason();
		descendMason();
	}
	
	private void move4steps() {
		for(int i=0;i<4;i++) {
			if(frontIsClear()) {
				move();
			}
		}
	}
	
	private void ascendMason() {
		turnLeft();
		while(frontIsClear()) {
			repairOrNot();
			move();
		}
		repairOrNot();
		turnRight();
	}
	
	private void descendMason() {
		turnRight();
		while(frontIsClear()) {
			move();
		}
		turnLeft();
	}
	
	private void repairOrNot() {
		if(noBeepersPresent()) {
			putBeeper();
		}
	}
}
