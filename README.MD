# How to run
Either import this into Eclipse or compile Game.java.
The main method is in Game.java

# How to make a strategy
The strategy must extend Strategy.java and must be in strat.

Below is a template for a strategy:
```java
package strat;

public class MyStrategy extends Strategy {
	/*
	 * Global variables here
	 */
	public Cooperate() {
		super();
	}

	/**
	 * Return 0 or 1. Make the strategy here.
	 */
	protected int generateMove() {
		return 0;
	}
	
	/**
	 *
	 * Some strategies need to reinitialize. This is called before each match between strategies.
	 */
	public void reinit() {
		/* reinitialize global variables */
	}
	
	/**
	 * Returns the name of your strategy. Maybe its called "70% chance to cooperate" or "Elizabeth". Your choice.
	 */
	protected String getName() {
		return "MyStrategy";
	}
	
	/**
	 * Return your name here. You can choose to return null as well.
	 */
	protected String getAuthor() {
		return "Sahee Thao";
	}
}
```



