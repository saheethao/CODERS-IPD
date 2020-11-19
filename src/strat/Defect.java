package strat;

public class Defect extends Strategy {
	
	public Defect() {
		super();
	}

	/**
	 * Unconditionally defects
	 */
	protected int generateMove() {
		return 1;
	}
	
	public void reinit() {
		/* None needed */
	}
	
	protected String getName() {
		return "Defect";
	}
	
	protected String getAuthor() {
		return null;
	}
}
