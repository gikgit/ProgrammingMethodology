/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

	private JTextField inputName;
	private NameSurferDataBase dataBase;
	private NameSurferGraph graph;
	
	
	
	/* Method: init() */
	/**
	 * This method has the responsibility for reading in the data base
	 * and initializing the interactors at the top of the window.
	 */
	public void init() {
	    // You fill this in, along with any helper methods 
		dataBase = new NameSurferDataBase("names-data.txt");
		graph = new NameSurferGraph();
		add(graph);
		inputName = new JTextField(25);
		add(new JLabel("Name: "), NORTH);
		add(inputName, NORTH);
		add(new JButton("Graph"), NORTH);
		add(new JButton("Clear"), NORTH);
		
		addActionListeners();
		inputName.addActionListener(this);
		inputName.setActionCommand("Graph");
			
	}

	/* Method: actionPerformed(e) */
	/**
	 * This class is responsible for detecting when the buttons are
	 * clicked, so you will have to define a method to respond to
	 * button actions.
	 */
	public void actionPerformed(ActionEvent e) {
		// You fill this in //
		if(e.getActionCommand().equals("Graph")){
			NameSurferEntry entry = dataBase.findEntry(inputName.getText());
			graph.restore.add(entry);
			graph.addEntry(entry);
		}
		else if(e.getActionCommand().equals("Clear")){
			graph.clear();
		}
	}
	
		
		
	
}
