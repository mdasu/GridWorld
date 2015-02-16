

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

/**
 *
 */
public class Termite extends Bug {
    Flower flower;

    Flower dropped = null;
    Location dropLoc = null;

    /*
     * Does that thing that termites do. 
     */
    public void act() {
        if (getGrid() == null)
            return;

        if (canMove()) {
	    move();
        }
	randomTurn();
    }

    /*
     * Turns left or right 45 degrees at random.
     */
    public void randomTurn() {
	if (Math.random() < 0.5) {
	    turn(-90);
	} else {
	    turn(90);
	}
    }

    /*
     * Turns the given number of degrees.
     */
    public void turn(int degrees) {
	setDirection(getDirection() + degrees);
    }

    /*
     * Checks whether the termite can move.
     * Note: unlike most Bugs, termites can't move onto flowers.
     */
    public boolean canMove() {
        Location next = getNextLocation();
        return (isValid(next) && getNeighbor() == null);
    }
  

    /*
     * Moves in the direction the termite is facing.
     * Drops a flower, if one has been dropped, in the old location.
     */
    public void move() {
	Location next = getNextLocation();
        if (isValid(next)) {
            moveTo(next);
        } else {
            removeSelfFromGrid();
	}
	if (dropped != null) {
	    dropped.putSelfInGrid(getGrid(), dropLoc);
	    dropped = null;
	}
    }

    /*
     * Returns true if the termite has a flower.
     */
    public boolean hasFlower() {
	return flower != null;
    }

    /*
     * Returns true if the termite is facing a flower.
     */
    public boolean seeFlower() {
	return getNeighbor() instanceof Flower;
    }

    /*
     * Creates a flower unless the termite already has one.
     */
    public void createFlower() {
	if (hasFlower()) {
	    System.out.println("createFlower? You already have one!");
	    return;
	}
	flower = new Flower();
    }

    /*
     * Drops the flower in the termites current location.
     * 
     * Note: only one Actor can occupy a grid cell, so the effect of
     * dropping a flower is delayed until the termite moves.
     */
    public void dropFlower() {
	if (!hasFlower()) {
	    System.out.println("dropFlower?  You don't have one!");
	    return;
	}
	dropped = flower;
	dropLoc = getLocation();
	flower = null;
    }

    /*
     * Throws the flower into the location the termite is facing.
     * 
     */
    public void throwFlower() {
	if (!hasFlower()) {
	    System.out.println("throwFlower?  You don't have one!");
	    return;
	}
	if (getNeighbor() != null) {
	    System.out.println("throwFlower?  There's someone there!");
	    return;
	}
	Location next = getNextLocation();
	if (!isValid(next)) {
	    System.out.println("throwFlower?  Invalid location!");
	    return;
	}
	flower.putSelfInGrid(getGrid(), next);
	flower = null;
    }

    /*
     * Picks up the flower the termite is facing, if there is one,
     * and if the termite doesn't already have a flower.
     */
    public void pickUpFlower() {
	if (hasFlower()) {
	    System.out.println("pickUpFlower? You already have one!");
	    return;
	}
	Actor neighbor = getNeighbor();
	if (neighbor instanceof Flower) {
	    flower = (Flower) neighbor;
	    flower.removeSelfFromGrid();
	} else {
	    System.out.println("pickUpFlower? There is no flower!");
	}
    }

    /*
     * Gets the location the termite is facing.
     */
    private Location getNextLocation() {
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
	return next;
    }

    /*
     * Gets the contents of the location the termite is facing.
     * Returns null if the location is empty or invalid.
     */
    private Actor getNeighbor() {
	Location next = getNextLocation();
	if (getGrid().isValid(next)) {
	    return getGrid().get(next);
	} else {
	    return null;
	}
    }

    /*
     * Checks whether a location is valid in the termite's grid.
     */
    private boolean isValid(Location loc) {
	return getGrid().isValid(loc);
    }

}
