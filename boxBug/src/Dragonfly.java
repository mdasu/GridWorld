import java.awt.Color;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class Dragonfly extends Bug {

	// Construct a dragonfly
	public Dragonfly() {

	}

	// Construct a dragonfly with color set
	public Dragonfly(Color DragonflyColor) {
		setColor(DragonflyColor);
	}

	/**
	 * Moves if it can move, turns otherwise.
	 */
	public void act() {
		if (canMove()) {
			move();			
		} else 
		turn();
	}
	
	

}
