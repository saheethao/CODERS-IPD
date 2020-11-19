package strat;

public class CD extends Strategy {
	
	private int move = 1;
	public CD() {
		super();
	}

	/**
	 * Plays CDCDCDCDCD...
	 */
	protected int generateMove() {
		if (move == 1) {
			move = 0;
		} else {
			move = 1;
		}
		return move;
	}
	
	public void reinit() {
		move = 1;
	}
	
	protected String getName() {
		return "CD";
	}
	
	protected String getAuthor() {
		return null;
	}
}
