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

# Info.java
This class contains get methods.

Strategy has an Info object that keeps track of the data relevant to its parent strategy.

## public int getSelfScore()
This method returns the current score of the strategy.

## public int getOppScore()
This method returns the current score of the opponent strategy.

## public int[] getSelfMoves()
This method returns an array of moves the strategy has played. Values of -1 indicate moves that haven't been played. Contains values of 0 or 1 up to index roundsPlayed.

## public int[] getOppMoves()
This method returns an array of moves your opponent has played. Values of -1 indicate moves that haven't been played. Contains values of 0 or 1 up to index roundsPlayed.

## public int getR()
This method returns the amount of times the strategy has recieved the payoff R (3)

## public int getS()
This method returns the amount of times the strategy has recieved the payoff S (0)

## public int getT()
This method returns the amount of times the strategy has recieved the payoff T (5)

## public int getP()
This method returns the amount of times the strategy has recieved the payoff P (1)

## public int getRoundsPlayed()
This method returns the amount of rounds played.

# How do I get the last move my opponent played?
```java
/* Somewhere in child strategy */
int rounds = info.getRoundsPlayed();
int[] oppMoves = info.getOppMoves();
int oppMove = oppMoves[info.getRoundsPlayed() - 1]; // This will be the opponent's last move. // Be aware of index out of bounds exceptions
```
