package strat;

import java.util.Random;

public class CopierShifted extends Strategy {
	private Random r;
	private int[] moves;
	private int pos;
	public CopierShifted() {
		super();
		r = new Random();
		moves = new int[150];
		pos = 0;
		for (int i = 0; i < 150; i++) {
            moves[i] = r.nextInt(2);
        }
	}
	
	public void reinit() {
		/* None needed */
	    pos = 0;
	}

	/**
	 * Randomly chooses to cooperate or defect
	 */
	protected int generateMove() {
	    int rounds = info.getRoundsPlayed();
        if (rounds == 0) {
            return 0;
        }
	    int[] oppMoves = info.getOppMoves();
        int oppMove = oppMoves[info.getRoundsPlayed() - 1];
        moves[info.getRoundsPlayed() - 1] = oppMove;
        int m = moves[pos];
        pos += 1;
		return m;
	}
	
	protected String getName() {
		return "Copier Shifted";
	}
	
	protected String getAuthor() {
		return "Sahee";
	}

}
