package strat;

public class Cooperate extends Strategy {
	
	public Cooperate() {
		super();
	}

	/**
	 * Unconditionally cooperates
	 */
	protected int generateMove() {
		return 0;
	}
	
	public void reinit() {
		/* None needed */
	}
	
	protected String getName() {
		return "Cooperate";
	}
	
	protected String getAuthor() {
		return null;
	}
}
