# How to run
Either import this into Eclipse or compile Game.java.

# How to make a strategy
The strategy must extend Strategy.java and must be in strat.

Below is a template for a strategy:
```java
package strat;

public class Cooperate extends Strategy {
	
	public Cooperate() {
		super();
	}

	/**
	 * Unconditionally cooperates
	 */
	protected int generateMove() {
		return 0;
	}
	
	public void reinit() {
		/* None needed */
	}
	
	protected String getName() {
		return "Cooperate";
	}
	
	protected String getAuthor() {
		return null;
	}
}
```



