package strat;

import java.util.Random;

public class RandomMove extends Strategy {
	private Random r;
	
	public RandomMove() {
		super();
		r = new Random();
	}
	
	public void reinit() {
		/* None needed */
	}

	/**
	 * Randomly chooses to cooperate or defect
	 */
	protected int generateMove() {
		return r.nextInt(2); // Generate 0 or 1
	}
	
	protected String getName() {
		return "Random";
	}
	
	protected String getAuthor() {
		return null;
	}

}
