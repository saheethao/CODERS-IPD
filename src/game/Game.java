package game;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

import strat.Strategy;

public class Game {

    private ArrayList<Strategy> strategies;

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

        strategies = new ArrayList<Strategy>();

        int i = 0;
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
        strategies.sort((a, b) -> (int) Math.round(b.getInfo().getMeanSelfScore() - a.getInfo().getMeanSelfScore()));
        System.out.println("Max Self Score");
        for (Strategy s : strategies) {
            System.out.printf("%6.2f %s\n", s.getInfo().getMeanSelfScore(), s);
        }
        System.out.println();

        strategies.sort((a, b) -> (int) Math.round(a.getInfo().getMeanSelfScore() - b.getInfo().getMeanSelfScore()));
        System.out.println("Min Self Score");
        for (Strategy s : strategies) {
            System.out.printf("%6.2f %s\n", s.getInfo().getMeanSelfScore(), s);
        }
        System.out.println();

        /* Opp Score */
        strategies.sort((a, b) -> (int) Math.round(b.getInfo().getMeanOppScore() - a.getInfo().getMeanOppScore()));
        System.out.println("Max Opp Score");
        for (Strategy s : strategies) {
            System.out.printf("%6.2f %s\n", s.getInfo().getMeanOppScore(), s);
        }
        System.out.println();

        strategies.sort((a, b) -> (int) Math.round(a.getInfo().getMeanOppScore() - b.getInfo().getMeanOppScore()));
        System.out.println("Min Opp Score");
        for (Strategy s : strategies) {
            System.out.printf("%6.2f %s\n", s.getInfo().getMeanOppScore(), s);
        }
        System.out.println();

        /* Score Diff */
        strategies.sort((a, b) -> (int) Math.round(b.getInfo().getMeanScoreDiff() - a.getInfo().getMeanScoreDiff()));
        System.out.println("Max Score Diff");
        for (Strategy s : strategies) {
            System.out.printf("%6.2f %s\n", s.getInfo().getMeanScoreDiff(), s);
        }
        System.out.println();

        strategies.sort((a, b) -> (int) Math.round(a.getInfo().getMeanScoreDiff() - b.getInfo().getMeanScoreDiff()));
        System.out.println("Min Score Diff");
        for (Strategy s : strategies) {
            System.out.printf("%6.2f %s\n", s.getInfo().getMeanScoreDiff(), s);
        }
        System.out.println();

        /* Max R */
        strategies.sort((a, b) -> (int) Math.round(b.getInfo().getMeanR() - a.getInfo().getMeanR()));
        System.out.println("Max R");
        for (Strategy s : strategies) {
            System.out.printf("%6.2f %s\n", s.getInfo().getMeanR(), s);
        }
        System.out.println();

        /* Max S */
        strategies.sort((a, b) -> (int) Math.round(b.getInfo().getMeanS() - a.getInfo().getMeanS()));
        System.out.println("Max S");
        for (Strategy s : strategies) {
            System.out.printf("%6.2f %s\n", s.getInfo().getMeanS(), s);
        }
        System.out.println();

        /* Max T */
        strategies.sort((a, b) -> (int) Math.round(b.getInfo().getMeanT() - a.getInfo().getMeanT()));
        System.out.println("Max T");
        for (Strategy s : strategies) {
            System.out.printf("%6.2f %s\n", s.getInfo().getMeanT(), s);
        }
        System.out.println();

        /* Max P */
        strategies.sort((a, b) -> (int) Math.round(b.getInfo().getMeanP() - a.getInfo().getMeanP()));
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
