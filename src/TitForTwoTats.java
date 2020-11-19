package strat;

public class TitForTwoTats extends Strategy {
	public TitForTwoTats() {
		super();
	}

	public void reinit() {
		/* None needed */
	}
	
	/**
	 * Begins by cooperating and plays the move the opponent did last move
	 */
	protected int generateMove() {
		int rounds = info.getRoundsPlayed();
		if (rounds < 2) {
			return 0;
		}
		int[] oppMoves = info.getOppMoves();
		int oppMove1 = oppMoves[info.getRoundsPlayed() - 1];
		int oppMove2 = oppMoves[info.getRoundsPlayed() - 2];

		if (oppMove1 == 1 && oppMove2 == 1) {
			return 1;
		}
		return 0;
	}
	
	protected String getName() {
		return "Tit for Two Tats";
	}
	
	protected String getAuthor() {
		return null;
	}
}
