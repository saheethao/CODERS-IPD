package game;

import strat.Strategy;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.io.File;

public class Game {
	
	private ArrayList<Strategy> strategies;
	
	public Game() {
		ArrayList<String> results = new ArrayList<String>();
		
		String[] ignore = {"Strategy.java"};

		List<String> ignoreList = Arrays.asList(ignore);
		
		File[] files = new File("src/strat").listFiles();
		for (File file : files) {
		    if (file.isFile() && !ignoreList.contains(file.getName())) {
		    	String name = file.getName();
		        results.add(name.substring(0, name.length() - 5)); // Remove ".java"
		    }
		}
		System.out.println("Strategies found: " + results);
		strategies = new ArrayList<Strategy>();
		
		int i = 0;
		for (String stratClassName : results) {
			Strategy s = null;
			
			try {
				s = (Strategy) Class.forName("strat." + stratClassName).newInstance();
				s.setId(-1);
				strategies.add(s);
				
				s = (Strategy) Class.forName("strat." + stratClassName).newInstance();
				s.setId(1);
				//strategies.add(s);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			i++;
		}
		Collections.shuffle(strategies);
		System.out.println("Round Robin Tournament");
		System.out.println();
		for (i = 0; i < 1; i++) {
			roundRobin(strategies);
		}
		for (Strategy s : strategies) {
			s.getInfo().calculateMeans();
		}
		
		/* Self Score */
		strategies.sort((a, b) -> (int)Math.round(b.getInfo().getMeanSelfScore() - a.getInfo().getMeanSelfScore()));
		System.out.println("Max Self Score");
		for (Strategy s : strategies) {
			System.out.printf("%6.2f %s\n", s.getInfo().getMeanSelfScore(), s);
		}
		System.out.println();
		
		strategies.sort((a, b) -> (int)Math.round(a.getInfo().getMeanSelfScore() - b.getInfo().getMeanSelfScore()));
		System.out.println("Min Self Score");
		for (Strategy s : strategies) {
			System.out.printf("%6.2f %s\n", s.getInfo().getMeanSelfScore(), s);
		}
		System.out.println();
		
		/* Opp Score */
		strategies.sort((a, b) -> (int)Math.round(b.getInfo().getMeanOppScore() - a.getInfo().getMeanOppScore()));
		System.out.println("Max Opp Score");
		for (Strategy s : strategies) {
			System.out.printf("%6.2f %s\n", s.getInfo().getMeanOppScore(), s);
		}
		System.out.println();
		
		strategies.sort((a, b) -> (int)Math.round(a.getInfo().getMeanOppScore() - b.getInfo().getMeanOppScore()));
		System.out.println("Min Opp Score");
		for (Strategy s : strategies) {
			System.out.printf("%6.2f %s\n", s.getInfo().getMeanOppScore(), s);
		}
		System.out.println();
		
		/* Score Diff */
		strategies.sort((a, b) -> (int)Math.round(b.getInfo().getMeanScoreDiff() - a.getInfo().getMeanScoreDiff()));
		System.out.println("Max Score Diff");
		for (Strategy s : strategies) {
			System.out.printf("%6.2f %s\n", s.getInfo().getMeanScoreDiff(), s);
		}
		System.out.println();
		
		strategies.sort((a, b) -> (int)Math.round(a.getInfo().getMeanScoreDiff() - b.getInfo().getMeanScoreDiff()));
		System.out.println("Min Score Diff");
		for (Strategy s : strategies) {
			System.out.printf("%6.2f %s\n", s.getInfo().getMeanScoreDiff(), s);
		}
		System.out.println();
		
		/* Max R */
		strategies.sort((a, b) -> (int)Math.round(b.getInfo().getMeanR() - a.getInfo().getMeanR()));
		System.out.println("Max R");
		for (Strategy s : strategies) {
			System.out.printf("%6.2f %s\n", s.getInfo().getMeanR(), s);
		}
		System.out.println();
		
		/* Max S */
		strategies.sort((a, b) -> (int)Math.round(b.getInfo().getMeanS() - a.getInfo().getMeanS()));
		System.out.println("Max S");
		for (Strategy s : strategies) {
			System.out.printf("%6.2f %s\n", s.getInfo().getMeanS(), s);
		}
		System.out.println();
		
		/* Max T */
		strategies.sort((a, b) -> (int)Math.round(b.getInfo().getMeanT() - a.getInfo().getMeanT()));
		System.out.println("Max T");
		for (Strategy s : strategies) {
			System.out.printf("%6.2f %s\n", s.getInfo().getMeanT(), s);
		}
		System.out.println();
		
		/* Max P */
		strategies.sort((a, b) -> (int)Math.round(b.getInfo().getMeanP() - a.getInfo().getMeanP()));
		System.out.println("Max P");
		for (Strategy s : strategies) {
			System.out.printf("%6.2f %s\n", s.getInfo().getMeanP(), s);
		}
		System.out.println();
		
		for (Strategy s : strategies) {
			s.getInfo().totalReset();
		}
		
		/* Elimination TODO */
		
		
	}
	
	public void roundRobin(ArrayList<Strategy> strategies) {
		for (int i = 0; i < strategies.size() - 1; i++) {
			for (int j = i + 1; j < strategies.size(); j++) {
				Strategy a = strategies.get(i);
				Strategy b = strategies.get(j);
				play(a, b);
				a.getInfo().reset();
				b.getInfo().reset();
			}
		}
	}
	
	/**
	 * Play a game (150 rounds) of IDP with Strategy a and Strategy b
	 * @param a
	 * @param b
	 */
	public void play(Strategy a, Strategy b) {
		a.reinit();
		b.reinit();
		for (int i = 0; i < 150; i++) {
			int aMove = a.getMove();
			int bMove = b.getMove();
			a.getInfo().updateRound(aMove, bMove);
			b.getInfo().updateRound(bMove, aMove);
		}
//		System.out.println("-------------------------");
//		System.out.println(a);
//		System.out.println(a.getInfo().toStringCur());
//		System.out.println(b);
//		System.out.println(b.getInfo().toStringCur());
//		System.out.println(Arrays.toString(a.getInfo().getSelfMoves()));
//		System.out.println(Arrays.toString(b.getInfo().getSelfMoves()));
	}
	
	public static void main(String[] args) {
		new Game();
	}

}
