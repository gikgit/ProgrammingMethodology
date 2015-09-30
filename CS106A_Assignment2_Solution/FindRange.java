/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	public void run() {
		/* You fill this in */
		setFont("DejaVuSerif-BOLD-24");
		println("This program finds the largest and smallest numbers.");
		int sentinel = 0;
		int max = 0;
		int min = 0;
		int count = 0;
		
		while(true){
			int tmp=0;
			tmp=readInt("?");
			if(tmp==sentinel) break;
			else{
				count++;
				if(tmp>max) max=tmp;
				else if(tmp<min) min=tmp;
				else continue;
			}
		}
		
		if(count==0) println("You have nothing to input");
		else if(count==1) 
			println("largest number and smallest number are both "+ (max==0 ? min : max));
		else {
			println("largest: "+max);
			println("smallest: "+min);
		}
	}
}

