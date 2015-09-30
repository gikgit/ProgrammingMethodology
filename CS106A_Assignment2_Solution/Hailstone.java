/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		/* You fill this in */
		setFont("DejaVuSerif-BOLD-24");
		int number = readInt("Enter a number: ");
		int step=0;
		
		while(number!=1){
			step++;
			if(number%2==0){
				println(number+" is even, so I take half: "+number/2);
				number = number/2;
			}
			else{
				println(number+" is odd, so I make 3n+1: "+(3*number+1));
				number = 3*number+1;
			}	
		}
		
		println("The process took "+step+" to reach 1");
	}
}

