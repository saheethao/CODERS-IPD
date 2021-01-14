package strat;

public class MittalAndDeb extends Strategy {
	private static String str = "DDDDDDCCCDDCDDDDDDCDDDDCDCCDDCDDDDCDCCCDDCDCDCDCDDCCDDDDCDDDDDCCCDCCDD";
	private int lookup[] = {
			1, 1, 1, 1, 1, 1, 0, 0, 0, 1,
			1, 0, 1, 1, 1, 1, 1, 1, 0, 1,
			1, 1, 1, 0, 1, 0, 0, 1, 1, 0,
			1, 1, 1, 1, 0, 1, 0, 0, 0, 1,
			1, 0, 1, 0, 1, 0, 1, 0, 1, 1,
			0, 0, 1, 1, 1, 1, 0, 1, 1, 1,
			1, 1, 0, 0
			};
	private int hist[] = {0, 1, 0, 0, 1, 1};
	
	private int binToInt(int[] binary) {
	    int result = 0;
	    for(int i=binary.length - 1; i>=0; i--)
	        if(binary[i]== 1)
	            result += Math.pow(2, (binary.length-i - 1));
	    return result;
	}
	
	public void reinit() {
		hist[0] = 0;
		hist[1] = 1;
		hist[2] = 0;
		hist[3] = 0;
		hist[4] = 1;
		hist[5] = 1;
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
		return "Mittal & Deb";
	}
	
	protected String getAuthor() {
		return "Mittal & Deb";
	}
	
	public static void main(String[] args) {
//		int[] arr = new int[70];
//		for (int i = 0; i < str.length(); i++) {
//			char c = str.charAt(i);
//			if (c == 'C') {
//				arr[i] = 0;
//			} else {
//				arr[i] = 1;
//			}
//		}
//		
//		for (int g : arr) {
//			System.out.print(g + ", ");
//		}
	}
}
