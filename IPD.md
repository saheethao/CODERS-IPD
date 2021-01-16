# The Iterated Prisoner's Dilemma
Below is a walkthrough on the prisoner's dilemma and the iterated prisoner's dilemma.

## The Prisoner's Dilemma
The [prisoner's dilemma](https://en.wikipedia.org/wiki/Prisoner%27s_dilemma) is a canonical game analyzed in game theory. Two players play against each other, simultaneously choosing two actions, cooperate (C) or defect (D).

Below are the payoffs for the individual given what action P1 and P2 take. For example if P1 chooses **D** and P2 choose **C**, the outcome is (5,0). P1 gains a reward of 5 and P2 gains a reward 0.

P1\P2 | C | D
:-------: | :------------: | :-------------:
**C** | (3,3) | (0,5)
**D** | (5,0) | (1,1)

The Nash equilibrium of is mutual defection - that is both players choose **D**.

### In Generalized Form
Below are the same payoffs in a generalized form. The payoffs are denoted as R (reward), T (temptation), S (sucker), P (penalty).

P1\P2 | C | D
:-------: | :------------: | :-------------:
**C** | (R,R) | (S,T)
**D** | (T,S) | (P,P)

The following condition must hold for the payoffs: T>R>P>S

The standard payoff quantities are:

Payoff | Quantity
:-------: | :------------: 
R | 3
S | 0
T | 5
P | 1

## The Iterated Prisoner's Dilemma
The iterated prisoner's dilemma (IPD) is simply the prisoner's dilemma played over multiple rounds. For example, this IPD game will consist of 5 rounds.
Round | P1 Action | P2 Action | P1 Payoff | P2 Payoff
:------------: | :-------------: | :-------------: | :-------------: | :-------------:
1 | C | D | S | T 
2 | D | D | P | P
3 | C | C | R | R
4 | C | C | R | R
5 | C | C | R | R

Using standard payoff quantities, the resulting score is P1: 10, P2: 15.

### Axelrod's Tournament
In Robert Axelrod's *The Evolution of Cooperation (1984)*, he invited academics from all over the world to participate in a tournament of computer strategies competing against each other in the IPD. Hundreds of strategies played against each other in a round-robin tournament. Below are some examples of submitted strategies and how they play:

Name | Strategy
:-------: | :------------: 
Always Cooperate | This strategy always chooses the action C
Always Defect | This strategy always chooses the action D
Random | This strategy randomly chooses action C or action D with equal chance
Grim | This strategy always chooses action C unless the opponent has chosen action D. If that is the case, they always choose action D
Majority | This strategy chooses action C on the first round. On all subsequent rounds, this strategy examines the history of the other player's actions, counting the total number of Cs and Ds by the other player. If the other player's Ds outnumber their Cs, this strategy will choose action C; otherwise this strategy will choose action D.
Tit-for-Tat | This strategy chooses action C on the first round. On all subsequent rounds, his strategy plays the action the other player choose in the previous round.

Tit-for-Tat was discovered to perform the best in the tournament.

### Importance
A large reason why this game has importance is due to the implications the 'solution' has on the real world. The prisoner's dilemma can extend beyond a game as a model to how we can approach the world. The prisoner's dilemma has been used by some political scientists as a base framework for approaching events such as the Cold War - hence the variant of the IPD, the [peace war game](https://en.wikipedia.org/wiki/Peace_war_game). Reimagining P1 and P2 as nuclear capable countries and the action of C as nuclear deescalation and D as nuclear mobilization, it is clear how the solutions of this game may inform ideas on approaching foreign policy.

## Creating a Strategy
This repository contains code to run your own tournament and implement your own strategy. Please refer the [README.MD](https://github.com/saheethao/CODERS-IPD/blob/main/README.MD) for more details.

# Explore IPD
- This [interactive web page](https://ncase.me/trust/) offers a great example of IPD and its implications.
- This [blog](https://webupon.com/blog/iterated-prisoners-dilemma-game/) introduces the game.
- An [article](https://plato.stanford.edu/entries/prisoner-dilemma/) from the Stanford Encyclopedia of Philosophy
- The Wikipedia [article](https://en.wikipedia.org/wiki/Prisoner%27s_dilemma) about IPD