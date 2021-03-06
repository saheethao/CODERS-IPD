package strat;

public class Grim extends Strategy {
	
	private boolean betrayed;
	public Grim() {
		super();
		betrayed = false;
	}

	/**
	 * Will cooperate unless the opponent defects. Then it will defect only
	 */
	protected int generateMove() {
		int rounds = info.getRoundsPlayed();
		if (rounds == 0) {
			return 0;
		}
		if (betrayed) {
			return 1;
		}
		int[] oppMoves = info.getOppMoves();
		int oppMove = oppMoves[info.getRoundsPlayed() - 1];
		if (oppMove == 1) {
			betrayed = true;
			return 1;
		}
		return 0;
	}
	
	public void reinit() {
		/* Set betrayed to false */
		betrayed = false;
	}
	
	protected String getName() {
		return "Grim";
	}
	
	protected String getAuthor() {
		return null;
	}
}
