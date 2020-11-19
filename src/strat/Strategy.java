package strat;
import game.Info;

public abstract class Strategy {
	
	protected Info info;
	private int id;
	
	public Strategy() {
		this.info = new Info();
		this.id = -1;
	}
	
	/**
	 * Method to generate a 0 (cooperate) or 1 (defect)
	 * @return 0 or 1
	 */
	protected abstract int generateMove();
	
	/**
	 * 
	 * @return the name of the strategy
	 */
	protected abstract String getName();
	
	/**
	 * May return null which no author name
	 * @return the authors name
	 */
	protected abstract String getAuthor();
	
	/**
	 * Used after each game. If the strategy has a memory than it needs to be reset or
	 * reinitialized
	 */
	public abstract void reinit();
	
	final public int getMove() {
		int move = generateMove();
		if (move < 0) {
			return 0;
		} else if (move > 1) {
			return 1;
		}
		return move;
	}
	
	public String toString() {
		String str = this.getName();
		if (this.id != -1) {
			str += " " + this.id;
		}
		if (this.getAuthor() != null) {
			str += " (" + this.getAuthor() + ")"; 
		}
		return str;
	}
	
	/* Setters and Getters */
	
	public int getId() {
		return this.id;
	}
	
	final public void setId(int id) {
		this.id = id;
	}
	
	final public void setInfo(Info info) {
		this.info = info;
	}
	
	final public Info getInfo() {
		return info;
	}

}
