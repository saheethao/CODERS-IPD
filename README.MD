# CODERS IPD
CODERS will be running a tournament playing the iterated prisoner's dilemma (IPD).

## Introduction
Please read [IPD.md](https://github.com/saheethao/CODERS-IPD/blob/main/IPD.md) discussing the iterated prisoner's dilemma (IPD).

## How to run
Either import this into Eclipse or compile Game.java.

`config.properties` can be modified to edit the number of trials and to exclude certain strategies in the tournament.

## Tournament Format
After all strategies are submitted, the tournament will run 1000 trials with all strategies.
Each strategy will be evaluated on the categories displayed in the tournament results.
Placements of 1st, 2nd, and 3rd exist for the maximizing and minimizing each category.
For example, strategies will place for having the maximum self score or the minimal self score.

## Tournament Results
After running Game, the following output should appear:

```
Strategies found: [] // All strategies found in Stat
Exlcuding: []        // Prints strategies excluded in config.properties & Strategy

--- Round Robin Tournament ---

--- Self Score ---
... // Prints the strategy and their mean self score in descending order

--- Opp Score ---
... // Prints the strategy and their mean opponent's score in descending order

--- Score Diff ---
... // Prints the strategy and the difference between the mean self score and mean opponent's score in descending order

--- Score Abs Diff ---
... // Prints the strategy and the absolute value of the above in descending order


--- Payoff R ---
... // Prints the strategy and the mean amount of payoff R it recieved in a game in descending order

--- Payoff S ---
... // Prints the strategy and the mean amount of payoff S it recieved in a game in descending order

--- Payoff T ---
... // Prints the strategy and the mean amount of payoff T it recieved in a game in descending order

--- Payoff P ---
... // Prints the strategy and the mean amount of payoff P it recieved in a game in descending order

```

## Directory Structure
├── bin
├── src
   ├── game    This directory contains files used for the logic of IPD and the tournament.
   ├── strat   This directory contains java files which are the strategies. ADD YOUR STRATEGY HERE.
├── config.properties  Edit this file to change properties of the tournament
├── IPD.md             Displays information about IPD
├── LICNESE
└── readme.md          This file

## How to make a strategy
The strategy must extend Strategy.java and must be in strat.

Below is a template for a strategy:

```java
package strat;

public class MyStrategy extends Strategy {
	/*
	 * Global variables here
	 */
    
    /**
     * @constructor intialize your global variables
     */
	public Cooperate() {
		super();
	}

	/**
     * Make the strategy here
	 * @return 0 or 1
	 */
	protected int generateMove() {
		return 0;
	}
	
	/**
	 * Some strategies need to reinitialize their global variables. This is called before each match between strategies.
	 */
	public void reinit() {
		/* reinitialize global variables */
	}
	
	/**
	 * @return the name of your strategy. Maybe its called "70% chance to cooperate" or "Elizabeth". Your choice.
	 */
	protected String getName() {
		return "MyStrategy";
	}
	
	/**
	 * @return your name. You can choose to return null
	 */
	protected String getAuthor() {
		return "Sahee Thao";
	}
}
```

# Info.java
This class contains get methods. You can check the source code [here](https://github.com/saheethao/CODERS-IPD/blob/main/src/game/Info.java).

Each Strategy has an Info object that keeps track of the data relevant to its parent strategy.

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
/* Somewhere in generateMove() */
int rounds = info.getRoundsPlayed();
int[] oppMoves = info.getOppMoves();
// Be aware of index out of bounds exceptions
int oppMove = oppMoves[info.getRoundsPlayed() - 1]; // This will be the opponent's last move. 
```

# Feedback
You can directly contact `@saheemax` on the discord channel or as a direct message if you have any questions or suggestions.