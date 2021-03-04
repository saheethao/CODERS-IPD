package strat;

public class LenientGrim extends Strategy {
	
	private int betrayed;
	public LenientGrim() {
		super();
		betrayed = 0;
	}

	/**
	 * Will cooperate unless the opponent defects. Then it will defect only
	 */
	protected int generateMove() {
		int rounds = info.getRoundsPlayed();
		if (rounds == 0) {
			return 0;
		}
		if (betrayed > 1) {
			return 1;
		}
		int[] oppMoves = info.getOppMoves();
		int oppMove = oppMoves[info.getRoundsPlayed() - 1];
		if (oppMove == 1) {
			betrayed += 1;
			return 1;
		}
		return 0;
	}
	
	public void reinit() {
		/* Set betrayed to false */
		betrayed = 0;
	}
	
	protected String getName() {
		return "Lenient Grim";
	}
	
	protected String getAuthor() {
		return null;
	}
}
