package strat;

public class EvilEliseBGA extends Strategy {
	private String str = "0100010101101100011010010111001101100101001000000100001001000101011011000110100101110011011001010010000001000010";
	private int lookup[];
	private int hist[];
	
	public EvilEliseBGA() {
		lookup = new int[64];
		hist = new int[6];
		
		for (int i = 0; i < lookup.length; i++) {
			lookup[i] = '1' - str.charAt(i);
		}
		hist[0] = '1' - str.charAt(64);
		hist[0] = '1' - str.charAt(65);
		hist[0] = '1' - str.charAt(66);
		hist[0] = '1' - str.charAt(67);
		hist[0] = '1' - str.charAt(68);
		hist[0] = '1' - str.charAt(69);
	}
	
	private int binToInt(int[] binary) {
	    int result = 0;
	    for(int i=binary.length - 1; i>=0; i--)
	        if(binary[i]== 1)
	            result += Math.pow(2, (binary.length-i - 1));
	    return result;
	}
	
	public void reinit() {
		hist[0] = '1' - str.charAt(64);
		hist[0] = '1' - str.charAt(65);
		hist[0] = '1' - str.charAt(66);
		hist[0] = '1' - str.charAt(67);
		hist[0] = '1' - str.charAt(68);
		hist[0] = '1' - str.charAt(69);
	}
	
	/**
	 * Begins by cooperating and cooperates as long as 
	 * the number of times the opponent has cooperated 
	 * is greater that or equal to the number of times 
	 * it has defected. Otherwise defects
	 */
	protected int generateMove() {
		if (info.getRoundsPlayed() > 0) {
			hist[4] = info.getSelfMoves()[info.getRoundsPlayed() - 1];
			hist[5] = info.getOppMoves()[info.getRoundsPlayed() - 1];
		}
		int pos = binToInt(hist);
		int move = lookup[pos];
		hist[0] = hist[2];
		hist[1] = hist[3];
		hist[2] = hist[4];
		hist[3] = hist[5];
		return move;
	}
	
	protected String getName() {
		return "Evil Elise B GA";
	}
	
	protected String getAuthor() {
		return "Elise B";
	}
}
