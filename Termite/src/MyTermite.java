public class MyTermite extends Termite {
	public void act() {
		if (getGrid() == null)
			return;
		if (seeFlower()) {
			if (hasFlower()) {
				dropFlower();
			} else
				pickUpFlower();
		}
		/*if (hasFlower()) {
			dropFlower();
		} */
		if (canMove()) {
			move();
		}
		randomTurn();
	}
}