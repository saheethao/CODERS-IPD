package strat;

public class EliseB extends Strategy {
	private String str = "01000101011011000110100101110011011001010010000001000010";
	private int lookup[];
	private int pos;
	
	public EliseB() {
		super();
		lookup = new int[str.length()];
		for (int i = 0; i < str.length(); i++) {
			lookup[i] = str.charAt(i) - '0';
		}
		pos = 0;
	}
	
	public void reinit() {
		pos = 0;
	}
	
	/**
	 * Looks through the binary ASCII encoding of "Elise B"
	 */
	protected int generateMove() {
		int move = lookup[pos];
		pos++;
		pos %= lookup.length;
		return move;
	}
	
	protected String getName() {
		return "Elise B";
	}
	
	protected String getAuthor() {
		return "Elise B";
	}
}
