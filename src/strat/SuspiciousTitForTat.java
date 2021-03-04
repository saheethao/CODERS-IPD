package strat;

public class SuspiciousTitForTat extends Strategy {
	public SuspiciousTitForTat() {
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
		return oppMove;
	}
	
	public void reinit() {
		/* None needed */
	}
	
	protected String getName() {
		return "Suspicious Tit for Tat";
	}
	
	protected String getAuthor() {
		return null;
	}
}
