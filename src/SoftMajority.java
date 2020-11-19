package strat;

public class SoftMajority extends Strategy {
	private int numC = 0;
	private int numD = 0;
	public SoftMajority() {
		super();
	}

	public void reinit() {
		numC = 0;
		numD = 0;
	}
	
	/**
	 * Begins by cooperating and cooperates as long as 
	 * the number of times the opponent has cooperated 
	 * is greater that or equal to the number of times 
	 * it has defected. Otherwise defects
	 */
	protected int generateMove() {
		int rounds = info.getRoundsPlayed();
		if (rounds == 0) {
			return 0;
		}
		int[] oppMoves = info.getOppMoves();
		int oppMove = oppMoves[info.getRoundsPlayed() - 1];
		if (oppMove == 0) {
			numC += 1;
		} else {
			numD += 1;
		}
		if (numC >= numD) {
			return 0;
		} else {
			return 1;
		}
	}
	
	protected String getName() {
		return "Soft Majority";
	}
	
	protected String getAuthor() {
		return null;
	}
}
