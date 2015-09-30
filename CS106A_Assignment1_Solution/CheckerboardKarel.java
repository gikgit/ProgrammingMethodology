/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {
	
	public void run() {
		while(leftIsClear()) {
			layoutOneRow();
			goToNextRow();
		}
		if(frontIsClear()) {
			layoutOneRow();
		}
	}
	
	private void layoutOneRow() {
		goToEnd();
		returnHome();
	}
	
	private void goToNextRow() {
		if(beepersPresent()) {
			turnLeft();
			if(frontIsClear()) {
				move();
			}
			turnRight();
			if(frontIsClear()){
				move();
			}else{
				turnLeft();
				if(frontIsClear()){
					move();
				}	
				turnRight();
			}
		}else{
			turnLeft();
			if(frontIsClear()) {
				move();
			}
			turnRight();
		}
		
	}
	
	private void goToEnd() {		
		do{
			putBeeper();
			for(int i=0;i<2;i++) {
				if(frontIsClear()) {
					move();
				}
			}	
		}while(frontIsClear());		
		if(noBeeperBack()) {
			if(frontIsClear()){
				move();
				putBeeper();
			}
		}
	}
	
	private void returnHome() {
		turnAround();
		while(frontIsClear()) {
			move();
		}
		turnAround();
		
	}
	
	private boolean noBeeperBack() {
		turnAround();
		if(frontIsBlocked()){
			turnAround();
			return true;
		}else{
			move();
			turnAround();
			return noBeepersPresent();
		}
	}
}
