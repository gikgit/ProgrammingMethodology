/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {

	// You fill in this part
	public void run() {
		setBeeperUp();
		while(noBeepersPresent()){
			backAndForth();
		}
		pickBeeper();
		turnAround();
		if(frontIsClear()){
			move();
		}
	}
	
	private void setBeeperUp() {
		putBeeper();
		while(frontIsClear()) {
			move();
		}
		putBeeper();
		turnAround();
		if(frontIsClear()) {
			move();
		}
	}
	
	private void backAndForth() {
		if(beepersPresent()) {
			pickBeeper();
			if(noBeepersPresent()&&frontIsClear()) {
				move();
				putBeeper();
				if(frontIsClear()) {
					move();
				}
			}
		}
		while(noBeepersPresent()&&frontIsClear()) {
			move();
		}
		
		pickBeeper();
		turnAround();
		if(frontIsClear()) {
			move();
		}
		putBeeper();
		if(frontIsClear()) {
			move();
		}
	}
}
