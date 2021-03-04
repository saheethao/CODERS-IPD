package strat;

// import Helpers.FileHelper;

public class Bennett_Static_Genetic extends Strategy {

    protected int curMove;
    protected boolean[] moves = {false, true, false, false, true, false, true, false, true, true, true, false, false, false, false, true, false, true, false, false, true, false, true, false, true, true, false, false, true, false, true, true, true, true, false, false, true, false, true, true, false, true, false, false, true, false, false, true, false, true, true, false, false, true, true, false, true, true, false, true, false, false, false, false, true, true, false, false, true, true, true, false, false, true, false, true, false, true, false, true, false, false, false, false, false, true, true, true, false, true, true, false, true, false, true, false, true, false, true, false, false, false, false, true, false, false, false, true, true, true, true, true, true, true, false, true, false, true, false, false, false, true, false, false, true, false, true, false, true, true, true, false, false, true, true, false, false, false, true, true, false, true, false, true, false, true, false, true, true, true};
    // protected boolean[] moves;

    protected final int DEFECT = 1;
    protected final int COOPERATE = 0;

    public Bennett_Static_Genetic() {
        super();
        curMove = 0;
    }

    /**
     * Plays off the pre-defined set of moves in moves.txt.
     * This file should be generated using the Static_Genetic_Alg.java in the
     * Helpers directory.
     * 
     * It is now adapted to play off the predefined array above, rather than from the file.
     * 
     * I discovered that this strategy doesn't work super well, but it was interesting to work on and I thought it would
     * be a fun experiment. It also turns out that it is still better than random, so in theory it could get better!
     * DM me on Discord @ Benato12#9709 if you are intersted in the backend code for training this.
     * 
     * @return an integer. Should be 1 or 0 corresponding to defect or cooperate
     * respectively.
     */
    protected int generateMove(){

        int curMove = info.getRoundsPlayed();

        // If there is no data in the array, then defect.
        // This means that there was likely some error with the algorithm and that
        // there is no actual data in the file.
        if(moves.length == 0){
            return DEFECT;
        }

        // If the file at this point says true, defect. Else, cooperate
        if(moves[curMove]){
            return DEFECT;
        } else {
            return COOPERATE;
        }
        
    }

    protected String getName() {
        return "Static Genetic Algorithm";
    }

    protected String getAuthor() {
        return "Bennett Wendorf";
    }

    public void reinit() {
        curMove = 0;

        // Pulls the moves from the FileHelper
        // if(moves == null){
            // moves = FileHelper.getMoves();
        // }
    }
    
}
