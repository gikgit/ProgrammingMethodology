/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	public ArrayList<NameSurferEntry> restore = new ArrayList<NameSurferEntry>();
	/**
	 * Creates a new NameSurferGraph object that displays the data.
	 */
	public NameSurferGraph() {
		addComponentListener(this);
		// You fill in the rest //
		drawBackground();
	}
	
	
	/**
	 * Clears the list of name surfer entries stored inside this class.
	 */
	public void clear() {
		// You fill this in //
		removeAll();
		drawBackground();
		restore.clear();
	}
	
	
	/* Method: addEntry(entry) */
	/**
	 * Adds a new NameSurferEntry to the list of entries on the display.
	 * Note that this method does not actually draw the graph, but
	 * simply stores the entry; the graph is drawn by calling update.
	 */
	public void addEntry(NameSurferEntry entry) {
		// You fill this in //		
		Random rand = new Random();
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		Color randomColor = new Color(r, g, b);
		
		double rankScale = (double)(getHeight()-2*GRAPH_MARGIN_SIZE)/(MAX_RANK-1);
		double xStartPos=0,yStartPos=0,xEndPos=0,yEndPos=0;
		double xScale;
		xStartPos = 0; 
		xScale = getWidth()/NDECADES;
		int year = START_DECADE;
		for(int i=0;i<NDECADES-1;i++){
			if(entry.getRank(year)==0) yStartPos = getHeight() - GRAPH_MARGIN_SIZE;
			else yStartPos = (entry.getRank(year)-1)*rankScale+GRAPH_MARGIN_SIZE;
			xEndPos = xStartPos + xScale;
			if(entry.getRank(year+10)==0) yEndPos = getHeight() - GRAPH_MARGIN_SIZE;
			else yEndPos = (entry.getRank(year+10)-1)*rankScale+GRAPH_MARGIN_SIZE;	
			GLine line = new GLine(xStartPos,yStartPos,xEndPos,yEndPos);
			GLabel label = new GLabel(entry.getName()+" "+entry.rank[i],xStartPos,yStartPos);
			line.setColor(randomColor);
			label.setColor(randomColor);
			add(line);
			add(label);
			xStartPos = xStartPos + xScale;
			year += 10;
		}
		
		GLabel label = new GLabel(entry.getName()+" "+entry.rank[11], xEndPos, yEndPos);
		label.setColor(randomColor);
		add(label);
	}
	
	
	/**
	 * Updates the display image by deleting all the graphical objects
	 * from the canvas and then reassembling the display according to
	 * the list of entries. Your application must call update after
	 * calling either clear or addEntry; update is also called whenever
	 * the size of the canvas changes.
	 */
	public void update() {
		// You fill this in //
		removeAll();
		drawBackground();
		for(NameSurferEntry item: restore){
			addEntry(item);
		}
	}
	
	public void drawBackground(){
		double xStartPos,yStartPos,xEndPos,yEndPos;
		double xScale,yScale;
		int yearStart = START_DECADE;
		xScale = getWidth()/NDECADES;
		xStartPos = 0;
		yStartPos = 0;
		xEndPos = xStartPos;
		yEndPos = getHeight();
		for(int i=0;i<NDECADES;i++){
			GLine verticalLine = new GLine(xStartPos,yStartPos,xEndPos,yEndPos);
			verticalLine.setColor(Color.BLACK);
			add(verticalLine);
			add(new GLabel(Integer.toString(yearStart),xEndPos,yEndPos));
			xStartPos += xScale;
			xEndPos += xScale;
			yearStart += 10;
		}
		
		yScale = GRAPH_MARGIN_SIZE;
		xStartPos = 0;
		yStartPos = yScale;
		xEndPos = getWidth();
		yEndPos = yScale;
		GLine horizontalUpLine = new GLine(xStartPos,yStartPos,xEndPos,yEndPos);
		horizontalUpLine.setColor(Color.BLACK);
		yStartPos = getHeight() - yScale;
		yEndPos = getHeight() - yScale;
		GLine horizontalDownLine = new GLine(xStartPos,yStartPos,xEndPos,yEndPos);
		horizontalDownLine.setColor(Color.BLACK);
		add(horizontalUpLine);
		add(horizontalDownLine);
	}
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
