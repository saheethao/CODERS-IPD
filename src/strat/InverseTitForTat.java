package strat;

public class InverseTitForTat extends Strategy {
	public InverseTitForTat() {
		super();
	}

	/**
	 * Begins by cooperating and plays the move the opponent did last move
	 */
	protected int generateMove() {
		int rounds = info.getRoundsPlayed();
		if (rounds == 0) {
			return 1;
		}
		int[] oppMoves = info.getOppMoves();
		int oppMove = oppMoves[info.getRoundsPlayed() - 1];
		if (oppMove == 0) {
		    return 1;
		} else {
		    return 0;
		}
	}
	
	public void reinit() {
		/* None needed */
	}
	
	protected String getName() {
		return "Inverse Tit for Tat";
	}
	
	protected String getAuthor() {
		return null;
	}
}
