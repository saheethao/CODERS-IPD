package strat;

import java.util.Arrays;

public class Shifter extends Strategy {
	public Shifter() {
		super();
	}
	// CCCC CCCD CCDC CCDD CDCC CDCD CDDC CDDD DCCC DCCD DCDC DCDD DDCC DDCD DDDC DDDD
	// R R  R S  R T  R P  S R  S S  S T  S P  T R  T S  T T  T P  P R  P S  P T  P P
	// 0    1    0    0    0    1    0    1    0    1    1    0    0    1    1    1
	private double[] vote = { 1, 0.1, 1, 0.9, 0.3, 0.03, 0.90, 0.12, 0.9, 0.5, 1.0, 0.65, 0.45, 0.1, 0.85, 0.20 };
	private int[] hist = { 0, 0, 1, 1 };
	private int lastPos = -1;
	
	private int binToInt(int[] binary) {
        int result = 0;
        for(int i=binary.length - 1; i>=0; i--)
            if(binary[i]== 1)
                result += Math.pow(2, (binary.length-i - 1));
        return result;
    }
	
	/**
	 * Begins by cooperating and plays the move the opponent did last move
	 */
	protected int generateMove() {
	    if (info.getRoundsPlayed() > 0) {
            hist[2] = info.getSelfMoves()[info.getRoundsPlayed() - 1];
            hist[3] = info.getOppMoves()[info.getRoundsPlayed() - 1];
            if (info.getOppScore() + 50 < info.getSelfScore()) {
                // If we are winning, don't do anything
            } else {
             // If we are close
                if (info.getOppScore() > info.getSelfScore() + 5) {
                    // Losing by 5
                    vote[lastPos] -= 0.01;
                    if (vote[lastPos] < 0) {
                        vote[lastPos] = 0;
                    }
                } else if (info.getOppScore() < info.getSelfScore() + 5) {
                    // Winning by 5
                    vote[lastPos] += 0.01;
                    if (vote[lastPos] > 1) {
                        vote[lastPos] = 1;
                    }
                } else {
                    // Within 4 points
                }
            }
        }

	    int pos = binToInt(hist);
	    int move = 0;

	    if (! (Math.random() < vote[pos]) ) {
	        move = 1;
	    }
	    lastPos = pos;
        hist[0] = hist[2];
        hist[1] = hist[3];
        return move;
	}
	
	public void reinit() {
		/* None needed */
	    lastPos = -1;
	    hist[0] = 0;
        hist[1] = 0;
        hist[2] = 1;
        hist[3] = 1;
	}
	
	protected String getName() {
		return "Shifter";
	}
	
	protected String getAuthor() {
		return "Sahee";
	}
}
