/**
 * Manages all the logic of the tournament and IPD
 * 
 * @author Sahee Thao
 * @date 01/15/20
 * @version 1.2.0
 */
package game;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Properties;

import strat.Strategy;

public class Game {

    private ArrayList<Strategy> strategies;
    private final String DECORATION = "---";
    private int trials = 300;
    public Game() {
        ArrayList<String> results = new ArrayList<String>();
        Properties prop = new Properties();
        InputStream input = null;
        String[] ignore = null;
        
        try {

            input = new FileInputStream("config.properties");

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            String exclude = prop.getProperty("exclude");
            if (exclude != null) {
                ignore = exclude.split(",");
            }
            
            String trialsStr = prop.getProperty("trials");
            if (trialsStr != null) {
                trials = Integer.parseInt(trialsStr);
            }
            System.out.println("Trials: " + trials);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
       
        
        ArrayList<String> ignoreList = new ArrayList<String>();
        ignoreList.add("Strategy");
        if (ignore != null) {
            ignoreList.addAll(Arrays.asList(ignore));
        }

        File[] files = new File("src/strat").listFiles();
        for (File file : files) {
            if (file.isFile() && ignoreList != null && !ignoreList.contains(file.getName())) {
                String name = file.getName();
                results.add(name.substring(0, name.length() - 5)); // Remove ".java"
            }
        }
        System.out.println("Strategies found: " + results);
        System.out.println("Excluding: " + ignoreList);

        int i;
        createStrategies(results, ignoreList);
        
        System.out.println("\n" + DECORATION + " Round Robin Tournament " + DECORATION);
        System.out.println();
        for (i = 0; i < trials; i++) {
            roundRobin(strategies);
        }
        for (Strategy s : strategies) {
            s.getInfo().calculateMeans();
        }

        /* Self Score */
        System.out.println(DECORATION + " Self Score " + DECORATION);
        //strategies.sort((a, b) -> (int) Math.round(b.getInfo().getMeanSelfScore() - a.getInfo().getMeanSelfScore()));
        strategies.sort((a, b) -> new Double(b.getInfo().getMeanSelfScore()).compareTo(new Double(a.getInfo().getMeanSelfScore())));
        for (Strategy s : strategies) {
            System.out.printf("%6.2f %s\n", s.getInfo().getMeanSelfScore(), s);
        }
        System.out.println();
//
//        strategies.sort((a, b) -> (int) Math.round(a.getInfo().getMeanSelfScore() - b.getInfo().getMeanSelfScore()));
//        System.out.println("Min Self Score");
//        for (Strategy s : strategies) {
//            System.out.printf("%6.2f %s\n", s.getInfo().getMeanSelfScore(), s);
//        }
//        System.out.println();

        /* Opp Score */
        System.out.println(DECORATION + " Opp Score " + DECORATION);
        strategies.sort((a, b) -> new Double(b.getInfo().getMeanOppScore()).compareTo(new Double(a.getInfo().getMeanOppScore())));
        for (Strategy s : strategies) {
            System.out.printf("%6.2f %s\n", s.getInfo().getMeanOppScore(), s);
        }
        System.out.println();

//        strategies.sort((a, b) -> (int) Math.round(a.getInfo().getMeanOppScore() - b.getInfo().getMeanOppScore()));
//        System.out.println("Min Opp Score");
//        for (Strategy s : strategies) {
//            System.out.printf("%6.2f %s\n", s.getInfo().getMeanOppScore(), s);
//        }
//        System.out.println();

        /* Score Diff */
        System.out.println(DECORATION + " Score Diff " + DECORATION);

        strategies.sort((a, b) -> new Double(b.getInfo().getMeanScoreDiff()).compareTo(a.getInfo().getMeanScoreDiff()));
        for (Strategy s : strategies) {
            System.out.printf("%6.2f %s\n", s.getInfo().getMeanScoreDiff(), s);
        }
        System.out.println();

//        strategies.sort((a, b) -> (int) Math.round(a.getInfo().getMeanScoreDiff() - b.getInfo().getMeanScoreDiff()));
//        System.out.println("Min Score Diff");
//        for (Strategy s : strategies) {
//            System.out.printf("%6.2f %s\n", s.getInfo().getMeanScoreDiff(), s);
//        }
//        System.out.println();
        
        /* Score Abs Diff */
        System.out.println(DECORATION + " Score Abs Diff " + DECORATION);

        strategies.sort((a, b) -> (int) Math.round(
                new Double(Math.abs(b.getInfo().getMeanScoreDiff())).compareTo(new Double(Math.abs(a.getInfo().getMeanScoreDiff())))
                ));
        for (Strategy s : strategies) {
            System.out.printf("%6.2f %s\n", Math.abs(s.getInfo().getMeanScoreDiff()), s);
        }
        System.out.println();

        /* Max R */
        System.out.println(DECORATION + " Payoff R " + DECORATION);

        strategies.sort((a, b) -> new Double(b.getInfo().getMeanR()).compareTo(new Double(a.getInfo().getMeanR())));
        for (Strategy s : strategies) {
            System.out.printf("%6.2f %s\n", s.getInfo().getMeanR(), s);
        }
        System.out.println();

        /* Max S */
        System.out.println(DECORATION + " Payoff S " + DECORATION);

        strategies.sort((a, b) -> new Double(b.getInfo().getMeanS()).compareTo(new Double(a.getInfo().getMeanS())));
        for (Strategy s : strategies) {
            System.out.printf("%6.2f %s\n", s.getInfo().getMeanS(), s);
        }
        System.out.println();

        /* Max T */
        System.out.println(DECORATION + " Payoff T " + DECORATION);

        strategies.sort((a, b) -> new Double(b.getInfo().getMeanT()).compareTo(new Double(a.getInfo().getMeanT())));
        for (Strategy s : strategies) {
            System.out.printf("%6.2f %s\n", s.getInfo().getMeanT(), s);
        }
        System.out.println();

        /* Max P */
        System.out.println(DECORATION + " Payoff P " + DECORATION);

        strategies.sort((a, b) -> new Double(b.getInfo().getMeanP()).compareTo(new Double(a.getInfo().getMeanP())));
        for (Strategy s : strategies) {
            System.out.printf("%6.2f %s\n", s.getInfo().getMeanP(), s);
        }
        System.out.println();
    }
    
    private void createStrategies(ArrayList<String> results, ArrayList<String> ignoreList) {
        strategies = new ArrayList<Strategy>();

        for (String stratClassName : results) {
            if (ignoreList.contains(stratClassName)) {
                continue;
            }
            Strategy s = null;

            try {
                s = (Strategy) Class.forName("strat." + stratClassName).newInstance();
                s.setId(1);
                strategies.add(s);
                s = (Strategy) Class.forName("strat." + stratClassName).newInstance();
                s.setId(2);
                 strategies.add(s);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        Collections.shuffle(strategies);
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
     * 
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
    }

    public static void main(String[] args) {
        new Game();
    }

}
