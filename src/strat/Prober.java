package strat;

public class Prober extends Strategy {
	private boolean defectFlag;
	public Prober() {
		super();
		defectFlag = false;
	}

	/**
	 * Plays the sequence d,c,c then always defects if its opponent has cooperated in the moves 1 and 2.
     * Plays as Tit for Tat in other cases
	 */
	protected int generateMove() {
		int rounds = info.getRoundsPlayed();
		if (rounds == 0) {
			return 1;
		} else if (rounds == 1) {
			return 0;
		} else if (rounds == 2) {
			return 0;
		} else if (rounds == 3) {
			int[] oppMoves = info.getOppMoves();
			int oppMove1 = oppMoves[1];
			int oppMove2 = oppMoves[2];

			if (oppMove1 == 0 && oppMove2 == 0) {
				defectFlag = true;
				return 1;
			}
		}
		if (defectFlag) {
			return 1;
		}
		
		int[] oppMoves = info.getOppMoves();
		int oppMove = oppMoves[info.getRoundsPlayed() - 1];
		return oppMove;
	}
	
	public void reinit() {
		/* Reset defect flag to false */
		defectFlag = false;
	}
	
	protected String getName() {
		return "Prober";
	}
	
	protected String getAuthor() {
		return null;
	}
}
