/**
* This class contains get methods to access information about the surroundings and other agents for strategies
* Contains set methods used to update info by Game
* 
* @author  Sahee Thao
* @version 1.0.0
* @since   2020-11-18
*/
package game;

import java.util.Arrays;

public class Info {	
	
	private int totalSelfScore; // Total self score
	private int totalOppScore; // Total opponent score
	private int totalR; // Payoff R - Total
	private int totalS; // Payoff S - Total
	private int totalT; // Payoff T - Total
	private int totalP; // Payoff P - Total
	
	private int selfScore; // Self score
	private int oppScore; // Opp score
	private int r; // Payoff R - Total
	private int s; // Payoff S - Total
	private int t; // Payoff T - Total
	private int p; // Payoff P - Total

	private int[] selfMoves; // History of your moves so far
	private int[] oppMoves; // History of you're opponent's moves so far
	
	private int roundsPlayed; // The number of rounds played
	private int opponentsPlayed; // The number of opponents played
	
	private double meanSelfScore;
	private double meanOppScore;
	private double meanScoreDiff;
	private double meanR;
	private double meanS;
	private double meanT;
	private double meanP;
	
	private int totalSelfScorePlace = 0;
	private int totalOppScorePlace = 0;
	private int totalScoreDiffPlace = 0;
	private int totalRPlace = 0;
	private int totalSPlace = 0;
	private int totalTPlace = 0;
	private int totalPPlace = 0;
	
	private double meanSelfScorePlace = 0;
	private double meanOppScorePlace = 0;
	private double meanScoreDiffPlace = 0;
	private double meanRPlace = 0;
	private double meanSPlace = 0;
	private double meanTPlace = 0;
	private double meanPPlace = 0;
	
	/**
	 * Constructor
	 * Calls reset()
	 */
	public Info() {
		reset();
	}
	
	/**
	 * Update attributes based on the moves chosen
	 * @param selfMove, move chosen by the (parent) strategy
	 * @param oppMove, move chosen by the opposing strategy
	 */
	void updateRound(int selfMove, int oppMove) {
		this.selfMoves[this.roundsPlayed] = selfMove;
		this.oppMoves[this.roundsPlayed] = oppMove;
		this.roundsPlayed += 1;
		if (selfMove == 0) {
			// Cooperate
			if (oppMove == 0) {
				// Cooperate
				this.selfScore += 3;
				this.oppScore += 3;
				this.r += 1;
			} else {
				// Defect
				// No self score
				this.s += 1;
				this.oppScore += 5;
			}
		} else {
			// Defect
			if (oppMove == 0) {
				// Cooperate
				this.selfScore += 5;
				// No opp score
				this.t += 1;
			} else {
				// Defect
				this.selfScore += 1;
				this.oppScore += 1;
				this.p += 1;
			}
		}
		
	}
	
	void totalReset() {
		selfScore = 0;
		oppScore = 0;
		selfMoves = new int[150];
		Arrays.fill(selfMoves, -1); // Set to -1 (not played)
		oppMoves = new int[150];
		Arrays.fill(oppMoves, -1); // Set to -1 (not played)
		roundsPlayed = 0;
		
		r = 0;
		s = 0;
		t = 0;
		p = 0;
		
		totalSelfScore = 0;
		totalOppScore = 0;
		totalR = 0;
		totalS = 0;
		totalT = 0;
		totalP = 0;
		opponentsPlayed = 0;
		
		totalSelfScorePlace = 0;
		totalOppScorePlace = 0;
		totalScoreDiffPlace = 0;
		totalRPlace = 0;
		totalSPlace = 0;
		totalTPlace = 0;
		totalPPlace = 0;
		
		meanSelfScorePlace = 0;
		meanOppScorePlace = 0;
		meanScoreDiffPlace = 0;
		meanRPlace = 0;
		meanSPlace = 0;
		meanTPlace = 0;
		meanPPlace = 0;
	}
	
	/**
	 * Reset all attributes to original values and updates totals
	 */
	void reset() {
		totalSelfScore += selfScore;
		totalOppScore += oppScore;
		totalR += r;
		totalS += s;
		totalT += t;
		totalP += p;
		opponentsPlayed += 1;
		
		selfScore = 0;
		oppScore = 0;
		selfMoves = new int[150];
		Arrays.fill(selfMoves, -1); // Set to -1 (not played)
		oppMoves = new int[150];
		Arrays.fill(oppMoves, -1); // Set to -1 (not played)
		roundsPlayed = 0;
		
		r = 0;
		s = 0;
		t = 0;
		p = 0;
	}
	
	/**
	 * Calculate means
	 */
	void calculateMeans() {
		meanSelfScore = ((double) totalSelfScore / opponentsPlayed);
		meanOppScore = ((double) totalOppScore / opponentsPlayed);
		meanScoreDiff = Math.abs(meanOppScore - meanSelfScore);
		meanR = ((double) totalR / opponentsPlayed);
		meanS = ((double) totalS / opponentsPlayed);
		meanT = ((double) totalT / opponentsPlayed);
		meanP = ((double) totalP / opponentsPlayed);
		
		meanSelfScorePlace = ((double) totalSelfScorePlace / opponentsPlayed);
		meanOppScorePlace = ((double) totalOppScorePlace / opponentsPlayed);
		meanScoreDiffPlace = ((double) totalScoreDiffPlace / opponentsPlayed);
		meanRPlace = ((double) totalRPlace / opponentsPlayed);
		meanSPlace = ((double) totalSPlace / opponentsPlayed);
		meanTPlace = ((double) totalTPlace / opponentsPlayed);
		meanPPlace = ((double) totalPPlace / opponentsPlayed);
	}
	
	/* Getters */
	public int getSelfScore() {
		return this.selfScore;
	}

	public int getOppScore() {
		return this.oppScore;
	}
	
	/**
	 * Get an array of moves you have played. Values of -1 indicate moves that haven't been played.
	 * Contains values of 0 or 1 up to index roundsPlayed
	 * @return an array (a copy) of self moves.
	 */
	public int[] getSelfMoves() {
		int[] newMoves = new int[selfMoves.length];
		for (int i = 0; i < selfMoves.length; i += 1) {
			newMoves[i] = selfMoves[i];
		}
		return newMoves;
	}
	
	/**
	 * Get an array of moves your opponent has played. Values of -1 indicate moves that haven't been played.
	 * Contains values of 0 or 1 up to index roundsPlayed
	 * @return an array (a copy) of opponent moves.
	 */
	public int[] getOppMoves() {
		int[] newMoves = new int[oppMoves.length];
		for (int i = 0; i < oppMoves.length; i += 1) {
			newMoves[i] = oppMoves[i];
		}
		return newMoves;
	}
	
	/**
	 * The amount of payoffs R received so far
	 * @return r
	 */
	public int getR() {
		return this.r;
	}
	
	/**
	 * The amount of payoffs S received so far
	 * @return s
	 */
	public int getS() {
		return this.s;
	}
	
	/**
	 * The amount of payoffs T received so far
	 * @return t
	 */
	public int getT() {
		return this.t;
	}
	
	/**
	 * The amount of payoffs P received so far
	 * @return p
	 */
	public int getP() {
		return this.p;
	}
	
	int getTotalSelfScore() {
		return totalSelfScore;
	}
	
	int getTotalOppScore() {
		return totalOppScore;
	}
	
	public int getTotalR() {
		return totalR;
	}

	int getTotalS() {
		return totalS;
	}

	int getTotalT() {
		return totalT;
	}

	public int getTotalP() {
		return totalP;
	}
	
	public int getRoundsPlayed() {
		return roundsPlayed;
	}

	public int getOpponentsPlayed() {
		return opponentsPlayed;
	}

	double getMeanSelfScore() {
		return meanSelfScore;
	}

	double getMeanOppScore() {
		return meanOppScore;
	}

	double getMeanScoreDiff() {
		return meanScoreDiff;
	}

	double getMeanR() {
		return meanR;
	}

	double getMeanS() {
		return meanS;
	}

	double getMeanT() {
		return meanT;
	}

	double getMeanP() {
		return meanP;
	}
	
	double getMeanSelfScorePlace() {
		return meanSelfScorePlace;
	}

	void setMeanSelfScorePlace(double meanSelfScorePlace) {
		this.meanSelfScorePlace = meanSelfScorePlace;
	}

	double getMeanOppScorePlace() {
		return meanOppScorePlace;
	}

	void setMeanOppScorePlace(double meanOppScorePlace) {
		this.meanOppScorePlace = meanOppScorePlace;
	}

	double getMeanScoreDiffPlace() {
		return meanScoreDiffPlace;
	}

	void setMeanScoreDiffPlace(double meanScoreDiffPlace) {
		this.meanScoreDiffPlace = meanScoreDiffPlace;
	}

	double getMeanRPlace() {
		return meanRPlace;
	}

	void setMeanRPlace(double meanRPlace) {
		this.meanRPlace = meanRPlace;
	}

	double getMeanSPlace() {
		return meanSPlace;
	}

	void setMeanSPlace(double meanSPlace) {
		this.meanSPlace = meanSPlace;
	}

	double getMeanTPlace() {
		return meanTPlace;
	}

	void setMeanTPlace(double meanTPlace) {
		this.meanTPlace = meanTPlace;
	}

	double getMeanPPlace() {
		return meanPPlace;
	}

	void setMeanPPlace(double meanPPlace) {
		this.meanPPlace = meanPPlace;
	}

	public String toString() {
		calculateMeans();
		String out =  "";
		out += "Mean Self Score: " + meanSelfScore + "\n";
		out += "Mean Opp Score: " + meanOppScore + "\n";
		out += "Mean Score Difference: " + meanScoreDiff + "\n";
		out += "Mean R: " + meanR + "\n";
		out += "Mean S: " + meanS + "\n";
		out += "Mean T: " + meanT + "\n";
		out += "Mean P: " + meanP + "\n";
		return out;
	}
	
	public String toStringCur() {
		String out =  "";
		out += "Self Score: " + selfScore + "\n";
		out += "Opp Score: " + oppScore + "\n";
		out += "Score Difference: " + Math.abs(selfScore - oppScore) + "\n";
		out += "R: " + r + "\n";
		out += "S: " + s + "\n";
		out += "T: " + t + "\n";
		out += "P: " + p + "\n";
		return out;		
	}
	
}
