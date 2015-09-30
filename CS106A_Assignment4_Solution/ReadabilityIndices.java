/* File: ReadabilityIndices.java
 * Name: [TODO: Your name here!]
 * Section Leader: [TODO: Your SL's name here!]
 * 
 * [TODO: Edit this header file to include a description of this program!]
 */

import acm.program.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ReadabilityIndices extends ConsoleProgram {
	
	public static final double C0 = -15.59;
	public static final double C1 = 0.39;
	public static final double C2 = 11.8;
	public static final double D0 = 0.1579;
	public static final double D1 = 100;
	public static final double D2 = 0.0496;
	public static final double D3 = 3.6365;
		
	public void run() {
		setFont("UbuntuMono-26-BOLD");
		while (true) {
			String filename = readLine("Enter filename: ");
			if (filename.isEmpty()) break;
			ArrayList<String> file = fileContents(filename);
			if(file.isEmpty()) continue;
			double grade = fleschKincaidGradeLevelOf(file);
			double difficulty = daleChallReadabilityScoreOf(file);
			println("Flesch-Kincaid Grade Level:"+"\t"+grade);
			println("Dale Chall Readability Score:"+"\t"+difficulty);
		}
		
	}
	
	private int syllablesInWord(String word) {
		int count = 0;
		word = word.toLowerCase();
		for(int i=0; i<word.length(); i++){
			char current = word.charAt(i);
			if(current == 'a'||current == 'e'||current == 'i'||
					current == 'o'||current == 'u'||current == 'y')
			{
				count++;
				i++;
			}
		}
		if(word.length()>=1 && word.charAt(word.length()-1) == 'e') 
			count = count - 1;	
		return count;
	}
	

	private ArrayList<String> tokenize(String line) {
		ArrayList<String> tokens = new ArrayList<String>();
		for(int i=0; i<line.length(); i++) {
			String tmp = "";
			char current = line.charAt(i);
			if(Character.isLetter(current)){
				while(Character.isLetter(current)){
					tmp += current;
					i++;
					if(i==line.length()) break;
					current = line.charAt(i);
				}
				i--;
				tokens.add(tmp);
			}
			else{
				tmp += current;
				tokens.add(tmp);
			}
		}	
		return tokens;
	}
	
	
	private int syllablesInLine(ArrayList<String> tokens){
		int sum = 0;
		for(String curr: tokens){
			if(curr!=" "&&Character.isLetter(curr.charAt(0))){
				sum += syllablesInWord(curr);
			}
		}		
		return sum;
	}
	
	
	private int wordsInLine(ArrayList<String> tokens){
		int count = 0;
		for(String curr: tokens){
			if(curr!=" "&&Character.isLetter(curr.charAt(0))) count++;
		}		
		return count;
	}
	
	private int sentencesInLine(ArrayList<String> tokens){
		int count = 0;
		for(String curr: tokens){
			if(curr!=" "&& (curr.charAt(0) == '.' || curr.charAt(0) == '!' || curr.charAt(0) == '?')) count++;
		}		
		return count;
	}
	
	
	private ArrayList<String> fileContents(String filename){	
		ArrayList<String> file = new ArrayList<String>();	
		try {
			BufferedReader br = 
				new BufferedReader(new FileReader(filename));
				
			while (true) {
				String line = br.readLine();
				if (line == null) break;			
				file.add(line);
			}
		
			br.close();
			
		} catch (IOException e) {
			println("Could not open that file.");
		}		
		return file;
	}
	
	private double fleschKincaidGradeLevelOf(ArrayList<String> lines){
		double numWords = 0;
		double numSentences = 0;
		double numSyllables = 0;	
		for(String curr: lines) {
			if(curr.isEmpty()) continue;
			ArrayList<String> tokens = tokenize(curr);
			numWords += wordsInLine(tokens);
			numSentences += sentencesInLine(tokens);
			numSyllables += syllablesInLine(tokens);
			if(numWords==0) numWords = 1;
			if(numSentences==0) numSentences = 1;
		}			
		return C0 + C1*(numWords/numSentences) + C2*(numSyllables/numWords);
	}
	

	private int difficultWordsInLine(ArrayList<String> tokens){
		int count = 0;
		for(String curr: tokens){
			if(curr!=" "&&Character.isLetter(curr.charAt(0))){
				if(syllablesInWord(curr)>=3) count++;
			}
		}	
		return count;
	}
	
	private double daleChallReadabilityScoreOf(ArrayList<String> lines){
		double numWords = 0;
		double numSentences = 0;
		double numDifficultyWords = 0;
		for(String curr: lines) {
			if(curr.isEmpty()) continue;
			ArrayList<String> tokens = tokenize(curr);
			numWords += wordsInLine(tokens);
			numSentences += sentencesInLine(tokens);
			numDifficultyWords += difficultWordsInLine(tokens);
			if(numWords==0) numWords = 1;
			if(numSentences==0) numSentences = 1;
		}		
		double bonus = (numDifficultyWords/numWords >= 0.05) ? 1 : 0;
		return D0*(numDifficultyWords*D1/numWords) + D2*(numWords/numSentences) + D3*bonus;
	}
	
}