/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Cay Horstmann
 */

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.Color;
import java.awt.color.*;

/**
 * This class runs a world that contains a bug and a rock, added at random
 * locations. Click on empty locations to add additional actors. Click on
 * populated locations to invoke methods on their occupants. <br />
 * To build your own worlds, define your own actors and a runner class. See the
 * BoxBugRunner (in the boxBug folder) for an example. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class BugRunner {

   static int [][] rowColArray = new int[100][100];
  static ActorWorld world = new ActorWorld();
	
	public static void main(String[] args) {
		
		// world.add(new Bug());
		Bug redBug = new Bug();
		world.add(redBug);
		System.out.println(redBug.canMove());
		// redBug.turn();
		// redBug.turn();
		// moveBug(redBug, 50);

		randomBug(redBug, 60);

		world.add(new Rock());
		world.add(new Rock());
		world.add(new Rock());

		world.show();

		// .33443
	}

	private static int getRandom() {
		int max = 4;
		double ran = Math.random();
		double ranD = ran * max;
		int ranI = (int) ranD;
		return ranI;
	}

	private static int getDegrees(int ranI) {

		int degrees = 0;

		switch (ranI) {
		case 0: // north
			degrees = 0;
			break;
		case 1: // east
			degrees = 90;
			break;
		case 2: // south
			degrees = 180;
			break;
		case 3: // west
			degrees = 270;
			break;
		default:
			break;
		}

		return degrees;
	}

	private static void colorBug(Bug bug, Color colr) {
	  bug.setColor(colr);
		
	}
	
	private static String getDirection(int ranI) {

		String direction = "";

		switch (ranI) {
		case 0: // north
			direction = "N";
			break;
		case 1: // east
			direction = "E";
			break;
		case 2: // south
			direction = "S";
			break;
		case 3: // west
			direction = "W";
			break;
		default:
			break;
		}
		return direction;
	}
	
	private static int getNextRow(String direction, int r) {
		int row = 0;
		
		switch (direction) {
		case "N":
			row = r-1;
			break;
		case "S":
			row = r+1;
			break;
		case "E":
			row = r;
			break;
		case "W":
			row = r;
			break;
		default:
			break;
		}
		return row;
	}
	
	private static int getNextCol(String direction, int c) {
		int col = 0;
		
		switch (direction) {
		case "N":
			col = c;
			break;
		case "S":
			col = c;
			break;
		case "E":
			col = c+1;
			break;
		case "W":
			col = c-1;		
			break;
		default:
			break;
		}
		return col;
	}
	
	private static boolean beenHereBefore(int nextR, int nextC) {
		boolean beenHere = false;
		
	    for (int i = 0; i < rowColArray.length; i++) {
			if ((rowColArray[i][0] == nextR) && (rowColArray[i][1] == nextC)) {
				beenHere = true;
			}
		}	
		return beenHere;
	}

	public static void makeBugs(ActorWorld world, int n) {
		int r, c = 0;
		
		for (int i = 0; i < n; i++) {
			Bug bug = new Bug();
			world.add(bug);		
			r = bug.getLocation().getRow();
			c = bug.getLocation().getCol();
			//Color colr = new Color(r,0,c);
			Color colr = new Color(255,0,255);
			//Color colr = new Color(Color.blue);
			bug.setColor(colr);
		}
	}
	
	public static void randomBug(Bug bug, int numMoves) {
		String direction = "";
		int degrees = 0;
		int aryCnt = 0;
		int row, nextRow = 0;
		int col, nextCol = 0;
		boolean beenHere = false;
		
		// set color of bug
		colorBug(bug, Color.yellow);
		
		
		//makeBugs(world,3);
		
		Location loc = new Location(0, 0);
		for (int i = 0; i < numMoves; i++) {
			int ranI = getRandom();
			degrees = getDegrees(ranI);
			direction = getDirection(ranI);
			bug.setDirection(degrees);  // Orient bug in direction he is going

			loc = bug.getLocation(); // r,c
			row = loc.getRow();
			col = loc.getCol();
			System.out.println("Old Location: " + loc + " Row I'm on is: " + row + " Col is: " + col + " " + direction);
			
			// get column and row where I'm going based on direction
			nextCol = getNextCol(direction,col);
			nextRow = getNextRow(direction,row);
			
			// based on values saved in array, check to see if I've been to this location before
			beenHere = beenHereBefore(nextRow,nextCol);
			if (beenHere) {
				System.out.println("*** I have been here before *** " + beenHere +  nextRow + nextCol);
			}
			
			System.out.println("Next Location. Next Row: "  + nextRow + " nextCol is: " + nextCol);
			
//			List<Location> locationList = Arrays.asList(locArray);
//			boolean result = locationList.contains(loc);
		//	beenHere = beenHereBefore(row,col,loc, direction);

			// only move if rock not in my way and I have not been here before
			if (bug.canMove() && beenHere == false) {
				bug.move();
				loc = bug.getLocation();
				row = loc.getRow();
				col = loc.getCol();
				
				rowColArray[aryCnt][0] = row;
				rowColArray[aryCnt][1] = col;

				System.out.println("New Location: " + loc + " Row I'm on is: " + row + " Col is: " + col);
				
				//locArray[aryCnt] = loc;
				aryCnt++;
			} else {
				System.out.println("cannot move");
				//numMoves ++;
				bug.turn();
			}
		}
	}

	public static void moveBug(Bug x, int moves) {
		System.out.println(x.canMove());

		for (int i = 0; i < moves; i++) {

			if (x.canMove()) {
				x.move();
			} else {
				System.out.println("cannot move");
				x.turn();
			}
			System.out.println(x.getLocation());
		}
	}
}
