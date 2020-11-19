package strat;

public class CCD extends Strategy {
	
	private int pos = 0;
	private int[] lookup = {0, 0, 1};
	public CCD() {
		super();
	}

	/**
	 * Plays CDCDCDCDCD...
	 */
	protected int generateMove() {
		pos %= lookup.length;
		int move = lookup[pos];
		pos++;
		return move;
	}
	
	public void reinit() {
		pos = 0;
	}
	
	protected String getName() {
		return "CCD";
	}
	
	protected String getAuthor() {
		return null;
	}
}
